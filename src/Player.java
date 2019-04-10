import java.util.ArrayList;
import java.util.HashMap;

public class Player extends Creature {

    private HashMap<String, Item> items;
    private Command command;

    public Command getCommand() {
        return command;
    }

    public void setCommand(Command command) {
        this.command = command;
    }

    public Player(String n, String d) {
        super(n, d);
        items = new HashMap<>();
        currentRoom = null;
        command = null;
    }

    public void act() {
        command.execute(this);
    }

    public void addItem(Item item) {
        items.put(item.getName(), item);
    }

    public Item removeItem(String name) {
        return items.remove(name);
    }

    public String displayItems() {
        String out = "";
        int ct = 0;
        for(Item item : items.values()) {
            out += item.getName();
            if(ct != items.size() - 1) {
                out += ", ";
            }
            ct++;
        }
        if(out.length() == 0) {
            out = "no items";
        }
        return out;
    }

    public boolean destroyItem(String name) {
        if(items.remove(name) != null) return true;
        return false;
    }

}
