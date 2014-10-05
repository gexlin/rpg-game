/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package by_software.game.gameobject.mob.body;
import by_software.engine.render.Sprite;
import by_software.engine.render.SpriteSheet;
import org.lwjgl.util.vector.Vector2f;
import org.lwjgl.util.vector.Vector3f;

/**
 *
 * @author Nigel
 */
public class BodySprite extends Sprite
{
    private Vector2f[] childOffsets;
    
    public static BodySprite[] makeSprites(Vector2f size ,  SpriteSheet sheet, Vector2f[][] childOffsets)
    {
        BodySprite[] sprites = new BodySprite[sheet.getNumberOfTiles()];

       for(int i = 0; i < sprites.length ; i++)
       {
           sprites[i] = new BodySprite(new Vector3f(1,1,1), size,sheet,i, childOffsets[i]);
       }
       return sprites;
    }
    
    public BodySprite(Vector3f color, Vector2f size,SpriteSheet sheet,int spriteIndex, Vector2f[] childOffsets)
    {
        super(color, size, sheet,spriteIndex);
        this.childOffsets = childOffsets;
    }
    
    public BodySprite(Vector3f color, Vector2f size, String path, Vector2f[] childOffsets)
    {
        super(color, size, path);
        this.childOffsets = childOffsets;
    }
     public BodySprite(Vector3f color, Vector2f size, String path, Vector2f childOffsets)
    {
        this(color, size, path,new Vector2f[] {childOffsets});
        
    }
     
    @Override
    public void scale(float scale)
    {
        super.scale(scale);
        if(childOffsets != null)
        for(Vector2f v : childOffsets)
        {
            if(v != null)
            v.scale(scale);
        }
    }
    public Vector2f getOffset(int index) { return childOffsets[index]; }
    
}
