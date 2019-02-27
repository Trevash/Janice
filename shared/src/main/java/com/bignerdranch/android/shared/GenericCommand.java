package com.bignerdranch.android.shared;
import java.lang.reflect.*;

import com.bignerdranch.android.shared.resultobjects.Results;
import com.google.gson.Gson;

public class GenericCommand {
    private String _className;
    private String _methodName;
    private String[] _paramTypes;
    private Object[] _paramValues;

    public GenericCommand(String className, String methodName,
                          String[] paramTypes, Object[] paramValues) {
        _className = className;
        _methodName = methodName;
        _paramValues = paramValues;
        _paramTypes = paramTypes;
    }

    public String getMethod(){
        return _methodName;
    }
    public Object[] getParamValues(){
        return _paramValues;
    }
    public void setParamValues(Object[] paramValues) {
        this._paramValues = paramValues;
    }


    private Class<?>[] getClasses() throws ClassNotFoundException {
        try {
            Class<?>[] paramTypes = new Class<?>[_paramTypes.length];
            for (int i = 0; i<paramTypes.length; i++) {
                paramTypes[i] = Class.forName(_paramTypes[i]);
            }
            return paramTypes;
        } catch(ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    private Object getData(Class<?> className, String data) {
        Gson parser = new Gson();
        return parser.fromJson(data, className);
    }

    public Results execute() {

        try {
            Class<?> receiver = Class.forName(_className);
            Class<?>[] paramTypes = getClasses();
            for (int i = 0; i < _paramValues.length; i++) {
                _paramValues[i] = getData(paramTypes[i], (String) _paramValues[i]);
            }
            Method method = receiver.getMethod(_methodName, paramTypes);
            return (Results) method.invoke(receiver.newInstance(), _paramValues);
        }
        catch (InvocationTargetException i) {
            i.printStackTrace();
            return new Results("ERROR", false, i.getTargetException().getMessage());
        }
        catch (Exception e) {
            e.printStackTrace();
            return new Results("ERROR", false, e.getMessage());
        }
    }
}