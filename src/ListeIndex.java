/**
 *  * @nom Hadji Nadir
 *  * @code_permanent HADN08069703
 *  *
 *
 * Mon teste de charge prend environ 45 secondes.
 */
public class ListeIndex< E extends Comparable< E > > {

    private Chainon<ListeMilieu<E>> index;
    private int nbrListe;

    /***
     * Construire une ListeIndex
     */
    public ListeIndex() {
        this.index = null;
        this.nbrListe = 0;
    }

    /**
     * Verifier si une valeur est contenue dans la ListeIndex courante
     * @param valeur la valeur a chercher dans l'object courant
     * @return vrai si valeur existe sinon faux
     */
    public boolean contient(E valeur ) {

        boolean resultat = false;
        Chainon<ListeMilieu<E>> index = this.index;

        while (index != null && !resultat) {
            resultat = index.getElement().contient(valeur);
            index = index.getSuivant();
        }

        return resultat;
    }

    /**
     * Recuperer une ListeIndex a l'index i en partant de 0
     * @param i l'indice de la ListeIndex a retourner
     * @return la ListeIndex a la position i
     */
    public ListeMilieu<E> get( int i ) {

        ListeMilieu<E> resultat = null;
        Chainon<ListeMilieu<E>> courant = index;

        if( i == 0 )
            resultat = index.getElement();
        else {

            while (courant != null && i >= 0) {
                resultat = courant.getElement();
                courant = courant.getSuivant();
                i--;
            }
        }

        return resultat;
    }

    /**
     * Inserer une valeur dans la ListeMilieu appropriee de la ListeIndex courante
     * @param valeur la valeur a inserer
     */
    public void inserer( E valeur ) {

        if (index == null) {

            index = new Chainon<>(new ListeMilieu<>());
            index.getElement().inserer(valeur);
            nbrListe++;

        } else if (nbrListe == 1 || index.getSuivant().getElement().minima().compareTo(valeur) > 0 ) {

            index.getElement().inserer(valeur);
            insererChainon(index);
        }
        else {
            insererChainon(index,index.getSuivant(),valeur);
        }
    }

    /**
     * Verifier si la taille d'une ListeMilieu apres une insertion respecte toujours l'invarient #1
     * Si oui , aucune action n'est appliquee sinon, divise la ListeMilieu en argument et insere la nouvelle
     * ListeMilieu a la suite de celle mise en argument.
     * @param chainon la ListeMilieu sur quoi appliquer le teste.
     */
    private void insererChainon(Chainon<ListeMilieu<E>> chainon) {

        if (chainon != null && chainon.getElement().taille() > (2 * nbrListe())) {

            ListeMilieu<E> nouveau = chainon.getElement().diviser();
            Chainon<ListeMilieu<E>> nouveauChainon = new Chainon<>(nouveau);

            nouveauChainon.setSuivant(chainon.getSuivant());
            chainon.setSuivant(nouveauChainon);
            nbrListe++;
        }
    }

    /**
     * Parcourir la ListeIndexe courante afin d'inserer valeur a la bonne ListeMilieu
     *
     * @param courant La ListeMilieu courante dans quoi inserer.
     * @param suivant La ListeMilieu suivante avec quoi verifier la valeur minimum.
     * @param valeur la valeur a inserer.
     */
    private void insererChainon(Chainon<ListeMilieu<E>> courant, Chainon<ListeMilieu<E>> suivant, E valeur ) {

        boolean enCoursDeRecherche = true;

        while (enCoursDeRecherche ) {

            if(suivant == null && courant.getElement().minima().compareTo(valeur) <= 0 ) {

                enCoursDeRecherche = false;
                courant.getElement().inserer(valeur);
                insererChainon(courant);
            }

            else if (courant.getElement().minima().compareTo(valeur) <= 0 &&
                    suivant.getElement().minima().compareTo(valeur) > 0) {

                enCoursDeRecherche = false;
                courant.getElement().inserer(valeur);
                insererChainon(suivant);

            }

            else {
                courant = suivant;
                suivant = suivant.getSuivant();
            }
        }
    }

    /**
     * Retourner le nombre de liste dans la {@link ListeIndex} courante
     *
     * @return le nombre de ListeMilieu dans la ListeIndex courante
     */
    public int nbrListe() {
        return nbrListe;
    }

    /**
     * Supprimer une valeur de la ListeIndexe courqnte si elle existe
     * @param valeur la valeur a supprimer
     */
    public void supprimer( E valeur ) {

        boolean enCoursDeRecherche = true;
        Chainon<ListeMilieu<E>> courant = index;
        Chainon<ListeMilieu<E>> suivant = index.getSuivant();

        if( nbrListe == 1 || index.getSuivant().getElement().minima().compareTo(valeur) > 0 ) {
            index.getElement().supprimer(valeur);
        }
        else if (nbrListe > 1){

            while (enCoursDeRecherche ) {

                if(suivant == null && courant.getElement().minima().compareTo(valeur) <= 0 ) {

                    enCoursDeRecherche = false;
                    courant.getElement().supprimer(valeur);

                }

                else if (courant.getElement().minima().compareTo(valeur) <= 0 &&
                        suivant.getElement().minima().compareTo(valeur) > 0) {

                    enCoursDeRecherche = false;
                    courant.getElement().supprimer(valeur);
                }

                else {
                    courant = suivant;
                    suivant = suivant.getSuivant();
                }
            }
        }
    }

    /**
     * Calculer le nombre d'element au total dans la ListeIndex courante
     * @return la somme du nombre de valeur de chaque listeMilieu de la ListeIndex courante
     */
    public int taille() {

        int resultat = 0;

        for (int i = 0 ; i < nbrListe ; i++)
            resultat = resultat + get(i).taille();

        return resultat;
    }
}
