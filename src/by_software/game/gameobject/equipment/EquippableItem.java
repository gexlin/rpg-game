/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package by_software.game.gameobject.equipment;

import by_software.game.Util;
import by_software.game.gameobject.Equipment.Slots;
import by_software.game.gameobject.Item;
import by_software.game.gameobject.mob.Mob;
import org.lwjgl.util.vector.Vector2f;
import org.lwjgl.util.vector.Vector3f;

/**
 *
 * @author Nigel
 */
public abstract class EquippableItem extends Item
{
    private Slots slot;
    protected Mob owner;
    protected Mob wielder;
    
    public EquippableItem(String name, Slots slot, Vector2f pos, Vector2f size, Vector3f color, String path)
    {
        super(name, pos, size, color, path);
        this.slot = slot;
    }

    public Slots getSlot(){return slot;}
    @Override
    public Vector2f getPos()
    {
        if(wielder == null)
        {
            
            return pos;
        }
        else
        {
            
           
            Vector2f offsetRotated =  Util.rotateRadians(Util.angleRadians(wielder.getDirection()), slot.getOffset());
            return Vector2f.add(wielder.getPos(),offsetRotated, offsetRotated);
        }
    }
    
    public void setWielder(Mob wielder)
    {
        this.wielder = wielder;
    }
    
    
}
