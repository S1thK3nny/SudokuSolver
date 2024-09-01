import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Sudoku {
    private final int[][] board;
    private final Map<Integer, Set<Integer>> subgridMissingNumbers;

    public Sudoku() {
        board = new int[9][9];
        subgridMissingNumbers = new HashMap<>();
        initializeSubgridMissingNumbers();
        readSudokuFromFile(System.getProperty("user.dir") + "\\src\\example.txt");
    }

    public boolean solveSudoku() {
        // System.out.println(this);
        int[] field = findEmptyCell();
        if(field[0] == -1 && field[1] == -1) return true;

        for (int i = 1; i <= 9; i++) {
            if(isValidCell(field[0], field[1], i)) {
                setCell(field[0], field[1], i);
                if (solveSudoku()) {
                    return true;
                }
                removeCell(field[0], field[1]);
            }
        }
        return false;
    }

    // Find the subgrid with the least missing numbers, then find the first empty cell in that subgrid.
    private int[] findEmptyCell() {
        int min = -1;
        for (int i = 0; i < subgridMissingNumbers.size(); i++) {
            if (subgridMissingNumbers.get(i).size() == 0) continue;

            // If 'min' has not been set yet, or the current subgrid has fewer missing numbers, update 'min'.
            if (min == -1 || subgridMissingNumbers.get(i).size() < subgridMissingNumbers.get(min).size()) min = i;
        }

        if (min == -1) return new int[]{-1, -1};

        int[] subgridStart = getRowCol(min);

        for (int i = subgridStart[0]; i < subgridStart[0] + 3; i++) {
            for (int j = subgridStart[1]; j < subgridStart[1] + 3; j++) {
                if(isEmptyCell(i, j)) return new int[]{i, j};
            }
        }

        return new int[]{-1, -1};
    }

    private void initializeSubgridMissingNumbers() {
        for (int i = 0; i < 9; i++) {
            subgridMissingNumbers.put(i, new HashSet<>(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9)));
        }
    }

    private int getSubgrid(int row, int col) {
        if((row<0 || row>8) || (col<0 || col>8)) return 0;
        return (row / 3) * 3 + col / 3;
    }

    public int[] getRowCol(int subgridIndex) {
        if(subgridIndex<0 || subgridIndex>8) return new int[]{0,0};
        return new int[]{(subgridIndex / 3) * 3, (subgridIndex % 3) * 3};
    }

    public void setCell(int row, int col, int val) {
        int sg = getSubgrid(row, col);
        // Allow val == 0 to allow for initializing the grid itself
        if(subgridMissingNumbers.get(sg).contains(val) || val == 0) {
            board[row][col] = val;
            subgridMissingNumbers.get(sg).remove(val);
            return;
        }
        System.out.println("Tried placing already existing number in subgrid, please check it out!");
        System.out.println("Row: " + row + "\tCol: " + col + "\tValue: " + val);
    }

    public void removeCell(int row, int col) {
        if (board[row][col] == 0) return;

        int sg = getSubgrid(row, col);
        subgridMissingNumbers.get(sg).add(board[row][col]);
        board[row][col] = 0;
    }

    public int getCell(int row, int col) {
        return board[row][col];
    }

    public boolean isEmptyCell(int row, int col) {
        return board[row][col] == 0;
    }

    // Check rows and columns for duplicates, then check if the number is missing in the 3x3 subgrid
    public boolean isValidCell(int row, int col, int val) {
        for (int i = 0; i < 9; i++) {
            if(board[row][i] == val || board[i][col] == val) return false;
        }

        return subgridMissingNumbers.get(getSubgrid(row, col)).contains(val);
    }

    private void readSudokuFromFile(String fileName) {
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            int row = 0;
            while ((line = br.readLine()) != null && row < 9) {
                for (int col = 0; col < 9; col++) {
                    setCell(row, col, Character.getNumericValue(line.charAt(col)));
                }
                row++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void printSubgridsMissingNumbers() {
        for (int i = 0; i < 9; i++) {
            System.out.println("Subgrid " + (i+1) + ": " + subgridMissingNumbers.get(i));
        }
        System.out.print("\n");
    }

    public void printSubgridMissingNumbers(int subgrid) {
        if(subgrid<0 || subgrid>8) {
            System.out.println("Subgrid: Invalid!");
            return;
        }

        System.out.println("Subgrid " + (subgrid+1) + ": " + subgridMissingNumbers.get(subgrid));
        System.out.print("\n");
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int row = 0; row < 9; row++) {
            if (row % 3 == 0 && row != 0) {
                sb.append("------+-------+------\n");
            }
            for (int col = 0; col < 9; col++) {
                if (col % 3 == 0 && col != 0) {
                    sb.append("| ");
                }
                int value = getCell(row, col);
                sb.append(value == 0 ? "." : value).append(" ");
            }
            sb.append("\n");
        }
        return sb.toString();
    }
}