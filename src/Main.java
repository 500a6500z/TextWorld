import java.util.Scanner;
import java.util.StringTokenizer;

public class Main {

    private static final String[] CHICKEN_DESCRIPTIONS = {"big", "small", "young", "old", "benevolent", "evil", "cute", "not cute", "chubby"};
    private static final String[] WUMPUS_DESCRIPTIONS = {"scared", "frightful", "panicking", "paranoid", "engaged in mental breakdown"};
    private static final String[] POPSTAR_DESCRIPTIONS = {"thirsty", "hungry", "in need of good food"};

//    public static void main(String[] args) {
//
//        Graph g = new Graph();
//        g.addNode(new Graph.Node("bed", "comfy"));
//
//        Player p = new Player("chip", "big boi");
//        p.setRoom(g.getNode("bed"));
//        g.getNode("bed").addItem(new Item("pillow", "warm"));
//
//        g.addNode(new Graph.Node("bathroom", "cold"));
//        g.addNode(new Graph.Node("window", "breezy"));
//        g.addNode(new Graph.Node("sleep", "nice"));
//        g.addNode(new Graph.Node("die", "nothing"));
//
//        g.addUndirectedEdge("bed", "bathroom");
//        g.addUndirectedEdge("bed", "window");
//        g.addUndirectedEdge("bed", "sleep");
//        g.addDirectedEdge("sleep", "die");
//        g.addDirectedEdge("window", "die");
//        g.addDirectedEdge("bathroom", "die");
//        g.addUndirectedEdge("sleep", "sleep");
//
//        Chicken[] chickens = new Chicken[100];
//        for(int i = 0; i < chickens.length; i++) {
//            chickens[i] = new Chicken("chicken" + (i + 1), getRandomChickenDescription());
//            chickens[i].setRoom(g.getNode("sleep"));
//        }
//
//        String resp = "";
//        Scanner in = new Scanner(System.in);
//        StringTokenizer st;
//
//        while(true) {
//            System.out.println("***\nYou are in the " + p.getCurrentRoom().getName());
//            System.out.println("it is " + p.getCurrentRoom().getDesc());
//            System.out.println("What do you want to do? >");
//            resp = in.nextLine();
//            st = new StringTokenizer(resp);
//            String[] tokens = new String[st.countTokens()];
//            for(int i = 0; i < tokens.length; i++) {
//                tokens[i] = st.nextToken();
//            }
//
//            if(tokens.length == 0) {
//                System.out.println("please don't break this delicate game");
//                System.out.println("try again.");
//                continue;
//            }
//
//            if(tokens[0].equals("go")) {
//                if(tokens.length == 1) {
//                    System.out.println("go where?");
//                    System.out.println("try again.");
//                    continue;
//                }
//                else if(tokens.length > 2) {
//                    System.out.println("syntax: go [roomname]");
//                    System.out.println("try again.");
//                    continue;
//                }
//                Graph.Node to = g.getNode(tokens[1]);
//                if(to == null) {
//                    System.out.println("no such room");
//                    System.out.println("try again.");
//                    continue;
//                }
//
//                p.moveToRoom(to.getName());
//            }
//            else if(tokens[0].equals("look")) {
//                if(tokens.length > 1) {
//                    System.out.println("syntax: look");
//                    System.out.println("try again.");
//                    continue;
//                }
//                System.out.println("you see rooms: " + p.getCurrentRoom().getNeighborNames());
//                System.out.println("you see items: " + p.getCurrentRoom().displayItems());
//                //TODO: make chicken display a better implementation
//                System.out.println("you see these chickens: ");
//                for(int i = 0 ; i < chickens.length; i++) {
//                    if(chickens[i].currentRoom == p.currentRoom) {
//                        System.out.println(chickens[i].getName());
//                    }
//                }
//            }
//            else if(tokens[0].equals("add")) {
//                if(tokens.length == 1) {
//                    System.out.println("add what?");
//                    System.out.println("syntax: add [roomname]");
//                    System.out.println("try again.");
//                    continue;
//                }
//                if(tokens.length > 2) {
//                    System.out.println("syntax: add [roomname]");
//                    System.out.println("try again.");
//                    continue;
//                }
//                System.out.println("what's the magic word?");
//                String password = in.nextLine();
//                if(password.equals("i'd like to die a second time")) {
//                    System.out.println("admins can't die, silly");
//                    System.out.println("that's not the magic word");
//                    System.out.println("try again");
//                    continue;
//                }
//                else if(password.equals("alliwantinlifeisanibmmodelf122")) {
//                    System.out.println("me too");
//                    System.out.println("what is this room like?");
//                    String d = in.nextLine();
//                    g.addNode(new Graph.Node(tokens[1], d));
//                    g.addUndirectedEdge(p.getCurrentRoom().getName(), tokens[1]);
//                    System.out.println("room added");
//                    continue;
//                }
//                else {
//                    System.out.println("that's not the magic word");
//                    System.out.println("try again.");
//                    continue;
//                }
//            }
//            else if(tokens[0].equals("take")) {
//                if(tokens.length == 1) {
//                    System.out.println("take what?");
//                    System.out.println("syntax: take [itemname]");
//                    System.out.println("try again.");
//                    continue;
//                }
//                if(tokens.length > 2) {
//                    System.out.println("syntax: take [itemname]");
//                    System.out.println("try again.");
//                    continue;
//                }
//                Item take = p.getCurrentRoom().removeItem(tokens[1]);
//                if(take == null) {
//                    System.out.println("no such item");
//                    System.out.println("try again.");
//                    continue;
//                }
//                else {
//                    p.addItem(take);
//                    System.out.println("you took " + take.getName());
//                    System.out.println("it is " + take.getDesc());
//                    continue;
//                }
//            }
//            else if(tokens[0].equals("drop")) {
//                if(tokens.length == 1) {
//                    System.out.println("drop what?");
//                    System.out.println("syntax: drop [itemname]");
//                    System.out.println("try again.");
//                    continue;
//                }
//                if(tokens.length > 2) {
//                    System.out.println("syntax: drop [itemname]");
//                    System.out.println("try again.");
//                    continue;
//                }
//                Item drop = p.removeItem(tokens[1]);
//                if(drop == null) {
//                    System.out.println("no such item");
//                    System.out.println("try again.");
//                    continue;
//                }
//                else {
//                    p.getCurrentRoom().addItem(drop);
//                    System.out.println("you dropped " + drop.getName());
//                    System.out.println("it is " + drop.getDesc());
//                }
//            }
//            else if(resp.equals("i'd like to die a second time")) {
//                System.out.println("goodbye");
//                break;
//            }
//            else {
//                System.out.println("you seem confused");
//                System.out.println("available commands: ");
//                System.out.println("look");
//                System.out.println("go [roomname]");
//                System.out.println("add [roomname]");
//                System.out.println("i'd like to die a second time");
//            }
//
//            for(int i = 0; i < chickens.length; i++) {
//                chickens[i].act();
//            }
//        }
//    }
//
//    private static String getRandomChickenDescription() {
//        return chickenDescriptions[(int) (Math.random() * chickenDescriptions.length)];
//    }

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
            for (Creature c : g.getCreatures().values()) {
                c.act();
            }
        }

        in.close();
    }

    private static Command parseResponse(String resp) {
      StringTokenizer st = new StringTokenizer(resp);
      String[] tokens = new String[st.countTokens()];
      for(int i = 0; i < st.countTokens(); i++) {
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
        return g;
    }

    private static void initializeCreatures(Graph g) {
        int chickenId = 0;
        for(Graph.Node room : g.getMap().values()) {
            for(int i = 0; i < (int) (Math.random() * 3); i++) {
                Chicken chicken = new Chicken("chicken" + chickenId, getRandomDescription(CHICKEN_DESCRIPTIONS));
                chicken.setRoom(room);

            }
        }
    }

    private static String getRandomDescription(String[] ar) {
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
        g.addNode(new Graph.Node("a hole", "good job"));
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
        g.addUndirectedEdge("0", "sleep");
        g.addDirectedEdge("0", "???");
        g.addDirectedEdge("???", "a hole");
    }

}