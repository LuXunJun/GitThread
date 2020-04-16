/*
=========== 线程礼让 ===========
*/
public class ThreadYield {
    public static void main(String[] args) {
        threadYield();
    }

    /*线程礼让*/
    private static void threadYield(){
        Runnable r = ()->{
            for (int i = 0; i < 10; i++) {
                System.out.println(Thread.currentThread().getName() + ":" + i);
                if(i == 5){
                    /*线程礼让,主动释放资源给其它线程继续执行*/
                    Thread.yield();
                }
            }
        };

        Thread t1 = new Thread(r,"t1");
        Thread t2 = new Thread(r,"t2");

        t1.start();
        t2.start();

    }
}
