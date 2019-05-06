package textprocessing;
import java.util.Queue;
import java.util.*;

public class FileContents {
    
    private Queue<String> queue = new LinkedList<String>();
    private int registerCount = 0;
    private boolean closed = false;
    private int maxFiles;
    private int maxChars;
    private int chars = 0;
    
    public FileContents(int maxFiles, int maxChars) {
        this.maxFiles = maxFiles;
        this.maxChars = maxChars;
    }
    
    public synchronized void registerWriter() {
        registerCount++;
    }
    
    public synchronized void unregisterWriter() {
        registerCount--;
        if (registerCount == 0) closed = true;
        notifyAll();
    }
    
    public synchronized void addContents(String contents) {
        if (contents != null) {
            while (((queue.size() >= maxFiles) || ((chars + contents.length()) >= maxChars)) && !queue.isEmpty()) {
                try {
                    wait();
                } catch (InterruptedException exc) {};  
            }
            queue.add(contents);
            chars += contents.length();
            notifyAll();
        }
    }
    
    public synchronized String getContents() {
        while (queue.isEmpty() && !closed) {
            try {
                wait();
            } catch (InterruptedException exc) {};
        }
        if (queue.isEmpty() && closed) {
            notifyAll();
            return null;
        }
        String content = queue.remove();
        chars -= content.length();
        notifyAll();
        return content;
    }
}