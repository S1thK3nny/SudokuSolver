public class Main {
    public static void main(String[] args) {
        Sudoku sud = new Sudoku();
        System.out.println(sud);
        long startTime = System.currentTimeMillis();
        if(sud.solveSudoku()) {
            long estimatedTime = System.currentTimeMillis() - startTime;
            double estimatedTimeInSeconds = estimatedTime / 1000.0;
            System.out.println(sud);
            System.out.println("This sudoku was solved in " + estimatedTimeInSeconds + " seconds!");
        }
        else {
            System.out.println("There is no solution for this sudoku. (Sad!)");
        }
    }
}