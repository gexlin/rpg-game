/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package by_software.engine;

import java.util.HashMap;

/**
 *
 * @author Nigel
 */
public class Animation
{
   public final static  HashMap<String,Animation[]> PLAYER = new HashMap<>(5,1f);
    static
    {
       PLAYER.put("helm",           new Animation[]{new Animation(Frame.PLAYER.get("helm"))});
       PLAYER.put("body",           new Animation[]{new Animation(Frame.PLAYER.get("body"))} );
       PLAYER.put("poldren-left",   new Animation[]{new Animation(Frame.PLAYER.get("poldren-left"))} );
       PLAYER.put("poldren-right",  new Animation[]{new Animation(Frame.PLAYER.get("poldren-right"))} );
       PLAYER.put("arm-left",       new Animation[]{new Animation(Frame.PLAYER.get("arm-left"))});
       PLAYER.put("arm-right",      new Animation[]{new Animation(Frame.PLAYER.get("arm-right"))} );

    }
    
    
    
    private Frame[] frames;
    private int currentFrame;
    
    public Animation()
    {
        frames = new Frame[10];
    }
    
    public Animation( Frame[] frames)
    {
        this.frames = frames;
    }
    
    private void setFrame(int index, Frame frame)
    {
        frames[index] = frame;
    }
    
    public void render()
    {
      Frame temp = frames[currentFrame];
      if(temp.render())
      {
          currentFrame++;
          currentFrame %= frames.length;
      } 
    }
}
