package controller.ai;

import model.GameData;
import model.game.object.MapObject;

/**
 * Class to control the behavior of MapObjects
 */
public abstract class MapObjectAI
{
    protected GameData data;
    protected MapObject target;
    protected int state;
    
    public MapObjectAI()
    {
        this.data = GameData.getInstance();
        this.state = 0;
    }
    
    public abstract void advance();
    
    public void registerTarget(MapObject mo) {
        target = mo;
    }
}
