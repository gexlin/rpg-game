/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package by_software.game.gameobject.mob.body;

import by_software.engine.Animation;
import by_software.engine.AnimationSet;
import org.lwjgl.util.vector.Vector2f;

/**
 *
 * @author Nigel
 */




public class Body
{
    
    public static BodyPart PLAYER_HEAD = new BodyPart(new Vector2f(0,0), new Vector2f(0,0),new Vector2f(0,1),AnimationSet.PLAYER_HEAD);
    public static BodyPart PLAYER_CHEST = new BodyPart(new Vector2f(0,0), new Vector2f(0,0),new Vector2f(0,1),AnimationSet.PLAYER_BODY);
    public static BodyPart PLAYER_ARM_LEFT = new BodyPart(new Vector2f(0,0), new Vector2f(-8,12),new Vector2f(0,1),AnimationSet.PLAYER_ARM_LEFT);
    public static BodyPart PLAYER_ARM_RIGHT = new BodyPart(new Vector2f(0,0), new Vector2f(8,12),new Vector2f(0,1),AnimationSet.PLAYER_ARM_RIGHT);
    public static BodyPart PLAYER_PAULDRON_LEFT = new BodyPart(new Vector2f(0,0), new Vector2f(-26,00),new Vector2f(0,1),AnimationSet.PLAYER_PAULDRON_LEFT);
    public static BodyPart PLAYER_PAULDRON_RIGHT = new BodyPart(new Vector2f(0,0), new Vector2f(26,00),new Vector2f(0,1),AnimationSet.PLAYER_PAULDRON_RIGHT);
    
    public static Body PLAYER_BODY;
    static
    {
        PLAYER_HEAD.addChildPart(PLAYER_CHEST);
        PLAYER_HEAD.addChildPart(PLAYER_PAULDRON_LEFT);
        PLAYER_HEAD.addChildPart(PLAYER_PAULDRON_RIGHT);
        PLAYER_PAULDRON_RIGHT.addChildPart(PLAYER_ARM_RIGHT);
        PLAYER_PAULDRON_LEFT.addChildPart(PLAYER_ARM_LEFT);
        PLAYER_BODY = new Body(PLAYER_HEAD);
    
    
    }
    
    private BodyPart head;
    
    public Body(BodyPart head)
    {
        this.head = head;
    }
    
    public void render()
    {
        head.render();
    }
}
