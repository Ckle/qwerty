package com.coffeede.engine.screen;

import com.badlogic.gdx.Application.ApplicationType;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.coffeede.game.QwertyGame;

/**
 * @author Byron
 */
public abstract class BaseScreen extends InputAdapter implements Screen {

	protected QwertyGame game;
	protected Viewport viewport;
	protected Camera camera;
	protected Stage stage;
	protected Batch batch;

	public BaseScreen(final QwertyGame game) {
		this.game = game;
		camera = new OrthographicCamera();
		viewport = new ExtendViewport(game.virtualWidth, game.virtualHeight, game.maxWidth, game.maxHeight, camera);
		batch = new SpriteBatch();
		stage = new Stage(viewport, batch);
	}

	// Override in subclass
	protected void loop(float delta) {
		this.clear();
	}

	// Logs an error and shows a popup
	protected void error(String message) {
		Gdx.app.log("ERROR", message);
		game.ui.dialog(message, 42).show(stage);
	}

	protected void clear() {
		this.clear(Color.BLACK);
	}

	protected void clear(Color color) {
		Gdx.gl.glClearColor(color.r, color.g, color.b, color.a);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
	}

	@Override
	public void render(float delta) {
		loop(delta);
		stage.act(delta);
		stage.draw();
	}

	@Override
	public void show() {
		game.multiplexer.clear();
		game.multiplexer.addProcessor(this);
		game.multiplexer.addProcessor(stage);
	}

	@Override
	public void hide() {

	}

	@Override
	public void pause() {

	}

	@Override
	public void resume() {

	}

	@Override
	public void dispose() {
		stage.dispose();
		batch.dispose();
	}

	@Override
	public void resize(int width, int height) {
		viewport.update(width, height, true);
	}

	@Override
	public boolean keyDown(int keycode) {

		// Desktop debug functions
		if (Gdx.app.getType() == ApplicationType.Desktop) {
			if (keycode == Input.Keys.ESCAPE) {
				Gdx.app.exit();
				return true;
			} else if (keycode == Input.Keys.F1) {
				stage.setDebugUnderMouse(true);
			}
		}
		return false;
	}

}
