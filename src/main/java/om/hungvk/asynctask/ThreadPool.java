package om.hungvk.asynctask;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

class VegetableChopper extends Thread {
    public void run() {
        System.out.println(Thread.currentThread().getName() + " chopped a vegetable!");
    }
}

public class ThreadPool {
    public static void main(String args[]) {
    	Integer numProcess = Runtime.getRuntime().availableProcessors();
    	System.out.println(numProcess);
    	ExecutorService pool =  Executors.newFixedThreadPool(numProcess);
        for (int i=0; i<100; i++)
            pool.submit(new VegetableChopper());
        
        pool.shutdown();
    }
}
