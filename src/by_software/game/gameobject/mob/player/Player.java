/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package by_software.game.gameobject.mob.player;

import by_software.engine.GameObject;
import by_software.engine.GameObjectType;
import by_software.game.gameobject.Item;
import by_software.game.gameobject.equipment.weapon.Fist;
import by_software.game.gameobject.equipment.weapon.Weapon;
import by_software.game.gameobject.mob.Faction;
import by_software.game.gameobject.mob.Mob;
import by_software.game.gameobject.mob.body.HumanBody;
import java.util.ArrayList;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.util.vector.Vector2f;
import org.lwjgl.util.vector.Vector3f;

/**
 *
 * @author Nigel
 */
public class Player extends Mob
{
    private final static Vector2f SIZE = new Vector2f(64,64);
    
    public static final int ATTACK_DELAY = 500;
    public static Vector3f color =  new Vector3f(.3f,.7f,.6f);
    private static float attackRange = 30; 


    public Player(Vector2f pos,int inventorySize,float scale)
    {  
        super("Player", Faction.PLAYERS, pos,(Vector2f)(new Vector2f(SIZE)).scale(scale), color,GameObjectType.PLAYER,0,true, inventorySize, ATTACK_DELAY,new Fist());
        this.body = new HumanBody(this, scale, "player/");
        
    }
    
    public Player(float x, float y,int inventorySize, float scale)
    {
        this(new Vector2f(x,y),inventorySize, scale);
    }
    @Override
    public void update()
    {
       
    }
    
    public void getInput()
    {
        float x = 0,y = 0;
        int mx = Mouse.getX(), my = Mouse.getY();
        //System.out.println(mx + ":mx before  my:"  + my);
//        mx -= Display.getWidth()/2;
//        my -= Display.getHeight()/2;
        mx -= this.getPos().x;
        my -= this.getPos().y;
        //System.out.println(mx + ":mx  after  my:"  + my);
        if(Keyboard.isKeyDown(Keyboard.KEY_W) || Keyboard.isKeyDown(Keyboard.KEY_UP))
        {y++;}
        if(Keyboard.isKeyDown(Keyboard.KEY_S) || Keyboard.isKeyDown(Keyboard.KEY_DOWN))
        {y--;}
        if(Keyboard.isKeyDown(Keyboard.KEY_D) || Keyboard.isKeyDown(Keyboard.KEY_RIGHT))
        {x++;}
        if(Keyboard.isKeyDown(Keyboard.KEY_A) || Keyboard.isKeyDown(Keyboard.KEY_LEFT))
        {x--;}
        if(Keyboard.isKeyDown(Keyboard.KEY_Q))
        {inventory.drop(0);}
        if(Keyboard.isKeyDown(Keyboard.KEY_E))
        {pickUp();}
        if(Keyboard.isKeyDown(Keyboard.KEY_F))
        {
            //equipment.equip(Slots.RIGHT_HAND_1,(Weapon)inventory.get(0));
            body.equipRight((Weapon)inventory.get(0));
        }
        if(Keyboard.isKeyDown(Keyboard.KEY_G))
        {
            //equipment.equip(Slots.RIGHT_HAND_1,(Weapon)inventory.get(0));
            body.equipLeft((Weapon)inventory.get(1));
        }
        if(Mouse.isButtonDown(1))
        {attackRight(0);}
        if(Mouse.isButtonDown(0))
        {attackLeft(0);}
        setDirection(mx,my);
        super.moveLocal(x,y);
        
    }
    /*private void move( float magX, float magY)
    {
        if(!(magX == 0 && magY ==0) )
        {
            Vector2f move = new Vector2f(magX,magY);
            move.normalise();
            move.scale(getSpeed());
            Vector2f.add(move, pos, pos);
        }
    }*/
    
    
    public void pickUp()
    {
       ArrayList<GameObject> objects = getGame().rectangleCollide(pos, size);
       for(GameObject go : objects)
       {
           if(go.getType() == GameObjectType.ITEM)
           {
               ((Item)go).pickUp();
               addItem((Item)go);
           }
       }
    }
  
    public float getAttackRange()
    {
        return attackRange;
    }
    

    protected boolean attackLeft(int index)
    { 
      return body.attackLeft(index);
    }
    protected boolean attackRight(int index)
    { 
      return body.attackRight(index);
    }
//    @Override
//    public void render()
//    {
//        glPushMatrix();
//        {
//            
//            
//            glTranslatef(pos.x,pos.y,0);
//            glRotated(Util.angleDegrees(direction), 0f, 0f, 1f);
//            
//          //  getFrame().render();
//            //equipment.render();
//            body.render();
//        }
//        glPopMatrix();
//    
//    }
}