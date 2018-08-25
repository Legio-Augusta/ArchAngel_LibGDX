package wait4u.littlewing.archangel;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.Random;

public class MainGameHelper {
    private Texture [] imgColor;

    public MainGameHelper() {
        loadTextures();
    }

    public void loadTextures() {
        /**
         * #0 for red
         * #1 for light blue, #3 light blue 2 6DCFF6
         * #2 for light yellow, #4 gray 93959A #5 for white
         */
        imgColor = new Texture[6];
        for (int i = 0; i < 6; i++) {
            imgColor[i] = new Texture("samsung-white/color-" + i + ".png");
        }
    }

    public void init_600(int[][] int_arr_bu)
    {
        for (int i1 = 0; i1 < 2; i1++)
        {
            int_arr_bu[i1][0] = (600 * i1 * 2);
            int_arr_bu[i1][1] = (600 * (i1 * 2 + 1));
        }
    }

    public int turn_calc(int paramInt1, int paramInt2)
    {
        int i1 = paramInt2 - paramInt1;
        if (i1 > 180) {
            i1 -= 360;
        }
        if (i1 < -180) { // TODO sync back to EclipME
            i1 = 360 + i1;
        }
        return i1;
    }

    public int angle_helper(int paramInt1, int paramInt2, byte[] stt_byte_arr_bt)
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

    public boolean config_helper(GameConfig paramgcnf1, GameConfig paramgcnf2)
    {
        if (Math.abs(paramgcnf1.boss_distance_1 - paramgcnf2.boss_distance_1) + Math.abs(paramgcnf1.boss_distance_2 - paramgcnf2.boss_distance_2) < 200)
        {
            paramgcnf2.m -= paramgcnf1.m;
            paramgcnf2.l += 5;
            if (paramgcnf2.m <= 0) {
                if (paramgcnf2.c == 14)
                {
                    paramgcnf2.c = 7;
                    paramgcnf2.l = 4;
                }
                else
                {
                    paramgcnf2.c = 8;
                    paramgcnf2.l = 2;
                }
            }
            return true;
        }
        return false;
    }

