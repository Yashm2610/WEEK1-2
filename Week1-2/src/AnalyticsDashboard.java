import java.util.*;

public class AnalyticsDashboard {

    HashMap<String,Integer> pageViews = new HashMap<>();
    HashMap<String,Set<String>> uniqueVisitors = new HashMap<>();
    HashMap<String,Integer> sources = new HashMap<>();

    public void processEvent(String url,String user,String source){

        pageViews.put(url,pageViews.getOrDefault(url,0)+1);

        uniqueVisitors.putIfAbsent(url,new HashSet<>());
        uniqueVisitors.get(url).add(user);

        sources.put(source,sources.getOrDefault(source,0)+1);
    }

    public void getDashboard(){

        System.out.println("Top Pages:");

        for(String page:pageViews.keySet())
            System.out.println(page+" "+pageViews.get(page));

        System.out.println("\nTraffic Sources:");

        for(String s:sources.keySet())
            System.out.println(s+" "+sources.get(s));
    }

    public static void main(String[] args){

        AnalyticsDashboard analytics = new AnalyticsDashboard();

        analytics.processEvent("/article/breaking-news","user123","google");
        analytics.processEvent("/article/breaking-news","user456","facebook");
        analytics.processEvent("/sports/championship","user123","direct");

        analytics.getDashboard();
    }
}