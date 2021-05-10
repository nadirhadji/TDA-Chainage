/**
 * Mon teste de charge prend environ 40 secondes.
 */
public class ListeMilieu< E extends Comparable< E > > {

    private Chainon<E> inferieur;
    private Chainon<E> superieur;
    private int tailleInferieur;
    private int tailleSuperieur;

    /**
     * Initialise une {@code ListeMilieu} avec les chainons inferieur et superieur null.
     */
    public ListeMilieu() {
        this.inferieur = null;
        this.superieur = null;
        this.tailleInferieur = 0;
        this.tailleSuperieur = 0;
    }

    public void setInferieur(Chainon<E> inferieur) {
        this.inferieur = inferieur;
    }

    public void setSuperieur(Chainon<E> superieur) {
        this.superieur = superieur;
    }

    public void setTailleInferieur(int tailleInferieur) {
        this.tailleInferieur = tailleInferieur;
    }

    public void setTailleSuperieur(int tailleSuperieur) {
        this.tailleSuperieur = tailleSuperieur;
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

        Chainon<E> sup = this.superieur;

        try {
            modifierResultat(sup,resultat);
            modifierCourant();
        } catch (ExceptionPileVide exceptionPileVide) {
            exceptionPileVide.printStackTrace();
        }

        return resultat;
    }

    private void modifierResultat(Chainon<E> sup, ListeMilieu<E> resultat) throws ExceptionPileVide {

        Pile<Chainon<E>> pile = new Pile<>();
        int nbSuperieur = tailleSuperieur;
        //l'indice de début de la seconde moitiée des élements de chainon
        int limite = getIndiceMilieuChaine(sup);

        while (limite > 0) {
            pile.empiler(sup);
            sup = sup.getSuivant();
            limite--;
            nbSuperieur--;
        }

        resultat.setSuperieur(sup);
        resultat.setTailleSuperieur(nbSuperieur);

        if(pile.estVide()){
            resultat.setInferieur(null);
            resultat.setTailleInferieur(0);
        }
        else {
            resultat.setTailleInferieur(pile.getTaille());
            resultat.setInferieur(inverserChaine(pile));
        }

        resultat.equilibrerListeMilieu();
    }

    /**
     * Empiler la premiere moitier de liste superieur et retourner la pile. Puis affecter
     * a superieur courant la deuxieme moitier de superieur.
     *
     * @return une pile contenant la premiere moitier des chainons
     */
    private void modifierCourant() throws ExceptionPileVide {

        Pile<Chainon<E>> pile = new Pile<>();
        int nbSuperieur = 0;
        //l'indice de début de la seconde moitiée des élements de chainon
        int limite = getIndiceMilieuChaine(inferieur);

        while (limite > 0) {
            pile.empiler(inferieur);
            inferieur = inferieur.getSuivant();
            limite--;
            tailleInferieur--;
            nbSuperieur++;
        }

        if (!pile.estVide()) {
            superieur = inverserChaine(pile);
            tailleSuperieur = nbSuperieur;
        }
        else {
            superieur = null;
            tailleSuperieur = 0;
        }

        equilibrerListeMilieu();
    }

    /**
     * Former une liste chainée a partir des elements dans une {@code Pile}
     *
     * @param pile une pile de donnée de type FILO {@code Pile}
     * @return ListeChainée nouvellement formé
     * @throws ExceptionPileVide exception lancé si la pile est vide
     */
    private Chainon<E> inverserChaine(Pile<Chainon<E>> pile) throws ExceptionPileVide {

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

        return teteDeChaine;
    }

    /**
     * Inserer un element dans la liste milieu courante
     *
     * @param valeur la valeur a ajouter dans la liste milieu
     */
    public void inserer( E valeur ) {

        if(inferieur == null) {
            inferieur = new Chainon<E>(valeur);
            tailleInferieur++;
        }

        else if ( inferieur.getElement().compareTo(valeur) > 0 ){
            insererInf(valeur);
        }

        else if (superieur == null){
            if (inferieur.getElement().compareTo(valeur) != 0) {
                superieur = new Chainon<E>(valeur);
                tailleSuperieur++;
            }
        }

        else {
            insererSup(valeur);
        }

        equilibrerListeMilieu();

    }

    /**
     * Preserver la taille de inferieur et superieur egale ou sinon liste inferieur avec un element de plus.
     */
    public void equilibrerListeMilieu() {

        int tailleDiff = tailleInferieur - tailleSuperieur;

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
        boolean egaliteTrouve = false;

        while(courant != null && courant.getElement().compareTo(valeur) < 0 ) {

            if( courant.getElement().compareTo(valeur) == 0)
                egaliteTrouve = true;

            ombre = courant;
            courant = courant.getSuivant();
        }

        if(!egaliteTrouve) {
            Chainon<E> nouveau = new Chainon<>(valeur);

            if(ombre == null) {
                nouveau.setSuivant(courant);
                superieur = nouveau;
            }
            else {
                ombre.setSuivant(nouveau);
                nouveau.setSuivant(courant);
            }
            tailleSuperieur++;
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
        boolean egaliteTrouve = false;

        while(courant != null && courant.getElement().compareTo(valeur) > 0 ) {

            if(courant.getElement().compareTo(valeur) == 0)
                egaliteTrouve = true;
            ombre = courant;
            courant = courant.getSuivant();
        }

        if(!egaliteTrouve) {
            Chainon<E> nouveau = new Chainon<>(valeur);

            if(ombre == null) {
                nouveau.setSuivant(courant);
                inferieur = nouveau;
            }
            else {
                ombre.setSuivant(nouveau);
                nouveau.setSuivant(courant);
            }
            tailleInferieur++;
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
        tailleInferieur--;
        tailleSuperieur++;
    }

    /**
     * Deplacer le premier élement de la liste superieur vers le premier élement de la liste inferieur
     */
    public void deplacerMinSuperieur() {
        Chainon<E> minSuperieur = superieur;
        superieur = superieur.getSuivant();
        minSuperieur.setSuivant(inferieur);
        inferieur = minSuperieur;
        tailleInferieur++;
        tailleSuperieur--;
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

        if(inferieur != null && contient(valeur,inferieur)){
            supprimerInf(valeur);
            tailleInferieur--;
        }

        else if(superieur != null && contient(valeur,superieur)){
            supprimerSup(valeur);
            tailleSuperieur--;
        }

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

    public boolean contient(E valeur) {

        boolean dansInferieur = contient(valeur,inferieur);
        boolean dansSuperieur = contient(valeur,superieur);

        return dansInferieur || dansSuperieur;
    }

    /**
     * Verifier qu'un element est contenue dans une liste chainée
     *
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
        return (tailleSuperieur+tailleInferieur);
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

        if( (taille >= 2) && (taille % 2 != 0) )
            resultat = resultat + 1;

        return resultat;
    }
}
