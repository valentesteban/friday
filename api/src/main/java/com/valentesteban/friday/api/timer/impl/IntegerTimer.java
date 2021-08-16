package com.valentesteban.friday.api.timer.impl;

import com.valentesteban.friday.api.timer.Timer;
import com.valentesteban.friday.api.util.TimeUtil;

import java.util.concurrent.TimeUnit;

public class IntegerTimer extends Timer {

    public IntegerTimer(TimeUnit unit, int amount) {
        super(unit, amount);
    }

    @Override
    public String formattedExpiration() {
        return TimeUtil.formatTimeMillis(expiry - System.currentTimeMillis());
    }
}
