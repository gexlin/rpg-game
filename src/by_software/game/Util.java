/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package by_software.game;

import by_software.engine.GameObject;
import org.lwjgl.util.vector.Vector2f;

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
    }
    
    public static void main(String[] args)
    {
        Vector2f a = new Vector2f(0f,1f);
        Vector2f b = new Vector2f(1f,0f);
        System.out.println(Math.toDegrees(Vector2f.angle(a, b)));
        
    }
            
}
