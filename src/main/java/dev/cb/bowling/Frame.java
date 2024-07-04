package dev.cb.bowling;

import java.util.ArrayList;
import java.util.List;

public class Frame {
    private int score;
    private boolean lastFrame;
    private PinGenerator pinGenerator;
    private List<Pin> pins = new ArrayList<>();

    public Frame(PinGenerator pinGenerator, boolean lastFrame) {
        this.lastFrame = lastFrame;
        this.pinGenerator = pinGenerator;
    }

    public boolean makeRoll() {
        int actualRoll = pinGenerator.randomFalledPin(getMaxRoll());
        pins.add(new Pin(actualRoll));
        score += actualRoll;

        return canMakeNewRoll();
    }

    private int getMaxRoll() {
        if (pins.isEmpty() || score >= 10) {
            return 10;
        } else {
            return 10 - pins.getLast().getQuantityFalledPin();
        }
    }

    private boolean canMakeNewRoll() {
        if (!lastFrame) {
            return pins.getFirst().getQuantityFalledPin() != 10 && pins.size() < 2;
        } else {
            return (pins.size() < 2 || score >= 10 && pins.size() <= 2);
        }
    }

    public int getScore() {
        return score;
    }
}
