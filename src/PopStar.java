public class PopStar extends Creature {
    private Player target;

    public PopStar(String n, String d, Player p) {
        super(n, d);
        target = p;
    }

    public void act() {
        moveTowards(target.getCurrentRoom());
    }
}
