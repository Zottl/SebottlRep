package model.game.maps;

import model.game.object.RedChest;

public class GrassMap01 extends GameMap
{
    public GrassMap01()
    {
        super(GameMap.loadMap("resources/maps/grass01.dat"));

        this.addMapObject(new RedChest(64, 80));
    }
}
