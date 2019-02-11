package com.bignerdranch.android.shared.resultobjects;

public class Results {
    private String type;
    private boolean success;
    private Object data;

    public Results(String type, boolean success, Object data) {
        this.type = type;
        this.success = success;
        this.data = data;
    }

    public String getType() {
        return type;
    }

    public boolean isSuccess() {
        return success;
    }

    //Object should be the data object specific to the type
    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
