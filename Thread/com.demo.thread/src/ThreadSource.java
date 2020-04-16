import java.util.concurrent.locks.ReentrantLock;

/*
=========== 共享线程资源 ===========
*/
public class ThreadSource {

    public static void main(String[] args) {

        /*线程锁*/
        ReentrantLock lock = new ReentrantLock();

        Runnable r =()->{
            while(Ticker.tickercount > 0){
                /*================================
                1、【同步代码段】
                锁分为：
                    1、对象【实例】锁 “” 空的字符串
                    2、类锁 ThreadSource.class
                当一个线程正在执行的时候，其它线程都会在【线程池】中进行等待
                */
                synchronized (ThreadSource.class){
                    if(Ticker.tickercount > 0){
                        System.out.println(Thread.currentThread().getName() + ": 买票" + --Ticker.tickercount + "张" );
                    }
                }

                /*==================================
                2、【同步方法】
                 */
                synchronizedfunction();

                /*==================================
                3、同步线程锁
                */
                lock.lock();
                if(Ticker.tickercount > 0){
                    System.out.println(Thread.currentThread().getName() + ": 买票" + --Ticker.tickercount + "张" );
                }
                lock.unlock();

            }
        };

        Thread t1 = new Thread(r,"t1");
        Thread t2 = new Thread(r,"t2");
        Thread t3 = new Thread(r,"t3");
        Thread t4 = new Thread(r,"t4");

        t1.start();
        t2.start();
        t3.start();
        t4.start();
    }

    /*e
    同步方法
    同步方法锁怎么设置
    静态方法：同步锁就是当前类.class为同步锁
    非静态方法: this同步锁
    */
    private synchronized static void synchronizedfunction(){
        if(Ticker.tickercount > 0){
            System.out.println(Thread.currentThread().getName() + ": 买票" + --Ticker.tickercount + "张" );
        }
    }
}

class Ticker{
    public static int tickercount =  100;

}