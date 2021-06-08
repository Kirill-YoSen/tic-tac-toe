package com.company;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class GAme {
    private int[][] field = new int[3][3];
    private int player = 1;
    private int opponent = -1;
    private int moves = 9;
    private boolean running = true;
    private int winner = 0;
    private ArrayList<Integer> avaliableMoves = new ArrayList<Integer>();

    private void giveValues() {
        for (int i = 0; i < 9; i++) {
            this.avaliableMoves.add(i);
        }
    }

    private void Winner() {
        if (field[0][0] != 0 & field[0][0] == field[0][1] && field[0][1] == field[0][2]) {
            this.winner = field[0][0];
        } else if (field[1][1] != 0 & field[1][0] == field[1][1] && field[1][1] == field[1][2]) {
            this.winner = field[1][0];
        } else if (field[2][1] != 0 & field[2][0] == field[2][1] && field[2][1] == field[2][2]) {
            this.winner = field[2][1];
        } else if (field[0][0] != 0 & field[0][0] == field[1][0] && field[1][0] == field[2][0]) {
            this.winner = field[0][0];
        } else if (field[0][1] != 0 & field[0][1] == field[1][1] && field[1][1] == field[2][1]) {
            this.winner = field[1][1];
        } else if (field[0][2] != 0 & field[0][2] == field[1][2] && field[1][2] == field[2][2]) {
            this.winner = field[0][2];
        } else if (field[0][0] != 0 & field[0][0] == field[1][1] && field[1][1] == field[2][2]) {
            this.winner = field[0][0];
        } else if (field[1][1] != 0 & field[0][2] == field[1][1] && field[1][1] == field[2][0]) {
            this.winner = field[1][1];
        }

    }

    private void End() {
        if (this.winner != 0 || this.moves == 0) {
            this.running = false;
            switch (winner) {
                case 1:
                    System.out.println("Player is a winner!");
                    break;
                case -1:
                    System.out.println("Bot is a winner!");
                    break;
                    default:
                    System.out.println("Draw!");
                    break;
            }
        } else  {
            this.moves--;
        }
    }

    private void PrintField() {
        System.out.println("Moves left: "+ moves);
        Integer move = 0;
        for (int col = 0; col < field.length; col++) {
            for (int row = 0; row < field[col].length; row++) {
                if (field[col][row] == 1) {
                    System.out.printf(" X");
                } else if (field[col][row] == -1) {
                    System.out.printf(" O");
                } else {
                    System.out.printf(" .");
                }
            }
            System.out.print("       ");
            for (int row = 0; row < field[col].length; row++) {
                //pirint avaliable moves
                if (field[col][row] != 0) {
                    System.out.printf(" .");
                } else {
                    System.out.printf(" %d", move);
                }
                move++;
            }
            System.out.printf("\n\n");
        }
        System.out.println();
    }

    public void BotMove() {
        Random random = new Random();
        for (;;) {
            Integer move = random.nextInt(avaliableMoves.get(avaliableMoves.size()-1)+1);
            if (avaliableMoves.contains(move)) {
                this.avaliableMoves.remove(move);
                int row, col;
                row = move / 3;
                col = move;
                while (col >= 3) {
                    col-=3;
                }
                this.field[row][col] = -1;
                return;
            }
        }
    }

    public void AskUserForMove() {
        Scanner scanner = new Scanner(System.in);
        for (;;) {
            Integer move;
            System.out.print("Enter your move: ");
            String str = scanner.nextLine();
            System.out.println();
            try {
                move = Integer.parseInt(str);
            } catch (Exception e) {
                continue;
            }

            if (avaliableMoves.contains(move)) {
                int row, col;
                row = move / 3;
                col = move;
                while (col >= 3) {
                    col-=3;
                }
                this.field[row][col] = 1;

                avaliableMoves.remove(move);
                //System.out.println(avaliableMoves.toString());
                return;
            } else {
                System.out.println("Invalid move");
            }
        }
    }

    public void Game() {
        giveValues();
        while (running) {
            PrintField();
            AskUserForMove();
            BotMove();
            Winner();
            End();
        }
        //PrintField();
    }

}
