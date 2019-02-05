package com.bignerdranch.android.shared;
import java.lang.reflect.*;

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

    public Results execute() {

        try {
            Class<?> receiver = Class.forName(_className);
            Class<?>[] paramTypes = getClasses();
            Method method = receiver.getMethod(_methodName, paramTypes);
            return (Results) method.invoke(receiver.newInstance(), _paramValues);
        }
        catch (InvocationTargetException i) {
            return new Results(false, "", i.getTargetException().getMessage());
        }
        catch (Exception e) {
            e.printStackTrace();
            return new Results(false, "", e.getMessage());
        }
    }
}