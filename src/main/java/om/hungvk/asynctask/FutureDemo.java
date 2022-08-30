package om.hungvk.asynctask;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

class HowManyVegetables implements Callable{

	@Override
	public Integer call() throws Exception {
		System.out.println("Olivia is couting vegetable");
		Thread.sleep(3000);
		return 42;
	}
	
}

public class FutureDemo {
	public static void main(String[] args) throws InterruptedException, ExecutionException {
		System.out.println("Barron asks Olivia how many vegetables are?");
		ExecutorService exec = Executors.newSingleThreadExecutor();
		Future rs =exec.submit(new HowManyVegetables());
		System.out.println("Barron does other work");
		System.out.println("Olivia response: " + rs.get());
		System.out.println("....");
		exec.shutdown();
		
	}
}
