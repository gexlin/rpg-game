/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package by_software.game.gameobject.mob.body;
import static by_software.engine.render.Sprite.makeSprites;
import by_software.engine.render.SpriteSheet;
import by_software.game.gameobject.mob.body.BodySprite;
import org.lwjgl.util.vector.Vector2f;
import org.lwjgl.util.vector.Vector3f;

/**
 *
 * @author Nigel
 */
public class ArmSprite extends BodySprite
{
    private Vector2f weaponOffset;
    
    public ArmSprite(Vector3f color, Vector2f size, String path, Vector2f[] childOffset, Vector2f weaponOffset)
    {
        super(color, size, path, childOffset);
        this.weaponOffset = weaponOffset;
    }
    
    public ArmSprite(Vector3f color, Vector2f size,SpriteSheet sheet,int spriteIndex, Vector2f[] childOffsets, Vector2f weaponOffset)
    {
        super(color, size, sheet,spriteIndex,childOffsets);
        this.weaponOffset = weaponOffset;
    }
    
    public  Vector2f getWeaponOffset()
    {   
        return  weaponOffset;
    }

    
    public static ArmSprite[] makeSprites(Vector2f size ,  SpriteSheet sheet, Vector2f[][] childOffsets, Vector2f[] weaponOffset)
    {
       
        ArmSprite[] sprites = new ArmSprite[sheet.getNumberOfTiles()];

       for(int i = 0; i < sprites.length ; i++)
       {
           sprites[i] = new ArmSprite(new Vector3f(1,1,1), size,sheet,i, childOffsets[i % childOffsets.length], weaponOffset[i % weaponOffset.length]);
       }
       return sprites;
    }
    
}
