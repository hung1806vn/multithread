package com.hungvk.synchronization;
/**
 * Connecting cell phones to a charger
 */

import java.util.concurrent.*;

class CellPhone extends Thread {

	/*
	 * Diff between Semaphore and Mutex (lock)
	 * Mutex can only be acquire and release in same thread
	 * Semaphore is opposite
	 */
	
    private static Semaphore charger = new Semaphore(4);

    public CellPhone(String name) {
        this.setName(name);
    }

    public void run() {
        try {
            charger.acquire();
            System.out.println(this.getName() + " is charging...");
            Thread.sleep(ThreadLocalRandom.current().nextInt(1000, 2000));
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            System.out.println(this.getName() + " is DONE charging!");
            charger.release();
        }
    }
}

public class SemaphoreDemo {
    public static void main(String args[]) {
        for (int i =0; i < 10; i++)
            new CellPhone("Phone-"+i).start();
    }
}