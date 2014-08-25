/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package by_software.game.gameobject.equipment.weapon;

import by_software.game.gameobject.Equipment.Slots;
import by_software.game.gameobject.equipment.EquippableItem;
import by_software.game.gameobject.mob.Mob;
import static org.lwjgl.opengl.GL11.glPopMatrix;
import static org.lwjgl.opengl.GL11.glPushMatrix;
import static org.lwjgl.opengl.GL11.glTranslatef;
import org.lwjgl.util.vector.Vector2f;
import org.lwjgl.util.vector.Vector3f;

/**
 *
 * @author Nigel
 */
public abstract class Weapon extends EquippableItem
{
    private float attackRange;
   
    private float stabDamage;
    private float slashDamage;
    private float bluntDamage;
    private float weight;
    
    private float attackDelayMod;
    private float attackSpeedMod;
    
    private Attack[] attacks;
    
    private Slots equipedIn;
    
    public Weapon(String name, Vector2f pos, Vector2f size, Vector3f color,float attackRange,
                               float stabDamage, float slashDamage, float bluntDamage, float weight,float attackDelayMod, float attackSpeedMod, String path)
    { //TODO change slot
        super(name, Slots.RIGHT_HAND_1, pos, size, color, path);
        this.attacks = new  Attack[3];
        this.stabDamage = stabDamage;
        this.slashDamage = slashDamage;
        this.bluntDamage = bluntDamage;
        this.weight = weight;
        this.attackRange = attackRange;
        this.attackDelayMod = attackDelayMod;
        this.attackSpeedMod = attackSpeedMod;
        this.attacks = attacks;
    }
    
    
    
    public boolean attack(int attackIndex, Mob attacker)
    {
       return attacks[attackIndex].attack(attacker);
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
    public void setEquipedIn(Slots equipedIn)
    {
        this.equipedIn = equipedIn;
    }
    
    public Slots getEquipedIn()
    {
        return equipedIn;
    }
    
    @Override
    public void render()
    {   
        if(equipedIn == null)
        {
            glPushMatrix();
            {
                glTranslatef(pos.x,pos.y,0);
                getFrame().render();
            }
            glPopMatrix();   
        } else 
        {
            Vector2f offset = equipedIn.getOffset();
            
            glPushMatrix();
            {
                glTranslatef(offset.x,offset.y,0);
                getFrame().render();
            }
            glPopMatrix(); 
        }
        
    }
  
}
