/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package by_software.engine;

import static org.lwjgl.opengl.GL11.GL_BLEND;
import static org.lwjgl.opengl.GL11.GL_ONE_MINUS_SRC_ALPHA;
import static org.lwjgl.opengl.GL11.GL_QUADS;
import static org.lwjgl.opengl.GL11.GL_SRC_ALPHA;
import static org.lwjgl.opengl.GL11.GL_TEXTURE_2D;
import static org.lwjgl.opengl.GL11.GL_TEXTURE_WRAP_S;
import static org.lwjgl.opengl.GL11.GL_TEXTURE_WRAP_T;
import static org.lwjgl.opengl.GL11.glBegin;
import static org.lwjgl.opengl.GL11.glBlendFunc;
import static org.lwjgl.opengl.GL11.glDisable;
import static org.lwjgl.opengl.GL11.glEnable;
import static org.lwjgl.opengl.GL11.glEnd;
import static org.lwjgl.opengl.GL11.glTexCoord2f;
import static org.lwjgl.opengl.GL11.glTexParameteri;
import static org.lwjgl.opengl.GL11.glVertex2f;
import static org.lwjgl.opengl.GL12.GL_CLAMP_TO_EDGE;
import org.newdawn.slick.opengl.Texture;
import org.lwjgl.util.vector.Vector2f;
import by_software.game.Util;
/**
 *
 * @author Nigel
 */
public class SpriteSheet
{private static String dirPath = "C:/Users/Nigel/Documents/NetBeansProjects/RPGGame/src/by_software/res/";
    
    public static final SpriteSheet PLAYER_LEGS = new SpriteSheet(dirPath + "player/legs.png" ,64,64); 
    
    private final int tw;
    private final int th;
    private final int numX;
    private final int numY;
    private final int numOfTiles;
    
    private final Vector2f size;
    private Texture sheet;
    
    public SpriteSheet(String path,int tw, int th)
    {
        this.th= th;
        this.tw = tw;
        this.size = new Vector2f(tw,th);
        sheet = Util.loadTexture(path);
        numY = sheet.getImageHeight() / th;
        numX = sheet.getImageWidth() / tw;   
        numOfTiles = numY * numX;
    }
    
     
    public void render(int index)
   {
       
    
       if(sheet == null)
       {
           
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
            float startX = (((float)(index % numX))/numX);
            float startY = (float)(index  / numX) ;
            float endX = startX +(1 /(float) numX);      
            float endY = startY + (1 /(float)numY);  
            
            System.out.println(startX +" " + startY +" "+ + endX +"  " +(1 / (float)numY));
            
            glEnable(GL_TEXTURE_2D);
            glTexParameteri( GL_TEXTURE_2D, GL_TEXTURE_WRAP_S, GL_CLAMP_TO_EDGE );
            glTexParameteri( GL_TEXTURE_2D, GL_TEXTURE_WRAP_T, GL_CLAMP_TO_EDGE );
            //glColor3f(1,1,1);
            glEnable(GL_BLEND);
            glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
            sheet.bind();
            glBegin(GL_QUADS);
                 glTexCoord2f( startX,endY);
                glVertex2f(-size.x / 2, -size.y / 2  );
                
                 glTexCoord2f( startX,startY );
                glVertex2f(-size.x / 2,  size.y / 2  );
                
                 glTexCoord2f(endX,startY );
                glVertex2f( size.x / 2,  size.y / 2 );
                
                 glTexCoord2f(endX,endY);
               glVertex2f( size.x / 2, -size.y / 2 );   
//              glTexCoord2f(0,1);
//                glVertex2f(-size.x / 2, -size.y / 2  );
//                
//                 glTexCoord2f(0,0);
//                glVertex2f(-size.x / 2,  size.y / 2  );
//                
//                 glTexCoord2f(.125f,0);
//                glVertex2f( size.x / 2,  size.y / 2 );
//                
//                 glTexCoord2f(.125f,1);
//                glVertex2f( size.x / 2, -size.y / 2 );   
//                
            glEnd();
            glDisable(GL_TEXTURE_2D);
       }
   }
    
    public int getNumberOfTiles(){ return this.numOfTiles; }

    public int getNumberOfTilesX() { return numX; }
    public int getNumberOfTilesY() { return numY; }
    
}   
