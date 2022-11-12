package com.example.javafxlapp3;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.util.ArrayList;

public class ShapeModel {
    public ObservableList<Shape> shapes = FXCollections.observableArrayList();

    public SimpleDoubleProperty size = new SimpleDoubleProperty(10);
    public ObjectProperty<Color> color = new SimpleObjectProperty<>(Color.RED);

    ObjectProperty<ShapeType> selectedShape = new SimpleObjectProperty<>();


    public ArrayList<Shape> isSelected = new ArrayList<>();



    public ArrayList<Shape> getIsSelected() {
        return isSelected;
    }

    public void setIsSelected(ArrayList<Shape> isSelected) {
        this.isSelected = isSelected;
    }


    public ObservableList<Shape> getShapes() {
        return shapes;
    }

    public void setShapes(ObservableList<Shape> shapes) {
        this.shapes = shapes;
    }

    public double getSize() {
        return size.get();
    }

    public SimpleDoubleProperty sizeProperty() {
        return size;
    }

    public void setSize(double size) {
        this.size.set(size);
    }

    public Color getColor() {
        return color.get();
    }

    public ObjectProperty<Color> colorProperty() {
        return color;
    }

    public void setColor(Color color) {
        this.color.set(color);
    }

    public ShapeType getSelectedShape() {
        return selectedShape.get();
    }

    public ObjectProperty<ShapeType> selectedShapeProperty() {
        return selectedShape;
    }

    public void setSelectedShape(ShapeType selectedShape) {
        this.selectedShape.set(selectedShape);
    }



    public void addShape(Shape shape) {
        shapes.add(shape);
    }

    public void removeLastShape() {

        shapes.remove(shapes.size() - 1);
    }




    public Shape creatShape(ShapeType shapeType, double x, double y, double width, double height, Color color) {
        return switch (shapeType) {
            case Square -> new Square(x, y, width, height, color);
            case Circle -> new Circle(x, y, width, height, color);
        };
    }

    public void changeColor(GraphicsContext g) {

        for (Shape shape : isSelected
        ) {
          shape.setColor(color.getValue());
            shape.drawShape(g);

        }

    }


}