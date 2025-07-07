package bridge.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class BridgeStepsTest {

    private Bridge bridge;

    @BeforeEach
    void setUp() {
        bridge = new Bridge(List.of(Direction.UP, Direction.DOWN, Direction.UP));
    }

    @Test
    void move_메서드는_방향_정보와_정답_여부에_따라_BridgeStep을_추가한다() {
        // given
        BridgeSteps steps = new BridgeSteps(bridge);

        // when
        steps.move(Direction.UP);
        steps.move(Direction.UP);
        steps.move(Direction.DOWN);

        // then
        List<BridgeStep> result = steps.currentProgress();
        assertAll(
                () -> assertThat(result).hasSize(3),
                () -> assertThat(result.get(0).getDirection()).isEqualTo(Direction.UP),
                () -> assertThat(result.get(1).getDirection()).isEqualTo(Direction.UP),
                () -> assertThat(result.get(2).getDirection()).isEqualTo(Direction.DOWN),
                () -> assertThat(result.get(0).isCorrect()).isTrue(),
                () -> assertThat(result.get(1).isCorrect()).isFalse(),
                () -> assertThat(result.get(2).isCorrect()).isFalse()
        );
    }

    @Test
    void isSuccess는_모든_칸을_정답으로_이동했을_때_true를_반환한다() {
        // given
        BridgeSteps steps = new BridgeSteps(bridge);
        steps.move(Direction.UP);
        steps.move(Direction.DOWN);
        steps.move(Direction.UP);

        // when
        boolean success = steps.isSuccess();

        // then
        assertThat(success).isTrue();
    }

    @Test
    void isSuccess는_중간에_틀리면_false를_반환한다() {
        // given
        BridgeSteps steps = new BridgeSteps(bridge);
        steps.move(Direction.UP);
        steps.move(Direction.UP);

        // when
        boolean success = steps.isSuccess();

        // then
        assertThat(success).isFalse();
    }

    @Test
    void isFinished는_성공이나_실패시_true를_반환한다() {
        // given
        BridgeSteps successSteps = new BridgeSteps(bridge);
        successSteps.move(Direction.UP);
        successSteps.move(Direction.DOWN);
        successSteps.move(Direction.UP);

        BridgeSteps failSteps = new BridgeSteps(bridge);
        failSteps.move(Direction.UP);
        failSteps.move(Direction.UP);

        // when & then
        assertAll(
                () -> assertThat(successSteps.isFinished()).isTrue(),
                () -> assertThat(failSteps.isFinished()).isTrue()
        );
    }

    @Test
    void reset_호출시_모든_스텝이_지워진다() {
        // given
        BridgeSteps steps = new BridgeSteps(bridge);
        steps.move(Direction.UP);
        steps.move(Direction.DOWN);

        // when
        steps.reset();

        // then
        assertThat(steps.currentProgress()).isEmpty();
    }
}