package com.coffeede.engine.screen;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.IntMap;

/**
 * @author Byron
 */
public class ScreenManager implements Disposable {

	protected BaseScreen screen;

	private BaseGame game;
	private IntMap<BaseScreen> screens;

	public ScreenManager(BaseGame game) {
		this.game = game;
		screens = new IntMap<BaseScreen>();
	}

	@Override
	public void dispose() {

	}
}