    // int
    public ReturnHelper random_helper(GameConfig paramg, int paramInt, Random rnd, int af, int al, int am, int an, int ao, int bo,
                             int bp, int bq, int br, byte[] stt_byte_arr_bt, byte[] stt_byte_arr_bs)
    {
        ReturnHelper randomReturn = new ReturnHelper();

        int i3 = 8;
        int i4;
        int i1;
        if (paramg.e > 4000)
        {
	        // i4 = angle_helper(-paramg.boss_distance_1, -paramg.screen);
            i4 = angle_helper(-paramg.boss_distance_1, -paramg.boss_distance_2, stt_byte_arr_bt);
            i1 = 20;
        }
        else
        {
            i1 = (Math.abs(rnd.nextInt()) & 0x7) + 5;
            int i2 = Math.abs(rnd.nextInt() & 0x7);
            i3 = Math.abs(paramg.e - 3500) >> 8;
            if (i2 >= i3)
            {
                if (af < 1) {
                    setup_18_item(12, paramInt);
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
        ReturnHelper returnHelper = turn_helper(i4, bo, bp, stt_byte_arr_bs);
        bo = (returnHelper.one > returnHelper.MIN_INT) ? returnHelper.one : bo;
        bp = (returnHelper.two > returnHelper.MIN_INT) ? returnHelper.two : bp;

        paramg.h = (al - an + bp);
        // paramg.i = (am - ao + turn_helper2(i4, bq, br, stt_byte_arr_bs));
        ReturnHelper turnReturn = turn_helper2(i4, bq, br, stt_byte_arr_bs);
        bq = (turnReturn.one > turnReturn.MIN_INT) ? turnReturn.one : bq;
        br = (turnReturn.two > turnReturn.MIN_INT) ? turnReturn.two : br;
        paramg.i = (am - ao + br);
        paramg.j = i4;

        randomReturn.one = bo;
        randomReturn.two = bp;
        randomReturn.three = bq;
        randomReturn.four = br;
        // return i1;
        randomReturn.five = i1;
        return randomReturn;
    }

    // int
    public ReturnHelper shift_byte_6(int paramInt1, int paramInt2, int paramInt3, int bo, int bp, int bq, int br, byte[] stt_byte_arr_bs)
    {
        ReturnHelper byteReturn = new ReturnHelper();

        ReturnHelper turnReturn = turn_helper2(paramInt1, bq, br, stt_byte_arr_bs);
        byteReturn.three = turnReturn.one;
        byteReturn.four = turnReturn.two;

        ReturnHelper returnHelper2 = turn_helper(paramInt1, bo, bp, stt_byte_arr_bs);
        byteReturn.one = returnHelper2.one;
        byteReturn.two = returnHelper2.two;

        byteReturn.five =
         returnHelper2.two * paramInt2 - turnReturn.two * paramInt3 >> 6; // return

        return byteReturn;
    }

    // void; seem AI function
    public ReturnHelper draw_radar_dot(SpriteBatch paramGraphics, GameConfig paramg, int al, int am, int av, int bo, int bp, int bq, int br, byte[] stt_byte_arr_bs)
    {
        ReturnHelper arrReturn = new ReturnHelper();

        int[] arrayOfInt = { 255, 16711680, 16776960, 16776960 }; // 255 ~ blue; 16711680 ~ red; 16776960 ~ yellow
        // This function seem only draw radar dot red for missile, yellow for enermy ?
        int i5 = paramg.c;
        int i3 = paramg.boss_distance_1;
        int i4 = paramg.boss_distance_2;
        // int i1 = shift_byte_6(450 - av, i3, i4, bo, bp, bq, br, stt_byte_arr_bs);
        ReturnHelper shiftReturn = shift_byte_6(450 - av, i3, i4, bo, bp, bq, br, stt_byte_arr_bs);
        int i1 = shiftReturn.five;
        bo = (shiftReturn.one > shiftReturn.MIN_INT) ? shiftReturn.one : bo;
        bp = (shiftReturn.two > shiftReturn.MIN_INT) ? shiftReturn.two : bp;
        bq = (shiftReturn.three > shiftReturn.MIN_INT) ? shiftReturn.three : bq;
        br = (shiftReturn.four > shiftReturn.MIN_INT) ? shiftReturn.four : br;

        // int i2 = return_turn_helper(450 - av, i3, i4, bo, bp, bq, br, stt_byte_arr_bs);
        ReturnHelper returnHelper2 = return_turn_helper(450 - av, i3, i4, bo, bp, bq, br, stt_byte_arr_bs);
        int i2 = returnHelper2.five;
        bo = (returnHelper2.one > returnHelper2.MIN_INT) ? returnHelper2.one : bo;
        bp = (returnHelper2.two > returnHelper2.MIN_INT) ? returnHelper2.two : bp;
        bq = (returnHelper2.three > returnHelper2.MIN_INT) ? returnHelper2.three : bq;
        br = (returnHelper2.four > returnHelper2.MIN_INT) ? returnHelper2.four : br;

        if ((i5 >= 11) && ((paramg.e = shift_3(i3, i4)) < 4284))
        {
            i3 = i1 >> 8;
            i4 = i2 >> 8;
            if( (i5 - 11) < arrayOfInt.length) {
                // paramGraphics.setColor(arrayOfInt[(i5 - 11)]);
            }
            // Enermy dot yellow for fighter, red for missile
            fillRect(paramGraphics, 28 + i3, 21 - i4, 3, 3, 2);
        }
        i2 += 300;
        int i6 = i2 + 151;
        if (i6 > 0)
        {
            paramg.g = (20000 / i6);
            paramg.f = (i1 * 151 / i6);
            paramg.d = (7 - (i2 >> 9));
        }
        else
        {
            paramg.d = 8;
        }
        paramg.boss_distance_1 -= al; // -1 too slow; but *1000 -> error boss disapear n can not pass first boss scene
        // may be this is boss hp and used in somewhere
        if(paramg.boss_distance_2 < 0) {
            paramg.boss_distance_2 -= am*-1000; // 20, 10; combined other calc => distance -189 per loop
        }
        // *-100000 -> error null img[108]-plasma and not pass scene 1
        // -> this similar as al has some other usage.
        // or -100 000 too big n distance_2 drop too fast to MIN_INT

        arrReturn.one = bo;
        arrReturn.two = bp;
        arrReturn.three = bq;
        arrReturn.four = br;

        return arrReturn;
    }

    public int shift_3(int paramInt1, int paramInt2)
    {
        int i1 = 0;
        if (paramInt1 < 0) {
            paramInt1 = -paramInt1;
        }
        if (paramInt2 < 0) {
            paramInt2 = -paramInt2;
        }
        if (paramInt1 + paramInt2 == 0) {
            return 0;
        }
        if (paramInt1 > paramInt2) {
            i1 = (18 - (paramInt1 << 3) / (paramInt1 + paramInt2)) * paramInt1;
        } else {
            i1 = (18 - (paramInt2 << 3) / (paramInt1 + paramInt2)) * paramInt2;
        }
        return i1 / 10;
    }

    public int turnAngleCalc(int paramInt,  byte[] stt_byte_arr_bs)
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
            } else {
                return stt_byte_arr_bs[89];
            }
        }
        if ((paramInt > 90) && (paramInt < 180)) { // >=90
            // Why in some app section, bound of array is out by 1 like in e.java i1++ (line 52)
            // This bs byte array is 90 in length, so index of it has maximum value equal 89.
            // May be this way to trick/optimize processText custom compiler for old Java mobile devices ?
            // Or decompiler resulted in defect
            return -stt_byte_arr_bs[(180 - paramInt)];
        }
        if ((paramInt == 90) && (paramInt < 180)) { // dungnv
            return -stt_byte_arr_bs[89];
        }

        if ((paramInt > 180) && (paramInt < 270)) { // >=180
            return -stt_byte_arr_bs[(paramInt - 180)];
        }
        if ((paramInt == 180) && (paramInt < 270)) { // dungnv
            return -stt_byte_arr_bs[89];
        }
        if ( (360 - paramInt) < stt_byte_arr_bs.length ) {
            return stt_byte_arr_bs[(360 - paramInt)];
        } else {
            return stt_byte_arr_bs[89];
        }
    }

