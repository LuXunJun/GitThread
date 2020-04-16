import java.beans.PropertyEditorSupport;
import java.util.LinkedList;
import java.util.concurrent.SynchronousQueue;


// 消费队列
class QueueMessageInfo{
    private LinkedList<MessageInfo> messageInfos = new LinkedList<MessageInfo>();
    private Integer messageInfoscount = 0;

    public QueueMessageInfo(Integer messageInfoscount) {
        this.messageInfoscount = messageInfoscount;
    }

    public MessageInfo Get() throws InterruptedException {
        synchronized(messageInfos){
            if(messageInfos.size() == 0){
                messageInfos.wait();
            }
            MessageInfo result = messageInfos.removeFirst();
            // 我已经消费完成、通知生产线程可以进生产操作
            messageInfos.notifyAll();
            System.out.println("当前线程：" + Thread.currentThread().getName() + "消费队列的数据:" + result);
            return result;
        }
    }

    public void Put(MessageInfo item) throws InterruptedException {
        synchronized(messageInfos){
            if(messageInfos.size() >= messageInfoscount){
                // 如果队列已经满
                messageInfos.wait();
            }
            // 加入到messageinfo队列中去
            messageInfos.addLast(item);
            // 我已经生产完成、通知消费线程可以进行消费处理
            messageInfos.notifyAll();
            System.out.println("当前线程：" + Thread.currentThread().getName() + "加入队列的数据:" + item);
        }
    }
}

// 消息队列数据模型类
class MessageInfo{
    private int id;
    private Object message;

    public MessageInfo(int id, Object message) {
        this.id = id;
        this.message = message;
    }

    public int getId() {
        return id;
    }

    public Object getMessage() {
        return message;
    }

    @Override
    public String toString() {
        return "Message{" +
                "id=" + id +
                ", message=" + message +
                '}';
    }
}

public class 消费者生产模式队列 {
    public static void main(String[] args) throws InterruptedException {
        QueueMessageInfo queuemessage = new QueueMessageInfo(2);

        for (int i = 0; i < 4; i++) {
            //生产 （4）组 线程
            int id = i;
            new Thread(()->
            {
                try {
                    queuemessage.Put(new MessageInfo(id,"值:" + id));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            },"生产线程:" + i).start();
        }

        Thread.sleep(2000);

        // 消费 (1)组 线程
        new Thread(()->{
            try {
                while (true){
                    MessageInfo result =  queuemessage.Get();
                    /*System.out.println("消费者最终获取到的值：" + result);*/
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"消费线程:" + 1).start();
    }
}
