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

public class MissionOneBriefing extends DefaultScreen {
    // TODO handle touch event in button like position
    Texture archangel, steelbar_up, steelbar_down;
    Texture brief0_map, brief_pilot;
    BitmapFont font;
    Vector3 touchPoint;

    SpriteBatch batch;
    float time = 0;

    public MissionOneBriefing(Game game) {
        super(game);
        touchPoint = new Vector3();
    }

    @Override
    public void show() {
        font = new BitmapFont();
        font.getRegion().getTexture().setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        font.getData().setScale(3);

        // TODO handle screen ratio
        steelbar_up = new Texture(Gdx.files.internal("data/samsung-white/steelbar_menu_up.png")); // 240x20
        steelbar_down = new Texture(Gdx.files.internal("data/samsung-white/steelbar_menu_down.png")); // 240x20
        archangel = new Texture(Gdx.files.internal("data/samsung-white/archangel_text.png")); // 195x30 <-> 877x135

        brief0_map = new Texture(Gdx.files.internal("data/samsung-white/brief0_map.png")); // 167x79 <-> 751x356
        brief_pilot = new Texture(Gdx.files.internal("data/samsung-white/brief_pilot.png")); // 52x63 <-> 234x284
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

        batch.draw(brief0_map, 20, (1420-brief0_map.getHeight()));
        font.draw(batch, "In Dark Eden an area in " +
                "the Nekkar desert, the\n" +
                "enemy has concentrated " +
                "its troops to prepare \n" +
                "for war. You will face " +
                "a group of 10 AZ-1\n" +
                "combat units, which you " +
                "need to destroy. Then, " +
                "follow the directions\n" +
                "of your navigator for " +
                "60 km, which will leads\n" +
                "you directly to their " +
                "newly developed AL-101" +
                "air fighter...\n" +
                "Destroy it!", 110, 720);

        batch.draw(brief_pilot, SCREEN_WIDTH-brief_pilot.getWidth(), 720+brief_pilot.getHeight());
        batch.draw(steelbar_down, 0, steelbar_down.getHeight());
        batch.draw(steelbar_down, 0, 0);
        batch.end();

        if (Gdx.input.justTouched()) {
            game.setScreen(new GameScreen(game, 6, 1));
        }
    }

    @Override
    public void hide() {
        batch.dispose();
        steelbar_down.dispose();
        steelbar_up.dispose();
        archangel.dispose();
        brief0_map.dispose();
        brief_pilot.dispose();
        font.dispose();
    }
}
