package bridge.domain;

public class BridgeStep {

    private final Direction direction;
    private final boolean isCorrect;

    public BridgeStep(Direction direction, boolean isCorrect) {
        this.direction = direction;
        this.isCorrect = isCorrect;
    }

    public Direction getDirection() {
        return this.direction;
    }

    public boolean isCorrect() {
        return this.isCorrect;
    }
}
