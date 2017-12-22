package wait4u.littlewing.archangel;

import com.badlogic.gdx.Game;
import wait4u.littlewing.archangel.screens.GameScreen;
import wait4u.littlewing.archangel.screens.LogoScreen;
import wait4u.littlewing.archangel.screens.VictoryScreen;

/**
 * Created by nickfarow on 13/10/2016.
 */
public class ArchAngel extends Game {

    public void create () {
        // TODO handle global vars in GameScreen. setScreen as repaint J2ME
        // Can put global var here ?
        setScreen(new LogoScreen(this));
//        setScreen(new GameScreen(new SnowBallFight()));
//        setScreen(new GameScreen(this));
//        setScreen(new VictoryScreen(this));
    }

}