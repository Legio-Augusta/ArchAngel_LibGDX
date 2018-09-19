package wait4u.littlewing.archangel;

public class Enemy
{
    public int enemy_distance_1 = this.enemy_distance_2 = this.c = this.d = this.e = this.f = this.g = this.h = this.i = this.j = this.k = this.l = this.m = 0;
    public int enemy_distance_2;
    public int c; // related to boss distance calc
    public int d;
    public int e; // related to fly ai
    public int f;
    public int g;
    public int h; // boss distance calc, turn step
    public int i; // boss dist calc
    public int j; // distance
    public int k;
    public int l; // related to boss finding
    public int m;
    public boolean bool_n = false;

    public String debug() {
        String debugMsg = "";
        debugMsg = (enemy_distance_1 != 0) ? debugMsg + " enemy_distance_1 = " + enemy_distance_1 : debugMsg;
        debugMsg = (enemy_distance_2 != 0) ? debugMsg + " enemy_distance_2 = " + enemy_distance_2 : debugMsg;
        debugMsg = (c != 0) ? debugMsg + " c = " + c : debugMsg;
        debugMsg = (d != 0) ? debugMsg + " d = " + d : debugMsg;
        debugMsg = (e != 0) ? debugMsg + " e = " + e : debugMsg;
        debugMsg = (f != 0) ? debugMsg + " f = " + f : debugMsg;
        debugMsg = (g != 0) ? debugMsg + " g = " + g : debugMsg;
        debugMsg = (h != 0) ? debugMsg + " h = " + h : debugMsg;
        debugMsg = (i != 0) ? debugMsg + " i = " + i : debugMsg;
        debugMsg = (j != 0) ? debugMsg + " fighter_hp = " + j : debugMsg;
        debugMsg = (k != 0) ? debugMsg + " k = " + k : debugMsg;
        debugMsg = (l != 0) ? debugMsg + " l = " + l : debugMsg;
        debugMsg = (m != 0) ? debugMsg + " m = " + m : debugMsg;
        debugMsg = debugMsg + " bool_n = " + bool_n;

        return debugMsg;
    }
}
