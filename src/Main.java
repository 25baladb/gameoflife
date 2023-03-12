import processing.core.PApplet;
public class Main extends PApplet {
    private static final int CELL_SIZE = 10;
    public static Main app;
    private final int NUM_ROWS = 50;
    private final int NUM_COLUMNS = 100;
    private Cell[][] moss;
    private CellState cellstate;
    private boolean doEvolve = false;

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
        Rules rules = new MooreRules(new int[]{3}, new int[]{2, 3});
        int ex = 0;
        int why = 0;
        for(int r = 0; r < moss.length; r++){
            for(int c = 0; c < moss[r].length; c++) {
                Cell m = new Cell(ex, why, CELL_SIZE, r, c, CellState.DEAD, rules);
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
        if(doEvolve == true){
            evolve();
            applyRules();
        }
    }
    public void applyRules() {
        for (int r = 1; r < moss.length - 1; r++) {
            for (int c = 1; c < moss[r].length - 1; c++) {
                moss[r][c].applyRules(moss);
            }
        }
    }

    public void evolve(){
        for (int r = 0; r < moss.length; r++) {
            for (int c = 0; c < moss[r].length; c++) {
                moss[r][c].evolve();
            }
        }
    }

    public void mouseClicked(){
        moss[mouseY/CELL_SIZE][mouseX/CELL_SIZE].handleClick();
    }

    public void keyPressed(){
        doEvolve = !doEvolve;
    }
}
