/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package by_software.game.gameobject.mob;

import by_software.game.gameobject.mob.body.Body;
import by_software.engine.Delay;
import by_software.engine.GameObject;
import static by_software.engine.GameObject.getGame;
import by_software.engine.GameObjectType;
import by_software.engine.Time;
import by_software.game.Util;
import by_software.game.gameobject.Equipment;
import by_software.game.gameobject.Equipment.Slots;
import by_software.game.gameobject.Inventory;
import by_software.game.gameobject.Item;
import by_software.game.gameobject.Stats;
import by_software.game.gameobject.equipment.armour.Armour;
import by_software.game.gameobject.equipment.weapon.Weapon;
import java.util.ArrayList;
import static org.lwjgl.opengl.GL11.glPopMatrix;
import static org.lwjgl.opengl.GL11.glPushMatrix;
import static org.lwjgl.opengl.GL11.glRotated;
import static org.lwjgl.opengl.GL11.glTranslatef;
import org.lwjgl.util.vector.Vector2f;
import org.lwjgl.util.vector.Vector3f;

/**
 *
 * @author Nigel
 */
public class Mob extends GameObject
{
    protected Stats stats;
    protected Inventory inventory;
    protected Delay attackDelay;
    protected int attackRecover;
    protected boolean attacking;
    
    protected Vector2f direction;
    protected Equipment equipment;
    protected Weapon defaultWeapon;
    protected Faction faction;
    protected Body body;
    public Mob(String name, Faction faction, Vector2f pos, Vector2f size, Vector3f color, GameObjectType type,int xp, boolean levelAble, int inventorySize, int attackDelay, Weapon defaultWeapon)
    {
        
        super(name,pos, size, color, type);
        direction = new Vector2f(0,1);
        stats = new Stats(xp,levelAble);
        inventory = new Inventory(inventorySize,this);
        this.attackRecover = attackDelay;
        this.attackDelay = new Delay(attackDelay);
        this.attackDelay.zeroDelay();
        this.defaultWeapon = defaultWeapon;
        this.faction = faction;
        equipment = new Equipment(this);
        defaultWeapon.setWielder(this);
    }
    
    protected boolean attack()
    {
        
        if(attackDelay.isOver())
        {
            equipment.attackRight();
        }
        return false;
    }
    
    public void damage(int dmg)
    {
        stats.damage(dmg);
    }
    
    protected void move( float magX, float magY)
    {
        if(!(magX == 0 && magY ==0) )
        {
            Vector2f move = new Vector2f(magX,magY);
            move(move);
        }
    }
    protected void setDirection(float x, float y)
    {
        if(!( x == 0 && y ==0))
        {
            setDirection(new Vector2f(x,y));
        }
    }
 
    protected void move(Vector2f move)
    {
        if(!attacking)
        {
            move.normalise();
            //direction.set(move);
            move.scale((float)(getSpeed() * Time.getDelta()));
            Vector2f.add(move, pos, pos);
        }
    }
    protected void moveLocal(float x , float y)
    { 
        if(!( x == 0 && y ==0))
        {
            moveLocal(new Vector2f(x,y));
        }
    }
    protected void moveLocal(Vector2f move)
    {
        if(!attacking)
        {
            move.normalise();
            Util.rotate(move, direction, move);
            //direction.set(move);
            move.scale((float)(getSpeed() * Time.getDelta()));
            Vector2f.add(move, pos, pos);
        }
    }
    protected void setDirection(Vector2f dir)
    {
        dir.normalise();
        direction.set(dir);
    }
    public boolean addItem(Item item)
    {
        return inventory.add(item);
    }
    
    public boolean removeItem(Item item)
    {
       return inventory.remove(item);
    }
    public void pickUp()
    {
       ArrayList<GameObject> objects = getGame().rectangleCollide(pos, size);
       for(GameObject go : objects)
       {
           if(go.getType() == GameObjectType.ITEM)
           {
               ((Item)go).pickUp();
               addItem((Item)go);
           }
       }
    }
    public boolean equip(Slots ws, Weapon weapon)
    {
        return equipment.equip(ws,weapon);
    }
    public boolean equip(Armour armour)
    {
        return equipment.equip(armour);
    }
    //Stats
    public float getSpeed(){return stats.getSpeed();}
    public int getLevel(){return stats.getLevel();}
    public int getMaxHealth(){return stats.getMaxHealth();}
    public int getHealth(){return stats.getHealth();}
    public int getStrength(){return stats.getStrength();}
    public void addXp(int xp)
    {
        stats.addXp(xp);
    }
    
    public Delay getAttackDelay(){return attackDelay;}
    public void setAttackDelay(int delay){ attackDelay.start(delay);}
    
    public Vector2f getDirection(){return direction;}
    public Weapon getDefaultWeapon(){return defaultWeapon;}
    public Faction getFaction()
    {
        return this.faction;
    }
    
    public boolean isEnemy(GameObject go)
    {
        if(go.getType() == GameObjectType.ITEM)
        {
            return false;
        }
        return faction.isEnemy((Mob)go);
    }
    @Override
    public void render()
    {
        glPushMatrix();
        {
            
            
            glTranslatef(pos.x,pos.y,0);
            glRotated(Util.angleDegrees(direction), 0f, 0f, 1f);
            
            getFrame().render();
            equipment.render();
        }
        glPopMatrix();
    
    }
}
