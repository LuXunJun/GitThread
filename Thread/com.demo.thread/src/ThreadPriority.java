
/*
=========== 线程优先级 ===========
*/
public class ThreadPriority {
    public static void main(String[] args) {
        threadpriority();
    }

    // 线程优先级
    private static void threadpriority(){

        /*线程run的内部逻辑,实现Runnable(可运行)接口,重写Run的方法*/
        Runnable r = () -> {
            for (int i = 0; i < 10; i++) {
                System.out.println(Thread.currentThread().getName() + ":" + i);
            }
        };

        /*创建线程*/
        Thread t1 = new Thread(r,"t1");
        Thread t2 = new Thread(r,"t2");

        /*设置线程优先级 范围从【0：10】*/
        t1.setPriority(10);
        t2.setPriority(5);

        /*开始启动线程*/
        t1.start();
        t2.start();
    }
}
