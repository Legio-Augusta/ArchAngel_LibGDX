package wait4u.littlewing.archangel.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;

import wait4u.littlewing.archangel.OverlapTester;

/**
 * Created by Nick Farrow on 07/10/2018.
 */

public class BriefOneScreen extends DefaultScreen {
    // TODO handle touch event in button like position
    Texture archangel, steelbar_up, steelbar_down;
    Texture open0;
    BitmapFont font;
    Vector3 touchPoint;

    SpriteBatch batch;
    float time = 0;

    public BriefOneScreen(Game game) {
        super(game);
        touchPoint = new Vector3();
    }

    @Override
    public void show() {
        font = new BitmapFont();
        font.getRegion().getTexture().setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        font.getData().setScale(3);

        // TODO handle screen ratio
        steelbar_up = new Texture(Gdx.files.internal("samsung-white/steelbar_menu_up.png")); // 240x20
        steelbar_down = new Texture(Gdx.files.internal("samsung-white/steelbar_menu_down.png")); // 240x20
        archangel = new Texture(Gdx.files.internal("samsung-white/archangel_text.png")); // 195x30 <-> 877x135

        open0 = new Texture(Gdx.files.internal("samsung-white/open0.png")); // 240x146 <-> 1080x657
        batch = new SpriteBatch();
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        int SCREEN_HEIGHT = Gdx.graphics.getHeight();
        int SCREEN_WIDTH = Gdx.graphics.getWidth();

        batch.begin();
        batch.draw(steelbar_down, 0, 1420+(90 + 135 + 40*2));
        // TODO use ratio for multi-screen size
        batch.draw(archangel, 100, 1420+90+40);
        batch.draw(steelbar_up, 0, 1420);

        batch.draw(open0, 0, (1420-open0.getHeight()));
        font.draw(batch, "The year 2028...\n" +
                "A conflict between" +
                "the Earth and the\n" +
                "other planets has" +
                "led the worlds to \n" +
                "the edge of a final " +
                "war which threatens\n" +
                "man's very survival." +
                "In a struggle to end\n" +
                "the conflict, the " +
                "confederation has\n" +
                "developed a " +
                "revolutionary land-" +
                "sea-air-space unit \n" +
                "in a top secret " +
                "operation.\n" +
                "Due to the fact that " +
                "this unit looks like \n" +
                "a fire-spitting angel " +
                "its code name is " +
                "'Arch Angel'.", 110, 720);

        batch.draw(steelbar_down, 0, steelbar_down.getHeight());
        batch.draw(steelbar_down, 0, 0);
        batch.end();

        if (Gdx.input.justTouched()) {
            game.setScreen(new MissionOneScreen(game));
        }
    }

    @Override
    public void hide() {
        batch.dispose();
        steelbar_down.dispose();
        steelbar_up.dispose();
        archangel.dispose();
        open0.dispose();
        font.dispose();
    }
}
