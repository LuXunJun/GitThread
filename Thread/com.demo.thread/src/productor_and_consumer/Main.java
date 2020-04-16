package productor_and_consumer;

import productor_and_consumer.QueuePool.Listpool;
import productor_and_consumer.Thread.Consumer;
import productor_and_consumer.Thread.Productor;

/*程序入口*/
public class Main {
    public static void main(String[] args) {
         Listpool pool = new Listpool(15);
         Productor productor = new Productor(pool);
         Consumer consumer = new Consumer(pool);

        productor.start();
        consumer.start();
    }
}
