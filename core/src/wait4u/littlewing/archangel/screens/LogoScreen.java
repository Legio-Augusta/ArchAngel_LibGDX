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
    public Music music = Gdx.audio.newMusic(Gdx.files.internal("audio/9.mid"));

    public LogoScreen(Game game) {
        super(game);
    }

    @Override
    public void show() {
        // TODO handle screen ratio
        // logo = new TextureRegion(new Texture(Gdx.files.internal("data/samsung-white/logo.png")), 0, 0, 1080, 1122);
        batch = new SpriteBatch();
        // batch.getProjectionMatrix().setToOrtho2D(0, 0, 1080, 1122);
        steelbar_up = new Texture(Gdx.files.internal("samsung-white/steelbar_menu_up.png")); // 240x20
        steelbar_down = new Texture(Gdx.files.internal("samsung-white/steelbar_menu_down.png")); // 240x20
        archangel = new Texture(Gdx.files.internal("samsung-white/archangel_text.png")); // 195x30 <-> 877x135
        logo = new Texture(Gdx.files.internal("samsung-white/logo.png")); // 240x202 <-> 1080x909
        withmobile_n_wait4u = new Texture(Gdx.files.internal("samsung-white/withmobile_wait4u.png")); // 240x20 <-> 1080x90
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        int SCREEN_HEIGHT = Gdx.graphics.getHeight();

        if(music != null) {
            if(!music.isPlaying()) {
                music.play();
                music.setLooping(false);
            }
        }
        batch.begin();
        batch.draw(steelbar_down, 0, 1420+(90 + 135 + 40*2));
        // TODO use ratio for multi-screen size
        batch.draw(archangel, 100, 1420+90+40);
        batch.draw(steelbar_up, 0, 1420);
        batch.draw(logo, 0, 510);
        batch.draw(steelbar_down, 0, 420);
        batch.draw(withmobile_n_wait4u, 0, 420-90 );
        batch.end();

        // TODO Click to start region blink
        if (Gdx.input.isKeyPressed(Input.Keys.ANY_KEY) || Gdx.input.justTouched()) {
//            game.setScreen(new NewGameScreen(game));
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
