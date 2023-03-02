public class Cell {

    private int x;
    private int y;

    private int size;
    private int row;
    private int column;
    //private int fill;
    private CellState cellstate;
    private MooreRules mrules;
    private Rules rules;
    private boolean comeAlive;
    private boolean dieNext;

    public Cell(int x, int y, int size, int row, int column, CellState cellstate/*, MooreRules mrules, Rules rules*/){
        this.x = x;
        this.y = y;
        this.size = size;
        this.row = row;
        this.column = column;
    }

    public void display(){
        if(cellstate == CellState.ALIVE){
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


}
