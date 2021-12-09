package pt.uma.arq.entities;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import pt.uma.arq.game.Animator;

public class BlackBlock extends Block{
    // Variaveis da class BlackBlock
    private Animator animator;
    private java.awt.Rectangle boundingBox;

    // Construtor
    public BlackBlock(SpriteBatch batch, int points, int x, int y) {
        super(points, x, y);
        animator = new Animator(batch, "black.png", 1,1);
    }

    // Função para retornar o retangulo invisivel
    public java.awt.Rectangle getBoundingBox(){
        return boundingBox;
    }

    // Função para criar o modelo do bloco
    public void create(){
        animator.create();
        boundingBox = new java.awt.Rectangle(super.getX(),super.getY(), animator.getWidth(), animator.getHeight());
    }

    // Função utilizada para dar render do bloco
    public void render() {
        animator.render(super.getX(),super.getY());
    }
}
