package generalCommand;
import java.lang.reflect.*;
import java.io.IOException;

import wsClient.EmptyClient;


public class GeneralCommand {
    private String className;
    private String methodName;
    private String[] paramTypes;
    private Object[] paramValues;
    
    
     public GeneralCommand(String className, String methodName,
                          String[] paramTypes, Object[] paramValues) {
        this.className = className;
        this.methodName = methodName;
        this.paramTypes = paramTypes;
        this.paramValues = paramValues;
    }
    
    public GeneralCommand() {//Constructor that takes in JSON formatted string?
    	
    }
    public String getMethod(){
        return this.methodName;
    }
    public Object getParamValues(){
        return this.paramValues;
    }

    
    
    
    
    
    public Object execute() {

        try {
            //Class<?> receiver = Class.forName(_className);            
            //Method method = receiver.getMethod(_methodName, paramTypes);
            //Object o = method.invoke(null, _paramValues);
            return null;
        }
        catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    } 
}
