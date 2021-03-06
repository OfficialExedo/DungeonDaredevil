package pjkck.dungeondaredevil.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.utils.viewport.FitViewport;

import pjkck.dungeondaredevil.GamDungeonDaredevil;

public class ScrMenu implements Screen {

    private Texture txImg;

    private GamDungeonDaredevil game;
    private FitViewport port;
    private OrthographicCamera cam;

    private SpriteBatch batch;

    private Stage stage;
    private TextButton playButton;

    private Music bgMusic;

    public ScrMenu(GamDungeonDaredevil game, SpriteBatch batch) {
        this.game = game;
        this.batch = batch;

        cam = new OrthographicCamera();
        port = new FitViewport(1920, 1080, cam);
        cam.position.set(port.getWorldWidth() / 2, port.getWorldHeight() / 2, 0);

        stage = new Stage(port);
        Skin skin = new Skin(Gdx.files.internal("skins/uiskin.json"));
        playButton = new TextButton("Play", skin);
        playButton.setSize(300, 100);
        playButton.getLabel().setSize(300, 100);
        playButton.setPosition(port.getWorldWidth() / 2 - playButton.getWidth() / 2, port.getWorldHeight() / 2 - playButton.getHeight() / 2);
        stage.addActor(playButton);
        Gdx.input.setInputProcessor(stage);

        txImg = new Texture("textures/titlescreen.png");

        bgMusic = Gdx.audio.newMusic(Gdx.files.internal("sounds/titlescreen.wav"));
        bgMusic.setLooping(true);
        bgMusic.play();
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        cam.update();
        stage.act(delta);

        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.setProjectionMatrix(cam.combined);
        batch.begin();
        batch.draw(txImg, 0, 0);
        batch.end();

        stage.getBatch().setProjectionMatrix(cam.combined);
        stage.draw();

        if (playButton.isPressed() || Gdx.input.isKeyJustPressed(Input.Keys.SPACE)) {
            bgMusic.stop();
            game.updateState(2);
        }
    }

    @Override
    public void resize(int width, int height) {
        port.update(width, height);
        stage.getViewport().update(width, height);
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        txImg.dispose();
        stage.dispose();
    }
}
