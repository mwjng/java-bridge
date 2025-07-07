package bridge.domain;

import java.util.ArrayList;
import java.util.List;

public class BridgeSteps {

    private final Bridge bridge;
    private final List<BridgeStep> steps = new ArrayList<>();

    public BridgeSteps(Bridge bridge) {
        this.bridge = bridge;
    }

    public void move(Direction direction) {
        int currentIndex = steps.size();
        boolean isCorrect = bridge.canMove(currentIndex, direction);

        steps.add(new BridgeStep(direction, isCorrect));
    }

    public void reset() {
        this.steps.clear();
    }

    public boolean isFinished() {
        return isSuccess() || isFailed();
    }

    public boolean isSuccess() {
        return steps.size() == bridge.length() && steps.stream()
                .allMatch(BridgeStep::isCorrect);
    }

    private boolean isFailed() {
        return steps.stream()
                .anyMatch(step -> !step.isCorrect());
    }

    public List<BridgeStep> currentProgress() {
        return List.copyOf(steps);
    }
}
