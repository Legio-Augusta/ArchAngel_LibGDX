package wait4u.littlewing.archangel;

public class GameConfig
{
    public int a = this.b = this.c = this.d = this.e = this.f = this.g = this.h = this.i = this.j = this.k = this.l = this.m = 0;
    public int b;
    public int c;
    public int d;
    public int e;
    public int f;
    public int g;
    public int h;
    public int i;
    public int j;
    public int k;
    public int l;
    public int m;
    public boolean bool_n = false;

    public String debug() {
        String debugMsg = "";
        debugMsg = (a != 0) ? debugMsg + " a = " + a : debugMsg;
        debugMsg = (b != 0) ? debugMsg + " b = " + b : debugMsg;
        debugMsg = (c != 0) ? debugMsg + " c = " + c : debugMsg;
        debugMsg = (d != 0) ? debugMsg + " d = " + d : debugMsg;
        debugMsg = (e != 0) ? debugMsg + " e = " + e : debugMsg;
        debugMsg = (f != 0) ? debugMsg + " f = " + f : debugMsg;
        debugMsg = (g != 0) ? debugMsg + " g = " + g : debugMsg;
        debugMsg = (h != 0) ? debugMsg + " h = " + h : debugMsg;
        debugMsg = (i != 0) ? debugMsg + " i = " + i : debugMsg;
        debugMsg = (j != 0) ? debugMsg + " j = " + j : debugMsg;
        debugMsg = (k != 0) ? debugMsg + " k = " + k : debugMsg;
        debugMsg = (l != 0) ? debugMsg + " l = " + l : debugMsg;
        debugMsg = (m != 0) ? debugMsg + " m = " + m : debugMsg;
        debugMsg = debugMsg + " bool_n = " + bool_n;

        return debugMsg;
    }
    public String debug2() {
        return " a = " + a + " b= "+b + " c = " + c + " d= " +d + " e =" + e + " f = " + f + " g = " +g + " h = "+ h +" i = "+ i + " j= " +j
                + " k = "+ k + " l= "+ l + " m= " + m + " bool_n= "+ bool_n;
    }
}
