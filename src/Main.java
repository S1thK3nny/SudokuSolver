public class Main {
    public static void main(String[] args) {
        Sudoku sud = new Sudoku();
        System.out.println(sud);
        long startTime = System.currentTimeMillis();
        if(sud.solveSudoku()) {
            long estimatedTime = System.currentTimeMillis() - startTime;
            System.out.println(sud);
            System.out.println("This sudoku was solved in " + estimatedTime + " seconds!");
        }
        else {
            System.out.println("There is no solution for this sudoku. (Sad!)");
        }
    }
}