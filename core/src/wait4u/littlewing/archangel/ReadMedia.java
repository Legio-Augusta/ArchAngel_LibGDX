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

    private static int MOBI_SCL = (int)Gdx.graphics.getWidth()/120; // 120 or 128px from original J2ME resolution.

    public void destroyImage(int paramInt)
    {
        this.img_arr_a[paramInt] = null;
        System.gc();
    }

    public void mySetClip(SpriteBatch paramGraphics)
    {
//        paramGraphics.setClip(0, 0, 240, 320);
//        Rectangle scissors = new Rectangle();
//        Rectangle clipBounds = new Rectangle(x,y,w,h);
//        ScissorStack.calculateScissors(camera, spriteBatch.getTransformMatrix(), clipBounds, scissors);
//        ScissorStack.pushScissors(scissors);
//        spriteBatch.draw(...);
//        spriteBatch.flush();
//        ScissorStack.popScissors();
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
        if (this.img_arr_a[paramInt1] == null) {
            Gdx.app.log("ERROR", "load Media: " + this.msr_media + " int1= "+ paramInt1 + " int2= " + paramInt2);
            this.img_arr_a[paramInt1] = new Texture(Gdx.files.internal("archangel/shop_0.png"));
        }
        paramGraphics.draw(this.img_arr_a[paramInt1], paramInt2*MOBI_SCL, paramInt3*MOBI_SCL); // 20 anchor
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
        paramGraphics.draw(this.img_arr_a[paramInt1], paramInt2*MOBI_SCL, paramInt3*MOBI_SCL); // 36
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

    // TODO debug to figure out how to load image to array[120] and clean
    // The order of load is important because of index in array depend on how many item has been loaded already.
    // For example plasma has 24 sprite image, enermy has 9 ...
    public void reloadImageArr(int paramInt1, int paramInt2)
    {
        this.img_arr_a[paramInt2] = null;
        this.img_arr_a[paramInt2] = loadImage(paramInt1);
    }

    public Texture loadImage(int paramInt)
    {
        /*
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
        */
        // return Image.createImage(arrayOfByte, 0, arrayOfByte.length);
        // TODO calculate and return Texture image instead of byte stream as original J2ME
        if (this.msr_media == "font") { // 0->5
           return new Texture("archangel/font_" + paramInt + ".png");
        } else if(this.msr_media == "aa") { // 0-24 9-33 10-34
            // 24 -> 34
            return new Texture("archangel/aa_" + paramInt +".png");
        } else if(this.msr_media == "background0") { // 7 -> 17
            return new Texture("archangel/background0_" + paramInt + ".png");
        } else if(this.msr_media == "background1") {
        } else if(this.msr_media == "background2") {
        } else if(this.msr_media == "boss0") {
            // this.img_arr_a[21] = new Texture("archangel/boss0_8.png");
            return new Texture("archangel/boss0_" + paramInt + ".png");
        } else if(this.msr_media == "boss1") {
            this.img_arr_a[21] = new Texture("archangel/boss1_8.png");
        } else if(this.msr_media == "boss2") {
        } else if(this.msr_media == "boss3") {
        } else if(this.msr_media == "boss4") {
        } else if(this.msr_media == "boss5") {
        } else if(this.msr_media == "boss6") {
        } else if(this.msr_media == "boss7") {
        } else if(this.msr_media == "brief") { // 17->20
            return new Texture("archangel/brief_" + paramInt + ".png");
        } else if(this.msr_media == "effect") { // 71 -> 83
            return new Texture("archangel/effect_" + paramInt + ".png");
        } else if(this.msr_media == "end") {
        } else if(this.msr_media == "enermy0") { // 53 -> 60
            return new Texture("archangel/enermy0_" + paramInt + ".png");
            // this.img_arr_a[22] = new Texture("archangel/enermy0_8.png");
        } else if(this.msr_media == "enermy1") { // 53 -> 60
            return new Texture("archangel/enermy1_" + paramInt + ".png");
        } else if(this.msr_media == "enermy2") {
        } else if(this.msr_media == "enermy3") {
        } else if(this.msr_media == "etc") { // 3_114 0_111
            return new Texture("archangel/etc_"+ paramInt + ".png");
        } else if(this.msr_media == "fence0") { // 44 -> 51
            return new Texture("archangel/fence0_" + paramInt + ".png");
        } else if(this.msr_media == "fence1") {
        } else if(this.msr_media == "fence2") {
        } else if(this.msr_media == "intro") { // 30
            return new Texture("archangel/intro_0.png");
        } else if(this.msr_media == "logo") { // 3 img
        } else if(this.msr_media == "menu") { // 13 -> 16
            return new Texture("archangel/menu_" + paramInt + ".png");
        } else if(this.msr_media == "open") { // 29
            return new Texture("archangel/open_0.png");
        } else if(this.msr_media == "plasma") { // 102 -> 107
            return new Texture("archangel/plasma_" + paramInt + ".png");
        } else if(this.msr_media == "result") { // 6->12
            // return new Texture("archangel/result_" + (i) + ".png");
        } else if(this.msr_media == "select") {
            return new Texture("archangel/select_" + paramInt + ".png"); // It seem index is flexible, not constant
        } else if(this.msr_media == "shop") {
        } else if(this.msr_media == "shot") {
        } else if(this.msr_media == "ui") { // 21-23
            return new Texture("archangel/ui_" + paramInt + ".png");
        }

        return new Texture(Gdx.files.internal("archangel/shop_0.png")); // DEBUG
    }

    public void drawImageInArr(SpriteBatch paramGraphics, int paramInt1, int paramInt2, int paramInt3)
    {
        if (this.img_arr_a[paramInt1] == null) {
            Gdx.app.log("ERROR", "load Media Arr: " + this.msr_media + " int 1 "+ paramInt1 + " int 2 " + paramInt2);
            if(this.msr_media.matches("enermy.")) {
                readMediaStream("enermy0");
            }
            this.img_arr_a[paramInt1] = new Texture("archangel/boss7_7.png");
        }
        paramGraphics.draw(this.img_arr_a[paramInt1], paramInt2*MOBI_SCL, paramInt3*MOBI_SCL); // 3
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
        // paramGraphics.draw(loadImage(paramInt1), paramInt2, paramInt3); //20
        paramGraphics.draw(this.img_arr_a[paramInt1], paramInt2*MOBI_SCL, paramInt3*MOBI_SCL); //20
    }

    public void drawLoadImage(int paramInt1, SpriteBatch paramGraphics, int paramInt2, int paramInt3)
    {
        paramGraphics.draw(loadImage(paramInt1), paramInt2*MOBI_SCL, paramInt3*MOBI_SCL); // 20
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
     * @param paramString
     *  aa.msr => 00 - 18           background0.msr  10
        boss0.msr 8                 brief.msr 0-3
        effect.msr 12               end.msr 01
        enermy0.msr 8               etc.msr 3
        fence0.msr 8                font.msr 5
        intro.msr 00                logo.msr 2
        menu.msr 3                  open.msr 00
        plasma.msr 23               result.msr 2
        select.msr 6                shop.msr 2
        shot.msr 8                  ui.msr 2
     */
    public void readMediaStream(String paramString)
    {
        this.msr_media = paramString;
        /*
        paramString = "android/assets/msr/" + paramString + ".msr";
            int i = readBinaryData() + 1;
            this.int_arr_d = new int[i];
            for (int j = 0; j < i; j++) {
                this.int_arr_d[j] = readBinaryData();
            }
        this.int_bound_e = this.int_arr_d[0];
        */
        // TODO try read file from path

        if (paramString == "font") { // 0->5
            for (int i = 0; i < 6; i++) {
                this.img_arr_a[i] = new Texture("archangel/font_" + i + ".png");
            }
        } else if(paramString == "aa") { // 0-24 9-33 10-34
            // 24 -> 34
            for (int i = 24; i <= 42; i++) {
                this.img_arr_a[i] = new Texture("archangel/aa_" + (i-24) +".png");
            }
        } else if(paramString == "background0") { // 7 -> 17
            for (int i = 7; i <= 17; i++) {
                this.img_arr_a[i] = new Texture("archangel/background0_" + (i-7) + ".png");
            }
        } else if(paramString == "background1") {
        } else if(paramString == "background2") {
        } else if(paramString == "boss0") {
            this.img_arr_a[21] = new Texture("archangel/boss0_8.png");
            for (int i = 62; i <= 68; i++) {
                this.img_arr_a[i] = new Texture("archangel/boss0_" + (i-62) + ".png");
            }
        } else if(paramString == "boss1") {
            this.img_arr_a[21] = new Texture("archangel/boss1_8.png");
        } else if(paramString == "boss2") {
        } else if(paramString == "boss3") {
        } else if(paramString == "boss4") {
        } else if(paramString == "boss5") {
        } else if(paramString == "boss6") {
        } else if(paramString == "boss7") {
        } else if(paramString == "brief") { // 17->20
            for (int i = 17; i <= 20; i++) {
                this.img_arr_a[i] = new Texture("archangel/brief_" + (i-17) + ".png");
            }
        } else if(paramString == "effect") { // 71 -> 83
            for (int i = 71; i < 83; i++) {
                this.img_arr_a[i] = new Texture("archangel/effect_" + (i-71) + ".png");
            }
        } else if(paramString == "end") {
        } else if(paramString == "enermy0") { // 53 -> 60
            for (int i = 53; i <= 60; i++) {
                this.img_arr_a[i] = new Texture("archangel/enermy0_" + (i-53) + ".png");
            }
            this.img_arr_a[22] = new Texture("archangel/enermy0_8.png");
        } else if(paramString == "enermy1") { // 53 -> 60
            this.img_arr_a[22] = new Texture("archangel/enermy1_8.png");
            for (int i = 53; i <= 60; i++) {
                this.img_arr_a[i] = new Texture("archangel/enermy1_" + (i-53) + ".png");
            }
        } else if(paramString == "enermy2") {
        } else if(paramString == "enermy3") {
        } else if(paramString == "etc") { // 3_114 0_111
            this.img_arr_a[114] = new Texture("archangel/etc_3.png");
            this.img_arr_a[111] = new Texture("archangel/etc_0.png");
            this.img_arr_a[112] = new Texture("archangel/etc_1.png");
        } else if(paramString == "fence0") { // 44 -> 51
            for (int i = 44; i <= 51; i++) {
                this.img_arr_a[i] = new Texture("archangel/fence0_" + (i-44) + ".png");
            }
        } else if(paramString == "fence1") {
        } else if(paramString == "fence2") {
        } else if(paramString == "intro") { // 30
            this.img_arr_a[30] = new Texture("archangel/intro_0.png");
        } else if(paramString == "logo") {
        } else if(paramString == "menu") { // 13 -> 16
            for (int i = 13; i <= 16; i++) {
                this.img_arr_a[i] = new Texture("archangel/menu_" + (i-13) + ".png");
            }
        } else if(paramString == "open") { // 29
                this.img_arr_a[29] = new Texture("archangel/open_0.png");
        } else if(paramString == "plasma") { // 102 -> 107
            for (int i = 102; i <= 107; i++) {
                this.img_arr_a[i] = new Texture("archangel/plasma_" + (i-102) + ".png");
            }
            for (int i = 84; i <= 101; i++) {
                this.img_arr_a[i] = new Texture("archangel/plasma_" + (i-84) + ".png");
            }
        } else if(paramString == "result") { // 6->12
            // this.img_arr_a[i] = new Texture("archangel/result_" + (i-6) + ".png");
        } else if(paramString == "select") {
            for (int i = 6; i <= 12; i++) {
                this.img_arr_a[i] = new Texture("archangel/select_" + (i-6) + ".png");
            }
        } else if(paramString == "shop") {
        } else if(paramString == "shot") {
        } else if(paramString == "ui") { // 21-23
            for (int i = 21; i <= 23; i++) {
                this.img_arr_a[i] = new Texture("archangel/ui_" + (i-21) + ".png");
            }
        } else {
            return;
        }
    }
}
