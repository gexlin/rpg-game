/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package by_software.game.gameobject.mob.body;

import by_software.engine.AnimationSet;
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
    
    private static BodyPart PLAYER_HEAD = new BodyPart(new Vector2f(0,0), new Vector2f(0,0),new Vector2f(0,1),AnimationSet.PLAYER_HEAD);
    private static BodyPart PLAYER_CHEST = new BodyPart(new Vector2f(0,0), new Vector2f(0,0),new Vector2f(0,1),AnimationSet.PLAYER_BODY);
    private static Arm PLAYER_ARM_LEFT = new Arm(new Vector2f(0,0), new Vector2f(-8,12),new Vector2f(0,1),AnimationSet.PLAYER_ARM_LEFT);
    private static Arm PLAYER_ARM_RIGHT = new Arm(new Vector2f(0,0), new Vector2f(8,12),new Vector2f(0,1),AnimationSet.PLAYER_ARM_RIGHT);
    private static BodyPart PLAYER_PAULDRON_LEFT = new BodyPart(new Vector2f(0,0), new Vector2f(-26,00),new Vector2f(0,1),AnimationSet.PLAYER_PAULDRON_LEFT);
    private static BodyPart PLAYER_PAULDRON_RIGHT = new BodyPart(new Vector2f(0,0), new Vector2f(26,00),new Vector2f(0,1),AnimationSet.PLAYER_PAULDRON_RIGHT);
    private static BodyPart PLAYER_LEGS = new BodyPart(new Vector2f(0,0), new Vector2f(0,0),new Vector2f(0,1),AnimationSet.PLAYER_LEGS);
    
    private static Body PLAYER_BODY;
    
    static
    {
        PLAYER_HEAD.addChildPart(PLAYER_CHEST);
        PLAYER_CHEST.addChildPart(PLAYER_LEGS);
        PLAYER_HEAD.addChildPart(PLAYER_PAULDRON_LEFT);
        PLAYER_HEAD.addChildPart(PLAYER_PAULDRON_RIGHT);
        PLAYER_PAULDRON_RIGHT.addChildPart(PLAYER_ARM_RIGHT);
        PLAYER_PAULDRON_LEFT.addChildPart(PLAYER_ARM_LEFT);
        PLAYER_BODY = new Body(new BodyPart[]{PLAYER_HEAD,PLAYER_CHEST,PLAYER_ARM_LEFT,PLAYER_ARM_RIGHT,PLAYER_PAULDRON_LEFT,PLAYER_PAULDRON_RIGHT,PLAYER_LEGS});
    
    
    }
    
    private BodyPart[] parts;
    //private Arm[] arms;
    private Mob owner;
    private int type;
    public static Body getHuman(Mob owner)
    {
        Body human = new Body(PLAYER_BODY);
        
         if(human.parts[HEAD] == null)
           {
               System.out.println("done   goofed.");
           
           }
        human.parts[HEAD].addChildPart(human.parts[CHEST]);
        human.parts[CHEST].addChildPart(human.parts[LEGS]);
        human.parts[HEAD].addChildPart(human.parts[PAULDRON_LEFT]);
        human.parts[HEAD].addChildPart(human.parts[PAULDRON_RIGHT]);
        human.parts[PAULDRON_RIGHT].addChildPart(human.parts[ARM_RIGHT]);
        human.parts[PAULDRON_LEFT].addChildPart(human.parts[ARM_LEFT]);
        human.owner = owner;
        human.equipLeft(owner.getDefaultWeapon());
        human.equipRight(owner.getDefaultWeapon());
        return human;
    }
            
    private Body( BodyPart[] parts)
    {
       
        this.parts = parts;
    }
    
    private Body(Body that)
    {
        this.parts = new BodyPart[that.parts.length];
        //this.arms = new Arm[that.arms.length];
        this.owner = that.owner;
        
        for(int i = 0;i < that.parts.length; i++)
        {
            this.parts[i] = that.parts[i].clone();
          
        }
        
        
        
    }
    public void setOwner(Mob owner)
    {this.owner = owner;}
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
        weapon.setWielder(owner,PLAYER_ARM_LEFT);
        weapon.setEquipedOn(PLAYER_ARM_LEFT);
        ((Arm)parts[ARM_LEFT]).equip(weapon);
    }
    public void equipRight( Weapon weapon)
    {
        weapon.setWielder(owner,PLAYER_ARM_RIGHT);
        weapon.setEquipedOn(PLAYER_ARM_RIGHT);
        ((Arm)parts[ARM_RIGHT]).equip(weapon);
    }
    public boolean attackLeft(int index)
    {
       return (((Arm)parts[ARM_LEFT]).attack(index, owner));
    }
    public boolean attackRight(int index)
    {
        return (((Arm)parts[ARM_RIGHT]).attack(index, owner));
    }
}
