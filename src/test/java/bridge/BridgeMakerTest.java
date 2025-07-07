package bridge;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.List;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

class BridgeMakerTest {

    private final BridgeMaker bridgeMaker = new BridgeMaker(new BridgeRandomNumberGenerator());

    @ParameterizedTest
    @CsvSource(value = {"3,3", "4,4", "19,19", "20,20"})
    void 길이를_입력받아_다리를_생성할_수_있다(int input, int expected) {
        // when
        List<String> bridge = bridgeMaker.makeBridge(input);

        // then
        assertThat(bridge).hasSize(expected);
    }

    @ParameterizedTest
    @ValueSource(ints = {-1, 2, 21, 22})
    void 길이가_3미만이거나_20을_초과하면_예외가_발생한다(int input) {
        // when & then
        assertThatThrownBy(() -> bridgeMaker.makeBridge(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("다리의 길이는 3 이상 20 이하의 숫자를 입력해야 합니다.");
    }
}