public class Principale {

    public static void main(String[] args) {

        Chainon<Integer> c4 = new Chainon<Integer>(-8);
        Chainon<Integer> c3 = new Chainon<Integer>(-5,c4);
        Chainon<Integer> c2 = new Chainon<Integer>(1,c3);
        Chainon<Integer> c1 = new Chainon<Integer>(4,c2);

        Chainon<Integer> d4 = new Chainon<Integer>(20);
        Chainon<Integer> d3 = new Chainon<Integer>(16,d4);
        Chainon<Integer> d2 = new Chainon<Integer>(11,d3);
        Chainon<Integer> d1 = new Chainon<Integer>(9,d2);

        ListeMilieu<Integer> listeMilieu = new ListeMilieu();

        //listeMilieu.setInferieur(c1);
        //listeMilieu.setSuperieur(d1);

        ListeMilieu<Integer> res = listeMilieu.diviser();

    }
}
