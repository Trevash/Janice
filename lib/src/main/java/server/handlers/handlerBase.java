package server.handlers;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.lang.reflect.InvocationTargetException;
import java.net.HttpURLConnection;

public class handlerBase implements HttpHandler {

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        try{
            InputStream reqBody = exchange.getRequestBody();
            String reqData = readString(reqBody);

            String respData = execute(reqData);

            exchange.sendResponseHeaders(HttpURLConnection.HTTP_OK, 0);

            OutputStream respBody = exchange.getResponseBody();
            writeString(respData, respBody);
            respBody.close();
        }
        catch (IOException e) {
            exchange.sendResponseHeaders(HttpURLConnection.HTTP_BAD_REQUEST, 0);
            e.printStackTrace();
        }
    }

    private String readString(InputStream is) throws IOException {
        StringBuilder sb = new StringBuilder();
        InputStreamReader sr = new InputStreamReader(is);
        char[] buf = new char[1024];
        int len;
        while ((len = sr.read(buf)) > 0) {
            sb.append(buf, 0, len);
        }
        return sb.toString();
    }

    private void writeString(String str, OutputStream os) throws IOException {
        OutputStreamWriter sw = new OutputStreamWriter(os);
        sw.write(str);
        sw.flush();
    }

    public String execute(String s){
        return s;
    }
}
