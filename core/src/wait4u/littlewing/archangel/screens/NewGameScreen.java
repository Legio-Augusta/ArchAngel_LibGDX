package wait4u.littlewing.archangel.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * Created by Admin on 11/28/2017.
 */

public class NewGameScreen extends DefaultScreen {
    Texture archangel, steelbar_up, steelbar_down;
    Texture select_new_game, select_load_game, select0, select1, select_chose;
    SpriteBatch batch;
    float time = 0;
    // public Music music = Gdx.audio.newMusic(Gdx.files.internal("data/audio/Donald_Christmas.mp3"));

    public NewGameScreen(Game game) {
        super(game);
    }

    @Override
    public void show() {
        // TODO handle screen ratio
        steelbar_up = new Texture(Gdx.files.internal("samsung-white/steelbar_menu_up.png")); // 240x20
        steelbar_down = new Texture(Gdx.files.internal("samsung-white/steelbar_menu_down.png")); // 240x20
        archangel = new Texture(Gdx.files.internal("samsung-white/archangel_text.png")); // 195x30 <-> 877x135

        select0 = new Texture(Gdx.files.internal("samsung-white/select0.png")); // 205x42 <-> 922x189
        select1 = new Texture(Gdx.files.internal("samsung-white/select1.png")); // 205x42 <-> 922x189
        select_chose = new Texture(Gdx.files.internal("samsung-white/select_chose.png")); // 160x26 <-> 720x117
        select_new_game = new Texture(Gdx.files.internal("samsung-white/select_new_game.png")); // 116x17 <-> 522x76
        select_load_game = new Texture(Gdx.files.internal("samsung-white/select_load_game.png")); // 129x17 <-> 580x76

        batch = new SpriteBatch();
    }

    @Override
    public void render(float delta) {
        int SCREEN_HEIGHT = Gdx.graphics.getHeight();
        int SCREEN_WIDTH = Gdx.graphics.getWidth();
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        batch.draw(steelbar_down, 0, 1420+(90 + 135 + 40*2));
        // TODO use ratio for multi-screen size
        batch.draw(archangel, 100, 1420+90+40);
        batch.draw(steelbar_up, 0, 1420);

        batch.draw(select0, 0, SCREEN_HEIGHT/2);
        batch.draw(select1, (SCREEN_WIDTH-select1.getWidth()), 510);
        batch.draw(select_chose, (select0.getWidth()-select_chose.getWidth()-20), SCREEN_HEIGHT/2+60);
        batch.draw(select_new_game, (select0.getWidth()-select_new_game.getWidth()-100), SCREEN_HEIGHT/2+70);
        batch.draw(select_load_game, (SCREEN_WIDTH-select1.getWidth()+70), 510+80);

        batch.draw(steelbar_down, 0, 420);
        batch.draw(steelbar_down, 0, 330);
        batch.end();

        if (Gdx.input.justTouched()) {
            game.setScreen(new GameModeScreen(game));
        }
    }

    @Override
    public void hide() {
        batch.dispose();
        steelbar_down.dispose();
        steelbar_up.dispose();
        archangel.dispose();
        select0.dispose();
        select1.dispose();
        select_chose.dispose();
        select_new_game.dispose();
        select_load_game.dispose();
    }
}
