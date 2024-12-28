import java.util.HashMap;
import java.util.Map;

/**
 * A robust implementation of Least Response Time Load Balancer.
 */
public class LeastResponseTime<T> {

    private final Map<T, ServerStats> serverStats;

    /**
     * Inner class to hold server statistics such as active connections and response time.
     */
    private static class ServerStats {
        int activeConnections;
        long averageResponseTime; // in milliseconds

        ServerStats(int activeConnections, long averageResponseTime) {
            this.activeConnections = activeConnections;
            this.averageResponseTime = averageResponseTime;
        }
    }

    /**
     * Constructor to initialize the load balancer.
     */
    public LeastResponseTime() {
        this.serverStats = new HashMap<>();
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
        serverStats.putIfAbsent(server, new ServerStats(0, 0));
    }

    /**
     * Remove a server from the load balancer.
     *
     * @param server the server to remove
     * @return true if the server was removed, false otherwise
     */
    public synchronized boolean removeServer(T server) {
        return serverStats.remove(server) != null;
    }

    /**
     * Update the response time for a server.
     *
     * @param server          the server to update
     * @param responseTime the new response time in milliseconds
     */
    public synchronized void updateResponseTime(T server, long responseTime) {
        ServerStats stats = serverStats.get(server);
        if (stats != null) {
            // Update the average response time using a moving average formula
            stats.averageResponseTime = (stats.averageResponseTime + responseTime) / 2;
        }
    }

    /**
     * Simulate adding an active connection to a server.
     *
     * @param server the server to add a connection to
     */
    public synchronized void incrementConnection(T server) {
        ServerStats stats = serverStats.get(server);
        if (stats != null) {
            stats.activeConnections++;
        }
    }

    /**
     * Simulate releasing a connection from a server.
     *
     * @param server the server to release a connection from
     */
    public synchronized void decrementConnection(T server) {
        ServerStats stats = serverStats.get(server);
        if (stats != null && stats.activeConnections > 0) {
            stats.activeConnections--;
        }
    }

    /**
     * Get the next server with the least response time.
     *
     * @return the server with the least estimated response time
     */
    public synchronized T getNextServer() {
        if (serverStats.isEmpty()) {
            throw new IllegalStateException("No servers available.");
        }

        T selectedServer = null;
        long minResponseTime = Long.MAX_VALUE;

        for (Map.Entry<T, ServerStats> entry : serverStats.entrySet()) {
            T server = entry.getKey();
            ServerStats stats = entry.getValue();
            long estimatedResponseTime = stats.averageResponseTime + stats.activeConnections;

            if (estimatedResponseTime < minResponseTime) {
                minResponseTime = estimatedResponseTime;
                selectedServer = server;
            }
        }

        return selectedServer;
    }

    public static void main(String[] args) {
        // Example usage
        LeastResponseTime<String> loadBalancer = new LeastResponseTime<>();

        // Add servers
        loadBalancer.addServer("Server1");
        loadBalancer.addServer("Server2");
        loadBalancer.addServer("Server3");

        // Simulate response times and connections
        loadBalancer.updateResponseTime("Server1", 100);
        loadBalancer.updateResponseTime("Server2", 200);
        loadBalancer.updateResponseTime("Server3", 50);

        loadBalancer.incrementConnection("Server1");
        loadBalancer.incrementConnection("Server3");

        // Get the server with the least response time
        System.out.println("Next server: " + loadBalancer.getNextServer());

        // Simulate more updates
        loadBalancer.decrementConnection("Server1");
        loadBalancer.updateResponseTime("Server2", 80);

        System.out.println("Next server: " + loadBalancer.getNextServer());
    }
}
