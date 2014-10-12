package com.coffeede.qwerty;

import com.coffeede.engine.platform.stubs.FileSystemStub;
import org.robovm.apple.foundation.NSAutoreleasePool;
import org.robovm.apple.uikit.UIApplication;

import com.badlogic.gdx.backends.iosrobovm.IOSApplication;
import com.badlogic.gdx.backends.iosrobovm.IOSApplicationConfiguration;
import com.coffeede.game.QwertyGame;

public class IOSLauncher extends IOSApplication.Delegate {
    @Override
    protected IOSApplication createApplication() {
        IOSApplicationConfiguration config = new IOSApplicationConfiguration();

	    // iOS-specific application configuration
	    config.useAccelerometer = false;
	    config.useCompass = false;
	    config.orientationLandscape = false;
	    config.orientationPortrait = true;

        return new IOSApplication(new QwertyGame(new FileSystemStub()), config);
    }

    public static void main(String[] argv) {
        NSAutoreleasePool pool = new NSAutoreleasePool();
        UIApplication.main(argv, null, IOSLauncher.class);
        pool.close();
    }
}