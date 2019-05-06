package textprocessing;
import java.util.Map;
import java.util.HashMap;

public class FileProcessor extends Thread {
    private FileContents fc;
    private WordFrequencies wf;
    private boolean termina = false;
    
    public FileProcessor(FileContents fc, WordFrequencies wf){
        this.fc = fc;
        this.wf = wf;
    }
    
    public void run() {
        while (!termina) {
            Map<String, Integer> map = new HashMap<String, Integer>();
            String delimiters = "[\\p{Space}\\p{Blank}\\p{Punct}\\p{Cntrl}]+";
            String content = fc.getContents();
            if (content != null) {
                String [] delimitedContent = content.split(delimiters);
                int count = 0;
                for (int i = 0 ; i<delimitedContent.length; i++) {
                    for (int j = 0; j<delimitedContent.length; j++) {
                        if (delimitedContent[i].equals(delimitedContent[j])) count++;
                    }
                    map.put(delimitedContent[i], count);
                    count = 0;
                }
                wf.addFrequencies(map);
            } else termina = true;
        }
    }
}