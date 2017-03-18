package com.benoxley.logtail;

import org.codehaus.jackson.*;
import org.codehaus.jackson.map.ObjectMapper;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.function.Consumer;

/**
 * Created by ben on 18/03/2017.
 */
public class UKHASNetSocket implements Runnable {

    private Consumer<Packet> handler = j-> System.out.println(j.getP());

    private Consumer<Exception> errorHandler = e->e.printStackTrace();
    private JsonParser jsonParser;


    @Override
    public void run() {
        start();
    }


    public void start(){
        try {
            Socket socket = new Socket("ukhas.net",3010);
            InputStream inputStream = socket.getInputStream();
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
            BufferedReader bis = new BufferedReader(inputStreamReader);

            final JsonFactory jsonFactory = new JsonFactory();
            try {
                this.jsonParser = jsonFactory.createJsonParser(bis);
            } catch (final IOException e) {
                errorHandler.accept(e);
            }
            parse(jsonParser);

        } catch (IOException e) {
            errorHandler.accept(e);
        }
    }


    private void parse(JsonParser parser) throws IOException {
        while (!Thread.currentThread().isInterrupted()) {
            parser.setCodec(new ObjectMapper());
            Packet packet = parser.readValueAs(Packet.class);
            handler.accept(packet);
        }
    }

    public Consumer<Packet> getHandler() {
        return handler;
    }

    public void setHandler(Consumer<Packet> handler) {
        this.handler = handler;
    }

    public Consumer<Exception> getErrorHandler() {
        return errorHandler;
    }

    public void setErrorHandler(Consumer<Exception> errorHandler) {
        this.errorHandler = errorHandler;
    }


}
