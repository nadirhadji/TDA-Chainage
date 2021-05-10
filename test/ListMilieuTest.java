import org.junit.jupiter.api.Test;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

class ListeMilieuTest {

    //------------------------------------------------
    // méthode pour assister les tests.

    private < E extends Comparable< E > > ListeMilieu< E >
    construire( E ... valeurs ) {
        ListeMilieu< E > resultat = new ListeMilieu<>();

        for( E v : valeurs ) {
            resultat.inserer( v );
        }

        return resultat;
    }

    //------------------------------------------------
    // Test pour la construction

    @Test
    void construire_taille_0() {
        ListeMilieu< Integer > a = construire();

        assertEquals( 0, a.taille() );
    }

    //------------------------------------------------
    // Tests pour l'insertion

    // une valeur
    @Test
    void inserer_uneValeur_taille() {
        ListeMilieu< Integer > a = construire( 4 );

        assertEquals( 1, a.taille() );
    }

    @Test
    void inserer_uneValeur_milieu() {
        ListeMilieu< Integer > a = construire( 4 );

        assertEquals( 4, a.milieu() );
    }

    @Test
    void inserer_uneValeur_minima() {
        ListeMilieu< Integer > a = construire( 4 );

        assertEquals( 4, a.minima() );
    }

    @Test
    void inserer_uneValeur_maxima() {
        ListeMilieu< Integer > a = construire( 4 );

        assertEquals( 4, a.maxima() );
    }

    // deux valeurs
    @Test
    void inserer_deuxValeurs_ordre_taille() {
        ListeMilieu< Integer > a = construire( 4, 8 );

        assertEquals( 2, a.taille() );
    }

    @Test
    void inserer_deuxValeurs_ordre_milieu() {
        ListeMilieu< Integer > a = construire( 4, 8 );

        assertEquals( 4, a.milieu() );
    }

    @Test
    void inserer_deuxValeurs_ordre_minima() {
        ListeMilieu< Integer > a = construire( 4, 8 );

        assertEquals( 4, a.minima() );
    }

    @Test
    void inserer_deuxValeurs_ordre_maxima() {
        ListeMilieu< Integer > a = construire( 4, 8 );

        assertEquals( 8, a.maxima() );
    }

    @Test
    void inserer_deuxValeurs_desordre_taille() {
        ListeMilieu< Integer > a = construire( 8, 4 );

        assertEquals( 2, a.taille() );
    }

    @Test
    void inserer_deuxValeurs_desordre_milieu() {
        ListeMilieu< Integer > a = construire( 8, 4 );

        assertEquals( 4, a.milieu() );
    }

    @Test
    void inserer_deuxValeurs_desordre_minima() {
        ListeMilieu< Integer > a = construire( 8, 4 );

        assertEquals( 4, a.minima() );
    }

    @Test
    void inserer_deuxValeurs_desordre_maxima() {
        ListeMilieu< Integer > a = construire( 8, 4 );

        assertEquals( 8, a.maxima() );
    }

    @Test
    void inserer_deuxValeurs_double_taille() {
        ListeMilieu< Integer > a = construire( 4, 4 );

        assertEquals( 1, a.taille() );
    }

    @Test
    void inserer_deuxValeurs_double_milieu() {
        ListeMilieu< Integer > a = construire( 8, 8 );

        assertEquals( 8, a.milieu() );
    }

    @Test
    void inserer_deuxValeurs_double_minima() {
        ListeMilieu< Integer > a = construire( 8, 8 );

        assertEquals( 8, a.minima() );
    }

    @Test
    void inserer_deuxValeurs_double_maxima() {
        ListeMilieu< Integer > a = construire( 4, 4 );

        assertEquals( 4, a.maxima() );
    }

    // trois valeurs
    @Test
    void inserer_troisValeurs_ordre_taille() {
        ListeMilieu< Integer > a = construire( -4, 0, 12 );

        assertEquals( 3, a.taille() );
    }

