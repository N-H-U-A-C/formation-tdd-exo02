package dev.cb.bowling;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class FrameTest {

    private Frame frame;
    private PinGenerator pinGenerator = mock(PinGenerator.class);
    private boolean lastFrame;

    @BeforeEach
    void setUp() {
    }

    @Test
    public void Roll_SimpleFrame_FirstRoll_CheckScore() {
        // given
        lastFrame = false;
        frame = new Frame(pinGenerator, lastFrame);
        int expected = 5;
        when(pinGenerator.randomFalledPin(anyInt())).thenReturn(5);

        // when
        frame.makeRoll();
        int result = frame.getScore();

        // then
        assertThat(result).isEqualTo(expected);
    }

    @Test
    public void Roll_SimpleFrame_SecondRoll_CheckScore() {
        // given
        lastFrame = false;
        frame = new Frame(pinGenerator, lastFrame);
        int expected = 7;
        when(pinGenerator.randomFalledPin(anyInt())).thenReturn(6, 1);

        // when
        frame.makeRoll();
        frame.makeRoll();
        int result = frame.getScore();

        // then
        assertThat(result).isEqualTo(expected);
    }

    @Test
    public void Roll_SimpleFrame_FirstRollStrike_ReturnFalse() {
        // given
        lastFrame = false;
        frame = new Frame(pinGenerator, lastFrame);
        when(pinGenerator.randomFalledPin(anyInt())).thenReturn(10);

        // when
        boolean result = frame.makeRoll();

        // then
        assertThat(result).isFalse();
    }

    @Test
    public void Roll_SimpleFrame_SecondRoll_ReturnFalse() {
        // given
        lastFrame = false;
        frame = new Frame(pinGenerator, lastFrame);
        when(pinGenerator.randomFalledPin(anyInt())).thenReturn(2, 2);

        // when
        frame.makeRoll();
        boolean result = frame.makeRoll();

        // then
        assertThat(result).isFalse();
    }

    @Test
    public void Roll_LastFrame_FirstRollStrike_ReturnTrue() {
        // given
        lastFrame = true;
        frame = new Frame(pinGenerator, lastFrame);
        when(pinGenerator.randomFalledPin(anyInt())).thenReturn(10);

        // when
        boolean result = frame.makeRoll();

        // then
        assertThat(result).isTrue();
    }
}
