package trafficlight.observer;
import trafficlight.states.State;

public interface Observer {
    public void update(State s);//called by Observed Subject on change
}
