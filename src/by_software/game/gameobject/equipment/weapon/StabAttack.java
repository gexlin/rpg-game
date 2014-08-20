/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package by_software.game.gameobject.equipment.weapon;

import by_software.engine.GameObject;
import static by_software.engine.GameObject.getGame;
import by_software.engine.GameObjectType;
import by_software.game.gameobject.mob.Mob;
import java.util.ArrayList;
import org.lwjgl.util.vector.Vector2f;

/**
 *
 * @author Nigel
 */

public class StabAttack implements Attack
{

    private Vector2f startPos;
    private Vector2f endPos;
            
    private Weapon weapon;
    private float attackDelay;
    public StabAttack(Weapon weapon)
    {
        this.weapon = weapon;
        startPos = weapon.getPos();
        attackDelay = 200;
            
    }
    
    @Override
    public boolean attack(Mob attacker)
    {
        boolean result = false;
        if(attacker.getAttackDelay().isOver())
        {
            
            startPos = weapon.getPos();
            endPos = new Vector2f(attacker.getDirection());
            System.out.println(weapon +" "+ weapon.getAttackRange());
            endPos.scale(weapon.getAttackRange());
            
            Vector2f.add(startPos, endPos, endPos);
            
            System.out.println("ATTACK");
            
            ArrayList<GameObject>  rayRes = getGame().rayCast(startPos, endPos);
            ArrayList<Mob> enemys = new ArrayList();
            //direction.normalise();
            for(GameObject go : rayRes)
            {
                if(attacker.isEnemy(go))
                {
                    enemys.add((Mob)go);
                }
            }
            
            for(Mob mob : enemys)
            {
                mob.damage((int)weapon.getStabDamage() * attacker.getStrength());  
                result = true;
            }
            attacker.setAttackDelay((int)(weapon.getAttackDelayMod()* attackDelay));
            
        }
        return result;
    }

}
