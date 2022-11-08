package game;

import board.boxes.RealBox;

import java.util.concurrent.CompletableFuture;
import java.util.function.Supplier;

public class Game {

    public void humanTurn () {

    }

    public static void main(String[] args) {
        CompletableFuture
                .supplyAsync(turn1)
                .thenApply(turn2);
    }

}
