/*
=========== 线程死锁 ===========
*/

/*
wait : 将线程处于等待状态,释放锁标记,线程进入到等待队列中
notify: 通知等待的线程唤醒,继续去执行线程中后面代码
notifyAll:通知等待队列中所有线程的唤醒
*/


public class ThreadWaitNotify {
    public static void main(String[] args) {
        Runnable r1 =()->{
            synchronized("A"){
                System.out.println(Thread.currentThread().getName() + "：子线程进入A锁");

                // 将A线程处于等待状态，进入到等待队列中，释放A锁
                try {
                    "A".wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
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
                    // 通知唤醒A线程，继续执行后续代码
                    "A".notify();
                }
            }
        };

        Thread t1 = new Thread(r1,"t1");
        Thread t2 = new Thread(r2,"t2");

        t1.start();
        t2.start();
    }
}
