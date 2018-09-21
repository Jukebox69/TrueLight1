package com.bylarobotics.truelight1;

import java.util.Timer;
import java.util.TimerTask;

import static com.bylarobotics.truelight1.Headlight.flashLamp;

/**Singleton Class to beacon control
 * @author Ilya Evstafev
 * @version 1.1 */
public class Beacon {
    public static final Beacon beacon = new Beacon();
    public static Timer timer = new Timer(true);
    private TimerTask onTimerTask;
    private TimerTask offTimerTask;

    /**Start Econom beacon */
    public void startBeacon()  {

        onTimerTask = new TimerTask() {
            @Override
            public void run() {
                flashLamp.turnLightOn();
            }
        };
        offTimerTask = new TimerTask() {
            @Override
            public void run() {
                flashLamp.turnLightOff();
            }
        };

        timer.schedule(onTimerTask, 100, 3000);
        timer.schedule(offTimerTask, 300, 3000);
    }
    /**Stop beacon timer*/
    public void stopBeacon() {
        timer.cancel();
    }
}
