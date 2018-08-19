package wait4u.littlewing.archangel;

import com.badlogic.gdx.Gdx;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

public class ReadText
{
    public int a = 1;
    public int b = 0;
    public boolean bool_c = false;
    public boolean bool_d = false;
    public boolean bool_e = false;
    public boolean bool_f = false;
    public int g = 1;
    public int h = 1;
    public int i;
    public int j;
    public int k;
    // public byte[] byte_arr_l = new byte['?']; // ASCII '?' = 63
    public byte[] byte_arr_l = new byte['?']; // ASCII '?' = 63
    public int[] int_arr_m = new int[9];
    public int n = -1;
    public int o = -1;

    public boolean processTxt(int paramInt)
    {
        int i1 = 0;
        if (this.n != paramInt)
        {
            if (this.bool_c) {
                this.a = this.g;
            } else if (this.bool_d) {
                this.a = this.h;
            } else {
                this.a = 1;
            }
            if (this.bool_e) {
                this.a = 5;
            }
            this.b = 0;
            this.n = paramInt;
        }
        this.o = 0;
        for (int i2 = 0; i2 < 9; i2++) {
            this.int_arr_m[i2] = 0;
        }
        this.k = extractValueFromTxt(paramInt); // 1, 2 => infi loop, 3 10 4 ...
        if (this.k == -1)
        {
            this.k = 0;
            return false;
        }
        while (bool_ascii_helper('+')) { // boolean a (String str)
            // Exception on i1 = 8 or 9
            System.out.println(" bool e.a i1= " + i1 + " k= " + this.k);
            if (i1 <= 8) {
                this.o = (this.int_arr_m[(i1++)] = calcIntCharFromASCII(this.k));
            } else {
                break;
            }
        }
        this.k += 2;
        return true;
    }

    /*
     * Guest: Calculate integer value from byte (ASCII)
     */
    public int calcIntCharFromASCII(int paramInt)
    {
        int i2 = 0;
        int i1 = 0; // dungnv
        for (i1 = paramInt; i1 < this.j; i1++) {
            if ((this.byte_arr_l[i1] >= 48) && (this.byte_arr_l[i1] <= 57)) {
                break;
            }
        }
        while (i1 < this.j)
        {
            if ((this.byte_arr_l[i1] >= 48) && (this.byte_arr_l[i1] > 57)) { // ASCII 0-9
                break;
            }
            i2 *= 10;
            i2 += this.byte_arr_l[i1] - 48; // byte - 48 (x48 = 0, x57 = 9)
            i1++;
        }
        this.k = i1;
        return i2;
    }

