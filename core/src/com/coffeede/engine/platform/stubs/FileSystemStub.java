package com.coffeede.engine.platform.stubs;

import com.coffeede.engine.platform.FileSystem;

/**
 * @author Byron
 */
public class FileSystemStub implements FileSystem {

	@Override
	public boolean fileExists(String path) {
		return false;
	}

	@Override
	public boolean saveFile(String path, String data) {
		return false;
	}
}
