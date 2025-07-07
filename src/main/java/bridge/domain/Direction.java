package bridge.domain;

import java.util.Arrays;

public enum Direction {

    UP("U", "위 칸"),
    DOWN("D", "아래 칸");

    private static final String INVALID_DIRECTION_MESSAGE = "U(위 칸)와 D(아래 칸) 중 하나의 문자를 입력할 수 있습니다.";

    private final String code;
    private final String description;

    Direction(String code, String description) {
        this.code = code;
        this.description = description;
    }

    public static Direction from(String code) {
        return Arrays.stream(values())
                .filter(d -> d.code.equals(code))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(INVALID_DIRECTION_MESSAGE));
    }
}
