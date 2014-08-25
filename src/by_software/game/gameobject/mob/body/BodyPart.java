/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package by_software.game.gameobject.mob.body;

import by_software.engine.AnimationSet;
import by_software.engine.AnimationSet.AnimationType;
import by_software.game.Util;
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
    private Vector2f pos;
    private Vector2f offsetPos;
    
    private Vector2f direction;
    private Vector2f offsetDirection;
    private ArrayList<BodyPart> childParts;
    private ArrayList<Vector2f> childOffsets;
    private AnimationSet animations;
    private AnimationType currentAnimation = AnimationType.IDLE;
    
    public BodyPart(Vector2f pos,Vector2f offsetPos,Vector2f direction, ArrayList<BodyPart> body, AnimationSet animations)
    {   
        this(pos,offsetPos,direction,animations);
        childParts = body;
    }
    
    
    public BodyPart(Vector2f pos,Vector2f offsetPos,Vector2f direction,  AnimationSet animations)
    {
        
        this(pos,offsetPos,direction);
        this.animations = animations;
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
        }
        childParts.add(part);
        childOffsets.add(part.getOffsetPos());
    }
    
    public void render()
    {
        glPushMatrix();
        {
            glTranslatef(offsetPos.x,offsetPos.y,0);
            glRotated(Util.angleDegrees(Vector2f.add(direction, offsetDirection, new Vector2f())), 0f, 0f, 1f);
            
            if(childParts != null)
            {    
                for(BodyPart  childPart: childParts)
                {
                    childPart.render();   
                }
            }
            animations.render(currentAnimation);
        }
        glPopMatrix();
        
    }

    public void setCurrentAnimation(AnimationType currentAnimation){this.currentAnimation = currentAnimation;}

    
    
    public AnimationSet getAnimations() {return animations;}
    public AnimationType getCurrentAnimation(){return currentAnimation;}
    public Vector2f getPos()                     { return pos; }
    public Vector2f getOffsetPos()               { return offsetPos; }
    public Vector2f getDirection()               { return direction;}
    public Vector2f getOffsetDirection()         { return offsetDirection; }
    public ArrayList<BodyPart> getChildParts()   { return childParts; }
    public ArrayList<Vector2f> getChildOffsets() { return childOffsets; }
    
    
}
