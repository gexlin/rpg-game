/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package by_software.game.gameobject.items;

import by_software.engine.Physics;
import by_software.game.gameobject.Item;
import by_software.game.gameobject.mob.player.Player;
import org.lwjgl.util.vector.Vector2f;
import org.lwjgl.util.vector.Vector3f;

/**
 *
 * @author Nigel
 */
public class Item_Cube extends Item
{
    public static final float SIZE = 32;
    

    public Item_Cube(Vector2f pos, Vector3f color)
    {
        super(" Cube", pos, new Vector2f(SIZE, SIZE), color);
    
    }
   
    
}
