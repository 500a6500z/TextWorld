public class Chicken extends Creature {

    public Chicken(String n, String d) {
        super(n, d);
    }

    @Override
    public void act() {
        moveRandom();
    }

}
