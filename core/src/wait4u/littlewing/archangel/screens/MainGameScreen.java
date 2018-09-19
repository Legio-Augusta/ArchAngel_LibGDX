package wait4u.littlewing.archangel.screens;
import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import wait4u.littlewing.archangel.ArchAngelME;
import wait4u.littlewing.archangel.Enemy;
import wait4u.littlewing.archangel.GameSettings;
import wait4u.littlewing.archangel.MainGameHelper;
import wait4u.littlewing.archangel.ReadMedia;
import wait4u.littlewing.archangel.ReturnHelper;

public class MainGameScreen {
    public ArchAngelME AA;
    public ReadMedia readMedia;
    public GameSettings gameSetting;
    public Random rnd = new Random();
    public String str_e;
    public int f; // Fighter HP related, for example Fighter lose 50 HP each time collidate cliff
    public int g;
    public int h;
    public int i;
    public int j;
    public int k;
    public int l = 0;
    public String enemy_fighter; // Enermy left
    public int n;
    public int o;
    public int p = 0;
    public String str_q;
    public int boss_distance_r;
    public int boss_distance_s;
    public int t;
    public int downed_e_count; // Number of enemy fighter destroyed in draw_string_y305 mission.
    public int v;
    public int w;
    public int x;
    public int destroy_n_e; // Number of enemy have to destroy before reach boss
    public int fighter_turn; // ~ fighter turn
    public int mission_stage;
    public final byte byte_ac = 1;
    public final byte byte_ad = 2;
    public static Enemy[] enemyArr = new Enemy[18];
    public int af = 0;
    public int ag = 0;
    public int ah = 0;
    public int ai;  // aim related
    public int aj = -1;
    public int ak = 0;
    public int al = 0; // boss related
    public int am = 0; // boss dist related
    public int an = 0;
    public int ao = 0;
    public int ap = 0; // Related to mission stage (ie. hecman transformer)
    public int aq = 0;
    public int ar = 0; // related to fighter speed
    public int as = 0; // aim target related
    public int at;
    public int au = 0;
    public int av = 90; // ie. 146; related to boss finder
    public int gamespeed = 20;
    public int ax = 0;
    public boolean bool_ay = false;
    public boolean bool_az = false;
    public boolean bool_a0 = false;
    public boolean bool_a1 = false;
    public boolean bool_a2 = false;
    public int a3 = 0;
    public int a4 = 0;
    public int[] int_arr_a5 = new int[5];
    public int[] int_arr_a6 = { 187, 232, 304 }; // Positioning y of bg
    public boolean[] bool_arr_a7 = new boolean[4]; // Overlap or serie of bg in draw_string_y305 line position x
    public int a8;
    public int hecman_y_step; // Related to fighter game speed
    public int hecman_step;
    public int fighter_x = 90; // Fighter init position x 240px width; aa width range from 35, 47 to ab 61px; turn sprite is more slim
    public int fighter_y = 169; // Game speed or fighter y-position (bc)
    public int bd;
    public int be;
    public int bf;
    public boolean bool_bg; //
    public boolean bool_bh;
    public int gamestage1; // bi
    public int bj; // aim target
    public int bk; // aim target
    public boolean bool_bl = false;
    public int bm = -1;
    public int bn = 0;
    public int bo = 0;
    public int bp = 64;
    public int bq = 0; // boss finder
    public int br = 0; // boss finder
    // These 2 arrays bellow seem to be used as AI pattern for enemies fly path.
    public static byte[] stt_byte_arr_bs = { 64, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 62, 62, 62, 62, 61, 61, 61, 60, 60, 60, 59, 59, 58, 58, 58, 57, 57, 56, 55, 55, 54, 54, 53, 53, 52, 51, 51, 50, 49, 49, 48, 47, 46, 46, 45, 44, 43, 42, 41, 41, 40, 39, 38, 37, 36, 35, 34, 33, 32, 31, 31, 30, 29, 28, 27, 26, 25, 23, 22, 21, 20, 19, 18, 17, 16, 15, 14, 13, 12, 11, 10, 8, 7, 6, 5, 4, 3, 2, 1 };
    public static byte[] stt_byte_arr_bt = { 0, 1, 2, 3, 4, 5, 6, 7, 8, 10, 11, 12, 13, 14, 15, 17, 18, 19, 20, 22, 23, 24, 25, 27, 28, 29, 31, 32, 34, 35, 36, 38, 39, 41, 43, 44, 46, 48, 50, 51, 53, 55, 57, 59, 61, 64 };
    public int[][] int_arr_bu = new int[3][2];
    public int bv; // bg related
    public int bw; // aim related
    public int bx;
    public int by;
    public int bz;
    public int b0;
    public int b1;
    public int b2;
    public int b3;
    public int b4;
    public int b5;
    public int b6;
    public int b7;
    public int b8;
    public int other_fighter_y;
    public int boss_distance; // Boss distance
    public int cb = 0;
    public int cc = 0;

    public MainGameHelper gameHelper = new MainGameHelper(); // new MainGameHelper(this.readMedia)

