/**
 * Unité de base d'une pile de type FILO ( First In Last Out )
 *
 * @param <T> Type de donnée génerique, a definir en fonction des besoins de l'utilisateur
 */
public class ChainonPile<T> {

    protected T element;
    protected ChainonPile<T> precedant;

    /**
     * Constructeur d'un Chainon de pile
     *
     * @param element l'object contenu dans le chainon de pile
     * @param precedant l'adresse du {@code ChainonPile} precedant le courant.
     */
    public ChainonPile( T element, ChainonPile<T> precedant ) {
        this.element = element;
        this.precedant = precedant;
    }

    /**
     * Récuperer l'élement contenu dans le {@code ChainonPile} courant
     *
     * @return l'object de type T contenue dans le chainon de pile courant
     */
    public T getElement() {
        return element;
    }

    /**
     * Récuperer le {@code ChaisonPile} précedent le chainon courant dans la pile
     *
     * @return l'adresse du {@code ChaisonPile} précedent
     */
    public ChainonPile<T> getPrecedant() {
        return precedant;
    }
}
