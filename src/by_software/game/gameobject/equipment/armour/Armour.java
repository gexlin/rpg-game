/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package by_software.game.gameobject.equipment.armour;

import by_software.game.gameobject.Equipment.Slots;
import by_software.game.gameobject.equipment.EquippableItem;
import org.lwjgl.util.vector.Vector2f;
import org.lwjgl.util.vector.Vector3f;

/**
 *
 * @author Nigel
 */
public class Armour extends EquippableItem
{

    public Armour(String name, Slots slot, Vector2f pos, Vector2f size, Vector3f color)
    {
        super(name, slot, pos, size, color);
    }
    
}
