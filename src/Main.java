public class Main {
    public static void main(String[] args) {
        Sudoku sud = new Sudoku();
        System.out.println(sud);
        sud.solveSudoku();
        System.out.println(sud);
    }
}