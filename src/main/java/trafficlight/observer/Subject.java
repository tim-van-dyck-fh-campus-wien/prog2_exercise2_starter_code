package trafficlight.observer;
import trafficlight.states.State;

public interface Subject {
    <T extends Observer>  void addObserver(T t);
    <T extends Observer> void removeObserver(T t);
    void update(State s);
}
