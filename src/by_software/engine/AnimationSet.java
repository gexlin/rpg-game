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
//    
//    public static final int HEAD = 0;
//    public static final int CHEST = 1;
//    public static final int ARM_LEFT = 2;
//    public static final int ARM_RIGHT = 3;
//    public static final int PAULDRON_LEFT = 4;
//    public static final int PAULDRON_RIGHT = 5;
//    public static final int LEGS = 6;
    
    
    public final static AnimationSet PLAYER_HEAD             = new AnimationSet(new Animation[]{Animation.PLAYER_HEAD});
    public final static AnimationSet PLAYER_BODY             = new AnimationSet(new Animation[]{Animation.PLAYER_BODY });
    public final static AnimationSet PLAYER_PAULDRON_LEFT    = new AnimationSet(new Animation[]{Animation.PLAYER_PAULDRON_LEFT });
    public final static AnimationSet PLAYER_PAULDRON_RIGHT   = new AnimationSet(new Animation[]{Animation.PLAYER_PAULDRON_RIGHT });
    public final static AnimationSet PLAYER_ARM_LEFT         = new AnimationSet(new Animation[]{Animation.PLAYER_ARM_LEFT });
    public final static AnimationSet PLAYER_ARM_RIGHT        = new AnimationSet(new Animation[]{Animation.PLAYER_ARM_RIGHT });
    public final static AnimationSet PLAYER_LEGS             = new AnimationSet(new Animation[]{Animation.PLAYER_LEGS });
    
    
//    public final static AnimationSet[] HUMAN  = new AnimationSet[]{ PLAYER_HEAD,
//                                                                    PLAYER_BODY,
//                                                                    PLAYER_ARM_LEFT,
//                                                                    PLAYER_ARM_RIGHT,
//                                                                    PLAYER_PAULDRON_LEFT,
//                                                                    PLAYER_PAULDRON_RIGHT,
//                                                                    PLAYER_LEGS };

    
    
    
    
    
    Animation[] animations;

    private AnimationSet()
    {
        this.animations = new Animation[AnimationType.values().length];
    }
    
    public AnimationSet(AnimationSet that)
    {
        this.animations = new Animation[that.animations.length];
        for(int i = 0; i < that.animations.length; i++)
        {
            animations[i] = new Animation(that.animations[i]);
        }
    }
        
    private AnimationSet(Animation[] animations)
    {
        this();
        for(int i = 0; i < animations.length;i++)
        {
            this.animations[i] = animations[i];
        }
    }
    
    public void render(AnimationType animation)
    {
        animations[animation.ordinal()].render();
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
