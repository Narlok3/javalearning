package testExecutor1;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ScheduledThreadPool
{

    public static void main(String[] args)
    {

        // Cette instruction permet de lister le nombre de processeurs disponibles
        // sur la machine exécutant le programme
        int corePoolSize = Runtime.getRuntime().availableProcessors();
        System.out.println("Nombre de processeurs disponibles : " + corePoolSize);

        // Notre executor avec un nombre de processeurs fixés dynamiquement
        ScheduledExecutorService execute = Executors.newScheduledThreadPool(corePoolSize);

        // Nous créons une liste stockant les objets Future<Long>
        ArrayList<Future<Long>> listFuture = new ArrayList<>();

        // Nous créons maintenant nos objets
        Path chemin = Paths.get("C:\\Drivers");
        Path chemin2 = Paths.get("C:\\dell");
        Path chemin3 = Paths.get("C:\\Partage");

        Long total = 0L;

        // Ici, nous lançons la tâche N° 1 dans 10 secondes
        Future<Long> ft = execute.schedule(new FolderScanner(chemin), 10, TimeUnit.SECONDS);
        listFuture.add(ft);

        // Ici, nous lançons la tâche N° 2 dans 1 secondes
        Future<Long> ft2 = execute.schedule(new FolderScanner(chemin2), 1000, TimeUnit.MILLISECONDS);
        listFuture.add(ft2);

        // Ici, nous lançons la tâche N° 3 dans 1 minute
        Future<Long> ft3 = execute.schedule(new FolderScanner(chemin3), 1, TimeUnit.MINUTES);
        listFuture.add(ft3);

        // Afin d'avoir un traitement en parallèle
        // nous parcourons maintenant la liste de nos objets Future<T>
        Iterator<Future<Long>> it = listFuture.iterator();
        while (it.hasNext())
        {
            try
            {
                total += it.next().get();
            }
            catch (InterruptedException | ExecutionException e)
            {
                e.printStackTrace();
            }
        }

        System.out.println("nombre total de fichiers trouvés : " + total);

        // Dès que nos tâches sont terminées, nous fermons le pool
        // Sans cette ligne, ce programme restera en cours d'exécution
        execute.shutdown();
    }
}