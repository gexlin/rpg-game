/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package by_software.game.gameobject;

/**
 *
 * @author Nigel
 */
public class Stats
{
    private int xp;
   // private int maxHealth;
    private int health;
    private final boolean levelAble;
    private int level;
    private float speed;
    private float attackRate;
    
    public Stats(int xp, boolean levelAble)
    {
        speed = 50;
        attackRate = 1f;
        this.levelAble = levelAble;
        if(levelAble)
        {
            this.xp = xp;
        }
        else
        {
            this.xp = - 1;
            this.level = xp;
        }
        this.health = getMaxHealth();
    }

    public float getSpeed()
    { 
       return speed;
    }
    
    public int getLevel()
    {
        if(!levelAble)
        {
            return level;
        }
        return xp/100 + 1;
    }
    
    public int getMaxHealth()
    {
        return getLevel() * 10;
    }
    
    public int getHealth()
    {
        int max = getMaxHealth();
        if(health > max)
        {
            health = max;
        }
        return health;
    }
 
    public int getStrength()
    {        
        return getLevel() * 4;
    }
    
    public int getXp()
    {
        return xp;
    }
    public float getAttackRate()
    {
        return attackRate;
    }
    public void addXp(int xp)
    {
        this.xp += xp;
    }
    public void damage(int dmg)
    {
        health -= dmg;
        System.out.println(health + "  ");
    }
    
 
    
}
