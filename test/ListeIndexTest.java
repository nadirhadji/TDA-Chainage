import org.junit.jupiter.api.Test;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

class ListeIndexTest {

    //------------------------------------------------
    // méthode pour assister les tests.
    private < E extends Comparable< E > > ListeIndex< E >
    construire( E ... valeurs ) {
        ListeIndex< E > resultat = new ListeIndex<>();

        for( E v : valeurs ) {
            resultat.inserer( v );
        }

        return resultat;
    }

    //------------------------------------------------
    // tests construction.

    @Test
    void construction_nbrListe() {
        ListeIndex< Integer > a = construire();

        assertEquals( 0, a.nbrListe() );
    }

    @Test
    void construction_taille() {
        ListeIndex< Integer > a = construire();

        assertEquals( 0, a.taille() );
    }

    @Test
    void construction_contient() {
        ListeIndex< Integer > a = construire();

        assertFalse( a.contient( 4 ) );
    }

    //------------------------------------------------
    // tests insertion.

    // 1 valeur, 1 liste : 10
    // ----+-----
    //     | -
    // 0   | 10
    // ----+-----
    // 7 tests : 1 contient, 2 ! contient(9, 11), 1 get (x2 extremum), nbrListe, taille

    @Test
    void inserer_uneValeur_taille() {
        ListeIndex< Integer > a = construire( 10 );

        assertEquals( 1, a.taille() );
    }

    @Test
    void inserer_uneValeur_nbrListe() {
        ListeIndex< Integer > a = construire( 10 );

        assertEquals( 1, a.nbrListe() );
    }

    @Test
    void inserer_uneValeur_get_0_minima() {
        ListeIndex< Integer > a = construire( 10 );

        assertEquals( 10, a.get(0).minima() );
    }

    @Test
    void inserer_uneValeur_get_0_maxima() {
        ListeIndex< Integer > a = construire( 10 );

        assertEquals( 10, a.get(0).maxima() );
    }

    @Test
    void inserer_uneValeur_contient_10() {
        ListeIndex< Integer > a = construire( 10 );

        assertTrue( a.contient( 10 ) );
    }

    @Test
    void inserer_uneValeur_neContientPas_9() {
        ListeIndex< Integer > a = construire( 10 );

        assertFalse( a.contient( 9 ) );
    }

    @Test
    void inserer_uneValeur_neContientPas_11() {
        ListeIndex< Integer > a = construire( 10 );

        assertFalse( a.contient( 11 ) );
    }

    // 2 valeurs, 1 liste : 4, 7
    // ----+-----
    //     | 7
    // 0   | 4
    // ----+-----
    // 9 tests : 2 contient, 3 ! contient(1, 6, 9), 1 get (x2 extremum), nbrListe, taille

    @Test
    void inserer_deuxValeurs_taille() {
        ListeIndex< Integer > a = construire( 4, 7 );

        assertEquals( 2, a.taille() );
    }

    @Test
    void inserer_deuxValeurs_nbrListe() {
        ListeIndex< Integer > a = construire( 4, 7 );

        assertEquals( 1, a.nbrListe() );
    }

    @Test
    void inserer_deuxValeurs_get_0_minima() {
        ListeIndex< Integer > a = construire( 4, 7 );

        assertEquals( 4, a.get(0).minima() );
    }

    @Test
    void inserer_deuxValeurs_get_0_maxima() {
        ListeIndex< Integer > a = construire( 4, 7 );

        assertEquals( 7, a.get(0).maxima() );
    }

    @Test
    void inserer_deuxValeurs_contient_4() {
        ListeIndex< Integer > a = construire( 4, 7 );

        assertTrue( a.contient( 4 ) );
    }

    @Test
    void inserer_deuxValeurs_contient_7() {
        ListeIndex< Integer > a = construire( 4, 7 );

        assertTrue( a.contient( 7 ) );
    }

    @Test
    void inserer_deuxValeurs_neContientPas_1() {
        ListeIndex< Integer > a = construire( 4, 7 );

        assertFalse( a.contient( 1 ) );
    }

    @Test
    void inserer_deuxValeurs_neContientPas_6() {
        ListeIndex< Integer > a = construire( 4, 7 );

        assertFalse( a.contient( 6 ) );
    }

