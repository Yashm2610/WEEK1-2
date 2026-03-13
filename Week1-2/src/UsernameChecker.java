import java.util.*;

public class UsernameChecker {

    HashMap<String,Integer> users = new HashMap<>();
    HashMap<String,Integer> attempts = new HashMap<>();

    public boolean checkAvailability(String username){

        attempts.put(username, attempts.getOrDefault(username,0)+1);
        return !users.containsKey(username);
    }

    public List<String> suggestAlternatives(String username){

        List<String> list = new ArrayList<>();

        for(int i=1;i<=3;i++)
            list.add(username+i);

        list.add(username.replace("_","."));

        return list;
    }

    public String getMostAttempted(){

        String ans="";
        int max=0;

        for(String u:attempts.keySet()){
            if(attempts.get(u)>max){
                max=attempts.get(u);
                ans=u;
            }
        }

        return ans;
    }

    public static void main(String[] args){

        UsernameChecker system = new UsernameChecker();

        System.out.println(system.checkAvailability("john_doe"));
        System.out.println(system.suggestAlternatives("john_doe"));
        System.out.println(system.getMostAttempted());
    }
}