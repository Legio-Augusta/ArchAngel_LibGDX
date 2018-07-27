package wait4u.littlewing.archangel.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;

import wait4u.littlewing.archangel.OverlapTester;

/**
 * Created by Admin on 11/28/2017.
 */

public class GameModeScreen extends DefaultScreen {
    // TODO handle touch event in button like position
    Texture archangel, steelbar_up, steelbar_down;
    Texture select_easy, select_hard, select0, select1, select_chose;
    Vector3 touchPoint;

    SpriteBatch batch;
    float time = 0;
    public Music music = Gdx.audio.newMusic(Gdx.files.internal("audio/m_briefing.mp3"));

    public GameModeScreen(Game game) {
        super(game);
        touchPoint = new Vector3();
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
        select_easy = new Texture(Gdx.files.internal("samsung-white/select_easy.png")); // 130x17 <-> 585x76
        select_hard = new Texture(Gdx.files.internal("samsung-white/select_hard.png")); // 130x17 <-> 585x76
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

        batch.draw(select0, 0, SCREEN_HEIGHT/2);
        batch.draw(select1, (SCREEN_WIDTH-select1.getWidth()), 510);
        batch.draw(select_chose, (select0.getWidth()-select_chose.getWidth()-20), SCREEN_HEIGHT/2+60);
        batch.draw(select_easy, (select0.getWidth()-select_easy.getWidth()-110), SCREEN_HEIGHT/2+80);
        batch.draw(select_hard, (SCREEN_WIDTH-select1.getWidth()+80), 510+80);

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
            game.setScreen(new BriefOneScreen(game));
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
        select_easy.dispose();
        select_hard.dispose();
    }
}
