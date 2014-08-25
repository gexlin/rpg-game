/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package by_software.engine;

import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author Nigel
 */
public class AnimationSets
{
    public final static  HashMap<String,Animation[]> player = new HashMap<>(5,1f);
    
     public static ArrayList<Animation> playerHeadAnimations;
    static
    {
        playerHeadAnimations = new ArrayList<>();
        
    }
    
 
}
