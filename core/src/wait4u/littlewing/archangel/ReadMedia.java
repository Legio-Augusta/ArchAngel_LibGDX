package wait4u.littlewing.archangel;

import com.badlogic.gdx.Gdx;
import java.io.InputStream;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 *
 * @author Nick Farrow
 * Read .msr (media binary combined file). .msr is optional extension. It can be
 * extracted using some binary extract tool like photo_spliter.py by Greg Lavino.
 */
public class ReadMedia
{
    public Texture[] img_arr_a = new Texture[120];
    public Texture img_b = null;
    public InputStream inputStream;
    public int[] int_arr_d = null;
    public int int_bound_e = 0;
    public String msr_media;

    public void destroyImage(int paramInt)
    {
        this.img_arr_a[paramInt] = null;
        System.gc();
    }

    public void mySetClip(SpriteBatch paramGraphics)
    {
//        paramGraphics.setClip(0, 0, 240, 320);
    }

    public void destroyImage53_115()
    {
        for (int i = 53; i <= 115; i++) {
            this.img_arr_a[i] = null;
        }
        System.gc();
    }

    public void drawImageAnchor20(SpriteBatch paramGraphics, int paramInt1, int paramInt2, int paramInt3)
    {
        paramGraphics.draw(this.img_arr_a[paramInt1], paramInt2, paramInt3); // 20 anchor
    }

    public ReadMedia()
    {
        readMediaStream("font");
//        reloadImageArr(0, 0);
//        reloadImageArr(1, 1);
//        for (int i = 3; i < 6; i++) {
//            reloadImageArr(i, i);
//        }
//        closeInputStream();
    }

    public void destroyImage7_53()
    {
        for (int i = 7; i <= 53; i++) {
            this.img_arr_a[i] = null;
        }
        System.gc();
    }

    // Load image from binary file seem complex, in many case the img_arr (120 items) has many null value.
    // These null value lead to NullPointer exception
    public void drawImageAnchor36(SpriteBatch paramGraphics, int paramInt1, int paramInt2, int paramInt3)
    {
        paramGraphics.draw(this.img_arr_a[paramInt1], paramInt2, paramInt3); // 36
    }

    public void drawStringGraphic(SpriteBatch paramGraphics, int paramInt1, int paramInt2, String paramString, int paramInt3)
    {
        if(paramString == null) { // dungnv
            byte[] test = {-47, 1, 16, 84, 2, 101, 110, 83, 111, 109, 101, 32, 78, 70, 67, 32, 68, 97, 116, 97};
            paramString = new String(test, 0, 20);
        }
        for (int k = 0; k < paramString.length(); k++) // NAME: \n AZ 1 \n DAMAGE: 30MP
        {
            int j = paramString.charAt(k);
            if ((j >= 48) && (j <= 90))
            {
                int i = j - 48;
//                paramGraphics.setClip(paramInt1 + 6 * k, paramInt2, 5, 5);
                drawImageAnchor20(paramGraphics, 0, paramInt1 + 6 * k - i * 5, paramInt2);
            }
        }
        mySetClip(paramGraphics);
    }

    public void drawGraphicStr40_122(SpriteBatch paramGraphics, int paramInt1, int paramInt2, String paramString)
    {
        for (int k = 0; k < paramString.length(); k++)
        {
            int j = paramString.charAt(k);
            if ((j >= 40) && (j <= 122))
            {
                int i = j - 40;
//                paramGraphics.setClip(paramInt1 + 9 * k, paramInt2, 9, 13);
                drawImageAnchor20(paramGraphics, 1, paramInt1 + 9 * k - i * 9, paramInt2);
            }
        }
        mySetClip(paramGraphics);
    }

    public void drawImageSwitch(SpriteBatch paramGraphics, int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6)
    {
//        paramGraphics.setClip(paramInt1, paramInt2, paramInt3, paramInt4);
        switch (paramInt6)
        {
            case 0:
                drawImageAnchor20(paramGraphics, 26, paramInt1, paramInt2 - paramInt4 * paramInt5);
                break;
            case 1:
                drawImageAnchor20(paramGraphics, 27, paramInt1, paramInt2 - paramInt4 * paramInt5);
                break;
            case 2:
                drawImageAnchor20(paramGraphics, 28, paramInt1, paramInt2 - paramInt4 * paramInt5);
                break;
        }
        mySetClip(paramGraphics);
    }