    @Test
    void inserer_deuxValeurs_neContientPas_9() {
        ListeIndex< Integer > a = construire( 4, 7 );

        assertFalse( a.contient( 9 ) );
    }

    // 3 valeurs, 2 listes : 3, 7, 11
    // ----+-----
    //     | -
    // 1   | 11
    // ----+-----
    //     | 7
    // 0   | 3
    // ----+-----
    // 13 tests : 3 contient, 4 ! contient( 2, 5, 9, 13), 2 get (x2 extremum), nbrListe, taille

    @Test
    void inserer_troisValeurs_taille() {
        ListeIndex< Integer > a = construire( 3, 7, 11 );

        assertEquals( 3, a.taille() );
    }

    @Test
    void inserer_troisValeurs_nbrListe() {
        ListeIndex< Integer > a = construire( 3, 7, 11 );

        assertEquals( 2, a.nbrListe() );
    }

    @Test
    void inserer_troisValeurs_get_0_minima() {
        ListeIndex< Integer > a = construire( 3, 7, 11 );

        assertEquals( 3, a.get( 0 ).minima() );
    }

    @Test
    void inserer_troisValeurs_get_0_maxima() {
        ListeIndex< Integer > a = construire( 3, 7, 11 );

        assertEquals( 7, a.get( 0 ).maxima() );
    }

    @Test
    void inserer_troisValeurs_get_1_minima() {
        ListeIndex< Integer > a = construire( 3, 7, 11 );

        assertEquals( 11, a.get( 1 ).minima() );
    }

    @Test
    void inserer_troisValeurs_get_1_maxima() {
        ListeIndex< Integer > a = construire( 3, 7, 11 );

        assertEquals( 11, a.get( 1 ).maxima() );
    }

    @Test
    void inserer_troisValeurs_contient_3() {
        ListeIndex< Integer > a = construire( 3, 7, 11 );

        assertTrue( a.contient( 3 ) );
    }

    @Test
    void inserer_troisValeurs_contient_7() {
        ListeIndex< Integer > a = construire( 3, 7, 11 );

        assertTrue( a.contient( 7 ) );
    }

    @Test
    void inserer_troisValeurs_contient_11() {
        ListeIndex< Integer > a = construire( 3, 7, 11 );

        assertTrue( a.contient( 11 ) );
    }

    @Test
    void inserer_troisValeurs_neContientPas_2() {
        ListeIndex< Integer > a = construire( 3, 7, 11 );

        assertFalse( a.contient( 2 ) );
    }

    @Test
    void inserer_troisValeurs_neContientPas_5() {
        ListeIndex< Integer > a = construire( 3, 7, 11 );

        assertFalse( a.contient( 5 ) );
    }

    @Test
    void inserer_troisValeurs_neContientPas_9() {
        ListeIndex< Integer > a = construire( 3, 7, 11 );

        assertFalse( a.contient( 9 ) );
    }

    @Test
    void inserer_troisValeurs_neContientPas_13() {
        ListeIndex< Integer > a = construire( 3, 7, 11 );

        assertFalse( a.contient( 13 ) );
    }

    // 6 valeurs, 3 listes : 1, 10, 15, 8, 5, 3
    // ----+-----
    //     | -
    // 2   | 15
    // ----+-----
    //     | 10
    // 1   | 8
    // ----+-----
    //     | 5
    // 0   | 3 1
    // ----+-----
    // 21 tests : 6 contient, 7 ! contient(-1, 2, 4, 6, 9, 11, 17), 3 get (x2 extremum), nbrListe, taille

    @Test
    void inserer_sixValeurs_taille() {
        ListeIndex< Integer > a = construire( 1, 10, 15, 8, 5, 3 );

        assertEquals( 6, a.taille() );
    }

    @Test
    void inserer_sixValeurs_nbrListe() {
        ListeIndex< Integer > a = construire( 1, 10, 15, 8, 5, 3 );

        assertEquals( 3, a.nbrListe() );
    }

    @Test
    void inserer_sixValeurs_get_0_minima() {
        ListeIndex< Integer > a = construire( 1, 10, 15, 8, 5, 3 );

        assertEquals( 1, a.get(0).minima() );
    }

