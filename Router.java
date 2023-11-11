import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Semaphore;
public class Router {
    private final List<Boolean> connections;
    private final Semaphore semaphore;

    Router(int maxConnections){
        connections = new ArrayList<>(maxConnections);
        for (int i = 0; i < maxConnections; i++) {
            connections.add(false);
        }
        semaphore = new Semaphore(maxConnections, true);
    }

    public int connect() throws InterruptedException{
        semaphore.acquire();
        int connectionNum = occupyConnection();
        System.out.println("Connection " + connectionNum + ": Occupied");
        return connectionNum;
    }
    public void release(int connectionNum){
        releaseConnection(connectionNum);
        semaphore.release();
    }
    private synchronized int occupyConnection(){
        for (int i = 0; i < connections.size(); i++) {
            if(!connections.get(i)){
                connections.set(i, true);
                return i+1;
            }
        }
        return -1;
    }
    private void releaseConnection(int connectionNum){
        if(connectionNum > 0 && connectionNum <= connections.size()){
            connections.set(connectionNum-1, false);
        }
    }
}
