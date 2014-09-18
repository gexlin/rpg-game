/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package by_software.engine.render;

import static by_software.engine.render.Frame.frameArray;

/**
 *
 * @author Nigel
 */
public class Animation
{  
    private Frame[] frames;
    private int currentFrame;
    
    public Animation( Frame[] frames)
    {
        this.frames = frames;
    }
    
    public Animation( Animation that)
    {
        if(that == null)
        {
            return;
        }
        this.frames = Frame.clone(that.frames);
        this.currentFrame = this.currentFrame;
    }
    
    private void setFrame(int index, Frame frame)
    {
        frames[index] = frame;
    }
    
    public boolean render()
    {
      boolean animationOver = false;
      Frame temp = frames[currentFrame];
      if(temp.render())
      {
          animationOver = ++currentFrame == frames.length ? true:false;
          currentFrame %= frames.length;
      } 
      return animationOver;
    }
    
    public static Animation[] clone( Animation[] that)
    {
        Animation[] clone = new Animation[that.length];
        
        for(int i = 0;i < that.length; i++)
        {
            clone[i] = new Animation(that[i]);
        }
        
        return clone;
    }
    
    public void scale(float scale)
    {
        for(Frame f : frames)
        {
            f.scale(scale);
        }
    }

    public int getCurrentFrameNumber() { return currentFrame; }
    public Frame getCurrentFrame() { return frames[currentFrame]; }
    
}