    @Test
    void inserer_sixValeurs_get_0_maxima() {
        ListeIndex< Integer > a = construire( 1, 10, 15, 8, 5, 3 );

        assertEquals( 5, a.get(0).maxima() );
    }

    @Test
    void inserer_sixValeurs_get_1_minima() {
        ListeIndex< Integer > a = construire( 1, 10, 15, 8, 5, 3 );

        assertEquals( 8, a.get(1).minima() );
    }

    @Test
    void inserer_sixValeurs_get_1_maxima() {
        ListeIndex< Integer > a = construire( 1, 10, 15, 8, 5, 3 );

        assertEquals( 10, a.get(1).maxima() );
    }

    @Test
    void inserer_sixValeurs_get_2_minima() {
        ListeIndex< Integer > a = construire( 1, 10, 15, 8, 5, 3 );

        assertEquals( 15, a.get(2).minima() );
    }

    @Test
    void inserer_sixValeurs_get_2_maxima() {
        ListeIndex< Integer > a = construire( 1, 10, 15, 8, 5, 3 );

        assertEquals( 15, a.get(2).maxima() );
    }

    @Test
    void inserer_sixValeurs_contient_1() {
        ListeIndex< Integer > a = construire( 1, 10, 15, 8, 5, 3 );

        assertTrue( a.contient( 1 ) );
    }

    @Test
    void inserer_sixValeurs_contient_3() {
        ListeIndex< Integer > a = construire( 1, 10, 15, 8, 5, 3 );

        assertTrue( a.contient( 3 ) );
    }

    @Test
    void inserer_sixValeurs_contient_5() {
        ListeIndex< Integer > a = construire( 1, 10, 15, 8, 5, 3 );

        assertTrue( a.contient( 5 ) );
    }

    @Test
    void inserer_sixValeurs_contient_8() {
        ListeIndex< Integer > a = construire( 1, 10, 15, 8, 5, 3 );

        assertTrue( a.contient( 8 ) );
    }

    @Test
    void inserer_sixValeurs_contient_10() {
        ListeIndex< Integer > a = construire( 1, 10, 15, 8, 5, 3 );

        assertTrue( a.contient( 10 ) );
    }

    @Test
    void inserer_sixValeurs_contient_15() {
        ListeIndex< Integer > a = construire( 1, 10, 15, 8, 5, 3 );

        assertTrue( a.contient( 15 ) );
    }

    @Test
    void inserer_sixValeurs_neContientPas_m1() {
        ListeIndex< Integer > a = construire( 1, 10, 15, 8, 5, 3 );

        assertFalse( a.contient( -1 ) );
    }

    @Test
    void inserer_sixValeurs_neContientPas_2() {
        ListeIndex< Integer > a = construire( 1, 10, 15, 8, 5, 3 );

        assertFalse( a.contient( 2 ) );
    }

    @Test
    void inserer_sixValeurs_neContientPas_4() {
        ListeIndex< Integer > a = construire( 1, 10, 15, 8, 5, 3 );

        assertFalse( a.contient( 4 ) );
    }

    @Test
    void inserer_sixValeurs_neContientPas_6() {
        ListeIndex< Integer > a = construire( 1, 10, 15, 8, 5, 3 );

        assertFalse( a.contient( 6 ) );
    }

    @Test
    void inserer_sixValeurs_neContientPas_9() {
        ListeIndex< Integer > a = construire( 1, 10, 15, 8, 5, 3 );

        assertFalse( a.contient( 9 ) );
    }

    @Test
    void inserer_sixValeurs_neContientPas_11() {
        ListeIndex< Integer > a = construire( 1, 10, 15, 8, 5, 3 );

        assertFalse( a.contient( 11 ) );
    }

    @Test
    void inserer_sixValeurs_neContientPas_17() {
        ListeIndex< Integer > a = construire( 1, 10, 15, 8, 5, 3 );

        assertFalse( a.contient( 17 ) );
    }

    // 6 valeurs, 3 listes, ppq minima : 1, 10, 15, 6, 4, -4
    // ----+-----
    //     | -
    // 2   | 15
    // ----+-----
    //     | 10
    // 1   | 6
    // ----+-----
    //     | 4
    // 0   | 1 -4
    // ----+-----
    // 21 tests : 6 contient, 7 ! contient(-5, -2, 3, 5, 8, 12, 17), 3 get (x2 extremum), nbrListe, taille

