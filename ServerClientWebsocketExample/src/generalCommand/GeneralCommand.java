package generalCommand;
import java.lang.reflect.*;
import java.io.IOException;

public class GeneralCommand {
    private String _className;
    private String _methodName;
    private String[] _paramTypes;
    private Object[] _paramValues;
    
    
     public GeneralCommand(String className, String methodName,
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
    
    public Object execute() {

        try {
            Class<?> receiver = Class.forName(_className); 
        	Class<?>[] paramTypes = getClasses();
            Method method = receiver.getMethod(_methodName, paramTypes);
            Object o = method.invoke(receiver.newInstance(), _paramValues);
            return o;
        }
        catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    } 
}
