package productor_and_consumer.QueuePool;

import productor_and_consumer.Source.Product;

import java.util.LinkedList;
import java.util.List;

/*队列池*/
public class Listpool {
    private List<Product> productList;
    private int maxSize = 0;

    public Listpool(int maxSize){
         this.productList = new LinkedList<Product>();
         this.maxSize = maxSize;
    }

    //生产者线程将数据压入到队列中 并且是线程安全
    public synchronized void push(Product item) throws InterruptedException {
        if(this.productList.size() == this.maxSize){
            // [生产线程] 等待
            this.wait();
        }
        this.productList.add(item);
        // 通知[消费线程]，你可以来消费了
        this.notify();
    }

    //消费者线程用于获取数据
    public synchronized Product pop() throws InterruptedException {
        if(this.productList.size() == 0){
            // [消费线程] 等待
            this.wait();
        }
        Product item = this.productList.remove(0);
        // 通知[生产线程]可以继续生产了,我已经消费了
        this.notify();
        return item;
    }

}