    public ReturnHelper turn_helper(int paramInt, int bo, int bp, byte[] stt_byte_arr_bs) // int
    {
        ReturnHelper returnHelper = new ReturnHelper();
        if (paramInt == bo) {
            // return bp;
            returnHelper.two = bp;
            return returnHelper;
        }
        returnHelper.one = paramInt; // bo = paramInt;
        returnHelper.two = turnAngleCalc(paramInt, stt_byte_arr_bs); // return bp =

        return returnHelper;
    }

    public ReturnHelper turn_helper2(int paramInt, int bq, int br, byte[] stt_byte_arr_bs) // int
    {
        ReturnHelper turnReturn = new ReturnHelper();
        if (paramInt == bq) {
            // return br;
            turnReturn.two = br;
            return turnReturn;
        }
        turnReturn.one = paramInt; // bq = paramInt;
        turnReturn.two = turnAngleCalc(90 - paramInt, stt_byte_arr_bs); // return br =

        return turnReturn;
    }

    //	public void follow_boss(int paramInt)
    public ReturnHelper follow_boss(int paramInt, int bb, int bd, int x, boolean bool_bg, int[] int_arr_a5, boolean[] bool_arr_a7 )
    {
        ReturnHelper bossReturn = new ReturnHelper();
        int i1;
        if (!bool_bg)
        {
            if (paramInt == 2)
            {
                bd += 1;
                int_arr_a5[0] -= 4;
                if (int_arr_a5[0] < -240) {
                    int_arr_a5[0] = 0;
                }
                if (int_arr_a5[0] < 0) {
                    bool_arr_a7[0] = false;
                }
                int_arr_a5[1] -= 6;
                if (int_arr_a5[1] < -340) {
                    int_arr_a5[1] = 240;
                }
                for (i1 = 0; i1 < 3; i1++)
                {
                    int_arr_a5[(i1 + 2)] -= 6 - 2 * i1;
                    if (int_arr_a5[(i1 + 2)] < -240) {
                        int_arr_a5[(i1 + 2)] = 0;
                    }
                    if (int_arr_a5[(i1 + 2)] < 0) {
                        bool_arr_a7[(i1 + 1)] = false;
                    }
                }
            }
            else if (paramInt == 1)
            {
                bd += 1;
                int_arr_a5[0] += 4;
                if (int_arr_a5[0] > 240) {
                    int_arr_a5[0] = 0;
                }
                if (int_arr_a5[0] > 0) {
                    bool_arr_a7[0] = true;
                }
                int_arr_a5[1] += 6;
                if (int_arr_a5[1] > 240) {
                    int_arr_a5[1] = -340;
                }
                for (i1 = 0; i1 < 3; i1++)
                {
                    int_arr_a5[(i1 + 2)] += 6 - 2 * i1;
                    if (int_arr_a5[(i1 + 2)] > 240) {
                        int_arr_a5[(i1 + 2)] = 0;
                    }
                    if (int_arr_a5[(i1 + 2)] > 0) {
                        bool_arr_a7[(i1 + 1)] = true;
                    }
                }
            }
            if (paramInt == 4)
            {
                bd += 1;
                int_arr_a5[0] -= 4;
                if (int_arr_a5[0] < -240) {
                    int_arr_a5[0] = 0;
                }
                if (int_arr_a5[0] < 0) {
                    bool_arr_a7[0] = false;
                }
                int_arr_a5[1] -= 2;
                if (int_arr_a5[1] < -340) {
                    int_arr_a5[1] = 240;
                }
                for (i1 = 0; i1 < 3; i1++)
                {
                    int_arr_a5[(i1 + 2)] -= 2 + 2 * i1;
                    if (int_arr_a5[(i1 + 2)] < -240) {
                        int_arr_a5[(i1 + 2)] = 0;
                    }
                    if (int_arr_a5[(i1 + 2)] < 0) {
                        bool_arr_a7[(i1 + 1)] = false;
                    }
                }
            }
            else if (paramInt == 3)
            {
                bd += 1;
                int_arr_a5[0] += 4;
                if (int_arr_a5[0] > 240) {
                    int_arr_a5[0] = 0;
                }
                if (int_arr_a5[0] > 0) {
                    bool_arr_a7[0] = true;
                }
                int_arr_a5[1] += 2;
                if (int_arr_a5[1] > 240) {
                    int_arr_a5[1] = -340;
                }
                for (i1 = 0; i1 < 3; i1++)
                {
                    int_arr_a5[(i1 + 2)] += 2 + 2 * i1;
                    if (int_arr_a5[(i1 + 2)] > 240) {
                        int_arr_a5[(i1 + 2)] = 0;
                    }
                    if (int_arr_a5[(i1 + 2)] > 0) {
                        bool_arr_a7[(i1 + 1)] = true;
                    }
                }
            }
            else if (paramInt > 0)
            {
                bd += 1;
            }
        }
        else if (paramInt == 2)
        {
            if (bb >= 133)
            {
                x -= 2;
                if (x < -10) {
                    x = -10;
                }
                int_arr_a5[0] -= 2;
                if (int_arr_a5[0] < -240) {
                    int_arr_a5[0] = 0;
                }
                if (int_arr_a5[0] < 0) {
                    bool_arr_a7[0] = false;
                }
                int_arr_a5[1] -= 1;
                if (int_arr_a5[1] < -340) {
                    int_arr_a5[1] = 240;
                }
                for (i1 = 0; i1 < 3; i1++)
                {
                    int_arr_a5[(i1 + 2)] -= 2 + 1 * i1;
                    if (int_arr_a5[(i1 + 2)] < -240) {
                        int_arr_a5[(i1 + 2)] = 0;
                    }
                    if (int_arr_a5[(i1 + 2)] < 0) {
                        bool_arr_a7[(i1 + 1)] = false;
                    }
                }
            }
        }
        else if ((paramInt == 1) && (bb <= 53))
        {
            x += 2;
            if (x > 10) {
                x = 10;
            }
            int_arr_a5[0] += 2;
            if (int_arr_a5[0] > 240) {
                int_arr_a5[0] = 0;
            }
            if (int_arr_a5[0] > 0) {
                bool_arr_a7[0] = true;
            }
            int_arr_a5[1] += 1;
            if (int_arr_a5[1] > 240) {
                int_arr_a5[1] = -340;
            }
            for (i1 = 0; i1 < 3; i1++)
            {
                int_arr_a5[(i1 + 2)] += 2 + 1 * i1;
                if (int_arr_a5[(i1 + 2)] > 240) {
                    int_arr_a5[(i1 + 2)] = 0;
                }
                if (int_arr_a5[(i1 + 2)] > 0) {
                    bool_arr_a7[(i1 + 1)] = true;
                }
            }
        }

        bossReturn.one = bd;
        bossReturn.two = x;
        return bossReturn;
    }

