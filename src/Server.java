import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Server {
    private static String[] names = {"Wily", "Felix", "Carlsbad", "Hobob"};
    private static String[] adjs = {"the gentle", "the un-gentle", "the overwrought", "the urbane"};
    private static HashMap<String, String> objDesc = new HashMap<>();
    private static StringTokenizer st;
    private static final int PORT = 9090;
    public static void main(String[] args) throws IOException {
        ServerSocket listener = new ServerSocket(PORT);
        System.out.println("[SERVER] Waiting for client connection...");
        Socket client = listener.accept();
        System.out.println("[SERVER] Connected to client!");
        PrintWriter out = new PrintWriter(client.getOutputStream(), true);
        BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()));
        try {
            while (true) {
                String request = in.readLine();
                if (request.contains("name")) {
                    out.println(getRandomName());
                }
                st = new StringTokenizer(request);
                int tokenCt = st.countTokens();
                String[] tokens = new String[tokenCt];
                for(int i = 0; i < tokenCt; i++) {
                    tokens[i] = st.nextToken();
                }
                int isIndex = contains(tokens, "is");
                if(tokenCt > 3 && isIndex > 0) {
                    String object = "";
                    String def = "";
                    for(int i = 0; i < isIndex; i++) {
                        object += tokens[i];
                        if(i != isIndex - 1) {
                            object += " ";
                        }
                    }
                    for(int i = isIndex; i < tokenCt; i++) {
                        def += tokens[i];
                        if(i != tokenCt - 1) {
                            def += " ";
                        }
                    }
                    if(tokens[isIndex - 1].equals("what")) {
                        if(objDesc.containsKey(object)) {
                            out.println(object + " is " + objDesc.get(object));
                        }
                    }
                    else {
                        objDesc.put(object, def);
                    }
                }

            }
        } finally {
            out.close();
            in.close();
        }
    }

    private static int contains(String[] ar, String in) {
        for(int i = 0; i < ar.length; i++) {
            if(ar[i].equals(in)) return i;
        }
        return -1;
    }

    public static String getRandomName() {
        String name = names[(int) (Math.random() * names.length)];
        String adj = adjs[(int) (Math.random() * adjs.length)];
        return name + " " + adj;
    }
}