package pjkck.dungeondaredevil.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.FitViewport;

import pjkck.dungeondaredevil.GamDungeonDaredevil;
import pjkck.dungeondaredevil.utils.InputManager;

public class ScrLoadingscreen implements Screen {

    private Texture txImg;
    private GamDungeonDaredevil game;
    private FitViewport port;
    private OrthographicCamera cam;

    private SpriteBatch batch;

    private float fElapsedTime;

    private InputManager inputManager;

    public ScrLoadingscreen(GamDungeonDaredevil game, SpriteBatch batch) {
        this.game = game;
        this.batch = batch;

        cam = new OrthographicCamera();
        port = new FitViewport(1920, 1080, cam);
        cam.position.set(port.getWorldWidth()/2, port.getWorldHeight()/2, 0);

        txImg = new Texture("textures/LoadingScreen.png");

        inputManager = new InputManager();
        Gdx.input.setInputProcessor(inputManager);

        fElapsedTime = 0;
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        fElapsedTime += delta;
        cam.update();
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.setProjectionMatrix(cam.combined);
        batch.begin();
        batch.draw(txImg, 0, 0);
        batch.end();
        // Show splash screen for 5 seconds than switch to menu
        if (fElapsedTime >= 5 || inputManager.isKeyPressed(Input.Keys.SPACE)) {
            game.updateState(1);
            dispose();
        }
    }

    @Override
    public void resize(int width, int height) {
        port.update(width, height);
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
    }
}
