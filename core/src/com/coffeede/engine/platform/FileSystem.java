package com.coffeede.engine.platform;

/**
 * @author Byron
 */
public interface FileSystem {

	public boolean fileExists(String path);
	public boolean saveFile(String path, String data);

}