    @Test
    void inserer_sixValeursMinima_taille() {
        ListeIndex< Integer > a = construire( 1, 10, 15, 6, 4, -4 );

        assertEquals( 6, a.taille() );
    }

    @Test
    void inserer_sixValeursMinima_nbrListe() {
        ListeIndex< Integer > a = construire( 1, 10, 15, 6, 4, -4 );

        assertEquals( 3, a.nbrListe() );
    }

    @Test
    void inserer_sixValeursMinima_get_0_minima() {
        ListeIndex< Integer > a = construire( 1, 10, 15, 6, 4, -4 );

        assertEquals( -4, a.get( 0 ).minima() );
    }

    @Test
    void inserer_sixValeursMinima_get_0_maxima() {
        ListeIndex< Integer > a = construire( 1, 10, 15, 6, 4, -4 );

        assertEquals( 4, a.get( 0 ).maxima() );
    }

    @Test
    void inserer_sixValeursMinima_get_1_minima() {
        ListeIndex< Integer > a = construire( 1, 10, 15, 6, 4, -4 );

        assertEquals( 6, a.get( 1 ).minima() );
    }

    @Test
    void inserer_sixValeursMinima_get_1_maxima() {
        ListeIndex< Integer > a = construire( 1, 10, 15, 6, 4, -4 );

        assertEquals( 10, a.get( 1 ).maxima() );
    }

    @Test
    void inserer_sixValeursMinima_get_2_minima() {
        ListeIndex< Integer > a = construire( 1, 10, 15, 6, 4, -4 );

        assertEquals( 15, a.get( 2 ).minima() );
    }

    @Test
    void inserer_sixValeursMinima_get_2_maxima() {
        ListeIndex< Integer > a = construire( 1, 10, 15, 6, 4, -4 );

        assertEquals( 15, a.get( 2 ).maxima() );
    }

    @Test
    void inserer_sixValeursMinima_contient_m4() {
        ListeIndex< Integer > a = construire( 1, 10, 15, 6, 4, -4 );

        assertTrue( a.contient( -4 ) );
    }

    @Test
    void inserer_sixValeursMinima_contient_1() {
        ListeIndex< Integer > a = construire( 1, 10, 15, 6, 4, -4 );

        assertTrue( a.contient( 1 ) );
    }

    @Test
    void inserer_sixValeursMinima_contient_4() {
        ListeIndex< Integer > a = construire( 1, 10, 15, 6, 4, -4 );

        assertTrue( a.contient( 4 ) );
    }

    @Test
    void inserer_sixValeursMinima_contient_6() {
        ListeIndex< Integer > a = construire( 1, 10, 15, 6, 4, -4 );

        assertTrue( a.contient( 6 ) );
    }

    @Test
    void inserer_sixValeursMinima_contient_10() {
        ListeIndex< Integer > a = construire( 1, 10, 15, 6, 4, -4 );

        assertTrue( a.contient( 10 ) );
    }

    @Test
    void inserer_sixValeursMinima_contient_15() {
        ListeIndex< Integer > a = construire( 1, 10, 15, 6, 4, -4 );

        assertTrue( a.contient( 15 ) );
    }

    @Test
    void inserer_sixValeursMinima_neContientPas_m5() {
        ListeIndex< Integer > a = construire( 1, 10, 15, 6, 4, -4 );

        assertFalse( a.contient( -5 ) );
    }

    @Test
    void inserer_sixValeursMinima_neContientPas_m2() {
        ListeIndex< Integer > a = construire( 1, 10, 15, 6, 4, -4 );

        assertFalse( a.contient( -2 ) );
    }

    @Test
    void inserer_sixValeursMinima_neContientPas_3() {
        ListeIndex< Integer > a = construire( 1, 10, 15, 6, 4, -4 );

        assertFalse( a.contient( 3 ) );
    }

    @Test
    void inserer_sixValeursMinima_neContientPas_5() {
        ListeIndex< Integer > a = construire( 1, 10, 15, 6, 4, -4 );

        assertFalse( a.contient( 5 ) );
    }