    @Test
    void inserer_troisValeurs_ordre_milieu() {
        ListeMilieu< Integer > a = construire( -4, 0, 12 );

        assertEquals( 0, a.milieu() );
    }

    @Test
    void inserer_troisValeurs_ordre_minima() {
        ListeMilieu< Integer > a = construire( -4, 0, 12 );

        assertEquals( -4, a.minima() );
    }

    @Test
    void inserer_troisValeurs_ordre_maxima() {
        ListeMilieu< Integer > a = construire( -4, 0, 12 );

        assertEquals( 12, a.maxima() );
    }

    @Test
    void inserer_troisValeurs_iii_taille() {
        ListeMilieu< Integer > a = construire( 12, 5, 2 );

        assertEquals( 3, a.taille() );
    }

    @Test
    void inserer_troisValeurs_iii_milieu() {
        ListeMilieu< Integer > a = construire( 12, 5, 2 );

        assertEquals( 5, a.milieu() );
    }

    @Test
    void inserer_troisValeurs_iii_minima() {
        ListeMilieu< Integer > a = construire( 12, 5, 2 );

        assertEquals( 2, a.minima() );
    }

    @Test
    void inserer_troisValeurs_iii_maxima() {
        ListeMilieu< Integer > a = construire( 12, 5, 2 );

        assertEquals( 12, a.maxima() );
    }

    @Test
    void inserer_troisValeurs_iis_taille() {
        ListeMilieu< Integer > a = construire( 0, -9, 12 );

        assertEquals( 3, a.taille() );
    }

    @Test
    void inserer_troisValeurs_iis_milieu() {
        ListeMilieu< Integer > a = construire( 0, -9, 12 );

        assertEquals( 0, a.milieu() );
    }

    @Test
    void inserer_troisValeurs_iis_minima() {
        ListeMilieu< Integer > a = construire( 0, -9, 12 );

        assertEquals( -9, a.minima() );
    }

    @Test
    void inserer_troisValeurs_iis_maxima() {
        ListeMilieu< Integer > a = construire( 0, -9, 12 );

        assertEquals( 12, a.maxima() );
    }

    @Test
    void inserer_troisValeurs_iss_taille() {
        ListeMilieu< Integer > a = construire( 0, 9, 12 );

        assertEquals( 3, a.taille() );
    }

    @Test
    void inserer_troisValeurs_iss_milieu() {
        ListeMilieu< Integer > a = construire( 0, 9, 12 );

        assertEquals( 9, a.milieu() );
    }

    @Test
    void inserer_troisValeurs_iss_minima() {
        ListeMilieu< Integer > a = construire( 0, 9, 12 );

        assertEquals( 0, a.minima() );
    }

    @Test
    void inserer_troisValeurs_iss_maxima() {
        ListeMilieu< Integer > a = construire( 0, 9, 12 );

        assertEquals( 12, a.maxima() );
    }

    // dix valeurs
    @Test
    void inserer_dixValeurs_taille() {
        ListeMilieu< Integer > a = construire( 1, 4, 7, 2, 5, 8, 3, 6, 9, 0 );

        assertEquals( 10, a.taille() );
    }

    @Test
    void inserer_dixValeurs_milieu() {
        ListeMilieu< Integer > a = construire( 1, 4, 7, 2, 5, 8, 3, 6, 9, 0 );

        assertEquals( 4, a.milieu() );
    }

    @Test
    void inserer_dixValeurs_minima() {
        ListeMilieu< Integer > a = construire( 1, 4, 7, 2, 5, 8, 3, 6, 9, 0 );

        assertEquals( 0, a.minima() );
    }

    @Test
    void inserer_dixValeurs_maxima() {
        ListeMilieu< Integer > a = construire( 1, 4, 7, 2, 5, 8, 3, 6, 9, 0 );

        assertEquals( 9, a.maxima() );
    }

