package bridge.view;

import bridge.domain.BridgeStep;
import bridge.domain.BridgeSteps;
import bridge.domain.Direction;
import java.util.ArrayList;
import java.util.List;

/**
 * 사용자에게 게임 진행 상황과 결과를 출력하는 역할을 한다.
 */
public class OutputView {

    public void printStartMessage() {
        System.out.println("다리 건너기 게임을 시작합니다.");
    }

    public void requestBridgeLengthMessage() {
        System.out.println();
        System.out.println("다리의 길이를 입력해주세요.");
    }

    public void requestDirectionMessage() {
        System.out.println();
        System.out.println("이동할 칸을 선택해주세요. (위: U, 아래: D)");
    }

    public void requestRestartOrQuitMessage() {
        System.out.println();
        System.out.println("게임을 다시 시도할지 여부를 입력해주세요. (재시도: R, 종료: Q)");
    }

    /**
     * 현재까지 이동한 다리의 상태를 정해진 형식에 맞춰 출력한다.
     * <p>
     * 출력을 위해 필요한 메서드의 인자(parameter)는 자유롭게 추가하거나 변경할 수 있다.
     */
    public void printMap(BridgeSteps steps) {
        List<String> upper = new ArrayList<>();
        List<String> lower = new ArrayList<>();

        appendProgressSymbols(steps, upper, lower);
        printBridgeRows(upper, lower);
    }

    /**
     * 게임의 최종 결과를 정해진 형식에 맞춰 출력한다.
     * <p>
     * 출력을 위해 필요한 메서드의 인자(parameter)는 자유롭게 추가하거나 변경할 수 있다.
     */
    public void printResult(BridgeSteps steps, boolean isSuccess, int tryCount) {
        System.out.println();
        System.out.println("최종 게임 결과");
        printMap(steps);
        printGameSuccess(isSuccess);
        printTryCount(tryCount);
    }

    public void printErrorMessage(String message) {
        System.out.println("[ERROR] " + message);
    }

    private void appendProgressSymbols(BridgeSteps steps, List<String> upper, List<String> lower) {
        for (BridgeStep step : steps.currentProgress()) {
            if (step.isCorrect()) {
                appendSuccessSymbol(step, upper, lower);
                continue;
            }
            appendFailureSymbol(step, upper, lower);
        }
    }

    private void appendSuccessSymbol(BridgeStep step, List<String> upper, List<String> lower) {
        if (step.getDirection() == Direction.UP) {
            upper.add("O");
            lower.add(" ");
            return;
        }
        upper.add(" ");
        lower.add("O");
    }

    private void appendFailureSymbol(BridgeStep step, List<String> upper, List<String> lower) {
        if (step.getDirection() == Direction.UP) {
            upper.add("X");
            lower.add(" ");
            return;
        }
        upper.add(" ");
        lower.add("X");
    }

    private void printBridgeRows(List<String> upper, List<String> lower) {
        System.out.println("[ " + String.join(" | ", upper) + " ]");
        System.out.println("[ " + String.join(" | ", lower) + " ]");
    }

    private void printGameSuccess(boolean isSuccess) {
        System.out.println();
        System.out.print("게임 성공 여부: ");
        if (isSuccess) {
            System.out.println("성공");
            return;
        }
        System.out.println("실패");
    }

    private void printTryCount(int tryCount) {
        System.out.println("총 시도한 횟수: " + tryCount);
    }
}
