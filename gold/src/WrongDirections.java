import java.io.*;
import java.util.*;

public class WrongDirections {

    static HashMap<Integer, HashSet<Integer>>[][] positions;
    static String moves;
    public static void main(String[] args) throws Exception {
        Scanner fin = new Scanner(new File("wrongdirections.in"));
        moves = fin.nextLine();
        int N = moves.length();
        // N is up to 100K, so NxN is 10000 million, which is 10 billion, we won't be able to
        // accommodate an NxN matrix since it will take up too much RAM.
        // I could use a hash map with Point as a key with boolean as value to indicate whether that Point is a
        // legit coordinate, that will save a lot of RAM but someone won't like it. So using an alternative, which
        // is a hash map from integer to integer, each entry indicate a coordinate of (x, y), x is the key, y is the
        // value.

        // each move, with or without errors to make the possible positions
        positions = new HashMap[N+1][2];
        for (int i = 0; i <= N; i++) {
            for (int j = 0; j < 2; j++) {
                if (i == 0) {
                    positions[i][j] = new HashMap<>();
                    positions[i][j].put(0, new HashSet<>(Arrays.asList(0)));
                } else {
                    positions[i][j] = null;
                }
            }
        }

        // start from the move indicated by the last character on the string with 1 errors left to make
        updatePositions(N, 1);
        System.out.println(positions[N][1].size());
    }

    static void updatePositions(int i, int errorsLeft) {
        if (i == 0) {
            return;
        }
        if (positions[i][errorsLeft] != null) {
            return;
        }
        Character c = moves.charAt(i-1);
        HashMap<Integer, HashSet<Integer>> updatedPositions = new HashMap<>();
        if (errorsLeft == 0) {
            updatePositions(i - 1, 0);
            makeMoveAndUpdateReachedPositions(c, positions[i-1][0], updatedPositions);
        } else {
            // if there's error left to make, you can always use the quota for error here so
            // make your moves based off your previous hasn't made an error
            updatePositions(i - 1, 0);
            if (c == 'F') {
                makeMoveAndUpdateReachedPositions('R', positions[i-1][0], updatedPositions);
                makeMoveAndUpdateReachedPositions('L', positions[i-1][0], updatedPositions);
            }
            if (c == 'R') {
                makeMoveAndUpdateReachedPositions('F', positions[i-1][0], updatedPositions);
                makeMoveAndUpdateReachedPositions('L', positions[i-1][0], updatedPositions);
            }
            if (c == 'L') {
                makeMoveAndUpdateReachedPositions('R', positions[i-1][0], updatedPositions);
                makeMoveAndUpdateReachedPositions('F', positions[i-1][0], updatedPositions);
            }
            // if it's not the first one, you have the choice to not make the error to pass it down
            if (i != 1) {
                updatePositions(i - 1, 1);
                makeMoveAndUpdateReachedPositions(c, positions[i-1][1], updatedPositions);
            }
        }
        positions[i][errorsLeft] = updatedPositions;
        return;

    }

    static void makeMoveAndUpdateReachedPositions(
            Character c,
            HashMap<Integer, HashSet<Integer>> start_positions,
            HashMap<Integer, HashSet<Integer>> end_positions) {
        for (int x : start_positions.keySet()) {
            for (int y : start_positions.get(x)) {
                if (!end_positions.containsKey(x)) {
                    end_positions.put(x, new HashSet<>());
                }
                // adding the starting position
                HashSet ySet = end_positions.get(x);
                ySet.add(y);
                if (c == 'R') {
                    x += 1;
                } else if (c == 'L') {
                    x -= 1;
                } else if (c == 'F') {
                    y += 1;
                }
                if (!end_positions.containsKey(x)) {
                    end_positions.put(x, new HashSet<>());
                }
                // adding the ending position
                ySet = end_positions.get(x);
                ySet.add(y);
            }
        }
        return;
    }
}
