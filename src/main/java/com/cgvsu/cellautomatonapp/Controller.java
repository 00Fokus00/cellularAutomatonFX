package com.cgvsu.cellautomatonapp;

import com.cgvsu.cellautomaton.WorldPanel;
import com.cgvsu.cellautomaton.LifeWorld;
import com.cgvsu.cellautomaton.WolframWorld;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.util.Duration;

import java.util.Arrays;
import java.util.List;

import javafx.scene.canvas.GraphicsContext;

public class Controller {
    public TextField area;
    public TextField chance;
    public Button buttonStopLife;
    public Button buttonCleanLife;
    public TextField wolfram;
    public Button buttonStartWolfram;
    public TextField TextCellSize;
    @FXML
    private Button buttonStartLife;
    @FXML
    private TextField survivor;
    @FXML
    private TextField born;
    @FXML
    private TextField width;
    @FXML
    private TextField height;
    @FXML
    private Canvas canvas;

    private Timeline timeline;

    private WorldPanel worldPanel;

    @FXML
    protected void startGeneration(){
        if (timeline != null && timeline.getStatus() == Animation.Status.RUNNING) {
            timeline.stop();
        }
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());

        String bornText = this.born.getText();
        List<Integer> listBorn = Arrays.stream(bornText.split(""))
                .map(s -> {
                    try {
                        return Integer.parseInt(s);
                    } catch (NumberFormatException e) {
                        System.out.println("Некорректный элемент: " + s);
                        return null;
                    }
                })
                .filter(num -> num != null)
                .toList();

        String survivorText = this.survivor.getText();
        List<Integer> listSurvivor = Arrays.stream(survivorText.split(""))
                .map(s -> {
                    try {
                        return Integer.parseInt(s);
                    } catch (NumberFormatException e) {
                        System.out.println("Некорректный элемент: " + s);
                        return null;
                    }
                })
                .filter(num -> num != null)
                .toList();


        int w = Integer.parseInt(this.width.getText());
        int h = Integer.parseInt(this.height.getText());

        int area = Integer.parseInt(this.area.getText());
        double chance = Double.parseDouble(this.chance.getText());

        LifeWorld lifeWorld = new LifeWorld(w, h);
        worldPanel = new WorldPanel(lifeWorld);
        worldPanel.cellSize = Integer.parseInt(TextCellSize.getText());
        lifeWorld.randomizeFilling(w / 2, h / 2, area, area, chance);

        timeline = new Timeline(new KeyFrame(Duration.seconds(0.15), event -> {
            gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
            lifeWorld.updateWorld(listBorn, listSurvivor);
            worldPanel.drawCelluralWorld(gc, canvas.getWidth(), canvas.getHeight());
        }));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }

    public void pauseLife(ActionEvent actionEvent) {
        if (timeline != null && timeline.getStatus() == Animation.Status.RUNNING) {
            timeline.pause();
        }
        else if (timeline != null && timeline.getStatus() == Animation.Status.PAUSED){
            timeline.play();
        }
    }

    public void cleanLife(ActionEvent actionEvent) {
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
    }

    public void startWolfram(ActionEvent actionEvent) {
        if (timeline != null && timeline.getStatus() == Animation.Status.RUNNING) {
            timeline.stop();
        }
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());

        String wolframStr = this.wolfram.getText();
        List<Integer> wolframList = Arrays.stream(wolframStr.split(""))
                .map(s -> {
                    try {
                        return Integer.parseInt(s);
                    } catch (NumberFormatException e) {
                        System.out.println("Некорректный элемент: " + s);
                        return null;
                    }
                })
                .filter(num -> num != null)
                .toList();


        int w = Integer.parseInt(this.width.getText());
        int h = Integer.parseInt(this.height.getText());

        int area = Integer.parseInt(this.area.getText());
        double chance = Double.parseDouble(this.chance.getText());

        WolframWorld wolframAutomaton = new WolframWorld(w, h);
        worldPanel = new WorldPanel(wolframAutomaton);
        worldPanel.cellSize = Integer.parseInt(TextCellSize.getText());

        timeline = new Timeline(new KeyFrame(Duration.seconds(0.01), event -> {
            gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
            worldPanel.drawCelluralWorld(gc, canvas.getWidth(), canvas.getHeight());
            wolframAutomaton.updateWorld(wolframList);
        }));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }

    public void celleSize(ActionEvent actionEvent) {
        worldPanel.cellSize = Integer.parseInt(TextCellSize.getText());
    }
}