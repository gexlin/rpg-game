/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package by_software.engine;

import java.util.ArrayList;

/**
 *
 * @author Nigel
 */
public class Animation
{
    private ArrayList<Frame> frame;
    private int currentFrame;
    
    public Animation()
    {
        frame = new ArrayList();
    }
    
    public void render()
    {
      Frame temp = frame.get(currentFrame);
      if(temp.render())
      {
          currentFrame++;
          currentFrame %= frame.size();
      }
      
    }
}
