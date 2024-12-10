package pairmatching.controller;

import static pairmatching.controller.ConfigController.configCrews;

import pairmatching.domain.Crews;
import pairmatching.view.InputView;
import pairmatching.view.OutputView;

public class MatchingController {
    private final InputView inputView;
    private final OutputView outputView;
    private final Crews crews;

    public MatchingController(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
        crews = configCrews();
    }

    public void run() {

    }
}
