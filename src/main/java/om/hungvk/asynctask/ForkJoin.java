package om.hungvk.asynctask;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

class RecursiveSum extends RecursiveTask<Long>{
	
	private long lo, hi;
	
	public RecursiveSum(long lo , long hi) {
		this.hi= hi;
		this.lo = lo;
	}

	@Override
	protected Long compute() {
		if(hi-lo <= 100_000) {
			long total = 0;
			for(long i=lo;i<=hi;i++) {
				total+=i;
			}
			return total;
		} else {
			long mid = (hi+lo)/2;
			RecursiveSum lef = new RecursiveSum(lo, mid);
			RecursiveSum right = new RecursiveSum(mid+1, hi);
			lef.fork();
			return right.compute() + lef.join();
		}
	}
	
}

public class ForkJoin {
	public static void main(String[] args) {
		ForkJoinPool pool = ForkJoinPool.commonPool();
		Long total = pool.invoke(new RecursiveSum(0, 1_000_000_000));
		pool.shutdown();
		System.out.println(total);
	}
}
