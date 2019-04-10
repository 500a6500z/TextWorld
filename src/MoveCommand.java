public class MoveCommand implements Command {
    private String nextRoom;

    public MoveCommand(String roomName) {
        nextRoom = roomName;
    }

    @Override
    public void execute(Player p) {
        if(p.moveToRoom(nextRoom)) {
            System.out.println("you moved to " + nextRoom);
        }
        else {
            System.out.println("since there was no path to " +
                    nextRoom + ", you stayed in the same room");
        }
    }
}
