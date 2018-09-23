package wait4u.littlewing.archangel.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import wait4u.littlewing.archangel.ArchAngelME;
import wait4u.littlewing.archangel.OverlapTester;
import wait4u.littlewing.archangel.ReadText;
import wait4u.littlewing.archangel.DrawShopAndBrief;
import wait4u.littlewing.archangel.DrawGamePlay;
import wait4u.littlewing.archangel.ReturnHelper;

public class GameScreen extends DefaultScreen implements InputProcessor {
    public int a;
    public boolean bool_b;
    public final byte byte_c;
    public final byte byte_d;
    public final byte byte_e;
    public final byte byte_f;
    public final byte byte_g;
    public final byte byte_h;
    public final byte byte_i;
    public final byte byte_j;
    public int k;
    public int l;
    public int m;
    public int n;
    public int o;
    public int p;
    public int q;
    public ReadText readText;
    public int s;
    public int t;
    public int u;
    public boolean bool_v;
    public String[][] str_arr_w;
    public int x;
    public int y;
    public boolean bool_z;
    public int aa;
    public int ab;
    public int ac;
    public int ad;
    public int ae;
    public int af;
    public int ag;
    public boolean gameOff;
    public final ArchAngelME archAngel;

    public DrawShopAndBrief helper = new DrawShopAndBrief();
    public DrawGamePlay secondHelper = new DrawGamePlay();

    private static float MOBI_SCL = (float)Gdx.graphics.getWidth()/240; // FIXME 4.5 is not integer
    private static int MOBI_H = 320;  // JavaME height = 320px
    private static int MOBI_W = 240; // Original Java Phone resolution.
    float SCALE = (float)SCREEN_HEIGHT/1920;

    OrthographicCamera camera;
    SpriteBatch batch;
    Texture fireBtnTexture;

    // Ratio 3:4 ~ 9:12 So with ratio 9:16 we lost (not use) 4/16 = 1/4 of height.
    // Ie. 1920 we will cut 1/4 = 480px to keep ratio 3:4 1080:1440.
    // Bottom space used for fireBtn, so top should space only 240px
    private static int SCREEN_WIDTH = Gdx.graphics.getWidth();
    private static int SCREEN_HEIGHT = Gdx.graphics.getHeight();
    private static int BOTTOM_SPACE = (int)(SCREEN_HEIGHT/8 + 20*MOBI_SCL); // May be change for fit touch button

    // Use rectangle until figure out how to work with BoundingBox multi input.
    Rectangle upBtnRect = new Rectangle((20+(200/3))*SCALE, (20+(400/3))*SCALE, 72*SCALE, 70*SCALE);
    Rectangle downBtnRect = new Rectangle((20+(200/3))*SCALE, 20*SCALE, 72*SCALE, 70*SCALE);
    Rectangle leftBtnRect = new Rectangle(20*SCALE, (20+(200/6))*SCALE, 70*SCALE, 140*SCALE);
    Rectangle rightBtnRect = new Rectangle((20+(400/3))*SCALE, (20+(200/6))*SCALE, 2*70*SCALE, 140*SCALE);
    Rectangle optionBtnRect = new Rectangle(SCREEN_WIDTH/2+150*SCALE, SCREEN_HEIGHT/8, SCREEN_WIDTH/2-180*SCALE, 70*SCALE);
    Rectangle leftMenuBtn = new Rectangle(SCREEN_WIDTH-(275+400)*SCALE, 20*SCALE, 200*SCALE, 100*SCALE);
    Rectangle rightMenuBtn = new Rectangle(SCREEN_WIDTH-(275+200)*SCALE, 20*SCALE, 200*SCALE, 100*SCALE);

    private int game_action = 0;
    private int key_code = 0;
    private static final int GAME_ACTION_OK = 8; // simulate KEY, gameAction in J2ME
    private static final int GAME_ACTION_LEFT = 2;
    private static final int GAME_ACTION_RIGHT = 5;
    private static final int GAME_ACTION_UP = 8;
    private static final int GAME_ACTION_DOWN = 6;
    private static final int KEY_RIGHT_MENU = -7; // action = 0
    private static final int KEY_LEFT_MENU = -6;  // action = 0
    private static final int KEY_OK = -5;

    Vector3 touchPoint;
    TouchStatus touchStatus = TouchStatus.NONE;

    enum TouchStatus {
        TOUCH_DOWN, TOUCH_UP, NONE
    }

    Preferences prefs = Gdx.app.getPreferences("gamestate");
    private static final String PREF_VIBRATION      = "vibration";
    private static final String PREF_SOUND_ENABLED  = "soundenabled";
    private static final String PREF_SPEED          = "gamespeed";
    private static final String PREF_LEVEL          = "level";
    private static final String PREF_SAVEDGOLD      = "saved_gold";
    private static final String PREF_MANA           = "mana";
    private static final String PREF_GAME_STAGE     = "game_stage";
    private static final String PREF_LAST_GAME_STAGE = "last_game_stage";
    private int item_mode;

    private Texture [] imgColor; // For fillRect with color
    private Texture imgKeyNum3;
    private Texture imgSpeedUp;
    private Texture imgSpeedDown;
    private Texture touch_pad;
    private Texture touch_pad_knob;
    BitmapFont font;
    private Music music;
    Viewport viewport;

