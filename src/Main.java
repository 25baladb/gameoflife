import processing.core.PApplet;
import java.util.Random;
public class Main extends PApplet {
    private static final int CELL_SIZE = 10;
    private Cell[][] cells;
    private CellState cellState;
    private boolean doEvolve = false;
    public static Main app;
    public final int NUM_ROWS = 50;
    public final int NUM_COLUMNS = 120;

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
        cells = new Cell [NUM_ROWS][NUM_COLUMNS];
        Rules rules = new MooreRules(new int[]{3}, new int[]{2, 3});
        int x = 0;
        int y = 0;
        for(int r = 0; r < cells.length; r++){
            for(int c = 0; c < cells[r].length; c++) {
                Cell m = new Cell(x, y, CELL_SIZE, r, c, cellState, rules);
                x += CELL_SIZE;
                cells[r][c] = m;
                if(r == 0 || c == 0 || r == cells.length - 1 || c == cells[r].length - 1){
                    cellState = CellState.DEAD;
                } else {
                    float i = random(0, 1);
                    if(i <= 0.75) {
                        cellState = CellState.DEAD;
                    } else {
                        cellState = CellState.ALIVE;
                    }
                }
                // 75% of the time the cell will be dead, the rest of the time it will be alive
                //However, cells on the perimeter of the grid must be dead
            }
            x = 0;
            y += CELL_SIZE;
        }
        //makes 2d array of cells, uses nested loop to iterate through all elements,
        // adds instance of each cell to array
    }

    public void draw() {
        for (int r = 0; r < cells.length; r++) {
            for (int c = 0; c < cells[r].length; c++) {
                cells[r][c].display();
            }
        }
        //iterates through each element and calls display in cell to show each one
        if(doEvolve == true){
            evolve();
            applyRules();
        }
        //uses boolean to go to next generation and reapply the rules
    }
    public void applyRules() {
        for (int r = 1; r < cells.length - 1; r++) {
            for (int c = 1; c < cells[r].length - 1; c++) {
                cells[r][c].applyRules(cells);
            }
        }
        //applies rules to each cell through a nested loop
    }

    public void evolve(){
        for (int r = 0; r < cells.length; r++) {
            for (int c = 0; c < cells[r].length; c++) {
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
