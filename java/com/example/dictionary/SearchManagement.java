package com.example.dictionary;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import org.controlsfx.control.textfield.AutoCompletionBinding;
import org.controlsfx.control.textfield.TextFields;
import javafx.util.Callback;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class SearchManagement {
    private FXMLLoader loader;
    private TextField textField;
    private WebView webView;
    private WebEngine webEngine;
    private Stage secondaryStage;
    private List<String> suggestionList;
    protected ArrayList<String> searchedWord;
    protected String ENGVIE_directory = "E:\\Coding\\Dictionary\\src\\main\\resources\\com\\example\\dictionary\\eng_vie.txt";

    public SearchManagement() {
        suggestionList = new ArrayList<>();
    }

    public void EnglishTranslation() {
        secondaryStage = new Stage();
        secondaryStage.setTitle("Tra từ Tiếng Anh");
        loader = new FXMLLoader(getClass().getResource("searchManagement.fxml"));
        try {
            Parent root = loader.load();
            textField = (TextField) loader.getNamespace().get("SearchTaskbar");
            Button searchButton = (Button) loader.getNamespace().get("searchButton");
            searchButton.setOnAction(e -> loadWebPage());
            webView = (WebView) loader.getNamespace().get("translation");
            webEngine = webView.getEngine();

            AutoCompletionBinding<String> auto = TextFields.bindAutoCompletion(textField, new Callback<AutoCompletionBinding.ISuggestionRequest, Collection<String>>() {
                @Override
                public Collection<String> call(AutoCompletionBinding.ISuggestionRequest iSuggestionRequest) {
                    String userText = iSuggestionRequest.getUserText().toLowerCase();
                    List<String> suggestions = suggestionList.stream()
                            .filter(word -> word.toLowerCase().startsWith(userText))
                            .collect(Collectors.toList());

                    return suggestions;
                }
            });

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
                String[] WORD = line.split("<");
                if (WORD[0].startsWith(searchQuery)) {
                    suggestionList.add(WORD[0]);
                }
                if (searchQuery.equals(WORD[0])) {
                    webEngine.loadContent(line);
                    searchedWord.add(WORD[0]);
                }
            }
        } catch (IOException e) {
            System.err.println("Đã xảy ra lỗi khi đọc tệp: " + e.getMessage());
        }
        suggestionList = new ArrayList<>(new LinkedHashSet<>(suggestionList));
    }
}
