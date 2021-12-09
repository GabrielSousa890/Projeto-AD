package pt.uma.arq.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import pt.uma.arq.entities.*;


public class Game extends ApplicationAdapter {
    // Váriaveis da Class "Game"
    private SpriteBatch batch;
    private BackgroundManagement backgroundManagement;
    private PlayerPaddle playerPaddle;
    private Ball ball;
    private GreenBlock bloco;
    private PurpleBlock bloco2;
    private RedBlock bloco3;
    private YellowBlock bloco4;
    private float timeSeconds = 0f;
    private float period = 10f;
    private Board blocks;
    private Explosion explosion;
    private String playerScore = "SCORE: ";
    int count = 190;
    private BitmapFont score, font;
    private boolean startgame = false;

    @Override
    public void create() {
        // Inicialização de todas as váriaveis
        batch = new SpriteBatch();
        backgroundManagement = new BackgroundManagement(batch);
        playerPaddle = new PlayerPaddle(batch);
        ball = new Ball(batch);
        explosion = new Explosion(batch);
        blocks = new Board();
        font = new BitmapFont(Gdx.files.internal("gamefont.fnt"), Gdx.files.internal("gamefont.png"), false);
        score = new BitmapFont(Gdx.files.internal("gamefont.fnt"), Gdx.files.internal("gamefont.png"), false);

        // For utilizado para criar o Arraylist de blocos
        for(int i = 0; i <= 24; i++){
            bloco = new GreenBlock(batch,20, count, Gdx.graphics.getHeight() - 85);
            bloco2 = new PurpleBlock(batch,15, count, Gdx.graphics.getHeight() - 120);
            bloco3 = new RedBlock(batch,10, count, Gdx.graphics.getHeight() - 155);
            bloco4 = new YellowBlock(batch,5, count, Gdx.graphics.getHeight() - 195);
            count += 60;
            blocks.addBlock(bloco);
            blocks.addBlock(bloco2);
            blocks.addBlock(bloco3);
            blocks.addBlock(bloco4);

        }

        // Criando os modelos
        ball.create();
        playerPaddle.create();
        explosion.create();
        for (Block b: blocks.getBlocks()) {
                b.create();
        }
    }

    @Override
    public void render() {

        // Verificação para ver se o jogo está iniciado ou não
        if(!startgame){

            // Bacth.begin() -> Comando que nos deixa renderizar coisas no Ecra
            batch.begin();

            // Renderização das imagens principais do jogo
            backgroundManagement.render();
            playerPaddle.render();
            ball.render();
            for (Block b : blocks.getBlocks()) {
                b.render();
            }

            // A linha abaixo é utilizada para representar texto na tela
            font.draw(batch, "PRESS ENTER TO START GAME!", Gdx.graphics.getWidth() / 2 - 200, Gdx.graphics.getHeight() / 2);
            batch.end();

            // Verificação se a tecla Enter esta a ser pressionada para começar o jogo
            if (Gdx.input.isKeyPressed(Input.Keys.ENTER)) {
                startgame = true;
            }
        }else{
            // Bacth.begin() -> Comando que nos deixa renderizar coisas no Ecra
            batch.begin();

            // Funções durante a execução do jogo
            ball.updatePosition();
            playerScore = blocks.removeBlock(ball, playerScore);

            // Renderização das imagens do jogo
            backgroundManagement.render();
            for (Block b : blocks.getBlocks()) {
                b.render();
            }
            ball.render();
            playerPaddle.render();

            // Verificação se a bola toca no paddle
            if (ball.getBoundingBox().intersects(playerPaddle.getBoundingBox())) {
                if (ball.getBoundingBox().x >= playerPaddle.getBoundingBox().x && ball.getBoundingBox().x <= playerPaddle.getBoundingBox().x + 40) {
                    ball.changeDirection(true, -1, 1);
                } else if (ball.getBoundingBox().x >= playerPaddle.getBoundingBox().x + 54 && ball.getBoundingBox().x <= playerPaddle.getBoundingBox().x + 94) {
                    ball.changeDirection(true, 1, 1);
                } else if (ball.getBoundingBox().x >= playerPaddle.getBoundingBox().x + 40 && ball.getBoundingBox().x <= playerPaddle.getBoundingBox().x + 54) {
                    ball.changeDirection(true, 0, 1);
                }
            }

            // Tentativa de usar a animação explosion
            // explosion.render(ball.getBoundingBox().x,ball.getBoundingBox().y);

            // Verificação se o jogo esta a ser executado!
            if (ball.getGameStatus() == false) {
                if(!blocks.getBlocks().isEmpty()) {
                    font.setColor(255,0,0,1);
                    font.draw(batch, "GAME OVER!", Gdx.graphics.getWidth() / 2 - 100, Gdx.graphics.getHeight() / 2);
                    score.draw(batch, "PRESS ESC TO EXIT THE GAME!", Gdx.graphics.getWidth() / 2 - 215, Gdx.graphics.getHeight() / 2 - 30);
                }
            }else{
                // Verificação se as teclas estão a ser pressionadas para mover o paddle
                if (Gdx.input.isKeyPressed(Input.Keys.RIGHT) || Gdx.input.isKeyPressed(Input.Keys.D)) {
                    playerPaddle.updatePosition(true);
                }
                if (Gdx.input.isKeyPressed(Input.Keys.LEFT) || Gdx.input.isKeyPressed(Input.Keys.A)) {
                    playerPaddle.updatePosition(false);
                }

                // Verificação de 10 em 10 segundos para adicionar um bloco random na tela
                timeSeconds +=Gdx.graphics.getRawDeltaTime();
                if(timeSeconds > period){
                    timeSeconds-=period;
                    blocks.randomBlock(batch);
                }
            }

            // Verificação se ainda existe blocos no Array de blocos (Para verificar se o player ganhou o jogo)
            if (blocks.getBlocks().isEmpty()) {
                font.setColor(0,255,0,1);
                font.draw(batch, "WIN!", Gdx.graphics.getWidth() / 2 - 50, Gdx.graphics.getHeight() / 2);
                score.draw(batch, "PRESS ESC TO EXIT THE GAME!", Gdx.graphics.getWidth() / 2 - 215, Gdx.graphics.getHeight() / 2 - 30);
                ball.setGameStatus(false);
            }

            // Comando usado para atualizar o score do player durante o jogo
            score.draw(batch, playerScore, 20, Gdx.graphics.getHeight() - 30);
            batch.end();
        }
        // Verificação se esta a ser pressionado a tecla ESCAPE ou BACKSCAPE para fechar do jogo
        if (Gdx.input.isKeyPressed(Input.Keys.ESCAPE) || Gdx.input.isKeyPressed(Input.Keys.BACKSPACE)) {
            Gdx.app.exit();
        }
    }

    @Override
    public void dispose() {
        batch.dispose();
    }

}