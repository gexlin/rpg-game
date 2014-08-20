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
public class Delay
{
    public static int MILLI_TO_NANO = 1000000;
    private int length;
    private long endTime;
    private boolean started;

    public Delay(int length)
    {
        this.length = length;
        this.started = false;
    }
    
    public boolean isOver()
    {
        if(!started)
        {
            return false;
        }
        
        return Time.getTime() >= endTime;
    }
    
    public void start()
    {
        
        started = true;
        endTime = length * 1000 + Time.getTime();
    }
    
    public void start(int length)
    {
        System.out.println(length);
        started = true;
        endTime = ((long)length) * MILLI_TO_NANO + Time.getTime();
    }
    
    public void zeroDelay()
    {
        started = true;
        endTime = 0;
    }
    
    public void stop()
    {
        started = false;
    }
            
}
