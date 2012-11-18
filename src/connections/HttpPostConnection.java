/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package connections;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 *
 * @author headyin
 */
public class HttpPostConnection {
    
    private static final String URL_ADDRESS = 
            "https://stage-api.e-signlive.com/aws/rest/services/codejam";
    private static final String Authorization = 
            "Y29kZWphbTpBRkxpdGw0TEEyQWQx";
    
    private URL url;
    private HttpURLConnection connection;
    private BufferedReader bufferedReader;
    private InputStreamReader inputStreamReader;
    private OutputStreamWriter outputStreamWriter;
    private BufferedWriter bufferedWriter;
    /**
     *
     * @param url
     */
    public HttpPostConnection() {
        try {
            this.url = new URL(URL_ADDRESS);
            this.connection = (HttpURLConnection) this.url.openConnection();
            connection.setRequestMethod("POST");
            connection.setDoOutput(true);
            connection.setDoInput(true);
            connection.setRequestProperty("Authorization", 
                    "Basic" + Authorization);
            connection.connect();            
            this.outputStreamWriter = new 
                    OutputStreamWriter(connection.getOutputStream());
            this.inputStreamReader = new
                    InputStreamReader(connection.getInputStream());
            this.bufferedReader = new 
                    BufferedReader(this.inputStreamReader);
            this.bufferedWriter = new
                    BufferedWriter(this.outputStreamWriter);           
        } catch (IOException e){
            
        }  
    }
    
    public BufferedReader getBufferedReader() {
        return this.bufferedReader;
    }
    
    public BufferedWriter getBufferedWriter() {
        return this.bufferedWriter;
    }

    public void disconnect() {
        this.connection.disconnect();
    }


}
