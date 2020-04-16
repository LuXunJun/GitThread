
// 线程（1）处理业务代码
class ThreadOneMethod extends ThreadBase{
    public ThreadOneMethod(Object lock) {
        super(lock);
    }
    public void Run() throws InterruptedException {
        synchronized (lock){
            while (!ThreadBase.isThreadOne){
                lock.wait();
                System.out.println("当前线程:" + Thread.currentThread().getName() + "处于wait()" + "lock" + lock);
            }
            System.out.println("当前线程:" + Thread.currentThread().getName() + "处理线程代码");
        }
    }
}

// 线程（2）处理业务代码
class ThreadTwoMethod extends ThreadBase{
    public ThreadTwoMethod(Object lock) {
        super(lock);
    }
    public static void Run() throws InterruptedException {
        synchronized (lock){
            while (!ThreadBase.isThreadTwo){
                lock.wait();
                System.out.println("当前线程:" + Thread.currentThread().getName() + "处于wait()" + "lock" + lock);
            }
            System.out.println("当前线程:" + Thread.currentThread().getName() + "处理线程代码");
        }
    }
}

// 消息同步数据类
class ThreadBase{
    public static Boolean isThreadOne = false;
    public static Boolean isThreadTwo = false;
    public static Object lock;

    public ThreadBase(Object lock) {
        this.lock = lock;
    }
}


public class 通知指定的消息处理线程 {

    private static Object lock = new Object();;
    public static void main(String[] args) throws InterruptedException {

        // 处理业务线程_1
        new Thread(()->{
            ThreadOneMethod threadOneMethod = new ThreadOneMethod(lock);
            try {
                threadOneMethod.Run();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"t1").start();

        // 处理业务线程_2——2——2
        new Thread(()->{
            ThreadTwoMethod threadTwoMethod = new ThreadTwoMethod(lock);
            try {
                threadTwoMethod.Run();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"t2").start();

        Thread.sleep(2000);

        // 控制线程
        new Thread(()->{
            synchronized(lock){
                ThreadBase.isThreadTwo= true;
                lock.notifyAll();
                System.out.println(Thread.currentThread().getName() +  "控制线程_notifyAll" + "lock:" + lock);
            }
        },"t3").start();



    }
}