    // onze valeurs
    @Test
    void inserer_onzeValeurs_taille() {
        ListeMilieu< Integer > a = construire( -1, -4, -7, -2, -5, -8, -3, -6, -9, -10, -11 );

        assertEquals( 11, a.taille() );
    }

    @Test
    void inserer_onzeValeurs_milieu() {
        ListeMilieu< Integer > a = construire( -1, -4, -7, -2, -5, -8, -3, -6, -9, -10, -11 );

        assertEquals( -6, a.milieu() );
    }

    @Test
    void inserer_onzeValeurs_minima() {
        ListeMilieu< Integer > a = construire( -1, -4, -7, -2, -5, -8, -3, -6, -9, -10, -11 );

        assertEquals( -11, a.minima() );
    }

    @Test
    void inserer_onzeValeurs_maxima() {
        ListeMilieu< Integer > a = construire( -1, -4, -7, -2, -5, -8, -3, -6, -9, -10, -11 );

        assertEquals( -1, a.maxima() );
    }

    //------------------------------------------------
    // Tests pour la suppression

    // 2 valeurs

    @Test
    void supprimer_maxima_de_deuxValeurs_taille() {
        ListeMilieu< Integer > a = construire( -1, 8 );

        a.supprimer( 8 );

        assertEquals( 1, a.taille() );
    }

    @Test
    void supprimer_maxima_de_deuxValeurs_milieu() {
        ListeMilieu< Integer > a = construire( -1, 8 );

        a.supprimer( 8 );

        assertEquals( -1, a.milieu() );
    }

    @Test
    void supprimer_maxima_de_deuxValeurs_minima() {
        ListeMilieu< Integer > a = construire( -1, 8 );

        a.supprimer( 8 );

        assertEquals( -1, a.minima() );
    }

    @Test
    void supprimer_maxima_de_deuxValeurs_maxima() {
        ListeMilieu< Integer > a = construire( -1, 8 );

        a.supprimer( 8 );

        assertEquals( -1, a.maxima() );
    }

    @Test
    void supprimer_minima_de_deuxValeurs_taille() {
        ListeMilieu< Integer > a = construire( -1, 8 );

        a.supprimer( -1 );

        assertEquals( 1, a.taille() );
    }

    @Test
    void supprimer_minima_de_deuxValeurs_milieu() {
        ListeMilieu< Integer > a = construire( -1, 8 );

        a.supprimer( -1 );

        assertEquals( 8, a.milieu() );
    }

    @Test
    void supprimer_minima_de_deuxValeurs_minima() {
        ListeMilieu< Integer > a = construire( -1, 8 );

        a.supprimer( -1 );

        assertEquals( 8, a.minima() );
    }

    @Test
    void supprimer_minima_de_deuxValeurs_maxima() {
        ListeMilieu< Integer > a = construire( -1, 8 );

        a.supprimer( -1 );

        assertEquals( 8, a.maxima() );
    }

    // trois valeurs

    @Test
    void supprimer_minima_de_troisValeurs_taille() {
        ListeMilieu< Integer > a = construire( 12, -1, 8 );

        a.supprimer( -1 );

        assertEquals( 2, a.taille() );
    }

    @Test
    void supprimer_minima_de_troisValeurs_milieu() {
        ListeMilieu< Integer > a = construire( 12, -1, 8 );

        a.supprimer( -1 );

        assertEquals( 8, a.milieu() );
    }

    @Test
    void supprimer_minima_de_troisValeurs_minima() {
        ListeMilieu< Integer > a = construire( 12, -1, 8 );

        a.supprimer( -1 );

        assertEquals( 8, a.minima() );
    }

    @Test
    void supprimer_minima_de_troisValeurs_maxima() {
        ListeMilieu< Integer > a = construire( 12, -1, 8 );

        a.supprimer( -1 );

        assertEquals( 12, a.maxima() );
    }

    @Test
    void supprimer_maxima_de_troisValeurs_taille() {
        ListeMilieu< Integer > a = construire( 12, -1, 8 );

        a.supprimer( 12 );

        assertEquals( 2, a.taille() );
    }

