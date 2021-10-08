package controller;

import exception.GameException;
import model.GameMode;
import model.Player;
import service.GameService;

import static controller.PrintController.printMessage;
import static utils.Constants.EXCEPTION_MESSAGE;
import static utils.Constants.FLEETS_CREATED_MESSAGE;
import static utils.Constants.WELCOME_MESSAGE;

public class GameController {
    private final GameService service = new GameService();
    private final InputController inputController = new InputController();

    public void run() {
        printMessage(WELCOME_MESSAGE);
        GameMode gameMode = inputController.getGameMode();
        Player[] players = service.createPlayers(gameMode);
        service.rollingDiceToChooseWhoStarts(players);
        try {
            service.createFleet(players);
        } catch (GameException e) {
            printMessage(EXCEPTION_MESSAGE);
        }
        printMessage(FLEETS_CREATED_MESSAGE);
        Player player1 = players[0];
        Player player2 = players[1];
        boolean isGameOver = false;
        while (!isGameOver) {
            isGameOver = service.isGameOver(player1, player2, gameMode);
            if (!isGameOver) {
                isGameOver = service.isGameOver(player2, player1, gameMode);
            }
        }
        service.determineWinner(players);
    }

}
