package classes;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class Command {
    private String _className;
    private String _methodName;
    private String[] _paramTypes;
    private Object[] _paramValues;

    public Command(String className, String methodName,
                   String[] paramTypes, Object[] paramValues) {
        _className = className;
        _methodName = methodName;
        _paramTypes = paramTypes;
        _paramValues = paramValues;
    }

    public Results execute() {
        try {
            Class<?> receiver = Class.forName(_className);
            List<Class<?>> newParamTypes = new ArrayList<Class<?>>();
            for(String type : _paramTypes){
                newParamTypes.add(Class.forName(type));
            }

            Method method = receiver.getMethod(_methodName, newParamTypes.toArray(new Class<?>[newParamTypes.size()]));
            Object o = receiver.newInstance();
            Object returned = method.invoke(o, _paramValues);

            return new Results(true, returned, "");
        }
        catch (Exception e) {
            e.printStackTrace();
            return new Results(true, e.getMessage(), e.getMessage());
        }
    }
}
