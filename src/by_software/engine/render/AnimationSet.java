/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package by_software.engine.render;

import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author Nigel
 */
public class AnimationSet
{

    private Animation[] animations;
    private AnimationType currentAnimation = AnimationType.IDLE;
    private int loopAnimation = -1;
    
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
        
    public AnimationSet(Animation[] animations)
    {
        this();
        for(int i = 0; i < animations.length;i++)
        {
            this.animations[i] = animations[i];
        }
    }
    
    public void render()
    {
        if(getCurrentAnimation().render())
        {
            if(--loopAnimation == 0)
            {
                currentAnimation = AnimationType.IDLE;
            } 
        }
    }

    public void scale(float scale)
    {
        for(Animation a: animations)
        {
            if(a != null)
            {
                a.scale(scale);
            }
        }
    }
    public Animation getCurrentAnimation()
    {
        return animations[currentAnimation.ordinal()];
    }
    
    public AnimationType getCurrentAnimationType( )
    {
        return this.currentAnimation;
    }
    
    public void setCurrentAnimation(AnimationType currentAnimation, int loop)
    {
        this.currentAnimation = currentAnimation;
        this.loopAnimation = loop;
    }

    
    public void setCurrentAnimation(AnimationType currentAnimation, int loop, int time, boolean ratio)
    {
        this.currentAnimation = currentAnimation;/////////////////////////////////////////
        this.loopAnimation = loop;
        getCurrentAnimation().setLength(time,ratio);
        rearm();
    }
    
    public void rearm()
    {
        getCurrentAnimation().rearm();
    }
    public boolean isActive()
    {
        return this.getCurrentAnimation().isActive();
    }
    public boolean triggerEvent()
    {
        return this.getCurrentAnimation().triggerEvent();
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
