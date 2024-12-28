import java.util.HashMap;
import java.util.Map;

/**
 * An implementation of a Least Connections Load Balancer.
 */
public class LeastConnections<T> {

    private final Map<T, Integer> serverConnectionCounts;

    /**
     * Constructor to initialize the load balancer with a list of servers.
     */
    public LeastConnections() {
        this.serverConnectionCounts = new HashMap<>();
    }

    /**
     * Add a server to the load balancer.
     *
     * @param server the server to add
     */
    public synchronized void addServer(T server) {
        if (server == null) {
            throw new IllegalArgumentException("Server cannot be null.");
        }
        serverConnectionCounts.putIfAbsent(server, 0);
    }

    /**
     * Remove a server from the load balancer.
     *
     * @param server the server to remove
     * @return true if the server was removed, false otherwise
     */
    public synchronized boolean removeServer(T server) {
        return serverConnectionCounts.remove(server) != null;
    }

    /**
     * Get the next server with the least connections.
     */
    public synchronized T getNextServer() {
        if (serverConnectionCounts.isEmpty()) {
            throw new IllegalStateException("No servers available.");
        }

        return serverConnectionCounts.entrySet()
                .stream()
                .min(Map.Entry.comparingByValue())
                .orElseThrow(() -> new IllegalStateException("No servers available."))
                .getKey();
    }

    /**
     * Simulate adding a connection to a server.
     */
    public synchronized void incrementConnection(T server) {
        serverConnectionCounts.computeIfPresent(server, (key, count) -> count + 1);
    }

    /**
     * Simulate releasing a connection from a server.
     */
    public synchronized void decrementConnection(T server) {
        serverConnectionCounts.computeIfPresent(server, (key, count) -> Math.max(0, count - 1));
    }

    /**
     * Get the current connection count of all servers.
     */
    public synchronized Map<T, Integer> getServerConnectionCounts() {
        return new HashMap<>(serverConnectionCounts);
    }

    public static void main(String[] args) {
        // Example usage
        LeastConnections<String> loadBalancer = new LeastConnections<>();

        // Add servers
        loadBalancer.addServer("Server1");
        loadBalancer.addServer("Server2");
        loadBalancer.addServer("Server3");

        // Simulate some connections
        loadBalancer.incrementConnection("Server1");
        loadBalancer.incrementConnection("Server1");
        loadBalancer.incrementConnection("Server2");

        // Get the server with the least connections
        System.out.println("Next server: " + loadBalancer.getNextServer());

        // Simulate releasing a connection
        loadBalancer.decrementConnection("Server1");

        // Get the updated server with the least connections
        System.out.println("Next server: " + loadBalancer.getNextServer());

        // Print all server connection counts
        System.out.println("Connection counts: " + loadBalancer.getServerConnectionCounts());
    }
}
