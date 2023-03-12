public class Cell {

    private int x;
    private int y;

    private int size;
    private int row;
    private int column;
    private CellState cellstate;
    private Rules rules;

    public Cell(int x, int y, int size, int row, int column, CellState cellstate, Rules rules){
        super();
        this.x = x;
        this.y = y;
        this.size = size;
        this.row = row;
        this.column = column;
        this.rules = rules;
    }

    public void display(){
        if(cellstate == CellState.ALIVE || cellstate == CellState.WILL_DIE){
            Main.app.fill(0);
        } else {
            Main.app.fill(255);
        }
        Main.app.rect(x, y, size, size);
    }

    public void handleClick() {
        if (cellstate == CellState.ALIVE) {
                cellstate = CellState.DEAD;
        } else {
                cellstate = CellState.ALIVE;
        }
    }

    public void applyRules(Cell[][] cells){
        int liveNeighbors = countLiveNeighbors(cells);
        cellstate = rules.applyRules(cellstate, liveNeighbors);
    }


    public void evolve(){
        if (cellstate == CellState.WILL_DIE) {
            cellstate = CellState.DEAD;
        } else if (cellstate == CellState.WILL_REVIVE) {
            cellstate = CellState.ALIVE;
        }
    }

    private int countLiveNeighbors(Cell[][] grass){
        int alive = 0;
        for (int h = -1; h <= 1; h++){
            for (int i = - 1; i <= 1; i++){
                if(grass[row+h][column+i].cellstate == CellState.ALIVE || grass[row+h][column+i].cellstate == CellState.WILL_DIE){
                    alive++;
                }
            }
        }
        if(cellstate == CellState.ALIVE){
            alive--;
        }
        return alive;
    }


}
