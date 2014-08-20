/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package by_software.engine;

import by_software.game.Game;
import org.lwjgl.LWJGLException;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import static org.lwjgl.opengl.GL11.*;

/**
 *
 * @author Nigel
 */
public class Main 
{
    public static final int FPS = 60;
    
    private static int displayWidth = 900;
    private static int displayHeight = 450;
    
    
    private static Game game;
    
    public static void main(String[] args)
    {
        initDisplay();
        initGL();
        initGame();
        
        gameLoop();
        cleanUp();
    }
    
   
    private static void getInput()
    {
        game.getInput();
    }
    
    private static void update()
    {
        game.update();
    }
    
    private static void render()
    {
        glClear(GL_COLOR_BUFFER_BIT);
        glLoadIdentity();
        
        game.render();
        
        Display.update();
        Display.sync(FPS);
    }
    

        
        
    private static void gameLoop()
    {
        Time.init();
        while(!Display.isCloseRequested())
        {
            Time.update();
            getInput();
            update();
            render();
        }
    }
    

    private static void cleanUp()
    {
        Display.destroy();
        Keyboard.destroy();
    }
    private static void initDisplay()
    {
        try
        {
            Display.setDisplayMode(new DisplayMode(displayWidth,displayHeight));
            Display.create();
            Keyboard.create();
            Display.setVSyncEnabled(true);
        } catch (LWJGLException ex){}
    }
        private static void initGL()
    {
        glMatrixMode(GL_PROJECTION);
        glLoadIdentity();
        glOrtho(0,Display.getWidth(),0,Display.getHeight(),-1,1);
        glMatrixMode(GL_MODELVIEW);
        
        glDisable(GL_DEPTH_TEST);
        
        glClearColor(0,0,0,1);
    }
        
    private static void initGame()
    {
        game = new Game("daadsad");
    }
}
