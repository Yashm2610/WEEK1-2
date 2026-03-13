import java.util.*;

public class Plagariasmdetection {

    HashMap<String, Set<String>> map = new HashMap<>();

    public void addDocument(String docId, String text){

        String[] words = text.split(" ");

        for(int i=0;i<words.length-4;i++){

            String gram = words[i]+" "+words[i+1]+" "+words[i+2]+" "+words[i+3]+" "+words[i+4];

            map.putIfAbsent(gram,new HashSet<>());
            map.get(gram).add(docId);
        }
    }

    public int checkSimilarity(String text){

        String[] words = text.split(" ");
        int matches = 0;

        for(int i=0;i<words.length-4;i++){

            String gram = words[i]+" "+words[i+1]+" "+words[i+2]+" "+words[i+3]+" "+words[i+4];

            if(map.containsKey(gram))
                matches++;
        }

        return matches;
    }

    public static void main(String[] args){

        Plagariasmdetection detector = new Plagariasmdetection();

        detector.addDocument("essay1",
                "machine learning is a powerful technology for data analysis");

        detector.addDocument("essay2",
                "deep learning is a powerful technology used in artificial intelligence");

        int result = detector.checkSimilarity(
                "machine learning is a powerful technology");

        System.out.println("Matching n-grams: " + result);
    }
}