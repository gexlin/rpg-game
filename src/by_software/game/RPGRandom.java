/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package by_software.game;

import java.util.Random;

/**
 *
 * @author Nigel
 */

public class RPGRandom extends Random
{
  
    private long[] state;
    private int index;
    
    public RPGRandom()
    {
       this(System.currentTimeMillis());
    }
    
    public RPGRandom(long seed)
    {
 
        state = new long[16];
        index = 0;       
        seed(seed);
    
    }

   private void seed(long seed)
   {
        seed = Math.abs(seed);
        for(int i = 0; i<16; i++)
        {
            state[i] = (seed + 1) * ((seed + 1) << 2) * i;
        }
   }
    @Override
    protected int next(int nbits)
    {
       long a,b,c,d;
       a = state[index];
       c = state[(index+13)&15];
       b = a^c^(a<<16)^(c<<15);
       c = state[(index+9)&15];
       c ^= (c>>11);
       a = state[index] = b^c;
       d = a^((a<<5)&0xDA442D20L);
       index = (index + 15)&15;
       a = state[index];
       state[index] = a^b^d^(a<<2)^(b<<18)^(c<<28);
       return (int)state[index];
    }
    
    public static void main(String[] args)
    {
        RPGRandom r = new RPGRandom();
        for(int i = 0; i < 100; i++)
        {
            System.out.println(r.nextInt(10));
        }
    }
}
