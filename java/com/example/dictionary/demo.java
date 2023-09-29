package com.example.dictionary;

import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import javafx.fxml.FXMLLoader;
import java.lang.AutoCloseable;

public class demo extends Application {

    private Stage primaryStage;
    private Stage secondaryStage;
    private TextField textField;
    private WebView webView;
    private WebEngine webEngine;
    String ENGVIE_directory = "E:\\Coding\\demo\\src\\main\\resources\\com\\example\\demo\\dictionary.txt";

    public void start(Stage primaryStage) throws Exception {
        this.primaryStage = primaryStage;
        primaryStage.setTitle("Cửa sổ chính");

        Button okButton = new Button("OK");
        okButton.setOnAction(e -> openSecondaryWindow());

        StackPane primaryLayout = new StackPane();
        primaryLayout.getChildren().addAll(okButton);
        Scene primaryScene = new Scene(primaryLayout, 300, 200);
        primaryStage.setScene(primaryScene);

        primaryStage.show();
    }

    private void openSecondaryWindow() {
        secondaryStage = new Stage();
        secondaryStage.setTitle("Cửa sổ thứ hai");

        // Load file FXML
        FXMLLoader loader = new FXMLLoader(getClass().getResource("hello-view.fxml"));

        // Lấy controller của FXML (nếu cần)
        try  {
            Parent root = loader.load();
            textField = (TextField) loader.getNamespace().get("SearchTaskbar");
            //Button searchButton = new Button("Tìm kiếm");
            Button searchButton = (Button) loader.getNamespace().get("searchButton");
            searchButton.setOnAction(e ->loadWebPage());
            webView = (WebView) loader.getNamespace().get("translation");
            webEngine = webView.getEngine();
            //VBox secondaryLayout = new VBox(10);
            //secondaryLayout.getChildren().addAll(textField, searchButton, webView);
            Scene secondaryScene = new Scene(root, 800, 600);
            secondaryStage.setScene(secondaryScene);
            secondaryStage.show();

        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

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
}