    @Test
    void supprimer_maxima_de_troisValeurs_milieu() {
        ListeMilieu< Integer > a = construire( 12, -1, 8 );

        a.supprimer( 12 );

        assertEquals( -1, a.milieu() );
    }

    @Test
    void supprimer_maxima_de_troisValeurs_minima() {
        ListeMilieu< Integer > a = construire( 12, -1, 8 );

        a.supprimer( 12 );

        assertEquals( -1, a.minima() );
    }

    @Test
    void supprimer_maxima_de_troisValeurs_maxima() {
        ListeMilieu< Integer > a = construire( 12, -1, 8 );

        a.supprimer( 12 );

        assertEquals( 8, a.maxima() );
    }

    @Test
    void supprimer_milieu_de_troisValeurs_taille() {
        ListeMilieu< Integer > a = construire( 12, -1, 8 );

        a.supprimer( 8 );

        assertEquals( 2, a.taille() );
    }

    @Test
    void supprimer_milieu_de_troisValeurs_milieu() {
        ListeMilieu< Integer > a = construire( 12, -1, 8 );

        a.supprimer( 8 );

        assertEquals( -1, a.milieu() );
    }

    @Test
    void supprimer_milieu_de_troisValeurs_minima() {
        ListeMilieu< Integer > a = construire( 12, -1, 8 );

        a.supprimer( 8 );

        assertEquals( -1, a.minima() );
    }

    @Test
    void supprimer_milieu_de_troisValeurs_maxima() {
        ListeMilieu< Integer > a = construire( 12, -1, 8 );

        a.supprimer( 8 );

        assertEquals( 12, a.maxima() );
    }

    // 10 valeurs

    @Test
    void supprimer_inferieur_de_dixValeurs_milieu() {
        ListeMilieu< Integer > a = construire( 1, 3, 5, 7, 9, 11, 13, 15, 17, 19 );

        a.supprimer( 5 );

        assertEquals( 11, a.milieu() );
    }

    @Test
    void supprimer_inferieur_de_dixValeurs_minima() {
        ListeMilieu< Integer > a = construire( 1, 3, 5, 7, 9, 11, 13, 15, 17, 19 );

        a.supprimer( 5 );

        assertEquals( 1, a.minima() );
    }

    @Test
    void supprimer_inferieur_de_dixValeurs_maxima() {
        ListeMilieu< Integer > a = construire( 1, 3, 5, 7, 9, 11, 13, 15, 17, 19 );

        a.supprimer( 5 );

        assertEquals( 19, a.maxima() );
    }

    // 11 valeurs

    @Test
    void supprimer_superieur_de_onzeValeurs_milieu() {
        ListeMilieu< Integer > a = construire( 1, 3, 5, 7, 9, 11, 13, 15, 17, 19, 21 );

        a.supprimer( 17 );

        assertEquals( 9, a.milieu() );
    }

    @Test
    void supprimer_superieur_de_onzeValeurs_minima() {
        ListeMilieu< Integer > a = construire( 1, 3, 5, 7, 9, 11, 13, 15, 17, 19, 21 );

        a.supprimer( 17 );

        assertEquals( 1, a.minima() );
    }

    @Test
    void supprimer_superieur_de_onzeValeurs_maxima() {
        ListeMilieu< Integer > a = construire( 1, 3, 5, 7, 9, 11, 13, 15, 17, 19, 21 );

        a.supprimer( 17 );

        assertEquals( 21, a.maxima() );
    }

    //------------------------------------------------
    // Test

    // 2 valeurs

    @Test
    void diviser_deuxValeurs_tailleOriginal() {
        ListeMilieu< Integer > a = construire( 1, 3 );

        ListeMilieu< Integer > d = a.diviser();

        assertEquals( 1, a.taille() );
    }

    @Test
    void diviser_deuxValeurs_tailleResultat() {
        ListeMilieu< Integer > a = construire( 1, 3 );

        ListeMilieu< Integer > d = a.diviser();

        assertEquals( 1, d.taille() );
    }

