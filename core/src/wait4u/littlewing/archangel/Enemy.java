package wait4u.littlewing.archangel;

public class Enemy
{
    public int enemy_distance_1 = this.enemy_distance_2 = this.gameplay_ctl = this.d = this.e = this.f = this.g = this.enemy_step = this.enemy_step2 = this.j = this.k = this.l = this.enemy_damage = 0;
    public int enemy_distance_2;
    public int gameplay_ctl; // related to boss distance calc and/or game state
    public int d; // Related to draw/appear enemies, fence
    public int e; // related to fly ai
    public int f;
    public int g; // boss ai
    public int enemy_step; // boss/enemy distance calc, turn step
    public int enemy_step2; // boss dist calc
    public int j; // distance
    public int k;
    public int l; // related to boss finding
    public int enemy_damage; // m: enemy damage or boss_hp
    public boolean bool_n = false;

    public String debug() {
        String debugMsg = "";
        debugMsg = (enemy_distance_1 != 0) ? debugMsg + " enemy_distance_1 = " + enemy_distance_1 : debugMsg;
        debugMsg = (enemy_distance_2 != 0) ? debugMsg + " enemy_distance_2 = " + enemy_distance_2 : debugMsg;
        debugMsg = (gameplay_ctl != 0) ? debugMsg + " c = " + gameplay_ctl : debugMsg;
        debugMsg = (d != 0) ? debugMsg + " d = " + d : debugMsg;
        debugMsg = (e != 0) ? debugMsg + " e = " + e : debugMsg;
        debugMsg = (f != 0) ? debugMsg + " direction_guide = " + f : debugMsg;
        debugMsg = (g != 0) ? debugMsg + " g = " + g : debugMsg;
        debugMsg = (enemy_step != 0) ? debugMsg + " h = " + enemy_step : debugMsg;
        debugMsg = (enemy_step2 != 0) ? debugMsg + " i = " + enemy_step2 : debugMsg;
        debugMsg = (j != 0) ? debugMsg + " fighter_hp = " + j : debugMsg;
        debugMsg = (k != 0) ? debugMsg + " k = " + k : debugMsg;
        debugMsg = (l != 0) ? debugMsg + " l = " + l : debugMsg;
        debugMsg = (enemy_damage != 0) ? debugMsg + " m = " + enemy_damage : debugMsg;
        debugMsg = debugMsg + " bool_n = " + bool_n;

        return debugMsg;
    }
}