    @Test
    void inserer_sixValeursMinima_neContientPas_8() {
        ListeIndex< Integer > a = construire( 1, 10, 15, 6, 4, -4 );

        assertFalse( a.contient( 8 ) );
    }

    @Test
    void inserer_sixValeursMinima_neContientPas_12() {
        ListeIndex< Integer > a = construire( 1, 10, 15, 6, 4, -4 );

        assertFalse( a.contient( 12 ) );
    }

    @Test
    void inserer_sixValeursMinima_neContientPas_17() {
        ListeIndex< Integer > a = construire( 1, 10, 15, 6, 4, -4 );

        assertFalse( a.contient( 17 ) );
    }

    // 7 valeurs, 3 liste, pgq maxima : 1, 10, 20, 30, 40, 50, 60
    // ----+-----
    //     | 60
    // 2   | 50
    // ----+-----
    //     | 40
    // 1   | 30 20
    // ----+-----
    //     | 10
    // 0   | 1
    // ----+-----
    // 23 tests : 7 contient, 8 ! contient(-2, 5, 15, 25, 35, 45, 55, 65), 3 get (x2 extremum), nbrListe, taille

    @Test
    void inserer_septValeursMaxima_taille() {
        ListeIndex< Integer > a = construire( 1, 10, 20, 30, 40, 50, 60 );

        assertEquals( 7, a.taille() );
    }

    @Test
    void inserer_septValeursMaxima_nbrListe() {
        ListeIndex< Integer > a = construire( 1, 10, 20, 30, 40, 50, 60 );

        assertEquals( 3, a.nbrListe() );
    }

    @Test
    void inserer_septValeursMaxima_get_0_minima() {
        ListeIndex< Integer > a = construire( 1, 10, 20, 30, 40, 50, 60 );

        assertEquals( 1, a.get( 0 ).minima() );
    }

    @Test
    void inserer_septValeursMaxima_get_0_maxima() {
        ListeIndex< Integer > a = construire( 1, 10, 20, 30, 40, 50, 60 );

        assertEquals( 10, a.get( 0 ).maxima() );
    }

    @Test
    void inserer_septValeursMaxima_get_1_minima() {
        ListeIndex< Integer > a = construire( 1, 10, 20, 30, 40, 50, 60 );

        assertEquals( 20, a.get( 1 ).minima() );
    }

    @Test
    void inserer_septValeursMaxima_get_1_maxima() {
        ListeIndex< Integer > a = construire( 1, 10, 20, 30, 40, 50, 60 );

        assertEquals( 40, a.get( 1 ).maxima() );
    }

    @Test
    void inserer_septValeursMaxima_get_2_minima() {
        ListeIndex< Integer > a = construire( 1, 10, 20, 30, 40, 50, 60 );

        assertEquals( 50, a.get( 2 ).minima() );
    }

    @Test
    void inserer_septValeursMaxima_get_2_maxima() {
        ListeIndex< Integer > a = construire( 1, 10, 20, 30, 40, 50, 60 );

        assertEquals( 60, a.get( 2 ).maxima() );
    }

    @Test
    void inserer_septValeursMaxima_contient_1() {
        ListeIndex< Integer > a = construire( 1, 10, 20, 30, 40, 50, 60 );

        assertTrue( a.contient( 1 ) );
    }

    @Test
    void inserer_septValeursMaxima_contient_10() {
        ListeIndex< Integer > a = construire( 1, 10, 20, 30, 40, 50, 60 );

        assertTrue( a.contient( 10 ) );
    }

    @Test
    void inserer_septValeursMaxima_contient_20() {
        ListeIndex< Integer > a = construire( 1, 10, 20, 30, 40, 50, 60 );

        assertTrue( a.contient( 20 ) );
    }

    @Test
    void inserer_septValeursMaxima_contient_30() {
        ListeIndex< Integer > a = construire( 1, 10, 20, 30, 40, 50, 60 );

        assertTrue( a.contient( 30 ) );
    }

    @Test
    void inserer_septValeursMaxima_contient_40() {
        ListeIndex< Integer > a = construire( 1, 10, 20, 30, 40, 50, 60 );

        assertTrue( a.contient( 40 ) );
    }

