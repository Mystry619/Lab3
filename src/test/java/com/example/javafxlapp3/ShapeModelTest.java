package com.example.javafxlapp3;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.paint.Color;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ShapeModelTest {

    @Test
    void addShapeToArraysList() {
        ShapeModel shapeModel = new ShapeModel();
        ObservableList<Shape> shapes = FXCollections.observableArrayList();
        Circle circle = new Circle(10, 20, 50, 100, Color.BLUE);
        shapeModel.addShape(circle);
        assertEquals(1, shapeModel.shapes.size());

    }

    @Test
    void removeShapeFromArrayList() {
        ShapeModel shapeModel = new ShapeModel();

        ObservableList<Shape> shapes = FXCollections.observableArrayList();
        Circle circle = new Circle(10, 20, 50, 100, Color.YELLOW);
        shapeModel.addShape(circle);
        shapeModel.removeLastShape();
        assertEquals(0, shapeModel.shapes.size());
    }


}

