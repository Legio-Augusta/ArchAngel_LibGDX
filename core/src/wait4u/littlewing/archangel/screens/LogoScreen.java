package wait4u.littlewing.archangel.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import wait4u.littlewing.archangel.ArchAngelME;

/**
 * Created by Admin on 11/28/2017.
 */

public class LogoScreen extends DefaultScreen {
    // TODO do we need use InputProcessor or just texture ?
    TextureRegion logo2;
    Texture logo, archangel, withmobile_n_wait4u, steelbar_up, steelbar_down;
    SpriteBatch batch;
    float time = 0;
    public Music music = Gdx.audio.newMusic(Gdx.files.internal("audio/m_briefing.wav"));

    public LogoScreen(Game game) {
        super(game);
    }

    @Override
    public void show() {
        // TODO handle screen ratio
        batch = new SpriteBatch();
        // batch.getProjectionMatrix().setToOrtho2D(0, 0, 1080, 1122);
        steelbar_up = new Texture(Gdx.files.internal("archangel/font_4.png")); // 240x20
        steelbar_down = new Texture(Gdx.files.internal("archangel/font_3.png")); // 240x20 <-> 1080x90
        archangel = new Texture(Gdx.files.internal("archangel/font_5.png")); // 195x30 <-> 877x135
        logo = new Texture(Gdx.files.internal("archangel/intro_0.png")); // 240x202 <-> 1080x909
        withmobile_n_wait4u = new Texture(Gdx.files.internal("archangel/withmobile_wait4u.png")); // 240x20 <-> 1080x90
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        int SCREEN_HEIGHT = Gdx.graphics.getHeight();
        int SCREEN_WIDTH = Gdx.graphics.getWidth();
        float SCALE = (float)SCREEN_HEIGHT/1920;

        if(music != null) {
            if(!music.isPlaying()) {
                music.play();
                music.setLooping(false);
            }
        }
        // From bot to top: total ab 1575px => in 1920 we have ab 436 gap top+bot => bot-gap ~ 220px;
        // 90px wait4u + 90 steelbar + 909 logo + 90 bar + 40 gap + 134 archangel + 40 gap + 90 bar
        int BOTTOM = (int)(220*SCALE);
        batch.begin();
        int logo_y = (int) (BOTTOM + (steelbar_down.getHeight() + withmobile_n_wait4u.getHeight())*SCALE);
        batch.draw(steelbar_down, 0, logo_y + (logo.getHeight() + 90 + 135 + 40*2) * SCALE, steelbar_down.getWidth()*SCALE, steelbar_down.getHeight()*SCALE);
        batch.draw(archangel, 100*SCALE, logo_y + (logo.getHeight() + 90+40)*SCALE, archangel.getWidth()*SCALE, archangel.getHeight()*SCALE);
        batch.draw(steelbar_up, 0, logo_y + logo.getHeight()*SCALE, SCREEN_WIDTH, steelbar_up.getHeight()*SCALE);
        batch.draw(logo, 0, logo_y, SCREEN_WIDTH, logo.getHeight()*SCALE);
        batch.draw(steelbar_down, 0, BOTTOM + withmobile_n_wait4u.getHeight()*SCALE, SCREEN_WIDTH, steelbar_down.getHeight()*SCALE);
        batch.draw(withmobile_n_wait4u, 0, BOTTOM, SCREEN_WIDTH, withmobile_n_wait4u.getHeight()*SCALE );
        batch.end();

        if (Gdx.input.justTouched()) { // Gdx.input.isKeyPressed(Input.Keys.ANY_KEY)
            ArchAngelME archAngel = new ArchAngelME();
            game.setScreen(new GameScreen(archAngel, game));
        }
    }

    @Override
    public void hide() {
        batch.dispose();
        logo.dispose();
        music.dispose();
        steelbar_down.dispose();
        steelbar_up.dispose();
        archangel.dispose();
        withmobile_n_wait4u.dispose();
    }

}
