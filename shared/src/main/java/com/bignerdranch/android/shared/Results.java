package com.bignerdranch.android.shared;

/**
 * Created by coy on 1/14/2019.
 */

public class Results {
	private String type = "";
    private boolean success = false;
    private Object data = null;
    private String errorInfo = null;

    public Results(String newType, boolean newSuccess, Object newData, String newErrorInfo){
        type = newType;
    	success = newSuccess;
        data = newData;
        errorInfo = newErrorInfo;
    }

    public String getType() {
    	return type;
    }
    
    public boolean isSuccess() {
        return success;
    }
    
    public Object getData() {
        return data;
    }

    public String getErrorInfo() {
        return errorInfo;
    }
}
