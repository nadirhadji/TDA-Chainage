/**
 *
 * @param <E>
 */
public class ListeMilieu< E extends Comparable< E > > {

    private Chainon<E> inferieur;
    private Chainon<E> superieur;

    public void setInferieur(Chainon<E> inferieur) {
        this.inferieur = inferieur;
    }

    public void setSuperieur(Chainon<E> superieur) {
        this.superieur = superieur;
    }

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


    public void inserer( E valeur ) {
    }


    public E milieu() {
        return null;
    }


    public E minima() {
        return null;
    }


    public E maxima() {
        return null;
    }


    public void supprimer( E valeur ) {
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