    /*
     *    https://docs.oracle.com/javame/config/cldc/ref-impl/midp2.0/jsr118/constant-values.html#javax.microedition.lcdui.Canvas.UP
     *    key code = -5 game action = 8 OK
     *    key code = 35 game action = 0 #
     *    key code = -2 game action = 6 DOWN
     *    key code = -3 game action = 5 LEFT ?
     *    key code = -3 game action = ? RIGHT
     *    key code = -1 game action = 1 UP ~ OK
     *    key code = 35 game action = 0 #
     *    key code = 49 game action = 9 KEY_2 = UP ?
     *    key code = 51 game action = 10 KEY_3 (use item)
     *    action        key_code
     *    UP = 1        KEY_NUM0 48     // Unknown (this is case: screen)
     *    DOWN = 6      KEY_NUM1 49     case 26:
     *    LEFT 2        KEY_NUM2 50     case 25: fighting
     *    RIGHT 5       KEY_NUM3 51     case 7:
     *    FIRE 8        KEY_NUM4 52     case 4:
     *    GAME_A 9      KEY_NUM5 53     case 13:
     *    GAME_B 10     KEY_NUM6 54     case 14:
     *    GAME_C 11     KEY_NUM7 55     case 27:
     *    GAME_D 12     KEY_NUM8 56
     *                  KEY_NUM9 57
     *    KEY_STAR 42
     *    KEY_POUND 35 #
     */
    public void keyPressed()
    {
        int keyCode = this.key_code;
        int i1 = getGameAction2(keyCode);
        if (this.archAngel.bool_h) {
            return;
        }
        switch (this.archAngel.screen)
        {
            case 0:
                if (this.archAngel.game_state == 1) {
                    this.archAngel.game_state += 1;
                }
                break;
            case 3: // Game menu screen from new game to easy mode
                if (this.archAngel.game_state == 2) {
                    switch (keyCode)
                    {
                        case -4:
                        case -2:
                            this.l += 1;
                            if (this.l > 1) {
                                this.l = 0;
                            }
                            this.archAngel.playSound("s_menu_move", 1);
                            break;
                        case -3:
                        case -1:
                            this.l += -1;
                            if (this.l < 0) {
                                this.l = 1;
                            }
                            this.archAngel.playSound("s_menu_move", 1);
                            break;
                        case -7: // GAME_B RIGHT_UP, TODO clear key_code (key_released)
                        case -5:
                            if (this.l == 0)
                            {
                                this.archAngel.game_state += 1;
                                this.l = 0;
                            }
                            else if (this.archAngel.bool_w == true)
                            {
                                if (this.archAngel.e()) { // Load game from RecordStore
                                    this.archAngel.game_state = 7;
                                } else {
                                    this.archAngel.game_state = 6;
                                }
                            }
                            break;
                    }
                } else if (this.archAngel.game_state == 4) {
                    switch (keyCode)
                    {
                        case -4:
                        case -3:
                        case -2:
                        case -1:
                            this.archAngel.ah = (1 - this.archAngel.ah);
                            this.archAngel.playSound("s_menu_move", 1);
                            break;
                        case -7:
                        case -5: // easy mode scene
                            this.archAngel.readMedia.readMediaStream("open");
                            this.archAngel.readMedia.reloadImageArr(0, 29); // open-0 fighter over coast
                            this.archAngel.readMedia.closeInputStream();
                            this.archAngel.game_state += 1;
                            break;
                    }
                }
                this.archAngel.bool_q = true;
                break;
            case 26: // open brief next screen (after the year 2028...)
                if ((this.archAngel.game_state > 1) && (this.archAngel.game_state < 18)) {
                    switch (keyCode)
                    {
                        case -7: // Right menu, OK, left menu respectively
                        case -5:
                            this.archAngel.game_state += 1;
                            break;
                        case -6: // KEY_LEFT_MENU
                            this.archAngel.game_state = 18; // start playing from menu brief
                    }
                }
                break;
            case 5: //
                if (this.archAngel.game_state == 3) {
                    switch (keyCode)
                    {
                        case -4: // right
                        case -2: // down
                        case 56: // key_num 8
                            this.l += 1;
                            if (this.l > 4) {
                                this.l = 0;
                            }
                            this.archAngel.playSound("s_menu_move", 1);
                            this.archAngel.bool_e = true;
                            break;
                        case -3: // left
                        case -1: // up
                        case 50: // key_num 2
                            this.l += -1;
                            if (this.l < 0) {
                                this.l = 4;
                            }
                            this.archAngel.playSound("s_menu_move", 1);
                            this.archAngel.bool_e = true;
                            break;
                        case -6: // action menu left
                            this.archAngel.screen = 9; // missoin start menu
                            break;
                        case -7: // action menu right
                        case -5: // ok
                            if (this.l == 5) {
                                this.archAngel.screen = 4;
                            } else {
                                this.archAngel.screen = ((byte)(8 + this.l));
                            }
                    }
                }
                break;
            case 2:
                break;
            case 8: // brief start
                if (((keyCode == -7) || (keyCode == -5)) && (this.archAngel.game_state > 0)) {
                    this.archAngel.game_state += 1;
                    // nickfarrow Temporary disable this condition to avoid blank screen
                    this.archAngel.game_state = 999;
                }
                break;
            case 9: // brief start
                if (this.archAngel.game_state >= 11) {
                    if ((keyCode == -7) || (keyCode == -5))
                    {
                        if (this.archAngel.game_state > 0) {
                            this.archAngel.game_state += 1;
                        }
                        // nickfarrow Temporary disable this condition to avoid blank screen
                        this.archAngel.game_state = 999;
                    }
                    else if (keyCode == -6) {
                        this.archAngel.game_state = 999;
                    }
                }
                break;
            case 25: // fighting screen
                if ((this.archAngel.game_state == 4) && (this.archAngel.bool_m == true))
                {
                    if ((this.archAngel.mainGameScreen.gamestage1 == 1) && (keyCode != 53) && (keyCode != -5))
                    {
                        this.archAngel.mainGameScreen.play_s_gun(false);
                        this.archAngel.stopSound();
                    }
                    if ((this.archAngel.mainGameScreen.gamestage1 == 3) && (this.archAngel.ag != 1) && (keyCode != 53) && (keyCode != -5))
                    {
                        keyReleased(53);
                        this.archAngel.mainGameScreen.play_s_plasma(false);
                        this.archAngel.stopSound();
                    }
                    if ((keyCode == 53) || (keyCode == -5)) // firing
                    {
                        keyReleased(50);
                        this.archAngel.mainGameScreen.failed_mission();
                        this.archAngel.mainGameScreen.hecman_step = 0; // may be used to stop turning
                        this.archAngel.mainGameScreen.hecman_y_step = 0;
                        this.archAngel.mainGameScreen.bd = 0;
                    }
                    switch (keyCode)
                    {
                        case 49:
                            this.archAngel.mainGameScreen.simple_90(true);
                            this.archAngel.mainGameScreen.hecman_step = 3;
                            break;
                        case 51:
                            this.archAngel.mainGameScreen.simple_90(false);
                            this.archAngel.mainGameScreen.hecman_step = 4;
                            break;
                        case 53:
                            if ((this.archAngel.mainGameScreen.gamestage1 == 1) && (this.archAngel.bool_m == true)) {
                                this.archAngel.mainGameScreen.play_s_gun(true);
                            }
                            if ((this.archAngel.mainGameScreen.gamestage1 == 3) && (this.archAngel.bool_n == true))
                            {
                                this.archAngel.mainGameScreen.bool_bh = false;
                                this.archAngel.mainGameScreen.play_s_plasma(true);
                            }
                            break;
                        case 57:
                            if (this.archAngel.mainGameScreen.gamestage1 == 1) {
                                this.archAngel.mainGameScreen.play_missile_sound();
                            }
                            break;
                        case 50: // NUM 2
                            this.archAngel.mainGameScreen.speed_up_down(true);
                            this.archAngel.mainGameScreen.hecman_y_step = 1;
                            break;
                        case 56: // NUM 8
                            this.archAngel.mainGameScreen.speed_up_down(false);
                            this.archAngel.mainGameScreen.hecman_y_step = 2;
                            break;
                        case 52: // key left NUM 4
                            this.archAngel.mainGameScreen.left_right(true);
                            this.archAngel.mainGameScreen.hecman_step = 1;
                            break;
                        case 54: // key right NUM 6
                            this.archAngel.mainGameScreen.left_right(false);
                            this.archAngel.mainGameScreen.hecman_step = 2;
                            break;
                        case 48: // Press 0 to reload ?
                            if (this.archAngel.mainGameScreen.gamestage1 == 3)
                            {
                                this.archAngel.mainGameScreen.bool_az = false;
                                if (this.archAngel.gameSetting.t < this.archAngel.gameSetting.r) {
                                    this.archAngel.playSound("s_reload", 1);
                                }
                                this.archAngel.mainGameScreen.bool_bh = true;
                            }
                            break;
                        case 55: // NUM7
                        default:
                            switch (keyCode)
                            {
                                case -2:  // Game speed up/down here; -2 ~ DOWN
                                    this.archAngel.mainGameScreen.speed_up_down(false);
                                    this.archAngel.mainGameScreen.hecman_y_step = 2;
                                    break;
                                case -1: // up speed
                                    this.archAngel.mainGameScreen.speed_up_down(true);
                                    this.archAngel.mainGameScreen.hecman_y_step = 1;
                                    break;
                                case -4:
                                    this.archAngel.mainGameScreen.left_right(false);
                                    this.archAngel.mainGameScreen.hecman_step = 2;
                                    break;
                                case -3:
                                    this.archAngel.mainGameScreen.left_right(true);
                                    this.archAngel.mainGameScreen.hecman_step = 1;
                                    break;
                                case -7:
                                    if (this.archAngel.bool_l) {
                                        // this.archAngel.screen = 13; // Options menu while playing
                                        // Temporary disable this function
                                    }
                                    break;
                                case -6:
                                    if (this.archAngel.bool_l) {
                                        this.archAngel.screen = 14;
                                    }
                                    break;
                                case -5: // firing
                                    if ((this.archAngel.mainGameScreen.gamestage1 == 1) && (this.archAngel.bool_m == true)) {
                                        this.archAngel.mainGameScreen.play_s_gun(true);
                                    }
                                    if ((this.archAngel.mainGameScreen.gamestage1 == 3) && (this.archAngel.bool_n == true))
                                    {
                                        this.archAngel.mainGameScreen.bool_bh = false;
                                        this.archAngel.mainGameScreen.play_s_plasma(true);
                                    }
                                    break;
                            }
                    }
                }
                break; // end fighting screen
            case 11: // info screen
                if (keyCode == -6)
                {
                    if (this.archAngel.game_state < 10) {
                        this.archAngel.game_state = this.archAngel.p;
                    }
                }
                else
                {
                    int i2 = -1;
                    if ((keyCode >= 49) && (keyCode <= 51)) // NUM_1 to NUM_3
                    {
                        if (this.archAngel.game_state < 10) {
                            i2 = keyCode - 49 + 1;
                        }
                    }
                    else
                    {
                        switch (keyCode)
                        {
                            case -3:
                            case -2: // speed down ?
                                if (this.archAngel.game_state < 10)  // Not fighting boss ?
                                {
                                    this.archAngel.readText.a += 1;
                                    this.archAngel.run_state2 = -1;
                                    this.archAngel.bool_g = true;
                                }
                                break;
                            case -4:
                            case -1: // speed up
                                if (this.archAngel.game_state < 10)
                                {
                                    this.archAngel.readText.a += -1;
                                    this.archAngel.run_state2 = -1;
                                    this.archAngel.bool_g = true;
                                }
                                break;
                            case -7:
                            case -5:
                                if (this.archAngel.game_state == 5) {
                                    i2 = this.archAngel.readText.a;
                                } else {
                                    i2 = 1;
                                }
                                break;
                        }
                        if (this.archAngel.readText.a <= 0) {
                            this.archAngel.readText.a = this.archAngel.readText.b;
                        }
                        if (this.archAngel.readText.a > this.archAngel.readText.b) {
                            this.archAngel.readText.a = 1;
                        }
                    }
                    if ((i2 > 0) && (this.archAngel.readText.int_arr_m[(i2 - 1)] > 0))
                    {
                        this.archAngel.game_state = this.archAngel.readText.int_arr_m[(i2 - 1)];
                        this.archAngel.run_state2 = 0;
                    }
                    this.archAngel.game_state = 999; // nickfarrow fix blank
                }
                break;
            case 12: // Machine shop
                System.gc();
                if (this.bool_v == true)
                {
                    this.archAngel.run_state2 = -1;
                    if ((keyCode == 50) || (keyCode == -5) || (keyCode == 49) || (keyCode == -7)) {
                        this.archAngel.a = 1;
                    }
                    if (((keyCode == 50) || (keyCode == 49) || (keyCode == -5) || (keyCode == -7) || (keyCode == -6)) && (this.archAngel.bool_s)) {
                        this.archAngel.bool_s = false;
                    }
                    if ((this.archAngel.game_state == 100) || (this.archAngel.game_state == 200) || (this.archAngel.game_state == 300)) {
                        switch (keyCode)
                        {
                            case -1:
                                if (this.archAngel.a == 1)
                                {
                                    this.archAngel.a = 7;
                                    this.archAngel.bool_r = true;
                                }
                                else
                                {
                                    this.archAngel.a += 8;
                                    this.archAngel.a = ((this.archAngel.a += -1) % 8);
                                }
                                break;
                            case -2:
                                if (this.archAngel.a == 7) {
                                    this.archAngel.a = 1;
                                } else {
                                    this.archAngel.a = (++this.archAngel.a % 8);
                                }
                                break;
                        }
                    }
                    if (((this.archAngel.game_state == 111) || (this.archAngel.game_state == 211) || (this.archAngel.game_state == 311)) && ((keyCode == -7) || (keyCode == -5)))
                    {
                        this.archAngel.a = 1;
                        if (this.archAngel.p > 0) {
                            this.archAngel.game_state = this.archAngel.p;
                        }
                    }
                    this.archAngel.bool_q = true;
                }
                if (this.bool_v == true) {
                    if (keyCode == -6)
                    {
                        this.archAngel.a = 1;
                        if (this.archAngel.p > 0) {
                            this.archAngel.game_state = this.archAngel.p;
                        }
                    }
                    else if ((this.archAngel.game_state != 111) && (this.archAngel.game_state != 211) && (this.archAngel.game_state != 311))
                    {
                        this.secondHelper.read_text_helper(keyCode, i1, this.archAngel);
                    }
                }
                this.archAngel.game_state = 999; // nickfarrow fix blank
                break;
            case 7:
                if ((keyCode == -1) || (keyCode == -2)) {
                    this.archAngel.c = (1 - this.archAngel.c);
                }
                if ((keyCode == -7) || (keyCode == -5)) {
                    if (this.archAngel.c == 0) {
                        this.archAngel.game_state = 2;
                    } else {
                        this.archAngel.game_state = 3;
                    }
                }
                break;
            case 4: // open brief screen (The year 2028...)
                if ((this.archAngel.game_state == 1) && (keyCode == -6)) {
                    this.archAngel.screen = 5;
                }
                break;
            case 1:
            case 10: // System settings
                if (this.archAngel.game_state == 1) {
                    this.archAngel.readText.bool_e = false;
                }
                if (keyCode == -6)
                {
                    if (this.archAngel.screen == 1)
                    {
                        this.archAngel.game_state = 10;
                    }
                    else if ((this.archAngel.game_state == 100) || (this.archAngel.game_state == 200) || (this.archAngel.game_state == 300))
                    {
                        this.archAngel.game_state = 50;
                    }
                    else if (((this.archAngel.game_state >= 101) && (this.archAngel.game_state <= 104)) || ((this.archAngel.game_state >= 201) && (this.archAngel.game_state <= 205)) || ((this.archAngel.game_state >= 301) && (this.archAngel.game_state <= 304)))
                    {
                        this.archAngel.game_state += -1;
                    }
                    else if (this.archAngel.p > 0)
                    {
                        if (this.archAngel.game_state == 61) {
                            this.archAngel.readText.bool_e = true;
                        }
                        this.archAngel.game_state = this.archAngel.p;
                    } else {
                        this.archAngel.game_state = 999; // nickfarrow fix blank screen
                    }
                }
                else if (this.archAngel.bool_v) {
                    this.secondHelper.read_text_helper(keyCode, i1, this.archAngel);
                }
                this.archAngel.bool_w = true;
                break;
            case 13:
                if ((keyCode != -6) && (this.archAngel.bool_v))
                {
                    this.secondHelper.read_text_helper(keyCode, i1, this.archAngel);
                    if ((this.archAngel.mainGameScreen.bool_az == true) && (this.q == 1) && (this.archAngel.readText.a == 1) && ((keyCode == -7) || (keyCode == -5) || (keyCode == 49))) {
                        this.archAngel.playSound("s_plasma", 0);
                    }
                    if ((this.archAngel.readText.a == 1) && ((keyCode == -7) || (keyCode == -5) || (keyCode == 49)))
                    {
                        this.archAngel.mainGameScreen.failed_mission();
                        this.archAngel.mainGameScreen.hecman_step = 0;
                        this.archAngel.mainGameScreen.hecman_y_step = 0;
                        this.archAngel.mainGameScreen.bd = 0;
                    }
                }
                break;
            case 6: // victory
                if ((keyCode == -7) && ((this.archAngel.game_state == 2) || (this.archAngel.game_state == 4))) {
                    this.archAngel.game_state += 1;
                }
                break;
            case 14: // pause screen
                if ((keyCode == -7) || (keyCode == -5) || (keyCode == 35))
                {
                    this.archAngel.game_state += 1;
                    if (this.archAngel.mainGameScreen.bool_az == true) {
                        this.archAngel.playSound("s_plasma", 0);
                    }
                    this.archAngel.mainGameScreen.failed_mission();
                    this.archAngel.mainGameScreen.hecman_step = 0;
                    this.archAngel.mainGameScreen.hecman_y_step = 0;
                    this.archAngel.mainGameScreen.bd = 0;
                }
                break;
            case 27:
                if ((this.archAngel.game_state > 1) && (this.archAngel.game_state < 15)) {
                    switch (keyCode)
                    {
                        case -7:
                        case -5:
                            this.archAngel.game_state += 1;
                            break;
                        case -6:
                            this.archAngel.game_state = 15;
                    }
                }
                break;
        }
    }

