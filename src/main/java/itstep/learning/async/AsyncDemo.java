package itstep.learning.async;

import java.util.concurrent.*;

public class AsyncDemo
{
    private final ExecutorService pool = Executors.newFixedThreadPool(3);

    public void run()
    {
        System.out.println("AsyncDemo");
//        percentDemo();
//        taskDemo();
//        pandigitalDemo();
        pandigitalTaskDemo();
    }

    private void pandigitalTaskDemo()
    {
        pandigital = "";
        try{

            Future<String>[] tasks = new Future[10];

            for (int i = 1; i <= 10; i++)
            {
                tasks[i-1] = pool.submit(new PandigitalCallable(i));
            }

            for (int i = 1; i <= 10; i++)
            {
                String pandigitalTask = tasks[i-1].get();
                if(pandigitalTask.length() == 10)
                {
                    System.out.println("task: " + (i) + " pandigital: " + pandigitalTask + " ++++++++ Last");
                }
                else {
                    System.out.println("task: " + (i) + " pandigital: " + pandigitalTask);
                }
            }
        }
        catch (InterruptedException ignore){}
        catch (ExecutionException e) {
            throw new RuntimeException(e);
        }

        pool.shutdown();
        try{
            pool.awaitTermination(5000,TimeUnit.MICROSECONDS);

        }catch (InterruptedException ignore){}
        pool.shutdownNow();
    }

    class PandigitalCallable implements Callable<String>{

        private final int task;

        public PandigitalCallable(int task){
            this.task = task;
        }

        @Override
        public String call() throws Exception {

            String temp;
            synchronized (sumLocker)
            {
                pandigital = pandigital + (task - 1);
                temp = pandigital;

            }
            return temp;
        }
    }




    private String pandigital;
    private int treadsCountdown;

    private void pandigitalDemo()
    {
        pandigital = "";
        treadsCountdown = 10;
        Thread[] treads = new Thread[treadsCountdown];
        for(int i = 1; i <= treadsCountdown; i++)
        {
            treads[i-1] = new Thread(new PandigitalRunnable(i));
            treads[i-1].start();
        }

        for(int i = 1; i <= 10; i++)
        {
            try{
                treads[i-1].join();
            } catch (InterruptedException ignore) {}
        }

        System.out.println("total: " + pandigital);
    }

    private class PandigitalRunnable implements Runnable{

        private final int thread;

        public PandigitalRunnable(int thread)
        {
            this.thread = thread;
        }

        @Override
        public void run()
        {
            try {
                TimeUnit.MICROSECONDS.sleep(2000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            String temp;
            int lockCountdown;

            synchronized (sumLocker)
            {
                pandigital = pandigital + (thread - 1);
                temp = pandigital;
                lockCountdown = --treadsCountdown;
            }
            System.out.println("thread: " + thread + " pandigital: " + temp);

            if (lockCountdown == 0)
            {
                System.out.println("thread: " + thread + " pandigital: " + pandigital + " Last");
            }
        }
    }































    private void taskDemo()
    {
        Callable<String> callable = new Callable<String>(){
            @Override
            public String call() throws Exception {
                try{ TimeUnit.MICROSECONDS.sleep(500); } catch (InterruptedException ignore) {}
                return "Callable data";
            }
        };

        Callable<String> callable2 = () ->{
            try{ TimeUnit.MICROSECONDS.sleep(5000); } catch (InterruptedException ignore) {}
            return "Callable2 data";
        };

        Future<String> task1 = pool.submit(callable);
        Future<String> task2 = pool.submit(callable2);
        Future<Double> task3 = pool.submit(()->getPetcent(1));

        sum = 100;
        try{
            String res1 = task1.get();
            System.out.println(res1);
            String res2 = task2.get();
            System.out.println(res2);
            Double res3 = task3.get();
            System.out.println(res3);

            Future<Double>[] tasks = new Future[12];

            for (int i = 1; i <= 12; i++)
            {
                tasks[i-1] = pool.submit(new PersentCallable(i));

            }

            for (int i = 1; i <= 12; i++)
            {
                double percent = tasks[i-1].get();
                double factor = 1.0 + percent/100.0;

                sum = sum * factor;

                System.out.println("month: " + (i) + " sum: " + sum);
            }

        }
        catch (InterruptedException ignore){}
        catch (ExecutionException e) {
            throw new RuntimeException(e);
        }

        pool.shutdown();
        try{
            pool.awaitTermination(5000,TimeUnit.MICROSECONDS);

        }catch (InterruptedException ignore){}
        pool.shutdownNow();
    }



    class PersentCallable implements Callable<Double>{

        private final int month;

        public PersentCallable(int month){
            this.month = month;
        }

        @Override
        public Double call() throws Exception {


            double percent = getPetcent(month);

            return percent;
        }
    }

    private String message;
    private void threadDemo()
    {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                message = "Runnable";
            }
        });
        thread.start();
        System.out.println("Main");
        System.out.println("Main");
        try { thread.join();
        }catch (InterruptedException ignore){}

        System.out.println(message);
        System.out.println("Main");
    }

    private double sum;

    private void percentDemo() {
        sum = 100.0;
        treadsCountdown = 12;
        Thread[] treads = new Thread[treadsCountdown];
        for(int i = 1; i <= treadsCountdown; i++)
        {
            treads[i-1] = new Thread(new PercentRunnable(i));
            treads[i-1].start();
        }

        for(int i = 1; i <= 12; i++)
        {
            try{
                treads[i-1].join();
            } catch (InterruptedException ignore) {}
        }

        System.out.println("total " + sum + " ==== Last");
    }

    private class PercentRunnable implements Runnable{

        private final int month;

        public PercentRunnable(int month)
        {
            this.month = month;
        }

        @Override
        public void run()
        {
            double factor = 1.0 + getPetcent(month) / 100.0;
            double temp;
            int lockCountdown;

            synchronized (sumLocker)
            {
                sum = sum * factor;
                temp = sum;
                lockCountdown = --treadsCountdown;
            }
            System.out.println("month: " + month + " sum: " + temp);

            if (lockCountdown == 0)
            {
                System.out.println("month: " + month + " sum: " + temp + " --------------");
            }
        }
    }

    private final Object sumLocker = new Object();

    private double getPetcent(int month)
    {
        try{
            TimeUnit.MICROSECONDS.sleep(2000);
        } catch (InterruptedException ignore) {}
        return 10.0;
    }
}