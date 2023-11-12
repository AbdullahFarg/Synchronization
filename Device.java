
public class Device extends Thread {
    public String name, type;
    public int connectionID;
    public  Router router;

    public Device(String name, String type, Router router) {
        this.name = name;
        this.type = type;
        this.router = router;
        connectionID = 1;
    }
    public void run() {
        try {
//            router.arrived(this);
            router.semaphore.acquire(this);
            connectionID = router.connect(this);
            System.out.println("Connection " + connectionID + ": (" + name + ") Occupied");
            login();
            activity();
            router.release(this);
            router.semaphore.release();

        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    public void activity() throws InterruptedException {
        System.out.println("Connection " + connectionID + ": (" + name + ") Performs online activity");
        sleep(2000);
    }
    public void login() throws InterruptedException {
        System.out.println("Connection " + connectionID + ": (" + name + ") login");
        sleep(2000);
    }
}