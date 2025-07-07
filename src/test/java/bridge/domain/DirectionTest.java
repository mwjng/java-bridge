package bridge.domain;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class DirectionTest {

    @ParameterizedTest
    @ValueSource(strings = {"U", "D"})
    void 정적_팩토리_메서드_from으로_인스턴스를_생성할_수_있다(String input) {
        // when
        Direction direction = Direction.from(input);

        // then
        assertNotNull(direction);
    }

    @ParameterizedTest
    @ValueSource(strings = {"A", "1", "c"})
    void U와_D이외의_문자를_입력하면_예외가_발생한다(String input) {
        // when & then
        assertThatThrownBy(() -> Direction.from(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("U(위 칸)와 D(아래 칸) 중 하나의 문자를 입력할 수 있습니다.");
    }
}