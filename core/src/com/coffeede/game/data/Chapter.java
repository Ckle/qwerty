package com.coffeede.game.data;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.XmlReader;
import com.badlogic.gdx.utils.XmlReader.Element;
import com.badlogic.gdx.utils.XmlWriter;
import com.coffeede.engine.platform.Platform;

import java.io.StringWriter;

/**
 * @author Byron
 */
public class Chapter {

	private String path;
	private String text;

	public Chapter(String filePath) {
		path = filePath;
		if (path.length() < 4 || !path.substring(path.length()-4, path.length()).equalsIgnoreCase(".xml")) {
			path += ".xml";
		}
	}


	// File operations

	public boolean exists() {
		return Platform.files.fileExists(path);
	}

	public boolean save() {

		if (text == null || text.equals("")) {
			text = "(empty)";
		}

		StringWriter writer = new StringWriter();

		try {
			XmlWriter xml = new XmlWriter(writer);
			xml.element("chapter")
				.element("text", text)
			.pop();

			String data = writer.toString();
			Platform.files.saveFile(path, data);
			Gdx.app.log("INFO", "Saving " + path);
			return true;
		} catch (Exception e) {
			Gdx.app.log("ERROR", e.getMessage());
			return false;
		}
	}

	public boolean load() {

//		if (!this.exists()) {
//			Gdx.app.log("ERROR", "File '" + path + "' not found");
//			return false;
//		}

		XmlReader xml = new XmlReader();
		try {
			String fullPath = "chapters/" + path;
			Element root = xml.parse(Gdx.files.internal(fullPath));
			text = root.get("text");
			if (text == null) return false;
			return true;
		} catch (Exception e) {
			Gdx.app.log("ERROR", e.getMessage());
			return false;
		}
	}


	// Getters/setters

	public String getPath() {
		return path;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

}
