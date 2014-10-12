package com.coffeede.qwerty.android;

import android.os.Bundle;

import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;
import com.coffeede.engine.platform.stubs.FileSystemStub;
import com.coffeede.game.QwertyGame;

public class AndroidLauncher extends AndroidApplication {

	@Override
	protected void onCreate (Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();

		initialize(new QwertyGame(new FileSystemStub()), config);
	}

}
