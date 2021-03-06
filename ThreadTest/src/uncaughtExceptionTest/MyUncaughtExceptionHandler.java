package uncaughtExceptionTest;

import java.lang.Thread.UncaughtExceptionHandler;

public class MyUncaughtExceptionHandler
    implements UncaughtExceptionHandler
{

    @Override
    public void uncaughtException(Thread t, Throwable e)
    {
        System.out.println("Une exception de type : " + e.getClass().getName());
        System.out.println("Est survenue dans " + t.getName());
    }
}
