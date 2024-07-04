package dev.cb.bowling;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class FrameTest {

    private Frame frame;
    @Mock
    private PinGenerator pinGenerator;
    private boolean lastFrame;
    private int maxRoll;
    private int firstRoll;
    private int secondRoll;
    private int thirdRoll;

    @BeforeEach
    void setUp() {
        maxRoll = 10;
    }

    // SIMPLE FRAME
    @Test
    public void Roll_SimpleFrame_FirstRoll_CheckScore() {
        // given
        lastFrame = false;
        frame = new Frame(pinGenerator, lastFrame);
        firstRoll = 5;
        int expected = firstRoll;
        when(pinGenerator.randomFalledPin(maxRoll)).thenReturn(firstRoll);

        // when
        frame.makeRoll();
        int result = frame.getScore();

        // then
        assertThat(result).isEqualTo(expected);
    }

    @Test
    public void Roll_SimpleFrame_FirstRoll_ReturnTrue() {
        // given
        lastFrame = false;
        frame = new Frame(pinGenerator, lastFrame);
        firstRoll = 2;
        when(pinGenerator.randomFalledPin(maxRoll)).thenReturn(firstRoll);

        // when
        boolean result = frame.makeRoll();

        // then
        assertThat(result).isTrue();
    }

    @Test
    public void Roll_SimpleFrame_FirstRollStrike_ReturnFalse() {
        // given
        lastFrame = false;
        frame = new Frame(pinGenerator, lastFrame);
        firstRoll = 10;
        when(pinGenerator.randomFalledPin(maxRoll)).thenReturn(firstRoll);

        // when
        boolean result = frame.makeRoll();

        // then
        assertThat(result).isFalse();
    }

    @Test
    public void Roll_SimpleFrame_SecondRoll_CheckScore() {
        // given
        lastFrame = false;
        frame = new Frame(pinGenerator, lastFrame);
        firstRoll = 6;
        secondRoll = 1;
        int expected = firstRoll + secondRoll;
        when(pinGenerator.randomFalledPin(maxRoll)).thenReturn(firstRoll);
        when(pinGenerator.randomFalledPin(maxRoll - firstRoll)).thenReturn(secondRoll);

        // when
        frame.makeRoll();
        frame.makeRoll();
        int result = frame.getScore();

        // then
        assertThat(result).isEqualTo(expected);
    }

    @Test
    public void Roll_SimpleFrame_SecondRoll_ReturnFalse() {
        // given
        lastFrame = false;
        frame = new Frame(pinGenerator, lastFrame);
        firstRoll = 2;
        secondRoll = 2;
        when(pinGenerator.randomFalledPin(maxRoll)).thenReturn(firstRoll);
        when(pinGenerator.randomFalledPin(maxRoll - firstRoll)).thenReturn(secondRoll);

        // when
        frame.makeRoll();
        boolean result = frame.makeRoll();

        // then
        assertThat(result).isFalse();
    }

    // LAST FRAME
    @Test
    public void Roll_LastFrame_FirstRoll_CheckScore() {
        // given
        lastFrame = true;
        frame = new Frame(pinGenerator, lastFrame);
        firstRoll = 5;
        int expected = firstRoll;
        when(pinGenerator.randomFalledPin(maxRoll)).thenReturn(firstRoll);

        // when
        frame.makeRoll();
        int result = frame.getScore();

        // then
        assertThat(result).isEqualTo(expected);
    }

    @Test
    public void Roll_LastFrame_FirstRoll_ReturnTrue() {
        // given
        lastFrame = true;
        frame = new Frame(pinGenerator, lastFrame);
        firstRoll = 2;
        when(pinGenerator.randomFalledPin(maxRoll)).thenReturn(firstRoll);

        // when
        boolean result = frame.makeRoll();

        // then
        assertThat(result).isTrue();
    }

    @Test
    public void Roll_LastFrame_FirstRollStrike_ReturnTrue() {
        // given
        lastFrame = true;
        frame = new Frame(pinGenerator, lastFrame);
        firstRoll = 10;
        when(pinGenerator.randomFalledPin(maxRoll)).thenReturn(firstRoll);

        // when
        boolean result = frame.makeRoll();

        // then
        assertThat(result).isTrue();
    }

    @Test
    public void Roll_LastFrame_SecondRoll_CheckScore() {
        // given
        lastFrame = true;
        frame = new Frame(pinGenerator, lastFrame);
        firstRoll = 6;
        secondRoll = 1;
        int expected = firstRoll + secondRoll;
        when(pinGenerator.randomFalledPin(maxRoll)).thenReturn(firstRoll);
        when(pinGenerator.randomFalledPin(maxRoll - firstRoll)).thenReturn(secondRoll);

        // when
        frame.makeRoll();
        frame.makeRoll();
        int result = frame.getScore();

        // then
        assertThat(result).isEqualTo(expected);
    }

    @Test
    public void Roll_LastFrame_SecondRoll_ReturnFalse() {
        // given
        lastFrame = true;
        frame = new Frame(pinGenerator, lastFrame);
        firstRoll = 5;
        secondRoll = 3;
        when(pinGenerator.randomFalledPin(maxRoll)).thenReturn(firstRoll);
        when(pinGenerator.randomFalledPin(maxRoll - firstRoll)).thenReturn(secondRoll);

        // when
        frame.makeRoll();
        boolean result = frame.makeRoll();

        // then
        assertThat(result).isFalse();
    }

    @Test
    public void Roll_LastFrame_SecondRoll_FirstRollStrike_CheckScore() {
        // given
        lastFrame = true;
        frame = new Frame(pinGenerator, lastFrame);
        firstRoll = 10;
        secondRoll = 5;
        int expected = firstRoll + secondRoll;
        when(pinGenerator.randomFalledPin(maxRoll)).thenReturn(firstRoll).thenReturn(secondRoll);

        // when
        frame.makeRoll();
        frame.makeRoll();
        int result = frame.getScore();

        // then
        assertThat(result).isEqualTo(expected);
    }

    @Test
    public void Roll_LastFrame_SecondRoll_FirstRollStrike_ReturnTrue() {
        // given
        lastFrame = true;
        frame = new Frame(pinGenerator, lastFrame);
        firstRoll = 10;
        secondRoll = 5;
        when(pinGenerator.randomFalledPin(maxRoll)).thenReturn(firstRoll).thenReturn(secondRoll);

        // when
        frame.makeRoll();
        boolean result = frame.makeRoll();

        // then
        assertThat(result).isTrue();
    }

    @Test
    public void Roll_LastFrame_SecondRoll_FirstRollSpare_CheckScore() {
        // given
        lastFrame = true;
        frame = new Frame(pinGenerator, lastFrame);
        firstRoll = 5;
        secondRoll = 5;
        int expected = firstRoll + secondRoll;
        when(pinGenerator.randomFalledPin(maxRoll)).thenReturn(firstRoll);
        when(pinGenerator.randomFalledPin(maxRoll - firstRoll)).thenReturn(secondRoll);

        // when
        frame.makeRoll();
        frame.makeRoll();
        int result = frame.getScore();

        // then
        assertThat(result).isEqualTo(expected);
    }

    @Test
    public void Roll_LastFrame_SecondRoll_SecondRollSpare_ReturnTrue() {
        // given
        lastFrame = true;
        frame = new Frame(pinGenerator, lastFrame);
        firstRoll = 5;
        secondRoll = 5;
        when(pinGenerator.randomFalledPin(maxRoll)).thenReturn(firstRoll);
        when(pinGenerator.randomFalledPin(maxRoll - firstRoll)).thenReturn(secondRoll);

        // when
        frame.makeRoll();
        boolean result = frame.makeRoll();

        // then
        assertThat(result).isTrue();
    }

    @Test
    public void Roll_LastFrame_ThirdRoll_FirstRollStrike_CheckScore() {
        // given
        lastFrame = true;
        frame = new Frame(pinGenerator, lastFrame);
        firstRoll = 10;
        secondRoll = 10;
        thirdRoll = 5;
        int expected = firstRoll + secondRoll + thirdRoll;
        when(pinGenerator.randomFalledPin(maxRoll)).thenReturn(firstRoll, secondRoll, thirdRoll);

        // when
        frame.makeRoll();
        frame.makeRoll();
        frame.makeRoll();
        int result = frame.getScore();

        // then
        assertThat(result).isEqualTo(expected);
    }

    @Test
    public void Roll_LastFrame_ThirdRoll_FirstRollStrike_ReturnFalse() {
        // given
        lastFrame = true;
        frame = new Frame(pinGenerator, lastFrame);
        firstRoll = 10;
        secondRoll = 10;
        thirdRoll = 5;
        when(pinGenerator.randomFalledPin(maxRoll)).thenReturn(firstRoll, secondRoll, thirdRoll);

        // when
        frame.makeRoll();
        frame.makeRoll();
        boolean result = frame.makeRoll();

        // then
        assertThat(result).isFalse();
    }

    @Test
    public void Roll_LastFrame_ThirdRoll_SecondRollSpare_CheckScore() {
        // given
        lastFrame = true;
        frame = new Frame(pinGenerator, lastFrame);
        firstRoll = 6;
        secondRoll = 4;
        thirdRoll = 7;
        int expected = 17;
        when(pinGenerator.randomFalledPin(maxRoll)).thenReturn(firstRoll, thirdRoll);
        when(pinGenerator.randomFalledPin(maxRoll - firstRoll)).thenReturn(secondRoll);

        // when
        frame.makeRoll();
        frame.makeRoll();
        frame.makeRoll();
        int result = frame.getScore();

        // then
        assertThat(result).isEqualTo(expected);
    }

    @Test
    public void Roll_LastFrame_ThirdRoll_SecondRollSpare_ReturnFalse() {
        // given
        lastFrame = true;
        frame = new Frame(pinGenerator, lastFrame);
        firstRoll = 5;
        secondRoll = 5;
        thirdRoll = 10;
        when(pinGenerator.randomFalledPin(maxRoll)).thenReturn(firstRoll, secondRoll, thirdRoll);

        // when
        frame.makeRoll();
        frame.makeRoll();
        boolean result = frame.makeRoll();

        // then
        assertThat(result).isFalse();
    }
}
