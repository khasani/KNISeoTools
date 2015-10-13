package com.kniapps.seotools.service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

import org.json.JSONObject;

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
    
    public static int getPostion(String domain, String sKeyword,String sSearchEngine, String sOutputExactURL) 
    {
    
        final String USER_AGENT = "Mozilla/5.0";
        
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
        
                
        try {
            
            URL obj = new URL(sSearchURL);
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
            
            /*String inputLine;
            StringBuffer response = new StringBuffer();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();*/

            int i =0;

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return 1;
        
    }

}
