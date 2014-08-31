/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package by_software.engine;

/**
 *
 * @author Nigel
 */


public class Frame
{
  //  public final static  HashMap<String,Frame[]> PLAYER = new HashMap<>(5,1f);
     
    
   public final static Frame[] PLAYER_HEAD             =    frameArray(250,Sprite.PLAYER_HEAD);
   public final static Frame[] PLAYER_BODY             =    frameArray(250,Sprite.PLAYER_BODY );
   public final static Frame[] PLAYER_PAULDRON_LEFT    =    frameArray(250,Sprite.PLAYER_PAULDRON_LEFT );
   public final static Frame[] PLAYER_PAULDRON_RIGHT   =    frameArray(250,Sprite.PLAYER_PAULDRON_RIGHT );
   public final static Frame[] PLAYER_ARM_LEFT         =    frameArray(250,Sprite.PLAYER_ARM_LEFT );
   public final static Frame[] PLAYER_ARM_RIGHT        =    frameArray(250,Sprite.PLAYER_ARM_RIGHT );
   public final static Frame[] PLAYER_LEGS             =    frameArray(250,Sprite.PLAYER_LEGS );

           
    /*static
    {
       PLAYER.put("helm",           frameArray(250,Sprite.PLAYER_HEAD));
       PLAYER.put("body",           frameArray(250,Sprite.PLAYER_BODY );
       PLAYER.put("poldren-left",   frameArray(250,Sprite.PLAYER.get("poldren-left")) );
       PLAYER.put("poldren-right",  frameArray(400,Sprite.PLAYER.get("poldren-right")) );
       PLAYER.put("arm-left",       frameArray(400,Sprite.PLAYER.get("arm-left")));
       PLAYER.put("arm-right",      frameArray(400,Sprite.PLAYER.get("arm-right")) );

    }*/
    
    private int length;
    private Sprite sprite;
    private boolean rendering = false;
    private long frameEnd;
    
    public Frame(int lenghtMileSec, Sprite sprite)
    {
        this.length = lenghtMileSec * 1000000;
        this.sprite = sprite;
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
    
    
    public boolean render()
    {
        if(!rendering)
        {
            rendering = true;
            frameEnd = Time.getTime() + length;
        }
        sprite.render();
      //  System.out.println("frame"   + frameEnd + "   "+ Time.getTime() +"  " + (frameEnd <= Time.getTime()));
      
      
        if(frameEnd <= Time.getTime())
        {
            rendering = false;
            return true;
        } 
        return false;
    }
        
        
}
