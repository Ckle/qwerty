package com.coffeede.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.assets.AssetManager;
import com.coffeede.editor.MenuScreen;
import com.coffeede.engine.platform.FileSystem;
import com.coffeede.engine.platform.Platform;
import com.coffeede.engine.platform.stubs.FileSystemStub;
import com.coffeede.engine.screen.BaseGame;
import com.coffeede.engine.ui.UserInterface;

public class QwertyGame extends BaseGame {

	public final boolean editor;

	// Dimensions
	public final int virtualWidth = 640;
	public final int virtualHeight = 1136;
	public final int maxWidth = virtualWidth;
	public final int maxHeight = virtualHeight;

	// Screens
	public MainMenuScreen mainMenuScreen;
	public MenuScreen editorMenuScreen;
	public ChapterSelectScreen chapterSelectScreen;
	public GameplayScreen gameplayScreen;

	// Managers
	public UserInterface ui;

	public QwertyGame(FileSystem fileSystem) {
		editor = !fileSystem.getClass().equals(FileSystemStub.class);
		Platform.files = fileSystem;
	}

	@Override
	public void create () {
		super.create();

		String font = "ui/Interstate-Regular.ttf";
		String skin = "ui/Holo-dark-hdpi.json";

		ui = new UserInterface(this, assets, font, skin);

		gameplayScreen = new GameplayScreen(this);
		chapterSelectScreen = new ChapterSelectScreen(this);

		if (editor) {
			editorMenuScreen = new MenuScreen(this);
		}

		mainMenuScreen = new MainMenuScreen(this);

		this.setScreen(mainMenuScreen);
	}



	@Override
	public void dispose() {
		super.dispose();
	}
}
