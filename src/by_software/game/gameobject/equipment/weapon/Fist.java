/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package by_software.game.gameobject.equipment.weapon;
import by_software.game.gameobject.mob.Mob;
import org.lwjgl.util.vector.Vector2f;
import org.lwjgl.util.vector.Vector3f;

/**
 *
 * @author Nigel
 */
public class Fist extends Weapon 
{
  //  String name, int slot, Vector2f pos, Vector2f size, Vector3f color,
   //                            float stabDamage, float slashDamage, float bluntDamage, float weight,float attackDelayMod, float attackSpeedMod
    public Fist()
    {
        super( "Fist Of Punching",new Vector2f(32,32),new Vector2f(4,4), new Vector3f(1f,.5f,.1f),10 ,1f,1f,1f,1f,2f,1f, "");
        Attack[] attacks = {new StabAttack(this)};
        this.initAttacks(attacks);
     
        
    }
    
}

