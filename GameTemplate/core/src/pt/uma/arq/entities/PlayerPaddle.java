package pt.uma.arq.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import pt.uma.arq.game.Animator;

public class PlayerPaddle {
    // Variaveis da class PlayerPaddle
    private int x;
    private int y;
    private int direcao;
    private int velocity;
    private Animator animator;
    private java.awt.Rectangle boundingBox;
    private int centerX = Gdx.graphics.getWidth() /2;

    // Construtor
    public PlayerPaddle(SpriteBatch batch) {
        this.x = centerX;
        this.y = 86;
        this.velocity = 10;
        this.direcao = 1;
        animator = new Animator(batch, "full_paddle.png", 6,1);
    }

    // Função para dar update à posição do paddle dependendo da tecla que o utilizador clica
    public void updatePosition(boolean direction){
        if(direction){
            if(x <= Gdx.graphics.getWidth() - animator.getWidth()){
                x+= 10;
            }
        }else{
            if(x >= 2){
                x -= 10;
            }
        }
        boundingBox.setLocation(x,y);
    }

    // Função para retornar o retangulo invisivel
    public java.awt.Rectangle getBoundingBox(){
        return boundingBox;
    }

    // Função para criar o modelo do paddle
    public void create(){
        animator.create();
        boundingBox = new java.awt.Rectangle(x,y, animator.getWidth(), animator.getHeight());
    }

    // Função utilizada para dar render do paddle
    public void render() {
        animator.render(x,y);
    }


}
