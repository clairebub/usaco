import java.io.File;
import java.util.*;

public class Tractor {

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

    static class PosComparator implements Comparator<Pos> {
        @Override
        public int compare(Pos o1, Pos o2) {
            if (costAtPositions.get(o1) < costAtPositions.get(o2)) {
                return -1;
            } else if (costAtPositions.get(o1) > costAtPositions.get(o2)) {
                return + 1;
            } else {
                return 0;
            }
        }
    }

    // keeping track of positionsToExplore in a BFS fashion and
    // cost, which is the number of bales to remove, at the visited position
    static HashMap<Pos, Integer> costAtPositions = new HashMap<>();
    static PriorityQueue<Pos> positionsToExplore = new PriorityQueue<>(100, new PosComparator());

    static void run() throws Exception {
        Scanner fin = new Scanner(new File("tractor.in"));
        String line = fin.nextLine();
        StringTokenizer st = new StringTokenizer(line);
        int N = Integer.parseInt(st.nextToken());
        int X = Integer.parseInt(st.nextToken());
        int Y = Integer.parseInt(st.nextToken());
        // grid[i][j] == 0 means its empty, grid[i][j] == 1 means its bale
        int maxBaleX = 0;
        int maxBaleY = 0;
        int[][] grid = new int[1000][1000];
        for (int i = 0; i < 1000; i++) {
            for (int j = 0; j < 1000; j++) {
                grid[i][j] = 0;
            }
        }
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(fin.nextLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            grid[x-1][y-1] = 1;
            if (x > maxBaleX) {
                maxBaleX = x;
            }
            if (y > maxBaleY) {
                maxBaleY = y;
            }
        }


        Pos pos = new Pos(X-1, Y-1);
        costAtPositions.put(pos, 0);
        positionsToExplore.add(pos);

        while (!positionsToExplore.isEmpty()) {
            pos = positionsToExplore.remove();
            int startingCost = costAtPositions.get(pos);
//            System.out.println(pos + ", " + startingCost);
            // check if it's what we are looking for
            if (pos.row == 0 && pos.col == 0) {
                System.out.println("Got back to origin with cost of " + startingCost);
            }
            // check left
            if (pos.col - 1 >= 0) {
                visitNewPos(new Pos(pos.row, pos.col-1), startingCost, grid, costAtPositions, positionsToExplore);
            }
            // check right
            if (pos.col + 1 < maxBaleX) {
                visitNewPos(new Pos(pos.row, pos.col+1), startingCost, grid, costAtPositions, positionsToExplore);
            }
            // check upper
            if (pos.row - 1 >= 0) {
                visitNewPos(new Pos(pos.row-1, pos.col), startingCost, grid, costAtPositions, positionsToExplore);
            }
            // check lower
            if (pos.row + 1 < maxBaleY) {
                visitNewPos(new Pos(pos.row+1, pos.col), startingCost, grid, costAtPositions, positionsToExplore);
            }
        }
    }

    static void visitNewPos(Pos pos, int startingCost, int[][] grid, Map<Pos, Integer> costAtPos, Queue<Pos> positionsToExplore) {
        int newCost = startingCost;
        // increment the cost if it contains bale
        if (grid[pos.row][pos.col] == 1) {
            newCost += 1;
        }
        // add the new position to the list of positions to explore later if and only if it has a new value of cost
        // you assign a new value of cost if it's visited for the first time or get a lower cost
        if (!costAtPos.containsKey(pos) || newCost < costAtPos.get(pos)) {
            costAtPos.put(pos, newCost);
            positionsToExplore.add(pos);
        }
    }
}
