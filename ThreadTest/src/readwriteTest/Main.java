package readwriteTest;

public class Main
{
    public static void main(String[] args)
    {
        Dictionnaire dico = new Dictionnaire();

        ThreadWriter tw1 = new ThreadWriter("Writer 1", dico);
        tw1.start();
        ThreadWriter tw2 = new ThreadWriter("Writer 2", dico);
        tw2.start();

        for (int i = 0; i < 6; i++)
        {
            ThreadReader tr = new ThreadReader("Reader " + i, dico);
            tr.start();
        }
    }
}
