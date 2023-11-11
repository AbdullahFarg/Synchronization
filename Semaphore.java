class Semaphore
{
    private int permits; //it refers to the units or tokens that control access to a shared resource (ie: the number of available connections that devices can use to connect to the internet through the router's Wi-Fi)

    public Semaphore(int initialPermits)
    {
        if (initialPermits < 0)
        {
            throw new IllegalArgumentException("Initial permits must be non-negative");
        }
        this.permits = initialPermits;
    }

    public synchronized void acquire() throws InterruptedException     //This Function request and acquire a permit to be allowed to use the shared resource "WI-FI"
    {
        while (permits == 0)
        {
            wait(); // Wait until a permit "connection" is available
        }
        permits--; //we take from the Premits "number of connection" 1 cuz there's a one that will be used rn
    }

    public synchronized void release()           //This Function indicates that it has finished using the resource it wanted
    {
        permits++;   //we increas number of connection by 1 cuz there's a thread ended from using it
        notify(); // Notify waiting threads that a permit is available
    }
}
