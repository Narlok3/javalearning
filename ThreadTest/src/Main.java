public class Main
{

    public static void main(String[] args)
    {
        Thread t1 = new Thread1("t1");

        Thread t2 = new Thread1("t2");

        t1.start();
        t2.start();

        System.out.println("Coucou");

        TestJoinMethod1 a1 = new TestJoinMethod1();
        TestJoinMethod1 a2 = new TestJoinMethod1();
        TestJoinMethod1 a3 = new TestJoinMethod1();

        a1.start();
        try
        {
            a1.join();
        }
        catch (Exception e)
        {
            System.out.println(e);
        }

        a2.start();
        a3.start();

    }

}