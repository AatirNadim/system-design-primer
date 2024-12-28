import java.util.*;

/**
 * A robust implementation of IP Hashing Load Balancing algorithm.
 */
public class IpHashing<T> {

    private final List<T> servers;

    /**
     * Constructor to initialize the load balancer with a list of servers.
     *
     * @param servers the list of servers to distribute requests to
     */
    public IpHashing(List<T> servers) {
        if (servers == null || servers.isEmpty()) {
            throw new IllegalArgumentException("Server list cannot be null or empty.");
        }
        this.servers = new ArrayList<>(servers);
    }

    /**
     * Get the server corresponding to a given IP address using consistent hashing.
     *
     * @param ipAddress the client's IP address
     * @return the server selected for this IP address
     */
    public T getServerForIP(String ipAddress) {
        if (ipAddress == null || ipAddress.isEmpty()) {
            throw new IllegalArgumentException("IP address cannot be null or empty.");
        }

        int hash = ipAddress.hashCode();
        int index = Math.abs(hash % servers.size());
        return servers.get(index);
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
        IpHashing<String> loadBalancer = new IpHashing<>(initialServers);

        // Example IP addresses
        List<String> ipAddresses = List.of(
                "192.168.1.1",
                "192.168.1.2",
                "192.168.1.3",
                "192.168.1.4",
                "192.168.1.5"
        );

        for (String ip : ipAddresses) {
            System.out.println("IP: " + ip + " -> Server: " + loadBalancer.getServerForIP(ip));
        }

        // Add a new server and demonstrate hashing
        loadBalancer.addServer("Server4");
        System.out.println("Added Server4");

        for (String ip : ipAddresses) {
            System.out.println("IP: " + ip + " -> Server: " + loadBalancer.getServerForIP(ip));
        }

        // Remove a server and demonstrate hashing
        loadBalancer.removeServer("Server2");
        System.out.println("Removed Server2");

        for (String ip : ipAddresses) {
            System.out.println("IP: " + ip + " -> Server: " + loadBalancer.getServerForIP(ip));
        }
    }
}
