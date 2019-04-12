import java.util.ArrayList;
import java.util.HashMap;

public class Graph {

    HashMap<String, Node> map;

    Player player;

    public HashMap<String, Node> getMap() {
        return map;
    }

    public void setMap(HashMap<String, Node> map) {
        this.map = map;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public Graph(String n, String d) {
        map = new HashMap<>();
        player = new Player(n, d);
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
        return map.get(name);
    }

    static class Node {
        private HashMap<String, Node> adj;
        private HashMap<String, Item> items;
        private HashMap<String, Creature> creatures;
        private String name;
        private String desc;

        public Node(String n, String d) {
            name = n;
            desc = d;
            adj = new HashMap<>();
            items = new HashMap<>();
            creatures = new HashMap<>();
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

        public void addItem(String name, String desc) {
            items.put(name, new Item(name, desc));
        }

        public void addItem(Item item) {
            items.put(item.getName(), item);
        }

        public Item removeItem(String name) {
            return items.remove(name);
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

        public HashMap<String, Item> getItems() {
            return items;
        }

        public String displayCreatures() {
            String out = "";
            int ct = 0;
            for(Creature creature : creatures.values()) {
                if(creature.isPlayer()) continue;
                out += creature.getName();
                if(ct != creatures.size() - 1) {
                    out += ", ";
                }
                ct++;
            }
            if(out.length() == 0) {
                out = "no creatures (besides you)";
            }
            return out;
        }

        public void addCreature(Creature creature) {
            creatures.put(creature.getName(), creature);
        }

        public Creature removeCreature(String name) {
            return creatures.remove(name);
        }

        public HashMap<String, Creature> getCreatures() {
            return creatures;
        }

        public HashMap<String, Node> getNeighbors() {
            return adj;
        }

        public void addNeighbor(Node n) {
            adj.put(n.getName(), n);
        }

        public String displayNeighbors() {
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
}
