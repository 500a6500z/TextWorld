public class TakeCommand implements Command {
    private String itemName;

    public TakeCommand(String itemName) {
        this.itemName = itemName;
    }

    @Override
    public void execute(Player p) {
        Item itemTaken = p.getCurrentRoom().removeItem(itemName);
        if(itemTaken != null) {
            p.addItem(itemTaken);
            System.out.println("you took " + itemName);
        }
        else {
            System.out.println("since there was no such item, you took nothing");
        }
    }
}