    public void init_game(Enemy paramGameCnf, int paramInt, Enemy[] enemyArr)
    {
        int i2;
        if ((i2 = paramGameCnf.c) == 0) {
            return;
        }
        paramGameCnf.l += -1;
        if ((i2 <= 6) && (paramGameCnf.l <= 0))
        {
            paramGameCnf.c = 0;
            return;
        }
        paramGameCnf.enemy_distance_1 += paramGameCnf.h;
        paramGameCnf.enemy_distance_2 += paramGameCnf.i;
        int i3;
        int i1;
        switch (i2)
        {
            case 9:
                if (this.mission_stage == 1)
                {
                    if (this.bool_a1 == true)
                    {
                        paramGameCnf.enemy_distance_1 = this.boss_distance_r;
                        paramGameCnf.enemy_distance_2 = this.boss_distance_s;
                        paramGameCnf.e = 99999;
                        paramGameCnf.h = 0;
                        paramGameCnf.i = 0;
                        this.bool_a1 = false;
                    }
                    // this.f = turn_calc(angle_helper(paramGameCnf.enemy_distance_1, paramGameCnf.screen), this.av);
                    // turn speed
                    this.f = this.gameHelper.turn_calc(this.gameHelper.angle_helper(paramGameCnf.enemy_distance_1, paramGameCnf.enemy_distance_2, stt_byte_arr_bt), this.av);
                    // Magnificent
                    // distance_2 = -2122697960; distance_1 = -1137360710;
                    // 2122697960 + 1137360710 = -1034908626 (out of range treated like this)
                    // MIN_INT = -2147483648

                    this.boss_distance = (Math.abs(paramGameCnf.enemy_distance_1) + Math.abs(paramGameCnf.enemy_distance_2) - 200);
                }

                if ((this.boss_distance <= 0) && (this.mission_stage == 1))
                {
                    this.boss_distance = 0;
                    paramGameCnf.c = 0;
                    this.readMedia.readMediaStream("etc");
                    this.readMedia.reloadImageArr(1, 112);
                    this.readMedia.closeInputStream();
                    this.readMedia.readMediaStream("aa");
                    // Hecman transform from fighter
                    for (i3 = 11; i3 < 19; i3++) {
                        this.readMedia.reloadImageArr(i3, 24 + i3);
                    }
                    this.readMedia.closeInputStream();
                    this.gamestage1 = 2;
                    this.mission_stage = 2;
                    this.ap = 0;
                }
                break;
            case 10:
                if (paramGameCnf.d == 8)
                {
                    paramGameCnf.c = 0;
                    this.ah += -1;
                    return;
                }
                if ((paramGameCnf.d >= 7) && (paramGameCnf.f > -20) && (paramGameCnf.f < 20))
                {
                    this.au += 2;
                    paramGameCnf.c = 0;
                    this.ah += -1;
                    // May be boss hp, fighter seme have inital only 78hp
                    this.gameSetting.fighter_hp -= 50; // ammunition reduce ? 1000 round available; or Fighter hp
                    play_s_gun(false);
                    if (this.gameSetting.fighter_hp <= 0) {
                        setup2();
                    }
                }
                if (this.gamestage1 == 2)
                {
                    paramGameCnf.c = 0;
                    this.ah = 2;
                }
                break;
            case 14:
                if (paramGameCnf.l <= 0) {
                    paramGameCnf.l = complex_helper2(paramGameCnf, paramInt);
                }
                break;
            case 13:
                if (paramGameCnf.l <= 0) {
                    // paramGameCnf.l = e_turn_ai2(paramGameCnf, paramInt);
                    // ReturnHelper randomReturn = this.gameHelper
                    e_turn_ai2(paramGameCnf, paramInt);
                    // paramGameCnf.l = (randomReturn.five > randomReturn.MIN_INT) ? randomReturn.five : paramGameCnf.l;
                    // this.bo = (randomReturn.one > randomReturn.MIN_INT) ? randomReturn.one : this.bo;
                    // this.bp = (randomReturn.two > randomReturn.MIN_INT) ? randomReturn.two : this.bp;
                    // this.bq = (randomReturn.three > randomReturn.MIN_INT) ? randomReturn.three : this.bq;
                    // this.br = (randomReturn.four > randomReturn.MIN_INT) ? randomReturn.four : this.br;
                }
                if (this.gamestage1 == 2)
                {
                    this.ag = 2;
                    paramGameCnf.c = 0;
                }
                break;
            case 12:
                if (paramGameCnf.l <= 0)
                {
                    paramGameCnf.c = 0;
                    this.af += -1;
                    return;
                }
                if ((this.gamestage1 == 3) && (this.AA.bool_n == true))
                {
                    if (paramGameCnf.bool_n == true)
                    {
                        paramGameCnf.bool_n = false;
                        paramGameCnf.c = 0;
                        this.af += -1;
                        this.au += 3;
                        this.gameSetting.loseHP(paramGameCnf.m); // This seem to be reduce ammo round instead of hp
                        if (this.gameSetting.fighter_hp <= 0) {
                            setup2();
                        }
                    }
                }
                else if (paramGameCnf.e < 150)
                {
                    paramGameCnf.c = 0;
                    this.af += -1;
                    this.au += 3;
                    this.gameSetting.loseHP(paramGameCnf.m);
                    play_s_gun(false);
                    if (this.gameSetting.fighter_hp <= 0) { // ammo round reduce ?
                        setup2();
                    }
                }
                break;
            case 11:
                if (paramGameCnf.l <= 0)
                {
                    paramGameCnf.c = 0;
                    return;
                }
                if ((i1 = paramGameCnf.k) == -1) {
                    return;
                }
                if (enemyArr[i1].c == 0)
                {
                    paramGameCnf.c = 0;
                    return;
                }
                // draw_string_y305 = 0011 1100
                // b = 0000 1101
                // draw_string_y305 & b = 0000 1100 (12 Dec)
                if (((paramGameCnf.l & 0x1) == 0) && (Math.abs(paramGameCnf.enemy_distance_1 - enemyArr[i1].enemy_distance_1) + Math.abs(paramGameCnf.enemy_distance_2 - enemyArr[i1].enemy_distance_2) < 300))
                {
                    paramGameCnf.c = 4;
                    paramGameCnf.l = 2;
                    enemyArr[i1].m -= this.gameSetting.s; // decs_e_hp ? similar SBF decrease hp
                    if(this.n != 0) {
                        this.AA.ae = (40 * enemyArr[i1].m / this.n);
                    }
                    if (enemyArr[i1].m <= 0)
                    {
                        enemyArr[i1].c = 7;
                        enemyArr[i1].l = 4;
                    }
                }
                break;
            case 1:
                if (paramGameCnf.l <= 0)
                {
                    paramGameCnf.c = 0;
                    return;
                }
                if ((i1 = paramGameCnf.k) == -1) {
                    return;
                }
                if (enemyArr[i1].c == 0)
                {
                    paramGameCnf.c = 0;
                    return;
                }
                if (this.gameSetting.o > 0) {
                    config2(paramGameCnf, enemyArr[i1].enemy_distance_1, enemyArr[i1].enemy_distance_2);
                }
                if (Math.abs(paramGameCnf.enemy_distance_1 - enemyArr[i1].enemy_distance_1) + Math.abs(paramGameCnf.enemy_distance_2 - enemyArr[i1].enemy_distance_2) < 300)
                {
                    paramGameCnf.c = 4;
                    paramGameCnf.l = 2;
                    enemyArr[i1].m -= this.gameSetting.n;
                    if (enemyArr[i1].m <= 0)
                    {
                        enemyArr[i1].c = 8;
                        enemyArr[i1].l = 4;
                    }
                }
                break;
            case 6:
                for (i3 = 0; i3 < 18; i3++) {
                    if ((enemyArr[i3].c >= 13) && (this.gameHelper.config_helper(paramGameCnf, enemyArr[i3])))
                    {
                        paramGameCnf.c = 5;
                        paramGameCnf.l = 2;
                        return;
                    }
                }
                break;
            case 8:
                if (paramGameCnf.l <= 0)
                {
                    this.ag += -1;
                    paramGameCnf.c = 0;
                    this.downed_e_count += 1;
                }
                break;
            case 7:
                if (paramGameCnf.l <= 0)
                {
                    this.ag += -1;
                    paramGameCnf.c = 0;
                    this.t += 1;
                    this.AA.ab = 6;
                    this.g = (this.t * this.i + this.downed_e_count * this.h);
                }
                break;
        }
    }

    public void left_right(boolean paramBoolean)
    {
        if (!this.bool_bg) {
            this.aq = (paramBoolean ? -4 : 4);
        }
    }

