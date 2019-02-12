package com.bignerdranch.android.shared.resultobjects;

import com.bignerdranch.android.shared.Serializer;
import com.google.gson.Gson;

public class Results {
    private String type;
    private boolean success;
    private String JSONdata;
    
    public Results(String type, boolean success, Object JSONdata) {
        this.type = type;
        this.success = success;
        this.JSONdata = Serializer.getInstance().serializeObject(JSONdata);
    }

    public String getType() {
        return type;
    }

    public boolean isSuccess() {
        return success;
    }

    public String getJSONdata() {
    	return JSONdata;
    }
    
    //Object should be the data object specific to the type
    public Object getData(Class<?> classType) {
        Gson gson = new Gson();

    	Object dataObject = gson.fromJson(JSONdata, classType); //possibly return classType not Object.class
        return dataObject;
    }

    public void setData(Object JSONdata) {
        this.JSONdata = Serializer.getInstance().serializeObject(JSONdata);
    }
}
