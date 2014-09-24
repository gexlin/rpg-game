/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package by_software.game.gameobject;

import by_software.game.gameobject.mob.Mob;
import org.lwjgl.util.vector.Vector2f;

/**
 *
 * @author Nigel
 */
public class Inventory
{
    private Mob owner;
    private Item[] items;
    private int firstFree;
    
    public Inventory(int size, Mob owner)
    {
        items = new Item[size];
        this.owner = owner;
        firstFree = 0;
    }
    
    public boolean add(Item item)
    {
        if(firstFree == items.length)
        {
            System.out.println("inventory full");
            item.drop(owner.getPos());
            return false;
        }
        items[firstFree] = item;
        System.out.println("inventory added " +  firstFree + getNumItems());
        for(int i = firstFree +1  ; i < items.length;i++)
        {
            if(items[i] == null )
            {
                firstFree = i;
                return true;
                
            }
        }
        firstFree = items.length;
        
        return true;
    }
    
    public Item get(int index)
    {
        return items[index];
    }
    
    public boolean remove(int index)
    {
        if(index < firstFree)
        {
            firstFree = index;
        }
        items[index] = null;
        return true;
    }
    
    public boolean remove(Item item)
    {
        for(int i = 0 ; i < items.length;i++)
        {
            if(items[i] == item )
            {
                return remove(i); 
            }
        }
        return false;
    }
    
    public void drop(Item item)
    {
         for(int i = 0 ; i < items.length;i++)
        {
            if(items[i] == item )
            {
                drop(i);
            }
        }
    }
    
    public void drop(int index)
    {
        if(items[index] != null)
        {
            items[index].drop(owner.getPos());
            items[index] = null;
            if(index < firstFree)
            {
                firstFree = index;
            }
        }
    }
    
    public boolean has(Item item)
    {
        for(int i = 0 ; i < items.length;i++)
        {
            if(items[i] == item )
            {
                return true;
            }
        }
        return false;
    }
    
   /* public Item[] getItems()
    {
        return items;
    }*/
    public int getNumItems()
    {
        int count = 0;
        for(int i = 0; i > items.length;i++)
        {
            if(items[i] != null);
            {
                count++;
            }
        }
        return count;
    }
}