    public void empty_func() {}

    public void draw_data_helper(SpriteBatch paramGraphics, int paramInt1, int paramInt2)
    {
        this.o = paramInt2;
        this.p = paramInt1;
        this.helper.drawDataInTxt(paramGraphics, paramInt1, paramInt2, this.t, this.x, this.str_arr_w, this.archAngel, this.readText);
    }

    public void draw_resume(SpriteBatch paramGraphics)
    {
        if (this.archAngel.run_state2 > 0) {
            // nickfarrow added; run_state2 keep > 0 so "PAUSE" message not show continuously
            fillRect(paramGraphics, 0, 150, 240, 16, 4);
            int position_y = (int) ((MOBI_H-152)*MOBI_SCL + BOTTOM_SPACE);
            font.setColor(1, 1, 1, 1);
            font.draw(paramGraphics, "PAUSE", (int)(98 * MOBI_SCL), position_y);
            this.archAngel.draw_string_y305(paramGraphics, "RESUME", false);

            return;
        }
        switch (this.archAngel.game_state)
        {
            case 0:
                this.archAngel.stopSound();
                // paramGraphics.setColor(0);
                fillRect(paramGraphics, 0, 150, 240, 15, 4);

                int position_y = (int) ((MOBI_H-152)*MOBI_SCL + BOTTOM_SPACE);
                font.setColor(1, 1, 1, 1);
                font.draw(paramGraphics, "PAUSE", (int)(98 * MOBI_SCL), position_y);
                // this.archAngel.readMedia.drawGraphicStr40_122(paramGraphics, 98, 152, "PAUSE");

                this.archAngel.drawImage(paramGraphics);
                this.archAngel.draw_string_y305(paramGraphics, "RESUME", false);
                break;
            case 1:
                this.archAngel.temp_screen = (this.archAngel.screen = 25);
                this.archAngel.game_state = 4;
                return;
        }
    }

