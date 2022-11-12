package com.example.javafxlapp3;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import static java.lang.Math.sqrt;

public class Circle extends Shape {


    public Circle(double x, double y, double width, double height, Color color) {
        super(x, y, width, height, color);
    }

    public void drawShape(GraphicsContext gc) {
        gc.fillOval(getX(),getY(),getWidth(),getHeight());
        gc.setFill(getColor());
        gc.strokeOval(getX(),getY(),getWidth(),getHeight());


    }

    @Override
    public boolean selectedShape(double x, double y) {
        double distX = x - getX();
        double distY = y - getY();
        double distance = sqrt( (distX*distX) + (distY*distY) );


        if (distance <= getWidth() + getHeight()) {
            return true;
        }
        return false;
    }
    }