    public void init_game2()
    {
        this.fighter_x = 90;
        this.fighter_y = 169;
        this.int_arr_a5[1] = -82;
        this.AA.ae = 40;
        this.f = (this.ax = this.al = this.am = this.as = this.aq = this.ar = this.an = this.ao = 0);
        this.gamespeed = 20;
        this.av = 90;
        this.ap = 0;
        this.au = 0;
        this.hecman_step = 0;
        this.a8 = 0;
        this.bd = 0;
        this.mission_stage = 0;
        this.bool_az = (this.bool_ay = this.bool_a0 = this.bool_a2 = false);
        //this.az = (this.ay = this.a0 = this.a2 = 0);
        this.a3 = 20;
        this.a4 = 0;
        this.t = 0;
        this.downed_e_count = 0;
        this.v = (this.w = 0);
        this.at = 262143;
        this.af = (this.ag = this.ah = 0);
        this.AA.ab = 25;
        this.bool_bl = false;
        this.bm = -1;
        this.bn = 0;
        for (int i1 = 0; i1 < 18; i1++) {
            enemyArr[i1].c = 0;
        }
        this.gameHelper.init_600(this.int_arr_bu);
        this.gamestage1 = 1;
        this.bool_bg = false;
        this.bool_bh = false;
        updateEnemyArr(9, 0);
    }

    public void main_paint(SpriteBatch paramGraphics)
    {
        this.AA.bool_l = true; // gameStart, gameOn ?
        draw_background(paramGraphics);
        draw_lower_bg(paramGraphics);
        clip_color_arr_helper(paramGraphics);
        draw_aim_lock(paramGraphics);
        sound_explode(paramGraphics);
    }

    // Draw enemy target lock and aim
    public void draw_aim_lock(SpriteBatch paramGraphics)
    {
        int i6 = 0;
        // setColor(65280);
        this.ai = (85 + this.as * 7);
        if ((this.aj >= 0) && (enemyArr[this.aj].c == 0)) {
            this.aj = -1;
        }
        int i1;
        int i2;
        for (int i7 = 0; i7 < 18; i7++)
        {
            int i4 = enemyArr[i7].c;
            int i5 = enemyArr[i7].d;
            if ((i4 >= 13) && (i5 >= 1) && (i5 < 6))
            {
                i1 = enemyArr[i7].f + 88 + 32;
                i2 = 158 - enemyArr[i7].g;
                this.bw = i1;
                this.bx = i2;
                if ((i1 > this.ai) && (i1 < this.ai + 88) && (i2 > 87) && (i2 < 188)) {
                    if (this.aj == -1)
                    {
                        i6 = 1;
                        this.aj = i7;
                        this.ak = 5;
                    }
                    else if (this.aj == i7)
                    {
                        i6 = 1;
                        if (this.ak > 0) {
                            this.ak += -1;
                        }
                    }
                }
            }
        }
        if (i6 != 0)
        {
            if (this.aj >= 0)
            {
                i1 = enemyArr[this.aj].f + 88 + 32; // enemy position x
                i2 = 155 - enemyArr[this.aj].g;     // enemy position y
                int i3 = enemyArr[this.aj].d * 8 + 4;
                if (this.gamestage1 == 1) {
                    if (enemyArr[this.aj].d < 4) {
                        // nickfarrow +3
                        this.readMedia.drawImageAnchor20(paramGraphics, 82, i1 - 10 +3, i2 - 6 + 14); // nickfarrow +14
                    } else {
                        this.readMedia.drawImageAnchor20(paramGraphics, 81, i1 - 17 +3, i2 - 12 + 14); // nickfarrow +14
                    }
                }
            }
        }
        else // fix me image idx 81 82 83 seem missing, debug JME to find it
        {
            this.aj = -1;
            if (this.gamestage1 == 1) { // effect 12 (aim target)
                this.readMedia.drawImageAnchor20(paramGraphics, 83, this.ai+5, 136); // nickfarrow+15
            }
        }
    }

    // Draw sky background (far and high bg)
    public void draw_background(SpriteBatch paramGraphics)
    {
        int i1 = -this.av * 4 + 360;
        int i2 = this.at >> 14; // 262143 >> 14 = 15
        if (i2 < 0) { // fix me why unused code here
            i2 = 0;
        }
        // Double Background 0, may be bug sky edge sharky here.
        // Background in same line need careful put together to make smooth bg view. May be 240px to 1080 scale calculation and rounding number caused this.
        this.readMedia.drawImageAnchor20(paramGraphics, 7, this.int_arr_a5[0], 27);
        this.readMedia.drawImageAnchor20(paramGraphics, 7, this.int_arr_a5[0] + (this.bool_arr_a7[0] != false ? -240 : 240), 27); // != 0
        // Bg 1 Table mount Hara Berezaiti ﺐﻠﻧﺩ
        this.readMedia.drawImageAnchor36(paramGraphics, 8, this.int_arr_a5[1], 166+24); // orig:166
    }

    // Draw bottom (land and near) background bellow bg 0 and bg 1; Original low bg seem have same height as high one
    public void draw_lower_bg(SpriteBatch paramGraphics)
    {
        this.bv += 1;
        int i2 = this.bv / (5 - this.a8) % 3;
        int[][] arrayOfInt = { new int[3], { 1, 2, 3 }, { 2, 3, 4 } };
        for (int i1 = 0; i1 < 3; i1++)
        {
            // Backgrounds many bg in same line (row) for turn viewport.
            // this.readMedia.drawImageAnchor36(paramGraphics, 9 + i1 * 3 + i2, this.int_arr_a5[(i1 + 2)], this.int_arr_a6[i1] - arrayOfInt[i1][i2] + 25); // nickfarrow +25
            // this.screen.screen(paramGraphics, 9 + i1 * 3 + i2, this.a5[(i1 + 2)] + (this.a7[(i1 + 1)] != 0 ? -240 : 240), this.a6[i1] - arrayOfInt[i1][i2]);
            // this.readMedia.drawImageAnchor36(paramGraphics, 9 + i1 * 3 + i2, this.int_arr_a5[(i1 + 2)] + ((this.bool_arr_a7[(i1 + 1)] != false) ? -240 : 240), this.int_arr_a6[i1] - arrayOfInt[i1][i2] + 25);
        }
        // Custom draw lower bg position. Careful with x position so many layer of bg match view.
        this.readMedia.drawImageAnchor36(paramGraphics, 9 + 0 * 3 + i2, this.int_arr_a5[(0 + 2)], this.int_arr_a6[0] - arrayOfInt[0][i2] + 25); // nickfarrow +25
        // 240 +2 in x position to smoother edge caused by rounding calculation
        this.readMedia.drawImageAnchor36(paramGraphics, 9 + 0 * 3 + i2, this.int_arr_a5[(0 + 2)] + ((this.bool_arr_a7[(0 + 1)] != false) ? -240 : 240), this.int_arr_a6[0] - arrayOfInt[0][i2] + 25);

        this.readMedia.drawImageAnchor36(paramGraphics, 9 + 1 * 3 + i2, this.int_arr_a5[(1 + 2)], this.int_arr_a6[1] - arrayOfInt[1][i2] + 5);
        this.readMedia.drawImageAnchor36(paramGraphics, 9 + 1 * 3 + i2, this.int_arr_a5[(1 + 2)] + ((this.bool_arr_a7[(1 + 1)] != false) ? -240 : 240), this.int_arr_a6[1] - arrayOfInt[1][i2] + 5);

        this.readMedia.drawImageAnchor36(paramGraphics, 9 + 2 * 3 + i2, this.int_arr_a5[(2 + 2)], this.int_arr_a6[2] - arrayOfInt[2][i2] - 5); // nickfarrow +30
        this.readMedia.drawImageAnchor36(paramGraphics, 9 + 2 * 3 + i2, this.int_arr_a5[(2 + 2)] + ((this.bool_arr_a7[(2 + 1)] != false) ? -240 : 240), this.int_arr_a6[2] - arrayOfInt[2][i2] - 5);

        if ((this.AA.ad == 2) || (this.AA.screen == 0)) {
            return;
        }
        // Briefs; screen = 0 seem to be force show brief on first mission
        this.readMedia.drawImageAnchor20(paramGraphics, 18 + i2, 0, 166);
    }

