package productor_and_consumer.Thread;

import productor_and_consumer.QueuePool.Listpool;
import productor_and_consumer.Source.Product;

/*生产者线程*/
public class Productor extends Thread {

    private Listpool pool;

    public Productor(Listpool pool){
        this.pool = pool;
    }

    @Override
    public void run() {
        while (true){
            String name = (int)(Math.random() * 100 ) + "号产品" ;
            System.out.println("生产了一个产品: " + name);
            Product product = new Product(name);
            try {
                this.pool.push(product);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