    public void paint(SpriteBatch paramGraphics) // J2ME
    {
    }

    public GameScreen(ArchAngelME paramArchAngel, Game game)
    {
        super(game);
        (this.archAngel = paramArchAngel).getClass();
        this.a = 0;
        this.bool_b = true;
        this.byte_c = -1;
        this.byte_d = -2;
        this.byte_e = -3;
        this.byte_f = -4;
        this.byte_g = -5;
        this.byte_h = -6;
        this.byte_i = -7;
        this.byte_j = -8;
        this.k = 0;
        this.l = 0;
        this.m = 0;
        this.n = 0;
        this.o = -1;
        this.p = -1;
        this.bool_v = true;
        this.str_arr_w = new String[][] { { "DAMAGE", "BULLET/M", "PRICE" }, { "DEFENCE", "REPAIR", "PRICE" } };
        this.y = 1;
        this.bool_z = false;
        this.aa = 0;
        this.ab = 0;
        this.ac = 0;
        this.ad = 0;
        this.ae = 0;
        this.af = 0;
        this.ag = 0;
        this.gameOff = false;

        Gdx.input.setInputProcessor(this);
        Gdx.input.setCatchBackKey( true );

        // Calculate global var width/height, view port ...
        create();

        touchPoint = new Vector3();
        // game_speed = getGameSpeed();
        // game_play(stage);
    }

    public void void_empty() {}

    public void keyReleased(int paramInt)
    {
        switch (this.archAngel.screen)
        {
            case 25:
                if (this.archAngel.mainGameScreen.gamestage1 == 1)
                {
                    if ((paramInt == -5) || (paramInt == 53))
                    {
                        this.archAngel.mainGameScreen.play_s_gun(false);
                        this.archAngel.stopSound();
                    }
                }
                else if ((this.archAngel.mainGameScreen.gamestage1 == 3) && ((paramInt == -5) || (paramInt == 53)) && (this.archAngel.ag != 1))
                {
                    this.archAngel.mainGameScreen.play_s_plasma(false);
                    this.archAngel.stopSound();
                }
                if ((paramInt == 49) || (paramInt == 50) || (paramInt == 51) || (paramInt == 52) || (paramInt == 54) || (paramInt == 57) || (paramInt == -1) || (paramInt == -2) || (paramInt == -4) || (paramInt == -3))
                {
                    this.archAngel.mainGameScreen.failed_mission();
                    this.archAngel.mainGameScreen.hecman_step = 0;
                    this.archAngel.mainGameScreen.hecman_y_step = 0;
                    this.archAngel.mainGameScreen.bd = 0;
                }
                break;
        }
    }

    public void showNotify()
    {
        this.archAngel.bool_t = true;
        this.archAngel.run_state2 = -1;
        if (this.archAngel.screen == 25)
        {
            this.archAngel.mainGameScreen.failed_mission();
            this.archAngel.mainGameScreen.hecman_step = 0;
            this.archAngel.mainGameScreen.hecman_y_step = 0;
            this.archAngel.mainGameScreen.bd = 0;
        }
    }

