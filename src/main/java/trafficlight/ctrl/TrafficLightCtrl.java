package trafficlight.ctrl;

import trafficlight.gui.TrafficLight;
import trafficlight.gui.TrafficLightGui;
import trafficlight.observer.Observer;
import trafficlight.observer.Subject;
import trafficlight.states.State;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class TrafficLightCtrl implements Subject {
    private static  TrafficLightCtrl instance = null;
    private State greenState;

    private State redState;

    private State yellowState;

    private State currentState;

    private State previousState;

    private final TrafficLightGui gui;

    private boolean doRun = true;

    public static TrafficLightCtrl getInstance() {
        if (instance == null) {
            instance = new TrafficLightCtrl();
        }
        return instance;
    }
    private List<Observer> observers = new ArrayList<>();
    private TrafficLightCtrl() {
        super();
        initStates();
        gui = new TrafficLightGui(this);
        update(currentState);
        gui.setVisible(true);
        //TODO useful to update the current state
    }

    private void initStates() {
        greenState = new State() {
            @Override
            public State getNextState() {
                previousState = currentState;
                //TODO useful to update the current state and the old one
                return yellowState;
            }
            @Override
            public String getColor() {
                return "green";
            }
            @Override
            public Color getAWTColor(){
                return Color.GREEN;
            }
        };

        redState = new State() {
            @Override
            public State getNextState() {
                previousState = currentState;
                //TODO useful to update the current state and the old one
                return yellowState;
            }
            @Override
            public String getColor() {
                return "red";
            }
            @Override
            public Color getAWTColor(){
                return Color.RED;
            }
        };

        yellowState = new State() {
            @Override
            public State getNextState() {
                if (previousState.equals(greenState)) {
                    previousState = currentState;
                    //TODO useful to update the current state and the old one
                    return redState;
                }else {
                    previousState = currentState;
                    //TODO useful to update the current state and the old one
                    return greenState;
                }
            }
            @Override
            public String getColor() {
                return "yellow";
            }
            @Override
            public Color getAWTColor(){
                return Color.YELLOW;
            }
        };
        currentState = greenState;
        previousState = yellowState;
    }

    public State getGreenState() {
        return greenState;
    }

    public State getRedState() {
        return redState;
    }

    public State getYellowState() {
        return yellowState;
    }

    public void run()  {
        int intervall = 1500;
        while (doRun) {
            try {
                Thread.sleep(intervall);
                nextState();
                update(currentState);
                //System.out.println(currentState.getColor());
            } catch (InterruptedException e) {
                gui.showErrorMessage(e);
            }
        }
        System.out.println("Stopped");
        System.exit(0);
    }

    public void nextState() {
        currentState = currentState.getNextState();
    }

    public void stop() {
        doRun = false;
    }

    @Override
    public <T extends Observer> void addObserver(T t) {
        this.observers.add(t);
    }

    @Override
    public <T extends Observer> void removeObserver(T t) {
        this.observers.remove(t);
    }

    @Override
    public void update(State s) {
        for(Observer obs:this.observers){
            obs.update(s);
        }
    }
}