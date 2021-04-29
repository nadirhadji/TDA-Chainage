/**
 *
 * @param <E>
 */
public class ListeMilieu< E extends Comparable< E > > {

    private Chainon<E> inferieur;
    private Chainon<E> superieur;

    /**
     * Initialise une {@code ListeMilieu} avec les chainons inferieur et superieur null.
     */
    public ListeMilieu() {
        this.inferieur = null;
        this.superieur = null;
    }

    /**
     * Construit une nouvelle {@code ListeMilieu} à partir des élements de la liste courante.
     *
     * Les élements de la liste inferieur serviront a créer la nouvelle {@code ListeMilieu}
     * dite resultat dans cette méthode.
     *
     * Ensuite la {@code ListeMilieu} courante sera modifiée et ne contiendra que les élements
     * de la liste supérieur. La moitiées des élements les plus grand resteront dans la liste
     * superieurs courante. La premiere moitiers des elements plus petit seront déplacé vers
     * la liste inférieur de l'élement courant.
     *
     * La {@methode remanier} explique comment la transformation ce fait.
     *
     * @return une {@code ListeMilieu} constituée grace au elements (inferieur) de la liste courante.
     */
    public ListeMilieu<E> diviser() {

        ListeMilieu<E> resultat = new ListeMilieu<>();

        Chainon<E> inf = inferieur;
        Chainon<E> sup = superieur;

        remanier(this,sup);
        remanier(resultat,inf);

        return resultat;
    }

    /**
     * Transforme une {@code ListeMilieu} avec le {@code Chainon} en argument.
     *
     * Cette méthode est utilisé par la {@methode diviser} de {@code ListeMilieu}.
     * Deux cas sont pris en compte par la méthode suivante :
     *          cas 1 - {@code ListeMilieu} vide , {@code Chainon} non vide
     *          cas 2 - {@code ListeMilieu} non vide , {@code Chainon} non vide
     *
     * Dans le cas 1 :
     *      On va tranformer la ListeMilieu dite 'resultat' retourné par la
     *      {@code ListeMilieu.diviser()}. Au début, 'resultat' est vide et on
     *      veut créer une nouvelle ListeMilieu avec la liste inferieur courante.
     *
     * Dans le cas 2 :
     *      On va transformer la ListeMilieu courante pour que la moitier des
     *      élements les plus grand de liste superieur reste dans liste superieur
     *      et que la moitier des élements les plus petit de liste superieurs
     *      soit maintenant liée a la liste inferieur et ce classée du plus grand
     *      au plus petit.
     *
     * @param listeMilieu la {@code ListeMilieu} à tranformer
     * @param chainon le {@code Chainon} avec quoi tranformer listeMilieu
     */
    private void remanier(ListeMilieu<E> listeMilieu, Chainon<E> chainon) {

        Pile<Chainon<E>> pile = new Pile<>();

        //l'indice de début de la seconde moitiée des élements de chainon
        int limite = getIndiceMilieuChaine(chainon);

        /*On empile la premiere moitiée de chainon dans une pile de type FILO
        pour pouvoir ensuite les liée dans le sens inverse.
        */
        while (limite > 0) {
            pile.empiler(chainon);
            chainon = chainon.getSuivant();
            limite--;
        }

        /*
         Puisque chainon pointe maintenant vers le premier élement de la deuxieme moitier
         du chainon initiale, on peut l'affecter a listeMilieu.inferieur si dans la methode
         appelante chainon fait reference a listeMilieu.inferieur. ou sinon a
         listeMilieu.superieur dans le cas inverse
         */
        if(listeMilieu.inferieur == null)
            listeMilieu.inferieur = chainon;
        else
            listeMilieu.superieur = chainon;

        /*
        Cette partie va s'occuper de depiler 1 a 1 les élements de la pile pour constituer
        une liste chainée dans le sens inverse.
         */
        try {
            Chainon<E> aux = pile.depiler();
            Chainon<E> teteDeChaine = aux;
            Chainon<E> buff = null;

            while (!pile.estVide()) {
                buff = pile.depiler();
                aux.setSuivant(buff);
                aux = buff;
            }

            //Faire pointer le dernier element de la chaine vers null pour marquer la fin de la chaine.
            aux.setSuivant(null);

            /*
            Si la fonction appelante fait reference a listeMilieu.superieur en ce qui conserne chainon,
            alors la nouvelle liste chainée constituée sera placé dans listeMilieu.inferieur.
            Si la fonction appelante fait reference a listeMilieu.inferieur en ce qui conserne chainon,
            alors la nouvelle liste chainée constituée sera placé dans listeMilieu.superieur.
            */
            if(listeMilieu.superieur == null)
                listeMilieu.superieur = teteDeChaine;
            else
                listeMilieu.inferieur = teteDeChaine;

        } catch (ExceptionPileVide exceptionPileVide) {
            exceptionPileVide.printStackTrace();
        }
    }

