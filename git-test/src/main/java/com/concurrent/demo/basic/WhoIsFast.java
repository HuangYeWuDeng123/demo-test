package com.concurrent.demo.basic;

/**
 * @Author apple
 * @Date 2021/12/20 22:35
 * @Description //TODO
 **/
public class WhoIsFast {

    private static final long count = 100;

    public static void main(String[] args) throws InterruptedException {
        concurrency();
        serial();
    }

    private static void concurrency() throws InterruptedException {
        long start = System.currentTimeMillis();
        Thread t1 = new Thread(() -> {
            int a = 0;
            for (long i = 0; i < count; i++) {
                a += 5;
                System.out.println("aa");
            }
        });
        t1.start();
        int b = 0;
        for (long i = 0; i < count; i++) {
            b--;
            System.out.println("bb");
        }
        t1.join();
        System.out.println("cc");
        long time = System.currentTimeMillis() - start;
        System.out.println("-----------" + time + ";" + b);
    }

    private static void serial() {
        long start = System.currentTimeMillis();
        int a = 0;
        for (long i = 0; i < count; i++) {
            a += 5;
        }
        int b = 0;
        for (long i = 0; i < count; i++) {
            b--;
        }
        long time = System.currentTimeMillis() - start;
        System.out.println("=========" + time + ";" + b);
    }
}
