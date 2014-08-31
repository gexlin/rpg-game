/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package by_software.game.gameobject.mob.body;

import by_software.engine.AnimationSet;
import by_software.game.Util;
import by_software.game.gameobject.equipment.weapon.Weapon;
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
    private Vector2f weponPos;
    
    public Arm(Vector2f pos, Vector2f offsetPos, Vector2f direction, ArrayList<BodyPart> body, AnimationSet animations)
    {
        super(pos, offsetPos, direction, body, animations);
    }
    public Arm(Vector2f pos,Vector2f offsetPos,Vector2f direction,  AnimationSet  animations)
    {
      super(pos, offsetPos, direction, animations);
    }
    public Arm(Vector2f pos,Vector2f offsetPos,Vector2f direction)
    {
       super(pos, offsetPos, direction);
    }
    
    @Override
    public void render()
    {
        
        glPushMatrix();
        {
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
            animations.render(currentAnimation);
        }
        glPopMatrix();
        
    }
    
}
