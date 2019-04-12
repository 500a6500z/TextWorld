import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;

public abstract class Creature implements Entity {

    protected Graph.Node currentRoom;
    protected String name;
    protected String desc;
    protected boolean isPlayer;

    public Creature(String n, String d) {
        name = n;
        desc = d;
        isPlayer = false;
    }

    public abstract void act();

    protected Graph.Node getCurrentRoom() {
        return currentRoom;
    }

    protected void setRoom(Graph.Node node) {
        this.currentRoom = node;
        currentRoom.addCreature(this);
    }

    protected boolean moveToRoom(Graph.Node node) {
        if(currentRoom.getNeighbors().get(node.getName()) != null) {
            currentRoom.removeCreature(name);
            currentRoom = node;
            currentRoom.addCreature(this);
            return true;
        }
        return false;
    }

    protected boolean moveToRoom(String name) {
        if(currentRoom.getNeighbors().get(name) != null) {
            currentRoom.removeCreature(name);
            currentRoom = currentRoom.getNeighbor(name);
            currentRoom.addCreature(this);
            return true;
        }
        return false;
    }

    //TODO: Use less memory in moveRandom() and getRandomAdjacentRoom()
    protected void moveRandom() {
        Graph.Node nextRoom = getRandomAdjacentRoom();
        if(nextRoom == null) return;
        moveToRoom(nextRoom);
    }

    protected Graph.Node getRandomAdjacentRoom() {
        ArrayList<Graph.Node> adj = new ArrayList<>(currentRoom.getNeighbors().values());
        if(adj.size() == 0) return null;
        return adj.get((int) (Math.random() * adj.size()));
    }

    //does not find shortest path but popstars aren't the smartest so it's ok
    protected boolean moveTowards(Graph.Node node) {
        if(currentRoom == node) return true;
        HashSet<Graph.Node> set = dfs(currentRoom, new HashSet<>(), node);
        if(set == null) return false;
        for(Graph.Node n : currentRoom.getNeighbors().values()) {
            if(set.contains(n)) {
                moveToRoom(n);
                return true;
            }
        }
        return false; //should never happen
    }

    private HashSet<Graph.Node> dfs(Graph.Node cur, HashSet<Graph.Node> path, Graph.Node goal) {
        for(Graph.Node neighbor : cur.getNeighbors().values()) {
            if(path.contains(neighbor)) continue;
            if(neighbor == goal) {
                path.add(goal);
                return path;
            }
            path.add(neighbor);
            if(dfs(neighbor, path, goal) != null) {
                return path;
            }
            path.remove(neighbor);
        }
        return null;
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

    public boolean isPlayer() {
        return isPlayer;
    }
}