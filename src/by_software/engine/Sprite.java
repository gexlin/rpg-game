/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package by_software.engine;

import static org.lwjgl.opengl.GL11.*;
import org.lwjgl.util.vector.Vector2f;
import org.lwjgl.util.vector.Vector3f;

/**
 *
 * @author Nigel
 */
public class Sprite
{
    
   private Vector3f color;
   private Vector2f size;   
   
   public Sprite(Vector3f color, Vector2f size)
   {
       this.color = color;
       this.size = size;
   }
   
   public Sprite(float r, float g, float b, float sX, float sY)
   {
       this(new Vector3f(r,g,b),new Vector2f(sX,sY));
   }
   
   
   public void render()
   {
       glColor3f(color.x,color.y,color.z);
       glBegin(GL_QUADS);
       {
            glVertex2f(-size.x / 2, -size.y / 2  );
            glVertex2f(-size.x / 2,  size.y / 2  );
            glVertex2f( size.x / 2,  size.y / 2 );
            glVertex2f( size.x / 2, -size.y / 2 );
           
       }
       glEnd();
   }

    public Vector2f getSize()
    {
        return size;
    }
    
    public void setSize(Vector2f size)
    {
        this.size = size;
    }
}
