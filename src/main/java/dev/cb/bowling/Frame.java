package dev.cb.bowling;

import java.util.ArrayList;
import java.util.List;

public class Frame {
    private int score;
    private boolean lastFrame;
    private PinGenerator pinGenerator;
    private List<Pin> pins = new ArrayList<Pin>();

    public Frame(PinGenerator pinGenerator, boolean lastFrame) {
        this.lastFrame = lastFrame;
        this.pinGenerator = pinGenerator;
    }

    public boolean makeRoll() {
        int maxRoll = pins.isEmpty() ? 0 : 10 - pins.getLast().getQuantityFalledPin();
        int actualRoll = pinGenerator.randomFalledPin(maxRoll);
        pins.add(new Pin(actualRoll));
        score += actualRoll;

        if (!lastFrame) {
            return pins.getFirst().getQuantityFalledPin() != 10 && pins.size() < 2;
        } else {
            return score >= 10 && pins.size() <= 2;
        }
    }

    public int getScore() {
        return score;
    }
}
