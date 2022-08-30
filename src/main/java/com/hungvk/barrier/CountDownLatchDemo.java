package com.hungvk.barrier;

/**
 * Deciding how many bags of chips to buy for the party
 */

import java.util.concurrent.locks.*;
import java.util.concurrent.*;

class ShopperCountDownLatch extends Thread {

    public static int bagsOfChips = 1; // start with one on the list
    private static Lock pencil = new ReentrantLock();
    private static CountDownLatch fistBump = new CountDownLatch(5);

    public ShopperCountDownLatch(String name) {
        this.setName(name);
    }

    public void run() {
        if (this.getName().contains("Olivia")) {
            pencil.lock();
            try {
                bagsOfChips += 3;
                System.out.println(this.getName() + " ADDED three bags of chips.");
            } finally {
                pencil.unlock();
            }
            fistBump.countDown();
        } else { // "Barron"
            try {
                fistBump.await();
            } catch (InterruptedException  e) {
                e.printStackTrace();
            }
            pencil.lock();
            try {
                bagsOfChips *= 2;
                System.out.println(this.getName() + " DOUBLED the bags of chips.");
            } finally {
                pencil.unlock();
            }
        }
    }
}

public class CountDownLatchDemo {
    public static void main(String args[]) throws InterruptedException  {
        // create 10 shoppers: Barron-0...4 and Olivia-0...4
    	ShopperCountDownLatch[] shoppers = new ShopperCountDownLatch[10];
        for (int i=0; i<shoppers.length/2; i++) {
            shoppers[2*i] = new ShopperCountDownLatch("Barron-"+i);
            shoppers[2*i+1] = new ShopperCountDownLatch("Olivia-"+i);
        }
        for (ShopperCountDownLatch s : shoppers)
            s.start();
        for (ShopperCountDownLatch s : shoppers)
            s.join();
        System.out.println("We need to buy " + ShopperCountDownLatch.bagsOfChips + " bags of chips.");
    }
}
