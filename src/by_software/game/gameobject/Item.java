/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package by_software.game.gameobject;

import by_software.engine.GameObject;
import by_software.engine.GameObjectType;
import by_software.engine.Physics;
import org.lwjgl.util.vector.Vector2f;
import org.lwjgl.util.vector.Vector3f;

/**
 *
 * @author Nigel
 */
public abstract class Item extends GameObject
{

    public Item(String name, Vector2f pos, Vector2f size, Vector3f color, String path)
    {
        super(name, pos, size, color,GameObjectType.ITEM,path);
        this.name = name;
        
    }
    
    public void pickUp()
    {
        remove();
    }

    public String getName() {return name;}
    
    public void drop(Vector2f pos)
    {
        this.pos = new Vector2f(pos);
        this.addToGame();
    }
}
