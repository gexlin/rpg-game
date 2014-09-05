/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package by_software.game.gameobject;

import by_software.game.gameobject.equipment.EquippableItem;
import by_software.game.gameobject.equipment.armour.Armour;
import by_software.game.gameobject.equipment.weapon.Weapon;
import by_software.game.gameobject.mob.Mob;
import org.lwjgl.util.vector.Vector2f;

/**
 *
 * @author Nigel
 */
public final class Equipment
{
    
    private final EquippableItem[] equipment;

    private final Mob owner;
    
    public Equipment(Mob owner)
    {
        equipment = new EquippableItem[Slots.values().length];
        this.owner = owner;
        
        
        
    }
    
    public boolean equip(Armour item)
    {
        int index = item.getSlot().getSlot();
        if(equipment[index] != null)
        {
            if(!unEquipItem(index))
            {
                return false;
            } 
        }
        owner.removeItem(item);
        equipment[index] = item;
        return true;
    }
    
//    public boolean equip(Slots ws,Weapon weapon)
//    {
//        if(weapon != null)
//        {
//            int index = ws.getSlot();
//            if(equipment[index] != null)
//            {
//                if(!unEquipItem(index))
//                {
//                    return false;
//                } 
//            }
//            owner.removeItem(weapon);
//            equipment[index] = weapon;
//            
//            //TODO get right slot
//            weapon.setWielder(owner);
//            //weapon.setEquipedIn(weapon.getSlot());
//            return true;
//        }
//        return false;
//    }
    
    public boolean unEquipItem(int slot)
    {
        EquippableItem item = equipment[slot];
        item.unequip();
        equipment[slot] = null;
        return owner.addItem(item);
    }
    
//    public boolean unEquipWeapon(int slot)
//    {
//        Weapon weapon = weapons[slot];
//        weapon.setEquipedIn(null);
//        weapons[slot] = null;
//        return owner.addItem(weapon);
//    }
     
//
//    public void render()
//    {
//        for(Armour a: armour )
//        {   
//            if(a != null)
//            {
//                a.render();
//            }
//        }
//        if(weapons[equipedLeft.getSlot()] != null)
//        {
//            weapons[equipedLeft.getSlot()].render();
//        }
//        if(weapons[equipedRight.getSlot()] != null)
//        {
//            weapons[equipedRight.getSlot()].render();
//        }
//    }
//    public Slots getEquipedLeft()
//    {
//        return equipedLeft;
//    }
//
//    public Slots getEquipedRight()
//    {
//        return equipedRight;
//    }
//    
//    public boolean attackRight()
//    {
//        Weapon w = weapons[equipedRight.getSlot()];
//        if(w == null)
//        {
//            w = owner.getDefaultWeapon();
//        }
//                
//        return w.attack(0,owner);
//    }
//    public boolean attackLeft()
//    {
//        Weapon w = weapons[equipedLeft.getSlot()];
//        
//        if(w == null)
//        {
//            w = owner.getDefaultWeapon();
//        }
//                
//        return w.attack(0,owner);
//    }

     public enum Slots{
        //ARMOUR
        HEAD(new Vector2f(0,0)),
        PAULDRON_LEFT(new Vector2f(0,4)),
        PAULDRON_RIGHT(new Vector2f(0,-4)),
        BODY(new Vector2f(0,0)),
        WAIST(new Vector2f(0,0)),
        LEGS(new Vector2f(0,0)),
        LEFT_ARM(new Vector2f(0,4)),
        RIGHT_ARM(new Vector2f(0,-4)),
        GAUNTLET_LEFT(new Vector2f(0,4)),
        GAUNTLET_RIGHT(new Vector2f(0,-4)),
        
        //RINGS
        RING_1(new Vector2f(0,0)),
        RING_2(new Vector2f(0,0)),
        RING_3(new Vector2f(0,0)),
        RING_4(new Vector2f(0,0)),
        
        
        //WEAPONS / SHIELDS
        LEFT_HAND_1(new Vector2f(-17,20)),
        LEFT_HAND_2(new Vector2f(-17,20)),
        RIGHT_HAND_1(new Vector2f(30,40)),
        RIGHT_HAND_2(new Vector2f(30,40));;

       
         private Vector2f renderOffset;
        
        private Slots(Vector2f renderOffset)
        {
            this.renderOffset = renderOffset;
        }
        
        public int getSlot()
        {
            return this.ordinal();
        }
        public Vector2f getOffset()
        {
            return renderOffset;
        }
        

    }  
}