    // FIXME can not change speed of fighter
    public void simple_helper2(int paramInt, int a8, boolean bool_bg)
    {
        if (!bool_bg) {
            if (paramInt == 1)
            {
                a8 += 1;
                if (a8 > 4) {
                    a8 = 4;
                }
            }
            else if (paramInt == 2)
            {
                a8 += -1;
                if (a8 < 0) {
                    a8 = 0;
                }
            }
        }
    }

    // FIXME
    public boolean setup_18_item(int paramInt1, int paramInt2)
    {
        return true;
    }

    // public void complex_draw_helper(SpriteBatch paramGraphics, GameConfig paramg)
    // These int params is original from this (MainGameScreen)
    // Be careful with param pass by reference ie. aa_bool_n here is not change value, only used for condition
    public ReturnHelper complex_draw_helper(SpriteBatch paramGraphics, GameConfig paramg, ReadMedia readMedia, int af,
                                    int b0, int b1, int b2, int b3, int bb, int bc, int be, int bf,
                                    int bi, int bj, int bk, int by, int bz, boolean aa_bool_n, Random rnd)
    {
        ReturnHelper complexReturn = new ReturnHelper();

        int i1 = paramg.c;
        int i2 = paramg.f;
        int i3 = paramg.g;
        int i4 = paramg.d;
        // paramGraphics.setClip(0, 50, 240, 250);
        switch (i1)
        {
            case 9:
                break;
            case 10:
                if (i4 < 8) {
                    readMedia.drawImageInArr(paramGraphics, 44 + i4, i2 + 88 + 32, 163 + i3);
                }
                break;
            case 13:
                if (i4 < 8) {
                    readMedia.drawImageInArr(paramGraphics, 53 + i4, i2 + 88 + 32, 158 - i3);
                }
                break;
            case 14:
                readMedia.drawImageInArr(paramGraphics, 63 + i4, i2 + 88 + 32, 158 - i3);
                break;
            case 12:
                if (bi == 3)
                {
                    if (paramg.l == 29)
                    {
                        b0 = (i2 + 88 + 32);
                        b1 = (i3 + 98 + 50);
                        b2 = (bb + 27);
                        b3 = (bc + 30);
                        ReturnHelper anchorReturn =
                        draw_anchor_helper(paramGraphics, readMedia, b0, b1, i3 >> 2, paramg.d, true); //  be, bf
                        complexReturn.san = anchorReturn.one;
                        complexReturn.si = anchorReturn.two;
                    }
                    else
                    {
                        complexReturn.eight = (b0 + paramg.d * ((b2 - b0) / 10)); // b6
                        complexReturn.night = (b1 + paramg.d * ((b3 - b1) / 10)); // b7
                        ReturnHelper anchorReturn =
                        draw_anchor_helper(paramGraphics, readMedia, complexReturn.eight, complexReturn.night, i3 >> 2, paramg.d, true); // b6 b7
                        complexReturn.san = anchorReturn.one;
                        complexReturn.si = anchorReturn.two;
                    }
                    if (paramg.d == 8) {
                        if ((bb - be > -50) && (bb - be < 27) && (bc - bf > -50) && (bc - bf < 30))
                        {
                            paramg.bool_n = true;
                        }
                        else
                        {
                            paramg.c = 0;
                            af += -1;
                        }
                    }
                }
                else if (paramg.d < 8)
                {
                    // *6960 = yellow, *8608 = dark red
                    fillRect_helper(paramGraphics, i2 + 88 + 32, i3 + 98 + 50, i3 >> 2, 16776960, 8388608);
                }
                break;
            case 11:
                if (aa_bool_n == true) // this.AA.bool_n
                {
                    if (paramg.l == 29)
                    {
                        by = (93 - bb);
                        bz = (144 - bc);
                    }
                    complexReturn.six = (i2 + 88 + 32 - by + (7 - paramg.d) * (by / 10)); // b4
                    complexReturn.seven = (i3 + 100 + 50 - bz + (7 - paramg.d) * (bz / 10)); // b5
                    ReturnHelper anchorReturn =
                    draw_anchor_helper(paramGraphics, readMedia, complexReturn.six, complexReturn.seven, i3 >> 1, paramg.l, false); // b4 b5
                    complexReturn.san = anchorReturn.one;
                    complexReturn.si = anchorReturn.two;
                }
                break;
            case 1:
                draw_img_helper(paramGraphics, readMedia, i2 + 88 + 32, i3 + 100 + 50, i3 >> 1, paramg.d);
                break;
            case 6:
                graphic_helper(paramGraphics, i2 + 93 + 2 + 32, i3 + 94 + 50, i3 >> 2, paramg.l, bj, bk);
                break;
            case 4:
            case 7:
            case 8:
                shift_1(paramGraphics, true, i2 + 88 + 32, 158 - i3, i4 * 4 + 2, i4 + 1, i4 + 1, readMedia, rnd);
                break;
            case 5:
                shift_1(paramGraphics, true, i2 + 88 + 32, 158 - i3, i4 * 4 + 2, i4 + 1, i4 + 1, readMedia, rnd);
                break;
        }
        complexReturn.one  = af;
        complexReturn.two  = b0;
        complexReturn.three = b1;
        complexReturn.four = b2;
        complexReturn.five = b3;
        complexReturn.bool_one = (aa_bool_n) ? 1 : 0;
        complexReturn.yi   = bb;
        complexReturn.er   = bc;
        complexReturn.san  = be;
        complexReturn.si   = bf;
        complexReturn.wu   = bi;
        complexReturn.liu  = bj;
        complexReturn.qi   = bk;
        complexReturn.ba   = by;
        complexReturn.jiu  = bz;

        return complexReturn;
    }