    public void draw_archangel_and_related(SpriteBatch paramGraphics)
    {
        byte[][] arrayOfByte1 = { { 5, -8 }, { 2, -18 }, { 6, -22 }, { 3, -25 } };
        // Used in draw fuel burn thrust position
        byte[][] arrayOfByte2 = { new byte[2], { 1, 6 }, { -2, -10 }, { 4, 8 }, { 1, -12 }, { -1, -4 }, { 0, 4 }, { 0, -6 }, { 1, 5 } };
        int i1 = this.ap / 3;
        int i2 = this.ap / 4 % 8;
        if (this.hecman_step < 3)
        {
            if (this.bd < 5) {
                this.fighter_turn = this.hecman_step; // calc turn sprite change affect position of fighter TOP-LEFT of fighter sprite
            } else {
                this.fighter_turn = (this.hecman_step + 2);
            }
        }
        else if (this.bd < 5) {
            this.fighter_turn = (this.hecman_step + 2);
        } else {
            this.fighter_turn = (this.hecman_step + 4);
        }
        switch (this.gamestage1) // TODO this seem game state ?
        {
            case 1: // playing
                // this.z ~ turn sprite position shift
                // TODO fix smooth turn
                this.readMedia.drawImageInArr(paramGraphics, 24 + this.fighter_turn, this.fighter_x +10, this.fighter_y + 11); // x + 30
                if ((this.bool_ay == true) && (this.ap % 2 == 0)) {
                    this.readMedia.drawImageInArr(paramGraphics, 80, 120, 165);
                }
                if (this.AA.game_state != 3)
                {
                    // Note This set clip have both x, y posision dynamic, so it very fit with Fighter fuel burn thrust posision.
                    // Only few setClip region in source code have DYNAMIC position.
                    // Original [12x22] aa_09/10 and [22x28]px aa_17 ; media idx 24->42
                    // paramGraphics.setClip(this.fighter_x + 10 + arrayOfByte2[this.game_state][0], this.bc + 11 + arrayOfByte2[this.game_state][1], 12, 11);

                    // 33 ~ 24 + 9 ~ Fighter thrust normal mode
                    // this.readMedia.drawImageAnchor20(paramGraphics, 33, this.fighter_x + 10 + arrayOfByte2[this.z][0], this.fighter_y + 11 + arrayOfByte2[this.z][1] - 11 * (this.ap % 2));
                    this.readMedia.myDrawClipRegion(paramGraphics, 0, this.fighter_x + 10 + arrayOfByte2[this.fighter_turn][0]+13, this.fighter_y + 11 + arrayOfByte2[this.fighter_turn][1] - 11 * (this.ap % 2) +20); // nickfarrow x+15; y+42

                    //paramGraphics.setClip(this.fighter_x + 38 - arrayOfByte2[this.game_state][0], this.bc + 11 - arrayOfByte2[this.game_state][1], 12, 11);
                    // this.readMedia.drawImageAnchor20(paramGraphics, 34, this.fighter_x + 38 - arrayOfByte2[this.z][0], this.fighter_y + 11 - arrayOfByte2[this.z][1] - 11 * (this.ap % 2));
                    this.readMedia.myDrawClipRegion(paramGraphics, 2, this.fighter_x + 38 - arrayOfByte2[this.fighter_turn][0]+13, this.fighter_y + 11 - arrayOfByte2[this.fighter_turn][1] - 11 * (this.ap % 2) +20);
                    // paramGraphics.setClip(0, 0, 240, 300);
                }
                this.AA.bool_m = true;
                break;
            case 2: // this seem starting engine scene
                this.AA.stopSound();
                //paramGraphics.setClip(0, 0, 240, 300);
                if (i1 / 2 % 2 == 0) {
                    this.readMedia.drawImageAnchor20(paramGraphics, 112, 66, 103);
                }
                this.gamespeed = 10;
                this.a8 = 0;
                this.bool_ay = false;
                this.bool_bg = true;
                if (i1 < 5) {
                    if (i1 == 0) {
                        // Fighter
                        // Need careful fix position to center and sync this position value to GameController for other anchor data.
                        this.readMedia.drawImageAnchor20(paramGraphics, 24, this.fighter_x, this.fighter_y); // fix me
                    } else {
                        // Hecman transformer
                        this.readMedia.drawImageAnchor20(paramGraphics, 24 + i1 + 10, this.fighter_x + arrayOfByte1[(i1 - 1)][0], this.fighter_y + arrayOfByte1[(i1 - 1)][1]);
                    }
                }
                if (i1 == 5)
                {
                    this.readMedia.destroyImage(112);
                    for (int i3 = 0; i3 < 11; i3++) {
                        this.readMedia.destroyImage(24 + i3);
                    }
                    for (int i3 = 0; i3 < 9; i3++)
                    {
                        this.readMedia.destroyImage(53 + i3);
                        this.readMedia.destroyImage(44 + i3);
                    }
                    for (int i3 = 10; i3 < 13; i3++) {
                        this.readMedia.destroyImage(71 + i3);
                    }
                    for (int i3 = 18; i3 < 24; i3++) {
                        this.readMedia.destroyImage(84 + i3);
                    }
                    // Normal hecman
                    this.readMedia.drawImageAnchor20(paramGraphics, 38, this.fighter_x + arrayOfByte1[3][0], this.fighter_y + arrayOfByte1[3][1]);
                    this.fighter_x = 93;
                    this.fighter_y = 144;
                    System.gc();
                    this.readMedia.readMediaStream("boss" + this.AA.boss_sprite_level);
                    for (int i3 = 0; i3 < 7; i3++) {
                        this.readMedia.reloadImageArr(i3, 62 + i3); // sprite Idx from 62 to 68
                    }
                    this.readMedia.closeInputStream();
                    this.readMedia.readMediaStream("plasma");
                    for (int i3 = 0; i3 < 18; i3++) {
                        this.readMedia.reloadImageArr(i3, 84 + i3);
                    }
                    this.readMedia.closeInputStream();
                    this.gamestage1 = 3;
                }
                break;
            case 3: // stage boss fight
                switch (this.hecman_step) // TODO boss move or what this ?
                {
                    case 1:
                        this.fighter_x -= 10;
                        if (this.fighter_x < 33) {
                            this.fighter_x = 33;
                        }
                        break;
                    case 2:
                        this.fighter_x += 10;
                        if (this.fighter_x > 153) {
                            this.fighter_x = 153;
                        }
                        break;
                    case 3:
                    case 4:
                        this.hecman_step = 0;
                        break;
                }
                switch (this.hecman_y_step)  // Hecman (fighter transformed)  move
                {
                    case 1:
                        this.fighter_y -= 10;
                        if (this.fighter_y < 104) {
                            this.fighter_y = 104;
                        }
                        break;
                    case 2:
                        this.fighter_y += 10;
                        if (this.fighter_y > 174) {
                            this.fighter_y = 174;
                        }
                        break;
                }
                // Hecman step move
                this.readMedia.drawImageAnchor20(paramGraphics, 38 + this.hecman_step, this.fighter_x, this.fighter_y); // Hecman
                if (this.hecman_step == 1)
                {
                    //paramGraphics.setClip(this.fighter_x + 14, this.bc + 21, 19, 14);
                    // this.readMedia.drawImageAnchor20(paramGraphics, 42, this.fighter_x + 14, this.fighter_y + 7);
                    this.readMedia.myDrawClipRegion(paramGraphics, 6, this.fighter_x + 14, this.fighter_y + 7);
                }
                else if (this.hecman_step == 2)
                {
                    //paramGraphics.setClip(this.fighter_x + 20, this.bc + 21, 19, 14);
                    // this.readMedia.drawImageAnchor20(paramGraphics, 42, this.fighter_x + 20, this.fighter_y + 21);
                    this.readMedia.myDrawClipRegion(paramGraphics, 7, this.fighter_x + 20, this.fighter_y + 21);
                }
                else
                {
                    //paramGraphics.setClip(this.fighter_x + 16, this.bc + 21, 22, 14);
                    // this.readMedia.drawImageAnchor20(paramGraphics, 41, this.fighter_x + 16, this.fighter_y + 21 - 15 * (i1 / 3 % 2));
                    this.readMedia.myDrawClipRegion(paramGraphics, 4, this.fighter_x + 16, this.fighter_y + 21 - 15 * (i1 / 3 % 2));
                }
                //paramGraphics.setClip(0, 0, 240, 300);
                break;
        }
    }

