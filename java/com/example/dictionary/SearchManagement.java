package com.example.dictionary;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class SearchManagement {
    FXMLLoader loader;
    private TextField textField;
    private WebView webView;
    private WebEngine webEngine;
    private Stage secondaryStage;
    protected ArrayList<String> searchedWord;
    String ENGVIE_directory = "E:\\Coding\\Dictionary\\src\\main\\resources\\com\\example\\dictionary\\eng_vie.txt";
    public void EnglishTranslation() {
        secondaryStage = new Stage();
        secondaryStage.setTitle("Tra từ Tiếng Anh");
        loader = new FXMLLoader(getClass().getResource("hello-view.fxml"));
        try  {
            Parent root = loader.load();
            textField = (TextField) loader.getNamespace().get("SearchTaskbar");
            Button searchButton = (Button) loader.getNamespace().get("searchButton");
            searchButton.setOnAction(e ->loadWebPage());
            webView = (WebView) loader.getNamespace().get("translation");
            webEngine = webView.getEngine();
            Scene secondaryScene = new Scene(root, 800, 600);
            secondaryStage.setScene(secondaryScene);
            secondaryStage.show();

        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }
    /*private void VietNamTranslation() {
        secondaryStage = new Stage();
        secondaryStage.setTitle("Cửa sổ thứ hai");
        loader = new FXMLLoader(getClass().getResource("hello-view.fxml"));
        try  {
            Parent root = loader.load();
            textField = (TextField) loader.getNamespace().get("SearchTaskbar");
            Button searchButton = (Button) loader.getNamespace().get("searchButton");
            searchButton.setOnAction(e ->loadWebPage());
            webView = (WebView) loader.getNamespace().get("translation");
            webEngine = webView.getEngine();
            Scene secondaryScene = new Scene(root, 800, 600);
            secondaryStage.setScene(secondaryScene);
            secondaryStage.show();

        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }*/

    private void loadWebPage() {
        String searchQuery = textField.getText();
        try (BufferedReader reader = new BufferedReader(new FileReader(ENGVIE_directory))) {
            String line;
            while ((line = reader.readLine()) != null) {
                // Load nội dung HTML vào WebView
                String[] WORD = line.split("<");
                if (searchQuery.equals(WORD[0]))
                {
                    webEngine.loadContent(line);
                    break;
                }
            }
        } catch (IOException e) {
            System.err.println("Đã xảy ra lỗi khi đọc tệp: " + e.getMessage());
        }
    }
    void addHistory() {
        searchedWord.add(textField.getText());
    }
}
