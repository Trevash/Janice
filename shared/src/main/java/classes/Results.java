package classes;

/**
 * Created by coy on 1/14/2019.
 */

public class Results {
    public boolean success = false;
    public Object data = null;
    public String errorInfo = null;

    public Results(boolean newSuccess, Object newData, String newErrorInfo){
        success = newSuccess;
        data = newData;
        errorInfo = newErrorInfo;
    }
}
