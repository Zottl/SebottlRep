package model.game.maps;

import model.game.loot.Coin;
import model.game.object.RedChest;

public class GrassMap01 extends GameMap
{
    public GrassMap01()
    {
        super(GameMap.loadMap("resources/maps/grass01.dat"));

        this.addMapObject(new RedChest(64, 80));
        this.addMapObject(new Coin(64, 140));
        this.addMapObject(new Coin(68, 147));
        this.addMapObject(new Coin(61, 149));
        this.addMapObject(new Coin(55, 120));
    }
}