    /**
     * Inserer un element dans la liste milieu courante
     *
     * @param valeur la valeur a ajouter dans la liste milieu
     */
    public void inserer( E valeur ) {

        int tailleDiff;

        if(inferieur == null)
            inferieur = new Chainon<E>(valeur);

        else if ( inferieur.getElement().compareTo(valeur) > 0 )
            insererInf(valeur);

        else if (superieur == null)
            superieur = new Chainon<E>(valeur);

        else
            insererSup(valeur);

        equilibrerListeMilieu();

    }

    /**
     * Preserver la taille de inferieur et superieur egale ou sinon liste inferieur avec un element de plus.
     */
    public void equilibrerListeMilieu() {

        int tailleDiff = taille(inferieur) - taille(superieur);

        if(tailleDiff > 1)
            deplacerMaxInferieur();
        else if (tailleDiff == -1)
            deplacerMinSuperieur();
    }

    /**
     * Inserer un élement dans une liste chainée triée en ordre croissant
     *
     * @param valeur un element de type E
     */
    public void insererSup( E valeur) {

        Chainon<E> ombre = null;
        Chainon<E> courant = superieur;

        while(courant != null && courant.getElement().compareTo(valeur) < 0 ) {
            ombre = courant;
            courant = courant.getSuivant();
        }

        Chainon<E> nouveau = new Chainon<>(valeur);

        if(ombre == null) {
            nouveau.setSuivant(courant);
            superieur = nouveau;
        }
        else {
            ombre.setSuivant(nouveau);
            nouveau.setSuivant(courant);
        }
    }

    /**
     * Inserer un élement dans une liste chainée triée en ordre decroissant
     *
     * @param valeur
     */
    public void insererInf( E valeur) {

        Chainon<E> ombre = null;
        Chainon<E> courant = inferieur;

        while(courant != null && courant.getElement().compareTo(valeur) > 0 ) {
            ombre = courant;
            courant = courant.getSuivant();
        }

        Chainon<E> nouveau = new Chainon<>(valeur);

        if(ombre == null) {
            nouveau.setSuivant(courant);
            inferieur = nouveau;
        }
        else {
            ombre.setSuivant(nouveau);
            nouveau.setSuivant(courant);
        }
    }

    /**
     * Deplacer le premier élement de la liste infereur vers le premier élement de la liste superieur
     */
    public void deplacerMaxInferieur() {
        Chainon<E> maxInferieur = inferieur;
        inferieur = inferieur.getSuivant();
        maxInferieur.setSuivant(superieur);
        superieur = maxInferieur;
    }

    /**
     * Deplacer le premier élement de la liste superieur vers le premier élement de la liste inferieur
     */
    public void deplacerMinSuperieur() {
        Chainon<E> minSuperieur = superieur;
        superieur = superieur.getSuivant();
        minSuperieur.setSuivant(inferieur);
        inferieur = minSuperieur;
    }

    /**
     * Retourne la première valeur de la liste inférieure
     *
     * @return
     */
    public E milieu() {

        E resultat = null;

        if(inferieur != null)
            resultat = inferieur.getElement();

        return resultat;
    }

    /**
     * Retourne la dernière valeur de la liste inférieure
     *
     * @return
     */
    public E minima() {

        E resultat = null;
        Chainon<E> buffer = inferieur;

        while(buffer != null) {
            resultat = buffer.getElement();
            buffer = buffer.getSuivant();
        }

        return resultat;
    }

