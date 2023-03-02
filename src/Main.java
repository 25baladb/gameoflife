import processing.core.PApplet;
public class Main extends PApplet {
    private static final int CELL_SIZE = 10;
    public static Main app;
    private final int NUM_ROWS = 50;
    private final int NUM_COLUMNS = 100;
    private Cell[][] moss;

    public static void main(String[] args){
        PApplet.main("Main");
    }

    public Main(){
        super();
        app = this;
    }

    public void settings(){
        size(600,500);
    }

    public void setup(){
        moss = new Cell [NUM_ROWS][NUM_COLUMNS];
        int ex = 0;
        int why = 0;
        for(int r = 0; r < moss.length; r++){
            for(int c = 0; c < moss[r].length; c++) {
                Cell m = new Cell(ex, why, CELL_SIZE, r, c, CellState.DEAD);
                ex += CELL_SIZE;
                moss[r][c] = m;
            }
            ex = 0;
            why += CELL_SIZE;
        }
    }

    public void draw() {
        for (int r = 0; r < moss.length; r++) { 
            for (int c = 0; c < moss[r].length; c++) {
                moss[r][c].display();
            }
        }
    }

    public void mouseClicked(){
        moss[mouseY/CELL_SIZE][mouseX/CELL_SIZE].handleClick();
    }

}
