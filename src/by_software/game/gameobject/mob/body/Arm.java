/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package by_software.game.gameobject.mob.body;

import by_software.engine.Delay;
import by_software.engine.render.AnimationSet;
import by_software.game.Util;
import by_software.game.gameobject.equipment.weapon.Weapon;
import by_software.game.gameobject.mob.Mob;
import java.util.ArrayList;
import static org.lwjgl.opengl.GL11.glPopMatrix;
import static org.lwjgl.opengl.GL11.glPushMatrix;
import static org.lwjgl.opengl.GL11.glRotated;
import static org.lwjgl.opengl.GL11.glTranslatef;
import org.lwjgl.util.vector.Vector2f;

/**
 *
 * @author Nigel
 */
public class Arm extends BodyPart
{
    
    private Weapon weapon;
    private Vector2f weaponPos;
    private Delay attackDelay;
   
    
    public Arm(Vector2f pos, Vector2f offsetPos, Vector2f direction, ArrayList<BodyPart> body, AnimationSet animations,Weapon weapon,Vector2f weaponPos)
    {
        this(pos, offsetPos, direction, body, animations);
        this.weaponPos = weaponPos;
        this.weapon = weapon;
        

    }
    public Arm(Vector2f pos, Vector2f offsetPos, Vector2f direction, ArrayList<BodyPart> body, AnimationSet animations)
    {
        super(pos, offsetPos, direction, body, animations);
        this.attackDelay = new Delay(0);
        attackDelay.start();
        this.attackDelay.zeroDelay();
        this.weaponPos = new Vector2f(0,0);
        
    }
    public Arm(Vector2f pos,Vector2f offsetPos,Vector2f direction,  AnimationSet  animations)
    {
      super(pos, offsetPos, direction, animations);
      this.attackDelay = new Delay(0);
      attackDelay.start();
      this.attackDelay.zeroDelay();
      this.weaponPos = new Vector2f(0, 0);
    }
//    public Arm(Vector2f pos,Vector2f offsetPos,Vector2f direction)
//    {
//       super(pos, offsetPos, direction);
//       
//    }
    
    @Override
    public void render()
    {
        
        glPushMatrix();
        {
//            float angle = 1f;
//            angle = (float)Math.toRadians(angle);
//            Vector2f a = new Vector2f((float)Math.sin(-angle),(float)Math.cos(angle));
//            System.out.println(a + " angle : " + Util.angleDegrees(a));
//          
//            a.normalise();
//            Util.rotateRotationVector(offsetDirection,a , offsetDirection);
////            
            glTranslatef(offsetPos.x,offsetPos.y,0);
            //glRotated(Util.angleDegrees(Vector2f.add(direction, offsetDirection, new Vector2f())), 0f, 0f, 1f);
            glRotated(Util.angleDegrees(offsetDirection), 0f, 0f, 1f);
            
            if(childParts != null)
            {    
                for(BodyPart  childPart: childParts)
                {
                    childPart.render();   
                   
                }
            }
            
            if(weapon != null)
            {  
                weapon.render();
                if(animations.isActive())
                {    
                    weapon.active();
                    if(animations.triggerEvent())
                    {
                        weapon.event();
                    }
                }
            }
            
            animations.render();
          
        }
        glPopMatrix();       
    }
    
    public boolean equip(Weapon weapon)
    {
        this.weapon = weapon;
        return true;
    }
    
    public void unequip()
    {
        this.weapon = null;
    }
//    #####           getRootWeaponOffset
//    public Vector2f getWeaponRootOffset()
//    {
//        return Vector2f.add(parent.getRootOffset(), weaponPos, new Vector2f()) ;// weaponPos;
//    }
    public Vector2f getWeaponOffset()
    {
        return getWeaponPos();// weaponPos; 
    }
    
    public boolean attack(int attackIndex, Mob attacker)
    {   
       weapon.clearTargets();
       return weapon.attack(attackIndex,attacker,this);
    }
    
    @Override
    public Arm clone()
    {
       
        return new Arm(new Vector2f(this.pos),new Vector2f(this.offsetPos),new Vector2f(this.direction),(ArrayList<BodyPart>)null,new AnimationSet(this.animations),this.weapon,new Vector2f(this.weaponPos));
//        this.pos = new Vector2f(this.pos);
//        this.offsetPos = new Vector2f(this.offsetPos);
//
//        this.direction = new Vector2f(this.direction);
//        this.offsetDirection = new Vector2f(this.offsetDirection);
//    
//        this.animations = new AnimationSet(this.animations);
//        this.currentAnimation = AnimationType.IDLE;
//        //IMPORTANT! change to clone ?
//        this.armour = this.armour;
    }
    
    public Vector2f getWeaponPos()
    {
       return ((ArmSprite)this.getCurrentAnimation().getCurrentFrame().getSprite()).getWeaponOffset();
    }
    
    public Vector2f getRootWeaponOffset() 
    { 
        //System.out.println("Arm offsetpos " + this.getOffsetPos() + " " + parent.getOffsetPos() + "  " + Vector2f.add(this.getOffsetPos(), parent.getOffsetPos(), new Vector2f()));
        Vector2f answer = new Vector2f(); 
        Vector2f.add(this.getRootOffset(),
        Util.rotateRadians(Util.angleRadians(this.getOffsetDirection()), getWeaponPos()),answer);

        return Util.rotateRadians(Util.angleRadians(this.getRootDirectionOffset()), answer);
//                new Vector2f()
//            return Vector2f.add
//            (
//                Vector2f.add
//                (
//                        parent.getRootOffset(), 
//                        this.getOffsetPos(), 
//                        new Vector2f()
//                ),
//                Util.rotateRadians(Util.angleRadians(this.getOffsetDirection()), getWeaponPos()),
//                new Vector2f()
//            );
            
            
        
        //TODO ####################################################################
//         Vector2f offsetRotated =  Util.rotateRadians(Util.angleRadians(((Arm)equipedOn).getDirection()), ((Arm)equipedOn).getRootWeaponOffset());
//            return Vector2f.add(wielder.getPos(),offsetRotated, offsetRotated);
        
//        for(BodyPart child: childParts)
//        {
//           if(child == part)
//           {
//               return Vector2f.add(parent.getChildOffsets(this), this.offsetPos, new Vector2f());
//           }
//        }
      // return  getWeaponPos();
    }
    
    public Delay getAttackDelay(){return attackDelay;}
    public void setAttackDelay(int delay){ attackDelay.start(delay);}
    
}