    @Test
    void diviser_deuxValeurs_minimaOriginal() {
        ListeMilieu< Integer > a = construire( 1, 3 );

        ListeMilieu< Integer > d = a.diviser();

        assertEquals( 1, a.minima() );
    }

    @Test
    void diviser_deuxValeurs_minimaResultat() {
        ListeMilieu< Integer > a = construire( 1, 3 );

        ListeMilieu< Integer > d = a.diviser();

        assertEquals( 3, d.minima() );
    }

    @Test
    void diviser_deuxValeurs_maximaOriginal() {
        ListeMilieu< Integer > a = construire( 1, 3 );

        ListeMilieu< Integer > d = a.diviser();

        assertEquals( 1, a.maxima() );
    }

    @Test
    void diviser_deuxValeurs_maximaResultat() {
        ListeMilieu< Integer > a = construire( 1, 3 );

        ListeMilieu< Integer > d = a.diviser();

        assertEquals( 3, d.maxima() );
    }

    // 3 valeurs

    @Test
    void diviser_troisValeurs_tailleOriginal() {
        ListeMilieu< Integer > a = construire( -9, 1, 3 );

        ListeMilieu< Integer > d = a.diviser();

        assertEquals( 2, a.taille() );
    }

    @Test
    void diviser_troisValeurs_tailleResultat() {
        ListeMilieu< Integer > a = construire( -9, 1, 3 );

        ListeMilieu< Integer > d = a.diviser();

        assertEquals( 1, d.taille() );
    }

    @Test
    void diviser_troisValeurs_minimaOriginal() {
        ListeMilieu< Integer > a = construire( -9, 1, 3 );

        ListeMilieu< Integer > d = a.diviser();

        assertEquals( -9, a.minima() );
    }

    @Test
    void diviser_troisValeurs_minimaResultat() {
        ListeMilieu< Integer > a = construire( -9, 1, 3 );

        ListeMilieu< Integer > d = a.diviser();

        assertEquals( 3, d.minima() );
    }

    @Test
    void diviser_troisValeurs_maximaOriginal() {
        ListeMilieu< Integer > a = construire( -9, 1, 3 );

        ListeMilieu< Integer > d = a.diviser();

        assertEquals( 1, a.maxima() );
    }

    @Test
    void diviser_troisValeurs_maximaResultat() {
        ListeMilieu< Integer > a = construire( -9, 1, 3 );

        ListeMilieu< Integer > d = a.diviser();

        assertEquals( 3, d.maxima() );
    }

    // 3 (impair) + 3 (impair) = 6 valeurs

    @Test
    void diviser_sixValeurs_tailleOriginal() {
        ListeMilieu< Integer > a = construire( -12, -9, 1, 3, 8, 15 );

        ListeMilieu< Integer > d = a.diviser();

        assertEquals( 3, a.taille() );
    }

    @Test
    void diviser_sixValeurs_tailleResultat() {
        ListeMilieu< Integer > a = construire( -12, -9, 1, 3, 8, 15 );

        ListeMilieu< Integer > d = a.diviser();

        assertEquals( 3, d.taille() );
    }

    @Test
    void diviser_sixValeurs_milieuOriginal() {
        ListeMilieu< Integer > a = construire( -12, -9, 1, 3, 8, 15 );

        ListeMilieu< Integer > d = a.diviser();

        assertEquals( -9, a.milieu() );
    }

    @Test
    void diviser_sixValeurs_milieuResultat() {
        ListeMilieu< Integer > a = construire( -12, -9, 1, 3, 8, 15 );

        ListeMilieu< Integer > d = a.diviser();

        assertEquals( 8, d.milieu() );
    }

    @Test
    void diviser_sixValeurs_minimaOriginal() {
        ListeMilieu< Integer > a = construire( -12, -9, 1, 3, 8, 15 );

        ListeMilieu< Integer > d = a.diviser();

        assertEquals( -12, a.minima() );
    }

