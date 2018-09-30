import java.io.File;
import java.io.PrintWriter;
import java.util.*;

public class Gravity {
    static class Pos {
        Pos(int row, int col) {
            this.row = row;
            this.col = col;
        }

        int row;
        int col;

        @Override
        public boolean equals(Object obj) {
            if (!(obj instanceof Pos)) {
                return false;
            }
            Pos that = (Pos) obj;
            return that.col == this.col && that.row == this.row;
        }

        @Override
        public int hashCode() {
            return Objects.hash(row, col);
        }

        @Override
        public String toString() {
            return row + ", " + col;
        }
    }

    // The positions the cow will end up if it moves left
    static Pos moveLeft(Pos pos, char[][] grid, Map<Pos, Integer> flipsAtPositions) {
        int row = pos.row;
        int col = pos.col - 1;
        // is col index out-of-bound?
        if (col < 0) {
            return null;
        }
        // is it occupied
        if (grid[row][col] == '#') {
            return null;
        }
        // it is on solid ground?
        int gravity = 1;
        if (flipsAtPositions.get(pos) % 2 == 1) {
            gravity = -1;
        }
        return followGravity(pos, grid, gravity);
    }

    static Pos moveRight(Pos pos, char[][] grid, Map<Pos, Integer> flipsAtPositions) {
        int row = pos.row;
        int col = pos.col + 1;
        // is col index out-of-bound?
        if (col >= grid[0].length) {
            return null;
        }
        // is it occupied
        if (grid[row][col] == '#') {
            return null;
        }
        // it is on solid ground?
        int gravity = 1;
        if (flipsAtPositions.get(pos) % 2 == 1) {
            gravity = -1;
        }
        return followGravity(new Pos(row, col), grid, gravity);
    }

    static Pos followGravity(Pos pos, char[][] grid, int gravity) {
        int row = pos.row;
        while(row + gravity >= 0 && row + gravity < grid.length && grid[row+gravity][pos.col] != '#') {
            row += gravity;
        }
        if (row > 0 && row < grid.length - 1) {
            return new Pos(row, pos.col);
        } else {
            return null;
        }
    }

    static void run() throws Exception {
        // read the input
        Scanner fin = new Scanner(new File("gravity.in"));
        String line = fin.nextLine();
        StringTokenizer st = new StringTokenizer(line);
        int num_rows = Integer.parseInt(st.nextToken());
        int num_cols = Integer.parseInt(st.nextToken());
        Pos from = new Pos(-1, -1);
        Pos to = new Pos(-1, -1);
        char grid[][] = new char[num_rows][num_cols];
        for (int i = 0; i < num_rows; i++) {
            line = fin.nextLine();
            for (int j = 0; j < num_cols; j++) {
                grid[i][j] = line.charAt(j);
                if (grid[i][j] == 'C') {
                    from = new Pos(i, j);
                }
                if (grid[i][j] == 'D') {
                    to = new Pos(i, j);
                }
            }
        }

        Map<Pos, Integer> flipsAtPositions = new HashMap<>();
        Queue<Pos> positionsToExplore = new LinkedList<Pos>();
        // start exploring until you reach the 'to' position
        // having a map contain the number of flips at each position
        positionsToExplore.add(from);
        flipsAtPositions.put(from, 0);
        int smallestFlips = -1;
        while(!positionsToExplore.isEmpty()) {
            Pos pos = positionsToExplore.remove();
            System.out.println(pos);
            Pos left = moveLeft(pos, grid, flipsAtPositions);
            if (left != null) {
                if (left.equals(to)) {
                    // reached the destination, no need for further exploration, update the minimum if conditions are met
                    if (smallestFlips < 0 || flipsAtPositions.get(pos) < smallestFlips) {
                        smallestFlips = flipsAtPositions.get(pos);
                    }
                } else {
                    // otherwise, continue the exploration from left if it hasn't been visited before or it has been
                    // visited but number of flips are smaller now
                    if (!flipsAtPositions.containsKey(left) || flipsAtPositions.get(left) > flipsAtPositions.get(pos)) {
                        positionsToExplore.add(left);
                        flipsAtPositions.put(left, flipsAtPositions.get(pos));
                    }
                }
            }
            Pos right = moveRight(pos, grid, flipsAtPositions);
            if (right != null) {
                if (right.equals(to)) {
                    // reached the destination, no need for further exploration, update the minimum if conditions are met
                    if (smallestFlips < 0 || flipsAtPositions.get(pos) < smallestFlips) {
                        smallestFlips = flipsAtPositions.get(pos);
                    }
                } else {
                    // otherwise, continue the exploration from there if hasn't been visited before or it has been visisted
                    // but the number of flips are smaller now
                    if (!flipsAtPositions.containsKey(right) || flipsAtPositions.get(right) > flipsAtPositions.get(pos)) {
                        positionsToExplore.add(right);
                        flipsAtPositions.put(right, flipsAtPositions.get(pos));
                    }
                }
            }
            int gravity = 1;
            if (flipsAtPositions.get(pos) % 2 == 1) {
                gravity = -1;
            }
            Pos flipped = followGravity(pos, grid, -gravity);
            if (flipped != null) {
                if (flipped.equals(to)) {
                    // reached the destination, no need for further exploration, update the minimum if conditions are met
                    if (smallestFlips < 0 || flipsAtPositions.get(pos) + 1 < smallestFlips) {
                        smallestFlips = flipsAtPositions.get(pos) + 1;
                    }
                } else {
                    // otherwise, continue the exploration from there if hasn't been visited before or it has been visisted
                    // but the number of flips are smaller now
                    if (!flipsAtPositions.containsKey(flipped) || flipsAtPositions.get(flipped) > flipsAtPositions.get(pos) + 1) {
                        positionsToExplore.add(flipped);
                        flipsAtPositions.put(flipped, flipsAtPositions.get(pos) + 1);
                    }
                }
            }
        }
        PrintWriter fout = new PrintWriter(System.out);
        fout.println(smallestFlips);
        fout.close();
    }
}
