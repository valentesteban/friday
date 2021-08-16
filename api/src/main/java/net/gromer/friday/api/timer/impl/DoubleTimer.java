package net.gromer.friday.api.timer.impl;

import net.gromer.friday.api.timer.Timer;

import java.util.concurrent.TimeUnit;

public class DoubleTimer extends Timer {

    public DoubleTimer(int seconds) {
        super(TimeUnit.SECONDS, seconds);
    }

    @Override
    public String formattedExpiration() {
        double seconds = (expiry - System.currentTimeMillis()) / 1000.0;
        return String.format("%.1f seconds", seconds);
    }
}
