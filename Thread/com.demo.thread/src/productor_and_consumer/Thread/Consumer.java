package productor_and_consumer.Thread;

import productor_and_consumer.QueuePool.Listpool;
import productor_and_consumer.Source.Product;

/*消费者线程*/
public class Consumer extends Thread {
    private Listpool pool;

    public Consumer(Listpool pool){
        this.pool = pool;
    }

    @Override
    public void run() {
        while (true){
            try {
                Product item = this.pool.pop();
                System.out.println("消费者消费了一件商品: " + item.getName());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
