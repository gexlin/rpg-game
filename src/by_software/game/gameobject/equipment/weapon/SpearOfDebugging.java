/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package by_software.game.gameobject.equipment.weapon;


import org.lwjgl.util.vector.Vector2f;
import org.lwjgl.util.vector.Vector3f;

/**
 *
 * @author Nigel
 */
public class SpearOfDebugging extends Weapon 
{
  //  String name, int slot, Vector2f pos, Vector2f size, Vector3f color,
   //                            float stabDamage, float slashDamage, float bluntDamage, float weight,float attackDelayMod, float attackSpeedMod
    public SpearOfDebugging( Vector2f pos)
    {
        
        /*name,
        slot, should be all 
        size, for drawing
        color
        rangr,
        stabDamage,
        slashDamage,
        bluntDamage,
        weight,       
        */
       
        super( "The Spear Of Debugging",pos,new Vector2f(8,128), new Vector3f(1,1,1),128 ,4f,3f,1f,1f,5f,5f, WEAPON_PATH + "spear.png");
        Attack[] attacks = {new StabAttack(this)};
        this.initAttacks(attacks);
        
        
    }
    
    @Override
    public Weapon clone()
    {
        return new SpearOfDebugging(new Vector2f(this.pos));
    }
    
}
