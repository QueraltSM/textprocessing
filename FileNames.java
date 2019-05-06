package textprocessing;
import java.util.Queue;
import java.util.*;

public class FileNames {
    
    private Queue<String> queue = new LinkedList<String>();
    private boolean noMore = false;
    
    public synchronized void addName(String fileName) {
        queue.add(fileName);
        notifyAll();
    }
    
    public synchronized String getName() {
        while (queue.isEmpty() && !noMore) {
            try {
                wait();
            } catch (InterruptedException exc) {};
        } 
        if (queue.isEmpty() && noMore) return null;
        return queue.remove();
    }
    
    public synchronized void noMoreNames() {
        noMore = true;
        notifyAll();
    }
}