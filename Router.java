import java.util.ArrayList;
import java.util.List;

public class Router {
    private final List<Boolean> connections;
    public final Semaphore semaphore;
    private final int maxConnections;

    public Router(int maxConnections) {
        this.maxConnections = maxConnections;
        connections = new ArrayList<>(maxConnections);
        for (int i = 0; i < maxConnections; i++)
            connections.add(false);
        semaphore = new Semaphore(maxConnections);
    }

    public int connect(Device device) throws InterruptedException {
        synchronized (connections) {
            for (int i = 0; i < maxConnections; i++) {
                if (!connections.get(i)) {
                    device.connectionID = i + 1;
                    connections.set(i, true);
                    break;
                }
            }
        }
        return device.connectionID;
    }

    public void release(Device device) {
        connections.set(device.connectionID - 1, false);
        System.out.println("Connection " + device.connectionID + ": (" + device.name + ") Logged out");
        semaphore.release();
    }
}