    public void draw_shop_info_arm(SpriteBatch paramGraphics)
    {
        if ((this.archAngel.game_state > 0) && (this.archAngel.run_state2 > 0)) {
            return;
        }
        this.secondHelper.setup2(paramGraphics, this.archAngel.game_state, 210, this.o, this.p, this.q, this.t, this.x, this.str_arr_w, this.archAngel, this.readText, this.helper);
        switch (this.archAngel.game_state)
        {
            case 0:
                if (this.archAngel.run_state2 == 0)
                {
                    this.archAngel.readText.readTextFromStream("info");
                    this.readText = new ReadText();
                    this.o = 186;
                    this.p = 5;
                    this.archAngel.readMedia.readMediaStream("shop");
                    for (int i1 = 0; i1 < 3; i1++) {
                        this.archAngel.readMedia.reloadImageArr(i1, 26 + i1);
                    }
                    this.archAngel.readMedia.closeInputStream();
                    this.archAngel.readMedia.drawImageAnchor20(paramGraphics, 14, 17, 89);
                    // paramGraphics.setClip(17, 89, 223, 25);
                    this.archAngel.readMedia.drawImageAnchor20(paramGraphics, 13, 60, 90 - this.l * 33);
                    // paramGraphics.setClip(0, 0, 240, 320);
                }
                this.helper.simple_helper(paramGraphics, this.archAngel);
                return;
            case 1:
                this.archAngel.drawImage(paramGraphics);
                this.archAngel.draw_string_y305(paramGraphics, "BACK", true);
                this.archAngel.draw_string_y305(paramGraphics, "OK", false);
                this.archAngel.d = 2;
                this.archAngel.game_state = 5;
                break;
            case 10:
                this.readText.readTextFromStream("missile");
                this.archAngel.d = 0;
                this.secondHelper.draw_weapon_shop(paramGraphics, 100 + this.archAngel.gameSetting.d, false, 0, this.t, this.u, this.str_arr_w, this.archAngel, this.readText);
                this.archAngel.readText.bool_c = true;
                this.archAngel.readText.g = 1;
                break;
            case 20:
                this.readText.readTextFromStream("plasma");
                this.archAngel.d = 1;
                this.secondHelper.draw_weapon_shop(paramGraphics, 200 + this.archAngel.gameSetting.e, false, 1, this.t, this.u, this.str_arr_w, this.archAngel, this.readText);
                this.archAngel.readText.bool_c = true;
                this.archAngel.readText.g = 2;
                break;
            case 30:
                this.readText.readTextFromStream("arm");
                this.archAngel.d = 2;
                this.secondHelper.draw_weapon_shop(paramGraphics, 300 + this.archAngel.gameSetting.f, false, 2, this.t, this.u, this.str_arr_w, this.archAngel, this.readText);
                this.archAngel.readText.bool_c = true;
                this.archAngel.readText.g = 3;
                break;
            case 999:
                this.archAngel.screen = 5;
                this.readText = null;
                return;
        }
    }

    public void draw_shop_arm2(SpriteBatch paramGraphics)
    {
        if ((this.archAngel.game_state > 0) && (this.archAngel.run_state2 > 0)) {
            return;
        }
        this.bool_v = false;
        switch (this.archAngel.game_state)
        {
            case 0:
                if (this.archAngel.run_state2 == 0)
                {
                    this.archAngel.readMedia.readMediaStream("shop");
                    for (int i1 = 0; i1 < 3; i1++)
                    {
                        this.archAngel.readMedia.destroyImage(26 + i1);
                        this.archAngel.readMedia.reloadImageArr(i1, 26 + i1);
                    }
                    this.archAngel.readMedia.closeInputStream();
                    this.archAngel.readMedia.drawImageAnchor20(paramGraphics, 14, 17, 89);
                    // paramGraphics.setClip(17, 89, 223, 25);
                    this.archAngel.readMedia.drawImageAnchor20(paramGraphics, 13, 60, 90 - this.l * 33);
                    // paramGraphics.setClip(0, 0, 240, 320);
                    this.archAngel.drawImage(paramGraphics);
                    this.archAngel.draw_string_y305(paramGraphics, "BACK", true);
                    this.archAngel.draw_string_y305(paramGraphics, "OK", false);
                    this.archAngel.readText.readTextFromStream("shop");
                    this.readText = new ReadText();
                    this.archAngel.readText.g = 1;
                }
                this.helper.simple_helper(paramGraphics, this.archAngel);
                return;
            case 2:
                this.archAngel.drawImage(paramGraphics);
                this.archAngel.draw_string_y305(paramGraphics, "BACK", true);
                this.archAngel.draw_string_y305(paramGraphics, "OK", false);
                this.archAngel.game_state = 1;
                break;
            case 1:
                this.archAngel.drawImage(paramGraphics);
                this.archAngel.draw_string_y305(paramGraphics, "BACK", true);
                this.archAngel.draw_string_y305(paramGraphics, "OK", false);
                this.archAngel.readText.bool_c = true;
                this.bool_v = true;
                break;
            case 99:
                this.archAngel.game_state += 1;
                this.readText.readTextFromStream("missile");
                this.archAngel.d = 0;
                this.archAngel.readText.g = 1;
                break;
            case 111:
            case 211:
                this.archAngel.a = 1;
                this.secondHelper.setup2(paramGraphics, this.archAngel.game_state, 230, this.o, this.p, this.q, this.t, this.x, this.str_arr_w, this.archAngel, this.readText, this.helper);
                if (this.t >= this.readText.int_arr_m[2]) {
                    this.secondHelper.draw_buy(paramGraphics, this.s, this.readText.int_arr_m[2], this.x, this.archAngel, this.readText);
                } else {
                    draw_lack_of(paramGraphics, this.readText.int_arr_m[2]);
                }
                this.bool_v = true;
                return;
            case 199:
                this.archAngel.game_state += 1;
                this.readText.readTextFromStream("plasma");
                this.archAngel.d = 1;
                this.archAngel.readText.g = 2;
                break;
            case 299:
                this.archAngel.game_state += 1;
                this.readText.readTextFromStream("arm");
                this.archAngel.d = 2;
                this.archAngel.readText.g = 3;
                break;
            case 311:
                this.archAngel.a = 1;
                this.secondHelper.setup2(paramGraphics, this.archAngel.game_state, 230, this.o, this.p, this.q, this.t, this.x, this.str_arr_w, this.archAngel, this.readText, this.helper);
                if (this.t >= this.readText.int_arr_m[2]) {
                    this.secondHelper.draw_buy(paramGraphics, this.s, this.readText.int_arr_m[2], this.x, this.archAngel, this.readText);
                } else {
                    draw_lack_of(paramGraphics, this.readText.int_arr_m[2]);
                }
                this.bool_v = true;
                return;
            case 999:
                this.archAngel.screen = 5;
                this.readText = null;
                return;
            default:
                if ((this.archAngel.game_state % 100 > 0) && (this.archAngel.game_state % 100 < 8) && (this.archAngel.game_state > 100))
                {
                    this.helper.b(paramGraphics, this.archAngel.game_state, this.x, this.t, this.str_arr_w, this.archAngel, this.readText);
                    this.archAngel.drawImage(paramGraphics);
                    this.archAngel.draw_string_y305(paramGraphics, "BACK", true);
                    this.archAngel.draw_string_y305(paramGraphics, "OK", false);
                    // paramGraphics.setClip(0, 0, 178, 75);
                    this.helper.draw_ammunation_buy(paramGraphics, this.archAngel.game_state, true, this.archAngel.d, this.x, this.t, this.str_arr_w, this.archAngel, this.readText);
                    this.s = this.archAngel.game_state;
                    switch (this.archAngel.d)
                    {
                        case 0:
                            this.archAngel.game_state = 110;
                            break;
                        case 1:
                            this.archAngel.game_state = 210;
                            break;
                        case 2:
                            this.archAngel.game_state = 310;
                            break;
                    }
                    paramGraphics.setColor(0);
                    this.archAngel.bool_s = true;
                    paramGraphics.setColor(65280);
                    String str = "";
                    if (this.t - this.archAngel.gameSetting.a > this.u) {
                        str = "[+";
                    } else {
                        str = "[";
                    }
                    str = str + (this.t - this.archAngel.gameSetting.a - this.u) + "]"; // String
                    this.bool_v = true;
                    return;
                }
                this.bool_v = true;
        }
        if ((this.archAngel.game_state == 100) || (this.archAngel.game_state == 200) || (this.archAngel.game_state == 300))
        {
            if (this.archAngel.run_state2 == 0) {
                this.archAngel.bool_q = true;
            }
            this.archAngel.readText.bool_c = false;
            this.helper.b(paramGraphics, this.archAngel.game_state, this.x, this.t, this.str_arr_w, this.archAngel, this.readText);
            draw_data_helper(paramGraphics, this.archAngel.game_state, 190);
            this.archAngel.drawImage(paramGraphics);
            this.archAngel.draw_string_y305(paramGraphics, "BACK", true);
            this.archAngel.draw_string_y305(paramGraphics, "OK", false);
        }
        else
        {
            this.secondHelper.setup2(paramGraphics, this.archAngel.game_state, 150, this.o, this.p, this.q, this.t, this.x, this.str_arr_w, this.archAngel, this.readText, this.helper);
        }
    }

