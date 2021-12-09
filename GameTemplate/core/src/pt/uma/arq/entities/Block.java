package pt.uma.arq.entities;

public abstract class Block {
    // Variaveis da Class Block
    private int points;
    private int x;
    private int y;

    // Construtor
    public Block(int points, int x, int y) {
        this.points = points;
        this.x = x;
        this.y = y;
    }

    // Get and Sets
    public int getX(){
        return x;
    }

    public int getY(){
        return y;
    }

    public int getPoints(){
        return points;
    }

    // Funções abstracts para cada bloco
    public abstract void create();
    public abstract void render();
    public abstract java.awt.Rectangle getBoundingBox();

}
