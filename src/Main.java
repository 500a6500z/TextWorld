import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Main {

    private static final String[] CHICKEN_DESCRIPTIONS = {"big", "small", "young", "old", "benevolent", "evil", "cute", "not cute", "chubby"};
    private static final String[] WUMPUS_DESCRIPTIONS = {"scared", "frightful", "panicking", "paranoid", "engaged in mental breakdown"};
    private static final String[] POPSTAR_DESCRIPTIONS = {"thirsty", "hungry", "in need of good food"};
    private static final String[] ITEM_DESCRIPTIONS = {"big", "heavy", "small", "hot", "cold", "moist", "shiny", "dull", "dirty", "clean", "smooth", "ragged"};

    //TODO: Implement all this shit
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String resp = "";
        Graph g = initializeLevel(in);
        boolean quit = false;

        while(true) {
            while(true) {
                displayWorldInfo(g);
                resp = in.nextLine();
                if (resp.equals("q")) {
                    quit = true;
                    break;
                }
                Command command = parseResponse(resp);
                if (command != null) {
                    g.getPlayer().setCommand(command);
                    g.getPlayer().act();
                    break;
                } else {
                    displayHelpInfo();
                }
            }
            if(quit) break;
            for(Graph.Node room : g.getMap().values()) {
                //prevent concurrent modification exception
                ArrayList<Creature> creatures = new ArrayList<>(room.getCreatures().values());
                for(int i = creatures.size() - 1; i >= 0; i--) {
                    Creature c = creatures.get(i);
                    if(c.isPlayer()) continue;
                    //System.out.println(c.getName() + " was in " + c.getCurrentRoom().getName());
                    c.act();
                    //System.out.println(c.getName() + " now in " + c.getCurrentRoom().getName());
                }
            }
        }

        in.close();
    }

    private static Command parseResponse(String resp) {
      StringTokenizer st = new StringTokenizer(resp);
      String[] tokens = new String[st.countTokens()];
      for(int i = 0; i < tokens.length; i++) {
          tokens[i] = st.nextToken();
      }

      if(tokens.length == 0) return null;

      if(tokens.length == 1 && tokens[0].equals("look")) return new LookCommand();

      if(tokens.length == 2 && tokens[0].equals("enter")) return new MoveCommand(tokens[1]);

      if(tokens.length == 2 && tokens[0].equals("take")) return new TakeCommand(tokens[1]);

      if(tokens.length == 2 && tokens[0].equals("drop")) return new DropCommand(tokens[1]);

      return null;
    }

    private static void displayHelpInfo() {
        System.out.println("invalid command");
        System.out.println("commands:");
        System.out.println("look");
        System.out.println("enter [roomname]");
        System.out.println("take [itemname]");
        System.out.println("drop [itemname]");
        System.out.println("'q' to quit");
    }

    private static void displayWorldInfo(Graph g) {
        System.out.println("you are in " + g.getPlayer().getCurrentRoom().getName());
        System.out.println(g.getPlayer().getCurrentRoom().getDesc());
        System.out.println("what to do?");
    }

    private static Graph initializeLevel(Scanner in) {
        Graph g = initializePlayer(in);
        initializeRooms(g);
        initializeCreatures(g);
        initializeItems(g);
        g.getPlayer().setRoom(g.getNode("bed"));
        return g;
    }

    private static void initializeCreatures(Graph g) {
        int chickenId = 0;
        int wumpusId = 0;
        int popstarId = 0;
        for(Graph.Node room : g.getMap().values()) {
            for(int i = 0; i < (int) (Math.random() * 3); i++) {
                Chicken chicken = new Chicken("chicken" + chickenId, getRandomElement(CHICKEN_DESCRIPTIONS));
                chicken.setRoom(room);
                room.addCreature(chicken);
                chickenId++;
            }
            if(Math.random() < 0.25) {
                Wumpus wumpus = new Wumpus("wumpus" + wumpusId, getRandomElement(WUMPUS_DESCRIPTIONS), g.getPlayer());
                wumpus.setRoom(room);
                room.addCreature(wumpus);
                wumpusId++;
            }
            if(Math.random() < .1) {
                PopStar popstar = new PopStar("popstar" + popstarId, getRandomElement(POPSTAR_DESCRIPTIONS), g.getPlayer());
                popstar.setRoom(room);
                room.addCreature(popstar);
                popstarId++;
            }
        }
    }

    private static void initializeItems(Graph g) {
        int rockId = 0;
        for(Graph.Node room : g.getMap().values()) {
            for(int i = 0; i < (int) (Math.random() * 2); i++) {
                Item item = new Item("rock" + rockId, getRandomElement(ITEM_DESCRIPTIONS));
                room.addItem(item);
                rockId++;
            }
        }
    }

    private static String getRandomElement(String[] ar) {
        return ar[(int) (Math.random() * ar.length)];
    }

    private static Graph initializePlayer(Scanner in) {
        System.out.println("who are you?");
        String playerName = in.nextLine();
        System.out.println("what are you?");
        String playerDesc = in.nextLine();
        return new Graph(playerName, playerDesc);
    }

    private static void initializeRooms(Graph g) {
        addRooms(g);
        addEdges(g);
    }

    private static void addRooms(Graph g) {
        g.addNode(new Graph.Node("bed", "it's relaxing"));
        g.addNode(new Graph.Node("home", "it's cozy"));
        g.addNode(new Graph.Node("sleep", "it's comfy"));
        g.addNode(new Graph.Node("a", "surreal"));
        g.addNode(new Graph.Node("b", "b"));
        g.addNode(new Graph.Node("c", "c"));
        g.addNode(new Graph.Node("d", "d"));
        g.addNode(new Graph.Node("why give actual rooms", "when you can not give actual rooms"));
        g.addNode(new Graph.Node("432424", "85749375"));
        g.addNode(new Graph.Node("90374", "84592"));
        g.addNode(new Graph.Node("2834", "64325"));
        g.addNode(new Graph.Node("144", "943"));
        g.addNode(new Graph.Node("5", "4"));
        g.addNode(new Graph.Node("3", "2"));
        g.addNode(new Graph.Node("1", "turn around"));
        g.addNode(new Graph.Node("0", "actually, it's quite a far away back. here's a shortcut"));
        g.addNode(new Graph.Node("???", "i tried to stop you, but now you're stuck"));
        g.addNode(new Graph.Node("ahole", "good job"));
    }

    private static void addEdges(Graph g) {
        g.addUndirectedEdge("bed", "home");
        g.addUndirectedEdge("bed", "sleep");
        g.addUndirectedEdge("a", "sleep");
        g.addUndirectedEdge("a", "b");
        g.addUndirectedEdge("a", "c");
        g.addUndirectedEdge("a", "d");
        g.addUndirectedEdge("b", "c");
        g.addUndirectedEdge("c", "d");
        g.addUndirectedEdge("b", "why give actual rooms");
        g.addUndirectedEdge("why give actual rooms", "d");
        g.addUndirectedEdge("c", "432424");
        g.addUndirectedEdge("90374", "432424");
        g.addUndirectedEdge("2834", "90374");
        g.addUndirectedEdge("144", "2834");
        g.addUndirectedEdge("5", "144");
        g.addUndirectedEdge("3", "5");
        g.addUndirectedEdge("1", "3");
        g.addUndirectedEdge("0", "1");
        g.addDirectedEdge("0", "sleep");
        g.addDirectedEdge("0", "???");
        g.addDirectedEdge("???", "ahole");
    }

}