/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package by_software.game;

import by_software.engine.GameObject;
import by_software.engine.render.Sprite;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.lwjgl.util.vector.Vector2f;
import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;

/**
 *
 * @author Nigel
 */
public class Util
{
    private static Vector2f origin = new Vector2f(0,1);
    
    public static boolean lineOfSight(GameObject go1, GameObject go2)
    {
        return true;
    }
    public static float distance(Vector2f pos1, Vector2f pos2 )
    {
       double x =  pos2.x -pos1.x;
       double y =  pos2.y -pos1.y;
       
       return (float) Math.sqrt( x*x + y*y );  
    }
    
    public static float angleRadians(Vector2f a, Vector2f b )
    {
        float result = (float)(Vector2f.angle(a, b));
        if(a.x > 0)
        {
            result = ((float)Math.PI)*2 - result;
        }
        return result;
        
        
    }
    
    public static float angleRadians(Vector2f a)
    {
        
        return  angleRadians(a, origin);
    }
    public static float angleDegrees(Vector2f a, Vector2f b )
    { 
        float result = (float)Math.toDegrees(Vector2f.angle(a, b));
        if(a.x > 0)
        {
            result = 360 - result;
        }
        return result;
       
    }
    
    public static float angleDegrees(Vector2f a)
    {
        return  angleDegrees(a, origin);
    }
    
   
    
    
    
    public static Vector2f rotateRadians(double angle, Vector2f v)
    {
        double cos  = Math.cos(angle);
        double sin  = Math.sin(angle);
         
       return new Vector2f( (float)(cos * v.getX() - sin * v.getY()),
                            (float)(sin * v.getX() + cos * v.getY()));
    }
    
    public static Vector2f rotateDegrees(double angle, Vector2f v)
    {   System.out.print( angle );
        
    angle = Math.toRadians(angle);
        double cos  = Math.cos(angle);
        double sin  = Math.sin(angle);
        System.out.println( angle + " sin:" + sin + "  cos:" + cos);
        return new Vector2f( (float)(cos * v.getX() - sin * v.getY()),
                            (float)(sin * v.getX() + cos * v.getY()));
        //TODO dont return new vector change one passed in.
    }
    
    public static Texture loadTexture(String path)
    {
        Texture texture = null ;
        if(path.equals(""))
        {
            return texture;
        }
        try
        {
             texture = TextureLoader.getTexture("PNG", new FileInputStream(new File( path)));
        }
        catch (FileNotFoundException ex)
        {  
            texture = null ;
            Logger.getLogger(Sprite.class.getName()).log(Level.SEVERE, null, ex);
        }
        catch (IOException ex)
        {   
            texture = null ;
            Logger.getLogger(Sprite.class.getName()).log(Level.SEVERE, null, ex);
        }
        return texture;
    }
   
    
    
    
    public static void main(String[] args)
    {
        Vector2f a = new Vector2f(0f,1f);
        Vector2f b = new Vector2f(1f,0f);
        System.out.println(Math.toDegrees(Vector2f.angle(a, b))); 
    }
    public static void rotate(Vector2f vector, Vector2f angle, Vector2f dest )
    {
        float a = angleRadians(angle);
        float sin = (float)Math.sin(a);
        float cos = (float)Math.cos(a);       
        
      dest.set((float)(cos * vector.getX() - sin * vector.getY()),
               (float)(sin * vector.getX() + cos * vector.getY()));
    }
            
}
