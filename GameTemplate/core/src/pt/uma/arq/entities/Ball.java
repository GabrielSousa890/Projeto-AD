package pt.uma.arq.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import pt.uma.arq.game.Animator;

public class Ball {
    // Variaveis da Class Ball
    private int x;
    private int y;
    private int direcaoX;
    private int direcaoY;
    private int velocity;
    private boolean gameStatus;
    private Animator animator;
    private java.awt.Rectangle boundingBox;
    int centerX = Gdx.graphics.getWidth() /2;
    int centerY = 200;

    // Construtor
    public Ball(SpriteBatch batch) {
        this.x = centerX;
        this.y = centerY;
        this.gameStatus = true;
        this.velocity = 7;
        this.direcaoX = (int) (Math.random() * (1 - -1)) + -1;
        this.direcaoY = 1;
        animator = new Animator(batch, "ball.png", 2,2);
    }

    // Função para retornar o retangulo invisivel
    public java.awt.Rectangle getBoundingBox(){
        return boundingBox;
    }

    // Gets and Sets
    public boolean getGameStatus(){
        return gameStatus;
    }

    public void setGameStatus(boolean gameStatus){
        this.gameStatus = gameStatus;
    }


    // Função utilizada para dar update na posição da bola
    public void updatePosition(){
        x = x + (velocity * direcaoX);
        y = y + (velocity * direcaoY);

        if(x >= Gdx.graphics.getWidth() - animator.getWidth()){
            direcaoX = -1;
        }
        if(x <= 0){
            direcaoX = 1;
        }

        if(y >= Gdx.graphics.getHeight() - animator.getHeight()){
            direcaoY = -1;
        }
        if(y <= 0){
            gameStatus = false;
        }
        boundingBox.setLocation(x,y);
    }

    // Função usada para mudar a direção da bola quando entra em contacto com um objeto
    public void changeDirection(boolean verifyDirection, int direcaoX, int direcaoY){
        if(verifyDirection == true){
                this.direcaoX = direcaoX;
        }

        this.direcaoY = direcaoY;

        x = x + (velocity * this.direcaoX);
        y = y + (velocity * this.direcaoY);
    }

    // Função para criar o modelo da bola
    public void create(){
        animator.create();
        boundingBox = new java.awt.Rectangle(x,y, animator.getWidth(), animator.getHeight());
    }

    // Função utilizada para dar render da bola
    public void render() {
        animator.render(x,y);
    }

}
