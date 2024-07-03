package dev.cb.bowling;

import jdk.jshell.spi.ExecutionControl;

import java.util.List;

public class Frame {
    private int score;
    private boolean lastFrame;
    private PinGenerator pinGenerator;
    private List<Roll> rolls;

    public Frame(PinGenerator pinGenerator, boolean lastFrame) {
        this.lastFrame = lastFrame;
        this.pinGenerator = this.pinGenerator;
    }

    public boolean makeRoll() throws ExecutionControl.NotImplementedException {
        throw new ExecutionControl.NotImplementedException("Method not implemented");
    }
}
