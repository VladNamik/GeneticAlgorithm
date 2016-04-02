package ui;

import algorithm.*;
import algorithm.weight.function.WeightByDistance;
import javafx.application.Platform;
import javafx.beans.property.DoubleProperty;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Polygon;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

/**
 * Контроллер для основного окна
 */
public class MainWindowController {
    @FXML
    private Pane graphicWindow;

    @FXML
    private Button stopButton;
    @FXML
    private Button startButton;
    @FXML
    private TextField randomQuantity;


    private GeneticAlgorithm algorithm;
    private static AlgorithmStartParameters algorithmStartParameters = new AlgorithmStartParameters();
    private List<Circle> dots = new LinkedList<>();





    public MainWindowController() {
    }

    @FXML
    public void initialize()
    {
        graphicWindow.setOnMouseClicked(event ->
        {
            addNewDot(event.getX(), event.getY());
        });
        randomQuantity.setText("20");
    }


    private void addNewDot(double x, double y)
    {
        Circle circle = new Circle(x, y, 2);//x, y, radius точки
        dots.add(circle);
        graphicWindow.getChildren().add(circle);
    }

    private void addLine(double x1, double y1, double x2, double y2)
    {
        graphicWindow.getChildren().add(new Line(x1, y1, x2, y2));
    }

    @FXML
    public void onRandomButton()
    {
        Random random = RandomNumber.random;
        onClearAllButton();
        try {
            for (int i = 0; i < Integer.parseInt(randomQuantity.getText()); i++) {
                addNewDot(random.nextDouble() * graphicWindow.getWidth(), random.nextDouble() * graphicWindow.getHeight());
            }
        }
        catch (Exception e)
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Число введено неверно");
            alert.setHeaderText("Неверно введены данные");
            alert.setContentText("Невозможно создать " + randomQuantity.getText() + " точек");
            alert.show();
        }
    }

    @FXML
    public void onStartButton()
    {
        onClearLinesButton();
        algorithm = new GeneticAlgorithm(createRoads(), algorithmStartParameters);
        stopButton.setDisable(false);
        startButton.setDisable(true);

        new Thread(() -> {
            algorithm.run();
            Phenotype phenotype = algorithm.getResult();
            Platform.runLater(
                    () -> {
                        List<City> lineList = phenotype.getList();
                        if (lineList.size() > 0)
                            addLine(lineList.get(0).getX(), lineList.get(0).getY(), lineList.get(lineList.size() - 1).getX(), lineList.get(lineList.size() - 1).getY());
                        for (int i = 1; i < lineList.size(); i++) {
                            addLine(lineList.get(i - 1).getX(), lineList.get(i - 1).getY(), lineList.get(i).getX(), lineList.get(i).getY());
                        }
                        stopButton.setDisable(true);
                        startButton.setDisable(false);
                    });
        }).start();
    }

    @FXML
    public void onClearAllButton()
    {
        graphicWindow.getChildren().clear();
        dots.clear();
    }


    @FXML
    public void onClearLinesButton()
    {
        ObservableList<Node> list = graphicWindow.getChildren();
        int i = 0;
        while(list.size() > i)
        {
            if(list.get(i) instanceof Line)
                list.remove(i);
            else
                i++;
        }
    }

    @FXML
    public void onStopButton()
    {
        algorithm.stop();
    }

    @FXML
    public void onSettingsButton()
    {

    }

    private Roads createRoads()
    {
        City[] cities = new City[dots.size()];
        for (int i = 0; i < cities.length ; i++)
        {
            cities[i] = new City(dots.get(i).getCenterX(), dots.get(i).getCenterY());
        }
        Roads roads = new Roads(cities, new WeightByDistance());
        return roads;
    }

    public static AlgorithmStartParameters getAlgorithmStartParameters() {
        return algorithmStartParameters;
    }

}
