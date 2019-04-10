import java.util.ArrayList;

public class Player extends Creature {

    private ArrayList<Item> items;
    private Command command;

    public Command getCommand() {
        return command;
    }

    public void setCommand(Command command) {
        this.command = command;
    }

    public Player(String n, String d) {
        super(n, d);
        items = new ArrayList<>();
        currentRoom = null;
        command = null;
    }

    //TODO: implement act() in player
    public void act() {

    }

    public void addItem(Item item) {
        items.add(item);
    }

    public Item removeItem(String name) {
        for(int i = 0; i < items.size(); i++) {
            if(items.get(i).getName().equals(name)) {
                return items.remove(i);
            }
        }
        return null;
    }

    public boolean destroyItem(String name) {
        for(Item i : items) {
            if(i.getName().equals(name)) {
                items.remove(i);
                return true;
            }
        }
        return false;
    }

}
