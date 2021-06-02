package trafficlight.gui;


import trafficlight.observer.Observer;

import java.awt.*;
import java.lang.reflect.Field;

import trafficlight.states.State;

public class TrafficLight extends Light implements Observer {

    TrafficLight(Color color) {
        super(color);
        //System.out.println(super.on.toString());
        try{
            Field field = Color.class.getField("yellow");
            color = (Color)field.get(null);
            System.out.println( color.toString());

        }catch(Exception e){

        }
        //System.out.println(stateColor.toString());
    }

    public void turnOn(boolean a) {
        isOn = a;
        repaint();
    }

    public boolean isOn() {
        return isOn;
    }

    @Override
    public void update(State s) {
        if(s.getAWTColor()==super.on){
            turnOn(true);
        }else{
            turnOn(false);
        }
        System.out.println(s.getColor());

    }

    //TODO implement a part of the pattern here
}
