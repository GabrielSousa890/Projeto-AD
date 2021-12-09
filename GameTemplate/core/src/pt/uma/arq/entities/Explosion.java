package pt.uma.arq.entities;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import pt.uma.arq.game.Animator;

public class Explosion {
    private Animator animator;

    public Explosion(SpriteBatch batch) {
        animator = new Animator(batch, "explosion.png", 5,1);
    }

    public void create(){
        animator.create();
    }

    public void render(int x, int y) {
        animator.render(x,y);
    }
}
