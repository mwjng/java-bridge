package bridge.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.List;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

class BridgeTest {

    @ParameterizedTest
    @ValueSource(ints = {-1, 3, 4})
    void 다리_범위를_벗어난_위치로_이동_시_예외가_발생한다(int input) {
        // given
        Bridge bridge = new Bridge(List.of(Direction.UP, Direction.DOWN, Direction.UP));

        // when & then
        assertThatThrownBy(() -> bridge.canMove(input, Direction.UP))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("잘못된 이동 위치입니다.");
    }

    @ParameterizedTest
    @CsvSource(value = {"0, U, true", "1, U, false", "2, D, false"})
    void 올바른_위치로_이동하면_true_올바르지_않은_위치로_이동하면_false를_반환한다(int index, String dirCode, boolean expected) {
        // given
        Bridge bridge = new Bridge(List.of(Direction.UP, Direction.DOWN, Direction.UP));
        Direction direction = Direction.from(dirCode);

        // when
        boolean actual = bridge.canMove(index, direction);

        // then
        assertThat(actual).isEqualTo(expected);
    }
}