    public int enemy_ai_1(int paramInt)
    {
        if (paramInt == this.bo) {
            return this.bp;
        }
        this.bo = paramInt;
        return this.bp = this.gameHelper.turnAngleCalc(paramInt, stt_byte_arr_bs);
    }

    public void sound_explode(SpriteBatch paramGraphics)
    {
        draw_archangel_and_related(paramGraphics);

        if (this.au > 0)
        {
            this.b8 = (61 - this.fighter_x);
            this.other_fighter_y = (94 - this.fighter_y);

            this.gameHelper.shift_1(paramGraphics, false, 88 - this.b8, 133 - this.other_fighter_y, 30, 8, 4, readMedia, rnd);
            // shift_1(paramGraphics, false, 88 - this.b8, 133 - this.b9, 30, 8, 4);
            if ((this.gamestage1 == 1) && (!this.bool_ay)) {
                this.AA.playSound("s_explo", 1);
            }
            if ((this.gamestage1 == 3) && (!this.bool_az)) {
                this.AA.playSound("s_explo", 1);
            }
            this.au += -1;
            if ((this.AA.bool_n == true) && (this.au == 2) && (this.bool_az == true)) {
                play_s_plasma(false);
            }
        }
    }

    public void play_missile_sound()
    {
        if (this.gameSetting.o <= 0)
        {
            this.AA.x = 1;
            this.AA.bool_j = true;
        }
        else
        {
            this.AA.playSound("s_missile", 1);
        }
        this.bool_a0 = true;
    }

    public void play_s_gun(boolean paramBoolean)
    {
        this.bool_ay = paramBoolean;
        if (this.bool_ay == true) {
            this.AA.playSound("s_gun", 0);
        } else {
            this.AA.stopSound();
        }
    }

    public int enemy_ai_2(int paramInt) // For enemy 2; similar as enemy_ai_1()
    {
        if (paramInt == this.bq) {
            return this.br;
        }
        this.bq = paramInt;
        return this.br = this.gameHelper.turnAngleCalc(90 - paramInt, stt_byte_arr_bs);
    }

    // Change flying speed
    public void speed_up_down(boolean paramBoolean)
    {
        if (!this.bool_bg) {
            this.ar = (paramBoolean ? 2 : -2); // +/- 20
        }
    }

    public void simple_helper2(int paramInt)
    {
        if (!this.bool_bg) {
            if (paramInt == 1)
            {
                this.a8 += 1;
                if (this.a8 > 4) {
                    this.a8 = 4;
                }
            }
            else if (paramInt == 2)
            {
                this.a8 += -1;
                if (this.a8 < 0) {
                    this.a8 = 0;
                }
            }
        }
    }

    public MainGameScreen(ArchAngelME paramArchAngel, ReadMedia paramd)
    {
        this.AA = paramArchAngel;
        this.readMedia = paramd;
        this.gameSetting = paramArchAngel.gameSetting;
        for (int i1 = 0; i1 < 18; i1++) {
            enemyArr[i1] = new Enemy();
        }
    }

    public void config2(Enemy paramg, int paramInt1, int paramInt2)
    {
        int i1 = paramg.enemy_distance_1;
        int i2 = paramg.enemy_distance_2;
        int i3 = paramg.h;
        int i4 = paramg.i;
        int i5 = paramg.j;
        int i6 = 10;
        int i7 = i3 * (paramInt2 - i2) - i4 * (paramInt1 - i1);
        if (i7 < 0) {
            i6 = -i6;
        }
        i5 += i6;
        paramg.h = (6 * enemy_ai_1(i5));
        paramg.i = (6 * enemy_ai_2(i5));
        paramg.j = i5;
    }

