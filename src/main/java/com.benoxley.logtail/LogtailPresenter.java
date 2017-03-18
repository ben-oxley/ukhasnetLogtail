package com.benoxley.logtail;


import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import org.json.JSONObject;

import java.io.*;
import java.net.Socket;
import java.net.URL;
import java.nio.CharBuffer;
import java.util.ResourceBundle;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


/**
 * Created by ben on 17/03/2017.
 */
public class LogtailPresenter implements Initializable {

    @FXML
    private TextField log;

    @FXML
    private ListView<String> view;
        //3010 tcp port

    private ExecutorService executor = Executors.newSingleThreadExecutor();

    public void initialize(URL location, ResourceBundle resources) {

        UKHASNetSocket socket = new UKHASNetSocket();
        socket.setHandler(s->{

            Platform.runLater(()->view.getItems().add(0,s.toString()));
        });
        executor.execute(socket);


    }




}
