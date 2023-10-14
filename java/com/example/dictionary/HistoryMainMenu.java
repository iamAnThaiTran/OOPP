package com.example.dictionary;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;
public class HistoryMainMenu {
    // Các nút Button đã được khởi tạo và gán giá trị trong constructor
    Button button1;
    Button button2;
    Button button3;
    Button button4;
    Button button5;
    Button button6;
    Button button7;
    Button button8;
    public SearchManagement search = new SearchManagement();
    private Stage secondaryStage;
    FXMLLoader loader;
    private HistorySearchMenu historySearchMenu;

    public HistoryMainMenu() {
    }

    // Phương thức loadHistory() không cần tạo một FXMLLoader mới
    public void loadHistoryMenu() {
        // Đã có các nút Button được khởi tạo và gán giá trị trong constructor
        // Không cần tạo mới FXMLLoader ở đây
        secondaryStage = new Stage();
        secondaryStage.setTitle("Lịch sử tra từ");
        try {
            loader = new FXMLLoader(getClass().getResource("historyMainMenu.fxml"));
            Parent root = loader.load();
            button1 = (Button) loader.getNamespace().get("word1");
            button2 = (Button) loader.getNamespace().get("word2");
            button3 = (Button) loader.getNamespace().get("word3");
            button4 = (Button) loader.getNamespace().get("word4");
            button5 = (Button) loader.getNamespace().get("word5");
            button6 = (Button) loader.getNamespace().get("word6");
            button7 = (Button) loader.getNamespace().get("word7");
            button8 = (Button) loader.getNamespace().get("word8");

            updateHistoryBar();
            Scene secondaryScene = new Scene(root, 800, 600);
            secondaryStage.setScene(secondaryScene);
            secondaryStage.show();

        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }
    public void updateHistoryBar() {
        button1.setText(search.searchedWord.get(0));
        button3.setText(search.searchedWord.get(1));
        button2.setText(search.searchedWord.get(2));
        button4.setText(search.searchedWord.get(3));
        button5.setText(search.searchedWord.get(4));
        button6.setText(search.searchedWord.get(5));
        button7.setText(search.searchedWord.get(6));
        button8.setText(search.searchedWord.get(7));
    }

    public void loadHistory() {
        button1.setOnAction(e->historySearchMenu.loadWebPage(button1.getText()));
        button2.setOnAction(e->historySearchMenu.loadWebPage(button2.getText()));
        button3.setOnAction(e->historySearchMenu.loadWebPage(button3.getText()));
        button4.setOnAction(e->historySearchMenu.loadWebPage(button4.getText()));
        button5.setOnAction(e->historySearchMenu.loadWebPage(button5.getText()));
        button6.setOnAction(e->historySearchMenu.loadWebPage(button6.getText()));
        button7.setOnAction(e->historySearchMenu.loadWebPage(button7.getText()));
        button8.setOnAction(e->historySearchMenu.loadWebPage(button8.getText()));
    }

}