    // This seem to ammunation machine gun round available (1000 ?)
    public void setup2()
    {
        this.au = 1000;
        this.gameSetting.fighter_hp = 0;
        this.bool_ay = false;
        this.bool_az = false;
        this.bool_a0 = false;
        this.AA.ab = 7;
        setup3();
        this.ar = -20;
    }

    public void clip_color_arr_helper(SpriteBatch paramGraphics)
    {
        this.readMedia.drawImageAnchor20(paramGraphics, 21, 0, 0);
        for (int i1 = 0; i1 < 18; i1++) {
            if (enemyArr[i1].c != 0) {
                ReturnHelper arrReturn =
                this.gameHelper.draw_radar_dot(paramGraphics, enemyArr[i1], this.al, this.am, this.av, this.bo, this.bp, this.bq, this.br, this.gamestage1, this.AA.boss_sprite_level, stt_byte_arr_bs);
                Gdx.app.log("ERROR", ""+this.gamestage1);
                this.bo = (arrReturn.one > arrReturn.MIN_INT) ? arrReturn.one : this.bo;
                this.bp = (arrReturn.two > arrReturn.MIN_INT) ? arrReturn.two : this.bp;
                this.bq = (arrReturn.three > arrReturn.MIN_INT) ? arrReturn.three : this.bq;
                this.br = (arrReturn.four > arrReturn.MIN_INT) ? arrReturn.four : this.br;
            }
        }
        // paramGraphics.setClip(0, 50, 240, 250);
        for (int i1 = 0; i1 < 9; i1++) {
            for (int i2 = 0; i2 < 18; i2++) {
                if ((enemyArr[i2].c != 0) && (enemyArr[i2].d == i1)) {
                    // complex_draw_helper(paramGraphics, enemyArr[i2]);
                    ReturnHelper returnComplexDrawHelper =
                        this.gameHelper.complex_draw_helper(paramGraphics, enemyArr[i2], this.readMedia, this.af, this.b0, this.b1, this.b2,
                            this.b3, this.fighter_x, this.fighter_y, this.be, this.bf, this.gamestage1, this.bj, this.bk, this.by, this.bz, this.AA.bool_n, this.rnd);

                    // If this.l value has changed
                    this.af = (returnComplexDrawHelper.one > returnComplexDrawHelper.MIN_INT) ? returnComplexDrawHelper.one : this.af;
                    this.b0 = (returnComplexDrawHelper.two >= returnComplexDrawHelper.MIN_INT) ? returnComplexDrawHelper.two : this.b0;
                    this.b1 = (returnComplexDrawHelper.three >= returnComplexDrawHelper.MIN_INT) ? returnComplexDrawHelper.three : this.b1;
                    this.b2 = (returnComplexDrawHelper.four >= returnComplexDrawHelper.MIN_INT) ? returnComplexDrawHelper.four : this.b2;
                    this.b3 = (returnComplexDrawHelper.five >= returnComplexDrawHelper.MIN_INT) ? returnComplexDrawHelper.five : this.b3;
                    this.b4 = (returnComplexDrawHelper.six >= returnComplexDrawHelper.MIN_INT) ? returnComplexDrawHelper.six : this.b4;
                    this.b5 = (returnComplexDrawHelper.seven >= returnComplexDrawHelper.MIN_INT) ? returnComplexDrawHelper.seven : this.b5;
                    this.b6 = (returnComplexDrawHelper.eight >= returnComplexDrawHelper.MIN_INT) ? returnComplexDrawHelper.eight : this.b6;
                    this.b7 = (returnComplexDrawHelper.night >= returnComplexDrawHelper.MIN_INT) ? returnComplexDrawHelper.night : this.b7;
                    if(returnComplexDrawHelper.bool_one > 0) {
                        this.AA.bool_n = (returnComplexDrawHelper.bool_one != 0) ? true : false;
                    }
                    this.fighter_x = (returnComplexDrawHelper.yi > returnComplexDrawHelper.MIN_INT) ? returnComplexDrawHelper.yi : this.fighter_x;
                    this.fighter_y = (returnComplexDrawHelper.er > returnComplexDrawHelper.MIN_INT) ? returnComplexDrawHelper.er : this.fighter_y;
                    this.be = (returnComplexDrawHelper.san > returnComplexDrawHelper.MIN_INT) ? returnComplexDrawHelper.san : this.be;
                    this.bf = (returnComplexDrawHelper.si > returnComplexDrawHelper.MIN_INT) ? returnComplexDrawHelper.si : this.bf;
                    this.gamestage1 = (returnComplexDrawHelper.wu > returnComplexDrawHelper.MIN_INT) ? returnComplexDrawHelper.wu : this.gamestage1;
                    this.bj = (returnComplexDrawHelper.liu > returnComplexDrawHelper.MIN_INT) ? returnComplexDrawHelper.liu : this.bj;
                    this.bk = (returnComplexDrawHelper.qi > returnComplexDrawHelper.MIN_INT) ? returnComplexDrawHelper.qi : this.bk;
                    this.by = (returnComplexDrawHelper.ba > returnComplexDrawHelper.MIN_INT) ? returnComplexDrawHelper.ba : this.by;
                    this.bz = (returnComplexDrawHelper.jiu > returnComplexDrawHelper.MIN_INT) ? returnComplexDrawHelper.jiu: this.bz;
                }
            }
        }
    }

    public void setup3()
    {
        this.aq = 0;
        this.ar = 0;
        this.an = (this.ao = 0);
    }

    public void config2()
    {
        int i2 = 0;
        this.ap += 1;
        this.bn += -1;
        for (int i3 = 0; i3 < 18; i3++) {
            init_game(enemyArr[i3], i3, enemyArr);
        }
        if ((this.ap & 0x3) == 0)
        {
            if ((this.gamestage1 == 3) && (this.v == 0))
            {
                i2 = 14;
                if (i2 > 0) {
                    updateEnemyArr(i2, 0);
                }
            }
            if ((this.ag < 2) && (this.gamestage1 == 1))
            {
                i2 = 13;
                if (i2 > 0) {
                    updateEnemyArr(i2, 0);
                }
            }
        }
        int i1 = this.at >> 10; // = 255
        if (i1 != this.cb)
        {
            if ((this.ah < 2) && (i2 != 14) && (this.gamestage1 == 1)) {
                updateEnemyArr(10, 0);
            }
            this.cb = i1;
        }
    }

