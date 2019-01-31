package classes;

import com.google.gson.Gson;

public class Serializer {
    private static Serializer sr = new Serializer();
    public static Serializer getInstance() {
        if (sr == null){
            sr = new Serializer();
        }
        return sr;
    }
    Gson parser = new Gson();

    public String serializeObject(Object obj){
        return parser.toJson(obj);
    }

    public Command deserializeObject(String str){ return parser.fromJson(str, Command.class); }

    public String serializeResults(Results r){
        return parser.toJson(r);
    }

    public Results deserializeResults(String str){
        return parser.fromJson(str, Results.class);
    }

}
