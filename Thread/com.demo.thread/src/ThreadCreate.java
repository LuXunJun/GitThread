
/*
=========== 创建线程 ===========
*/
public class ThreadCreate {
    public static void main(String[] args) {

       /* 创建线程:
        1、通过自定义类创建线程、重写Run方法*/
        MyThread mythread = new MyThread();
        mythread.start();

        /*
        2、通过Runable去实现线程创建
        Runable接口中有对run这个抽象方法定义，所以实现线程必须重写run的方法
        * */
        Runnable r = ()->{
            for (int i = 0; i < 10; i++) {
                System.out.println("子线程逻辑数:" + i);
            }
        };

        Thread t1 = new Thread(r,"t1");
        t1.start();
    }
}

/*自定义线程类*/
class MyThread extends Thread{

    /*需要重写run方法
    * 用于执行线程中的逻辑
    * */
    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            System.out.println("子线程逻辑数:" + i);
        }
    }
}
