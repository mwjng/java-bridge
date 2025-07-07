package bridge.domain;

import bridge.BridgeMaker;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 다리 건너기 게임을 관리하는 클래스
 */
public class BridgeGame {

    private final BridgeSteps bridgeSteps;
    private int tryCount;

    public BridgeGame(BridgeMaker bridgeMaker, int size) {
        Bridge bridge = initializeBridge(bridgeMaker, size);
        this.bridgeSteps = new BridgeSteps(bridge);
        this.tryCount = 1;
    }

    private Bridge initializeBridge(BridgeMaker bridgeMaker, int size) {
        List<String> rawBridge = bridgeMaker.makeBridge(size);
        List<Direction> directions = rawBridge.stream()
                .map(Direction::from)
                .collect(Collectors.toList());

        return new Bridge(directions);
    }

    /**
     * 사용자가 게임을 다시 시도할 때 사용하는 메서드
     * <p>
     * 재시작을 위해 필요한 메서드의 반환 타입(return type), 인자(parameter)는 자유롭게 추가하거나 변경할 수 있다.
     */
    public void retry() {
        this.tryCount++;
        bridgeSteps.reset();
    }

    /**
     * 사용자가 칸을 이동할 때 사용하는 메서드
     * <p>
     * 이동을 위해 필요한 메서드의 반환 타입(return type), 인자(parameter)는 자유롭게 추가하거나 변경할 수 있다.
     */
    public void move(Direction direction) {
        bridgeSteps.move(direction);
    }

    public boolean isFinished() {
        return bridgeSteps.isFinished();
    }

    public boolean isSuccess() {
        return bridgeSteps.isSuccess();
    }

    public int getTryCount() {
        return this.tryCount;
    }

    public BridgeSteps getBridgeSteps() {
        return this.bridgeSteps;
    }
}
