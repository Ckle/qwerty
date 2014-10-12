package com.coffeede.qwerty.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.coffeede.engine.platform.stubs.FileSystemStub;
import com.coffeede.game.QwertyGame;

public class DesktopLauncher {

	public static void main (String[] arg) {

		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		com.coffeede.game.QwertyGame game = new QwertyGame(new DesktopFileSystem());

		// Desktop-specific application configuration
		config.title = "Qwerty";
		config.width = game.maxWidth/4*3;
		config.height = game.maxHeight/4*3;
		config.vSyncEnabled = true;

		new LwjglApplication(game, config);
	}

}
