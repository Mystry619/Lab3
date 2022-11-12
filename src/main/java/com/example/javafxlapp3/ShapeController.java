package com.example.javafxlapp3;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;

public class ShapeController {

    public ColorPicker color;
    public Button undo;

    public Slider sliderSize;


    public Button change;



    public CheckBox selectedMode;


    @FXML
  public ChoiceBox<ShapeType> choice;


   public ShapeModel shapeModel = new ShapeModel();


  public ObservableList<ShapeType> ShapeTypeList = FXCollections.observableArrayList(ShapeType.values());

    public Canvas canvas;

    public GraphicsContext g;


    public Stage stage;


    @FXML
    public void initialize() {
        g = canvas.getGraphicsContext2D();
        choice.setItems(ShapeTypeList);
        color.valueProperty().bindBidirectional(shapeModel.colorProperty());
        sliderSize.valueProperty().bindBidirectional(shapeModel.sizeProperty());


    }


    public void setStage(Stage stage) {
        this.stage = stage;
    }


    public void drawOnCanvas(MouseEvent mouseEvent) {
        double x = mouseEvent.getX();
        double y = mouseEvent.getY();
        double size = Double.parseDouble(String.valueOf(this.sliderSize.getValue()));
        double size1 = Double.parseDouble(String.valueOf(this.sliderSize.getValue()));
        g.setFill(color.getValue());
        Shape shape = shapeModel.creatShape(choice.getValue(), x, y, size, size1, color.getValue());
        shapeModel.shapes.add(shape);
        shape.drawShape(g);


    }





    public void selectShape(MouseEvent mouseEvent) {

        double x = mouseEvent.getX();
        double y = mouseEvent.getY();
        if (selectedMode.isSelected()) {
            for (Shape shape1 : shapeModel.getShapes()
            ) {
                if (shape1.selectedShape(x, y)) {
                    shapeModel.isSelected.add(shape1);
                }
            }

        }
    }


    public void undo() {
        shapeModel.removeLastShape();

        g.clearRect(0, 0, 537.0, 297.0);
        for (Shape shape : shapeModel.getShapes()
        ) {
            shape.drawShape(g);

        }
    }







    public void changeSize(ActionEvent actionEvent) {
        g.clearRect(0, 0, 537.0, 297.0);
        for (Shape shape : shapeModel.getIsSelected()
        ) {
            shape.setWidth(sliderSize.getValue());
            shape.setHeight(sliderSize.getValue());
            shape.drawShape(g);
            for (Shape shape1 : shapeModel.getShapes()
            ) {
                shape1.drawShape(g);
            }
        }
    }




    public void changeColor(ActionEvent actionEvent) {

        shapeModel.changeColor(g);





    }

    public void save(ActionEvent actionEvent) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Save as");
        fileChooser.setInitialDirectory(new File(System.getProperty("user.home")));
        fileChooser.getExtensionFilters().clear();
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("SVG", "*.svg"));

        File filePath = fileChooser.showSaveDialog(stage);

        if (filePath != null)
            saveToFile(filePath.toPath());


    }





    public void saveToFile(Path toPath) {
        StringBuilder svg = new StringBuilder();
        String StrokeColor = "#000000ff";
        String fillColor = "#" + shapeModel.getColor().toString().substring(2, 10);
        svg.append("<svg width=\"537.0\" height=\"297.0\" version=\"1.1\" xmlns=\"http://www.w3.org/2000/svg\">\n");
        for (Shape sv : shapeModel.getShapes()
        ) {
            if (choice.getValue()==ShapeType.Square) {
                svg.append("<rect x=\"").append(sv.getX()).
                        append("\" y=\"").append(sv.getY()).
                        append("\" width=\"").append(sv.getWidth()).
                        append("\" height=\"").append(sv.getHeight()).
                        append("\" stroke=\"").append(StrokeColor).
                        append("\" fill=\"").append(fillColor).
                        append("\" stroke-width=\"1\"/>\n");
            } else {

                svg.append("<circle cx=\"").append(sv.getX()).
                        append("\" cy=\"").append(sv.getY()).
                        append("\" r=\"").append(sv.getWidth() + sv.getHeight() / 8).
                        append("\" stroke=\"").append(StrokeColor).
                        append("\" fill=\"").append(fillColor).
                        append("\" stroke-width=\"1\"/>\n");
            }
        }
                    svg.append("</svg>");



        try {
            FileWriter writeFile = new FileWriter(toPath.toFile());

            writeFile.write(svg.toString());

            writeFile.close();
            System.out.println("save process a prov");

        } catch (IOException e) {
            System.out.println("wrong.");
            e.printStackTrace();
        }


    }

}
