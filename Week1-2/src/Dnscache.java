import java.util.*;

class DNSEntry {

    String ip;
    long expiry;

    DNSEntry(String ip,long ttl){
        this.ip = ip;
        this.expiry = System.currentTimeMillis() + ttl * 1000;
    }
}

public class Dnscache {

    HashMap<String,DNSEntry> cache = new HashMap<>();

    public String resolve(String domain){

        if(cache.containsKey(domain)){

            DNSEntry entry = cache.get(domain);

            if(System.currentTimeMillis() < entry.expiry)
                return entry.ip;
        }

        // simulate DNS lookup
        String newIP = "192.168.1." + new Random().nextInt(255);

        cache.put(domain,new DNSEntry(newIP,300));

        return newIP;
    }

    public static void main(String[] args){

        Dnscache dns = new Dnscache();

        System.out.println(dns.resolve("google.com"));
        System.out.println(dns.resolve("google.com")); // cache hit
    }
}