    // TODO remove this method in MainGameScreen
    public void fillRect_helper(SpriteBatch paramGraphics, int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5)
    {
        // paramGraphics.setColor(paramInt5); // dark red *8608
        int i1 = paramInt1 - paramInt3;
        int i2 = paramInt2 - (paramInt3 >> 1);
        fillRect(paramGraphics, i1, i2, paramInt3 << 1, paramInt3, 0);
        i1 = paramInt1 - (paramInt3 >> 1);
        i2 = paramInt2 - paramInt3;
        fillRect(paramGraphics, i1, i2, paramInt3, paramInt3 << 1, 0);
        // paramGraphics.setColor(paramInt4); // yellow *6960
        i1 = paramInt1 - (paramInt3 >> 1);
        i2 = paramInt2 - (paramInt3 >> 1);
        fillRect(paramGraphics, i1, i2, paramInt3, paramInt3, 2);
    }

    //	public void draw_anchor_helper(SpriteBatch paramGraphics, int paramInt1, int paramInt2, int paramInt3, int paramInt4, boolean paramBoolean)
    public ReturnHelper draw_anchor_helper(SpriteBatch paramGraphics, ReadMedia readMedia, int paramInt1, int paramInt2, int paramInt3, int paramInt4, boolean paramBoolean)
    {
        ReturnHelper anchorReturn = new ReturnHelper();
        if (paramInt3 > 20) {
            paramInt3 = 20;
        }
        int i1 = paramInt1 - (paramInt3 >> 1);
        int i2 = paramInt2 - (paramInt3 >> 1);
        if (paramBoolean)
        {
            readMedia.drawImageAnchor20(paramGraphics, 93 + paramInt4, i1, i2);
            anchorReturn.one = i1; // be = i1;
            anchorReturn.two = i2; // bf = i2;
        }
        else
        {
            readMedia.drawImageAnchor20(paramGraphics, 84 + (29 - paramInt4), i1, i2);
        }

        return anchorReturn;
    }

