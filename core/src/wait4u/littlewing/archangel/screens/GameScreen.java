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

    private static int SMALL_GAP = 32; // 32px for gap

    OrthographicCamera camera;
    SpriteBatch batch;
    Texture fireBtnTexture;

    // Ratio 3:4 ~ 9:12 So with ratio 9:16 we lost (not use) 4/16 = 1/4 of height.
    // Ie. 1920 we will cut 1/4 = 480px to keep ratio 3:4 1080:1440.
    // Bottom space used for fireBtn, so top should space only 240px
    private static int SCREEN_WIDTH = Gdx.graphics.getWidth();
    private static int SCREEN_HEIGHT = Gdx.graphics.getHeight();
    private static int SGH_120_CELL = 5; // 5 pixel per cell in original 120px SGH
    private static int SGH_SCALE_RATIO = (int)Gdx.graphics.getWidth()/120; // 120 or 128px from original J2ME resolution.
    private static int CELL_WIDTH = SGH_120_CELL*SGH_SCALE_RATIO;
    private static int VIEW_PORT_HEIGHT = (int)SCREEN_HEIGHT*3/4;
    private static int TOP_BOUND = VIEW_PORT_HEIGHT + (int)SCREEN_HEIGHT/8;
    private static int BOTTOM_SPACE = (int)SCREEN_HEIGHT/8; // May be change for fit touch button

    // Use rectangle until figure out how to work with BoundingBox multi input.
    Rectangle upBtnRect = new Rectangle(20+(200/3), 20+(400/3), 72, 70);
    Rectangle downBtnRect = new Rectangle(20+(200/3), 20, 72, 70);
    Rectangle leftBtnRect = new Rectangle(20, 20+(200/6), 70, 140);
    Rectangle rightBtnRect = new Rectangle(20+(400/3), 20+(200/6), 2*70, 140);
    Rectangle optionBtnRect = new Rectangle(SCREEN_WIDTH/2+150, SCREEN_HEIGHT/8, SCREEN_WIDTH/2-180, 70);
    Rectangle leftMenuBtn = new Rectangle(SCREEN_WIDTH-275-400, 20, 200, 100);
    Rectangle rightMenuBtn = new Rectangle(SCREEN_WIDTH-275-200, 20, 200, 100);

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
    private static final int KEY_NUM_3 = 0; // for item mode
    private static final int KEY_SHARP = 0;

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
     *    key code = -7 game action = 0 RIGHT_MENU
     */
    public void keyPressed()
    {
        int paramInt = this.key_code;
        int i1 = getGameAction(paramInt);
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
            case 3:
                if (this.archAngel.game_state == 2) {
                    switch (paramInt)
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
                                if (this.archAngel.e()) {
                                    this.archAngel.game_state = 7;
                                } else {
                                    this.archAngel.game_state = 6;
                                }
                            }
                            break;
                    }
                } else if (this.archAngel.game_state == 4) {
                    switch (paramInt)
                    {
                        case -4:
                        case -3:
                        case -2:
                        case -1:
                            this.archAngel.ah = (1 - this.archAngel.ah);
                            this.archAngel.playSound("s_menu_move", 1);
                            break;
                        case -7:
                        case -5:
                            this.archAngel.readMedia.readMediaStream("open");
                            this.archAngel.readMedia.reloadImageArr(0, 29);
                            this.archAngel.readMedia.closeInputStream();
                            this.archAngel.game_state += 1;
                            break;
                    }
                }
                this.archAngel.bool_q = true;
                break;
            case 26:
                if ((this.archAngel.game_state > 1) && (this.archAngel.game_state < 18)) {
                    switch (paramInt)
                    {
                        case -7: // Right menu, OK, left menu respectively
                        case -5:
                            this.archAngel.game_state += 1;
                            break;
                        case -6:
                            this.archAngel.game_state = 18;
                    }
                }
                break;
            case 5:
                if (this.archAngel.game_state == 3) {
                    switch (paramInt)
                    {
                        case -4: // right
                        case -2: // down
                        case 56: // may be key_num 8
                            this.l += 1;
                            if (this.l > 4) {
                                this.l = 0;
                            }
                            this.archAngel.playSound("s_menu_move", 1);
                            this.archAngel.bool_e = true;
                            break;
                        case -3: // left
                        case -1: // up
                        case 50: // may be key_num 2
                            this.l += -1;
                            if (this.l < 0) {
                                this.l = 4;
                            }
                            this.archAngel.playSound("s_menu_move", 1);
                            this.archAngel.bool_e = true;
                            break;
                        case -6: // menu left
                            this.archAngel.screen = 9;
                            break;
                        case -7: // menu right
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
            case 8:
                if (((paramInt == -7) || (paramInt == -5)) && (this.archAngel.game_state > 0)) {
                    this.archAngel.game_state += 1;
                }
                break;
            case 9:
                if (this.archAngel.game_state >= 11) {
                    if ((paramInt == -7) || (paramInt == -5))
                    {
                        if (this.archAngel.game_state > 0) {
                            this.archAngel.game_state += 1;
                        }
                    }
                    else if (paramInt == -6) {
                        this.archAngel.game_state = 999;
                    }
                }
                break;
            case 25: // fighting screen
                if ((this.archAngel.game_state == 4) && (this.archAngel.bool_m == true))
                {
                    if ((this.archAngel.mainGameScreen.gamestage1 == 1) && (paramInt != 53) && (paramInt != -5))
                    {
                        this.archAngel.mainGameScreen.play_s_gun(false);
                        this.archAngel.stopSound();
                    }
                    if ((this.archAngel.mainGameScreen.gamestage1 == 3) && (this.archAngel.ag != 1) && (paramInt != 53) && (paramInt != -5))
                    {
                        keyReleased(53);
                        this.archAngel.mainGameScreen.play_s_plasma(false);
                        this.archAngel.stopSound();
                    }
                    if ((paramInt == 53) || (paramInt == -5))
                    {
                        keyReleased(50);
                        this.archAngel.mainGameScreen.setup3();
                        this.archAngel.mainGameScreen.hecman_step = 0; // may be used to stop turning
                        this.archAngel.mainGameScreen.hecman_y_step = 0;
                        this.archAngel.mainGameScreen.bd = 0;
                    }
                    switch (paramInt)
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
                        case 50:
                            this.archAngel.mainGameScreen.speed_up_down(true);
                            this.archAngel.mainGameScreen.hecman_y_step = 1;
                            break;
                        case 56:
                            this.archAngel.mainGameScreen.speed_up_down(false);
                            this.archAngel.mainGameScreen.hecman_y_step = 2;
                            break;
                        case 52:
                            this.archAngel.mainGameScreen.left_right(true);
                            this.archAngel.mainGameScreen.hecman_step = 1;
                            break;
                        case 54:
                            this.archAngel.mainGameScreen.left_right(false);
                            this.archAngel.mainGameScreen.hecman_step = 2;
                            break;
                        case 48:
                            if (this.archAngel.mainGameScreen.gamestage1 == 3)
                            {
                                this.archAngel.mainGameScreen.bool_az = false;
                                if (this.archAngel.gameSetting.t < this.archAngel.gameSetting.r) {
                                    this.archAngel.playSound("s_reload", 1);
                                }
                                this.archAngel.mainGameScreen.bool_bh = true;
                            }
                            break;
                        case 55:
                        default:
                            switch (paramInt)
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
                                        this.archAngel.screen = 13;
                                    }
                                    break;
                                case -6:
                                    if (this.archAngel.bool_l) {
                                        this.archAngel.screen = 14;
                                    }
                                    break;
                                case -5:
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
            case 11:
                if (paramInt == -6)
                {
                    if (this.archAngel.game_state < 10) {
                        this.archAngel.game_state = this.archAngel.p;
                    }
                }
                else
                {
                    int i2 = -1;
                    if ((paramInt >= 49) && (paramInt <= 51))
                    {
                        if (this.archAngel.game_state < 10) {
                            i2 = paramInt - 49 + 1;
                        }
                    }
                    else
                    {
                        switch (paramInt)
                        {
                            case -3:
                            case -2: // speed down ?
                                if (this.archAngel.game_state < 10)  // Not fighting boss ?
                                {
                                    this.archAngel.readText.a += 1;
                                    this.archAngel.x = -1;
                                    this.archAngel.bool_g = true;
                                    paramInt = 0; // fix me
                                }
                                break;
                            case -4:
                            case -1: // speed up
                                if (this.archAngel.game_state < 10)
                                {
                                    this.archAngel.readText.a += -1;
                                    this.archAngel.x = -1;
                                    this.archAngel.bool_g = true;
                                    paramInt = 0; // fix me
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
                        this.archAngel.x = 0;
                    }
                }
                break;
            case 12:
                System.gc();
                if (this.bool_v == true)
                {
                    this.archAngel.x = -1;
                    if ((paramInt == 50) || (paramInt == -5) || (paramInt == 49) || (paramInt == -7)) {
                        this.archAngel.a = 1;
                    }
                    if (((paramInt == 50) || (paramInt == 49) || (paramInt == -5) || (paramInt == -7) || (paramInt == -6)) && (this.archAngel.bool_s)) {
                        this.archAngel.bool_s = false;
                    }
                    if ((this.archAngel.game_state == 100) || (this.archAngel.game_state == 200) || (this.archAngel.game_state == 300)) {
                        switch (paramInt)
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
                    if (((this.archAngel.game_state == 111) || (this.archAngel.game_state == 211) || (this.archAngel.game_state == 311)) && ((paramInt == -7) || (paramInt == -5)))
                    {
                        this.archAngel.a = 1;
                        if (this.archAngel.p > 0) {
                            this.archAngel.game_state = this.archAngel.p;
                        }
                    }
                    this.archAngel.bool_q = true;
                }
                if (this.bool_v == true) {
                    if (paramInt == -6)
                    {
                        this.archAngel.a = 1;
                        if (this.archAngel.p > 0) {
                            this.archAngel.game_state = this.archAngel.p;
                        }
                    }
                    else if ((this.archAngel.game_state != 111) && (this.archAngel.game_state != 211) && (this.archAngel.game_state != 311))
                    {
                        this.secondHelper.read_text_helper(paramInt, i1, this.archAngel);
                    }
                }
                break;
            case 7:
                if ((paramInt == -1) || (paramInt == -2)) {
                    this.archAngel.c = (1 - this.archAngel.c);
                }
                if ((paramInt == -7) || (paramInt == -5)) {
                    if (this.archAngel.c == 0) {
                        this.archAngel.game_state = 2;
                    } else {
                        this.archAngel.game_state = 3;
                    }
                }
                break;
            case 4:
                if ((this.archAngel.game_state == 1) && (paramInt == -6)) {
                    this.archAngel.screen = 5;
                }
                break;
            case 1:
            case 10:
                if (this.archAngel.game_state == 1) {
                    this.archAngel.readText.bool_e = false;
                }
                if (paramInt == -6)
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
                    }
                }
                else if (this.archAngel.bool_v) {
                    this.secondHelper.read_text_helper(paramInt, i1, this.archAngel);
                }
                this.archAngel.bool_w = true;
                break;
            case 13:
                if ((paramInt != -6) && (this.archAngel.bool_v))
                {
                    this.secondHelper.read_text_helper(paramInt, i1, this.archAngel);
                    if ((this.archAngel.mainGameScreen.bool_az == true) && (this.q == 1) && (this.archAngel.readText.a == 1) && ((paramInt == -7) || (paramInt == -5) || (paramInt == 49))) {
                        this.archAngel.playSound("s_plasma", 0);
                    }
                    if ((this.archAngel.readText.a == 1) && ((paramInt == -7) || (paramInt == -5) || (paramInt == 49)))
                    {
                        this.archAngel.mainGameScreen.setup3();
                        this.archAngel.mainGameScreen.hecman_step = 0;
                        this.archAngel.mainGameScreen.hecman_y_step = 0;
                        this.archAngel.mainGameScreen.bd = 0;
                    }
                }
                break;
            case 6:
                if ((paramInt == -7) && ((this.archAngel.game_state == 2) || (this.archAngel.game_state == 4))) {
                    this.archAngel.game_state += 1;
                }
                break;
            case 14:
                if ((paramInt == -7) || (paramInt == -5) || (paramInt == 35))
                {
                    this.archAngel.game_state += 1;
                    if (this.archAngel.mainGameScreen.bool_az == true) {
                        this.archAngel.playSound("s_plasma", 0);
                    }
                    this.archAngel.mainGameScreen.setup3();
                    this.archAngel.mainGameScreen.hecman_step = 0;
                    this.archAngel.mainGameScreen.hecman_y_step = 0;
                    this.archAngel.mainGameScreen.bd = 0;
                }
                break;
            case 27:
                if ((this.archAngel.game_state > 1) && (this.archAngel.game_state < 15)) {
                    switch (paramInt)
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
        if (this.archAngel.x > 0) {
            return;
        }
        switch (this.archAngel.game_state)
        {
            case 0:
                this.archAngel.stopSound();
                // paramGraphics.setColor(0);
                fillRect(paramGraphics, 0, 150, 240, 15, 4);
                this.archAngel.readMedia.drawGraphicStr40_122(paramGraphics, 98, 152, "PAUSE");
                this.archAngel.drawImage(paramGraphics);
                this.archAngel.a(paramGraphics, "RESUME", false);
                break;
            case 1:
                this.archAngel.y = (this.archAngel.screen = 25);
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

        Gdx.input.setCatchBackKey( true );
        Gdx.input.setInputProcessor(this);

        // Calculate global var width/height, view port ...
        create();

        touchPoint = new Vector3();
        // game_speed = getGameSpeed();
        // init_game(stage);
    }

    public void void_empty() {}

    public void keyReleased(int paramInt)
    {
        // this.key_code = 0;
        touchStatus = TouchStatus.NONE; // debug
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
                    this.archAngel.mainGameScreen.setup3();
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
        this.archAngel.x = -1;
        if (this.archAngel.screen == 25)
        {
            this.archAngel.mainGameScreen.setup3();
            this.archAngel.mainGameScreen.hecman_step = 0;
            this.archAngel.mainGameScreen.hecman_y_step = 0;
            this.archAngel.mainGameScreen.bd = 0;
        }
    }

    public void draw_shop_info_arm(SpriteBatch paramGraphics)
    {
        if ((this.archAngel.game_state > 0) && (this.archAngel.x > 0)) {
            return;
        }
        this.secondHelper.setup2(paramGraphics, this.archAngel.game_state, 210, this.o, this.p, this.q, this.t, this.x, this.str_arr_w, this.archAngel, this.readText, this.helper);
        switch (this.archAngel.game_state)
        {
            case 0:
                if (this.archAngel.x == 0)
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
//                    paramGraphics.setClip(17, 89, 223, 25);
                    this.archAngel.readMedia.drawImageAnchor20(paramGraphics, 13, 60, 90 - this.l * 33);
//                    paramGraphics.setClip(0, 0, 240, 320);
                }
                this.helper.simple_helper(paramGraphics, this.archAngel);
                return;
            case 1:
                this.archAngel.drawImage(paramGraphics);
                this.archAngel.a(paramGraphics, "BACK", true);
                this.archAngel.a(paramGraphics, "OK", false);
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
        if ((this.archAngel.game_state > 0) && (this.archAngel.x > 0)) {
            return;
        }
        this.bool_v = false;
        switch (this.archAngel.game_state)
        {
            case 0:
                if (this.archAngel.x == 0)
                {
                    this.archAngel.readMedia.readMediaStream("shop");
                    for (int i1 = 0; i1 < 3; i1++)
                    {
                        this.archAngel.readMedia.destroyImage(26 + i1);
                        this.archAngel.readMedia.reloadImageArr(i1, 26 + i1);
                    }
                    this.archAngel.readMedia.closeInputStream();
                    this.archAngel.readMedia.drawImageAnchor20(paramGraphics, 14, 17, 89);
//                    paramGraphics.setClip(17, 89, 223, 25);
                    this.archAngel.readMedia.drawImageAnchor20(paramGraphics, 13, 60, 90 - this.l * 33);
//                    paramGraphics.setClip(0, 0, 240, 320);
                    this.archAngel.drawImage(paramGraphics);
                    this.archAngel.a(paramGraphics, "BACK", true);
                    this.archAngel.a(paramGraphics, "OK", false);
                    this.archAngel.readText.readTextFromStream("shop");
                    this.readText = new ReadText();
                    this.archAngel.readText.g = 1;
                }
                this.helper.simple_helper(paramGraphics, this.archAngel);
                return;
            case 2:
                this.archAngel.drawImage(paramGraphics);
                this.archAngel.a(paramGraphics, "BACK", true);
                this.archAngel.a(paramGraphics, "OK", false);
                this.archAngel.game_state = 1;
                break;
            case 1:
                this.archAngel.drawImage(paramGraphics);
                this.archAngel.a(paramGraphics, "BACK", true);
                this.archAngel.a(paramGraphics, "OK", false);
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
                    this.archAngel.a(paramGraphics, "BACK", true);
                    this.archAngel.a(paramGraphics, "OK", false);
//                    paramGraphics.setClip(0, 0, 178, 75);
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
            if (this.archAngel.x == 0) {
                this.archAngel.bool_q = true;
            }
            this.archAngel.readText.bool_c = false;
            this.helper.b(paramGraphics, this.archAngel.game_state, this.x, this.t, this.str_arr_w, this.archAngel, this.readText);
            draw_data_helper(paramGraphics, this.archAngel.game_state, 190);
            this.archAngel.drawImage(paramGraphics);
            this.archAngel.a(paramGraphics, "BACK", true);
            this.archAngel.a(paramGraphics, "OK", false);
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
            this.archAngel.mainGameScreen.config2();
        }

        if (this.gameOff) { // like GameOff, true then stop paint
            // Paint done ? can it just remove return to debug
            return;
        }
        this.gameOff = true;
        this.archAngel.x += 1;
        if (this.archAngel.screen != this.archAngel.y)
        {
            this.archAngel.x = 0;
            this.archAngel.game_state = 0;
            this.archAngel.aa = 0;
            this.archAngel.p = -1;
            this.bool_b = true;
            this.archAngel.y = this.archAngel.screen;
            this.archAngel.bool_i = false;
        }
        else if (this.archAngel.game_state != this.archAngel.aa)
        {
            this.archAngel.x = 0;
            this.archAngel.aa = this.archAngel.game_state;
        }

        switch (this.archAngel.screen)
        {
            case 25:
                ReturnHelper returnHelper2 = this.secondHelper.draw_game_play_screen(batch, this.archAngel);
                if(returnHelper2.one > returnHelper2.MIN_INT) { // If this.l value has changed
                    this.l = returnHelper2.one;
                }
                if(returnHelper2.bool_one >= 0) {
                    this.bool_b = (returnHelper2.bool_one != 0) ? true : false;
                }
                if(returnHelper2.yi > returnHelper2.MIN_INT) {
                    this.aa = returnHelper2.yi;
                }
                if(returnHelper2.er > returnHelper2.MIN_INT) {
                    this.ab = returnHelper2.er;
                }
                if(returnHelper2.san > returnHelper2.MIN_INT) {
                    this.ac = returnHelper2.san;
                }
                if(returnHelper2.si > returnHelper2.MIN_INT) {
                    this.ad = returnHelper2.si;
                }
                if(returnHelper2.wu > returnHelper2.MIN_INT) {
                    this.ae = returnHelper2.wu;
                }
                if(returnHelper2.liu > returnHelper2.MIN_INT) {
                    this.af = returnHelper2.liu;
                }
                if(returnHelper2.qi > returnHelper2.MIN_INT) {
                    this.ag = returnHelper2.qi;
                }
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
                draw_resume(batch);
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
                draw_shop_info_arm(batch);
                break;
            case 12:
                draw_shop_arm2(batch);
                break;
            case 4:
                this.helper.briefAbout(batch, this.o, this.p, this.l, this.archAngel);
                break;
            case 26:
                this.helper.briefOpen(batch, this.o, this.p, this.archAngel);
                break;
            case 2:
                simple_read_helper(batch);
                break;
            case 7:
                this.helper.displayGameOver(batch, this.archAngel);
                break;
            case 6:
                this.secondHelper.draw_font_result(batch, this.archAngel, this.helper);
                break;
            case 27:
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
        batch.begin();
        // TODO use boundingBox and touchAreas
        // TODO pass touchPoint instead of global
        // BoundingBox can be use Rectangle as alternative ?
        // convert touch event to key event (getGameAction)
        // TODO study libgdx-mario-bros key handle mechanism
        touchPoint.set(Gdx.input.getX(),Gdx.input.getY(), 0);

        // Gdx.app.log("DEBUG", "touch " + touchPoint.x + " destroy_n_e "+ (SCREEN_HEIGHT-touchPoint.destroy_n_e) + " key_code "+ this.key_code);
        //if(touchStatus == TouchStatus.NONE) {
        touchStatus = TouchStatus.TOUCH_DOWN;
        //game_action = getGameAction(); // seem take no effect

        if (isTouchedMenuLeft()) { // smaller value, shorter sleep
            this.key_code = KEY_LEFT_MENU;
            Gdx.input.vibrate(5);
        } else if (isTouchedMenuRight()) {
            this.key_code = KEY_RIGHT_MENU;
            Gdx.input.vibrate(5);
        } else if (isTouchedOK()) {
            this.key_code = KEY_OK;
        } else if (isTouchedUp()) {
            Gdx.input.vibrate(5);
            this.key_code = -1;
        } else if (isTouchedDown()) {
            Gdx.input.vibrate(5);
            this.key_code = -2;
        } else if (isTouchedLeft()) {
            Gdx.input.vibrate(5);
            this.key_code = -3;
        } else if (isTouchedRight()) {
            Gdx.input.vibrate(5);
            this.key_code = -4;
        } else if (isTouchedNum3()) {
            this.key_code = 57;
        } else {
             this.key_code = 0;
        }

        // Use rectangle instead of collisionRay. TODO fix collisionRay null and multiplex many Gdx.input
        // TODO May be use OverlapTester Class for these task
        keyPressed();
        batch.end();

        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        // TODO handle key released, touch and hold

        //if(touchStatus == TouchStatus.TOUCH_DOWN) {
        touchStatus = TouchStatus.TOUCH_UP;
        if(isTouchedMenuLeft() || isTouchedMenuRight() || isTouchedOK() || isTouchedUp() || isTouchedDown() || isTouchedLeft() || isTouchedRight()) {
            // seem only effect key-code can overide keep turning effect
            // this.key_code = KEY_OK; // debug KEY_OK -> turn ok but dupli fire; 51 ok but keep turn right; 35 no effect.
            this.game_action = 0;
            keyPressed();
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
    public int getGameAction(int keyCode) {
        return game_action;
    }

    public int getGameAction() {
        // TODO handle key_code and key released, multi touch
        if(OverlapTester.pointInRectangle(upBtnRect, touchPoint.x, (SCREEN_HEIGHT-touchPoint.y) )) {
            this.key_code = -1;
            return GAME_ACTION_UP;
        }
        if(OverlapTester.pointInRectangle(downBtnRect, touchPoint.x, (SCREEN_HEIGHT-touchPoint.y) )) {
            if(item_mode == 0) { // FIXME remove this, it only in SBF
            }
            this.key_code = -2;
            return GAME_ACTION_DOWN;
        }
        if(OverlapTester.pointInRectangle(leftBtnRect, touchPoint.x, (SCREEN_HEIGHT-touchPoint.y) )) {
            Gdx.input.vibrate(5);
            return GAME_ACTION_LEFT;
        }
        if(OverlapTester.pointInRectangle(rightBtnRect, touchPoint.x, (SCREEN_HEIGHT-touchPoint.y) )) {
            Gdx.input.vibrate(5);
            return GAME_ACTION_RIGHT;
        }
        if(OverlapTester.pointInRectangle(optionBtnRect, touchPoint.x, (SCREEN_HEIGHT-touchPoint.y) )) {
            return KEY_RIGHT_MENU; // FIXME remove
        }
        if(OverlapTester.pointInRectangle(leftMenuBtn, touchPoint.x, (SCREEN_HEIGHT-touchPoint.y) )) {
            return KEY_LEFT_MENU;
        }
        if(OverlapTester.pointInRectangle(rightMenuBtn, touchPoint.x, (SCREEN_HEIGHT-touchPoint.y) )) {
            return KEY_RIGHT_MENU;
        }

        return 0;
    }

    public void fillRect(SpriteBatch paramGraphics, int x, int y, int width, int height, int color) {
        // Hard code default width x height of color img: 12x12 px
        int scaleY = height / 12;
        int scaleX = width / 12;
        // (Texture, float x, float destroy_n_e, float originX, float originY, float width, float height, float scaleX, float scaleY, float rotation, int srcX, int srcY, int srcWidth, int srcHeight, boolean flipX, boolean flipY)
        paramGraphics.draw(imgColor[color], x, y, 0, 0, imgColor[color].getWidth(), imgColor[color].getHeight(), scaleX, scaleY, 0, 0, 0, imgColor[color].getWidth()*scaleX, imgColor[color].getHeight()*scaleY, false, false);
    }

    protected void drawUI() {
        batch.draw(fireBtnTexture, SCREEN_WIDTH-50-fireBtnTexture.getWidth(), 50, fireBtnTexture.getWidth(), fireBtnTexture.getHeight());
        batch.draw(imgKeyNum3, SCREEN_WIDTH-50-fireBtnTexture.getWidth()-fireBtnTexture.getWidth()/2 - imgKeyNum3.getWidth(), BOTTOM_SPACE-imgKeyNum3.getHeight(), imgKeyNum3.getWidth(), imgKeyNum3.getHeight());
        batch.draw(imgSpeedUp, SCREEN_WIDTH-50-fireBtnTexture.getWidth()-fireBtnTexture.getWidth()/2 - imgSpeedUp.getWidth(), 20, imgSpeedUp.getWidth(), imgSpeedUp.getHeight());
        batch.draw(imgSpeedDown, SCREEN_WIDTH-50-fireBtnTexture.getWidth()-fireBtnTexture.getWidth()/2 - 2*imgSpeedDown.getWidth(), 20, imgSpeedDown.getWidth(), imgSpeedDown.getHeight());
        batch.draw(touch_pad, 20, 20);
        batch.draw(touch_pad_knob, 20+touch_pad.getWidth()/2-touch_pad_knob.getWidth()/2, 20+touch_pad.getHeight()/2-touch_pad_knob.getHeight()/2);

//        fillRect(40, BOTTOM_SPACE+ui.getHeight()-(12 - 12 * hero.hp / hero.max_hp)*SGH_SCALE_RATIO-24, 81+5, (12 - 12 * hero.hp / hero.max_hp)*SGH_SCALE_RATIO, 4);
    }

    public void create () {
        batch = new SpriteBatch();

        //Create camera
        float aspectRatio = (float) SCREEN_WIDTH / (float) SCREEN_HEIGHT;
        camera = new OrthographicCamera();
        camera.position.x = SCREEN_WIDTH/2;
        camera.position.y = SCREEN_HEIGHT/2;
        camera.update();

        Gdx.input.setInputProcessor(this); // TODO use an InputProcessor object

        loadTextures();
        this.archAngel.startApp();

        font = new BitmapFont();
        font.getRegion().getTexture().setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        font.getData().setScale(6);
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
        Rectangle textureBounds = new Rectangle(SCREEN_WIDTH-fireBtnTexture.getWidth()-50, SCREEN_HEIGHT-50-fireBtnTexture.getHeight(), fireBtnTexture.getWidth(),fireBtnTexture.getHeight());
        return textureBounds.contains(touchPoint.x, touchPoint.y);
    }
    protected boolean isTouchedNum3() {
        Rectangle textureBounds=new Rectangle(SCREEN_WIDTH-fireBtnTexture.getWidth()-50-imgKeyNum3.getWidth()-(int)fireBtnTexture.getWidth()/2, SCREEN_HEIGHT-BOTTOM_SPACE, imgKeyNum3.getWidth(),imgKeyNum3.getHeight());
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
