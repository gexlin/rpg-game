/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package by_software.game.gameobject.mob.body;

import by_software.engine.render.Animation;
import by_software.engine.render.AnimationSet;
import by_software.engine.render.Frame;
import static by_software.engine.render.Frame.frameArray;
import by_software.engine.render.Sprite;
import by_software.engine.render.SpriteSheet;
import by_software.game.gameobject.mob.Mob;
import org.lwjgl.util.vector.Vector2f;
import org.lwjgl.util.vector.Vector3f;

/**
 *
 * @author Nigel
 */
public class HumanBody extends Body
{
    
    public static final int HEAD = 0;
    public static final int CHEST = 1;
    public static final int ARM_LEFT = 2;
    public static final int ARM_RIGHT = 3;
    public static final int PAULDRON_LEFT = 4;
    public static final int PAULDRON_RIGHT = 5;
    public static final int LEGS = 6;
    public static final int PARTS = 7;
    private static String dir = "src/res/";
    
    public HumanBody(Mob owner, float scale, String path)
    {
        super(PARTS, owner,1);
        path = dir + path;
        
        SpriteSheet SS_LEGS = new SpriteSheet(path + "legs.png" ,64,64); 
        SpriteSheet SS_RIGHT_ARMS = new SpriteSheet(path + "arm-right-attack.png" , 64, 128); 
        SpriteSheet SS_LEFT_ARMS = new SpriteSheet(path + "arm-left-attack.png" , 64, 128); 
        
        
        
        Sprite[] S_HEAD             = {new Sprite( new Vector2f(32,36),path + "helm.png " ) };
        Sprite[] S_BODY             = {new Sprite( new Vector2f(50,50),path + "chestplate.png" )};
        Sprite[] S_PAULDRON_LEFT    = {new Sprite( new Vector2f(27,40),path + "poldren-left.png" )};
        Sprite[] S_PAULDRON_RIGHT   = {new Sprite( new Vector2f(27,40),path + "poldren-right.png")};
        
        Vector2f[] leftWeaponOffset   = new Vector2f[]{new Vector2f(9,29),new Vector2f(9,27),new Vector2f(9,24),new Vector2f(9,23),new Vector2f(9,22),new Vector2f(9,33),new Vector2f(9,60)};
        //Sprite[] S_ARM_LEFT         = {new ArmSprite(new Vector3f(1,1,1), new Vector2f(48,80),path + "arm-left.png",new Vector2f[] {new Vector2f(64,64)},  new Vector2f(0,0)) }; 
        ArmSprite[] S_ARM_LEFT        = {new ArmSprite(new Vector3f(1,1,1), new Vector2f(48,80),path + "arm-left.png",new Vector2f[] {new Vector2f(64,64)},  new Vector2f(9,30)) };
        ArmSprite[] S_ARM_LEFT_ATTACK = ArmSprite.makeSprites( new Vector2f(48,80),SS_LEFT_ARMS, new Vector2f[][]{(Vector2f[]) null}, leftWeaponOffset);//
        
        
        Vector2f[] rightWeaponOffset   = new Vector2f[]{new Vector2f(-9,29),new Vector2f(-9,27),new Vector2f(-9,24),new Vector2f(-9,23),new Vector2f(-9,22),new Vector2f(-9,33),new Vector2f(-9,60)};
                
        ArmSprite[] S_ARM_RIGHT        = {new ArmSprite(new Vector3f(1,1,1), new Vector2f(48,80),path + "arm-right.png",new Vector2f[] {new Vector2f(64,64)},  new Vector2f(-9,30)) };
        ArmSprite[] S_ARM_RIGHT_ATTACK = ArmSprite.makeSprites( new Vector2f(48,80),SS_RIGHT_ARMS, new Vector2f[][]{(Vector2f[]) null}, rightWeaponOffset);//
        
        Sprite[] S_LEGS             = Sprite.makeSprites(new Vector2f(90,70), SS_LEGS);
        
        
        
        Frame[] F_HEAD             =    frameArray(250,S_HEAD,0,0);
        Frame[] F_BODY             =    frameArray(250,S_BODY,0,0 );
        Frame[] F_PAULDRON_LEFT    =    frameArray(250,S_PAULDRON_LEFT,0,0 );
        Frame[] F_PAULDRON_RIGHT   =    frameArray(250,S_PAULDRON_RIGHT,0,0 );
        Frame[] F_ARM_LEFT         =    frameArray(250,S_ARM_LEFT ,0,0);
        Frame[] F_ARM_RIGHT        =    frameArray(250,S_ARM_RIGHT,0,0 );
        Frame[] F_ARM_RIGHT_ATTACK =    frameArray(70,S_ARM_RIGHT_ATTACK, 5,7);
        Frame[] F_ARM_LEFT_ATTACK  =    frameArray(70,S_ARM_LEFT_ATTACK,  5,7);
        Frame[] F_LEGS             =    frameArray(250,S_LEGS,0,0 );
        
        
        
        Animation A_HEAD             = new Animation(F_HEAD);
        Animation A_BODY             = new Animation(F_BODY );
        Animation A_PAULDRON_LEFT    = new Animation(F_PAULDRON_LEFT );
        Animation A_PAULDRON_RIGHT   = new Animation(F_PAULDRON_RIGHT );
        Animation A_ARM_LEFT         = new Animation(F_ARM_LEFT );
        Animation A_ARM_LEFT_ATTACK  = new Animation(F_ARM_LEFT_ATTACK);
        
        Animation A_ARM_RIGHT        = new Animation(F_ARM_RIGHT);
        Animation A_ARM_RIGHT_ATTACK = new Animation(F_ARM_RIGHT_ATTACK);
        
        Animation IDLE_LEGS          = new Animation(new Frame[]{new Frame(F_LEGS[0])});
        Animation WALKING_LEGS       = new Animation(F_LEGS );
        
        
        
        AnimationSet AS_HEAD             = new AnimationSet(new Animation[]{A_HEAD});
        AnimationSet AS_BODY             = new AnimationSet(new Animation[]{A_BODY });
        AnimationSet AS_PAULDRON_LEFT    = new AnimationSet(new Animation[]{A_PAULDRON_LEFT });
        AnimationSet AS_PAULDRON_RIGHT   = new AnimationSet(new Animation[]{A_PAULDRON_RIGHT });
        AnimationSet AS_ARM_LEFT         = new AnimationSet(new Animation[]{A_ARM_LEFT,  null, null, A_ARM_LEFT_ATTACK });
        AnimationSet AS_ARM_RIGHT        = new AnimationSet(new Animation[]{A_ARM_RIGHT, null, null, A_ARM_RIGHT_ATTACK });
        AnimationSet AS_LEGS             = new AnimationSet(new Animation[]{ WALKING_LEGS,IDLE_LEGS });
        
        
//        
//        BodyPart PLAYER_HEAD = new BodyPart(new Vector2f(0,0), new Vector2f(0,0),new Vector2f(0,1),AS_HEAD);
//        BodyPart PLAYER_CHEST = new BodyPart(new Vector2f(0,0), new Vector2f(0,0),new Vector2f(0,1),AS_BODY);
//        
//        Arm PLAYER_ARM_LEFT = new Arm(new Vector2f(0,0), new Vector2f(-16,0),new Vector2f(0,1),AS_ARM_LEFT);
//        Arm PLAYER_ARM_RIGHT = new Arm(new Vector2f(0,0), new Vector2f(16,0),new Vector2f(0,1),AS_ARM_RIGHT);
//        
//        BodyPart PLAYER_PAULDRON_LEFT = new BodyPart(new Vector2f(0,0), new Vector2f(-28,00),new Vector2f(0,1),AS_PAULDRON_LEFT);
//        BodyPart PLAYER_PAULDRON_RIGHT = new BodyPart(new Vector2f(0,0), new Vector2f(28,00),new Vector2f(.7f,.7f),AS_PAULDRON_RIGHT);
//        BodyPart PLAYER_LEGS = new BodyPart(new Vector2f(0,0), new Vector2f(0,0),new Vector2f(0,1),AS_LEGS);
//        
        BodyPart PLAYER_HEAD = new BodyPart(new Vector2f(0,0), new Vector2f(0,0),new Vector2f(0,1),AS_HEAD);
        BodyPart PLAYER_CHEST = new BodyPart(new Vector2f(0,0), new Vector2f(0,0),new Vector2f(0,1),AS_BODY);
        
        Arm PLAYER_ARM_LEFT = new Arm(new Vector2f(0,0), new Vector2f(-44,0),new Vector2f(0,1),AS_ARM_LEFT);
        Arm PLAYER_ARM_RIGHT = new Arm(new Vector2f(0,0), new Vector2f(44,0),new Vector2f(0,1),AS_ARM_RIGHT);
        
        BodyPart PLAYER_PAULDRON_LEFT = new BodyPart(new Vector2f(0,0), new Vector2f(-28,00),new Vector2f(0,1),AS_PAULDRON_LEFT);
        BodyPart PLAYER_PAULDRON_RIGHT = new BodyPart(new Vector2f(0,0), new Vector2f(28,00),new Vector2f(.7f,.7f),AS_PAULDRON_RIGHT);
        BodyPart PLAYER_LEGS = new BodyPart(new Vector2f(0,0), new Vector2f(0,0),new Vector2f(0,1),AS_LEGS);
         
        PLAYER_HEAD.makeHead(this);
    
        this.parts[HEAD] = PLAYER_HEAD;
        this.parts[CHEST] = PLAYER_CHEST;
        this.parts[ARM_LEFT] = PLAYER_ARM_LEFT;
        this.parts[ARM_RIGHT] = PLAYER_ARM_RIGHT;
        this.parts[PAULDRON_LEFT] = PLAYER_PAULDRON_LEFT;
        this.parts[PAULDRON_RIGHT] = PLAYER_PAULDRON_RIGHT;
        this.parts[LEGS] = PLAYER_LEGS;
        
//        this.parts[HEAD].addChildPart(PLAYER_CHEST);
//        this.parts[CHEST].addChildPart(PLAYER_LEGS);
//        this.parts[HEAD].addChildPart(PLAYER_PAULDRON_LEFT);
//        this.parts[HEAD].addChildPart(PLAYER_PAULDRON_RIGHT);
//        this.parts[PAULDRON_RIGHT].addChildPart(PLAYER_ARM_RIGHT);
//        this.parts[PAULDRON_LEFT].addChildPart(PLAYER_ARM_LEFT);
//        this.equipLeft(owner.getDefaultWeapon());
//        this.equipRight(owner.getDefaultWeapon());
    
        this.parts[HEAD].addChildPart(PLAYER_CHEST);
        this.parts[CHEST].addChildPart(PLAYER_LEGS);
        //this.parts[HEAD].addChildPart(PLAYER_PAULDRON_LEFT);
        //this.parts[HEAD].addChildPart(PLAYER_PAULDRON_RIGHT);
        this.parts[CHEST].addChildPart(PLAYER_ARM_RIGHT);
        this.parts[CHEST].addChildPart(PLAYER_ARM_LEFT);
        this.equipLeft(owner.getDefaultWeapon());
        this.equipRight(owner.getDefaultWeapon());
        
        this.scale(scale);
    }
}
