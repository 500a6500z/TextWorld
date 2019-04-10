public class DropCommand implements Command {
    private String itemName;

    public DropCommand(String itemName) {
        this.itemName = itemName;
    }

    @Override
    public void execute(Player p) {
        Item itemDropped = p.removeItem(itemName);
        if(itemDropped != null) {
            p.getCurrentRoom().addItem(itemDropped);
            System.out.println("you dropped " + itemName);
        }
        else {
            System.out.println("since you don't have that item, you dropped nothing");
        }
    }
}