    public void readTextFromStream(String paramString) // Load text seem has problem, it not shown on screen
    {
        String str = "assets/text/" + paramString + ".txt";

        String textAsInputStream = "";
        if(paramString == "about") {
            textAsInputStream = "<1>\n" +
                    "ArchAngel Ver 1.0.0\n" +
                    "\n" +
                    "Published by wait4u\n" +
                    "URL: www.wait4u.net\n" +
                    "E-mail: info@wait4u.net\n" +
                    "\n" +
                    "Developed by WithMobile";
        } else if(paramString == "arm") {
            textAsInputStream = "<1>\n" +
                    "ArchAngel Ver 1.0.0\n" +
                    "\n" +
                    "Published by wait4u\n" +
                    "URL: www.wait4u.net\n" +
                    "E-mail: info@wait4u.net\n" +
                    "\n" +
                    "Developed by WithMobile\n" +
                    "nickfarrow:text$ cat arm.txt \n" +
                    "<1>+10+1+0\n" +
                    "T2-COMPO\n" +
                    "<2>+20+2+2000\n" +
                    "T-10 COMPO\n" +
                    "<3>+40+3+4000\n" +
                    "EX-T COMPO\n" +
                    "<4>+80+4+8000\n" +
                    "ERA 1000\n" +
                    "<5>+160+5+16000\n" +
                    "ERA 2000\n" +
                    "<6>+320+6+50000\n" +
                    "ERA 3000";
        } else if(paramString == "open") {
            textAsInputStream = "<11>\n" +
                    "The year 2028...\n" +
                    "A conflict between\n" +
                    "the Earth and the\n" +
                    "<12>\n" +
                    "other planets has\n" +
                    "led the worlds to \n" +
                    "the edge of a final\n" +
                    "<13>\n" +
                    "war which threatens\n" +
                    "man's very survival.\n" +
                    "In a struggle to end\n" +
                    "<14>\n" +
                    "the conflict, the\n" +
                    "confederation has\n" +
                    "developed a\n" +
                    "<15>\n" +
                    "revolutionary land-\n" +
                    "sea-air-space unit \n" +
                    "in a top secret\n" +
                    "<16>\n" +
                    "operation.\n" +
                    "Due to the fact that\n" +
                    "this unit looks like\n" +
                    "<17>\n" +
                    "a fire-spitting angel\n" +
                    "its code name is \n" +
                    "'Arch Angel'.\n" +
                    "\n";
        } else if(paramString == "plasma") {
            textAsInputStream = "<1>+40+50+0\n" +
                    "MAG 300KV\n" +
                    "<2>+60+60+2000\n" +
                    "LINIE 360KV\n" +
                    "<3>+100+70+4000\n" +
                    "DUNA 420KV\n" +
                    "<4>+150+80+8000\n" +
                    "MAG 480KV\n" +
                    "<5>+300+90+16000\n" +
                    "LINIE 540KV\n" +
                    "<6>+500+300+50000\n" +
                    "DUNA 600KV";
        } else if(paramString == "ms1") {
            textAsInputStream = "<1>+20000+40000+5\n" +
                    "<2>+100+30+1+200\n" +
                    "AZ-1\n" +
                    "<3>+1200+100+0+1000\n" +
                    "AL 101A\n" +
                    "<4>\n" +
                    "EndingMent\n" +
                    "<10>\n" +
                    "Mission 1\n" +
                    "<11>\n" +
                    "In Dark Eden an area in\n" +
                    "the Nekkar desert, the\n" +
                    "enemy has concentrated \n" +
                    "<12>\n" +
                    "its troops to prepare \n" +
                    "for war. You will face\n" +
                    "a group of 10 AZ-1\n" +
                    "<13>\n" +
                    "combat units, which you\n" +
                    "need to destroy. Then,\n" +
                    "follow the directions\n" +
                    "<14>\n" +
                    "of your navigator for\n" +
                    "60 km, which will leads\n" +
                    "you directly to their\n" +
                    "<15>\n" +
                    "newly developed AL-101\n" +
                    "air fighter...\n" +
                    "Destroy it!\n";
        } else if(paramString == "ms2") {
            textAsInputStream = "<1>+30000+50000+7\n" +
                    "<2>+100+30+1+200\n" +
                    "AZ-1\n" +
                    "<3>+1500+150+0+2000\n" +
                    "AL 101B\n" +
                    "<4>\n" +
                    "EndingMent\n" +
                    "<10>\n" +
                    "Mission 2\n" +
                    "<11>\n" +
                    "The remaining enemy\n" +
                    "troops are hiding\n" +
                    "in the Bellatrix\n" +
                    "<12>\n" +
                    "mountains, north-east\n" +
                    "of Dark Eden.\n" +
                    "Destroy 7 of their \n" +
                    "<13>\n" +
                    "AZ-1 units and then \n" +
                    "follow the navigator\n" +
                    "for 80km and destroy\n" +
                    "<14>\n" +
                    "their upgraded AL-101\n" +
                    "unit!\n" +
                    "Good luck!";
        }
        InputStream localInputStream = new ByteArrayInputStream(textAsInputStream.getBytes(StandardCharsets.UTF_8));

        try
        {
            // InputStream localInputStream = getClass().getResourceAsStream(str);
            this.j = localInputStream.read(this.byte_arr_l, 0, this.byte_arr_l.length-1);
            if(this.j < this.byte_arr_l.length-1){
                this.byte_arr_l[this.j] = 0;
            } else {
                Gdx.app.log("DEBUG", " fuck ");
            }
            Gdx.app.log("DEBUG", "text " + paramString + " length "+this.j);
            localInputStream.close();
        }
        catch (Exception localException) {}
        byte_arr_helper();
        this.k = 0;
    }

    public String buildString()
    {
        String str = null;
        int i1 = 0; // dungnv
        for (i1 = this.k; i1 < this.j; i1++)
        {
            if (this.byte_arr_l[i1] == 60)
            {
                this.k = i1;
                return null;
            }
            if (this.byte_arr_l[i1] == 10)
            {
                str = new String(this.byte_arr_l, this.k, i1 - this.k);
                break;
            }
        }
        this.k = (i1 + 1);
        return str;
    }

    /*
     * l[i1] == '<' && screen(i1) == paramInt
     * Calculate integer value inside <?> mark.
     * Return index i1? i1 is not simple index (increment) it may be has value
     */
    public int extractValueFromTxt(int paramInt)
    {
        for (int i1 = 0; i1 < this.j; i1++) {
            if ((this.byte_arr_l[i1] == 60) && (calcIntCharFromASCII(i1) == paramInt)) {
                return i1;
            }
        }
        return -1;
    }

    public void byte_arr_helper()
    {
        this.i = 0;
        for (int i1 = 0; i1 < this.j; i1++) {
            if (this.byte_arr_l[i1] == 10) {
                this.i += 1;
            }
        }
        this.n = -1;
    }
    /**
     * @param paramChar
     * @return
     * eg. + in text "<100>+101 The radar is situated"
     * use : while (a('+'))
     * byte array this.l; &#43 is Dec value of '+' ASCII
     */
    public boolean bool_ascii_helper(char paramChar)
    {
        int i1 = 0; // dungnv
        for (i1 = this.k; i1 < this.j; i1++) // int i1
        {
//      System.out.println(" e this.l arr : " + i1 + " " + this.l[i1]);
            if (this.byte_arr_l[i1] == 10)
            {
                this.k = (i1 - 1);
                return false;
            }
            // this.l is byte array. this.l[i1] is char ? if not then why compare it with paramChar ?
            // if this.l[i1] is char then why it can assigned/compared to 10 ?
            // May be byte-char conversion here
            // A byte is 8 bits, a char is 16 bits
            // 60 Dec: '<'
            // 10: 'NL' new line to 90: 'Z' in AZ-1 (ms1.txt)
            if (this.byte_arr_l[i1] == paramChar) {
                break;
            }
        }
        this.k = i1;
        return true;
    }
}