    /**
     * Retourne la dernière valeur de la liste supérieure si elle n’est pas vide, sinon elle retourne la
     * première valeur de la liste inférieure.
     *
     * @return
     */
    public E maxima() {

        E resultat = null;
        Chainon<E> buffer = superieur;

        if (superieur != null) {

            while(buffer != null) {
                resultat = buffer.getElement();
                buffer = buffer.getSuivant();
            }
        }
        else
            resultat = milieu();

        return resultat;
    }

    /**
     * Supprimer un element dans la ListeMilieu courante si il existe
     *
     * @param valeur valeur a supprimer
     */
    public void supprimer( E valeur) {

        if(inferieur != null && contient(valeur,inferieur))
            supprimerInf(valeur);

        else if(superieur != null && contient(valeur,superieur))
            supprimerSup(valeur);

        equilibrerListeMilieu();
    }

    /**
     * Supprimer un element dans la liste inferieur
     *
     * @param valeur element a supprimer de la liste inferieur
     */
    public void supprimerInf(E valeur) {

        if (inferieur.getElement().compareTo(valeur) == 0) {
            inferieur = inferieur.getSuivant();
        } else {
            Chainon<E> precedent = inferieur;
            Chainon<E> courant = inferieur.getSuivant();
            detacherChainon(precedent,courant,valeur);
        }
    }

    /**
     * Supprimer un element dans la liste superieur
     *
     * @param valeur element a supprimer de la liste superieur
     */
    public void supprimerSup(E valeur) {

        if (superieur.getElement().compareTo(valeur) == 0) {
            superieur = superieur.getSuivant();
        } else {
            Chainon<E> precedent = superieur;
            Chainon<E> courant = superieur.getSuivant();
            detacherChainon(precedent,courant,valeur);
        }
    }

    /**
     * Détache le chainon qui contient l'element égale à la valeur donnée en argument
     *
     * @param precedent
     * @param courant
     * @param valeur
     */
    public void detacherChainon(Chainon<E> precedent, Chainon<E> courant, E valeur) {

        while (courant != null && courant.getElement().compareTo(valeur) != 0) {
            precedent = courant;
            courant = courant.getSuivant();
        }

        if (courant != null) {
            precedent.setSuivant(courant.getSuivant());
        }
    }

    /**
     * Verifier qu'un element est contenue dans une liste chainée
     * @param valeur
     * @param premierChainon
     * @return
     */
    public boolean contient(E valeur, Chainon<E> premierChainon) {
        boolean trouve = false;
        Chainon<E> courant = premierChainon;

        while (! trouve && courant != null) {
            if (courant.getElement().compareTo(valeur) == 0 ) {
                trouve= true;
            } else {
                courant= courant.getSuivant();
            }
        }

        return trouve;
    }

    /**
     * Trouver le nombre totale d'element dans l'object {@code ListeMilieu} courant.
     * @return le nombre d'element dans la ListeMilieu courante
     */
    public int taille() {
        return taille(inferieur) + taille(superieur);
    }

    /**
     * Trouver combien d'elements sont chainée au chainon en argument.
     *
     * @param chainon le chainon de depart
     * @return le nombre d'element suivant le chainon
     */
    public int taille(Chainon<E> chainon) {

        int resultat = 0;

        while(chainon != null) {
            chainon = chainon.getSuivant();
            resultat = resultat + 1;
        }

        return resultat;
    }

    /**
     * Trouver le point milieu dans une liste chainée.
     *
     * Si le nombre d'élements dans la chaine est paire alors l'indice retournée sera l'indice de début
     * de la deuxiéme portion de la chaine. Exemple: la valeur retourné pour une chaine de taille 4 est
     * 2. Cela veux dire que la deuxieme postion de la chaine commence à l'indice 2 en partant de 0.
     *
     * Si le nombre d'élements dans la chaine est impaire alors la premiere portion de la chaine contiendra
     * un element de plus que la seconde portion. Exemple: la chaine contient 5 elements, alors l'indice
     * retournée sera 3 car c'est l'indice de debut de la seconde portion de la chaine en partant de 0.
     *
     * @param chainon La liste chainée sur laquelle on veux trouver l'indice de milieu.
     * @return l'indice de début de la seconde moitiée de la chaine en argument.
     */
    public int getIndiceMilieuChaine(Chainon<E> chainon) {

        int taille = taille(chainon);
        int resultat = taille / 2;

        if(taille % 2 != 0)
            resultat = resultat + 1;

        return resultat;
    }
}
