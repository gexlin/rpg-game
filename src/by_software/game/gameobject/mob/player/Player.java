/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package by_software.game.gameobject.mob.player;

import by_software.engine.Animation;
import by_software.engine.GameObject;
import by_software.engine.GameObjectType;
import by_software.game.Util;
import by_software.game.gameobject.Equipment.Slots;
import by_software.game.gameobject.Item;
import by_software.game.gameobject.equipment.weapon.Fist;
import by_software.game.gameobject.equipment.weapon.Weapon;
import by_software.game.gameobject.mob.Faction;
import by_software.game.gameobject.mob.Mob;
import by_software.game.gameobject.mob.body.Body;
import by_software.game.gameobject.mob.body.BodyPart;
import java.util.ArrayList;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import static org.lwjgl.opengl.GL11.glPopMatrix;
import static org.lwjgl.opengl.GL11.glPushMatrix;
import static org.lwjgl.opengl.GL11.glRotated;
import static org.lwjgl.opengl.GL11.glTranslatef;
import org.lwjgl.util.vector.Vector2f;
import org.lwjgl.util.vector.Vector3f;

/**
 *
 * @author Nigel
 */
public class Player extends Mob
{
    public static final int SIZE = 32;
    public static final int ATTACK_DELAY = 500;
    public static Vector3f color =  new Vector3f(.3f,.7f,.6f);
    private static float attackRange = 30; 
    private Body body ;

    public Player(Vector2f pos,int inventorySize)
    {  
        super("Player", Faction.PLAYERS, pos,new Vector2f(SIZE,SIZE), color,GameObjectType.PLAYER,0,true, inventorySize, ATTACK_DELAY,new Fist());
        this.body = Body.PLAYERBODY;
    }
    
    public Player(float x, float y,int inventorySize)
    {
        this(new Vector2f(x,y),inventorySize);
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
        mx -= Display.getWidth()/2;
        my -= Display.getHeight()/2;
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
        {equipment.equip(Slots.RIGHT_HAND_1,(Weapon)inventory.get(0));}
        if(Keyboard.isKeyDown(Keyboard.KEY_SPACE))
        {attack();}
        setDirection(mx,my);
        super.move(x,y);
        
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
    
    @Override
    protected boolean attack()
    { 
      return equipment.attackRight();
    }
    @Override
    public void render()
    {
        glPushMatrix();
        {
            
            
            glTranslatef(pos.x,pos.y,0);
            glRotated(Util.angleDegrees(direction), 0f, 0f, 1f);
            
          //  getFrame().render();
            equipment.render();
            body.render();
        }
        glPopMatrix();
    
    }
}