package bridge.domain;

import java.util.Arrays;

public enum Selection {

    RESTART("R", "재시도"),
    QUIT("Q", "종료");

    private static final String INVALID_SELECTION_MESSAGE = "R(재시도)와 Q(종료) 중 하나의 문자를 입력할 수 있습니다.";

    private final String code;
    private final String description;

    Selection(String code, String description) {
        this.code = code;
        this.description = description;
    }

    public static Selection from(String code) {
        return Arrays.stream(values())
                .filter(s -> s.code.equals(code))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(INVALID_SELECTION_MESSAGE));
    }
}
