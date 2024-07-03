package dev.cb.bowling;

import jdk.jshell.spi.ExecutionControl;

import java.util.ArrayList;
import java.util.List;

public class Frame {
    private int score;
    private boolean lastFrame;
    private PinGenerator pinGenerator;
    private List<Pin> pins = new ArrayList<Pin>();

    public Frame() {
    }

    public Frame(PinGenerator pinGenerator, boolean lastFrame) {
        this.lastFrame = lastFrame;
        this.pinGenerator = pinGenerator;
    }

    public boolean makeRoll() {
        int maxRoll = pins.isEmpty() ? 0 : 10 - pins.getLast().getQuantityFalledPin();
        int actualRoll = pinGenerator.randomFalledPin(maxRoll);
        pins.add(new Pin(actualRoll));
        score += actualRoll;

        if (pins.getLast().getQuantityFalledPin() == 10) {
            return false;
        }
        return true;
    }

    public int getScore() {
        return score;
    }
}
