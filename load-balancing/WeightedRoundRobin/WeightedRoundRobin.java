import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * An implementation of a Weighted Round Robin Load Balancer.
 */
public class WeightedRoundRobin<T> {

    private final List<T> servers;
    private final Map<T, Integer> weights;
    private final Map<T, Integer> currentWeights;
    private int totalWeight;

    /**
     * Constructor to initialize the load balancer with servers and their weights.
     */
    public WeightedRoundRobin() {
        this.servers = new ArrayList<>();
        this.weights = new HashMap<>();
        this.currentWeights = new HashMap<>();
        this.totalWeight = 0;
    }

    /**
     * Add a server with a specified weight to the load balancer.
     *
     * @param server the server to add
     * @param weight the weight of the server
     */
    public synchronized void addServer(T server, int weight) {
        if (server == null) {
            throw new IllegalArgumentException("Server cannot be null.");
        }
        if (weight <= 0) {
            throw new IllegalArgumentException("Weight must be a positive integer.");
        }

        servers.add(server);
        weights.put(server, weight);
        currentWeights.put(server, 0);
        totalWeight += weight;
    }

    /**
     * Remove a server from the load balancer.
     *
     * @param server the server to remove
     * @return true if the server was removed, false otherwise
     */
    public synchronized boolean removeServer(T server) {
        if (servers.remove(server)) {
            totalWeight -= weights.get(server);
            weights.remove(server);
            currentWeights.remove(server);
            return true;
        }
        return false;
    }

    /**
     * Get the next server based on the weighted round robin algorithm.
     *
     * @return the next server
     */
    public synchronized T getNextServer() {
        if (servers.isEmpty()) {
            throw new IllegalStateException("No servers available.");
        }

        T selectedServer = null;
        int maxWeight = Integer.MIN_VALUE;

        for (T server : servers) {
            // Increase the current weight of the server
            currentWeights.put(server, currentWeights.get(server) + weights.get(server));

            // Select the server with the highest current weight
            if (currentWeights.get(server) > maxWeight) {
                maxWeight = currentWeights.get(server);
                selectedServer = server;
            }
        }

        // Reduce the current weight of the selected server by the total weight
        currentWeights.put(selectedServer, currentWeights.get(selectedServer) - totalWeight);
        return selectedServer;
    }

    /**
     * Get the current state of the servers and their weights.
     *
     * @return a map of servers and their weights
     */
    public synchronized Map<T, Integer> getServerWeights() {
        return new HashMap<>(weights);
    }

    public static void main(String[] args) {
        // Example usage
        WeightedRoundRobin<String> loadBalancer = new WeightedRoundRobin<>();

        // Add servers with weights
        loadBalancer.addServer("Server1", 3);
        loadBalancer.addServer("Server2", 2);
        loadBalancer.addServer("Server3", 1);

        // Simulate 10 requests
        for (int i = 0; i < 10; i++) {
            System.out.println("Redirecting to: " + loadBalancer.getNextServer());
        }

        // Remove a server and simulate more requests
        loadBalancer.removeServer("Server2");
        System.out.println("Removed Server2");

        for (int i = 0; i < 5; i++) {
            System.out.println("Redirecting to: " + loadBalancer.getNextServer());
        }
    }
}
