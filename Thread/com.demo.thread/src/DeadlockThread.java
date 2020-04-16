/*
=========== 线程死锁 ===========
*/

public class DeadlockThread {
    public static void main(String[] args) {
        Runnable r1 =()->{
            synchronized("A"){
                System.out.println(Thread.currentThread().getName() + "：子线程进入A锁");

                synchronized("B"){
                    System.out.println(Thread.currentThread().getName() + "：子线程进入B锁");
                }
            }

        };
        Runnable r2 =()->{
            synchronized("B"){
                System.out.println(Thread.currentThread().getName() + "：子线程进入B锁");

                synchronized("A"){
                    System.out.println(Thread.currentThread().getName() + "：子线程进入A锁");
                }
            }
        };

        Thread t1 = new Thread(r1,"t1");
        Thread t2 = new Thread(r2,"t2");

        t1.start();
        t2.start();
    }
}
