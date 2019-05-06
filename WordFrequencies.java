package textprocessing;
import java.util.Map;
import java.util.HashMap;

public class WordFrequencies {
    private Map<String, Integer> map = new HashMap<String, Integer>();
    
    public synchronized void addFrequencies(Map<String,Integer> f){
        for (Map.Entry<String, Integer> entry : f.entrySet()) {
            if (map.containsKey(entry.getKey())) map.put(entry.getKey(), (map.get(entry.getKey()) + f.get(entry.getKey())));
            else map.put(entry.getKey(), f.get(entry.getKey()));
        }
    }
    
    public synchronized Map<String,Integer> getFrequencies(){
        return map;
    }
}