    public void boss_finder_ai()
    {
        simple_helper2(this.hecman_y_step);
        if ((this.x <= 10) && (this.x >= -10)) {
            // follow_boss(this.hecman_step);
            ReturnHelper bossReturn =
            this.gameHelper.follow_boss(this.hecman_step, this.fighter_x, this.bd, this.x, this.bool_bg, this.int_arr_a5, this.bool_arr_a7);
            this.bd = bossReturn.one;
            this.x = bossReturn.two;
        }
        this.as += this.aq;
        if (this.aq == 0)
        {
            if (this.as > 0) {
                this.as += -1;
            }
            if (this.as < 0) {
                this.as += 1;
            }
        }
        if (this.as > 4) {
            this.as = 4;
        }
        if (this.as < -4) {
            this.as = -4;
        }
        this.av -= this.as;
        if (this.av < 0) {
            this.av += 360;
        }
        if (this.av >= 360) {
            this.av -= 360;
        }
        this.al = ((enemy_ai_1(this.av) * this.gamespeed >> 6) + this.an);
        this.am = ((enemy_ai_2(this.av) * this.gamespeed >> 6) + this.ao);
        this.at -= this.am;
        this.gamespeed += this.ar; // change game speed
        if (this.gamespeed > 60) { // 140
            this.gamespeed = 60; // 140
        }
        if (this.gamespeed < 20) {
            this.gamespeed = 20;
        }
        this.a3 += -1;
        this.a4 += -1;
        if ((this.bool_ay) && (this.a4 <= 0) && (updateEnemyArr(6, 0)) && (this.bn <= 0)) {
            this.bn = 12;
        }
        int i1;
        if ((this.bool_az) && (this.AA.x % 5 == 0) && (this.AA.bool_n == true) && (this.gameSetting.t > 0))
        {
            i1 = this.aj;
            if ((this.aj < 0) || (this.ak > 0)) {
                i1 = -1;
            }
            if (updateEnemyArr(11, i1))
            {
                this.gameSetting.t += -1;
                if (this.gameSetting.t <= 0)
                {
                    this.AA.stopSound();
                    this.bool_az = false;
                }
            }
        }
        if (this.bool_a0)
        {
            if (this.gameSetting.o > 0)
            {
                i1 = this.aj;
                if ((this.aj < 0) || (this.ak > 0)) {
                    i1 = -1;
                }
                if (updateEnemyArr(1, i1))
                {
                    this.gameSetting.o += -1;
                    if (this.gameSetting.o <= 0) {
                        this.bool_a0 = false;
                    }
                    this.a3 = 20;
                }
            }
            this.bool_a0 = false;
        }
    }

    // Exception when move
    public void play_s_plasma(boolean paramBoolean)
    {
        if (this.AA.ag == 1) {
            this.bool_az = (!this.bool_az);
        } else {
            this.bool_az = paramBoolean;
        }
        if ((this.gameSetting.t > 0) && (this.AA.bool_n == true)) {
            if (this.bool_az == true) {
                this.AA.playSound("s_plasma", 0);
            } else {
                this.AA.stopSound();
            }
        }
    }

    public int complex_helper2(Enemy paramg, int paramInt)
    {
        int i3 = 8;
        int i5;
        if (this.AA.boss_sprite_level < 4) {
            i5 = 1;
        } else {
            i5 = 2;
        }
        int i4;
        int i1;
        if (paramg.e > 4000)
        {
            i4 = this.gameHelper.angle_helper(-paramg.enemy_distance_1, -paramg.enemy_distance_2, stt_byte_arr_bt);
            i1 = 20;
        }
        else
        {
            i4 = paramg.j;
            i1 = (Math.abs(this.rnd.nextInt()) & 0x7) + 5;
            int i2 = Math.abs(this.rnd.nextInt() & 0x7);
            i3 = Math.abs(paramg.e - 3500) >> 9;
            if (i2 >= i3)
            {
                if (this.af < i5) {
                    updateEnemyArr(12, paramInt);
                }
                i1 = 4;
            }
            i4 = Math.abs(this.rnd.nextInt() % 18) * 10;
            if (i4 < 80) {
                i4 = 80;
            }
            if (i4 > 100) {
                i4 = 100;
            }
        }
        paramg.h = (this.al - this.an + enemy_ai_1(i4) * 2);
        paramg.i = (this.am - this.ao + enemy_ai_2(i4) * 2);
        paramg.j = i4;
        return i1;
    }

    public boolean updateEnemyArr(int paramInt1, int paramInt2)
    {
        int i1;
        for (i1 = 0; i1 < 18; i1++) {
            if (enemyArr[i1].c == 0) {
                break;
            }
        }
        if (i1 == 18) {
            return false;
        }
        boss_distance_ai(enemyArr[i1], paramInt1, paramInt2);

        return true;
    }

    public void boss_distance_ai(Enemy paramg, int paramInt1, int paramInt2)
    {
        paramg.m = 0;
        int i1;
        switch (paramInt1)
        {
            case 9:
                paramg.enemy_distance_1 = this.boss_distance_r;
                paramg.enemy_distance_2 = this.boss_distance_s;
                paramg.e = 99999;
                paramg.h = 0;
                paramg.i = 0;
                break;
            case 10:
                paramg.m = 50;
                i1 = (this.rnd.nextInt() & 0x1F) - 15; // AND bit
                int i2 = (this.rnd.nextInt() & 0x7) + 63; // AND bit
                paramg.enemy_distance_1 = (i2 * enemy_ai_1(i1 + this.av));
                paramg.enemy_distance_2 = (i2 * enemy_ai_2(i1 + this.av));
                paramg.h = (paramg.i = 0);
                paramg.e = i1;
                this.ah += 1;
                break;
            case 14:
                paramg.m = this.n;
                this.av = 90;
                i1 = 0;
                paramg.enemy_distance_1 = (60 * enemy_ai_1(i1 + this.av));
                paramg.enemy_distance_2 = (60 * enemy_ai_2(i1 + this.av));
                paramg.e = i1;
                paramg.h = (paramg.i = 0);
                paramg.j = this.av;
                paramg.l = 2;
                this.v += 1;
                paramg.d = 0;
                this.AA.bool_n = true;
                break;
            case 13:
                paramg.m = this.j;
                i1 = (this.rnd.nextInt() & 0x7F) - 63;
                paramg.enemy_distance_1 = (60 * enemy_ai_1(i1 + this.av));
                paramg.enemy_distance_2 = (60 * enemy_ai_2(i1 + this.av));
                paramg.e = i1;
                paramg.h = (paramg.i = 0);
                paramg.j = ((this.av + 180) % 360);
                paramg.l = 2;
                this.w += 1;
                this.ag += 1;
                break;
            case 12:
                paramg.enemy_distance_1 = enemyArr[paramInt2].enemy_distance_1;
                paramg.enemy_distance_2 = enemyArr[paramInt2].enemy_distance_2;

                ReturnHelper shift3 = this.gameHelper.shift_3(paramg.enemy_distance_1, paramg.enemy_distance_2);
                paramg.e = (shift3.three > shift3.MIN_INT) ? shift3.three : paramg.e;

                // i1 = paramg.fighter_hp = angle_helper(-paramg.enemy_distance_1 + this.al, -paramg.screen + this.am);
                i1 = paramg.j = this.gameHelper.angle_helper(-paramg.enemy_distance_1 + this.al, -paramg.enemy_distance_2 + this.am, stt_byte_arr_bt);
                paramg.h = (3 * enemy_ai_1(i1) + this.al);
                paramg.i = (3 * enemy_ai_2(i1) + this.am);
                paramg.l = 30;
                paramg.m = (enemyArr[paramInt2].c == 14 ? this.o : this.k);
                this.af += 1;
                break;
            case 11:
                i1 = paramg.j = this.av;
                paramg.h = (6 * enemy_ai_1(i1));
                paramg.i = (6 * enemy_ai_2(i1));
                paramg.enemy_distance_1 = (enemy_ai_1(this.av - 90) + paramg.h);
                paramg.enemy_distance_2 = (enemy_ai_2(this.av - 90) + paramg.i);
                paramg.e = 64;
                paramg.k = paramInt2;
                paramg.l = 30;
                paramg.m = this.gameSetting.s;
                break;
            case 1:
                i1 = paramg.j = this.av;
                paramg.h = (6 * enemy_ai_1(i1));
                paramg.i = (6 * enemy_ai_2(i1));
                paramg.enemy_distance_1 = (enemy_ai_1(this.av - 90) + paramg.h);
                paramg.enemy_distance_2 = (enemy_ai_2(this.av - 90) + paramg.i);
                paramg.e = 64;
                paramg.k = paramInt2;
                paramg.l = 30;
                paramg.m = this.gameSetting.n;
                break;
            case 6:
                i1 = paramg.j = this.av;
                paramg.h = (6 * enemy_ai_1(i1));
                paramg.i = (6 * enemy_ai_2(i1));
                paramg.enemy_distance_1 = (enemy_ai_1(this.av + 90) + paramg.h);
                paramg.enemy_distance_2 = (enemy_ai_2(this.av + 90) + paramg.i);
                paramg.e = 64;
                paramg.l = (10 + this.cc);
                this.cc = (1 - this.cc);
                paramg.m = (this.gameSetting.n / 10);
                break;
        }
        paramg.c = paramInt1;
    }

