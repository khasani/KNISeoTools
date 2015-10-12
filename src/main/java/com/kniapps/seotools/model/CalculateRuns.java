package com.kniapps.seotools.model;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import org.json.JSONObject;

public class CalculateRuns {

    private CalculateRuns() {
        // TODO Auto-generated constructor stub
    }
    
    
    public static int calculatePosition(String sKeyword, String sUrl, SearchEngine searchEngine, String sReturnFindedUrl)
    {
        int iReturn = 0;
                
        
        iReturn = calculatePositionGoogle(sKeyword, sUrl, "google.fr", sReturnFindedUrl);
                
                
        return iReturn;
    }
        
    private static int calculatePositionGoogle(String sKeyword, String sUrl, String sSearchEngine, String sReturnFindedUrl)
    {
        
        int iReturn = 0;
             
        try {
            
            // URL Building
            URL url;
            url = new URL(
                    "https://ajax.googleapis.com/ajax/services/search/web?v=1.0&"
                    + "q=Paris%20Hilton&userip=USERS-IP-ADDRESS");
            
            URLConnection connection = url.openConnection();
            connection.addRequestProperty("Referer", sUrl);
            
            // Get the response
            String line;
            StringBuilder builder = new StringBuilder();
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            while((line = reader.readLine()) != null) {
             builder.append(line);
            }
            
            // Convert the response in JSON format
            JSONObject json = new JSONObject(builder.toString());
            
        } catch ( MalformedURLException e ) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            
        } catch ( IOException e ) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        

            
        
        
        return iReturn;
    }

}
