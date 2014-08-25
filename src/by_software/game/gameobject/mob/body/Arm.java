/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package by_software.game.gameobject.mob.body;

import by_software.engine.Animation;
import by_software.game.gameobject.equipment.weapon.Weapon;
import java.util.ArrayList;
import org.lwjgl.util.vector.Vector2f;

/**
 *
 * @author Nigel
 */
public class Arm extends BodyPart
{

    private Weapon weapon;
    
    public Arm(Vector2f pos, Vector2f offsetPos, Vector2f direction, ArrayList<BodyPart> body, Animation[] animations)
    {
        super(pos, offsetPos, direction, body, animations);
    }
    public Arm(Vector2f pos,Vector2f offsetPos,Vector2f direction,  Animation[] animations)
    {
      super(pos, offsetPos, direction, animations);
    }
    public Arm(Vector2f pos,Vector2f offsetPos,Vector2f direction)
    {
       super(pos, offsetPos, direction);
    }
    
}
