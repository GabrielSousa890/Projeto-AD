package pt.uma.arq.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

public class Board {
    // Variaveis da class Board
    private ArrayList<Block> blocks;
    private int x;
    private int y;
    private int countPoints;
    private Iterator<Block> iterator;
    private Block b;

    // Construtor
    public Board(){
        this.blocks = new ArrayList<Block>();
    }

    // Gets and Sets
    public ArrayList<Block> getBlocks(){
        return blocks;
    }

    public void addBlock(Block block){
        this.blocks.add(block);
    }

    // Função para remover um bloco quando a bola toca nesse mesmo bloco
    public String removeBlock(Ball ball, String playerScore){
        iterator = blocks.iterator();
        while(iterator.hasNext()) {
            Block block = iterator.next();

            //Verificação se a bola tocou no bloco
            if(ball.getBoundingBox().intersects(block.getBoundingBox())){

                // Chamada da função para alterar a direção da bola
                ball.changeDirection(false,0,-1);

                iterator.remove();

                // Variavel utilizada para guardar o score do player
                countPoints += block.getPoints();
                playerScore = "SCORE: " + countPoints;
            }
        }
        return playerScore;
    }

    // Função para criar um novo bloco random
    public void randomBlock(SpriteBatch batch){
        // Array com as posições possíveis de adicionar o bloco
        int[] xPositions = {190,250,310,370,430,490,550,610,670,730,790,850,910,970,1030,1090,1150,1210,1270,1330,1390,1450,1510,1570};

        // Pega um indice random do array criado
        int rnd = new Random().nextInt(xPositions.length);

        // Cria o novo bloco
        this.b = new BlackBlock(batch, 20, xPositions[rnd], Gdx.graphics.getHeight() - 235);
        this.b.create();

        // Adiciona o bloco ao ArrayList
        blocks.add(this.b);
    }
}
