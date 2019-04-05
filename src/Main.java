import java.util.Scanner;
import java.util.StringTokenizer;

public class Main {

    private static final String[] chickenDescriptions = {"fat", "skinny", "good", "bad", "sleepy"};

    public static void main(String[] args) {

        Graph g = new Graph();
        g.addNode(new Graph.Node("bed", "comfy"));

        Player p = new Player("chip", "big boi");
        p.setRoom(g.getNode("bed"));
        g.getNode("bed").addItem(new Item("pillow", "warm"));

        g.addNode(new Graph.Node("bathroom", "cold"));
        g.addNode(new Graph.Node("window", "breezy"));
        g.addNode(new Graph.Node("sleep", "nice"));
        g.addNode(new Graph.Node("die", "nothing"));

        g.addUndirectedEdge("bed", "bathroom");
        g.addUndirectedEdge("bed", "window");
        g.addUndirectedEdge("bed", "sleep");
        g.addDirectedEdge("sleep", "die");
        g.addDirectedEdge("window", "die");
        g.addDirectedEdge("bathroom", "die");
        g.addUndirectedEdge("sleep", "sleep");

        Chicken[] chickens = new Chicken[100];
        for(int i = 0; i < chickens.length; i++) {
            chickens[i] = new Chicken("chicken" + (i + 1), getRandomChickenDescription());
            chickens[i].setRoom(g.getNode("sleep"));
        }

        String resp = "";
        Scanner in = new Scanner(System.in);
        StringTokenizer st;

        while(true) {
            System.out.println("***\nYou are in the " + p.getCurrentRoom().getName());
            System.out.println("it is " + p.getCurrentRoom().getDescription());
            System.out.println("What do you want to do? >");
            resp = in.nextLine();
            st = new StringTokenizer(resp);
            String[] tokens = new String[st.countTokens()];
            for(int i = 0; i < tokens.length; i++) {
                tokens[i] = st.nextToken();
            }

            if(tokens.length == 0) {
                System.out.println("please don't break this delicate game");
                System.out.println("try again.");
                continue;
            }

            if(tokens[0].equals("go")) {
                if(tokens.length == 1) {
                    System.out.println("go where?");
                    System.out.println("try again.");
                    continue;
                }
                else if(tokens.length > 2) {
                    System.out.println("syntax: go [roomname]");
                    System.out.println("try again.");
                    continue;
                }
                Graph.Node to = g.getNode(tokens[1]);
                if(to == null) {
                    System.out.println("no such room");
                    System.out.println("try again.");
                    continue;
                }

                p.moveToRoom(to.getName());
            }
            else if(tokens[0].equals("look")) {
                if(tokens.length > 1) {
                    System.out.println("syntax: look");
                    System.out.println("try again.");
                    continue;
                }
                System.out.println("you see rooms: " + p.getCurrentRoom().getNeighborNames());
                System.out.println("you see items: " + p.getCurrentRoom().displayItems());
                //TODO: make chicken display a better implementation
                System.out.println("you see these chickens: ");
                for(int i = 0 ; i < chickens.length; i++) {
                    if(chickens[i].currentRoom == p.currentRoom) {
                        System.out.println(chickens[i].getName());
                    }
                }
            }
            else if(tokens[0].equals("add")) {
                if(tokens.length == 1) {
                    System.out.println("add what?");
                    System.out.println("syntax: add [roomname]");
                    System.out.println("try again.");
                    continue;
                }
                if(tokens.length > 2) {
                    System.out.println("syntax: add [roomname]");
                    System.out.println("try again.");
                    continue;
                }
                System.out.println("what's the magic word?");
                String password = in.nextLine();
                if(password.equals("i'd like to die a second time")) {
                    System.out.println("admins can't die, silly");
                    System.out.println("that's not the magic word");
                    System.out.println("try again");
                    continue;
                }
                else if(password.equals("alliwantinlifeisanibmmodelf122")) {
                    System.out.println("me too");
                    System.out.println("what is this room like?");
                    String d = in.nextLine();
                    g.addNode(new Graph.Node(tokens[1], d));
                    g.addUndirectedEdge(p.getCurrentRoom().getName(), tokens[1]);
                    System.out.println("room added");
                    continue;
                }
                else {
                    System.out.println("that's not the magic word");
                    System.out.println("try again.");
                    continue;
                }
            }
            else if(tokens[0].equals("take")) {
                if(tokens.length == 1) {
                    System.out.println("take what?");
                    System.out.println("syntax: take [itemname]");
                    System.out.println("try again.");
                    continue;
                }
                if(tokens.length > 2) {
                    System.out.println("syntax: take [itemname]");
                    System.out.println("try again.");
                    continue;
                }
                Item take = p.getCurrentRoom().removeItem(tokens[1]);
                if(take == null) {
                    System.out.println("no such item");
                    System.out.println("try again.");
                    continue;
                }
                else {
                    p.addItem(take);
                    System.out.println("you took " + take.getName());
                    System.out.println("it is " + take.getDesc());
                    continue;
                }
            }
            else if(tokens[0].equals("drop")) {
                if(tokens.length == 1) {
                    System.out.println("drop what?");
                    System.out.println("syntax: drop [itemname]");
                    System.out.println("try again.");
                    continue;
                }
                if(tokens.length > 2) {
                    System.out.println("syntax: drop [itemname]");
                    System.out.println("try again.");
                    continue;
                }
                Item drop = p.removeItem(tokens[1]);
                if(drop == null) {
                    System.out.println("no such item");
                    System.out.println("try again.");
                    continue;
                }
                else {
                    p.getCurrentRoom().addItem(drop);
                    System.out.println("you dropped " + drop.getName());
                    System.out.println("it is " + drop.getDesc());
                }
            }
            else if(resp.equals("i'd like to die a second time")) {
                System.out.println("goodbye");
                break;
            }
            else {
                System.out.println("you seem confused");
                System.out.println("available commands: ");
                System.out.println("look");
                System.out.println("go [roomname]");
                System.out.println("add [roomname]");
                System.out.println("i'd like to die a second time");
            }

            for(int i = 0; i < chickens.length; i++) {
                chickens[i].move();
            }
        }
    }

    private static String getRandomChickenDescription() {
        return chickenDescriptions[(int) (Math.random() * chickenDescriptions.length)];
    }
}