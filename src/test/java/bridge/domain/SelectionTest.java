package bridge.domain;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class SelectionTest {

    @ParameterizedTest
    @ValueSource(strings = {"R", "Q"})
    void 정적_팩토리_메서드_from으로_인스턴스를_생성할_수_있다(String input) {
        // when
        Selection selection = Selection.from(input);

        // then
        assertNotNull(selection);
    }

    @ParameterizedTest
    @ValueSource(strings = {"A", "b", "1"})
    void R와_Q이외의_문자를_입력하면_예외가_발생한다(String input) {
        // when & then
        assertThatThrownBy(() -> Selection.from(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("R(재시도)와 Q(종료) 중 하나의 문자를 입력할 수 있습니다.");
    }
}