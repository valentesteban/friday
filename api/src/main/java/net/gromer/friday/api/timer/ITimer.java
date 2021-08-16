package net.gromer.friday.api.timer;

public interface ITimer {

    boolean isActive(boolean autoReset);
    boolean isActive();

    String formattedExpiration();

    void reset();
}
