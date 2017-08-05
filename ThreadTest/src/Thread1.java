public class Thread1
    extends Thread
{

    private String name;

    public Thread1(String name)
    {
        this.name = name;
    }

    @Override
    public void run()
    {
        for (int i = 0; i < 10; i++)
        {
            System.out.println("My name is : " + name + " " + i);
            try
            {
                sleep(1000);
            }
            catch (InterruptedException e)
            {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

    }

}