    public void run()
    {
        if ((this.archAngel.screen == 25) && (this.archAngel.game_state == 4))
        {
            this.archAngel.mainGameScreen.boss_finder_ai();
            this.archAngel.mainGameScreen.draw_fighting();
        }

        if (this.gameOff) { // like GameOff, true then stop paint
            // Paint done ? can it just remove return to debug
            return;
        }
        this.gameOff = true;
        this.archAngel.run_state2 += 1;
        if (this.archAngel.screen != this.archAngel.temp_screen)
        {
            this.archAngel.run_state2 = 0;
            this.archAngel.game_state = 0;
            this.archAngel.temp_state = 0;
            this.archAngel.p = -1;
            this.bool_b = true;
            this.archAngel.temp_screen = this.archAngel.screen;
            this.archAngel.bool_i = false;
        }
        else if (this.archAngel.game_state != this.archAngel.temp_state)
        {
            this.archAngel.run_state2 = 0;
            this.archAngel.temp_state = this.archAngel.game_state;
        }

        switch (this.archAngel.screen)
        {
            case 25:
                ReturnHelper returnHelper2 = this.secondHelper.draw_game_play_screen(batch, this.archAngel);
                // If this.l value has changed
                this.l = (returnHelper2.one > returnHelper2.MIN_INT) ? returnHelper2.one : this.l;
                if(returnHelper2.bool_one >= 0) {
                    this.bool_b = (returnHelper2.bool_one != 0) ? true : false;
                }
                this.aa = (returnHelper2.yi > returnHelper2.MIN_INT) ? returnHelper2.yi : this.aa;
                this.ab = (returnHelper2.er > returnHelper2.MIN_INT) ? returnHelper2.er : this.ab;
                this.ac = (returnHelper2.san > returnHelper2.MIN_INT) ? returnHelper2.san : this.ac;
                this.ad = (returnHelper2.si > returnHelper2.MIN_INT) ? returnHelper2.si : this.ad;
                this.ae = (returnHelper2.wu > returnHelper2.MIN_INT) ? returnHelper2.wu : this.ae;
                this.af = (returnHelper2.liu > returnHelper2.MIN_INT) ? returnHelper2.liu : this.af;
                this.ag = (returnHelper2.qi > returnHelper2.MIN_INT) ? returnHelper2.qi : this.ag;
                if(returnHelper2.bool_yi > 0) {
                    this.bool_z = (returnHelper2.bool_yi != 0) ? true : false;
                }
                break;
            case 0:
                this.secondHelper.draw_intro(batch, this.archAngel);
                break;
            case 3:
                this.helper.drawGameMenu(batch, this.l, this.archAngel);
                break;
            case 1:
                this.secondHelper.load_system_txt(batch, this.l, this.o, this.p, this.q, this.t, this.x, this.y, this.str_arr_w, this.archAngel, this.readText, this.helper);
                break;
            case 5:
                this.secondHelper.draw_warning_etc_menu(batch, this.l, this.archAngel);
                break;
            case 13:
                this.secondHelper.goto_menu(batch, this.o, this.p, this.q, this.t, this.x, this.str_arr_w, this.archAngel, this.readText, this.helper);
                break;
            case 14:
                this.draw_resume(batch);
                break;
            case 9:
                if (this.archAngel.gameSetting.c != this.archAngel.gameSetting.boss_level) {
                    ReturnHelper startReturn =
                    this.secondHelper.draw_start_option(batch, this.o, this.p, this.archAngel);
                    this.o = (startReturn.one > startReturn.MIN_INT) ? startReturn.one : this.o;
                    this.p = (startReturn.two > startReturn.MIN_INT) ? startReturn.two : this.p;
                } else {
                    this.archAngel.screen = 25;
                }
                break;
            case 8:
                ReturnHelper startReturn =
                this.secondHelper.draw_start_option(batch, this.o, this.p, this.archAngel);
                this.o = (startReturn.one > startReturn.MIN_INT) ? startReturn.one : this.o;
                this.p = (startReturn.two > startReturn.MIN_INT) ? startReturn.two : this.p;
                break;
            case 10:
                this.helper.draw_system_setin(batch, this.l, this.o, this.p, this.q, this.t, this.x, this.y, this.str_arr_w, this.archAngel, this.readText);
                break;
            case 11:
                this.draw_shop_info_arm(batch);
                break;
            case 12:
                this.draw_shop_arm2(batch);
                break;
            case 4:
                this.helper.briefAbout(batch, this.o, this.p, this.l, this.archAngel);
                break;
            case 26:
                this.helper.briefOpen(batch, this.o, this.p, this.archAngel);
                break;
            case 2:
                this.simple_read_helper(batch);
                break;
            case 7:
                this.helper.displayGameOver(batch, this.archAngel); // gamestate = 1
                break;
            case 6: // victory
                this.secondHelper.draw_font_result(batch, this.archAngel, this.helper);
                break;
            case 27: // Final mission complete
                this.helper.loadBrief1(batch, this.o, this.p, this.archAngel);
                break;
        }
        this.gameOff = false;
    }

    public void draw_lack_of(SpriteBatch paramGraphics, int paramInt)
    {
        this.archAngel.readMedia.drawGraphicStr40_122(paramGraphics, 12, 145, "Lack of " + (paramInt - this.t));
    }