    @Test
    void inserer_septValeursMaxima_contient_50() {
        ListeIndex< Integer > a = construire( 1, 10, 20, 30, 40, 50, 60 );

        assertTrue( a.contient( 50 ) );
    }

    @Test
    void inserer_septValeursMaxima_contient_60() {
        ListeIndex< Integer > a = construire( 1, 10, 20, 30, 40, 50, 60 );

        assertTrue( a.contient( 60 ) );
    }

    @Test
    void inserer_septValeursMaxima_neContientPas_m2() {
        ListeIndex< Integer > a = construire( 1, 10, 20, 30, 40, 50, 60 );

        assertFalse( a.contient( -2 ) );
    }

    @Test
    void inserer_septValeursMaxima_neContientPas_5() {
        ListeIndex< Integer > a = construire( 1, 10, 20, 30, 40, 50, 60 );

        assertFalse( a.contient( 5 ) );
    }

    @Test
    void inserer_septValeursMaxima_neContientPas_15() {
        ListeIndex< Integer > a = construire( 1, 10, 20, 30, 40, 50, 60 );

        assertFalse( a.contient( 15 ) );
    }

    @Test
    void inserer_septValeursMaxima_neContientPas_25() {
        ListeIndex< Integer > a = construire( 1, 10, 20, 30, 40, 50, 60 );

        assertFalse( a.contient( 25 ) );
    }

    @Test
    void inserer_septValeursMaxima_neContientPas_35() {
        ListeIndex< Integer > a = construire( 1, 10, 20, 30, 40, 50, 60 );

        assertFalse( a.contient( 35 ) );
    }

    @Test
    void inserer_septValeursMaxima_neContientPas_45() {
        ListeIndex< Integer > a = construire( 1, 10, 20, 30, 40, 50, 60 );

        assertFalse( a.contient( 45 ) );
    }

    @Test
    void inserer_septValeursMaxima_neContientPas_55() {
        ListeIndex< Integer > a = construire( 1, 10, 20, 30, 40, 50, 60 );

        assertFalse( a.contient( 55 ) );
    }

    @Test
    void inserer_septValeursMaxima_neContientPas_65() {
        ListeIndex< Integer > a = construire( 1, 10, 20, 30, 40, 50, 60 );

        assertFalse( a.contient( 65 ) );
    }

    //------------------------------------------------
    // tests suppression.

    // 4 valeurs, 2 listes, 0, 1
    // ----+-----
    //     | 50
    // 1   | 30
    // ----+-----
    //     | 10
    // 0   | 1
    // ----+-----
    // 1, 10, 30, 50

    @Test
    void supprimer_quatreValeurs_supprimer_liste_0_contient() {
        ListeIndex< Integer > a = construire( 1, 10, 30, 50 );

        a.supprimer( 1 );

        assertFalse( a.contient( 1 ) );
    }

    @Test
    void supprimer_quatreValeurs_supprimer_liste_0_taille() {
        ListeIndex<Integer> a = construire(1, 10, 30, 50);

        a.supprimer(10);

        assertEquals(1, a.get(0).taille());
    }

    @Test
    void supprimer_quatreValeurs_supprimer_liste_1_contient() {
        ListeIndex< Integer > a = construire( 1, 10, 30, 50 );

        a.supprimer( 30 );

        assertFalse( a.contient( 30 ) );
    }

    @Test
    void supprimer_quatreValeurs_supprimer_liste_1_taille() {
        ListeIndex< Integer > a = construire( 1, 10, 30, 50 );

        a.supprimer( 50 );

        assertEquals( 1, a.get( 1 ).taille() );
    }

    @Test
    void supprimer_quatreValeurs_supprimer_liste_1_nbrListe() {
        ListeIndex< Integer > a = construire( 1, 10, 30, 50 );

        a.supprimer( 50 );

        assertEquals( 2, a.nbrListe() );
    }

    // 7 valeurs, 3 listes, 0, 1, 2
    // ----+-----
    //     | 50
    // 2   | 40
    // ----+-----
    //     | 30
    // 1   | 20 10
    // ----+-----
    //     | 0
    // 0   | -10
    // ----+-----
    // -10, 0, 10, 20, 30, 40, 50
    @Test
    void supprimer_septValeurs_supprimer_liste_0_contient() {
        ListeIndex< Integer > a = construire( -10, 0, 10, 20, 30, 40, 50 );

        a.supprimer( 0 );

        assertFalse( a.contient( 0 ) );
    }

