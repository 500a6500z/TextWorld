import java.util.ArrayList;

public class Player extends Creature {

    private ArrayList<Item> items;

    public Player(String n, String d) {
        super(n, d);
        items = new ArrayList<>();
        currentRoom = null;
    }

    //TODO: implement move() in player
    public void move() {

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
