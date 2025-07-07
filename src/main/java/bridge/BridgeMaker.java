package bridge;

import java.util.ArrayList;
import java.util.List;

/**
 * 다리의 길이를 입력 받아서 다리를 생성해주는 역할을 한다.
 */
public class BridgeMaker {

    private static final String INVALID_SIZE_MESSAGE = "다리의 길이는 %d 이상 %d 이하의 숫자를 입력해야 합니다.";

    private static final String LOWER_ROW = "D";
    private static final String UPPER_ROW = "U";
    private static final int MIN_LENGTH = 3;
    private static final int MAX_LENGTH = 20;

    private final BridgeNumberGenerator bridgeNumberGenerator;

    public BridgeMaker(BridgeNumberGenerator bridgeNumberGenerator) {
        this.bridgeNumberGenerator = bridgeNumberGenerator;
    }

    /**
     * @param size 다리의 길이
     * @return 입력받은 길이에 해당하는 다리 모양. 위 칸이면 "U", 아래 칸이면 "D"로 표현해야 한다.
     */
    public List<String> makeBridge(int size) {
        validateSize(size);
        List<String> bridge = new ArrayList<>();

        for (int i = 0; i < size; i++) {
            int bridgeNumber = bridgeNumberGenerator.generate();
            if (bridgeNumber == 0) {
                bridge.add(LOWER_ROW);
                continue;
            }
            bridge.add(UPPER_ROW);
        }
        return bridge;
    }

    private void validateSize(int size) {
        if (size < MIN_LENGTH || size > MAX_LENGTH) {
            throw new IllegalArgumentException(
                    String.format(INVALID_SIZE_MESSAGE, MIN_LENGTH, MAX_LENGTH));
        }
    }
}
