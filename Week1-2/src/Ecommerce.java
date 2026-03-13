import java.util.*;

public class Ecommerce {

    HashMap<String,Integer> stock = new HashMap<>();
    HashMap<String,Queue<Integer>> waitingList = new HashMap<>();

    public Ecommerce(){
        // initial stock
        stock.put("IPHONE15_256GB",100);
    }

    public int checkStock(String product){
        return stock.getOrDefault(product,0);
    }

    public synchronized String purchaseItem(String product,int userId){

        int count = stock.getOrDefault(product,0);

        if(count>0){
            stock.put(product,count-1);
            return "Success, remaining stock: "+(count-1);
        }

        waitingList.putIfAbsent(product,new LinkedList<>());
        waitingList.get(product).add(userId);

        return "Added to waiting list. Position: "+waitingList.get(product).size();
    }

    public static void main(String[] args){

        Ecommerce system = new Ecommerce();

        System.out.println("Stock: "+system.checkStock("IPHONE15_256GB"));

        System.out.println(system.purchaseItem("IPHONE15_256GB",12345));
        System.out.println(system.purchaseItem("IPHONE15_256GB",67890));

        // simulate stock finished
        for(int i=0;i<100;i++){
            system.purchaseItem("IPHONE15_256GB",i);
        }

        System.out.println(system.purchaseItem("IPHONE15_256GB",99999));
    }
}