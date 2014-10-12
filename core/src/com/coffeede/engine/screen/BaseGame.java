package com.coffeede.engine.screen;

import com.badlogic.gdx.*;
import com.badlogic.gdx.assets.AssetManager;
import com.coffeede.engine.ui.UserInterface;

/**
 * @author Byron
 */
public class BaseGame implements ApplicationListener {

	public AssetManager assets;
	public InputMultiplexer multiplexer;
	public ScreenManager screens;

	@Override
	public void create() {
		assets = new AssetManager();
		screens = new ScreenManager(this);

		multiplexer = new InputMultiplexer();
		Gdx.input.setInputProcessor(multiplexer);
	}

	// Screen management

	private Screen screen;

	public void setScreen(Screen screen) {
		if (this.screen != null) {
			this.screen.hide();
		}
		this.screen = screen;
		this.screen.show();
		this.screen.resize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
	}

	public void setScreenWithTransition(Screen screen) {
		TransitionScreen transition = new TransitionScreen(this, this.screen, screen);
		this.setScreen(transition);
	}

	@Override
	public void resize(int width, int height) {
		if (screen != null) {
			screen.resize(width, height);
		}
	}

	@Override
	public void render() {
		if (screen != null) {
			screen.render(Gdx.graphics.getDeltaTime());
		}
	}

	@Override
	public void pause() {
		if (screen != null) {
			screen.pause();
		}
	}

	@Override
	public void resume() {
		if (screen != null){
			screen.resume();
		}
	}

	@Override
	public void dispose() {
		if (screen != null) {
			screen.dispose();
		}
		screens.dispose();
		assets.dispose();
	}
}