    @Test
    void supprimer_septValeurs_supprimer_liste_0_taille() {
        ListeIndex< Integer > a = construire( -10, 0, 10, 20, 30, 40, 50 );

        a.supprimer( -10 );

        assertEquals( 1, a.get( 0 ).taille() );
    }

    @Test
    void supprimer_septValeurs_supprimer_liste_1_contient() {
        ListeIndex< Integer > a = construire( -10, 0, 10, 20, 30, 40, 50 );

        a.supprimer( 30 );

        assertFalse( a.contient( 30 ) );
    }

    @Test
    void supprimer_septValeurs_supprimer_liste_1_taille() {
        ListeIndex< Integer > a = construire( -10, 0, 10, 20, 30, 40, 50 );

        a.supprimer( 30 );

        assertEquals( 2, a.get( 1 ).taille() );
    }
    @Test
    void supprimer_septValeurs_supprimer_liste_2_contient() {
        ListeIndex< Integer > a = construire( -10, 0, 10, 20, 30, 40, 50 );

        a.supprimer( 40 );

        assertFalse( a.contient( 40 ) );
    }

    @Test
    void supprimer_septValeurs_supprimer_liste_2_taille() {
        ListeIndex< Integer > a = construire( -10, 0, 10, 20, 30, 40, 50 );

        a.supprimer( 50 );

        assertEquals( 1, a.get( 2 ).taille() );
    }

    @Test
    void supprimer_septValeurs_supprimer_liste_1_nbrListe() {
        ListeIndex< Integer > a = construire( -10, 0, 10, 20, 30, 40, 50 );

        a.supprimer( 30 );

        assertEquals( 3, a.nbrListe() );
    }


    //------------------------------------------------
    // Test charge
    // 0.323 secondes chez moi.
    // Pour le meme nombre de valeur, la listeMilieu seule a demandé 12.510 secondes.
    // avons-nous un temps O(sqrt(n)) ?
    @Test
    void charge() {
        final long NOMBRE_CHOIX = 100_003;
        final int MINIMA = -5_000_000;
        final int MAXIMA = 5_000_000;
        final int TAILLE_INTERVALE = MAXIMA - MINIMA;

        ListeIndex< Integer > a = new ListeIndex<>();

        Random r = new Random();
        for( int j = 0; j < NOMBRE_CHOIX; ++ j ){
            a.inserer( r.nextInt( TAILLE_INTERVALE ) + MINIMA );
        }

        assertTrue( a.taille() <= NOMBRE_CHOIX );

        // Quelques statisque.
        // Enlevez les commentaires pour les exécuter.
        // remettre les commentaires pour avoir le bon temps d'exécution pour l'insertion.
        /*
        int nbrListe = a.nbrListe();
        System.out.println( "nbrListe : " + nbrListe);
        System.out.println( "moyenne d'element par liste : " + ( ((double)NOMBRE_CHOIX) / nbrListe) );

        int tailleMinimale = Integer.MAX_VALUE;
        int tailleMaximale = Integer.MIN_VALUE;

        double sommeTailleIntervalle = 0.0;

        for(int i = 0; i < nbrListe; ++ i ) {
            ListeMilieu<Integer> liste = a.get( i );

            int tailleListe = liste.taille();
            if( tailleListe < tailleMinimale ) {
                tailleMinimale = tailleListe;
            }
            if( tailleMaximale < tailleListe ) {
                tailleMaximale = tailleListe;
            }

            sommeTailleIntervalle += ( liste.maxima() - liste.minima() );
        }

        System.out.println( "taille minimale : " + tailleMinimale );
        System.out.println( "taille maximale : " + tailleMaximale );
        System.out.println( "taille moyenne des intervales : " + ( sommeTailleIntervalle / nbrListe ) );
        System.out.println( "(intervale de valeur) div (nbrListe) : " + ( ((double)( MAXIMA - MINIMA ) ) / nbrListe ) );
        */
    }
}