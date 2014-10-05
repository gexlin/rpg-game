/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package by_software.engine.render;

import by_software.engine.Time;

/**
 *
 * @author Nigel
 */


public class Frame
{
   private final static int milesFromNano =  1000000;
    
    private int length;
    private Sprite sprite;
    private boolean rendering = false;
    private long frameEnd;
    private boolean activeFrame = false;
    private boolean triggeredAlready = false;  
    
    public Frame(int lenghtMileSec, Sprite sprite, boolean active)
    {
        this.length = lenghtMileSec * milesFromNano;
        this.sprite = sprite;
        this.activeFrame = active;
    }
    
    public Frame(Frame that)
    {
        this.length = that.length;
        this.sprite = new Sprite(that.sprite);
        this.activeFrame = that.activeFrame;
        
    }
    
    public static Frame[] frameArray(int[] lenghtMileSec, Sprite[] sprites, int beginActive , int endActive)
    {
        
        Frame[] frames = new Frame[sprites.length];
        
        for(int i = 0; i < sprites.length; i++)   
        { 
            
            int j = i % lenghtMileSec.length;
            boolean active = false;
            if(i >= beginActive && i < endActive)
            {
                active = true;
            }
            frames[i] = new Frame(lenghtMileSec[j], sprites[i],active);
        }
        return frames;
    }
    
    
    public static Frame[] frameArray(int lenghtMileSec, Sprite[] sprites, int beginActive , int endActive)
    {    
        Frame[] frames = new Frame[sprites.length];
        
        for(int i = 0; i < sprites.length; i++)   
        { 
            boolean active = false;
            if(i >= beginActive && i < endActive)
            {
                active = true;
            }
            frames[i] = new Frame(lenghtMileSec, sprites[i], active);
        }
        return frames;
    }
    
    public static Frame[] clone( Frame[] that)
    {
        Frame[] clone = new Frame[that.length];
        
        for(int i = 0;i < that.length; i++)
        {
            clone[i] = new Frame(that[i]);
        }
        
        return clone;
    }
    
    
    public boolean render()
    {
        if(!rendering)
        {
            rendering = true;
            frameEnd = Time.getTime() + length;
        }
        sprite.render();

        if(frameEnd <= Time.getTime())
        {
            rendering = false;
            return true;
        } 
        return false;
    }
        
    public void scale(float scale)
    {
        sprite.scale(scale);
    }

    public Sprite getSprite()
    {
        return sprite;
    }

    public int getLength() 
    {
        return length / milesFromNano;
    }

    public void setLength(int lenghtMileSec) 
    {
        this.length = lenghtMileSec * milesFromNano;
    }
    

    public boolean isActive()
    {
        return activeFrame;
    }

    public boolean isTriggeredAlready()
    {
        return triggeredAlready;
    }
    
    public boolean triggerEvent()
    {
        if(isActive() && !isTriggeredAlready())
        {
                return triggeredAlready = true;   
        }
        return false;
    }
    
    public void rearm()
    {
        triggeredAlready = false;
    }
}
