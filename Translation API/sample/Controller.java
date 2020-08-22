package sample;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.*;

public class Controller implements Initializable , EventHandler<ActionEvent> {

    @FXML private TextField input;
    @FXML private TextField output;
    @FXML private Button Translate;
    @FXML private MenuItem Close;
    @FXML private MenuItem English;
    @FXML private MenuItem German;
    @FXML private MenuItem French;
    @FXML private MenuItem Spanish;
    String LangChoice;


    public void DoTranslation(ActionEvent event){
        if(event.getSource().equals(Translate)){
            try{
                String inp = input.getText();
                String TranslateOut = translate( "en", LangChoice , inp);
                output.setText(TranslateOut);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

        /*https://cloud.google.com/translate/docs/languages <- All the languages google cloud supports*/
    public String LanguageChoice(ActionEvent event){
            if (event.getSource().equals(English)) LangChoice = "en";
            if (event.getSource().equals(German)) LangChoice = "de";
            if (event.getSource().equals(French)) LangChoice = "fr";
            if (event.getSource().equals(Spanish)) LangChoice = "es";
            return LangChoice;
    }


    public void Exit(ActionEvent event){
        if(event.getSource().equals(Close)) System.exit(1);
    }


    private static String translate(String langFrom, String langTo, String inp) throws IOException {
        String urlStr = "Google Script Url Goes Here." +
                "?q=" + URLEncoder.encode(inp, "UTF-8") +
                "&target=" + langTo +
                "&source=" + langFrom;
        URL url = new URL(urlStr);
        StringBuilder response = new StringBuilder();
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestProperty("User-Agent", "Mozilla/5.0");
        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
        String inputLine;
        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();
        return response.toString();
    }

    @Override
    public void handle(ActionEvent event){
    }
    @Override
    public void initialize (URL loction , ResourceBundle resources){
    }
}
