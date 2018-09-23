package wait4u.littlewing.archangel;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class DrawGamePlay {
    private static int SCREEN_HEIGHT = Gdx.graphics.getHeight();

    // 120x160 or 128x128px from original J2ME resolution (in some game). This case screen_width is 240px
    private static float MOBI_SCL = (float)Gdx.graphics.getWidth()/240; // FIXME 4.5 is not integer
    private static int MOBI_H = 320;  // JavaME height = 320px
    private static int MOBI_W = 240; // Original Java Phone resolution.

    private static int VIEW_PORT_HEIGHT = (int)SCREEN_HEIGHT*3/4;
    private static int BOTTOM_SPACE = (int)(SCREEN_HEIGHT/8 + 20*MOBI_SCL); // 20 as Java phone reserved top bar shift y

    private Texture[] imgColor; // For fillRect with color; TODO color constant and add remain color png
    public DrawGamePlay() {
        imgColor = new Texture[6];
        for (int i = 0; i < 6; i++) {
            imgColor[i] = new Texture("samsung-white/color-" + i + ".png");
        }
    }

    public void save_equipment(SpriteBatch paramGraphics, ArchAngelME archAngel)
    {
        // paramGraphics.setColor(0);
        archAngel.readMedia.drawGraphicStr40_122(paramGraphics, 12, 145, "Save equipment");
        archAngel.readMedia.drawGraphicStr40_122(paramGraphics, 12, 160, "as you are using.");
    }

    public void read_text_helper(int paramInt1, int paramInt2, ArchAngelME archAngel)
    {
        int i1 = -1;
        if ((paramInt1 >= 49) && (paramInt1 <= 57))
        {
            i1 = paramInt1 - 49 + 1;
            if (archAngel.screen == 13) {
                archAngel.readText.a = i1;
            }
        }
        else if ((paramInt1 != -8) && (paramInt1 != 42) && (paramInt1 != 48) && (paramInt1 != 35))
        {
            switch (paramInt1)
            {
                case -4:
                case -2:
                    archAngel.readText.a += 1;
                    archAngel.run_state2 = -1;
                    archAngel.bool_g = true;
                    break;
                case -3:
                case -1:
                    archAngel.readText.a += -1;
                    archAngel.run_state2 = -1;
                    archAngel.bool_g = true;
                    break;
                default:
                    i1 = archAngel.readText.a;
            }
            if (archAngel.readText.a <= 0) {
                archAngel.readText.a = archAngel.readText.b;
            }
            if (archAngel.readText.a > archAngel.readText.b) {
                archAngel.readText.a = 1;
            }
        }
        if ((i1 > 0) && (archAngel.readText.int_arr_m[(i1 - 1)] > 0))
        {
            archAngel.game_state = archAngel.readText.int_arr_m[(i1 - 1)];
            archAngel.run_state2 = 0;
        }
    }

    // void
    public ReturnHelper draw_start_option(SpriteBatch paramGraphics, int o, int p, ArchAngelME archAngel)
    {
        ReturnHelper startReturn = new ReturnHelper();

        if ((archAngel.game_state > 0) && (archAngel.run_state2 > 0)) {
            return startReturn;
        }
        int i1;
        switch (archAngel.game_state)
        {
            case 0:
                if (archAngel.run_state2 < 4)
                {
                    if (archAngel.run_state2 == 0)
                    {
                        archAngel.playSound("m_briefing", 0);
                        archAngel.readMedia.readMediaStream("brief");
                    }
                    archAngel.readMedia.reloadImageArr(archAngel.run_state2, 17 + archAngel.run_state2);
                }
                if (archAngel.run_state2 == 4)
                {
                    // archAngel.readMedia.closeInputStream();
                    archAngel.readMedia.readMediaStream("boss" + archAngel.gameSetting.boss_level);
                    archAngel.readMedia.reloadImageArr(8, 21);
                    // archAngel.readMedia.closeInputStream();
                    if (archAngel.bool_k)
                    {
                        archAngel.readMedia.readMediaStream("menu");
                        for (i1 = 0; i1 < 4; i1++) {
                            archAngel.readMedia.reloadImageArr(i1, 13 + i1);
                        }
                        // archAngel.readMedia.closeInputStream();
                        // paramGraphics.setColor(0);
                        // paramGraphics.fillRect(0, 80, 240, 220);
                        fillRect(paramGraphics, 0, 80, 240, 220, 5);
                        archAngel.readMedia.drawImageAnchor20(paramGraphics, 15, 33, 60);
                        // paramGraphics.setClip(198, 60, 18, 20);
                        archAngel.readMedia.drawImageAnchor20(paramGraphics, 16, 198, 60 - archAngel.gameSetting.boss_level * 20);
                        // paramGraphics.setClip(0, 0, 240, 320);
                    }
                    archAngel.readMedia.drawImageAnchor20(paramGraphics, 14, 17, 89);
                    // paramGraphics.setClip(17, 89, 223, 25);
                    archAngel.readMedia.drawImageAnchor20(paramGraphics, 13, 60, 90);
                    if (archAngel.gameSetting.boss_level < 4) {
                        archAngel.readMedia.readMediaStream("enermy" + archAngel.gameSetting.boss_level);
                    } else {
                        archAngel.readMedia.readMediaStream("boss" + (archAngel.gameSetting.boss_level - 4));
                    }
                    archAngel.readMedia.reloadImageArr(8, 22);
                    // archAngel.readMedia.closeInputStream();
                    archAngel.drawImage(paramGraphics);
                    if (archAngel.screen == 9) {
                        archAngel.draw_string_y305(paramGraphics, "SKIP", true);
                    }
                    archAngel.draw_string_y305(paramGraphics, "NEXT", false);
                    archAngel.game_state += 1;
                    archAngel.p = -1;
                    archAngel.bool_h = false;
                }
                return startReturn;
            case 1:
                archAngel.game_state = 11;
                archAngel.processText(archAngel.gameSetting.boss_level + 1);
                // paramGraphics.setColor(65280);
                break;
            case 999:
                if (archAngel.screen == 9) {
                    archAngel.screen = 25;
                } else {
                    archAngel.screen = 5;
                }
                archAngel.gameSetting.c = archAngel.gameSetting.boss_level;
                for (i1 = 0; i1 < 6; i1++) {
                    archAngel.readMedia.destroyImage(17 + i1);
                }
                return startReturn;
        }
        if (archAngel.game_state >= 11) {
            ReturnHelper armReturn =
            draw_arm(paramGraphics, archAngel.game_state, 242, archAngel);
            o = armReturn.one;
            p = armReturn.two;
        }
        if (!archAngel.readText.processTxt(archAngel.game_state + 1))
        {
            if (archAngel.screen == 9)
            {
                archAngel.drawImage(paramGraphics);
                archAngel.draw_string_y305(paramGraphics, "START", false);
            }
            else
            {
                archAngel.drawImage(paramGraphics);
                archAngel.draw_string_y305(paramGraphics, "OPTIONS", false);
            }
            archAngel.temp_state = (archAngel.game_state = '?');
        }

        startReturn.one = o;
        startReturn.two = p;

        return startReturn;
    }

    // void
    public  ReturnHelper draw_arm(SpriteBatch paramGraphics, int paramInt1, int paramInt2, ArchAngelME archAngel)
    {
        ReturnHelper armReturn = new ReturnHelper();
        armReturn.one = paramInt2; // o
        armReturn.two = paramInt1; // p
        draw_arm_detail(paramGraphics, paramInt1, paramInt2, archAngel);

        return armReturn;
    }

    public void draw_arm_detail(SpriteBatch paramGraphics, int paramInt1, int paramInt2, ArchAngelME archAngel)
    {
        int i1 = paramInt2;
        // paramGraphics.setColor(9605802);
        // paramGraphics.fillRect(0, 119, 240, 181);
        fillRect(paramGraphics, 0, 119, 240, 191, 4);
        if (paramInt1 == 11)
        {
            // Bottom background or brief (these index 17-20 is reused
            archAngel.readMedia.drawImageAnchor20(paramGraphics, 17, 10, 126);
        }
        else if ((paramInt1 >= 12) && (paramInt1 < 14))
        {
            archAngel.readMedia.drawImageAnchor20(paramGraphics, 20, 6, 126);
            archAngel.readMedia.drawImageInArr(paramGraphics, 22, 55, 170);
            archAngel.readMedia.drawStringGraphic(paramGraphics, 140, 140, "NAME:", 0);
            archAngel.readMedia.drawStringGraphic(paramGraphics, 140, 150, archAngel.mainGameScreen.enemy_fighter, 0);
            archAngel.readMedia.drawStringGraphic(paramGraphics, 140, 160, "DAMAGE:", 0);
            archAngel.readMedia.drawStringGraphic(paramGraphics, 140, 170, archAngel.mainGameScreen.k + "MP", 0);
        }
        else if (paramInt1 >= 14)
        {
            archAngel.readMedia.drawImageAnchor20(paramGraphics, 20, 6, 126);
            archAngel.readMedia.drawImageInArr(paramGraphics, 21, 55, 170);
            archAngel.readMedia.drawStringGraphic(paramGraphics, 140, 140, "NAME:", 0);
            archAngel.readMedia.drawStringGraphic(paramGraphics, 140, 150, archAngel.mainGameScreen.str_q, 0);
            archAngel.readMedia.drawStringGraphic(paramGraphics, 140, 160, "DAMAGE:", 0);
            archAngel.readMedia.drawStringGraphic(paramGraphics, 140, 170, archAngel.mainGameScreen.o + "MP", 0);
        }
        archAngel.readMedia.drawImageAnchor20(paramGraphics, 18, 189, 175);
        archAngel.readMedia.drawImageAnchor20(paramGraphics, 19, 0, 235);
        // paramGraphics.setColor(0);
        archAngel.readText.processTxt(paramInt1);
        archAngel.readText.b = 0;
        archAngel.p = archAngel.readText.o;
        String str;
        while ((str = archAngel.readText.buildString()) != null)
        {
            // paramGraphics.setColor(0);
            int i3 = str.indexOf(".");
            if (i3 == 1)
            {
                int i2;
                try
                {
                    i2 = Integer.parseInt(str.substring(0, i3));
                }
                catch (Exception localException)
                {
                    i2 = -1;
                }
                if (i2 > archAngel.readText.b) {
                    archAngel.readText.b = i2;
                }
            }
            // paramGraphics.drawString(str, 11, i1, 20);
            archAngel.readMedia.drawStringGraphic(paramGraphics, 11, i1, str, 20);
            i1 += 17;
        }
        archAngel.bool_g = false;
    }

    // Orig void int mission_stage, int ab, int ac, int ad, int ae, int af, int ag, boolean bool_z
    public ReturnHelper draw_game_play_screen(SpriteBatch paramGraphics, ArchAngelME archAngel)
    {
        ReturnHelper myReturn = new ReturnHelper();

        archAngel.boss_sprite_level = archAngel.gameSetting.boss_level;
        archAngel.mainGameScreen.fighter_turn = 0;
        if (archAngel.boss_sprite_level < 3) {
            archAngel.ad = 0;
        } else if ((archAngel.boss_sprite_level == 3) || (archAngel.boss_sprite_level == 4)) {
            archAngel.ad = 1;
        } else if (archAngel.boss_sprite_level > 4) {
            archAngel.ad = 2;
        }
        // archAngel.mainGameScreen.bj = 16749568;
        // archAngel.mainGameScreen.bk = 16768512;
        int i1;
        switch (archAngel.game_state)
        {
            case 0:
                archAngel.bool_m = false;
                archAngel.bool_n = false;
                archAngel.bool_l = false;
                int l = 1; // Careful with scope variable
                myReturn.one = l;
                archAngel.readMedia.destroyImage7_53();
                archAngel.readMedia.destroyImage53_115();
                archAngel.readMedia.destroyImage(2);
                archAngel.readMedia.destroyImage(5);
                archAngel.gameSetting.initGame1();
                archAngel.processText(archAngel.gameSetting.boss_level + 1);
                archAngel.mainGameScreen.init_game2();
                archAngel.readMedia.readMediaStream("etc");
                archAngel.readMedia.reloadImageArr(0, 111);
                archAngel.readMedia.closeInputStream();
                archAngel.readMedia.readMediaStream("aa");
                archAngel.readMedia.reloadImageArr(0, 24);
                archAngel.readMedia.reloadImageArr(9, 33);
                archAngel.readMedia.reloadImageArr(10, 34);
                archAngel.readMedia.closeInputStream();
                archAngel.game_state += 1;
                break;
            case 1:
                switch (archAngel.run_state2)
                {
                    case 0:
                        archAngel.readMedia.readMediaStream("background" + archAngel.ad);
                        for (i1 = 0; i1 < 2; i1++) {
                            archAngel.readMedia.reloadImageArr(i1, 7 + i1);
                        }
                        break;
                    case 1:
                        for (i1 = 0; i1 < 9; i1++) {
                            archAngel.readMedia.reloadImageArr(i1 + 2, 9 + i1);
                        }
                        if (archAngel.ad == 2) {
                            for (i1 = 0; i1 < 3; i1++) {
                                archAngel.readMedia.reloadImageArr(i1 + 11, 18 + i1);
                            }
                        }
                        archAngel.readMedia.closeInputStream();
                        break;
                    case 2:
                        System.gc();
                        archAngel.game_state += 1;
                        break;
                }
                draw_rect_clip_helper(paramGraphics, archAngel.run_state2, archAngel);
                break;
            case 2:
                switch (archAngel.run_state2)
                {
                    case 0:
                        archAngel.readMedia.readMediaStream("ui");
                        for (i1 = 0; i1 < 3; i1++) {
                            archAngel.readMedia.reloadImageArr(i1, 21 + i1);
                        }
                        archAngel.readMedia.closeInputStream();
                        archAngel.readMedia.readMediaStream("effect");
                        for (i1 = 0; i1 < 13; i1++) {
                            archAngel.readMedia.reloadImageArr(i1, 71 + i1);
                        }
                        archAngel.readMedia.closeInputStream();
                        break;
                    case 1:
                        System.gc();
                        archAngel.game_state += 1;
                        break;
                }
                draw_rect_clip_helper(paramGraphics, archAngel.run_state2 + 3, archAngel);
                break;
            case 3:
                draw_rect_clip_helper(paramGraphics, archAngel.run_state2 + 5, archAngel);
                switch (archAngel.run_state2)
                {
                    case 0:
                        archAngel.readMedia.readMediaStream("aa");
                        for (i1 = 0; i1 < 11; i1++) {
                            archAngel.readMedia.reloadImageArr(i1, 24 + i1);
                        }
                        archAngel.readMedia.closeInputStream();
                        break;
                    case 1:
                        if (archAngel.boss_sprite_level < 4) {
                            archAngel.readMedia.readMediaStream("enermy" + archAngel.boss_sprite_level);
                        } else {
                            archAngel.readMedia.readMediaStream("boss" + (archAngel.boss_sprite_level - 4));
                        }
                        for (i1 = 0; i1 < 8; i1++) {
                            archAngel.readMedia.reloadImageArr(i1, 53 + i1);
                        }
                        archAngel.readMedia.closeInputStream();
                        break;
                    case 2:
                        archAngel.readMedia.readMediaStream("fence" + archAngel.ad);
                        for (i1 = 0; i1 < 8; i1++) {
                            archAngel.readMedia.reloadImageArr(i1, 44 + i1);
                        }
                        archAngel.readMedia.readMediaStream("plasma");
                        for (i1 = 18; i1 < 24; i1++) {
                            archAngel.readMedia.reloadImageArr(i1, 84 + i1);
                        }
                        archAngel.readMedia.closeInputStream();
                        break;
                    case 3:
                        archAngel.stopSound();
                        System.gc();
                        archAngel.game_state += 1;
                }
                break;
            case 4:
                if (archAngel.run_state2 == 0)
                {
                    archAngel.readMedia.destroyImage(111);
                    int bool_b = 0; // false;
                    myReturn.bool_one = bool_b;

                    ReturnHelper settingReturn = setting2(paramGraphics, archAngel);
                    myReturn = mergeReturnValue(myReturn, settingReturn);

                    archAngel.no_missile = false;
                }
                // paramGraphics.setClip(0, 0, 240, 320);
                archAngel.mainGameScreen.main_paint(paramGraphics);
                draw_fighting(paramGraphics, archAngel);
                if ((archAngel.run_state2 < 10) && (archAngel.no_missile == true))
                {
                    archAngel.readMedia.drawStringGraphic(paramGraphics, 135, 27, "", 0);
                    // paramGraphics.setColor(16711680);
                    // paramGraphics.fillRect(80, 194, 80, 10);
                    fillRect(paramGraphics, 80, 194, 80, 10, 0);
                    // orig: 86; +10 BS font slim
                    archAngel.readMedia.drawStringGraphic(paramGraphics, 96, 197, "NO MISSILE", 0);
                }
                if (archAngel.temp_screen2 != 25)
                {
                    archAngel.mainGameScreen.bool_bl = true;
                    int bool_b = 1; // boolean true;
                    myReturn.bool_one = bool_b;
                    archAngel.game_state = 5;
                }
                archAngel.drawImage(paramGraphics);
                archAngel.draw_string_y305(paramGraphics, "OPTIONS", false);
                archAngel.draw_string_y305(paramGraphics, "PAUSE", true);
                break;
            case 5:
                // paramGraphics.setClip(0, 0, 240, 300);
                archAngel.mainGameScreen.main_paint(paramGraphics);
                archAngel.mainGameScreen.draw_fighting();
                archAngel.game_state = 6;
                break;
            case 6:
                archAngel.readMedia.destroyImage7_53();
                archAngel.readMedia.destroyImage53_115();
                archAngel.screen = archAngel.temp_screen2;
                break;
        }

        return myReturn;
    }

    // Append return value as dual variable series
    public ReturnHelper mergeReturnValue(ReturnHelper paramReturn1, ReturnHelper paramReturn2) {
        if(paramReturn2.one > paramReturn2.MIN_INT) {
            paramReturn1.yi = paramReturn2.one;
        }
        if(paramReturn2.two > paramReturn2.MIN_INT) {
            paramReturn1.er = paramReturn2.two;
        }
        if(paramReturn2.three > paramReturn2.MIN_INT) {
            paramReturn1.san = paramReturn2.three;
        }
        if(paramReturn2.four > paramReturn2.MIN_INT) {
            paramReturn1.si = paramReturn2.four;
        }
        if(paramReturn2.five > paramReturn2.MIN_INT) {
            paramReturn1.wu = paramReturn2.five;
        }
        if(paramReturn2.six > paramReturn2.MIN_INT) {
            paramReturn1.liu = paramReturn2.six;
        }
        if(paramReturn2.seven > paramReturn2.MIN_INT) {
            paramReturn1.qi = paramReturn2.seven;
        }
        if(paramReturn2.bool_one > 0) {
            paramReturn1.bool_yi = paramReturn2.bool_one;
        }

        return paramReturn1;
    }

    public void draw_rect_clip_helper(SpriteBatch paramGraphics, int paramInt, ArchAngelME archAngel)
    {
        // paramGraphics.setColor(0); // black ?
        // paramGraphics.fillRect(0, 0, 240, 320);
        archAngel.readMedia.drawImageAnchor20(paramGraphics, 24, 90, 169);
        // paramGraphics.setClip(100, 180, 12, 11);
        archAngel.readMedia.drawImageAnchor20(paramGraphics, 33, 100, 180 - 11 * (paramInt % 2));
        // paramGraphics.setClip(128, 180, 12, 11);
        archAngel.readMedia.drawImageAnchor20(paramGraphics, 34, 128, 180 - 11 * (paramInt % 2));
        // paramGraphics.setColor(16711680);
        // paramGraphics.setClip(87, 200, 66, 14);
        // paramGraphics.fillRect(87, 217 - paramInt * 3, 66, paramInt * 3);
        fillRect(paramGraphics, 87, 217 - paramInt * 3, 66, paramInt * 3, 0);

        archAngel.readMedia.drawImageAnchor20(paramGraphics, 111, 87, 200);
    }

    // void int mission_stage, int ab, int ac, int ad, int ae, int af, int ag, boolean bool_z
    public ReturnHelper setting2(SpriteBatch paramGraphics, ArchAngelME archAngel)
    {
        boolean bool_z = false;
        int aa = 0;
        int ab = 0;
        int ac = 0;
        int ad = 0;
        int ae = 0;
        int af = -1;
        int ag = -1;
        archAngel.readMedia.drawImageAnchor20(paramGraphics, 21, 0, 0);

        return new ReturnHelper(aa, ab, ac, ad, ae, af, ag, 0); // bool_z false as 0
    }

    public void draw_fighting(SpriteBatch paramGraphics, ArchAngelME archAngel)
    {
        // paramGraphics.setClip(0, 0, 240, 40);
        switch (archAngel.mainGameScreen.mission_stage) // mission_stage seem game sub state from destroy N enermies to reach boss
        {
            case 0:
                // From text data => first line is number of enermy need to destroy before reach boss.
                // For example mission 5 <1>+60000+80000+13 ~ destroy the 13 AL-101
                if (archAngel.boss_sprite_level < 4) { // Last mission show ARCHANGEL MK
                    // Original posiotion (75, 10); this fix caused by game status bar shifted to higher
                    archAngel.readMedia.drawStringGraphic(paramGraphics, 75, 10, "ENERMY:" + archAngel.mainGameScreen.enemy_fighter, 0);
                } else {
                    archAngel.readMedia.drawStringGraphic(paramGraphics, 75, 10, archAngel.mainGameScreen.enemy_fighter, 0);
                }
                archAngel.readMedia.drawStringGraphic(paramGraphics, 75, 20, "N:" + (archAngel.mainGameScreen.destroy_n_e - archAngel.mainGameScreen.downed_e_count), 0);
                if (archAngel.mainGameScreen.destroy_n_e - archAngel.mainGameScreen.downed_e_count <= 0)
                {
                    archAngel.mainGameScreen.bool_a1 = true;
                    // Next sub stage: destroyed enermy number reached
                    archAngel.mainGameScreen.mission_stage = archAngel.mainGameScreen.byte_ac;
                }
                break;
            case 1:
                // paramGraphics.setColor(14408703); // purple
                // paramGraphics.drawString(archAngel.mainGameScreen.boss_distance + " m", 120, 3, 17);
                archAngel.readMedia.drawStringGraphic(paramGraphics, 120, 3, archAngel.mainGameScreen.boss_distance + " m", 17);
                int direction = archAngel.mainGameScreen.direction_guide; // Related to Hero HP
                int i2 = Math.abs(direction) / 60 + 1;
                if ((direction <= 5) && (direction >= -5))
                {
                    // This seem slim vertical white line | indicate that fighter is following right direction
                    // Position y may be shifted a bit caused by setClip
                    fillRect(paramGraphics, 112, 20, 1, 7, 4); // TODO add purple color img
                }
                else
                {
                    int i3;
                    if (direction > 5) {
                        for (i3 = 0; i3 < i2; i3++) {
                            // ui_2 The direction marker indicates the direction.
                            archAngel.readMedia.drawImageAnchor20(paramGraphics, 23, 114 + i3 * 4, 20);
                        }
                    } else if (direction < -5) {
                        // ui_1
                        for (i3 = 0; i3 < i2; i3++) {
                            archAngel.readMedia.drawImageAnchor20(paramGraphics, 22, 106 - i3 * 4, 20);
                        }
                    }
                }
                break;
            case 2: // very last mission
                if (archAngel.boss_sprite_level < 7)
                {
                    archAngel.readMedia.drawStringGraphic(paramGraphics, 87, 8, archAngel.mainGameScreen.str_q, 0);
                }
                else
                {
                    archAngel.readMedia.drawStringGraphic(paramGraphics, 89, 5, "ARCHANGEL", 0);
                    archAngel.readMedia.drawStringGraphic(paramGraphics, 89, 11, "   MK2   ", 0);
                }
                archAngel.readMedia.drawStringGraphic(paramGraphics, 82, 18, "HP:", 0);
                // paramGraphics.setColor(9605802); // gray
                // paramGraphics.fillRect(102, 17, 42, 7);
                fillRect(paramGraphics, 102, 17, 42, 7, 4);
                // paramGraphics.setColor(14408703);
                // paramGraphics.drawRect(102, 17, 41, 6);
                // paramGraphics.setColor(16776960);
                // paramGraphics.fillRect(103, 18, archAngel.ae, 5);
                fillRect(paramGraphics, 103, 18, archAngel.ae, 5, 2);
                break;
        }
        int i1 = 40 * archAngel.gameSetting.fighter_hp >> 9;
        // 1000 >> 9 ~ 1; 40,000 >> 9 ~ 78; fighter_hp * 40 lost 50 value each time collidate with cliff
        // equal ab 4 Fighter HP each collision
        // paramGraphics.setColor(255);
        // Fighter hp ?
        fillRect(paramGraphics, 192, 5, i1 / 2, 4, 1); // anchor 20
        if (archAngel.mainGameScreen.gamestage1 == 3)
        {
            if (archAngel.mainGameScreen.bool_bh == true)
            {
                if (archAngel.gameSetting.t < archAngel.gameSetting.r) {
                    archAngel.gameSetting.t += 3;
                }
                if (archAngel.gameSetting.t == archAngel.gameSetting.r) {
                    archAngel.mainGameScreen.bool_bh = false;
                }
            }
            if (archAngel.gameSetting.t <= 0) // may be number of enemies left or flag indicate screen
            {
                archAngel.gameSetting.t = 3; // Try play flow
                // paramGraphics.setColor(16711680);
                if (archAngel.run_state2 / 2 % 2 == 0) // boss scene, out of amour
                {
                     fillRect(paramGraphics, 80, 194, 80, 19, 0);
                    // Boss screen
                    archAngel.readMedia.drawStringGraphic(paramGraphics, 86, 197, "PRESS 0 KEY", 0);
                    archAngel.readMedia.drawStringGraphic(paramGraphics, 92, 205, "TO RELOAD", 0);
                }
            }
            if (archAngel.gameSetting.r != 0) { // may be lever or stage mode ?
                i1 = archAngel.gameSetting.t * 40 / archAngel.gameSetting.r;
            }
        }
        else if (archAngel.mainGameScreen.gamestage1 == 1)
        {
            if(archAngel.gameSetting.caried_missile != 0) {
                i1 = archAngel.gameSetting.missile_left * 40 / archAngel.gameSetting.caried_missile;
            } else {
                i1 = 40; // dungnv
            }
        }
        else
        {
            i1 = 40;
        }
        // paramGraphics.setColor(16711680);
        // Missile left and/or plasma gun mana
        fillRect(paramGraphics, 192, 17, i1, 4, 0); // anchor 20
    }

    public void draw_weapon_shop(SpriteBatch paramGraphics, int paramInt1, boolean paramBoolean, int paramInt2, int t, int u, String[][] str_arr_w, ArchAngelME archAngel, ReadText readText)
    {
        int i3 = 170;
        int i4 = 3;
        String str2 = null;
        int i2 = paramInt1 / 100 - 1;
        int i1 = paramInt1 % 100;
        archAngel.drawImage(paramGraphics);
        archAngel.draw_string_y305(paramGraphics, "OK", false);
        // paramGraphics.setColor(7171414);
        // paramGraphics.fillRect(6, 127, 226, 147);
        fillRect(paramGraphics, 6, 127, 226, 147, 2);
        // paramGraphics.setColor(9605802);
        // paramGraphics.drawRect(6, 127, 225, 146);
        // paramGraphics.setColor(9605717);
        // paramGraphics.fillRect(15, 140, 72, 42);
        fillRect(paramGraphics, 15, 140, 72, 42, 4);
        // paramGraphics.setColor(16777130);
        // paramGraphics.drawRect(15, 139, 72, 43);
        archAngel.readMedia.drawImageSwitch(paramGraphics, 18, 141, 60, 40, i1 - 1, paramInt2);
        switch (paramInt2)
        {
            case 0:
                str2 = "MISSILE";
                break;
            case 1:
                str2 = "PLASMA CANON";
                break;
            case 2:
                str2 = "ARMOR";
                break;
        }
        readText.processTxt(i1);
        String str1 = readText.buildString();
        archAngel.readMedia.drawGraphicStr40_122(paramGraphics, 100, 145, str2);
        archAngel.readMedia.drawGraphicStr40_122(paramGraphics, 100, 165, str1);
        i3 += 30;
        switch (i2)
        {
            case 0:
                t = archAngel.gameSetting.q;
                break;
            case 1:
                i2 = 0;
                t = archAngel.gameSetting.v;
                break;
            case 2:
                i2 = 1;
                t = archAngel.gameSetting.l;
                i4 = 3;
                break;
        }
        t += archAngel.gameSetting.a;
        for (int i5 = 0; i5 < i4; i5++)
        {
            archAngel.readMedia.drawGraphicStr40_122(paramGraphics, 20, i3, str_arr_w[i2][i5] + ":" + readText.int_arr_m[i5]);
            i3 += 15;
        }
        u = readText.int_arr_m[i4];
    }

    public void goto_menu(SpriteBatch paramGraphics, int o, int p, int q, int t, int x, String[][] str_arr_w,
                          ArchAngelME archAngel, ReadText readText, DrawShopAndBrief helper)
    {
        if (archAngel.run_state2 > 0) {
            return;
        }
        switch (archAngel.game_state)
        {
            case 0:
                archAngel.stopSound();
                //ai.aq = null;
                archAngel.drawImage(paramGraphics);
                archAngel.readText.readTextFromStream("subm");
                archAngel.game_state = 1;
                archAngel.readText.bool_c = false;
                break;
            case 1:
                archAngel.bool_s = true;
                if (archAngel.readText.bool_c) {
                    archAngel.readText.g = 4;
                }
                break;
            case 2:
                archAngel.bool_s = false;
                archAngel.readText.bool_c = true;
                archAngel.readText.g = 1;
                break;
            case 40:
                archAngel.addScore12();
                archAngel.temp_screen2 = 5;
            case 10:
                archAngel.addScore12();
                archAngel.temp_screen = (archAngel.screen = 25);
                archAngel.game_state = 4;
                return;
            case 20:
                archAngel.af = (1 - archAngel.af);
                if (archAngel.af == 0) {
                    archAngel.stopSound();
                }
                archAngel.game_state = 1;
                return;
            case 30:
                archAngel.ag = (1 - archAngel.ag);
                if (archAngel.ag == 0) {
                    archAngel.mainGameScreen.play_s_plasma(false);
                }
                archAngel.game_state = 1;
                return;
            case 999:
                archAngel.stopSound();
                archAngel.addScore12();
                archAngel.addScore();
                archAngel.destroyApp(false);
                // archAngel.notifyDestroyed();
                return;
        }
        if (archAngel.bool_t)
        {
            archAngel.mainGameScreen.main_paint(paramGraphics);
            draw_fighting(paramGraphics, archAngel);
            archAngel.bool_t = false;
        }
        setup2(paramGraphics, archAngel.game_state, 217, o, p, q, t, x, str_arr_w, archAngel, readText, helper);
        archAngel.drawImage(paramGraphics);
        archAngel.draw_string_y305(paramGraphics, "OK", false);
    }

    public void setup2(SpriteBatch paramGraphics, int paramInt1, int paramInt2, int o, int p, int q, int t, int x,
                       String[][] str_arr_w, ArchAngelME archAngel, ReadText readText, DrawShopAndBrief helper)
    {
        o = paramInt2;
        p = paramInt1;
        archAngel.bool_w = false;
        helper.draw_arm_shop_menu(paramGraphics, paramInt1, paramInt2, q, t, x, str_arr_w, archAngel, readText);
    }

    public void load_system_txt(SpriteBatch paramGraphics, int l, int o, int p, int q, int t, int x, int y, String[][] str_arr_w, ArchAngelME archAngel, ReadText readText, DrawShopAndBrief helper)
    {
        if ((archAngel.game_state > 0) && (archAngel.run_state2 > 0)) {
            return;
        }
        switch (archAngel.game_state)
        {
            case 0:
                if (archAngel.run_state2 == 0) {
                    archAngel.readText.readTextFromStream("system");
                }
                archAngel.drawImage(paramGraphics);
                archAngel.draw_string_y305(paramGraphics, "PLAY", true);
                archAngel.draw_string_y305(paramGraphics, "OK", false);
                helper.simple_helper(paramGraphics, archAngel);
                return;
            case 1:
                archAngel.game_state = 2;
                break;
            case 10:
                archAngel.screen = 2;
                return;
        }
        helper.draw_system_setin(paramGraphics, l, o, p, q, t, x, y, str_arr_w, archAngel, readText);
    }

    public void draw_victory(SpriteBatch paramGraphics, ArchAngelME archAngel)
    {
        int i1 = 133;
        int i2 = 0;
        int i3 = 0;
        // paramGraphics.setColor(0);
        if (archAngel.mainGameScreen.enemy_fighter != null)
        {
            i3 = archAngel.mainGameScreen.h * archAngel.mainGameScreen.downed_e_count;
            archAngel.readMedia.drawGraphicStr40_122(paramGraphics, 50, i1, archAngel.mainGameScreen.enemy_fighter + ":" + archAngel.mainGameScreen.h + "x" + archAngel.mainGameScreen.downed_e_count);
            i1 += 16;
            i2 += i3;
        }
        if (archAngel.mainGameScreen.str_q != null)
        {
            i3 = archAngel.mainGameScreen.i * archAngel.mainGameScreen.t;
            archAngel.readMedia.drawGraphicStr40_122(paramGraphics, 50, i1, archAngel.mainGameScreen.str_q + ":" + archAngel.mainGameScreen.i + "x" + archAngel.mainGameScreen.t);
            i1 += 16;
            i2 += i3;
        }
        i3 = -archAngel.gameSetting.i * (archAngel.gameSetting.g - archAngel.gameSetting.fighter_hp);
        archAngel.readMedia.drawGraphicStr40_122(paramGraphics, 50, i1, "Maintenance Fee:");
        i1 += 16;
        archAngel.readMedia.drawGraphicStr40_122(paramGraphics, 59, i1, "" + i3);
        i1 += 25;
        i2 += i3;
        archAngel.readMedia.drawGraphicStr40_122(paramGraphics, 50, i1, "Total:" + i2);
        archAngel.gameSetting.a += i2;
    }

    public void draw_warning_etc_menu(SpriteBatch paramGraphics, int l, ArchAngelME archAngel)
    {
        switch (archAngel.game_state)
        {
            case 0:
                archAngel.bool_k = false;
                archAngel.readMedia.destroyImage53_115();
                archAngel.readMedia.destroyImage7_53();
                archAngel.readMedia.readMediaStream("menu");
                for (int i1 = 0; i1 < 4; i1++) {
                    archAngel.readMedia.reloadImageArr(i1, 13 + i1);
                }
                archAngel.readMedia.closeInputStream();
                archAngel.readMedia.readMediaStream("font");
                for (int i1 = 1; i1 < 3; i1++) {
                    archAngel.readMedia.reloadImageArr(i1, i1);
                }
                archAngel.readMedia.reloadImageArr(5, 5);
                archAngel.readMedia.closeInputStream();
                archAngel.bool_w = true;
                archAngel.readText.bool_c = false;
                archAngel.readText.bool_d = false;
                archAngel.game_state += 1;
                break;
            case 1:
                // paramGraphics.setColor(0);
                fillRect(paramGraphics, 0, 0, 240, 320, 4);
                // paramGraphics.setColor(4802901);
                fillRect(paramGraphics, 0, 0, 240, 40, 4);
                // paramGraphics.drawLine(0, 42, 240, 42);
                // paramGraphics.drawLine(0, 45, 240, 45);
                fillRect(paramGraphics, 0, 50, 240, 6, 4);
                fillRect(paramGraphics, 0, 119, 240, 30, 4);
                fillRect(paramGraphics, 0, 171, 240, 10, 4);
                fillRect(paramGraphics, 0, 205, 240, 30, 4);
                archAngel.readMedia.drawImageAnchor20(paramGraphics, 3, 0, 0);
                archAngel.readMedia.drawImageAnchor20(paramGraphics, 5, 27, 25);
                archAngel.readMedia.drawImageAnchor20(paramGraphics, 4, 0, 160);
                archAngel.readMedia.drawImageAnchor20(paramGraphics, 3, 0, 180);
                archAngel.game_state += 1;
                break;
            case 2:
                if (archAngel.run_state2 < 21)
                {
                    // paramGraphics.setColor(0);
                    fillRect(paramGraphics, 0, 80, 240, 200, 4);
                    // setColor(4802901);
                    fillRect(paramGraphics, 0, 119, 240, 30, 4);
                    fillRect(paramGraphics, 0, 171, 240, 10, 4);
                    fillRect(paramGraphics, 0, 205, 240, 30, 4);
                    archAngel.readMedia.drawImageAnchor20(paramGraphics, 4, 0, 160 - 5 * archAngel.run_state2);
                    archAngel.readMedia.drawImageAnchor20(paramGraphics, 3, 0, 180 + 5 * archAngel.run_state2);
                    // paramGraphics.setClip(0, 180 - 5 * archAngel.x, 240, 10 * archAngel.x);
                    archAngel.readMedia.drawImageAnchor20(paramGraphics, 14, 17, 89 + l * 33);
                    archAngel.readMedia.drawImageAnchor20(paramGraphics, 13, 60, 90);
                }
                if (archAngel.run_state2 == 20)
                {
                    archAngel.readMedia.closeInputStream();
                    archAngel.readMedia.readMediaStream("etc");
                    archAngel.readMedia.reloadImageArr(3, 114);
                    archAngel.game_state += 1;
                }
                break;
            case 3:
                archAngel.readMedia.drawImageAnchor20(paramGraphics, 15, 33, 60);
                // paramGraphics.setClip(198, 60, 18, 20);
                archAngel.readMedia.drawImageAnchor20(paramGraphics, 16, 198, 60 - archAngel.gameSetting.boss_level * 20);
                // paramGraphics.setClip(0, 0, 240, 280);
                // paramGraphics.setColor(0);
                fillRect(paramGraphics, 0, 80, 240, 200, 4); // black
                // paramGraphics.setColor(4802901);
                fillRect(paramGraphics, 0, 119, 240, 30, 4); // dark-gray
                fillRect(paramGraphics, 0, 171, 240, 10, 4);
                fillRect(paramGraphics, 0, 205, 240, 30, 4);
                archAngel.readMedia.drawImageAnchor20(paramGraphics, 14, 17, 89 + l * 33);
                archAngel.readMedia.drawImageAnchor20(paramGraphics, 13, 60, 90);
                archAngel.readMedia.drawImageAnchor20(paramGraphics, 3, 0, 280);
                break;
        }
        archAngel.drawImage(paramGraphics);
        archAngel.draw_string_y305(paramGraphics, "PLAY", true);
        archAngel.draw_string_y305(paramGraphics, "OK", false);
    }

    public void draw_font_result(SpriteBatch paramGraphics, ArchAngelME archAngel, DrawShopAndBrief helper)
    {
        switch (archAngel.game_state)
        {
            case 0:
                if (archAngel.run_state2 == 0)
                {
                    for (int i1 = 11; i1 < 19; i1++) {
                        archAngel.readMedia.destroyImage(24 + i1);
                    }
                    archAngel.readMedia.destroyImage53_115();
                    archAngel.readMedia.readMediaStream("font");
                    archAngel.readMedia.reloadImageArr(1, 1);
                    archAngel.readMedia.closeInputStream();
                    archAngel.drawImage(paramGraphics);
                    archAngel.draw_string_y305(paramGraphics, "OK", false);
                    archAngel.bool_s = false;
                    archAngel.bool_m = false;
                }
                helper.simple_helper(paramGraphics, archAngel);
                break;
            case 1:
                archAngel.playSound("m_win", 1);
                archAngel.readMedia.readMediaStream("result");
                archAngel.readMedia.drawLoadImage(2, paramGraphics, 45, 93);
                archAngel.readMedia.drawLoadImage(1, paramGraphics, 62, 98);
                archAngel.readMedia.closeInputStream();
                // paramGraphics.setColor(new Color(Color.WHITE)); // 16777215); #FFFFFF
                fillRect( paramGraphics, 45, 126, 150, 107, 5);
                if (archAngel.gameSetting.boss_level == 7)
                {
                    // paramGraphics.setColor(0);
                    fillRect(paramGraphics, 0, 0, 240, 300, 1);
                    archAngel.screen = 27;
                }
                else
                {
                    archAngel.game_state = 4;
                }
                break;
            case 4:
                if (archAngel.run_state2 == 0) { // game stage or boss hp?
                    draw_victory(paramGraphics, archAngel);
                }
                break;
            case 2:
            case 3:
            default:
                if (archAngel.run_state2 < 16)
                {
                    // paramGraphics.setColor(0);
                    fillRect(paramGraphics, 0, 0, 240, archAngel.run_state2 * 10, 4); // TODO may be set color as CONSTANT
                    fillRect(paramGraphics, 0, 300 - archAngel.run_state2 * 10, 240, archAngel.run_state2 * 10, 4);
                }
                if (archAngel.run_state2 == 16)
                {
                    archAngel.readMedia.destroyImage53_115();
                    // System.gc();
                    archAngel.gameSetting.boss_level += 1;
                    archAngel.addScore();
                    archAngel.screen = 5;
                }
                break;
        }
    }

    public void draw_buy(SpriteBatch paramGraphics, int paramInt1, int paramInt2, int x, ArchAngelME archAngel, ReadText readText)
    {
        int i3 = 93;
        int i4 = 0;
        String str1 = null;
        String str2 = null;
        int i1 = paramInt1 / 100 - 1;
        int i2 = paramInt1 % 100;
        switch (i1)
        {
            case 0:
                i4 = archAngel.gameSetting.q;
                if (archAngel.gameSetting.d == i2)
                {
                    save_equipment(paramGraphics, archAngel);
                    return;
                }
                archAngel.gameSetting.d = i2;
                archAngel.gameSetting.loadDataFromReadTxt(readText);
                break;
            case 1:
                i4 = archAngel.gameSetting.v;
                if (archAngel.gameSetting.e == i2)
                {
                    save_equipment(paramGraphics, archAngel);
                    return;
                }
                archAngel.gameSetting.e = i2;
                archAngel.gameSetting.loadConfig2(readText);
                break;
            case 2:
                i4 = archAngel.gameSetting.l;
                if (archAngel.gameSetting.f == i2)
                {
                    save_equipment(paramGraphics, archAngel);
                    return;
                }
                archAngel.gameSetting.f = i2;
                archAngel.gameSetting.loadConfig3(readText);
                break;
        }
        archAngel.gameSetting.a += i4 - paramInt2;
        switch (x)
        {
            case 0:
                str1 = "missile";
                str2 = archAngel.gameSetting.str_p;
                break;
            case 1:
                str1 = "plasma canon";
                str2 = archAngel.gameSetting.str_u;
                break;
            case 2:
                str1 = "Armor";
                str2 = archAngel.gameSetting.str_k;
                break;
        }
        archAngel.readMedia.drawGraphicStr40_122(paramGraphics, 12, 135, "You bought the");
        archAngel.readMedia.drawGraphicStr40_122(paramGraphics, 12, 150, str1);
        archAngel.readMedia.drawGraphicStr40_122(paramGraphics, 12, 165, str2);
        archAngel.readMedia.drawGraphicStr40_122(paramGraphics, 12, 195, "My money : " + archAngel.gameSetting.a);
    }

    public void draw_intro(SpriteBatch paramGraphics, ArchAngelME archAngel)
    {
        switch (archAngel.game_state)
        {
            case 0:
                if (archAngel.run_state2 == 0)
                {
                    // paramGraphics.setColor( intToFloatColor(16777215) );
                    // paramGraphics.fillRect(0, 0, 240, 320);
                    fillRect(paramGraphics, 0, 0, 240, 320, 5);
                    archAngel.readMedia.drawStringImage("logo", 0, paramGraphics, 48, 80); // Int3 80
                    archAngel.readMedia.drawStringImage("logo", 1, paramGraphics, 46, 161);
                    archAngel.readMedia.readMediaStream("intro");
                    archAngel.readMedia.reloadImageArr(0, 30);
                    archAngel.readMedia.closeInputStream();
                    archAngel.readMedia.readMediaStream("select");
                    for (int i1 = 0; i1 < 7; i1++) {
                        archAngel.readMedia.reloadImageArr(i1, 6 + i1);
                    }
                    archAngel.readMedia.closeInputStream();
                }
                if (archAngel.run_state2 == 8) {
                    archAngel.game_state += 1;
                }
                break;
            case 1:
                if (archAngel.run_state2 == 0)
                {
                    archAngel.playSound("m_front", 0);
                    // paramGraphics.setColor(0);
                    // paramGraphics.fillRect(0, 0, 240, 320);
                    fillRect(paramGraphics, 0, 0, 240, 320, 0);
                    // paramGraphics.setColor(4802901);
                    // paramGraphics.fillRect(0, 0, 240, 40);
                    fillRect(paramGraphics, 0, 0, 240, 40, 4);
                    // paramGraphics.drawLine(0, 42, 240, 42);
                    // paramGraphics.drawLine(0, 45, 240, 45);
                    // paramGraphics.fillRect(0, 50, 240, 6);
                    fillRect(paramGraphics, 0, 50, 240, 6, 4);
                    fillRect(paramGraphics, 0, 119, 240, 30, 0);
                    fillRect(paramGraphics, 0, 171, 240, 10, 0);
                    fillRect(paramGraphics, 0, 205, 240, 30, 0);
                    archAngel.readMedia.drawImageAnchor20(paramGraphics, 3, 0, 0);
                    archAngel.readMedia.drawImageAnchor20(paramGraphics, 3, 0, 200);
                    archAngel.readMedia.drawImageAnchor20(paramGraphics, 4, 0, 60);
                    archAngel.readMedia.drawImageAnchor20(paramGraphics, 5, 27, 25);
                    archAngel.readMedia.drawStringImage("logo", 2, paramGraphics, 0, 300);
                }
                if (archAngel.run_state2 % 10 < 5) {
                    archAngel.readMedia.drawImageAnchor20(paramGraphics, 30, 0, 80);
                } else {
                    archAngel.readMedia.drawGraphicStr40_122(paramGraphics, 92, 264, "Press Any Key"); // orig: x=62
                }
                break;
            case 2:
                archAngel.mainGameScreen.bd = 0;
                if (archAngel.run_state2 < 21)
                {
                    // paramGraphics.setColor(0);
                    // paramGraphics.fillRect(0, 60, 240, 240);
                    fillRect( paramGraphics,0, 60, 240, 240, 0);
                    // paramGraphics.setColor(4802901);
                    fillRect( paramGraphics, 0, 119, 240, 30, 4);
                    fillRect( paramGraphics, 0, 171, 240, 10, 4);
                    fillRect( paramGraphics, 0, 205, 240, 30, 4);
                    // TODO 3 drawClip in enemy_distance_1 row
                    // paramGraphics.setClip(0, 80 + 5 * archAngel.x, 240, 200 - 10 * archAngel.x);
                    archAngel.readMedia.drawImageAnchor20(paramGraphics, 30, 0, 80);
                    // paramGraphics.setClip(0, 0, 240, 320);
                    archAngel.readMedia.drawImageAnchor20(paramGraphics, 4, 0, 60 + 5 * archAngel.run_state2);
                    archAngel.readMedia.drawImageAnchor20(paramGraphics, 3, 0, 280 - 5 * archAngel.run_state2);
                }
                if (archAngel.run_state2 == 20)
                {
                    archAngel.readMedia.destroyImage(30);
                    archAngel.screen = 3;
                }
                break;
        }
    }

    /**
     * @color #0 for red
     * @color #1 for light blue, #3 light blue 2 6DCFF6
     * #2 for light yellow, #4 gray 93959A #5 for white
     * Fills the specified rectangle with the current color. => Need reset or manage color
     * TODO draw filled rectangle with float color instead of color image. Fix ratio.
     */
    public void fillRect(SpriteBatch batch, int x, int y, int width, int height, int color) {
        // Hard code default width x height of color img: 12x12 px
        float scaleY = (float) (height*MOBI_SCL / 12);
        float scaleX = (float) (width*MOBI_SCL / 12);
        // (Texture, float x, float destroy_n_e, float originX, float originY, float width, float height, float scaleX, float scaleY, float rotation, int srcX, int srcY, int srcWidth, int srcHeight, boolean flipX, boolean flipY)
        int pos_x = (int) (MOBI_SCL*x);
        int pos_y = (int) ((MOBI_H - y - 20)*MOBI_SCL - imgColor[color].getHeight()*scaleY + BOTTOM_SPACE); // anchor 20

        batch.draw(imgColor[color], pos_x, pos_y, 0, 0, imgColor[color].getWidth(), imgColor[color].getHeight(), scaleX, scaleY, 0, 0, 0, (int)(imgColor[color].getWidth()*scaleX), (int)(imgColor[color].getHeight()*scaleY), false, false);
    }

    /*
        Color.WHITE // 16777215 #FFFFFF
        20361 // #004F89 darkblue
        9342606 // 8E8E8E gray
        16775065 //FFF799 lightyellow
        16711680 // FF0000
        4960985 //4BB2D9 lightblue
     */
    public Color intToFloatColor(int color) {
        int red = color/(256^2);
        int green = (color/256) % 256;
        int blue = color%256;

        return new Color(red/255f, green/255f, blue/255f, 1.0f);
    }
}
