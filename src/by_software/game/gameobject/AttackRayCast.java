/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package by_software.game.gameobject;

import by_software.game.Game;
import static org.lwjgl.opengl.GL11.GL_LINES;
import static org.lwjgl.opengl.GL11.glBegin;
import static org.lwjgl.opengl.GL11.glColor3f;
import static org.lwjgl.opengl.GL11.glEnd;
import static org.lwjgl.opengl.GL11.glVertex2f;
import org.lwjgl.util.vector.Vector2f;

/**
 *
 * @author Nigel
 */
public class AttackRayCast
{
    private Vector2f startPos;
    private Vector2f endPos;
    
    public AttackRayCast(Vector2f startPos, Vector2f endPos)
    {
        this.startPos = startPos;
        this.endPos = endPos;
    }
    public void render()
    {
//        if(Game.isAttackRayVisable())
//        {
            glColor3f(1f,1f,0f);
            glBegin(GL_LINES);
            {
                glVertex2f(startPos.x, startPos.y  );
                glVertex2f(endPos.x,  endPos.y  );
            }
            glEnd();
//        }
    }
    public void set(Vector2f startPos, Vector2f endPos)
    {
        System.out.print( startPos + "  " + endPos);
        this.startPos = startPos;
        this.endPos = endPos;
    }
}
