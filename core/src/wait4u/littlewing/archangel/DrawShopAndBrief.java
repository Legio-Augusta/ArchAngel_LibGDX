package wait4u.littlewing.archangel;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class DrawShopAndBrief {
    private static int SCREEN_HEIGHT = Gdx.graphics.getHeight();

    // 120x160 or 128x128px from original J2ME resolution (in some game). This case screen_width is 240px
    private static int MOBI_SCL = (int)Gdx.graphics.getWidth()/240; // FIXME 4.5 is not integer
    private static int MOBI_H = 360;  // JavaME height = 320px

    private static int VIEW_PORT_HEIGHT = (int)SCREEN_HEIGHT*3/4;
    private static int TOP_BOUND = VIEW_PORT_HEIGHT + (int)SCREEN_HEIGHT/8;
    private static int BOTTOM_SPACE = (int)SCREEN_HEIGHT/8; // May be change for fit touch button

    private Texture[] imgColor; // For fillRect with color; TODO color constant and add remain color png

    public DrawShopAndBrief() {
        imgColor = new Texture[6];
        for (int i = 0; i < 6; i++) {
            imgColor[i] = new Texture("samsung-white/color-" + i + ".png");
        }
    }

    public void loadBrief1(SpriteBatch paramGraphics, int o, int p, ArchAngelME archAngel)
    {
        int i1;
        switch (archAngel.game_state)
        {
            case 0:
                archAngel.readMedia.readMediaStream("font");
                archAngel.readMedia.reloadImageArr(5, 5);
                archAngel.readMedia.closeInputStream();
                archAngel.readMedia.readMediaStream("end");
                for (i1 = 0; i1 < 2; i1++) {
                    archAngel.readMedia.reloadImageArr(i1, 31 + i1);
                }
                archAngel.readMedia.closeInputStream();
                archAngel.game_state += 1;
                break;
            case 1:
                if (archAngel.x == 0)
                {
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
                    archAngel.readMedia.drawImageAnchor20(paramGraphics, 4, 0, 60);
                    archAngel.readMedia.drawImageAnchor20(paramGraphics, 3, 0, 280);
                    archAngel.readMedia.drawImageAnchor20(paramGraphics, 31, 0, 80);
                    archAngel.readMedia.drawImageAnchor20(paramGraphics, 32, 0, 175);
                    archAngel.drawImage(paramGraphics);
                    archAngel.a(paramGraphics, "SKIP", true);
                    archAngel.a(paramGraphics, "NEXT", false);
                    archAngel.readText.readTextFromStream("end");
                }
                archAngel.game_state = 11;
                break;
            case 15:
                if (archAngel.x < 21)
                {
                    // paramGraphics.setColor(0);
                    fillRect(paramGraphics, 0, 60, 240, 240, 4);
                    // paramGraphics.setColor(4802901);
                    fillRect(paramGraphics, 0, 119, 240, 30, 4);
                    fillRect(paramGraphics, 0, 171, 240, 10, 4);
                    fillRect(paramGraphics, 0, 205, 240, 30, 4);
                    // paramGraphics.setClip(0, 80 + 5 * archAngel.x, 240, 200 - 10 * archAngel.x);
                    archAngel.readMedia.drawImageAnchor20(paramGraphics, 31, 0, 80);
                    archAngel.readMedia.drawImageAnchor20(paramGraphics, 32, 0, 175);
                    // paramGraphics.setClip(0, 0, 240, 320);
                    archAngel.readMedia.drawImageAnchor20(paramGraphics, 4, 0, 60 + 5 * archAngel.x);
                    archAngel.readMedia.drawImageAnchor20(paramGraphics, 3, 0, 280 - 5 * archAngel.x);
                }
                if (archAngel.x == 21)
                {
                    archAngel.readMedia.destroyImage(31);
                    archAngel.readMedia.destroyImage(32);
                    archAngel.readMedia.readMediaStream("select");
                    for (i1 = 0; i1 < 7; i1++) {
                        archAngel.readMedia.reloadImageArr(i1, 6 + i1);
                    }
                    archAngel.readMedia.closeInputStream();
                    archAngel.screen = 3;
                }
                break;
        }
        if (archAngel.game_state < 15)
        {
            // paramGraphics.setColor(0);
            fillRect(paramGraphics, 0, 227, 240, 53, 4);
            // paramGraphics.setColor(16777130);
            // paramGraphics.drawRect(0, 227, 239, 53);
            simple_arm_helper(paramGraphics, archAngel.game_state, 228, o, p, archAngel);
        }
    }

    public void simple_arm_helper(SpriteBatch paramGraphics, int paramInt1, int paramInt2, int o, int p, ArchAngelME archAngel)
    {
        o = paramInt2;
        p = paramInt1;
        machineShopLogic(paramGraphics, paramInt1, paramInt2, archAngel);
    }

    public void machineShopLogic(SpriteBatch paramGraphics, int paramInt1, int paramInt2, ArchAngelME archAngel)
    {
        int i1 = paramInt2;
        int i2 = 14;
        if (archAngel.screen == 7) {
            i2 = 30;
        }
        archAngel.readText.processTxt(paramInt1);
        archAngel.readText.b = 0;
        archAngel.p = archAngel.readText.o;
        String str;
        while ((str = archAngel.readText.buildString()) != null)
        {
            if ((archAngel.screen == 26) || (archAngel.screen == 27)) {
                // paramGraphics.setColor(16777215);
            } else {
                // paramGraphics.setColor(0);
            }
            int i4 = str.indexOf(".");
            if (i4 == 1)
            {
                int i3;
                try
                {
                    i3 = Integer.parseInt(str.substring(0, i4));
                }
                catch (Exception localException)
                {
                    i3 = -1;
                }
                if (i3 > archAngel.readText.b) {
                    archAngel.readText.b = i3;
                }
                if (i3 == archAngel.readText.a) {
                    // paramGraphics.setColor(14361600);
                }
            }
            if (archAngel.screen == 13)
            {
                // paramGraphics.drawString(str, i2, i1 + 8, 20);
                archAngel.readMedia.drawStringGraphic(paramGraphics, i2, i1 + 8, str, 0);
            }
            else
            {
                if (archAngel.screen != 7) {
                    // paramGraphics.drawString(str, i2, i1, 20);
                    archAngel.readMedia.drawStringGraphic(paramGraphics, i2, i1, str, 0);
                }
                if ((archAngel.screen == 10) && (paramInt1 == 51) && ((archAngel.readText.a <= 2) || (archAngel.readText.a == 4))) {
                    // paramGraphics.setColor(0);
                }
            }
            if (archAngel.screen == 13)
            {
                if (archAngel.readText.a == 2) {
                    // paramGraphics.setColor(0);
                } else {
                    // paramGraphics.setColor(40960);
                }
                // paramGraphics.drawString(archAngel.on_off[(1 - archAngel.af)], 83, 119, 20);
                archAngel.readMedia.drawStringGraphic(paramGraphics, 83, 119, archAngel.on_off[(1-archAngel.af)], 20); // int3 not use (it should be color or something
            }
            i1 += 17;
        }
        if (archAngel.bool_s)
        {
            // paramGraphics.setColor(0);
            // paramGraphics.drawString("1.YES   2.NO", 88, 105, 17);
            archAngel.readMedia.drawStringGraphic(paramGraphics, 88, 105, "1.YES   2.NO", 17);
        }
        archAngel.bool_g = false;
    }

    public void displayGameOver(SpriteBatch paramGraphics, ArchAngelME archAngel)
    {
        int i1;
        switch (archAngel.game_state)
        {
            case 0:
                if (archAngel.x == 0)
                {
                    archAngel.c = 0;
                    archAngel.readMedia.readMediaStream("font");
                    archAngel.readMedia.reloadImageArr(1, 1);
                    archAngel.readMedia.closeInputStream();
                    archAngel.drawImage(paramGraphics);
                    archAngel.a(paramGraphics, "OK", false);
                    archAngel.bool_s = false;
                }
                else
                {
                    archAngel.game_state = 1;
                }
                break;
            case 1:
                // paramGraphics.setColor(107, 222, 255); // fade-light blue
                fillRect(paramGraphics, 45, 93, 150, 33, 3);
                archAngel.readMedia.drawGraphicStr40_122(paramGraphics, 62, 103, "YOU ARE DEAD");
                // paramGraphics.setColor(16777215);
                fillRect(paramGraphics, 45, 126, 150, 107, 5);
                // paramGraphics.setColor(16777215);
                fillRect(paramGraphics, 45, 126, 150, 107, 5);
                archAngel.readMedia.drawGraphicStr40_122(paramGraphics, 50, 129, "You have been");
                archAngel.readMedia.drawGraphicStr40_122(paramGraphics, 50, 144, "hit deadly.");
                // paramGraphics.setColor(0);
                fillRect(paramGraphics, 49, 172 + archAngel.c * 15, 75, 15, 4);
                archAngel.readMedia.drawGraphicStr40_122(paramGraphics, 50, 174, "1. Retry");
                archAngel.readMedia.drawGraphicStr40_122(paramGraphics, 50, 189, "2. Back");
                break;
            case 2:
                for (i1 = 11; i1 < 19; i1++) {
                    archAngel.readMedia.destroyImage(24 + i1);
                }
                archAngel.readMedia.destroyImage7_53();
                archAngel.readMedia.destroyImage53_115();
                archAngel.screen = 25;
                break;
            case 3:
                if (archAngel.x < 16)
                {
                    if (archAngel.x == 0)
                    {
                        for (i1 = 11; i1 < 19; i1++) {
                            archAngel.readMedia.destroyImage(24 + i1);
                        }
                        archAngel.readMedia.destroyImage7_53();
                        archAngel.readMedia.destroyImage53_115();
                    }
                    // paramGraphics.setColor(0);
                    fillRect(paramGraphics, 0, 0, 240, archAngel.x * 10, 4);
                    fillRect(paramGraphics, 0, 300 - archAngel.x * 10, 240, archAngel.x * 10, 4);
                }
                if (archAngel.x == 16) {
                    archAngel.screen = 5;
                }
                break;
        }
    }

    public void drawGameMenu(SpriteBatch paramGraphics, int l, ArchAngelME archAngel)
    {
        int i1;
        switch (archAngel.game_state)
        {
            case 0:
                if (archAngel.x < 21)
                {
                    // paramGraphics.setColor(0);
                    fillRect(paramGraphics, 0, 60, 240, 240, 4);
                    // paramGraphics.setColor(4802901);
                    fillRect(paramGraphics, 0, 119, 240, 30, 4);
                    fillRect(paramGraphics, 0, 171, 240, 10, 4);
                    fillRect(paramGraphics, 0, 205, 240, 30, 4);
                    archAngel.readMedia.drawImageAnchor20(paramGraphics, 4, 0, 160 - 5 * archAngel.x);
                    archAngel.readMedia.drawImageAnchor20(paramGraphics, 3, 0, 180 + 5 * archAngel.x);
                }
                if (archAngel.x == 20)
                {
                    archAngel.drawImage(paramGraphics);
                    archAngel.game_state += 1;
                }
                break;
            case 1: // new game/saved game
                if (archAngel.x < 22)
                {
                    // paramGraphics.setColor(0);
                    fillRect(paramGraphics, 0, 80, 240, 200, 4);
                    // paramGraphics.setColor(4802901);
                    fillRect(paramGraphics, 0, 119, 240, 30, 4);
                    fillRect(paramGraphics, 0, 171, 240, 10, 4);
                    fillRect(paramGraphics, 0, 205, 240, 30, 4);
                    // TODO sync back to J2ME, investigate idx 6 for image sprite
                    archAngel.readMedia.drawImageAnchor20(paramGraphics, 6, -210 + archAngel.x * 10, 118);
                    // select1 or Bg0
                    archAngel.readMedia.drawImageAnchor20(paramGraphics, 7, 247 - archAngel.x * 10, 204);
                    // Game menu selects; Index reserved to Backgrounds;
                    archAngel.readMedia.drawImageAnchor20(paramGraphics, 9, -148 + archAngel.x * 10, 125);
                    archAngel.readMedia.drawImageAnchor20(paramGraphics, 10, 266 - archAngel.x * 10, 211);
                }
                if (archAngel.x == 21) {
                    archAngel.game_state += 1;
                }
                break;
            case 2: // highlight new game select
                // Backgrounds (no) new game scene
                archAngel.readMedia.drawImageAnchor20(paramGraphics, 6, 0, 118);
                archAngel.readMedia.drawImageAnchor20(paramGraphics, 7, 37, 204);
                archAngel.readMedia.drawImageAnchor20(paramGraphics, 8, 41, 122 + l * 86);
                archAngel.readMedia.drawImageAnchor20(paramGraphics, 9, 62, 125);
                archAngel.readMedia.drawImageAnchor20(paramGraphics, 10, 56, 211);
                archAngel.drawImage(paramGraphics);
                archAngel.a(paramGraphics, "OK", false);
                archAngel.readMedia.drawImageAnchor20(paramGraphics, 3, 0, 280);
                archAngel.bool_w = true;
                break;
            case 3:
                // paramGraphics.setColor(0);
                fillRect(paramGraphics, 0, 80, 240, 200, 4);
                // paramGraphics.setColor(4802901);
                fillRect(paramGraphics, 0, 119, 240, 30, 4);
                fillRect(paramGraphics, 0, 171, 240, 10, 4);
                fillRect(paramGraphics, 0, 205, 240, 30, 4);
                if (archAngel.x < 21)
                {
                    // Backgrounds
                    archAngel.readMedia.drawImageAnchor20(paramGraphics, 6, 0 - archAngel.x * 10, 118);
                    archAngel.readMedia.drawImageAnchor20(paramGraphics, 7, 37 + archAngel.x * 10, 204);
                    archAngel.readMedia.drawImageAnchor20(paramGraphics, 9, 62 - archAngel.x * 10, 125);
                    archAngel.readMedia.drawImageAnchor20(paramGraphics, 10, 56 + archAngel.x * 10, 211);
                }
                else if (archAngel.x < 43)
                {
                    archAngel.readMedia.drawImageAnchor20(paramGraphics, 6, -210 + (archAngel.x - 21) * 10, 118);
                    archAngel.readMedia.drawImageAnchor20(paramGraphics, 7, 247 - (archAngel.x - 21) * 10, 204);
                    archAngel.readMedia.drawImageAnchor20(paramGraphics, 11, -155 + (archAngel.x - 21) * 10, 125);
                    archAngel.readMedia.drawImageAnchor20(paramGraphics, 12, 265 - (archAngel.x - 21) * 10, 211);
                }
                if (archAngel.x == 42) {
                    archAngel.game_state += 1;
                }
                break;
            case 4: // easy/hard mode
                archAngel.readMedia.drawImageAnchor20(paramGraphics, 6, 0, 118);
                archAngel.readMedia.drawImageAnchor20(paramGraphics, 7, 37, 204);
                archAngel.readMedia.drawImageAnchor20(paramGraphics, 8, 41, 122 + archAngel.ah * 86);
                archAngel.readMedia.drawImageAnchor20(paramGraphics, 11, 55, 125);
                // Easy/Hard mode
                archAngel.readMedia.drawImageAnchor20(paramGraphics, 12, 55, 211);
                archAngel.drawImage(paramGraphics);
                archAngel.a(paramGraphics, "OK", false);
                archAngel.readMedia.drawImageAnchor20(paramGraphics, 3, 0, 280);
                break;
            case 5: // game mode selected => close modal
                if (archAngel.x < 21)
                {
                    // paramGraphics.setColor(0);
                    fillRect(paramGraphics, 0, 60, 240, 240, 4);
                    // paramGraphics.setColor(4802901); // dark-gray
                    fillRect(paramGraphics, 0, 119, 240, 30, 4);
                    fillRect(paramGraphics, 0, 171, 240, 10, 4);
                    fillRect(paramGraphics, 0, 205, 240, 30, 4);
                    // paramGraphics.setClip(0, 80 + 5 * archAngel.x, 240, 200 - 10 * archAngel.x);
                    archAngel.readMedia.drawImageAnchor20(paramGraphics, 6, 0, 118);
                    archAngel.readMedia.drawImageAnchor20(paramGraphics, 7, 37, 204);
                    archAngel.readMedia.drawImageAnchor20(paramGraphics, 8, 41, 122 + archAngel.ah * 86);
                    archAngel.readMedia.drawImageAnchor20(paramGraphics, 11, 55, 125);
                    archAngel.readMedia.drawImageAnchor20(paramGraphics, 12, 55, 211);
                    // paramGraphics.setClip(0, 0, 240, 320);
                    archAngel.readMedia.drawImageAnchor20(paramGraphics, 4, 0, 60 + 5 * archAngel.x);
                    archAngel.readMedia.drawImageAnchor20(paramGraphics, 3, 0, 280 - 5 * archAngel.x);
                }
                if (archAngel.x == 20)
                {
                    for (i1 = 0; i1 < 7; i1++) {
                        // Destroy all menu images (idx 6 to 12), these index is reserved for background used later
                        archAngel.readMedia.destroyImage(6 + i1);
                    }
                    archAngel.screen = 26;
                }
                break;
            case 6:
                if (archAngel.x < 20)
                {
                    archAngel.bool_w = false;
                    // paramGraphics.setColor(0);
                    fillRect(paramGraphics, 41, 208, 160, 26, 4);
                    archAngel.readMedia.drawStringGraphic(paramGraphics, 47, 218, "A SAVED GAME IS NOT FOUND.", 0);
                }
                if (archAngel.x == 20) {
                    archAngel.game_state = 2;
                }
                break;
            case 7:
                for (i1 = 0; i1 < 7; i1++) {
                    archAngel.readMedia.destroyImage(6 + i1);
                }
                archAngel.bool_k = true;
                archAngel.d();
                archAngel.gameSetting.initSetting();
                archAngel.screen = 9;
                l = 0;
                break;
        }
    }

    public void briefOpen(SpriteBatch paramGraphics, int o, int p, ArchAngelME archAngel)
    {
        switch (archAngel.game_state)
        {
            case 0:
                if (archAngel.x < 21)
                {
                    // paramGraphics.setColor(0);
                    fillRect(paramGraphics, 0, 60, 240, 240, 4);
                    // paramGraphics.setColor(4802901);
                    fillRect(paramGraphics, 0, 119, 240, 30, 4);
                    fillRect(paramGraphics, 0, 171, 240, 10, 4);
                    fillRect(paramGraphics, 0, 205, 240, 30, 4);
                    archAngel.readMedia.drawImageAnchor20(paramGraphics, 4, 0, 160 - 5 * archAngel.x);
                    archAngel.readMedia.drawImageAnchor20(paramGraphics, 3, 0, 180 + 5 * archAngel.x);
                    // paramGraphics.setClip(0, 180 - 5 * archAngel.x, 240, 10 * archAngel.x);
                    archAngel.readMedia.drawImageAnchor20(paramGraphics, 29, 0, 80);
                }
                if (archAngel.x == 20) {
                    archAngel.game_state += 1;
                }
                break;
            case 1:
                if (archAngel.x == 0) {
                    archAngel.readText.readTextFromStream("open");
                }
                archAngel.game_state = 11;
                break;
            case 18:
                if (archAngel.x < 21)
                {
                    // paramGraphics.setColor(0);
                    fillRect(paramGraphics, 0, 60, 240, 240, 4);
                    // paramGraphics.setColor(4802901); // dark-gray
                    fillRect(paramGraphics, 0, 119, 240, 30, 4);
                    fillRect(paramGraphics, 0, 171, 240, 10, 4);
                    fillRect(paramGraphics, 0, 205, 240, 30, 4);
                    // paramGraphics.setClip(0, 80 + 5 * archAngel.x, 240, 200 - 10 * archAngel.x);
                    archAngel.readMedia.drawImageAnchor20(paramGraphics, 29, 0, 80);
                    // paramGraphics.setClip(0, 0, 240, 320);
                    archAngel.readMedia.drawImageAnchor20(paramGraphics, 4, 0, 60 + 5 * archAngel.x);
                    archAngel.readMedia.drawImageAnchor20(paramGraphics, 3, 0, 280 - 5 * archAngel.x);
                }
                if (archAngel.x == 20)
                {
                    archAngel.readMedia.destroyImage(29);
                    archAngel.screen = 2;
                }
                break;
        }
        if (archAngel.game_state < 18)
        {
            // paramGraphics.setColor(0);
            fillRect(paramGraphics, 0, 227, 240, 53, 4);
            // paramGraphics.setColor(16777130);
            // paramGraphics.drawRect(0, 227, 239, 53);
            simple_arm_helper(paramGraphics, archAngel.game_state, 228, o, p, archAngel);
            archAngel.readMedia.drawImageAnchor20(paramGraphics, 3, 0, 280);
        }
        archAngel.drawImage(paramGraphics);
        archAngel.a(paramGraphics, "SKIP", true);
        archAngel.a(paramGraphics, "NEXT", false);
    }

    public void briefAbout(SpriteBatch paramGraphics, int o, int p, int l, ArchAngelME archAngel)
    {
        switch (archAngel.game_state)
        {
            case 0:
                archAngel.readText.readTextFromStream("about");
                archAngel.game_state += 1;
                break;
            case 1:
                archAngel.readMedia.drawImageAnchor20(paramGraphics, 14, 17, 89);
                //paramGraphics.setClip(17, 89, 223, 25);
                archAngel.readMedia.drawImageAnchor20(paramGraphics, 13, 60, 90 - l * 33);
                // paramGraphics.setClip(0, 0, 240, 320);
                // paramGraphics.setColor(7171414); // dark-gray-yellow
                fillRect(paramGraphics, 2, 119, 238, 181, 2);
                // paramGraphics.setColor(9605802);
                // paramGraphics.drawRect(2, 119, 237, 180);
                simple_helper2(paramGraphics, archAngel.game_state, 130, o, p, archAngel);
                archAngel.drawImage(paramGraphics);
                archAngel.a(paramGraphics, "BACK", true);
                break;
        }
    }

    public void simple_helper2(SpriteBatch paramGraphics, int paramInt1, int paramInt2, int o, int p, ArchAngelME archAngel)
    {
        o = paramInt2;
        p = paramInt1;
        draw_str_helper(paramGraphics, paramInt1, paramInt2, archAngel);
    }

    public void draw_str_helper(SpriteBatch paramGraphics, int paramInt1, int paramInt2, ArchAngelME archAngel)
    {
        int i1 = paramInt2;
        int i2 = 5;
        archAngel.readText.processTxt(paramInt1);
        String str;
        while ((str = archAngel.readText.buildString()) != null)
        {
            //return; // Avoid draw string error outOfBound
            // paramGraphics.setColor(16777215);
            if (archAngel.screen == 4) {
                // paramGraphics.drawString(str, 120, i1, 17);
                archAngel.readMedia.drawStringGraphic(paramGraphics, 120, i1, str, 17);
            }
            i1 += 17;
        }
    }

    public void draw_system_setin(SpriteBatch paramGraphics, int l, int o, int p, int q, int t, int x, int y, String[][] str_arr_w, ArchAngelME archAngel, ReadText readText)
    {
        if ((archAngel.game_state > 0) && (archAngel.x > 0)) {
            return;
        }
        switch (archAngel.game_state)
        {
            case 0:
                y = 1;
                archAngel.readMedia.drawImageAnchor20(paramGraphics, 14, 17, 89);
                // paramGraphics.setClip(17, 89, 223, 25);
                archAngel.readMedia.drawImageAnchor20(paramGraphics, 13, 60, 90 - l * 33);
                // paramGraphics.setClip(0, 0, 240, 320);
                if (archAngel.x == 0) {
                    archAngel.readText.readTextFromStream("system");
                }
                simple_helper(paramGraphics, archAngel);
                return;
            case 1:
                archAngel.drawImage(paramGraphics);
                archAngel.a(paramGraphics, "BACK", true);
                archAngel.a(paramGraphics, "OK", false);
                archAngel.readText.bool_c = true;
                archAngel.readText.bool_d = false;
                archAngel.readText.h = 1;
                archAngel.readText.g = y;
                if (archAngel.bool_u) {
                    archAngel.addScore12();
                }
                archAngel.bool_u = false;
                break;
            case 11:
                archAngel.addScore();
                archAngel.game_state = 12;
                break;
            case 100:
            case 200:
            case 300:
                archAngel.drawImage(paramGraphics);
                archAngel.a(paramGraphics, "BACK", true);
                archAngel.a(paramGraphics, "NEXT", false);
                break;
            case 101:
            case 102:
            case 103:
            case 201:
            case 202:
            case 203:
            case 204:
            case 301:
                archAngel.drawImage(paramGraphics);
                archAngel.a(paramGraphics, "PREV", true);
                archAngel.a(paramGraphics, "NEXT", false);
                break;
            case 104:
            case 205:
            case 302:
                archAngel.drawImage(paramGraphics);
                archAngel.a(paramGraphics, "PREV", true);
                archAngel.a(paramGraphics, "OK", false);
                break;
            case 21:
                if (archAngel.e())
                {
                    archAngel.d();
                    archAngel.readText.readTextFromStream("system");
                    archAngel.game_state = 22;
                }
                else
                {
                    archAngel.game_state = 23;
                }
                break;
            case 12:
            case 22:
                archAngel.drawImage(paramGraphics);
                archAngel.a(paramGraphics, "OK", false);
                break;
            case 10:
                archAngel.readText.bool_c = false;
                y = 1;
                break;
            case 20:
                archAngel.readText.bool_c = false;
                y = 2;
                break;
            case 33:
                archAngel.af = (1 - archAngel.af);
                if (archAngel.af == 0) {
                    archAngel.stopSound();
                }
                archAngel.bool_u = true;
            case 30:
                archAngel.readText.bool_c = false;
                y = 3;
                archAngel.game_state = (31 + archAngel.af);
                break;
            case 43:
                archAngel.ag = (1 - archAngel.ag);
                archAngel.bool_u = true;
            case 40:
                archAngel.readText.bool_c = false;
                y = 4;
                archAngel.game_state = (41 + archAngel.ag);
                break;
            case 50:
                archAngel.readText.bool_c = false;
                y = 5;
                archAngel.readText.readTextFromStream("helpmain");
                archAngel.drawImage(paramGraphics);
                archAngel.a(paramGraphics, "BACK", true);
                archAngel.a(paramGraphics, "OK", false);
                archAngel.readText.bool_d = true;
                archAngel.game_state = 61;
                break;
            case 99:
                archAngel.readText.bool_d = false;
                archAngel.readText.h = 1;
                archAngel.readText.readTextFromStream("help0");
                archAngel.game_state += 1;
                break;
            case 199:
                archAngel.readText.bool_d = false;
                archAngel.readText.h = 2;
                archAngel.readText.readTextFromStream("help1");
                archAngel.game_state += 1;
                break;
            case 299:
                archAngel.readText.bool_d = false;
                archAngel.readText.h = 3;
                archAngel.readText.readTextFromStream("help2");
                archAngel.game_state += 1;
                break;
            case 399:
                archAngel.readText.readTextFromStream("help3");
                archAngel.game_state += 1;
                break;
            case 500:
                archAngel.readText.readTextFromStream("system");
                archAngel.game_state = 1;
                if (archAngel.screen != 10) {
                    return;
                }
                break;
            case 70:
                archAngel.screen = 5;
                return;
            case 999:
                archAngel.stopSound();
                archAngel.addScore();
                archAngel.destroyApp(false);
                // archAngel.notifyDestroyed();
                return;
        }
        if (archAngel.bool_w) {
            setup2(paramGraphics, archAngel.game_state, 150, o, p, q, t, x, str_arr_w, archAngel, readText);
        }
    }

    public void simple_helper(SpriteBatch paramGraphics, ArchAngelME archAngel)
    {
        archAngel.game_state += 1;
        archAngel.p = -1;
        archAngel.bool_h = false;
    }

    public void setup2(SpriteBatch paramGraphics, int paramInt1, int paramInt2, int o, int p, int q, int t, int x, String[][] str_arr_w, ArchAngelME archAngel, ReadText readText)
    {
        o = paramInt2;
        p = paramInt1;
        archAngel.bool_w = false;
        draw_arm_shop_menu(paramGraphics, paramInt1, paramInt2, q, t, x, str_arr_w, archAngel, readText);
    }

    public void draw_arm_shop_menu(SpriteBatch paramGraphics, int paramInt1, int paramInt2, int q, int t, int x, String[][] str_arr_w, ArchAngelME archAngel, ReadText readText)
    {
        archAngel.bool_v = false;
        if ((archAngel.screen == 12) && ((paramInt1 == 100) || (paramInt1 == 200) || (paramInt1 == 300)))
        {
            drawDataInTxt(paramGraphics, paramInt1, 130, t, x, str_arr_w, archAngel, readText);
        }
        else
        {
            int i1 = paramInt2;
            int i4 = 10;
            if (archAngel.screen != 7)
            {
                if (archAngel.screen == 13)
                {
                    i4 = 5;
                    // paramGraphics.setColor(4343106); // dark-gray
                    fillRect(paramGraphics, 0, 217, 140, 83, 4);
                    // paramGraphics.setColor(14605311);
                    // paramGraphics.drawRect(0, 217, 139, 82);
                    q = paramInt1;
                }
                else
                {
                    archAngel.readMedia.drawImageAnchor20(paramGraphics, 2, 2, 120);
                }
            }
            else if (archAngel.screen == 7)
            {
                i4 = 18;
                // paramGraphics.setColor(16777215);
                fillRect(paramGraphics, 13, 76, 150, 107, 5);
            }
            else
            {
                i4 = 30;
            }
            //paramGraphics.setColor(40960);
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
                    if (i2 == archAngel.readText.a) {
                        if (archAngel.screen == 13)
                        {
                            // paramGraphics.setColor(83967); // light blue
                            fillRect(paramGraphics, i4 + 15, i1 + 6, str.length() * 5 + 3, 9, 3);
                        }
                        else
                        {
                            // paramGraphics.setColor(0);
                            fillRect(paramGraphics, i4 - 2, i1 - 2, str.length() * 9 + 3, 15, 4);
                        }
                    }
                }
                if (archAngel.screen == 13)
                {
                    archAngel.readMedia.drawStringGraphic(paramGraphics, i4, i1 + 8, str, 0);
                    // paramGraphics.setColor(0);
                    if (archAngel.bool_s)
                    {
                        // paramGraphics.setColor(0);
                        archAngel.readMedia.drawStringGraphic(paramGraphics, i4 + 66, 236, archAngel.on_off[(1 - archAngel.af)], 0);
                        archAngel.readMedia.drawStringGraphic(paramGraphics, i4 + 72, 247, archAngel.auto_manual[(1 - archAngel.ag)], 0);
                    }
                }
                else
                {
                    archAngel.readMedia.drawGraphicStr40_122(paramGraphics, i4, i1, str);
                    if ((archAngel.screen == 10) && (paramInt1 == 51) && ((archAngel.readText.a <= 2) || (archAngel.readText.a == 4))) {
                        // paramGraphics.setColor(0);
                    }
                }
                if (archAngel.screen == 13) {
                    i1 += 11;
                } else {
                    i1 += 15;
                }
            }
            if (archAngel.screen == 11)
            {
                // paramGraphics.setColor(9540180);
                fillRect(paramGraphics, 10, 135, 70, 42, 2);
                archAngel.readMedia.drawImageAnchor20(paramGraphics, 114, 10, 135);
                // paramGraphics.setColor(0);
                archAngel.readMedia.drawGraphicStr40_122(paramGraphics, 12, 180, "My money:" + archAngel.gameSetting.a);
                archAngel.readMedia.drawGraphicStr40_122(paramGraphics, 10, 210, "1.Missile");
                archAngel.readMedia.drawGraphicStr40_122(paramGraphics, 10, 225, "2.Plasma Canon");
                archAngel.readMedia.drawGraphicStr40_122(paramGraphics, 10, 240, "3.Armor");
            }
            archAngel.bool_g = false;
        }
        archAngel.bool_v = true;
    }

    /**
     * Data embeded in text file, like inline/custom Database.
     * @param paramGraphics
     * @param paramInt1
     * @param paramInt2
     */
    public void drawDataInTxt(SpriteBatch paramGraphics, int paramInt1, int paramInt2, int t, int x, String[][] str_arr_w, ArchAngelME archAngel, ReadText readText)
    {
        int i1 = paramInt2;
        b(paramGraphics, paramInt1, x, t, str_arr_w, archAngel, readText);
        archAngel.readText.processTxt(paramInt1);
        archAngel.readText.b = 0;
        archAngel.p = archAngel.readText.o;
        switch (x)
        {
            case 0:
                // paramGraphics.setColor(430330);
                break;
            case 1:
                // paramGraphics.setColor(65280);
                break;
            case 2:
                // paramGraphics.setColor(16776960);
                break;
        }
        String str;
        while ((str = archAngel.readText.buildString()) != null)
        {
            archAngel.readMedia.drawStringGraphic(paramGraphics, 10, i1, str, x);
            // paramGraphics.setClip(0, 0, 240, 300);
            int i3 = str.indexOf("=");
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
                if (i2 == archAngel.readText.a)
                {
                    // paramGraphics.setColor(0);
                    fillRect(paramGraphics, 7, i1 - 2, str.length() * 6 + 6, 9, 4);
                    archAngel.readMedia.drawStringGraphic(paramGraphics, 10, i1, str, 3);
                }
            }
            i1 += 11;
        }
        archAngel.bool_g = false;
    }

    public void b(SpriteBatch paramGraphics, int paramInt, int x, int t, String[][] str_arr_w, ArchAngelME archAngel, ReadText readText)
    {
        if (archAngel.bool_q)
        {
            // paramGraphics.setColor(7171414);
             fillRect(paramGraphics, 6, 127, 226, 147, 2);
            // paramGraphics.setColor(9605802);
            // paramGraphics.drawRect(6, 127, 225, 146);
            // paramGraphics.setColor(9605717);
             fillRect(paramGraphics, 10, 130, 72, 422, 2);
            // paramGraphics.setColor(16777130);
            // paramGraphics.drawRect(10, 129, 72, 43);
            if (archAngel.bool_r)
            {
                draw_ammunation_buy(paramGraphics, 101, true, archAngel.d, x, t, str_arr_w, archAngel, readText);
                archAngel.bool_r = false;
            }
            else if (archAngel.a <= 6)
            {
                draw_ammunation_buy(paramGraphics, archAngel.a + 100, true, archAngel.d, x, t, str_arr_w, archAngel, readText);
            }
            else
            {
                draw_ammunation_buy(paramGraphics, archAngel.a - 1 + 100, true, archAngel.d, x, t, str_arr_w, archAngel, readText);
            }
        }
        archAngel.bool_q = false;
    }

    public void draw_ammunation_buy(SpriteBatch paramGraphics, int paramInt1, boolean paramBoolean, int paramInt2, int x, int t, String[][] str_arr_w, ArchAngelME archAngel, ReadText readText)
    {
        int i3 = 190;
        int i4 = 3;
        String str2 = null;
        int i2 = paramInt1 / 100 - 1;
        int i1 = paramInt1 % 100;
        readText.processTxt(i1);
        String str1 = readText.buildString();
        // paramGraphics.setClip(0, 0, 176, 80);
        switch (paramInt2)
        {
            case 0:
                // paramGraphics.setColor(430330);
                break;
            case 1:
                // paramGraphics.setColor(65280);
                break;
            case 2:
                // paramGraphics.setColor(16776960);
                break;
        }
        // paramGraphics.setClip(0, 0, 240, 320);
        archAngel.readMedia.drawImageSwitch(paramGraphics, 13, 131, 60, 40, i1 - 1, paramInt2);
        switch (paramInt2)
        {
            case 0:
                x = 0;
                str2 = "MISSILE";
                t = archAngel.gameSetting.q;
                break;
            case 1:
                x = 1;
                i2 = 0;
                str2 = "PLASMA CANON";
                t = archAngel.gameSetting.v;
                break;
            case 2:
                x = 2;
                i2 = 1;
                str2 = "ARMOR";
                t = archAngel.gameSetting.l;
                break;
        }
        t += archAngel.gameSetting.a;
        archAngel.readMedia.drawGraphicStr40_122(paramGraphics, 100, 140, str2);
        for (int i5 = 0; i5 < i4; i5++)
        {
            archAngel.readMedia.drawStringGraphic(paramGraphics, 130, i3, str_arr_w[i2][i5] + ":" + readText.int_arr_m[i5], x);
            i3 += 11;
        }
        if (paramBoolean)
        {
            archAngel.readMedia.drawStringGraphic(paramGraphics, 130, 235, "MY MONEY:", x);
            archAngel.readMedia.drawStringGraphic(paramGraphics, 130, 245, "" + archAngel.gameSetting.a, x);
        }
    }

    public void fillRect(SpriteBatch batch, int x, int y, int width, int height, int color) {
        // Hard code default width x height of color img: 12x12 px
        int scaleY = height*MOBI_SCL / 12;
        int scaleX = width*MOBI_SCL / 12;
        // (Texture, float x, float destroy_n_e, float originX, float originY, float width, float height, float scaleX, float scaleY, float rotation, int srcX, int srcY, int srcWidth, int srcHeight, boolean flipX, boolean flipY)
        int pos_x = (int) (MOBI_SCL*x);
        int pos_y = (int) ((MOBI_H - y+20)*MOBI_SCL - imgColor[color].getHeight()*scaleY + BOTTOM_SPACE);

        batch.draw(imgColor[color], pos_x, pos_y, 0, 0, imgColor[color].getWidth(), imgColor[color].getHeight(), scaleX, scaleY, 0, 0, 0, imgColor[color].getWidth()*scaleX, imgColor[color].getHeight()*scaleY, false, false);
    }

}
