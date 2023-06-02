import processing.core.PApplet;

import java.util.Random;
public class Main extends PApplet {
    private static final int CELL_SIZE = 10;
    private static final int NUM_ROWS = 50;
    private static final int NUM_COLUMNS = 120;
    private Cell[][] cells = new Cell[NUM_ROWS][NUM_COLUMNS];
    private CellState cellState;
    private boolean doEvolve = false;
    public static Main app;

    public static void main(String[] args){
        PApplet.main("Main");
    }

    public Main(){
        super();
        app = this;
    }

    public void settings(){
        size(NUM_COLUMNS * CELL_SIZE,NUM_ROWS * CELL_SIZE);
    }

    public void setup(){
        Rules rules = new MooreRules(new int[]{3}, new int[]{2, 3});
        for(int rows = 0; rows < NUM_ROWS; rows++){
            for(int cols = 0; cols < NUM_COLUMNS; cols++) {
                if(rows == 0 || cols == 0 || rows == cells.length - 1 || cols == cells[0].length - 1){
                    cellState = CellState.DEAD;
                } else {
                    float i = random(0, 1);
                    if(i <= 0.75) {
                        cellState = CellState.DEAD;
                    } else {
                        cellState = CellState.ALIVE;
                    }
                // 75% of the time the cells will be dead, the rest of the time it will be alive
                //However, cells on the perimeter of the grid must be dead
            }
                Cell c = new Cell(cols * CELL_SIZE, rows * CELL_SIZE, CELL_SIZE, rows, cols, cellState, rules);
                cells[rows][cols] = c;
            }
        //makes 2d array of cells, uses nested loop to iterate through all elements,
        // adds instance of each cell to array
        }
    }

    public void draw(){
            if(doEvolve){
                applyRules();
                evolve();
            }
            //uses boolean to go to next generation and reapply the rules

        for (int r = 0; r < NUM_ROWS; r++) {
            for (int c = 0; c < NUM_COLUMNS; c++) {
                cells[r][c].display();
            }
        }
        //iterates through each element and calls display in cell to show each one
    }
    public void applyRules() {
        for (int r = 1; r < NUM_ROWS - 1; r++) {
            for (int c = 1; c < NUM_COLUMNS - 1; c++) {
                cells[r][c].applyRules(cells);
            }
        }
        //applies rules to each cell through a nested loop
    }

    public void evolve(){
        for (int r = 0; r < NUM_ROWS; r++) {
            for (int c = 0; c < NUM_COLUMNS; c++) {
                cells[r][c].evolve();
            }
        }
        //evolves each cell for a new generation through a nested loop
    }

    public void mouseClicked(){
        cells[mouseY/CELL_SIZE][mouseX/CELL_SIZE].handleClick();
        //gets coordinates of cell which was clicked (and therefore whose state was changed)
    }

    public void keyPressed(){
        doEvolve = !doEvolve;
        //toggles between whether cells should go to next generation or not
    }
}
