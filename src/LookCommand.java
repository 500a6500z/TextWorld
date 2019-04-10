public class LookCommand implements Command {

    @Override
    public void execute(Player p) {
        System.out.println("you can go: " + p.getCurrentRoom().displayNeighbors());
        System.out.println("you have: " + p.displayItems());
        System.out.println("here there are: " + p.getCurrentRoom().displayCreatures());
        System.out.println("you find: " + p.getCurrentRoom().displayItems());
    }
}