    public void simple_read_helper(SpriteBatch paramGraphics)
    {
        this.archAngel.gameSetting.initSetting();
        this.archAngel.gameSetting.readArmPlasmaMissile(this.archAngel.readText);
        this.archAngel.screen = 5;
    }

    @Override
    public boolean keyDown(int keycode) {
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        this.key_code = 0;
        batch.begin();
        // TODO use boundingBox and touchAreas; pass touchPoint instead of global
        // Pointers are the number of touches on the screen
        // TODO study libgdx-mario-bros key handle mechanism
        touchPoint.set(Gdx.input.getX(),Gdx.input.getY(), 0);

        // Gdx.app.log("DEBUG", "touch " + touchPoint.x + " y "+ (SCREEN_HEIGHT-touchPoint.y) + " key_code "+ this.key_code);
        game_action = getGameAction(pointer);

        if (isTouchedMenuLeft()) {
            this.key_code = KEY_LEFT_MENU;
            Gdx.input.vibrate(5);
        } else if (isTouchedMenuRight()) {
            this.key_code = KEY_RIGHT_MENU;
            Gdx.input.vibrate(5);
        } else if (isTouchedNum3()) {
            this.key_code = 57;
        }

        keyPressed();
        batch.end();

        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        // if(isTouchedMenuLeft() || isTouchedMenuRight() || isTouchedOK() || isTouchedUp() || isTouchedDown() || isTouchedLeft() || isTouchedRight()) {
        if(isTouchedLeft() || isTouchedRight()) {
            // Only apply tricky way on fighting scene
            if(this.archAngel.screen == 25) {
                if (this.archAngel.mainGameScreen.gamestage1 == 1) {
                    // normal play not boss scene
                    // Use key_code 53 (NUM5 ~ fire) for clear key_code, action LEFT
                    this.key_code = 53; // Fix me This is tricky way to implement touch & hold
                    keyPressed();
                    this.game_action = 0;
                }
            }
        }

        if(isTouchedUp() || isTouchedDown()) {
            if(this.archAngel.mainGameScreen.gamespeed <= 140 && this.archAngel.mainGameScreen.gamespeed >= 20) {
                if (this.archAngel.mainGameScreen.gamestage1 == 1) {
                    // normal play not boss scene
                    // Use key_code 53 (NUM5 ~ fire) for clear key_code, action LEFT
                    this.key_code = 53; // Fix me This is tricky way to implement touch & hold
                    keyPressed();
                    this.game_action = 0;
                }
            }
        }

        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl20.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        camera.position.x = SCREEN_WIDTH/2;
        camera.position.y = SCREEN_HEIGHT/2;
        camera.update();

        batch.enableBlending();
        batch.begin();

        run();

        // drawTouchPad();
        drawUI();
        batch.end();

        //if(this.game_action == 0 && key_code == 0 && touchStatus == TouchStatus.TOUCH_UP) {
        //    touchStatus = TouchStatus.NONE;
        //}
    }

    @Override
    public void hide() {

    }

    /*
     * Simulate J2ME keyCode
     * https://docs.oracle.com/javame/config/cldc/ref-impl/midp2.0/jsr118/constant-values.html#javax.microedition.lcdui.Canvas.UP
     * */
    public int getGameAction2(int keyCode) {
        return game_action;
    }

    public int getGameAction(int pointer) {
        if(isTouchedUp()) {
            Gdx.input.vibrate(5);
            this.key_code = -1;
            return GAME_ACTION_UP;
        }
        if(isTouchedDown()) { // Careful with game state, ie. item_mode = 0
            Gdx.input.vibrate(5);
            this.key_code = -2;
            return GAME_ACTION_DOWN;
        }
        if(isTouchedLeft() && Gdx.input.isTouched()) {
            Gdx.input.vibrate(5);
            this.key_code = -3;
            return GAME_ACTION_LEFT;
        }
        if(isTouchedRight()) {
            this.key_code = -4;
            Gdx.input.vibrate(5);
            return GAME_ACTION_RIGHT;
        }

        if(isTouchedOK()) {
            this.key_code = KEY_OK; // -5
            Gdx.input.vibrate(5);
            return GAME_ACTION_OK;
        }

        return 0;
    }

    public void fillRect(SpriteBatch paramGraphics, int x, int y, int width, int height, int color) {
        // Hard code default width x height of color img: 12x12 px
        float scaleY = (float) (height*MOBI_SCL / 12);
        float scaleX = (float) (width*MOBI_SCL / 12);
        // (Texture, float x, float destroy_n_e, float originX, float originY, float width, float height, float scaleX, float scaleY, float rotation, int srcX, int srcY, int srcWidth, int srcHeight, boolean flipX, boolean flipY)
        int pos_x = (int) (MOBI_SCL*x);
        int pos_y = (int) ((MOBI_H - y)*MOBI_SCL - imgColor[color].getHeight()*scaleY + BOTTOM_SPACE);

        paramGraphics.draw(imgColor[color], pos_x, pos_y, 0, 0, imgColor[color].getWidth(), imgColor[color].getHeight(), scaleX, scaleY, 0, 0, 0, (int)(imgColor[color].getWidth()*scaleX), (int)(imgColor[color].getHeight()*scaleY), false, false);
    }

    protected void drawUI() {
        // It seem single image can have it's own event Listener such as: touchDown/Up; See bellow
        // https://github.com/BrentAureli/ControllerDemo/blob/master/core/src/com/brentaureli/overlaydemo/Controller.java
        // TODO use custom IMAGE addEventListener for more UI refine. Can image used as Texture ?
        batch.draw(fireBtnTexture, SCREEN_WIDTH-(50+fireBtnTexture.getWidth())*SCALE, (int)(50*SCALE), fireBtnTexture.getWidth()*SCALE, fireBtnTexture.getHeight()*SCALE);
        batch.draw(imgKeyNum3, SCREEN_WIDTH-(50+fireBtnTexture.getWidth()+fireBtnTexture.getWidth()/2 + imgKeyNum3.getWidth())*SCALE, (40 + imgSpeedUp.getHeight())*SCALE, imgKeyNum3.getWidth()*SCALE, imgKeyNum3.getHeight()*SCALE);
        batch.draw(imgSpeedUp, SCREEN_WIDTH-(50+fireBtnTexture.getWidth()+fireBtnTexture.getWidth()/2 + imgSpeedUp.getWidth())*SCALE, 20*SCALE, imgSpeedUp.getWidth()*SCALE, imgSpeedUp.getHeight()*SCALE);
        batch.draw(imgSpeedDown, SCREEN_WIDTH-(50+fireBtnTexture.getWidth()+fireBtnTexture.getWidth()/2 + 2*imgSpeedDown.getWidth())*SCALE, 20*SCALE, imgSpeedDown.getWidth()*SCALE, imgSpeedDown.getHeight()*SCALE);
        batch.draw(touch_pad, 20*SCALE, 20*SCALE, touch_pad.getWidth()*SCALE, touch_pad.getHeight()*SCALE);
        batch.draw(touch_pad_knob, (20+touch_pad.getWidth()/2-touch_pad_knob.getWidth()/2)*SCALE, (20+touch_pad.getHeight()/2-touch_pad_knob.getHeight()/2)*SCALE, touch_pad_knob.getWidth()*SCALE, touch_pad_knob.getHeight()*SCALE);
    }

