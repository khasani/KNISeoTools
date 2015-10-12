package com.kniapps.seotools.service;

//JSON Response (AJAX)
public class ResponseGetWebsiteDetails{
     
    boolean success = true;
    String message = "";
    String name ="";
    String url ="";
    String category ="";
    String keywords ="";
    String searchEngine ="";
    public boolean isSuccess() {
        return success;
    }
    public void setSuccess( boolean success ) {
        this.success = success;
    }
    public String getMessage() {
        return message;
    }
    public void setMessage( String message ) {
        this.message = message;
    }
    public String getName() {
        return name;
    }
    public void setName( String name ) {
        this.name = name;
    }
    public String getUrl() {
        return url;
    }
    public void setUrl( String url ) {
        this.url = url;
    }
    public String getCategory() {
        return category;
    }
    public void setCategory( String category ) {
        this.category = category;
    }
    public String getKeywords() {
        return keywords;
    }
    public void setKeywords( String keywords ) {
        this.keywords = keywords;
    }
    public String getSearchEngine() {
        return searchEngine;
    }
    public void setSearchEngine( String searchEngine ) {
        this.searchEngine = searchEngine;
    }
    
}