    public void draw_img_helper(SpriteBatch paramGraphics, ReadMedia readMedia, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
    {
        int i1 = paramInt1 - (paramInt3 >> 1);
        int i2 = paramInt2 - (paramInt3 >> 1);
        if (paramInt4 < 6) {
            readMedia.drawImageInArr(paramGraphics, 102 + paramInt4, i1 - 5, i2);
        }
    }

    //	public void graphic_helper(SpriteBatch paramGraphics, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
    public void graphic_helper(SpriteBatch paramGraphics, int paramInt1, int paramInt2, int paramInt3, int paramInt4, int bj, int bk)
    {
//        paramGraphics.setColor(bj);
        switch (paramInt4 & 0x3)
        {
            case 0:
//                paramGraphics.fillRect(paramInt1, paramInt2 - paramInt3, paramInt3, paramInt3);
//                paramGraphics.fillRect(paramInt1 - paramInt3, paramInt2, paramInt3, paramInt3);
                break;
            case 1:
//                paramGraphics.fillRect(paramInt1 - (paramInt3 >> 1), paramInt2 - paramInt3, paramInt3, paramInt3);
//                paramGraphics.fillRect(paramInt1 - (paramInt3 >> 1), paramInt2, paramInt3, paramInt3);
                break;
            case 2:
//                paramGraphics.fillRect(paramInt1 - paramInt3, paramInt2 - paramInt3, paramInt3, paramInt3);
//                paramGraphics.fillRect(paramInt1, paramInt2, paramInt3, paramInt3);
                break;
            case 3:
//                paramGraphics.fillRect(paramInt1 - paramInt3, paramInt2 - (paramInt3 >> 1), paramInt3, paramInt3);
//                paramGraphics.fillRect(paramInt1, paramInt2 - (paramInt3 >> 1), paramInt3, paramInt3);
                break;
        }
//        paramGraphics.setColor(bk);
//        paramGraphics.fillRect(paramInt1 - (paramInt3 >> 1), paramInt2 - (paramInt3 >> 1), paramInt3, paramInt3);
    }

    //	public void shift_1(SpriteBatch paramGraphics, boolean paramBoolean, int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5)
    public void shift_1(SpriteBatch paramGraphics, boolean paramBoolean, int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, ReadMedia readMedia, Random rnd)
    {
        int i4;
        int i3;
        int i1;
        int i2;
        if (paramBoolean)
        {
            readMedia.drawImageInArr(paramGraphics, 71 + paramInt4, paramInt1, paramInt2);
            for (i4 = 0; i4 < paramInt5; i4++)
            {
                i3 = Math.abs(rnd.nextInt() % 7);
                i1 = Math.abs(rnd.nextInt() % paramInt3) - (paramInt3 >> 1);
                i2 = Math.abs(rnd.nextInt() % paramInt3) - (paramInt3 >> 1);
                if ((i4 & 0x1) == 1) {
                    readMedia.drawImageInArr(paramGraphics, 71 + i3, paramInt1 + i1, paramInt2 + i2);
                } else {
                    readMedia.drawImageInArr(paramGraphics, 71 + i3, paramInt1 + i1, paramInt2 + i2);
                }
            }
        }
        else
        {
            readMedia.drawImageInArr(paramGraphics, 71 + paramInt4, paramInt1 + 5, paramInt2 - 25);
            for (i4 = 0; i4 < paramInt5; i4++)
            {
                i3 = Math.abs(rnd.nextInt() % 7);
                i1 = Math.abs(rnd.nextInt() % paramInt3) - (paramInt3 >> 1);
                i2 = Math.abs(rnd.nextInt() % paramInt3) - (paramInt3 >> 1);
                if ((i4 & 0x1) == 1) {
                    readMedia.drawImageInArr(paramGraphics, 71 + i3, paramInt1 + i1 + 5, paramInt2 + i2 - 25);
                } else {
                    readMedia.drawImageInArr(paramGraphics, 71 + i3, paramInt1 + i1 + 5, paramInt2 + i2 - 25);
                }
            }
        }
    }

    // int
    public ReturnHelper return_turn_helper(int paramInt1, int paramInt2, int paramInt3, int bo, int bp, int bq, int br, byte[] stt_byte_arr_bs)
    {
        ReturnHelper returnHelper = new ReturnHelper();
        ReturnHelper returnHelper2 = turn_helper2(paramInt1, bq, br, stt_byte_arr_bs);
        returnHelper.three = returnHelper2.one;
        returnHelper.four = returnHelper2.two;

        ReturnHelper returnHelper3 = turn_helper(paramInt1, bo, bp, stt_byte_arr_bs);
        returnHelper.one = returnHelper3.one;
        returnHelper.two = returnHelper3.two;

        returnHelper.five = returnHelper2.two * paramInt2 +  returnHelper3.two * paramInt3 >> 6; // return

        return returnHelper;
    }

    public void fillRect(SpriteBatch batch, int x, int y, int width, int height, int color) {
        // Hard code default width x height of color img: 12x12 px
        int scaleY = height / 12;
        int scaleX = width / 12;
        // (Texture, float x, float y, float originX, float originY, float width, float height, float scaleX, float scaleY, float rotation, int srcX, int srcY, int srcWidth, int srcHeight, boolean flipX, boolean flipY)
        batch.draw(imgColor[color], x, y, 0, 0, imgColor[color].getWidth(), imgColor[color].getHeight(), scaleX, scaleY, 0, 0, 0, imgColor[color].getWidth()*scaleX, imgColor[color].getHeight()*scaleY, false, false);
    }
}
