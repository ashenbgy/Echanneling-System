package com.echanneling.util;

public enum MassageUtils {

    REQUEST_SUCCESSFUL("Request is Success"),
    SUCCESSFULLY_CREATED("Successfully Created"),
    SUCCESSFULLY_UPDATED("Successfully Updated");

    private String massage;
    MassageUtils(String massage){
        this.massage=massage;
    }

    public String massage(){
        return massage;
    }

}
