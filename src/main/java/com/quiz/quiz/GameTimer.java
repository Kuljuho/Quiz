package com.quiz.quiz;

import java.util.Timer;
import java.util.TimerTask;

public class GameTimer {
    private Timer timer;
    private Long startTime;

    public GameTimer() {
        this.timer = new Timer(true);
    }

    public synchronized void startGame() {
        if (timer != null) {
            timer.cancel();
        }
        timer = new Timer(true);
        startTime = System.currentTimeMillis();

        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                long currentTime = System.currentTimeMillis();
                long elapsedTime = currentTime - startTime;
                long elapsedSeconds = elapsedTime / 1000;
                long secondsDisplay = elapsedSeconds % 60;
                long elapsedMinutes = elapsedSeconds / 60;
                System.out.println("Aika: " + elapsedMinutes + " min, " + secondsDisplay + " s");
            }
        }, 0, 1000);
    }

    public synchronized String getElapsedTime() {
        if (startTime == null) {
            return "Peliä ei ole aloitettu.";
        }
        long currentTime = System.currentTimeMillis();
        long elapsedTime = currentTime - startTime;
        long elapsedSeconds = elapsedTime / 1000;
        long secondsDisplay = elapsedSeconds % 60;
        long elapsedMinutes = elapsedSeconds / 60;
        return String.format("Aika: %d min, %d s", elapsedMinutes, secondsDisplay);
    }

    public synchronized void stopGame() {
        if (timer != null) {
            timer.cancel();
            timer = null;
        }
        startTime = null;
    }
}
