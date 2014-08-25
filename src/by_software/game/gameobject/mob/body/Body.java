/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package by_software.game.gameobject.mob.body;

import by_software.engine.Animation;
import java.util.ArrayList;
import org.lwjgl.util.vector.Vector2f;

/**
 *
 * @author Nigel
 */




public class Body
{
    
    public static BodyPart head1 = new BodyPart(new Vector2f(0,0), new Vector2f(0,0),new Vector2f(0,1),Animation.PLAYER.get("helm"));
    public static BodyPart body = new BodyPart(new Vector2f(0,0), new Vector2f(0,0),new Vector2f(0,1),Animation.PLAYER.get("body"));
    public static BodyPart rightArm = new BodyPart(new Vector2f(0,0), new Vector2f(30,10),new Vector2f(0,1),Animation.PLAYER.get("arm-right"));
    public static BodyPart leftArm = new BodyPart(new Vector2f(0,0), new Vector2f(-30,10),new Vector2f(0,1),Animation.PLAYER.get("arm-left"));
    public static BodyPart poldrenRight = new BodyPart(new Vector2f(0,0), new Vector2f(25,00),new Vector2f(0,1),Animation.PLAYER.get("poldren-right"));
    public static BodyPart poldrenLeft = new BodyPart(new Vector2f(0,0), new Vector2f(-25,00),new Vector2f(0,1),Animation.PLAYER.get("poldren-left"));
    
    public static Body PLAYERBODY;
    static
    {
        head1.addChildPart(body);
        body.addChildPart(rightArm);
        body.addChildPart(leftArm);
        head1.addChildPart(poldrenRight);
        head1.addChildPart(poldrenLeft);
        PLAYERBODY = new Body(head1);
    
    
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
