package com.hust.weixin;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by Administration on 2016/5/9.
 */
public class TimerTest {
    public TimerTest(int second) {
        Timer timer = new Timer();
        timer.schedule(new MyTimeTask(), 0, second * 1000);
    }

    private class MyTimeTask extends TimerTask {

        @Override
        public void run() {
            System.out.println("time task is run");
        }
    }

    public static void main(String[] args) {
        new TimerTest(4);
    }
}
