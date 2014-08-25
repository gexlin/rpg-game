/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package by_software.engine;
import by_software.game.Game;
import static org.lwjgl.opengl.GL11.*;
import org.lwjgl.util.vector.Vector2f;
import org.lwjgl.util.vector.Vector3f;

/**
 *
 * @author Nigel
 */
public abstract class GameObject
{
    private static Game game;
    protected String name;
    protected Vector2f pos;
    protected Vector2f size;
    private Sprite sprite;
    protected GameObjectType type; 
    protected boolean[] flags = new boolean[GameObjectsFlags.values().length];
    
    //protected Animation animation;
    public GameObject(String name,Vector2f pos, Vector2f size, Vector3f color ,GameObjectType type )
    {
        this.name = name;
        this.pos = pos;
        this.size = size;
        this.type = type;
        this.sprite = new Sprite(color,size);
    }
    
    public GameObject(String name,Vector2f pos, Vector2f size, Vector3f color , GameObjectType type, String path)
    {
        this.name = name;
        this.pos = pos;
        this.size = size;
        this.type = type;
        this.sprite = new Sprite(color,size,path);
    }
   
    public GameObject(String name,float x, float y, float sX, float sY, float r, float g, float b,GameObjectType type)
    {
        this(name,new Vector2f(x,y), new Vector2f(sX,sY),new Vector3f(r,g,b),type);
    }
    public void update()
    {
    
    }
    
    public void render()
    {
        glPushMatrix();
        {
            glTranslatef(pos.x,pos.y,0);
            sprite.render();
        }
        glPopMatrix();
    
    }
    public static void initGameObjects(Game g)
    {
        game = g;
    }
    
    public void remove()
    {   
        flags[GameObjectsFlags.REMOVED.ordinal()] = true; 
    }
    
    public void addToGame()
    {
        flags[GameObjectsFlags.REMOVED.ordinal()] = false; 
        GameObject.getGame().addObject(this);
    }
   
    public Vector2f getPos() { return pos;}
    public float getX() { return pos.x;}
    public float getY() { return pos.y;}
    public Vector2f getSize(){return size;}
    public float getSizeX(){return size.x;}
    public float getSizeY(){return size.y;}
    public boolean isRemoved(){return flags[GameObjectsFlags.REMOVED.ordinal()]; }
    public Sprite getFrame(){return sprite;}
   
    public GameObjectType getType() {return type;}
    public static Game getGame()
    {
        return game;
    }

    //public Animation getAnimation(){return animation;}
    @Override
    public String toString()
    {
        return name;
    } 
    
}
