/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package by_software.game.gameobject.mob.body;

import by_software.engine.render.AnimationSet;
import by_software.game.gameobject.equipment.weapon.Weapon;
import by_software.game.gameobject.mob.Mob;
import org.lwjgl.util.vector.Vector2f;

/**
 *
 * @author Nigel
 */




public class Body
{
    public static final int HEAD = 0;
    public static final int CHEST = 1;
    public static final int ARM_LEFT = 2;
    public static final int ARM_RIGHT = 3;
    public static final int PAULDRON_LEFT = 4;
    public static final int PAULDRON_RIGHT = 5;
    public static final int LEGS = 6;


    
    protected BodyPart[] parts;
    //private Arm[] arms;
    protected Mob owner;
    //private int type;
    protected float scale;
    
    public Body(int numParts, Mob owner,float scale)
    {
        this.parts = new BodyPart[numParts];
        this.setOwner(owner);
        this.scale = scale;
    }
    
    public void render()
    {
        parts[0].render();
    }
    
    public BodyPart getHead()
    {
        return this.parts[0];
    }
    
    public void equipLeft( Weapon weapon)
    {   
        equip(weapon, ARM_LEFT);
    }
    
    public void equipRight( Weapon weapon)
    {
        
        equip(weapon, ARM_RIGHT);
       
       
    }
    
    private void equip( Weapon weapon, int index)
    {
        
        weapon.setWielder(owner,this.parts[index]);
        //weapon.setEquipedOn(PLAYER_ARM_RIGHT);
        ((Arm)parts[index]).equip(weapon);
    }
    
    public boolean attackLeft(int index)
    {
       return (((Arm)parts[ARM_LEFT]).attack(index, owner));
    }
    
    public boolean attackRight(int index)
    {
        return (((Arm)parts[ARM_RIGHT]).attack(index, owner));
    }
    
    public void scale(float scale)
    {
        if(scale != 1.0 && scale != 0)
        {
            for(BodyPart bp: parts)
            {
                bp.scale(scale);
            }
        }
    }
    
    
    
    public void setOwner(Mob owner) {this.owner = owner;}
    
}
