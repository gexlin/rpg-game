/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package by_software.engine;

import java.awt.Rectangle;
import java.awt.geom.Line2D;
import static org.lwjgl.opengl.GL11.GL_QUADS;
import static org.lwjgl.opengl.GL11.glBegin;
import static org.lwjgl.opengl.GL11.glColor3f;
import static org.lwjgl.opengl.GL11.glEnd;
import static org.lwjgl.opengl.GL11.glVertex2f;

/**
 *
 * @author Nigel
 */
public class Physics
{
    public static GameObject checkCollision(GameObject go1, GameObject go2)
    {
        Rectangle r1 = makeHitBox(go1);
        return checkCollision(r1,go2);
    }
    
    public static GameObject checkCollision(Rectangle r1, GameObject go)
    {
       
        Rectangle r = makeHitBox(go);
        boolean res = r1.intersects(r);
        if(res)
        {
            return go;
        }
        else
        {
            return null;
        }
    }
    
    public static GameObject checkCollision(Line2D l1, GameObject go)
    {
       
        Rectangle r = makeHitBox(go);
        boolean res = l1.intersects(r);
        if(res)
        {
            return go;
        }
        else
        {
            return null;
        }
    }
   
    
    
    
    
    public static Rectangle makeHitBox( GameObject go)
    {
       return new Rectangle((int)(go.getX() - go.getSizeX()/2),
                            (int)(go.getY() - go.getSizeY()/2),
                            (int)go.getSizeX() ,
                            (int)go.getSizeY());
        
    }
}
