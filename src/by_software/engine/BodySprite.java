/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package by_software.engine;

import org.lwjgl.util.vector.Vector2f;
import org.lwjgl.util.vector.Vector3f;

/**
 *
 * @author Nigel
 */
public class BodySprite extends Sprite
{
    private Vector2f[] childOffset;
    
    public BodySprite(Vector3f color, Vector2f size, String path, Vector2f[] childOffset)
    {
        super(color, size, path);
        this.childOffset = childOffset;
    }
     public BodySprite(Vector3f color, Vector2f size, String path, Vector2f childOffset)
    {
        this(color, size, path,new Vector2f[] {childOffset});
        
    }
    public Vector2f getOffset(int index)
    {
        if(index < childOffset.length)
        {
           return childOffset[index];
        }
        return null;
    }
    
}
