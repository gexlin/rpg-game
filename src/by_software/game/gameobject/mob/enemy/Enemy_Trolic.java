/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package by_software.game.gameobject.mob.enemy;

import by_software.game.gameobject.equipment.weapon.Fist;
import by_software.game.gameobject.mob.Faction;
import org.lwjgl.util.vector.Vector2f;
import org.lwjgl.util.vector.Vector3f;

/**
 *
 * @author Nigel
 */
public class Enemy_Trolic extends Enemy
{

    public Enemy_Trolic(Vector2f pos, Vector2f size, int type, int level, int inventorySize)
    {
        super("Trolic", Faction.TROLIC,pos, size, new Vector3f(.8f,.2f,.2f), type, level, inventorySize, new Fist());
        
    }

  
  
}
