/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package by_software.game.gameobject.mob.body;

import by_software.engine.render.Animation;
import by_software.engine.render.AnimationSet;
import by_software.engine.render.AnimationSet.AnimationType;
import by_software.game.Util;
import by_software.game.gameobject.equipment.armour.Armour;
import java.util.ArrayList;
import static org.lwjgl.opengl.GL11.glPopMatrix;
import static org.lwjgl.opengl.GL11.glPushMatrix;
import static org.lwjgl.opengl.GL11.glRotated;
import static org.lwjgl.opengl.GL11.glTranslatef;
import org.lwjgl.util.vector.Vector2f;

/**
 *
 * @author Nigel
 */
public class BodyPart
{    
    protected Vector2f pos;
    protected Vector2f offsetPos;

    protected Vector2f direction;
    protected Vector2f offsetDirection;
    
    protected ArrayList<BodyPart> childParts;
    protected ArrayList<Vector2f> childOffsets;
    protected ArrayList<Vector2f> childOffsetDirections;
    protected AnimationSet animations;
   
    protected Armour armour;
    protected BodyPart parent;
    protected Body body;////////
    public BodyPart(Vector2f pos,Vector2f offsetPos,Vector2f direction, ArrayList<BodyPart> parts, AnimationSet animations, Armour armour)
    {   
        this(pos,offsetPos,direction,parts,animations);
        this.armour = armour;
   
    }
    public BodyPart(Vector2f pos,Vector2f offsetPos,Vector2f direction, ArrayList<BodyPart> parts, AnimationSet animations)
    {   
        this(pos,offsetPos,direction,animations);
        childParts = parts;
    }
    
    
    public BodyPart(Vector2f pos,Vector2f offsetPos,Vector2f direction,  AnimationSet animations)
    {
        
        this(pos,offsetPos,direction);
        this.animations = animations;
    }
    
    public BodyPart clone()
    {
        return new BodyPart(new Vector2f(this.pos),new Vector2f(this.offsetPos),new Vector2f(this.direction),(ArrayList<BodyPart>)null,new AnimationSet(this.animations),this.armour);
//        this.pos = new Vector2f(that.pos);
//        this.offsetPos = new Vector2f(that.offsetPos);
//
//        this.direction = new Vector2f(that.direction);
//        this.offsetDirection = new Vector2f(that.offsetDirection);
//    
//        this.animations = new AnimationSet(that.animations);
//        this.currentAnimation = AnimationType.IDLE;
//        //IMPORTANT! change to clone ?
//        this.armour = that.armour;
    }

    public BodyPart(Vector2f pos,Vector2f offsetPos,Vector2f direction)
    {
        this.pos = pos;
        this.offsetPos = offsetPos;
        this.direction = direction;
        this.offsetDirection = new Vector2f(0,1);
        this.animations = null;
        childParts = null;
    }
    public void addChildPart(BodyPart part)
    {
        if(childParts == null)
        {
            childParts = new ArrayList<>();
            childOffsets = new ArrayList<>();
            childOffsetDirections = new ArrayList<>();
        }
        part.setParent(this);
        childParts.add(part);
        childOffsets.add(part.getOffsetPos());
        childOffsetDirections.add(part.getOffsetDirection());
    }
    
    public void render()
    {
        glPushMatrix();
        {
                        float angle = 1f;
//            angle = (float)Math.toRadians(angle);
//            Vector2f a = new Vector2f((float)Math.sin(-angle),(float)Math.cos(angle));
//            System.out.println(a + " angle : " + Util.angleDegrees(a));
//          
//            a.normalise();
//            Util.rotateRotationVector(offsetDirection,a , offsetDirection);
////            
            glTranslatef(offsetPos.x,offsetPos.y,0);
            glRotated(Util.angleDegrees(offsetDirection), 0f, 0f, 1f);
            
            if(childParts != null)
            {    
                for(BodyPart  childPart: childParts)
                {
                    childPart.render();   
                }
            }
            animations.render();
        }
        glPopMatrix();
        
    }

    public void setCurrentAnimation(AnimationType currentAnimation){animations.setCurrentAnimation(currentAnimation,1);}
    public void setCurrentAnimation(AnimationType currentAnimation, int time, boolean ratio)
    {
        animations.setCurrentAnimation(currentAnimation,1,time,ratio);
    }

    
    
    public AnimationSet getAnimations() {return animations;}
    public AnimationType getCurrentAnimationType(){return animations.getCurrentAnimationType();}
    public Animation getCurrentAnimation(){return animations.getCurrentAnimation();}
    public Vector2f getPos()                     { return pos; }
    public Vector2f getOffsetPos()               
    { 
        if(parent != null)
        {
           return Util.rotateRadians(Util.angleRadians(parent.getOffsetDirection()), offsetPos);
                
        }    
        return offsetPos; 
    }
    public Vector2f getDirection()               { return direction;}
    public Vector2f getOffsetDirection()         { return offsetDirection; }
    public ArrayList<BodyPart> getChildParts()   { return childParts; }

    
    public ArrayList<Vector2f> getChildOffsets() { return childOffsets; }
    
    public Vector2f getRootOffset() 
    { 
        
        if(parent != null)
        {
//            Util.rotateRadians(Util.angleRadians(parent.getOffsetDirection()), this.offsetPos);
            return Vector2f.add(parent.getRootOffset(), Util.rotateRadians(Util.angleRadians(parent.getDirection()), this.offsetPos), new Vector2f());
        }

      return new Vector2f(0,0);
    }
    
    public Vector2f getRootDirectionOffset() 
    { 
        
        if(parent != null)
        {
            return Util.rotateRotationVector(parent.getRootDirectionOffset(),this.offsetDirection,  new Vector2f() );
            //Vector2f.add(parent.getOffsetPos(), this.offsetPos, new Vector2f());
        }
        else
        {
            return body.getDirection();
        }
    }
    
    public void setParent(BodyPart parent){ this.parent = parent;}
    public void setChildOffset(int index, float x, float y) { childOffsets.get(index).translate(x,y); }
    
    public void setChildOffsetDirection(int index, float x, float y){ childOffsetDirections.get(index).set(x, y); }
    public void setChildOffsetDirection(int index, Vector2f offset )
    { 
        offset.normalise();
        childOffsetDirections.get(index).set(offset); 
    }
    public void equip(Armour armour)
    {
        this.armour = armour;
    }
    
    public void scale(float scale)
    {
        if(childOffsets != null)
        {
            for(Vector2f v: childOffsets)
            {
                v.scale(scale);
            }  
        }
        animations.scale(scale);
    }
    
    public void makeHead(Body body)
    {
        this.body = body;
    }
    
    protected Vector2f relitivePos()
    {
        return null;
    }
    
    protected Vector2f getBodyDirection()
    {
        if(parent != null)
        {
            return parent.getBodyDirection();
        }
            
        return body.getDirection();
        
    }
}
