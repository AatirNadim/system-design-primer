import java.util.ArrayList;
import java.util.List;

public class RoundRobin<T> {

    private List<T> servers;
    private int currentIndex;

    /**
     * Initializes the server node list
     */
    public RoundRobin(List<T> servers) {
        if (servers == null || servers.isEmpty()) {
            throw new IllegalArgumentException("Server list cannot be null or empty.");
        }
        this.servers = new ArrayList<>(servers);
        this.currentIndex = -1;
    }

    /**
     * Get the next server in a round-robin manner.
     *
     * @return the next server
     */
    public synchronized T getNextServer() {
        if (servers.isEmpty()) {
            throw new IllegalStateException("No servers available.");
        }
        currentIndex = (currentIndex + 1) % servers.size();
        return servers.get(currentIndex);
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
        servers.add(server);
    }

    /**
     * Remove a server from the load balancer.
     *
     * @param server the server to remove
     * @return true if the server was removed, false otherwise
     */
    public synchronized boolean removeServer(T server) {
        return servers.remove(server);
    }

    /**
     * Get the current list of servers.
     *
     * @return a list of servers
     */
    public synchronized List<T> getServers() {
        return new ArrayList<>(servers);
    }

    public static void main(String[] args) {
        // Example usage
        List<String> initialServers = List.of("Server1", "Server2", "Server3");
        RoundRobin<String> loadBalancer = new RoundRobin<>(initialServers);

        // Simulate 10 requests
        for (int i = 0; i < 10; i++) {
            System.out.println("Redirecting to: " + loadBalancer.getNextServer());
        }

        // Add a new server and simulate more requests
        loadBalancer.addServer("Server4");
        System.out.println("Added Server4");

        for (int i = 0; i < 5; i++) {
            System.out.println("Redirecting to: " + loadBalancer.getNextServer());
        }

        // Remove a server
        loadBalancer.removeServer("Server2");
        System.out.println("Removed Server2");

        // Simulate more requests
        for (int i = 0; i < 5; i++) {
            System.out.println("Redirecting to: " + loadBalancer.getNextServer());
        }
    }
}
