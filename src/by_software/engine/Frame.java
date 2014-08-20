/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package by_software.engine;

/**
 *
 * @author Nigel
 */


public class Frame
{
    private int length;
    private Sprite sprite;
    private int numDisplayed;
    
    public Frame(int lenght, Sprite sprite)
    {
        this.length = length;
        this.sprite = sprite;
        numDisplayed = 0;
    }
    
    public boolean render()
    {
       sprite.render();
        
        numDisplayed++;
        if(numDisplayed >= length)
        {
            numDisplayed = 0;
            return true;
        } 
        return false;
    }
        
        
}
