package com.optimissa;

import java.util.ArrayList;
import java.util.Objects;

/**
 * Copyright 2016 (C) NeMuX
 *
 * Board on 31-mar-2017
 *
 * @author NeMuX <nemux.1@gmail.com>
 */
public class Board {

    private int[][] board;
    private final int rows;
    private final int columns;

    /**
     * Creates a new instance of Board
     *
     * @param rows
     * @param columns
     */
    public Board(int rows, int columns) {
        this.rows = rows;
        this.columns = columns;
        board = new int[this.rows][this.columns];
    }

    public Boolean turn(Integer column, Player player) {
        Integer row = 0;
        column = column-1;
        Boolean winner = Boolean.FALSE;
        
        for (int i = rows - 1 ; i >= 0; i--) {
            if (board[i][column] == 0) {
                board[i][column] = player.putToken();
                row = i;
                break;
            }
        }

        if (checkHorizontal(row, column, player) || checkVertical(row, column, player)
                || checkDiagonal(row, column, player) || checkSecondaryDiagonal(row, column, player)) {
            winner = Boolean.TRUE;
        }
        
        printBoard();

        return winner;
    }

    private Boolean checkHorizontal(Integer row, Integer column, Player player) {
        Boolean win = Boolean.FALSE;

        Integer minColumn = column - 3 < 0
                ? 0
                : column - 3;
        Integer maxColumn = column + 3 >= 7
                ? this.columns - 1
                : column + 3;

        for (int aux = minColumn; aux < maxColumn; aux++) {
            Integer sum = 0;
            Integer currentToken = 0;
            for (int aux2 = 0; aux2 < 4; aux2++) {
                if ((aux + aux2) > maxColumn)
                    break;
                currentToken = board[row][aux + aux2];
                sum += (Objects.equals(currentToken, player.getTokenValue())) ? currentToken : 0;
            }
            if (sum == player.getTokenValue() * 4)  {
                win = Boolean.TRUE;
                System.out.println("WIN BY " + WinType.HORIZONTAL);
                break;
            }
        }
        return win;
    }

    private Boolean checkVertical(Integer row, Integer column, Player player) {
        Boolean win = Boolean.FALSE;
        if (row > 2) {
            return win;
        }

        Integer minRow = row - 3 < 0
                ? 0
                : row - 3;
        Integer maxRow = row + 3 >= 6
                ? this.rows - 1
                : row + 3;

        for (int aux = maxRow; aux > minRow; aux--) {
            Integer sum = 0;
            Integer currentToken = 0;
            for (int aux2 = 0; aux2 < 4; aux2++) {
                if((aux + aux2 > maxRow))
                    break;
                currentToken = board[aux + aux2][column];
                sum += (Objects.equals(currentToken, player.getTokenValue())) ? currentToken : 0;
            }
            if (sum == player.getTokenValue() * 4) {
                win = Boolean.TRUE;
                System.out.println("WIN BY " + WinType.VERTICAL);
                break;
            }
        }
        return win;
    }

    private Boolean checkDiagonal(Integer row, Integer column, Player player) {
        ArrayList<Integer> auxList = new ArrayList<>();
        Boolean win = Boolean.FALSE;
        if (row > 2) {
            return win;
        }

        //Calculate front from point OK
        for (int t = row, u = column; t < 6 && u < 7; t++, u++) {
            auxList.add(board[t][u]);
        }

        for (int n = 0; n < auxList.size() - 1 ; n++) {
            Integer total = 0;
            Integer currentToken = 0;
            for (int c = 0; c < 4; c++) {
                if(n + c > auxList.size() - 1)
                    break;
                currentToken = auxList.get(n + c);
                total += (Objects.equals(currentToken, player.getTokenValue())) ? 1 : 0;
            }
            if (total == 4) {
                win = Boolean.TRUE;
                System.out.println("WIN BY " + WinType.DIAGONAL);
                break;
            }
        }
        return win;
    }
    
     private Boolean checkSecondaryDiagonal(Integer row, Integer column, Player player) {
        ArrayList<Integer> auxList = new ArrayList<>();
        Boolean win = Boolean.FALSE;
        if (row > 2) {
            return win;
        }

        //Calculate front from point
        for (int t = row, u = column; t < 6 && u > -1; t++, u--) {
            auxList.add(board[t][u]);
        }

        for (int n = 0; n < auxList.size() - 1 ; n++) {
            Integer total = 0;
            Integer currentToken = 0;
            for (int c = 0; c < 4; c++) {
                if(n + c > auxList.size() - 1)
                    break;
                currentToken = auxList.get(n + c);
                total += (Objects.equals(currentToken, player.getTokenValue())) ? 1 : 0;
            }
            if (total == 4) {
                win = Boolean.TRUE;
                System.out.println("WIN BY " + WinType.SECUNDARY_DIAGONAL);
                break;
            }
        }
        return win;
    }
     
     private void printBoard(){
         
         for(int i = 0; i < this.board.length; i++){
             for(int j = 0; j < this.board[i].length; j++){
                 if(this.board[i][j] == 0)
                     System.out.print("_");
                 if(this.board[i][j] == 1)
                     System.out.print("X");
                 if(this.board[i][j] == 2)
                     System.out.print("O");
             }
             System.out.println("");
         }
     }
}