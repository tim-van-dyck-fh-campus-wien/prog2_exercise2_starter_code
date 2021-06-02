package trafficlight.states;

//TODO implement a part of the pattern here

import trafficlight.observer.Observer;
import trafficlight.observer.Subject;

import java.awt.*;

public abstract class State  {

    public abstract State getNextState();

    public abstract String getColor();
    public abstract Color getAWTColor();

    public String getSting(){
        return getColor();
    }


}