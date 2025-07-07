package bridge;

import bridge.controller.BridgeController;

public class Application {

    public static void main(String[] args) {
        BridgeNumberGenerator bridgeNumberGenerator = new BridgeRandomNumberGenerator();
        BridgeController bridgeController = new BridgeController(bridgeNumberGenerator);
        bridgeController.run();
    }
}
