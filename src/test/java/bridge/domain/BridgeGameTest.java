package bridge.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.util.Lists.newArrayList;
import static org.junit.jupiter.api.Assertions.assertAll;

import bridge.ApplicationTest.TestNumberGenerator;
import bridge.BridgeMaker;
import bridge.BridgeNumberGenerator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class BridgeGameTest {

    private BridgeGame game;

    @BeforeEach
    void setUp() {
        BridgeNumberGenerator numberGenerator = new TestNumberGenerator(newArrayList(1, 0, 1));
        BridgeMaker bridgeMaker = new BridgeMaker(numberGenerator);
        game = new BridgeGame(bridgeMaker, 3);
    }

    @Test
    void 첫_시도는_1이다() {
        assertThat(game.getTryCount()).isEqualTo(1);
    }

    @Test
    void move_호출시_step이_추가된다() {
        // when
        game.move(Direction.UP);

        // then
        assertThat(game.getBridgeSteps().currentProgress()).hasSize(1);
    }

    @Test
    void 다리를_정상적으로_건넜을_때_종료_여부를_반환한다() {
        // when
        game.move(Direction.UP);
        game.move(Direction.DOWN);
        game.move(Direction.UP);

        // then
        assertAll(
                () -> assertThat(game.isFinished()).isTrue(),
                () -> assertThat(game.isSuccess()).isTrue()
        );
    }

    @Test
    void 틀린_이동을_하면_isFinished는_true_isSuccess는_false를_반환한다() {
        // when
        game.move(Direction.UP);
        game.move(Direction.UP);

        // then
        assertAll(
                () -> assertThat(game.isFinished()).isTrue(),
                () -> assertThat(game.isSuccess()).isFalse()
        );
    }

    @Test
    void retry를_호출하면_tryCount가_증가하고_진행_상태가_초기화된다() {
        // given
        game.move(Direction.UP);
        game.move(Direction.UP);
        int beforeRetryCount = game.getTryCount();

        // when
        game.retry();

        // then
        assertAll(
                () -> assertThat(game.getTryCount()).isEqualTo(beforeRetryCount + 1),
                () -> assertThat(game.getBridgeSteps().currentProgress()).isEmpty()
        );
    }
}