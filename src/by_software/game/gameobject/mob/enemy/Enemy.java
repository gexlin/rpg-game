/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package by_software.game.gameobject.mob.enemy;

import by_software.engine.GameObject;
import by_software.engine.GameObjectType;
import by_software.engine.Time;
import by_software.game.Util;
import by_software.game.gameobject.equipment.weapon.Weapon;
import by_software.game.gameobject.mob.Faction;
import by_software.game.gameobject.mob.Mob;
import java.util.ArrayList;
import org.lwjgl.util.vector.Vector2f;
import org.lwjgl.util.vector.Vector3f;

/**
 *
 * @author Nigel
 */
public class Enemy extends Mob
{
    private Mob target;
    protected float attackRange = 32f;
    protected static final int ATTACK_DELAY = 100;
    protected float MELEE_RANGE = 32f;
    protected float SIGHT_RANGE = 128f;
    
    
    public Enemy(String name, Faction faction ,Vector2f pos, Vector2f size, Vector3f color, int type,int level,int inventorySize, Weapon defaultWeapon)
    {
        super(name, faction, pos, size, color, GameObjectType.ENEMY,level,false,inventorySize,ATTACK_DELAY,defaultWeapon);
        target = null;
    }
    
    
    @Override
    public void update()
    {
        if(target == null)
        {
            look();
        }
        else
        {
            
            if(Util.lineOfSight(this, target) &&  Util.distance(pos, getTarget().getPos()) <= attackRange)
            {
                if(attackDelay.isOver())
                {
                    attack(0);
                }
            }
            else
            {
                chase();
            }
        }
        
        
        if(stats.getHealth() <= 0)
        {
          
            death();
        }
        
    }
    
    protected void chase()
    {
        Vector2f speed = new Vector2f();
        Vector2f.sub(getTarget().getPos(),pos, speed);
        
        move(speed);
    }

    protected void look()
    {
        ArrayList<GameObject> objects = getGame().sphereCollide(pos, SIGHT_RANGE);
        for(GameObject go: objects)
        {
            if(go.getType() == GameObjectType.PLAYER)
            {
                System.out.println("I see you.");
                setTarget((Mob)go);
            }
        }
        
    }
    @Override
     protected void move(Vector2f move)
    {
        if(!attacking)
        {
            move.normalise();
            direction.set(move);
            move.scale((float)(getSpeed() * Time.getDelta()));
            Vector2f.add(move, pos, pos);
        }
    }
    protected void idle()
    {
    
    }
    
    protected void death()
    {
        this.remove();
    }
    
    /*@Override
    protected boolean attack()
    {
        super.attack();
        if(Util.distance(pos, getTarget().getPos()) > MELEE_RANGE)
        {
            return false;
        }
        getTarget().damage(this.getStrength());
        System.out.println("attack you!!!");
        return true;
    }*/
    
    public void setTarget(Mob target){this.target = target;}

    public Mob getTarget() { return target;}
    
}
