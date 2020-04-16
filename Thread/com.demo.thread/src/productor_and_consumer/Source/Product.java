package productor_and_consumer.Source;

/*消费的资源*/
public class Product {
    private String name;

    public Product(String name){
        this.name = name;
    }

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }
}
