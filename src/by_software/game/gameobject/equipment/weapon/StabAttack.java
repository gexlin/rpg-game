/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package by_software.game.gameobject.equipment.weapon;

import by_software.engine.render.AnimationSet;
import by_software.engine.GameObject;
import static by_software.engine.GameObject.getGame;
import by_software.game.gameobject.mob.Mob;
import by_software.game.gameobject.mob.body.Arm;
import java.util.ArrayList;
import org.lwjgl.util.vector.Vector2f;

/**
 *
 * @author Nigel
 */

public class StabAttack extends Attack
{

    public StabAttack(Weapon weapon)
    {
        super(weapon,200);    
    }
    
    @Override
    public boolean attack(Mob attacker, Arm arm)
    {
        boolean result = false;
        if(arm.getAttackDelay().isOver())
        {
            arm.setCurrentAnimation(AnimationSet.AnimationType.ATTACK_STAB_RIGHT);
            startPos = weapon.getPos();
            endPos = new Vector2f(attacker.getDirection());
            endPos.scale(weapon.getAttackRange());
            
            Vector2f.add(startPos, endPos, endPos);
            
            ArrayList<GameObject>  rayRes = getGame().rayCast(startPos, endPos);
            ArrayList<Mob> enemys = new ArrayList();
            
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
            arm.setAttackDelay((int)(weapon.getAttackDelayMod()* attackDelay));
            
        }
        return result;
    }

}
