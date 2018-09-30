import java.io.*;
import java.util.*;

public class LaserPhone {
    static char[][] grid = null;
    static int[][] cost = null;
    static int W = -1;
    static int H = -1;

    static void run() throws Exception {
        Scanner fin = new Scanner(new File("laserphone.in"));
        String line = fin.nextLine();
        StringTokenizer st = new StringTokenizer(line);
        W = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());
        grid = new char[H][W];
        cost = new int[H][W];
        int start_row = -1;
        int start_col = -1;
        for (int row = 0; row < H; row++) {
            line = fin.nextLine();
            for (int col = 0; col < W; col++) {
                grid[row][col] = line.charAt(col);
                cost[row][col] = -1;
                if (grid[row][col] == 'C') {
                    start_row = row;
                    start_col = col;
                }
            }
        }

        // start updating the cost matrix
        cost[start_row][start_col] = 0;
        System.out.println(String.format("starting at %d %d", start_row, start_col));
        // upper
        visit2(start_row-1, start_col, 0, true);
        // lower
        visit2(start_row+1, start_col, 0, true);
        // left
        visit2(start_row, start_col-1, 0, false);
        // lower
        visit2(start_row, start_col+1, 0, false);

        for (int row = 0; row < H; row++) {
            for (int col = 0; col < W; col++) {
                if(grid[row][col] == 'C') {
                    System.out.println(cost[row][col]);
                }
            }
        }
    }

    //
    // When you visit a cell, you do the following:
    // 1) check if the cell coordinate is legit
    // 2) if legit, set the cost of the cell is it makes it lower
    // 3) if it reset the cost, you will visit your neighbors
    //    a. when visit your neighbor, you pass the inertia and the cost
    //    b. when visit a neighbor with same inertia, same cost; otherwise, +1 cost
    /**
     *
     * @param row row coordinate of the cell
     * @param col column coordinate of the cell
     * @param new_cost cost of reaching the cell
     * @param row_inertia true for row inertia and false for column inertia
     */
    static void visit2(int row, int col, int new_cost, boolean row_inertia) {
        if (row < 0 || row >= H || col < 0 || col >= W) {
            return;
        }
        if (grid[row][col] == '*') {
            return;
        }
        if (cost[row][col] != -1 && new_cost >= cost[row][col]) {
            return;
        }
        System.out.println(String.format("%d %d %d %b", row, col, new_cost, row_inertia));
        // Now you come across a better way to reach here
        cost[row][col] = new_cost;
        // now explore your neighborhood
        if (row_inertia) {
            visit2(row - 1, col, new_cost, true);
            visit2(row + 1, col, new_cost, true);
            visit2(row, col - 1, new_cost + 1, false);
            visit2(row, col + 1, new_cost + 1, false);
        } else {
            visit2(row - 1, col, row_inertia ? new_cost : new_cost + 1, true);
            visit2(row + 1, col, row_inertia ? new_cost : new_cost + 1, true);
            visit2(row, col - 1, row_inertia ? new_cost + 1 : new_cost, false);
            visit2(row, col + 1, row_inertia ? new_cost + 1 : new_cost, false);

        }
    }
}
