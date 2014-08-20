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
public class Time
{
    
    private static long curTime;
    private static long lastTime;
    private static float delta;
    
    private static final int NANOPERSECOND = 1000000000;
    
    public static long getTime()
    {
        return System.nanoTime();
    }
    
    public static float getDelta()
    {
        return delta;
    }
    
    public static void update()
    {
        lastTime = curTime;
        curTime = getTime();
        delta = (float)(curTime - lastTime)/NANOPERSECOND;
    }
    
    public static void init()
    {
        lastTime = getTime();
        curTime = getTime();
        delta = (curTime - lastTime)/NANOPERSECOND; 
    }
}
