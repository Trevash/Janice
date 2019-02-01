package com.bignerdranch.android.shared;
import java.util.*;
import java.lang.reflect.*;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class GenericCommand implements java.io.Serializable {
    private String _className;
    private String _methodName;
    private Class<?>[] _paramTypes;
    private Object[] _paramValues;
    
    
     public GenericCommand(String className, String methodName,
                          Class<?>[] paramTypes, Object[] paramValues) {
        _className = className;
        _methodName = methodName;
        _paramTypes = paramTypes;
        _paramValues = paramValues;
    }
    public String getMethod(){
        return _methodName;
    }
    public Object[] getParamValues(){
        return _paramValues;
    }
    private void readObject(ObjectInputStream input) 
                            throws IOException, ClassNotFoundException {
        input.defaultReadObject();
    }
    private void readObject(ObjectOutputStream output) 
                            throws IOException, ClassNotFoundException {
        output.defaultWriteObject();
    }
    
    
    
    
    
    public Object execute() {

        try {
            Class<?> receiver = Class.forName(_className);
            Method method = receiver.getMethod(_methodName, _paramTypes);
            Object o = method.invoke(null, _paramValues);
            return o;
        }
        catch (Exception e) {
            e.printStackTrace();
            return "null";
        }
    } 
}
