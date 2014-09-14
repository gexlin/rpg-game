/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package by_software.engine.render;

import by_software.game.Util;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL12.GL_CLAMP_TO_EDGE;
import org.lwjgl.util.vector.Vector2f;
import org.lwjgl.util.vector.Vector3f;
import org.newdawn.slick.opengl.Texture;

/**
 *
 * @author Nigel
 */
public class Sprite
{
  
   private Vector3f color;
   private Texture texture; 
   private Vector2f size;   
   
   private SpriteSheet sheet;
   private int spriteIndex;
   
   
   public static Sprite[] MakeSprites(Vector2f size ,  SpriteSheet sheet)
   {
       Sprite[] sprites = new Sprite[sheet.getNumberOfTiles()];

       for(int i = 0; i < sprites.length ; i++)
       {
           sprites[i] = new Sprite(size,sheet,i);
       }
       return sprites;
   }
   
   public Sprite(Vector3f color, Vector2f size)
   {
       this.color = color;
       this.size = new Vector2f(size);
   }
   
   public Sprite(Sprite that)
   {
        this.color       = new Vector3f(that.color);
        this.texture     = that.texture; 
        this.size        = new Vector2f(that.size);   
        this.sheet       = that.sheet;
        this.spriteIndex = that.spriteIndex;
   }
   
   public Sprite(Vector3f color, Vector2f size,SpriteSheet sheet,int spriteIndex)
   {
       this.color = color;
       this.size =  new Vector2f(size);
       this.sheet = sheet;
       this.spriteIndex = spriteIndex;
   }
   public Sprite( Vector2f size,SpriteSheet sheet,int spriteIndex)
   {
       this.color = new Vector3f(1,1,1);
       this.size = new Vector2f(size);
       this.sheet = sheet;
       this.spriteIndex = spriteIndex;
   }
   public Sprite(Vector3f color, Vector2f size, String path)
   {
       this(size,path);
       this.color = color;
   }
   
   public Sprite(Vector2f size, String path)
   {
       this.color = new Vector3f(1,1,1);
       this.size =  new Vector2f(size);
       texture = Util.loadTexture(path);
   }
   

   public Sprite(float r, float g, float b, float sX, float sY)
   {
       this(new Vector3f(r,g,b),new Vector2f(sX,sY));
   }
   
   
   
   public void render()
   {
       if(sheet != null)
       {
           sheet.render(spriteIndex, size);
       }
       else if(texture == null)
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
       else
       {
            glEnable(GL_TEXTURE_2D);
            glTexParameteri( GL_TEXTURE_2D, GL_TEXTURE_WRAP_S, GL_CLAMP_TO_EDGE );
            glTexParameteri( GL_TEXTURE_2D, GL_TEXTURE_WRAP_T, GL_CLAMP_TO_EDGE );
            glColor3f(color.x,color.y,color.z);
            //glColor3f(1,1,1);
            glEnable(GL_BLEND);
            glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
            texture.bind();
            glBegin(GL_QUADS);
                 glTexCoord2f(0,1);
                glVertex2f(-size.x / 2, -size.y / 2  );
                
                 glTexCoord2f(0,0);
                glVertex2f(-size.x / 2,  size.y / 2  );
                
                 glTexCoord2f(1,0);
                glVertex2f( size.x / 2,  size.y / 2 );
                
                 glTexCoord2f(1,1);
                glVertex2f( size.x / 2, -size.y / 2 );   
            glEnd();
            glDisable(GL_TEXTURE_2D);
       }
   }

    public Vector2f getSize()
    {
        return size;
    }
    
    public void setSize(Vector2f size)
    {
        this.size = size;
    }
    
    public void scale(float scale)
    {
        size.scale(scale);
    }

}
