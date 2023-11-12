import java.util.ArrayList;
import java.util.List;
import static java.lang.Thread.sleep;
public class Router {
    public final List<Boolean> connections;
    public final Semaphore semaphore;
    public int maxConnections ;

    Router(int maxConnections){
        this.maxConnections = maxConnections;
        connections = new ArrayList<>(maxConnections);
        for(int i = 0 ; i < maxConnections ; i++)
            connections.add(false);
        semaphore = new Semaphore(maxConnections);
    }

    public int connect(Device device) throws InterruptedException{
        for (int i = 0; i < maxConnections; i++) {
            if(!connections.get(i)){
                device.connectionID = i + 1;
                connections.set(i, true);
                sleep(100);
                break;
            }
        }
        return device.connectionID;
    }
    public synchronized void release(Device device){
        connections.set(device.connectionID - 1, false);
        System.out.println("Connection " + device.connectionID + ": " + device.name + " Logged out");
        notify();
    }
//    public void release(int connectionNum){
//        releaseConnection(connectionNum);
//        semaphore.release();
//    }
//    private synchronized int occupyConnection(){
//        for (int i = 0; i < connections.size(); i++) {
//            if(!connections.get(i)){
//                connections.set(i, true);
//                return i+1;
//            }
//        }
//        return -1;
//    }
//    private void releaseConnection(int connectionNum){
//        if(connectionNum > 0 && connectionNum <= connections.size()){
//            connections.set(connectionNum-1, false);
//        }
//    }
//    public synchronized void arrived(Device device){
////        System.out.println( device.name +" (" + device.type + ")" +" arrived");
//    }
}