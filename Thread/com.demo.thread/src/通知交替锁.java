
/*=============================
 天猫精灵：小爱同学
 小爱：在
 天猫精灵：我们来唱首歌吧
 小爱：好的，我喜欢的歌曲是 棉子的勇气
 天猫精灵：这首歌曲真的很好听
 小爱：对的
*/

class XiaoAi extends Thread{

    private Message message;

    public XiaoAi(Message messgae){
        this.message = messgae;
    }

    @Override
    public void run() {
        try {
            this.message.XiaoAiMessage();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class TianMao extends Thread{

    private Message message;

    public TianMao(Message messgae){
        this.message = messgae;
    }

    @Override
    public void run() {
        try {
            this.message.TianMaoMessgae();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class Baidu extends Thread{

    private Message message;

    public Baidu(Message messgae){
        this.message = messgae;
    }

    @Override
    public void run() {
        try {
            this.message.BaiduMessage();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class Message{

    public static boolean isXiaoAi =false;
    public static boolean isBaidu =false;

    public synchronized void XiaoAiMessage() throws InterruptedException {
        this.wait();
        System.out.println("小爱：在");
        this.notify();
        this.wait();
        System.out.println("小爱：好的，我喜欢的歌曲是 棉子的勇气");
        this.notify();
        this.wait();
        System.out.println("小爱：对的");
    }

    public synchronized void BaiduMessage() throws InterruptedException{

            this.wait();

        System.out.println("百度精灵：在");
        this.notify();
        this.wait();
        System.out.println("百度精灵：好的，我喜欢的歌曲是 棉子的勇气");
        this.notify();
        this.wait();
        System.out.println("百度精灵：对的");
    }

    public synchronized void TianMaoMessgae() throws InterruptedException {
        System.out.println("天猫精灵：小爱同学");
        this.notify();
        this.wait();
        System.out.println("天猫精灵：我们来唱首歌吧");
        this.notify();
        this.wait();
        System.out.println("天猫精灵：这首歌曲真的很好听");
        this.notify();
    }

}

public class 通知交替锁 {
    public static void main(String[] args) {
        Message message = new Message();

        XiaoAi xm = new XiaoAi(message);
        Baidu bd = new Baidu(message);
        TianMao tm = new TianMao(message);


        xm.start();
        bd.start();
        tm.start();
        Message.isXiaoAi =true;

    }


}
