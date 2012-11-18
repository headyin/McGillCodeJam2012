/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package connections.data;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.net.URLEncoder;

/**
 *
 * @author headyin
 */
public class URLEncodedBufferedWriter {
    private BufferedWriter bw;
    public URLEncodedBufferedWriter(BufferedWriter bw) {
        this.bw = bw;
    }
        
    
    
    public void write(String str) throws IOException{
        String urlEncodedString;
        urlEncodedString = URLEncoder.encode(str,"UTF-8" );
        bw.write(urlEncodedString);
    }
    
    public void flush() throws IOException {
        this.bw.flush();
    }
}
