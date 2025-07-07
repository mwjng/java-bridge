package bridge.domain;

import java.util.List;

public class Bridge {

    private static final String INVALID_INDEX_MESSAGE = "잘못된 이동 위치입니다.";

    private final List<Direction> values;

    public Bridge(List<Direction> values) {
        this.values = values;
    }

    public boolean canMove(int index, Direction direction) {
        validateRange(index);
        return values.get(index) == direction;
    }

    public int length() {
        return values.size();
    }

    private void validateRange(int index) {
        if (isOutOfRange(index)) {
            throw new IllegalArgumentException(INVALID_INDEX_MESSAGE);
        }
    }

    private boolean isOutOfRange(int index) {
        return index < 0 || index >= values.size();
    }
}
