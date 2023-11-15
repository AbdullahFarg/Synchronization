public class Semaphore {
    private int permits;

    public Semaphore(int initialPermits) {
        if (initialPermits < 0) {
            throw new IllegalArgumentException("Initial permits must be non-negative");
        }
        this.permits = initialPermits;
    }

    public synchronized void acquire(Device device) throws InterruptedException {
        while (permits <= 0) {
            System.out.println(device.name + " (" + device.type + ")" + " arrived and waiting");
            wait();
        }
        permits--;
        System.out.println(device.name + " (" + device.type + ")" + " arrived");
    }

    public synchronized void release() {
        permits++;
        notify();
    }
}