    @Test
    void diviser_sixValeurs_minimaResultat() {
        ListeMilieu< Integer > a = construire( -12, -9, 1, 3, 8, 15 );

        ListeMilieu< Integer > d = a.diviser();

        assertEquals( 3, d.minima() );
    }

    @Test
    void diviser_sixValeurs_maximaOriginal() {
        ListeMilieu< Integer > a = construire( -12, -9, 1, 3, 8, 15 );

        ListeMilieu< Integer > d = a.diviser();

        assertEquals( 1, a.maxima() );
    }

    @Test
    void diviser_sixValeurs_maximaResultat() {
        ListeMilieu< Integer > a = construire( -12, -9, 1, 3, 8, 15 );

        ListeMilieu< Integer > d = a.diviser();

        assertEquals( 15, d.maxima() );
    }

    // 4 (pair) + 4 (pair) = 8 valeurs

    @Test
    void diviser_huitValeurs_tailleOriginal() {
        ListeMilieu< Integer > a = construire( -12, -9, 1, 3, 8, 15, 29, 54 );

        ListeMilieu< Integer > d = a.diviser();

        assertEquals( 4, a.taille() );
    }

    @Test
    void diviser_huitValeurs_tailleResultat() {
        ListeMilieu< Integer > a = construire( -12, -9, 1, 3, 8, 15, 29, 54 );

        ListeMilieu< Integer > d = a.diviser();

        assertEquals( 4, d.taille() );
    }

    @Test
    void diviser_huitValeurs_milieuOriginal() {
        ListeMilieu< Integer > a = construire( -12, -9, 1, 3, 8, 15, 29, 54 );

        ListeMilieu< Integer > d = a.diviser();

        assertEquals( -9, a.milieu() );
    }

    @Test
    void diviser_huitValeurs_milieuResultat() {
        ListeMilieu< Integer > a = construire( -12, -9, 1, 3, 8, 15, 29, 54 );

        ListeMilieu< Integer > d = a.diviser();

        assertEquals( 15, d.milieu() );
    }

    @Test
    void diviser_huitValeurs_minimaOriginal() {
        ListeMilieu< Integer > a = construire( -12, -9, 1, 3, 8, 15, 29, 54 );

        ListeMilieu< Integer > d = a.diviser();

        assertEquals( -12, a.minima() );
    }

    @Test
    void diviser_huitValeurs_minimaResultat() {
        ListeMilieu< Integer > a = construire( -12, -9, 1, 3, 8, 15, 29, 54 );

        ListeMilieu< Integer > d = a.diviser();

        assertEquals( 8, d.minima() );
    }

    @Test
    void diviser_huitValeurs_maximaOriginal() {
        ListeMilieu< Integer > a = construire( -12, -9, 1, 3, 8, 15, 29, 54 );

        ListeMilieu< Integer > d = a.diviser();

        assertEquals( 3, a.maxima() );
    }

    @Test
    void diviser_huitValeurs_maximaResultat() {
        ListeMilieu< Integer > a = construire( -12, -9, 1, 3, 8, 15, 29, 54 );

        ListeMilieu< Integer > d = a.diviser();

        assertEquals( 54, d.maxima() );
    }

    //------------------------------------------------
    // Test charge
    // 12.510 secondes chez moi.
    @Test
    void charge() {
        final int NOMBRE_CHOIX = 100_000;
        final int MINIMA = -5_000_000;
        final int MAXIMA = 5_000_000;
        final int TAILLE_INTERVALE = MAXIMA - MINIMA;

        ListeMilieu< Integer > a = new ListeMilieu<>();

        Random r = new Random();
        for( int i = 0; i < NOMBRE_CHOIX; ++ i ) {
            a.inserer( r.nextInt( TAILLE_INTERVALE ) + MINIMA );
        }

        assertTrue( a.taille() <= NOMBRE_CHOIX );
    }
}