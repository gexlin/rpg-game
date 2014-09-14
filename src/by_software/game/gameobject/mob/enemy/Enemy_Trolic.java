/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package by_software.game.gameobject.mob.enemy;

import by_software.game.Util;
import by_software.game.gameobject.equipment.weapon.Fist;
import by_software.game.gameobject.mob.Faction;
import by_software.game.gameobject.mob.body.Body;
import by_software.game.gameobject.mob.body.HumanBody;
import static org.lwjgl.opengl.GL11.glPopMatrix;
import static org.lwjgl.opengl.GL11.glPushMatrix;
import static org.lwjgl.opengl.GL11.glRotated;
import static org.lwjgl.opengl.GL11.glTranslatef;
import org.lwjgl.util.vector.Vector2f;
import org.lwjgl.util.vector.Vector3f;

/**
 *
 * @author Nigel
 */
public class Enemy_Trolic extends Enemy
{
    private final static Vector2f SIZE = new Vector2f(64,64);
    

    public Enemy_Trolic(Vector2f pos, float scale, int type, int level, int inventorySize)
    {
        super("Trolic", Faction.TROLIC,pos, (Vector2f)(new Vector2f(SIZE)).scale(scale), new Vector3f(.8f,.2f,.2f), type, level, inventorySize, new Fist());
        this.body = new HumanBody(this, scale, "player/");
        
    }
//
//   @Override
//    public void render()
//    {
//        glPushMatrix();
//        {
//            
//            
//            glTranslatef(pos.x,pos.y,0);
//            glRotated(Util.angleDegrees(direction), 0f, 0f, 1f);
//            
//          //  getFrame().render();
//            //equipment.render();
//            body.render();
//        }
//        glPopMatrix();
//    
//    }
  
}
