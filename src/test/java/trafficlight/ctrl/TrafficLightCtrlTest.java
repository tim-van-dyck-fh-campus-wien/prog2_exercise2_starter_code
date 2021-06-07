package trafficlight.ctrl;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import trafficlight.states.State;

import static org.junit.jupiter.api.Assertions.*;
public class TrafficLightCtrlTest {
    TrafficLightCtrl ctrl;
   /* @BeforeAll
    void setup() {
        ctrl = TrafficLightCtrl.getInstance();
    }*/
    @Test
    @DisplayName("Green state transition")
    public void nextStateTest_case1(){
        TrafficLightCtrl ctrl = TrafficLightCtrl.getInstance();
        State state = ctrl.getGreenState();
        State result = state.getNextState();
        assertEquals(result.getColor(),ctrl.getYellowState().getColor());
    }
    @Test
    @DisplayName("Red state transition")
    public void nextStateTest_case2(){
        TrafficLightCtrl ctrl = TrafficLightCtrl.getInstance();
        State state = ctrl.getRedState();
        State result = state.getNextState();
        assertEquals(ctrl.getYellowState().getColor(),result.getColor());
    }
    @Test
    @DisplayName("Yellow to Red transition")
    //Previous state is Green
    public void nextStateTest_case3(){
        TrafficLightCtrl ctrl = TrafficLightCtrl.getInstance();
        State state = ctrl.getYellowState();
        State result = state.getNextState();
        assertEquals(ctrl.getRedState().getColor(),result.getColor());
    }

    @Test
    @DisplayName("Yellow to Green transition")
    public void nextStateTest_case4(){
        TrafficLightCtrl ctrl = TrafficLightCtrl.getInstance();
        ctrl.nextState();//Set current state to yellow
        ctrl.nextState();//set prev state to red...
       State state= ctrl.getYellowState();
       State result = state.getNextState();
       assertEquals(result.getColor(),ctrl.getGreenState().getColor());
    }
    @Test
    @DisplayName("Test Singleton")
    public void singletonTest(){
        TrafficLightCtrl ctrl1 = TrafficLightCtrl.getInstance();
        TrafficLightCtrl ctrl2 = TrafficLightCtrl.getInstance();
        assertEquals(ctrl1,ctrl2);
    }

}
