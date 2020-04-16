/*
=========== 线程实现单例 ===========
*/

public class ThreadSingleton {
    public static void main(String[] args) {
        Runnable r = ()->{
            Boss boss = Boss.getBoss();
        };

        for (int i = 0; i < 100; i++) {
            new Thread(r).start();
        }
    }


}

class Boss {
    private Boss(){
        System.out.println("一个BOSS对象被实例化！");
    }
    private static Boss _boss=null;

    // 此 synchronized 修饰成 同步方法（锁方法） 这样线程就不会同时访问到此方法了
    public synchronized static Boss getBoss(){
        if(_boss == null){
            _boss = new Boss();
        }
        return _boss;
    }
}
