package Entidades;

import Niveis.*;

//  Felipe Aparecido da Silva - 11954502.
//  Vítor Augusto Paiva de Brito - 13732303.

//  Classe que representa o inimigo final do jogo.
public final class Boss extends Inimigo{
    
//  Variável que monitora o período de geração de inimigos na fase.
    private double special;

    public Boss(String path, int linha, int coluna, int vida, int entityWidth, int entityHeight, MyPanel gamePanel, double angle) {
        super(path, linha, coluna, vida, entityWidth, entityHeight, gamePanel, angle);
        
        this.setDeadSprite("bossDead.png");
        this.setWidth(Consts.SNIPER_WIDTH);
        this.setHeight(Consts.SNIPER_HEIGHT);
        this.setRangeRadius(Consts.SNIPER_RANGE);
        this.setVisionRadius(Consts.SNIPER_VISION);
        this.setSpecial(System.nanoTime());
        
        this.updateRange();
        this.updateVision();
    }

    public double getSpecial() {
        return special;
    }

    public void setSpecial(double special) {
        this.special = special;
    }
    
    @Override
    public void ataque() {
        if(this.getNow() - this.getLast() >= 500000000) {
            gamePanel.addEntidade(new Projetil("bullet.png",
                                                this.getX(),
                                                this.getY(),
                                                (int) (gamePanel.getHero().getX() - this.getX())/2,
                                                (int) (gamePanel.getHero().getY() - this.getY())/2,
                                                gamePanel, false));
            
            this.setLast(System.nanoTime());
        }
        
        if(this.getNow() - this.getSpecial() >= 2200000000L) {
            InimigoBase inimigoSup = new InimigoBase("base.png",
                                                    Consts.TILE_HEIGHT, 
                                                    Consts.TILE_WIDTH, 
                                                    5, Consts.TILE_WIDTH, Consts.TILE_HEIGHT, this.gamePanel, Math.PI),
                        inimigoInf = new InimigoBase("base.png", 
                                                    Consts.TILE_HEIGHT, 
                                                    Consts.TILE_WIDTH * (Consts.TILES_Y - 1) * Consts.LEVEL_SCALE,
                                                    5, Consts.TILE_WIDTH, Consts.TILE_HEIGHT, this.gamePanel, Math.PI);
            
            inimigoSup.setHunt(true);
            inimigoInf.setHunt(true);
            
            gamePanel.addInimigo(inimigoSup);
            gamePanel.addInimigo(inimigoInf);
            
            this.special = System.nanoTime();
        }
    }
}
