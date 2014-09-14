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
    
    public Frame(int lenghtMileSec, Sprite sprite)
    {
        this.length = lenghtMileSec * milesFromNano;
        this.sprite = sprite;
    }
    
    public Frame(Frame that)
    {
        this.length = that.length;
        this.sprite = new Sprite(that.sprite);
    }
    
    public static Frame[] frameArray(int[] lenghtMileSec, Sprite[] sprites)
    {
        
        Frame[] frames = new Frame[sprites.length];
        
        for(int i = 0; i < sprites.length; i++)   
        { 
            int j = i % lenghtMileSec.length;
            frames[i] = new Frame(lenghtMileSec[j], sprites[i]);
        }
        return frames;
    }
    
    
    public static Frame[] frameArray(int lenghtMileSec, Sprite[] sprites)
    {
        
        Frame[] frames = new Frame[sprites.length];
        
        for(int i = 0; i < sprites.length; i++)   
        { 
            frames[i] = new Frame(lenghtMileSec, sprites[i]);
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
}
