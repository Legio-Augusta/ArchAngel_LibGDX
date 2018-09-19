package wait4u.littlewing.archangel;

import com.badlogic.gdx.Game;

import wait4u.littlewing.archangel.screens.LogoScreen;

/**
 * Created by nickfarow on 13/10/2016.
 */
public class ArchAngel extends Game {

    public void create () {
        setScreen(new LogoScreen(this));
    }

}