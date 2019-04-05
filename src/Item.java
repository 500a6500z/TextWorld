public class Item implements Entity {
    private String name;
    private String desc;

    public Item(String n, String d) {
        name = n;
        desc = d;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
