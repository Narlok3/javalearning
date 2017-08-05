package testSynchro1;

public class Increment
{
    private int entier = 0;

    public void incremente()
    // public synchronized void incremente()
    {
        synchronized (this)
        {
            entier++;
        }
    }

    public Integer get()
    // public synchronized void get()
    {
        synchronized (this)
        {
            return entier;
        }
    }
}
