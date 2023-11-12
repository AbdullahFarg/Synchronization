class Semaphore
{
    public int permits; //it refers to the units or tokens that control access to a shared resource (ie: the number of available connections that devices can use to connect to the internet through the router's Wi-Fi)

    public Semaphore(int initialPermits)
    {
        if (initialPermits < 0)
        {
            throw new IllegalArgumentException("Initial permits must be non-negative");
        }
        this.permits = initialPermits;
    }

    public synchronized void acquire(Device device) throws InterruptedException     //This Function request and acquire a permit to be allowed to use the shared resource "WI-FI"
    {
        permits--;
        if (permits < 0) {
            System.out.println(device.name + " (" + device.type + ")" + " arrived and waiting");
            wait();

        }
        else{
            System.out.println( device.name +" (" + device.type + ")" +" arrived");
        }

        device.router.connect(device);
    }

    public synchronized void release()           //This Function indicates that it has finished using the resource it wanted
    {
        permits++;   //we increas number of connection by 1 cuz there's a thread ended from using it
        notify(); // Notify waiting threads that a permit is available
    }
}