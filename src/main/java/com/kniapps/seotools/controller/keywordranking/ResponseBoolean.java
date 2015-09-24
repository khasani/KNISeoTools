package com.kniapps.seotools.controller.keywordranking;

//JSON Response (AJAX)
public class ResponseBoolean {

    boolean success = true;
    String message = "";
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

}
