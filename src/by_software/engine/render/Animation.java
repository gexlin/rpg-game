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
    private int length;
            
    public Animation( Frame[] frames)
    {
        this.frames = frames;
        length = 0;
        for(Frame f : frames)
        {
            length += f.getLength();
        }
        
    }
    
    public Animation( Animation that)
    {
        if(that == null)
        {
            return;
        }
        this.frames = Frame.clone(that.frames);
        this.currentFrame = that.currentFrame;
        this.length = that.length;
        
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
    
    public void setLength(int time, boolean keepRatio)
    {
    //TODO
         
        if(time != this.length)
        {
             
            if(!keepRatio)
            {
             
                time = time/frames.length;
                for(Frame f: frames)
                {
                    f.setLength(time);
                }
            }
            else
            {
                //int length = this.length;
                
                for(Frame f: frames)
                {
                   
                    f.setLength((int)(time * ((float)f.getLength() / (float)this.length)));
                }       
            }
            this.length = time;
        }
                
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
    
    public boolean isActive()
    {
        return this.getCurrentFrame().isActive();
    }
    public boolean triggerEvent()
    {
        return this.getCurrentFrame().triggerEvent();
    }
    public void rearm()
    {
        for(Frame f: frames)
        {
            f.rearm();
        }
    }
}
