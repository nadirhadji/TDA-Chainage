/**
 * La structure de donnée Pile<E> est de type FILO ( First In Last Out ).
 *
 * La Pile suivante permet d'empiler et depiler des objets. Le premier empiler sera le dernier a etre
 * dépilée.
 *
 * @param <E> Type de donnée génerique. Le type de donnée soutenu par cette classe est au choix de son utilisateur
 */
public class Pile<E> {

    private ChainonPile<E> sommet;
    private int taille = 0;

    /**
     * Créer une {@code Pile} vide
     */
    public Pile() {
        this.sommet = null;
    }

    /**
     * Verifie si la pile courante est vide
     *
     * @return True si vide sinon False
     */
    public boolean estVide() {
        return null == sommet;
    }

    /**
     * Récuperer une copie de l'élement contenue dans le {@code ChainonPile} au sommet de la pile courante.
     *
     * Si la pile courante est vide, on lance une exception de type {@code ExceptionPileVide }
     *
     * @return l'element contenu dans le sommet de la pile
     * @throws ExceptionPileVide lancé lorsque la pile est vide
     */
    public E getSommet() throws ExceptionPileVide {
        if( null == sommet ) {
            throw new ExceptionPileVide( "sommet n'existe pas" );
        }

        return sommet.getElement();
    }

    /**
     * Placer au sommet de la pile courante un nouveau {@code ChainonPile} et le lier au précedent.
     * Puis incrémenter la taille de la pile de 1
     *
     * @param valeur un élement de type E a mettre dans la pile
     */
    public void empiler( E valeur ) {
        sommet = new ChainonPile<>( valeur, sommet );
        ++ taille;
    }

    /**
     * Retirer de la pile courante le {@code ChainonPile} a son sommet et retourner son element.
     *
     * @return l'élement contenue dans le {@code ChainonPile} au sommet de la pile
     * @throws ExceptionPileVide lancé lorsque la pile est vide
     */
    public E depiler() throws ExceptionPileVide {
        if( null == sommet ) {
            throw new ExceptionPileVide( "depiler" );
        }

        E resultat = sommet.getElement();
        sommet = sommet.getPrecedant();
        --taille;

        return resultat;
    }

    /**
     * Recuperer la nombre de {@code ChainonPile} present dans la pile suivante
     *
     * @return le nombre d'élement dans la pile
     */
    public int getTaille() {
        return taille;
    }
}
