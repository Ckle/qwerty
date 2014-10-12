package com.coffeede.qwerty.desktop;

import com.coffeede.engine.platform.FileSystem;

import java.io.*;


/**
 * @author Byron
 */
public class DesktopFileSystem implements FileSystem {

	private final String BASE_PATH = "chapters/";

	@Override
	public boolean fileExists(String path) {
		File touch = new File(BASE_PATH + path);
		return touch.exists();
	}

	@Override
	public boolean saveFile(String path, String data) {

		Writer writer = null;
		path = BASE_PATH + path;

		try {
			try {
				writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(path), "utf-8"));
				writer.write(data);
			} catch (IOException e) {
				e.printStackTrace();
				return false;
			} finally {
				if (writer != null) {
					writer.close();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		return true;
	}

}
