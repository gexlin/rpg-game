/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package by_software.game.gameobject.equipment.weapon;

import by_software.game.gameobject.mob.Mob;
import org.lwjgl.util.vector.Vector2f;

/**
 *
 * @author Nigel
 */
public abstract class Attack
{
    protected Vector2f startPos;
    protected Vector2f endPos;
            
    protected Weapon weapon;
    protected float attackDelay;
    
    public Attack(Weapon weapon, float attackDelay)
    {
        this.weapon = weapon;
        startPos = weapon.getPos();
        endPos = new Vector2f();
        this.attackDelay = attackDelay;
    }

    public abstract boolean attack(Mob attacker);

}