    public void reloadImageArr(int paramInt1, int paramInt2)
    {
        this.img_arr_a[paramInt2] = null;
        this.img_arr_a[paramInt2] = loadImage(paramInt1);
    }

    public Texture loadImage(int paramInt)
    {
        byte[] arrayOfByte = new byte[this.int_arr_d[(paramInt + 1)] - this.int_arr_d[paramInt]];
        try
        {
            if (this.int_bound_e <= this.int_arr_d[paramInt])
            {
                this.inputStream.skip(this.int_arr_d[paramInt] - this.int_bound_e);
            }
            else
            {
                this.inputStream.close();
                this.inputStream = getClass().getResourceAsStream(this.msr_media);
                this.inputStream.skip(this.int_arr_d[paramInt]);
            }
            this.inputStream.read(arrayOfByte);
            this.int_bound_e = this.int_arr_d[(paramInt + 1)];
        }
        catch (Exception localException) {}
//        return Image.createImage(arrayOfByte, 0, arrayOfByte.length);
        // TODO calculate and return Texture image instead of byte stream as original J2ME
        return new Texture(Gdx.files.internal("data/archangel/aa_00.png"));
    }

    public void drawImageInArr(SpriteBatch paramGraphics, int paramInt1, int paramInt2, int paramInt3)
    {
        paramGraphics.draw(this.img_arr_a[paramInt1], paramInt2, paramInt3); // 3
    }

    public int readBinaryData()
            throws Exception
    {
        int i = 0;
        byte[] arrayOfByte = new byte[4];
        this.inputStream.read(arrayOfByte);
        i = arrayOfByte[0] & 0xFF;
        i += ((arrayOfByte[1] & 0xFF) << 8);
        i += ((arrayOfByte[2] & 0xFF) << 16);
        i += ((arrayOfByte[3] & 0xFF) << 24);
        return i;
    }

    public void drawStringImage(String paramString, int paramInt1, SpriteBatch paramGraphics, int paramInt2, int paramInt3)
    {
        readMediaStream(paramString);
        paramGraphics.draw(loadImage(paramInt1), paramInt2, paramInt3); //20
        closeInputStream();
    }

    public void drawLoadImage(int paramInt1, SpriteBatch paramGraphics, int paramInt2, int paramInt3)
    {
        paramGraphics.draw(loadImage(paramInt1), paramInt2, paramInt3); // 20
    }

    public void closeInputStream()
    {
        this.int_arr_d = null;
        try
        {
            this.inputStream.close();
        }
        catch (Exception localException) {}
        System.gc();
    }