    public void simple_90(boolean paramBoolean)
    {
        if (!this.bool_bg)
        {
            int i1 = paramBoolean ? 90 : -90;
            this.an = (2 * enemy_ai_1(this.av + i1));
            this.ao = (2 * enemy_ai_2(this.av + i1));
        }
    }

    // TODO fix updateEnemyArr so we can move these below method to MainGameHelper
    public int e_turn_ai2(Enemy paramg, int paramInt)
    {
        int i3 = 8;
        int i4;
        int i1;
        if (paramg.e > 4000)
        {
            // i4 = angle_helper(-paramg.enemy_distance_1, -paramg.screen);
            i4 = angle_helper(-paramg.enemy_distance_1, -paramg.enemy_distance_2);
            i1 = 20;
        }
        else
        {
            i1 = (Math.abs(this.rnd.nextInt()) & 0x7) + 5;
            int i2 = Math.abs(this.rnd.nextInt() & 0x7);
            i3 = Math.abs(paramg.e - 3500) >> 8;
            if (i2 >= i3)
            {
                if (this.af < 1) {
                    updateEnemyArr(12, paramInt);
                }
                i1 = 10;
            }
            i4 = paramg.j;
            i4 += (i2 << 2) - 14;
            if (i4 < 0) {
                i4 += 360;
            }
            if (i4 >= 360) {
                i4 -= 360;
            }
        }
        paramg.h = (this.al - this.an + turn_helper(i4) * 2);
        paramg.i = (this.am - this.ao + turn_helper2(i4) * 2);
        paramg.j = i4;

        return i1;
    }

    public int angle_helper(int paramInt1, int paramInt2)
    {
        int i4 = 90;
        if (paramInt1 == 0)
        {
            if (paramInt2 > 0) {
                return 90;
            }
            return 270;
        }
        if (paramInt2 == 0)
        {
            if (paramInt1 > 0) {
                return 0;
            }
            return 180;
        }
        int i1 = Math.abs(paramInt1);
        int i2 = Math.abs(paramInt2);
        int i3;
        if (i1 > i2) {
            i3 = (i2 << 6) / i1;
        } else {
            i3 = (i1 << 6) / i2;
        }
        int i5 = 0;
        int i6 = 45;
        if (i3 >= 64) {
            i4 = 45;
        } else {
            for (int i7 = 0; i7 < 5; i7++)
            {
                i4 = i5 + i6 >> 1;
                if (i3 > stt_byte_arr_bt[i4]) {
                    i5 = i4;
                } else {
                    i6 = i4;
                }
            }
        }
        if (i2 > i1) {
            i4 = 90 - i4;
        }
        if (paramInt1 < 0) {
            i4 = 180 - i4;
        }
        if (paramInt2 < 0) {
            i4 = 360 - i4;
        }
        return i4;
    }

    public int turn_helper(int paramInt) // int
    {
        if (paramInt == this.bo) {
            return this.bp;
        }
        this.bo = paramInt; // bo = paramInt;
        return this.bp = turnAngleCalc(paramInt);
    }

    public int turn_helper2(int paramInt)
    {
        if (paramInt == this.bq) {
            return this.br;
        }
        this.bq = paramInt;
        return this.br = turnAngleCalc(90 - paramInt);
    }

    public int turnAngleCalc(int paramInt)
    {
        if (paramInt < 0) {
            paramInt += 360;
        }
        if (paramInt >= 360) {
            paramInt -= 360;
        }
        if (paramInt < 0) {
            paramInt += 360;
        }
        if (paramInt >= 360) {
            paramInt -= 360;
        }
        if ((paramInt >= 0) && (paramInt < 90)) {
            if(paramInt < stt_byte_arr_bs.length) {
                return stt_byte_arr_bs[paramInt];
            }
        }
        if ((paramInt > 90) && (paramInt < 180)) { // >=90
            // Why in some app section, bound of array is out by 1 like in e.java i1++ (line 52)
            // This bs byte array is 90 in length, so index of it has maximum value equal 89.
            // May be this way to trick/optimize processText custom compiler for old Java mobile devices ?
            // Or decompiler resulted in defect
            return -stt_byte_arr_bs[(180 - paramInt)];
        }
        if (paramInt == 90) { // fix me
            Gdx.app.log("ERROR", " 90 " + " arr_bs length "+ stt_byte_arr_bs.length);
            return -stt_byte_arr_bs[89];
        }

        if ((paramInt >= 180) && (paramInt < 270)) { // >=180
            return -stt_byte_arr_bs[(paramInt - 180)];
        }
        if ( (360 - paramInt) < stt_byte_arr_bs.length ) {
            return stt_byte_arr_bs[(360 - paramInt)];
        } else {
            Gdx.app.log("ERROR", " 360 "+ paramInt);
            return stt_byte_arr_bs[89];
        }
    }
}
