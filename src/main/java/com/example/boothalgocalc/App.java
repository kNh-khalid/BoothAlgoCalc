package com.example.boothalgocalc;


import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class App extends Application {

    @Override
    public void start(Stage stage) {
        Label num1Label = new Label("Enter first num:");
        Label num2Label = new Label("Enter second num:");
        Label title = new Label("Booth's Algorithm Calculator");
        TextField num1Field = new TextField();
        TextField num2Field = new TextField();
        Button calculateButton = new Button("Calculate");
        TextArea resultArea = new TextArea();

        HBox inputBox = new HBox(10, num1Label, num1Field, num2Label, num2Field, calculateButton);
        HBox titleContainer = new HBox(title);
        VBox root = new VBox(30, titleContainer,inputBox, resultArea);

        resultArea.setEditable(false); //just for display cannot edit
        resultArea.setMaxWidth(300);
        titleContainer.setAlignment(Pos.CENTER);
        titleContainer.setMinHeight(50);
        inputBox.setAlignment(Pos.CENTER);
        root.setAlignment(Pos.CENTER);

        calculateButton.setOnAction(e -> {
            try {
                int num1 = Integer.parseInt(num1Field.getText());
                int num2 = Integer.parseInt(num2Field.getText());
                if (num1 == 0 || num2 == 0 || Math.abs(num1) > 7 || Math.abs(num2) > 7) {
                    resultArea.setText("Please enter numbers in the range -7 to 7\n (excluding 0).");
                } else {
                    String result = BoothAlgoCalc.boothMultiply(num1, num2);
                    resultArea.setText(result);
                }
            } catch (NumberFormatException ex) {
                resultArea.setText("Invalid input. Please enter valid integers.");
            }
        });

        String titleStyle = "-fx-font-size: 20;" +
                "-fx-font-weight: bold;" +
                "-fx-font-family: 'Comic Sans MS';" +
                "-fx-text-fill: gray;" +
                "-fx-background-color: skyblue";
        String inputStyle = "-fx-font-family: 'Comic Sans MS';" +
                "-fx-font-size: 12;";
        String outputStyle = "-fx-font-family: 'Monospace';" +
                "-fx-font-size: 13;";
        String buttonStyle = "-fx-font-family: 'Comic Sans MS';" +
                "-fx-font-size: 12;" +
                "-fx-background-radius: 5;";

        resultArea.setStyle(outputStyle);
        titleContainer.setStyle(titleStyle);
        inputBox.setStyle(inputStyle);
        calculateButton.setStyle(buttonStyle);

        Scene scene = new Scene(root, 700, 400);
        stage.setScene(scene);
        stage.setTitle("Booth's Algorithm Calculator");
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