    /**
     *
     * @param paramString
     *  aa.msr => 00 - 18
    background0.msr  10
    background1.msr 10
    background2.msr 10
    boss0.msr 8
    boss1.msr 8
    boss2.msr 8
    boss3.msr 8
    boss4.msr 8
    boss5.msr 8
    boss6.msr 8
    boss7.msr 8
    brief.msr 0-3
    effect.msr 12
    end.msr 01
    enermy0.msr 8
    enermy1.msr 8
    enermy2.msr 8
    enermy3.msr 8
    etc.msr 3
    fence0.msr 8
    fence1.msr 8
    fence2.msr 8
    font.msr 5
    intro.msr 00
    logo.msr 2
    menu.msr 3
    open.msr 00
    plasma.msr 23
    result.msr 2
    select.msr 6
    shop.msr 2
    shot.msr 8
    ui.msr 2
     */
    public void readMediaStream(String paramString)
    {
        // TODO try read file from path
        /*
        paramString = "android/assets/msr/" + paramString + ".msr";
        this.msr_media = paramString;
        try
        {
            this.inputStream = getClass().getResourceAsStream(paramString);
            int i = readBinaryData() + 1;
            this.int_arr_d = new int[i];
            for (int j = 0; j < i; j++) {
                this.int_arr_d[j] = readBinaryData();
            }
        }
        catch (Exception localException) {}
        this.int_bound_e = this.int_arr_d[0];
        System.gc();
        */

        if (paramString == "font") {
            this.img_arr_a = new Texture[6];
            for (int i = 0; i < 6; i++) {
                this.img_arr_a[i] = new Texture("archangel/font_" + i + ".png");
            }
        } else if(paramString == "aa") {
            this.img_arr_a = new Texture[19];
            for (int i = 0; i < this.img_arr_a.length; i++) {
                this.img_arr_a[i] = new Texture("archangel/aa_" + i + ".png");
            }
        } else if(paramString == "background0") {
            this.img_arr_a = new Texture[11];
            for (int i = 0; i < this.img_arr_a.length; i++) {
                this.img_arr_a[i] = new Texture("archangel/background0_" + i + ".png");
            }
        } else if(paramString == "background1") {
            this.img_arr_a = new Texture[11];
            for (int i = 0; i < this.img_arr_a.length; i++) {
                this.img_arr_a[i] = new Texture("archangel/background1_" + i + ".png");
            }
        } else if(paramString == "background2") {
            this.img_arr_a = new Texture[11];
            for (int i = 0; i < this.img_arr_a.length; i++) {
                this.img_arr_a[i] = new Texture("archangel/background2_" + i + ".png");
            }
        } else if(paramString == "boss0") {
            this.img_arr_a = new Texture[9];
            for (int i = 0; i < this.img_arr_a.length; i++) {
                this.img_arr_a[i] = new Texture("archangel/boss0_" + i + ".png");
            }
        } else if(paramString == "boss1") {
            this.img_arr_a = new Texture[9];
            for (int i = 0; i < this.img_arr_a.length; i++) {
                this.img_arr_a[i] = new Texture("archangel/boss1_" + i + ".png");
            }
        } else if(paramString == "boss2") {
            this.img_arr_a = new Texture[9];
            for (int i = 0; i < this.img_arr_a.length; i++) {
                this.img_arr_a[i] = new Texture("archangel/boss2_" + i + ".png");
            }
        } else if(paramString == "boss3") {
            this.img_arr_a = new Texture[9];
            for (int i = 0; i < this.img_arr_a.length; i++) {
                this.img_arr_a[i] = new Texture("archangel/boss3_" + i + ".png");
            }
        } else if(paramString == "boss4") {
            this.img_arr_a = new Texture[9];
            for (int i = 0; i < this.img_arr_a.length; i++) {
                this.img_arr_a[i] = new Texture("archangel/boss4_" + i + ".png");
            }
        } else if(paramString == "boss5") {
            this.img_arr_a = new Texture[9];
            for (int i = 0; i < this.img_arr_a.length; i++) {
                this.img_arr_a[i] = new Texture("archangel/boss5_" + i + ".png");
            }
        } else if(paramString == "boss6") {
            this.img_arr_a = new Texture[9];
            for (int i = 0; i < this.img_arr_a.length; i++) {
                this.img_arr_a[i] = new Texture("archangel/boss6_" + i + ".png");
            }
        } else if(paramString == "boss7") {
            this.img_arr_a = new Texture[9];
            for (int i = 0; i < this.img_arr_a.length; i++) {
                this.img_arr_a[i] = new Texture("archangel/boss7_" + i + ".png");
            }
        } else if(paramString == "brief") {
            this.img_arr_a = new Texture[4];
            for (int i = 0; i < this.img_arr_a.length; i++) {
                this.img_arr_a[i] = new Texture("archangel/brief_" + i + ".png");
            }
        } else if(paramString == "effect") {
            this.img_arr_a = new Texture[13];
            for (int i = 0; i < this.img_arr_a.length; i++) {
                this.img_arr_a[i] = new Texture("archangel/effect_" + i + ".png");
            }
        } else if(paramString == "end") {
            this.img_arr_a = new Texture[2];
            for (int i = 0; i < this.img_arr_a.length; i++) {
                this.img_arr_a[i] = new Texture("archangel/end_" + i + ".png");
            }
        } else if(paramString == "enermy0") {
            this.img_arr_a = new Texture[9];
            for (int i = 0; i < this.img_arr_a.length; i++) {
                this.img_arr_a[i] = new Texture("archangel/enermy0_" + i + ".png");
            }
        } else if(paramString == "enermy1") {
            this.img_arr_a = new Texture[9];
            for (int i = 0; i < this.img_arr_a.length; i++) {
                this.img_arr_a[i] = new Texture("archangel/enermy1_" + i + ".png");
            }
        } else if(paramString == "enermy2") {
            this.img_arr_a = new Texture[9];
            for (int i = 0; i < this.img_arr_a.length; i++) {
                this.img_arr_a[i] = new Texture("archangel/enermy2_" + i + ".png");
            }
        } else if(paramString == "enermy3") {
            this.img_arr_a = new Texture[9];
            for (int i = 0; i < this.img_arr_a.length; i++) {
                this.img_arr_a[i] = new Texture("archangel/enermy3_" + i + ".png");
            }
        } else if(paramString == "etc") {
            this.img_arr_a = new Texture[4];
            for (int i = 0; i < this.img_arr_a.length; i++) {
                this.img_arr_a[i] = new Texture("archangel/etc_" + i + ".png");
            }
        } else if(paramString == "fence0") {
            this.img_arr_a = new Texture[9];
            for (int i = 0; i < this.img_arr_a.length; i++) {
                this.img_arr_a[i] = new Texture("archangel/fence0_" + i + ".png");
            }
        } else if(paramString == "fence1") {
            this.img_arr_a = new Texture[9];
            for (int i = 0; i < this.img_arr_a.length; i++) {
                this.img_arr_a[i] = new Texture("archangel/fence1_" + i + ".png");
            }
        } else if(paramString == "fence2") {
            this.img_arr_a = new Texture[9];
            for (int i = 0; i < this.img_arr_a.length; i++) {
                this.img_arr_a[i] = new Texture("archangel/fence2_" + i + ".png");
            }
        } else if(paramString == "intro") {
            this.img_arr_a = new Texture[1];
            for (int i = 0; i < this.img_arr_a.length; i++) {
                this.img_arr_a[i] = new Texture("archangel/intro_" + i + ".png");
            }
        } else if(paramString == "logo") {
            this.img_arr_a = new Texture[3];
            for (int i = 0; i < this.img_arr_a.length; i++) {
                this.img_arr_a[i] = new Texture("archangel/logo_" + i + ".png");
            }
        } else if(paramString == "menu") {
            this.img_arr_a = new Texture[4];
            for (int i = 0; i < this.img_arr_a.length; i++) {
                this.img_arr_a[i] = new Texture("archangel/menu_" + i + ".png");
            }
        } else if(paramString == "open") {
            this.img_arr_a = new Texture[1];
            for (int i = 0; i < this.img_arr_a.length; i++) {
                this.img_arr_a[i] = new Texture("archangel/open_" + i + ".png");
            }
        } else if(paramString == "plasma") {
            this.img_arr_a = new Texture[24];
            for (int i = 0; i < this.img_arr_a.length; i++) {
                this.img_arr_a[i] = new Texture("archangel/plasma_" + i + ".png");
            }
        } else if(paramString == "result") {
            this.img_arr_a = new Texture[3];
            for (int i = 0; i < this.img_arr_a.length; i++) {
                this.img_arr_a[i] = new Texture("archangel/result_" + i + ".png");
            }
        } else if(paramString == "select") {
            this.img_arr_a = new Texture[7];
            for (int i = 0; i < this.img_arr_a.length; i++) {
                this.img_arr_a[i] = new Texture("archangel/select_" + i + ".png");
            }
        } else if(paramString == "shop") {
            this.img_arr_a = new Texture[3];
            for (int i = 0; i < this.img_arr_a.length; i++) {
                this.img_arr_a[i] = new Texture("archangel/shop_" + i + ".png");
            }
        } else if(paramString == "shot") {
            this.img_arr_a = new Texture[10];
            for (int i = 0; i < this.img_arr_a.length; i++) {
                this.img_arr_a[i] = new Texture("archangel/shot_" + i + ".png");
            }
        } else if(paramString == "ui") {
            this.img_arr_a = new Texture[4];
            for (int i = 0; i < this.img_arr_a.length; i++) {
                this.img_arr_a[i] = new Texture("archangel/ui_" + i + ".png");
            }
        } else {
            return;
        }
    }
}
