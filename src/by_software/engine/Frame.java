/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package by_software.engine;

import static by_software.engine.Sprite.PLAYER;
import java.util.HashMap;
import org.lwjgl.util.vector.Vector2f;

/**
 *
 * @author Nigel
 */


public class Frame
{
    public final static  HashMap<String,Frame[]> PLAYER = new HashMap<>(5,1f);
     
    static
    {
       PLAYER.put("helm",           new Frame[]{new Frame(250,Sprite.PLAYER.get("helm"))});
       PLAYER.put("body",           new Frame[]{new Frame(250,Sprite.PLAYER.get("body"))} );
       PLAYER.put("poldren-left",   new Frame[]{new Frame(250,Sprite.PLAYER.get("poldren-left"))} );
       PLAYER.put("poldren-right",  new Frame[]{new Frame(400,Sprite.PLAYER.get("poldren-right"))} );
       PLAYER.put("arm-left",       new Frame[]{new Frame(400,Sprite.PLAYER.get("arm-left"))});
       PLAYER.put("arm-right",      new Frame[]{new Frame(400,Sprite.PLAYER.get("arm-right"))} );

    }
    
    private int length;
    private Sprite sprite;
    private boolean rendering = false;
    private long frameEnd;
    
    public Frame(int lenghtMileSec, Sprite sprite)
    {
        this.length = lenghtMileSec * 1000;
        this.sprite = sprite;
    }
    
    public boolean render()
    {
        if(!rendering)
        {
            rendering = true;
            frameEnd = Time.getTime() + length;
        }
        sprite.render();
        
       
        if(frameEnd >= Time.getTime())
        {
            rendering = false;
            return true;
        } 
        return false;
    }
        
        
}
