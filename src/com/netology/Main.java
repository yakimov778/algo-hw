package com.netology;

import java.util.Random;

public class Main {

    private static final int N = 10;
    private static final int X0 = 7;
    private static final int Y0 = 8;

    public static void main(String[] args) {
        Random random = new Random();
        char[][] field = new char[N][N];
        field[0][0] = 'Щ';
        field[X0][Y0] = 'Ч';

        for (char[] row : field) {
            row[random.nextInt(N)] = '*';
            for (char cell : row) {
                if (cell == 'Щ') {
                    cell = '-';
                }
            }
        }

        field[random.nextInt(N)][random.nextInt(N)] = '*';

        System.out.println("An initial field");
        formatField(field, X0, Y0);
        empty();

        findPath(field, X0, Y0);
        empty();
        System.out.println("A field with a path to the puppy");
        formatField(field, X0, Y0);

    }

    public static void empty() {
        System.out.println("");
    }

    public static char[][] formatField(char[][] field, int X0, int Y0) {

        for (int j = 0; j < N; j++) {
            System.out.println();
            for (int i = 0; i < N; i++) {
                if (field[i][j] == '\u0000') {
                    field[i][j] = '-';
                }
                System.out.print(field[i][j] + "\t");
            }
        }
        return field;
    }

    private static void findPath(char[][] field, int x0, int y0) {
        char[][] path = new char[N][N];
        int x = x0;
        int y = y0;
        while (x != 0 && y != 0) {
            char direction = searchLastCell(field, x, y, path);
            if (direction == 'N') {
                System.out.println("There is no such way");
                break;
            } else if (direction == 'U') {
                path[x][y] = 'U';
                y -= 1;
            } else if (direction == 'L') {
                path[x][y] = 'L';
                x -= 1;
            }
        }

        for (y = 0; y < N; y++) {
            for (x = 0; x < N; x++) {
                if (x == x0 && y == y0) {
                    field[x][y] = 'Ч';
                } else if (path[x][y] == 'U' || path[x][y] == 'L') {
                    field[x][y] = 'x';
                } else {
                    field[x][y] = field[x][y];
                }
            }
        }
    }

    private static char searchLastCell(char[][] field, int x, int y, char[][] memory) {
        if (memory[x][y] == 'N' || memory[x][y] == 'L' || memory[x][y] == 'U') {
            return memory[x][y];
        }
        if (x > 0) {
            int leftX = x - 1;
            int leftY = y;

            if (leftX == 0 && leftY == 0) {
                memory[x][y] = 'L';
                return 'L';
            }
            if (field[leftX][leftY] != '*') {
                if (searchLastCell(field, leftX, leftY, memory) != 'N') {
                    memory[x][y] = 'L';
                    return 'L';
                }
            }
        }
        if (y > 0) {
            int upX = x;
            int upY = y - 1;

            if (upX == 0 && upY == 0) {
                memory[x][y] = 'U';
                return 'U';
            }
            if (field[upX][upY] != '*') {
                if (searchLastCell(field, upX, upY, memory) != 'N') {
                    memory[x][y] = 'U';
                    return 'U';
                }
            }
        }
        memory[x][y] = 'N';
        return 'N';
    }

}
