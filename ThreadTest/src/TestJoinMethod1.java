class TestJoinMethod1
    extends Thread
{

    @Override
    public void run()
    {
        for (int i = 1; i <= 5; i++)
        {
            try
            {
                Thread.sleep(500);
            }
            catch (Exception e)
            {
                System.out.println(e);
            }
            System.out.println(i);
        }
    }
}
