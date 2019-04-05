import java.util.ArrayList;
import java.util.HashMap;

public class Graph {

    HashMap<String, Node> map;

    public Graph() {
        map = new HashMap<>();
    }

    public void addNode(Node node) {
        map.put(node.getName(), node);
    }

    public void addDirectedEdge(String name1, String name2) {
        Node from = getNode(name1);
        Node to = getNode(name2);
        from.addNeighbor(to);
    }

    public void addUndirectedEdge(String name1, String name2) {
        Node one = getNode(name1);
        Node two = getNode(name2);
        one.addNeighbor(two);
        two.addNeighbor(one);
    }

    public Node getNode(String name) {
        for(Node node : map.values()) {
            if(node.name.equals(name)) {
                return node;
            }
        }        return null;
    }

    static class Node {
        private HashMap<String, Node> adj;
        private ArrayList<Item> items;
        private String name;
        private String desc;

        public Node(String n, String d) {
            name = n;
            desc = d;
            adj = new HashMap<>();
            items = new ArrayList<>();
        }

        public ArrayList<Item> getItems() {
            return items;
        }

        public String displayItems() {
            String out = "";
            for(int i = 0; i < items.size(); i++) {
                out += items.get(i).getName();
                if(i != items.size() - 1) {
                    out += ", ";
                }
            }
            if(out.length() == 0) {
                out = "no items";
            }
            return out;
        }

        public void addItem(String name) {
            items.add(new Item(name, "default"));
        }

        public void addItem(String name, String desc) {
            items.add(new Item(name, desc));
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
            for(int i = 0; i < items.size(); i++) {
                if(items.get(i).getName().equals(name)) {
                    items.remove(i);
                    return true;
                }
            }
            return false;
        }

        public HashMap<String, Node> getNeighbors() {
            return adj;
        }

        public void addNeighbor(Node n) {
            adj.put(n.getName(), n);
        }

        public String getName() {
            return name;
        }

        public String getDescription() {
            return desc;
        }

        public String getNeighborNames() {
            String output = "";
            int ct = 0;
            for (Node n : adj.values()) {
                output += n.name;
                if (ct < adj.size()) {
                    output += ", ";
                }
                ct++;
            }
            if (output.length() == 0) {
                output = "nowhere.";
            }
            return output;
        }

        public Node getNeighbor(String n) {
            return adj.get(n);
        }
    }
}
