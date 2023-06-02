/**
 Class Cell displays and controls individual cells (one block in the grid)
 */
public class Cell {

    private int x;
    private int y;

    private int size;
    private int row;
    private int column;
    private CellState cellState;
    private Rules rules;

    /**
     Constructs a Cell using coordinates, size, location on the grid, its state,
     and the rules
     * @param x x-coordinate of cell
     * @param y y-coordinate of cell
     * @param size dimensions of the cell (only 1 var because each cell is a square)
     * @param row row in the 2d array of cells
     * @param column column in the 2d array of cells
     * @param cellState enum state of cell
     * @param rules rules that all cells use to evolve
     */
    public Cell(int x, int y, int size, int row, int column, CellState cellState, Rules rules){
        super();
        this.x = x;
        this.y = y;
        this.size = size;
        this.row = row;
        this.column = column;
        this.cellState = cellState;
        this.rules = rules;
    }

    /**
     draws cells as a square and displays live/dying cells as white and
     dead cells as black by accessing a cell's state
     */
    public void display(){
        if(cellState == CellState.ALIVE || cellState == CellState.WILL_DIE){
            Main.app.fill(0);
        } else if(cellState == CellState.DEAD || cellState == CellState.WILL_REVIVE){
            Main.app.fill(255);
        }
        Main.app.rect(x, y, size, size);
    }

    /**
     toggles a clicked cell's state between alive and dead
     */
    public void handleClick() {
        if (cellState == CellState.DEAD || cellState == CellState.WILL_REVIVE) {
                cellState = CellState.ALIVE;
        } else if(cellState == CellState.ALIVE || cellState == CellState.WILL_DIE){
                cellState = CellState.DEAD;
        }
    }

    /**
     gets number of live neighbors and changes cell's state according to the rules
     @param cells 2d array of cells to count neighbors of
     */
    public void applyRules(Cell[][] cells){
        int liveNeighbors = countLiveNeighbors(cells);
        cellState = rules.applyRules(cellState, liveNeighbors);
    }

    /**
     changes cells with the state of will_die to dead and will_revive to alive
     */
    public void evolve(){
        if (cellState == CellState.WILL_DIE) {
            cellState = CellState.DEAD;
        } else if (cellState == CellState.WILL_REVIVE) {
            cellState = CellState.ALIVE;
        }
    }

    /**
     makes a 3x3 square to iterate through moore neighbors of a cell and
     counts the amount of live cells. subtracts 1 if center cell is alive
     * @param cells 2d array of cells to count live neighbors of
     * @return the number of alive neighbors
     */
    private int countLiveNeighbors(Cell[][] cells){
        int alive = 0;
        for (int h = -1; h <= 1; h++){
            for (int i = - 1; i <= 1; i++){
                if(cells[row+h][column+i].cellState == CellState.ALIVE || cells[row+h][column+i].cellState == CellState.WILL_DIE){
                    alive++;
                }
            }
        }
        if(cellState == CellState.ALIVE){
            alive--;
        }
        System.out.println(alive);
        return alive;
    }

}
