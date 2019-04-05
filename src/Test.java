public class Test {

    /*
    TODO: Test moveTowards() works
     */
    public static void main(String[] args) {
        Graph g = new Graph();

        g.addNode(new Graph.Node("a", ""));
        g.addNode(new Graph.Node("b", ""));
        g.addNode(new Graph.Node("c", ""));
        g.addNode(new Graph.Node("e", ""));
        g.addNode(new Graph.Node("f", ""));
        g.addNode(new Graph.Node("g", ""));
        g.addNode(new Graph.Node("n", ""));
        g.addNode(new Graph.Node("x", ""));
        g.addNode(new Graph.Node("y", ""));
        g.addNode(new Graph.Node("z", ""));

        g.addUndirectedEdge("a", "b");
        g.addUndirectedEdge("b", "c");
        g.addUndirectedEdge("c", "f");
        g.addUndirectedEdge("f", "g");
        g.addUndirectedEdge("b", "f");
        g.addUndirectedEdge("n", "g");
        g.addUndirectedEdge("x", "g");
        g.addUndirectedEdge("x", "y");
        g.addUndirectedEdge("g", "z");
        g.addUndirectedEdge("b", "e");

        Player p = new Player("me", "me");
        PopStar s = new PopStar("mem", "mem", p);

        p.setRoom(g.getNode("a"));
        s.setRoom(g.getNode("g"));

        for(int i = 0; i < 5; i++) {
            p.moveRandom();
            s.move();

            System.out.println("player: " + p.getCurrentRoom().getName());
            System.out.println("star: " + s.getCurrentRoom().getName());
            System.out.println("***");
        }
    }
}
