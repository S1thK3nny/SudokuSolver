# Sudoku Solver

This project is a Java-based Sudoku solver that **reads** a Sudoku puzzle from a file, **solves** it using backtracking, and **displays** the solved puzzle. The program prioritizes subgrids with the least missing numbers to find empty cells, making the solving process more **efficient**.


## Features

-   **File Input**: Reads a 9x9 Sudoku puzzle from a text file.
-   **Backtracking Algorithm**: Efficiently solves the Sudoku puzzle using backtracking, considering subgrids with the fewest missing numbers first.
-   **Subgrid Tracking**: Keeps track of missing numbers in each subgrid for efficient validation and placement of numbers.
-   **User-Friendly Output**: Displays the Sudoku grid in a readable format.

## How It Works

1. The program reads the puzzle from a file (`example.txt`).
2. It identifies empty cells and places valid numbers using the backtracking algorithm.
3. The puzzle is solved and displayed.

## Example

Before:

    1 . 4 | . 7 . | . . 9 
    . . 7 | . . 9 | 5 . . 
    2 . . | 5 . 1 | . . 3 
    ------+-------+------
    . 2 . | 9 . 4 | . 3 . 
    . . 8 | . . 7 | . . . 
    . . 5 | . . 3 | 7 . 8 
    ------+-------+------
    . . . | . . 2 | . . 4 
    . 6 3 | . . 5 | . . . 
    8 . . | . 9 . | . . . 

After:

    1 5 4 | 3 7 8 | 2 6 9 
    6 3 7 | 4 2 9 | 5 8 1 
    2 8 9 | 5 6 1 | 4 7 3 
    ------+-------+------
    7 2 6 | 9 8 4 | 1 3 5 
    3 1 8 | 2 5 7 | 9 4 6 
    4 9 5 | 6 1 3 | 7 2 8 
    ------+-------+------
    5 7 1 | 8 3 2 | 6 9 4 
    9 6 3 | 7 4 5 | 8 1 2 
    8 4 2 | 1 9 6 | 3 5 7 
