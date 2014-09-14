/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package by_software.engine.render;

import org.lwjgl.util.vector.Vector2f;
import org.lwjgl.util.vector.Vector3f;

/**
 *
 * @author Nigel
 */
public class ArmSprite extends BodySprite
{
    private Vector2f[] weaponOffset;
    
    public ArmSprite(Vector3f color, Vector2f size, String path, Vector2f[] childOffset, Vector2f[] weaponOffset)
    {
        super(color, size, path, childOffset);
        this.weaponOffset = weaponOffset;
    }
    
    public  Vector2f getWeaponOffset()
    {
        return  weaponOffset[0];
    }
    
}
