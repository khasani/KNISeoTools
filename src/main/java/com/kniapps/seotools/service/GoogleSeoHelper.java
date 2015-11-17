package com.kniapps.seotools.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.kniapps.seotools.model.Keyword;

public class GoogleSeoHelper {
  
    public static int getPR(String domain) {

        String result = "";

        JenkinsHash jenkinsHash = new JenkinsHash();
        long hash = jenkinsHash.hash(("info:" + domain).getBytes());

        //Append a 6 in front of the hashing value.
        String url = "http://toolbarqueries.google.com/tbr?client=navclient-auto&hl=en&"
           + "ch=6" + hash + "&ie=UTF-8&oe=UTF-8&features=Rank&q=info:" + domain;

        System.out.println("Sending request to : " + url);

        try {
            URLConnection conn = new URL(url).openConnection();

            BufferedReader br = new BufferedReader(new InputStreamReader(
                conn.getInputStream()));

            String input;
            while ((input = br.readLine()) != null) {

                // What Google returned? Example : Rank_1:1:9, PR = 9
                System.out.println(input);

                result = input.substring(input.lastIndexOf(":") + 1);
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        if ("".equals(result)) {
            return 0;
        } else {
            return Integer.valueOf(result);
        }

      }
    
    public static int getIndexedPages(String domain) {
        
        // ! Depreciated API
        String url = "http://ajax.googleapis.com/ajax/services/search/web?v=1.0&q=" + domain;
        int iIndexedPages = -1;
                
        try {
            // Get the JSON response from Google
            URLConnection conn = new URL(url).openConnection();
            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String sResult = org.apache.commons.io.IOUtils.toString(br);
 
            // Read the JSON Object
            JSONObject result_json = new JSONObject(sResult);
            
            // Search the best position
            iIndexedPages = result_json.getJSONObject("responseData").getJSONObject("cursor").getInt("estimatedResultCount");


        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return iIndexedPages;
        
    }
    
    public static int getPostion(String domain, String sKeyword,String sSearchEngine, StringBuilder sOutputExactURL)  // Return parameter : sOutputExactURL
    {
    
        final String USER_AGENT = "Mozilla/5.0";
        int iPos = 0;
        
        // Config Query URL
        String sSearchURL = "https://www." + sSearchEngine + "/search?";
        
        switch(sSearchEngine){
        
            case "google.fr" : sSearchURL += "hl=fr&";
            break;
            
            case "google.co.uk" : sSearchURL += "hl=en&";
            break;
            
            case "google.ca" : sSearchURL += "hl=fr&";
            break;
            
            case "google.com" : sSearchURL += "hl=en&";
            break;
            
            case "google.ch" : sSearchURL += "hl=fr&";
            break;
            
            case "google.es" : sSearchURL += "hl=es&";
            break;
            
            case "google.it" : sSearchURL += "hl=it&";
            break;
            
            case "google.de" : sSearchURL += "hl=de&meta=lr%3Dlang_de&";
            break;
            
            case "google.pl" : sSearchURL += "hl=pl&meta=lr%3Dlang_pl&";
            break;
            
            default:
            break;

        }
        
        // Adding keyword to the query (completed)
        sSearchURL += "q=" + sKeyword;
        String sCurrentSearchURL = sSearchURL;
        
        
        try {
            
            
            // Browse the 10 first pages of Google
            for(int i=0 ; i<10 ; i++)    
            {            
                
                //String
                URL obj = new URL(sCurrentSearchURL);
                HttpURLConnection con = (HttpURLConnection) obj.openConnection();
    
                // optional default is GET
                con.setRequestMethod("GET");
    
                //add request header
                con.setRequestProperty("User-Agent", USER_AGENT);
    
                BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
                //String sResult = org.apache.commons.io.IOUtils.toString(br);
                
                String inputLine;
                StringBuffer response = new StringBuffer();
    
                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                in.close();
    
                //print result
                System.out.println(response.toString());
      
            
                // Get all the results in the current page
                Document doc = Jsoup.parse(response.toString());
                Elements results_a = doc.select("h3.r > a");
                
                // Get Position and URL
                iPos = getURLFounded(domain, results_a, sOutputExactURL);
                
                if (iPos > 0)
                {
                    iPos = (i)*10 + iPos;
                    
                    break;
                }
                
                // Pause
                Thread.sleep(2000 + (int)(Math.random()*100));
                
                // Prepare next request
                sCurrentSearchURL = sSearchURL + "&start=" + String.valueOf(i-1) + "0";
            }
            

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return iPos;
        
    }
    
    
    private static int getURLFounded(String domain,Elements results_a, StringBuilder sOutputExactURL)
    {
        
        int iPos = 0;
        String sURL_found = "";
        for (int i=0 ; i < results_a.size() ; i++) {
            
            // Get the URL on the local search result             
            sURL_found = results_a.get(i).attr("href");
            
            // Search if the website is present in the url
            if(sURL_found.contains(domain))
            {
                // Position
                iPos = i+1;
                
                // Exact URL
                sURL_found = sURL_found.substring(sURL_found.indexOf("http"), sURL_found.indexOf("&"));
                
                // Return StringBuilder
                sOutputExactURL.append(sURL_found);
                
                break;
            }     
        }
        
        return iPos;
        
    }

}
