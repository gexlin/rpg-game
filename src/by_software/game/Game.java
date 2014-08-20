/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package by_software.game;

import by_software.engine.GameObject;
import by_software.engine.Physics;
import by_software.game.gameobject.DEBUG_Line;
import by_software.game.gameobject.Equipment.Slots;
import by_software.game.gameobject.mob.enemy.Enemy_Trolic;
import by_software.game.gameobject.equipment.weapon.SpearOfDebugging;
import by_software.game.gameobject.mob.player.Player;
import by_software.game.gameobject.mob.Mob;
import by_software.game.gameobject.mob.Team;
import java.awt.Rectangle;
import java.awt.geom.Line2D;
import java.util.ArrayList;
import java.util.Iterator;
import org.lwjgl.opengl.Display;
import org.lwjgl.util.vector.Vector2f;

/**
 *
 * @author Nigel
 */
public class Game
{
    private ArrayList<GameObject> objects;
    private Player player;
    private String name;
    private DEBUG_Line rayTraceLine;
    
    public Game(String name)
    {
        Mob mob = new Enemy_Trolic(new Vector2f(100f,300f),new Vector2f(16f,16f),1,2,1); 
        mob.equip(Slots.RIGHT_HAND_1, new SpearOfDebugging(new Vector2f(32,32)));
        this.name = name;
        GameObject.initGameObjects(this);
        objects = new ArrayList();
        player = new Player(Display.getWidth()/2, Display.getHeight()/2,20);
        objects.add(player);
        rayTraceLine = new DEBUG_Line(player.getPos(),player.getPos());
        objects.add(new Enemy_Trolic(new Vector2f(200f,200f),new Vector2f(16f,16f),1,2,1));
        objects.add(new Enemy_Trolic(new Vector2f(220f,200f),new Vector2f(16f,16f),1,2,1));
        objects.add(new Enemy_Trolic(new Vector2f(240f,200f),new Vector2f(16f,16f),1,2,1));
        objects.add(mob);
        objects.add(new SpearOfDebugging(new Vector2f(32,32)));
        objects.add(new SpearOfDebugging(new Vector2f(Display.getWidth()/2, Display.getHeight()/2)));
        initGame();
    }
    
    public void getInput()
    {
        player.getInput();
    }
    public void update()
    {
       removeObjects();
        for(GameObject go: objects)
        {
            go.update();
        }
         
    }
    public void render()
    {
        int i = 0;
        for(GameObject go: objects)
        {
            go.render();
            
        }
        rayTraceLine.render();
    }
    public void removeObjects()
    {
        for(Iterator<GameObject> it = objects.iterator(); it.hasNext();)
        {
            GameObject go = it.next();
            if(go.isRemoved())
            {
               
                it.remove();
            }
        }
    }
    
    public void addObject(GameObject go)
    {
        objects.add(go);
    }
  
    public ArrayList<GameObject> sphereCollide(Vector2f pos, float radius)
    {
        ArrayList<GameObject> res = new ArrayList();
        
        for(GameObject go : objects)
        {
            if(Util.distance(go.getPos(), pos) < radius)
            {
                res.add(go);
            }
        }
        
        return res;
    }
    
    public ArrayList<GameObject> rectangleCollide(Vector2f pos, Vector2f size)
    {
        ArrayList<GameObject> res = new ArrayList();
        Rectangle r1 = new Rectangle((int)(pos.getX() - size.getX()/2),(int)(pos.getY() - size.getY()/2),(int)size.getX(),(int)size.getY());
        
        for(GameObject go : objects)
        {
            Rectangle r2 =  Physics.makeHitBox(go);
            if(r1.intersects(r2))
            {
                res.add(go);
            }
        }
        
        return res;
    }
    
    public ArrayList<GameObject> rayCast(Vector2f startPos, Vector2f endPos)
    {
        
        
        ArrayList<GameObject> res = new ArrayList();
        Line2D l1 = new Line2D.Float((int)startPos.getX(),(int)startPos.getY(),(int)endPos.getX(),(int)endPos.getY());
        
        rayTraceLine.set(new Vector2f(startPos), new Vector2f(endPos));
        for(GameObject go : objects)
        {
            
            if(Physics.checkCollision(l1, go) != null)
            {
                res.add(go);
            }
        }
        
        return res;
    }
    private void initGame()
    {
        Team.initTeams();
    }
}
