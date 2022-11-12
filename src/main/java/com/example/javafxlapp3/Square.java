package com.example.javafxlapp3;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Square extends Shape {


    public Square(double x, double y, double width, double height, Color color) {
        super(x, y, width, height, color);
    }

    public void drawShape(GraphicsContext gc) {
        gc.fillRect(getX(), getY(), getWidth(), getHeight());
        gc.setFill(getColor());
        gc.strokeRect(getX(), getY(), getWidth(), getHeight());


    }

    @Override
    public boolean selectedShape(double x, double y) {
        if (x >= getX() &&
                x <= getX() + getWidth() &&
                y >= getY() &&
                y <= getY() + getHeight()) {
            return true;
        }
        return false;
    }


}