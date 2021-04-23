/**
 * Unité de base d'une liste chainée
 *
 * @param <E>
 */
public class Chainon<E> {
    private E element;
    private Chainon<E> suivant = null;

    /**
     * Constructeur d'un chainon
     *
     * @param element l'element a placer dans le chainon
     * @param suivant l'adresse du chainon suivant le chainon courant
     */
    public Chainon(E element, Chainon<E> suivant) {
        this.element = element;
        this.suivant = suivant;
    }

    /**
     * Construire un chainon qui n'est pas reliée a une chaine
     *
     * @param element l'element de type E a placer dans le chainon
     */
    public Chainon(E element) {
        this.element = element;
    }

    /**
     * Recupérer l'element sauvegardé dans le chainon courant
     *
     * @return l'element sauvegardé dans le chainon courant
     */
    public E getElement() {
        return element;
    }

    /**
     * Placer un element de type E dans le le chainon courant
     *
     * @param element un object de type <E>
     */
    public void setElement(E element) {
        this.element = element;
    }

    /**
     * Recupérer l'adresse du chainon qui vien apres le chainon courant
     *
     * @return l'adresse du chainon suivant si il existe, sinon retourne null
     */
    public Chainon<E> getSuivant() {
        return suivant;
    }

    /**
     * Definir dans le chainon courant l'adresse du chainon suivant
     *
     * @param suivant un object de type {@code Chainon}
     */
    public void setSuivant(Chainon<E> suivant) {
        this.suivant = suivant;
    }
}
