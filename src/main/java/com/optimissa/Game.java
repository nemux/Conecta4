package com.optimissa;

import java.util.Scanner;

/**
 * Copyright 2016 (C) KEA
 *
 * Game on 31-mar-2017
 *
 * @author NeMuX <garaujo@kea.mx>
 */
public class Game {

    /**
     * Creates a new instance of Game
     */
    public Game() {
    }

    public static void main(String args[]) {
        Board board = new Board(6, 7);
        Player playerOne = Player.ONE;
        Player playerTwo = Player.TWO;
        Integer column = 0;

        Integer turn = 0;
        Boolean win1 = Boolean.FALSE;
        Boolean win2 = Boolean.FALSE;
        String activePlayer = "";
        char symbol;
         System.out.println("  _________  _  ____________________     ____");
         System.out.println(" / ___/ __ \\/ |/ / __/ ___/_  __/ _ |   / / /");
         System.out.println("/ /__/ /_/ /    / _// /__  / / / __ |  /_  _/");
         System.out.println("\\___/\\____/_/|_/___/\\___/ /_/ /_/ |_|   /_/ ");
        for (;;) {
            activePlayer = (turn % 2 == 0) ? playerOne.getTokenValue().toString() : playerTwo.getTokenValue().toString();
            symbol = (turn % 2 == 0) ? playerOne.getSymbol() : playerTwo.getSymbol();
            System.out.println("JUGADOR(" +activePlayer + "->"+ symbol +") Ingrese el # de columna, recuerde que es máximo 7");
            Scanner sc = new Scanner(System.in);
            column = sc.nextInt();

            if (column < 1 || column > 7) {
                turn =  turn + 2;
                continue;
            }

            if (turn % 2 == 0) {
                win1 = board.turn(column, playerOne);
            } else {
                win2 = board.turn(column, playerTwo);
            }

            if (win1) {
                System.out.println("Player " + playerOne.getTokenValue() +" Wins!");
                break;
            }
            if (win2) {
                System.out.println("Player " +  playerTwo.getTokenValue() + " Wins!");
                break;
            }

            if (playerOne.getTotalToken() == 0 && playerTwo.getTotalToken() == 0) {
                System.out.println("Game tied!.");
                break;
            }
            turn++;
        }
    }
}
