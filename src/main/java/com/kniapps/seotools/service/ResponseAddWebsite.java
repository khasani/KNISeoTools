package com.kniapps.seotools.service;

//JSON Response (AJAX)
public class ResponseAddWebsite{
     
    boolean success = true;
    String message = "";
    boolean nameError = false;
    boolean urlError = false;
    boolean keywordsError = false;
    boolean categoryError = false;
    boolean searchEngineError = false;
    
    public ResponseAddWebsite() {
    }

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

    public boolean isNameError() {
        return nameError;
    }

    public void setNameError( boolean nameError ) {
        this.nameError = nameError;
    }

    public boolean isUrlError() {
        return urlError;
    }

    public void setUrlError( boolean urlError ) {
        this.urlError = urlError;
    }

    public boolean isKeywordsError() {
        return keywordsError;
    }

    public void setKeywordsError( boolean keywordsError ) {
        this.keywordsError = keywordsError;
    }

    public boolean isCategoryError() {
        return categoryError;
    }

    public void setCategoryError( boolean categoryError ) {
        this.categoryError = categoryError;
    }

    public boolean isSearchEngineError() {
        return searchEngineError;
    }

    public void setSearchEngineError( boolean searchEngineError ) {
        this.searchEngineError = searchEngineError;
    }
    
}

