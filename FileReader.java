package textprocessing;
public class FileReader extends Thread {
    private FileNames fn;
    private FileContents fc;
    private boolean termina = false;
    
    public FileReader(FileNames fn, FileContents fc){
        this.fn = fn;
        this.fc = fc;
        fc.registerWriter();
    }
    
    public void run() {
        while (!termina) {
            try {
                if (fn.getName() != null) {
                    fc.addContents(Tools.getContents(fn.getName()));
                } else termina = true;
            } catch(Exception e) {}
        }
        fc.unregisterWriter();
    }
}