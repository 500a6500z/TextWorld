public class Wumpus extends Creature {
    private Player baddie;

    public Wumpus(String n, String d, Player p) {
        super(n, d);
        baddie = p;
    }

    @Override
    public void act() {
        for(Graph.Node room : currentRoom.getNeighbors().values()) {
            if(baddie.currentRoom != room) {
                moveToRoom(room);
                return;
            }
        }
        moveRandom();
    }
}
