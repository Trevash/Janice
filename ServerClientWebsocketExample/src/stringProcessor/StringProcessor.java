package stringProcessor;



public class StringProcessor implements IStringProcessor{
    @Override
    public String trim(String input) {
    return input.trim();
    }
    @Override
    public String toLower(String input) {
    return input.toLowerCase();
    }
    @Override
    public Object parseDouble(String input) {
    return (Object)Double.parseDouble(input);
    }

}

