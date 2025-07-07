package bridge.controller;

import bridge.BridgeMaker;
import bridge.BridgeNumberGenerator;
import bridge.domain.BridgeGame;
import bridge.domain.Direction;
import bridge.domain.Selection;
import bridge.view.InputView;
import bridge.view.OutputView;

public class BridgeController {

    private final InputView inputView = new InputView();
    private final OutputView outputView = new OutputView();
    private final BridgeMaker bridgeMaker;

    public BridgeController(BridgeNumberGenerator bridgeNumberGenerator) {
        this.bridgeMaker = new BridgeMaker(bridgeNumberGenerator);
    }

    public void run() {
        outputView.printStartMessage();
        BridgeGame bridgeGame = initializeGame();

        do {
            playRound(bridgeGame);
            if (bridgeGame.isSuccess()) {
                break;
            }
        } while (checkRestart(bridgeGame));
    }

    private BridgeGame initializeGame() {
        while (true) {
            outputView.requestBridgeLengthMessage();
            try {
                int inputSize = inputView.readBridgeSize();
                return new BridgeGame(bridgeMaker, inputSize);
            } catch (IllegalArgumentException e) {
                outputView.printErrorMessage(e.getMessage());
            }
        }
    }

    private void playRound(BridgeGame bridgeGame) {
        Direction direction = getDirectionFromInput();
        bridgeGame.move(direction);

        outputView.printMap(bridgeGame.getBridgeSteps());

        if (bridgeGame.isFinished()) {
            outputView.printResult(
                    bridgeGame.getBridgeSteps(),
                    bridgeGame.isSuccess(),
                    bridgeGame.getTryCount()
            );
            return;
        }

        playRound(bridgeGame);
    }

    private Direction getDirectionFromInput() {
        while (true) {
            outputView.requestDirectionMessage();
            try {
                String inputDirection = inputView.readMoving();
                return Direction.from(inputDirection);
            } catch (IllegalArgumentException e) {
                outputView.printErrorMessage(e.getMessage());
            }
        }
    }

    private boolean checkRestart(BridgeGame bridgeGame) {
        Selection selection = getSelectionFromInput();
        if (selection == Selection.RESTART) {
            bridgeGame.retry();
            return true;
        }
        return false;
    }

    private Selection getSelectionFromInput() {
        while (true) {
            outputView.requestRestartOrQuitMessage();
            try {
                String inputSelection = inputView.readGameCommand();
                return Selection.from(inputSelection);
            } catch (IllegalArgumentException e) {
                outputView.printErrorMessage(e.getMessage());
            }
        }
    }
}
