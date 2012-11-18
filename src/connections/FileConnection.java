/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package connections;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

/**
 *
 * @author headyin
 */
public class FileConnection {
    
    public static final String JSON_FILE_PATH = "codejam.json";
    
    private File jsonFile;
    private FileOutputStream fileOutputStream;
    private OutputStreamWriter outputStreamWriter;
    private BufferedWriter bufferedWriter;
    
    public FileConnection() {
        try {
            this.jsonFile = new File(JSON_FILE_PATH);
            this.fileOutputStream = new FileOutputStream(this.jsonFile);
            this.outputStreamWriter = new  
                    OutputStreamWriter(this.fileOutputStream);
            this.bufferedWriter = new 
                    BufferedWriter(this.outputStreamWriter);
        } catch (IOException e1) {
            System.out.println("failed to create file");
        }
    }
    
    public void closeFile() {
        try {
            this.fileOutputStream.close();
        } catch (IOException e) {
            
        }
        
    }
    
    public BufferedWriter getBufferedWriter() {
        return this.bufferedWriter;
    }
}