    public void create () {
        batch = new SpriteBatch();

        //Create camera
        float aspectRatio = (float) SCREEN_WIDTH / (float) SCREEN_HEIGHT;

        camera = new OrthographicCamera();
        // This seem take no effect on 16:9 multi-screen size 1280; 1920; or 2560px But on not 16:9, ie. 4:3 iPad this may take effect.
        int VP_WIDTH = 1080;
        int VP_HEIGHT = 1920;
        camera.setToOrtho(false, SCREEN_WIDTH, SCREEN_HEIGHT);
        viewport = new FitViewport(VP_WIDTH, VP_HEIGHT, camera);
        viewport.apply();

        camera.position.x = SCREEN_WIDTH/2;
        camera.position.y = SCREEN_HEIGHT/2;
        camera.position.set(camera.viewportWidth/2,camera.viewportHeight/2,0);
        camera.update();

        Gdx.input.setInputProcessor(this); // TODO use an InputProcessor object

        loadTextures();
        this.archAngel.startApp();

        font = new BitmapFont();
        font.getRegion().getTexture().setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        font.getData().setScale(6);
    }

    public void resize(int width, int height) {
        viewport.update(width,height);
        camera.position.set(camera.viewportWidth/2,camera.viewportHeight/2,0);
    }

    private void loadTextures() {
        fireBtnTexture = new Texture("samsung-white/fire.png");

        /**
         * #0 for red
         * #1 for light blue, #3 light blue 2 6DCFF6
         * #2 for light yellow, #4 gray 93959A #5 for white
         */
        imgColor = new Texture[6];
        for (int i = 0; i < 6; i++) {
            imgColor[i] = new Texture("samsung-white/color-" + i + ".png");
        }

        imgKeyNum3 = new Texture("samsung-white/use_item_btn.png");
        imgSpeedUp = new Texture("samsung-white/right_btn.png");
        imgSpeedDown = new Texture("samsung-white/left_btn.png");
        touch_pad = new Texture("gui/touchBackground.png");
        touch_pad_knob = new Texture("gui/touchKnob.png");
    }

    protected boolean isTouchedUp() {
        // this.key_code = -1;
        return OverlapTester.pointInRectangle(upBtnRect, touchPoint.x, (SCREEN_HEIGHT-touchPoint.y) );
    }
    protected boolean isTouchedDown() {
        // this.key_code = -2;
        return OverlapTester.pointInRectangle(downBtnRect, touchPoint.x, (SCREEN_HEIGHT-touchPoint.y) );
    }
    protected boolean isTouchedLeft() {
        // this.key_code = -3;
        return OverlapTester.pointInRectangle(leftBtnRect, touchPoint.x, (SCREEN_HEIGHT-touchPoint.y) );
    }
    protected boolean isTouchedRight() {
        // this.key_code = -4;
        return OverlapTester.pointInRectangle(rightBtnRect, touchPoint.x, (SCREEN_HEIGHT-touchPoint.y) );
    }
    protected boolean isTouchedOption() {
        return OverlapTester.pointInRectangle(optionBtnRect, touchPoint.x, (SCREEN_HEIGHT-touchPoint.y) );
    }
    protected boolean isTouchedOK() {
        // this.key_code = KEY_OK;
        Rectangle textureBounds = new Rectangle(SCREEN_WIDTH-(fireBtnTexture.getWidth()+50)*SCALE, SCREEN_HEIGHT-(50+fireBtnTexture.getHeight())*SCALE, fireBtnTexture.getWidth()*SCALE,fireBtnTexture.getHeight()*SCALE);
        return textureBounds.contains(touchPoint.x, touchPoint.y);
    }
    protected boolean isTouchedNum3() {
        Rectangle textureBounds=new Rectangle(SCREEN_WIDTH-(fireBtnTexture.getWidth()+50+imgKeyNum3.getWidth()+(int)fireBtnTexture.getWidth()/2)*SCALE, SCREEN_HEIGHT-(40+imgSpeedUp.getHeight()+imgKeyNum3.getHeight())*SCALE, imgKeyNum3.getWidth()*SCALE,imgKeyNum3.getHeight()*SCALE);
        return textureBounds.contains(touchPoint.x, touchPoint.y);
    }
    protected boolean isTouchedMenuLeft() {
        // this.key_code = KEY_LEFT_MENU;
        return OverlapTester.pointInRectangle(leftMenuBtn, touchPoint.x, (SCREEN_HEIGHT-touchPoint.y) );
    }
    protected boolean isTouchedMenuRight() {
        // this.key_code = KEY_RIGHT_MENU;
        return OverlapTester.pointInRectangle(rightMenuBtn, touchPoint.x, (SCREEN_HEIGHT-touchPoint.y) );
    }

    protected Preferences getPrefs() {
        if(prefs==null){
            prefs = Gdx.app.getPreferences("gamestate");
        }
        return prefs;
    }

    public boolean isSoundEffectsEnabled() {
        return getPrefs().getBoolean(PREF_SOUND_ENABLED, true);
    }

    public void setSoundEffectsEnabled(boolean soundEffectsEnabled) {
        getPrefs().putBoolean(PREF_SOUND_ENABLED, soundEffectsEnabled);
        getPrefs().flush();
    }

    public boolean getVibraEnabled() {
        return getPrefs().getBoolean(PREF_VIBRATION, true);
    }

    public void setVibraEnabled(boolean vibra) {
        getPrefs().putBoolean(PREF_VIBRATION, vibra);
        getPrefs().flush();
    }

    public int getLevel() {
        return getPrefs().getInteger(PREF_LEVEL, 11);
    }

    public void setLevel(int level) {
        getPrefs().putInteger(PREF_LEVEL, level);
        getPrefs().flush();

    }
    public int getSavedgold() {
        return getPrefs().getInteger(PREF_SAVEDGOLD, 64);
    }

    public void setSavedGold(int saved_gold) {
        getPrefs().putInteger(PREF_SAVEDGOLD, saved_gold);
        getPrefs().flush();
    }

    public int getSavedMana() {
        return getPrefs().getInteger(PREF_MANA, 64);
    }

    public void setSavedMana(int saved_gold) {
        getPrefs().putInteger(PREF_MANA, saved_gold);
        getPrefs().flush();
    }

    public int getGameSpeed() {
        return getPrefs().getInteger(PREF_SPEED, 24);
    }

    public void setGameSpeed(int saved_gold) {
        getPrefs().putInteger(PREF_SPEED, saved_gold);
        getPrefs().flush();
    }
    public int getGameStage() {
        return getPrefs().getInteger(PREF_GAME_STAGE, 11);
    }

    public void setGameStage(int game_stage) {
        getPrefs().putInteger(PREF_GAME_STAGE, game_stage);
        getPrefs().flush();
    }
    public int getLastGameStage() {
        return getPrefs().getInteger(PREF_LAST_GAME_STAGE, 11);
    }

    public void setLastGameStage(int last_game_stage) {
        getPrefs().putInteger(PREF_LAST_GAME_STAGE, last_game_stage);
        getPrefs().flush();
    }

}
