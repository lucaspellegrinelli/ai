package lucaspellegrinelli.ai.neuralnetwork.util;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.logging.Level;
import java.util.logging.Logger;

public class FileManager {
    public static void saveFile(String filePath, String content){       
        try (PrintWriter out = new PrintWriter(filePath)) {
            out.println(content);
        } catch (IOException e) {
            Logger.getLogger(FileManager.class.getName()).log(Level.SEVERE, null, e);
        }
    }
    
    public static String readFile(String filePath){
        String text = "";
        try {
            text = new String(Files.readAllBytes(Paths.get(filePath)));
        } catch (IOException ex) {
            Logger.getLogger(FileManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return text;
    }
}
