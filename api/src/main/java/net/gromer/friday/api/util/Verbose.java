package net.gromer.friday.api.util;

public final class Verbose {

    private int verbose;
    private long lastFlagTime;

    public boolean flag(int amount) {
        lastFlagTime = System.currentTimeMillis();
        return (verbose++) > amount;
    }

    public boolean flag(int amount, long reset) {
        if (!TimeUtils.Passed(lastFlagTime, reset)) {
            lastFlagTime = System.currentTimeMillis();
            return (verbose++) > amount;
        }
        verbose = 0;
        lastFlagTime = System.currentTimeMillis();
        return false;
    }

    public boolean flag(int amount, int cap, long reset) {
        if (!TimeUtils.Passed(lastFlagTime, reset)) {
            lastFlagTime = System.currentTimeMillis();
            if (verbose <= cap) verbose++;
            return verbose > amount;
        }
        verbose = 0;
        lastFlagTime = System.currentTimeMillis();
        return false;
    }

    public int getVerbose() {
        return verbose;
    }

    public void setVerbose(int verbose) {
        this.verbose = verbose;
    }

    public void takeaway() {
        verbose = verbose > 0 ? verbose - 1 : 0;
    }

    public void takeaway(int amount) {
        verbose = verbose > 0 ? verbose - amount : 0;
    }
        public boolean flag(int amount, long reset, int toAdd) {
        if (!TimeUtils.elapsed(lastFlagTime, reset)) {
            lastFlagTime = System.currentTimeMillis();
            return (verbose += toAdd) > amount;
        }
        verbose = 0;
        lastFlagTime = System.currentTimeMillis();
        return false;
    }
}