/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package by_software.game.gameobject.equipment.weapon;

import by_software.engine.GameObject;
import static by_software.engine.GameObject.getGame;
import by_software.game.Util;
import by_software.game.gameobject.equipment.EquippableItem;
import by_software.game.gameobject.mob.Mob;
import by_software.game.gameobject.mob.body.Arm;
import java.util.ArrayList;
import java.util.HashSet;
import static org.lwjgl.opengl.GL11.GL_LINES;
import static org.lwjgl.opengl.GL11.glBegin;
import static org.lwjgl.opengl.GL11.glEnd;
import static org.lwjgl.opengl.GL11.glPopMatrix;
import static org.lwjgl.opengl.GL11.glPushMatrix;
import static org.lwjgl.opengl.GL11.glRotated;
import static org.lwjgl.opengl.GL11.glTranslatef;
import static org.lwjgl.opengl.GL11.glVertex2f;
import org.lwjgl.util.vector.Vector2f;
import org.lwjgl.util.vector.Vector3f;

/**
 *
 * @author Nigel
 */
public abstract class Weapon extends EquippableItem
{
    public final static String WEAPON_PATH = "src/res/Weapons/";
    
    private float attackRange;
   
    private float stabDamage;
    private float slashDamage;
    private float bluntDamage;
    private float weight;
    
    private float attackDelayMod;
    private float attackSpeedMod;
    
    private Attack[] attacks;
    protected boolean attacking;
    protected Vector2f startPos;
    protected Vector2f endPos;
    protected HashSet<Mob> targets; 
    //protected Vector2f direction;
    protected Vector2f offsetDirection;
    
  //  private Arm equipedIn;
    
    public Weapon(String name, Vector2f pos, Vector2f size, Vector3f color,float attackRange,
                               float stabDamage, float slashDamage, float bluntDamage, float weight,float attackDelayMod, float attackSpeedMod, String path)
    { //TODO change slot
        super(name,  pos, size, color, path);
        this.attacks = new  Attack[3];
        this.stabDamage = stabDamage;
        this.slashDamage = slashDamage;
        this.bluntDamage = bluntDamage;
        this.weight = weight;
        this.attackRange = attackRange;
        this.attackDelayMod = attackDelayMod;
        this.attackSpeedMod = attackSpeedMod;
        targets = new HashSet();
        attacking = false;
        offsetDirection = new Vector2f(0,1);
    
        //this.attacks = attacks;
    }
    
    public abstract Weapon clone();

    
    
    public boolean attack(int attackIndex, Mob attacker, Arm arm)
    {
       return attacks[attackIndex].attack(attacker, arm);
    }
    public float getstabDamage(){return this.stabDamage;}

    public float getAttackRange(){return attackRange;}
    
    public float getAttackDelayMod(){return attackDelayMod;}
    public float getAttackSpeedMod(){return attackSpeedMod;}
    public float getStabDamage(){return stabDamage;}
    public float getSlashDamage(){return slashDamage;}
    public float getBluntDamage(){return bluntDamage;}
    public float getForce(){return weight;}
    public Attack[] getAttacks(){return attacks;}
    protected void initAttacks(Attack[] attacks)
    {
        this.attacks = attacks;
    }
    public void setEquipedOn(Arm equipedIn)
    {
        this.equipedOn = equipedIn;
    }
    
//    public Arm getEquipedOn()
//    {
//        return equipedOn;
//    }
//    
    @Override
    public void render()
    {  
        this.event();
        if(equipedOn == null)
        {
            glPushMatrix();
            {
                glTranslatef(pos.x,pos.y,0);
                glRotated(Util.angleDegrees(getDirection()), 0f, 0f, 1f);
                getFrame().render();
            }
            glPopMatrix();   
        } else 
        {
            Vector2f offset = ((Arm)equipedOn).getWeaponOffset();
            
            glPushMatrix();
            {
                
                glTranslatef(offset.x,offset.y,0);
                getFrame().render();
                glBegin(GL_LINES);
                {
                    startPos = new Vector2f(0,0);
                    endPos = new Vector2f(0,this.getAttackRange());

                   // Vector2f.add(startPos, endPos, endPos);
                    glVertex2f(startPos.x, startPos.y  );
                    glVertex2f(endPos.x,  endPos.y  );
                }
                glEnd();
            }
            glPopMatrix(); 
        }
        
    }
    
    public void active()
    {
        //block or peiry(?) window
    }
    public void event()
    {
        if(wielder == null)
        {
            return;
        }
        //attack or block if shield
            startPos = ((Arm)equipedOn).getRootWeaponOffset();//this.getPos();
            //Vector2f.add(startPos, ((Arm)equipedOn).getRootWeaponOffset(),startPos);
            Vector2f.add(startPos, wielder.getPos(),startPos);
            endPos = new Vector2f(this.getDirection());
            endPos.scale(this.getAttackRange());
            
            Vector2f.add(startPos, endPos, endPos);
            
            ArrayList<GameObject>  rayRes = getGame().rayCast(startPos, endPos);
            //ArrayList<Mob> enemys = new ArrayList();
            
            for(GameObject go : rayRes)
            {
                if(wielder.isEnemy(go))
                {
                    if(targets.add((Mob)go))
                    {
                        ((Mob)go).damage((int)this.getStabDamage() * wielder.getStrength()); 
                    }        
                }
            }
            
//            for(Mob mob : targets)
//            {
//                mob.damage((int)this.getStabDamage() * wielder.getStrength());  
//            }
    }
    @Override
    public Vector2f getPos()
    {
        if(wielder == null)
        {    
            return pos;
        }
        else
        {
            Vector2f offsetRotated =  Util.rotateRadians(Util.angleRadians(((Arm)equipedOn).getRootDirectionOffset()), ((Arm)equipedOn).getRootWeaponOffset());
            return Vector2f.add(wielder.getPos(),offsetRotated, offsetRotated);
        }
    }
    
    public Vector2f getDirection()
    {
        if(wielder == null)
        {    
            return offsetDirection;
        }
        else
        {
//            Vector2f offsetRotated =  Util.rotateRadians(Util.angleRadians(wielder.getDirection()), ((Arm)equipedOn).getRootWeaponOffset());
//            return Vector2f.add(wielder.getPos(),offsetRotated, offsetRotated);
            //return Util.angleRadians(wielder.getDirection());
            return Util.rotateRotationVector(this.equipedOn.getRootDirectionOffset(), this.offsetDirection, new Vector2f());
        }
    }
    
    public void clearTargets()
    {
        targets.clear();
    }
}
