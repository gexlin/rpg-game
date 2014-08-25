/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package by_software.engine;

import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author Nigel
 */
public class AnimationSet
{
    
    public static final AnimationSet PLAYER_HEAD             = new AnimationSet(new Animation[]{Animation.PLAYER_HEAD});
    public final static AnimationSet PLAYER_BODY             = new AnimationSet(new Animation[]{Animation.PLAYER_BODY });
    public final static AnimationSet PLAYER_PAULDRON_LEFT    = new AnimationSet(new Animation[]{Animation.PLAYER_PAULDRON_LEFT });
    public final static AnimationSet PLAYER_PAULDRON_RIGHT   = new AnimationSet(new Animation[]{Animation.PLAYER_PAULDRON_RIGHT });
    public final static AnimationSet PLAYER_ARM_LEFT         = new AnimationSet(new Animation[]{Animation.PLAYER_ARM_LEFT });
    public final static AnimationSet PLAYER_ARM_RIGHT        = new AnimationSet(new Animation[]{Animation.PLAYER_ARM_RIGHT });
    
    
    
    
    
    
    
    
    Animation[] animations;

    public AnimationSet()
    {
        this.animations = new Animation[AnimationType.values().length];
    }
    
    
    public void render(AnimationType animation)
    {
        animations[animation.ordinal()].render();
    }
    
    public AnimationSet(Animation[] animations)
    {
        this();
        
        
        for(int i = 0; i < animations.length;i++)
        {
            this.animations[i] = animations[i];
        }
    }
    
    //ordinals keep track of index into animations 
    public enum AnimationType
    {
        IDLE,
        WALK,
        ATTACK_STAB_LEFT,
        ATTACK_STAB_RIGHT;
    }
}
