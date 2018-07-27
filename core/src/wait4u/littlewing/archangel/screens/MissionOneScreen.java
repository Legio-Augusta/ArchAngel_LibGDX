package wait4u.littlewing.archangel.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Music;
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

public class MissionOneScreen extends DefaultScreen {
    // TODO handle touch event in button like position
    Texture archangel, steelbar_up, steelbar_down;
    Texture menu_highlight, menu_mission;
    Vector3 touchPoint;
    BitmapFont font;

    SpriteBatch batch;
    float time = 0;
    public Music music = Gdx.audio.newMusic(Gdx.files.internal("audio/FF_done_sound.wav"));

    public MissionOneScreen(Game game) {
        super(game);
        touchPoint = new Vector3();
    }

    @Override
    public void show() {
        font = new BitmapFont();
        font.getRegion().getTexture().setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        font.getData().setScale(6);

        // TODO handle screen ratio
        steelbar_up = new Texture(Gdx.files.internal("samsung-white/steelbar_menu_up.png")); // 240x20
        steelbar_down = new Texture(Gdx.files.internal("samsung-white/steelbar_menu_down.png")); // 240x20
        archangel = new Texture(Gdx.files.internal("samsung-white/archangel_text.png")); // 195x30 <-> 877x135
        menu_mission = new Texture(Gdx.files.internal("samsung-white/menu_mission.png")); // 206x24 <-> 827x108
        menu_highlight = new Texture(Gdx.files.internal("samsung-white/menu_highlight.png")); // 158x20 <-> 711x90

        batch = new SpriteBatch();
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        int SCREEN_HEIGHT = Gdx.graphics.getHeight();
        int SCREEN_WIDTH = Gdx.graphics.getWidth();

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

        batch.draw(menu_mission, 184, 1420); // TODO scale ratio 1080/240 or SCREEN_WIDTH/240
        batch.draw(menu_highlight, 75, 1220);
        font.draw(batch, "Mission start", 321, 1320);

        batch.draw(steelbar_down, 0, 420);
        batch.draw(steelbar_down, 0, 330);
        batch.end();

        /*
        if (Gdx.input.justTouched()) {
            // TODO use global var ie. screen or just pass value in constructor
            touchPoint.set(Gdx.input.getX(),Gdx.input.getY(), 0);
            Rectangle selectButtonBound = new Rectangle(SCREEN_WIDTH-imgPl.getWidth(), 480, imgPl.getWidth(), imgPl.getHeight());
            Rectangle backButtonBound = new Rectangle(0, 480, imgBk.getWidth(), imgBk.getHeight());

            Gdx.app.log("INFO", "touch " + touchPoint.x + " y "+ (SCREEN_HEIGHT-touchPoint.y) + " bound x "+ selectButtonBound.toString());
            // Adjust coordinate and geometry vector touch follow GDX anchor.
            if(OverlapTester.pointInRectangle(selectButtonBound, touchPoint.x, (SCREEN_HEIGHT-touchPoint.y) )) {
                game.setScreen(new NewGameMenuScreen(game));
            } else if(OverlapTester.pointInRectangle(backButtonBound, touchPoint.x, (SCREEN_HEIGHT-touchPoint.y) )) {
                // options
                //game.setScreen(new VillageScreen(game)); // Configurations screen
            }
        }*/
        if (Gdx.input.justTouched()) {
            game.setScreen(new MissionOneBriefing(game));
        }
    }

    @Override
    public void hide() {
        batch.dispose();
        steelbar_down.dispose();
        steelbar_up.dispose();
        archangel.dispose();
    }
}
