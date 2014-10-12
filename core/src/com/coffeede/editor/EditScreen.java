package com.coffeede.editor;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextArea;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.coffeede.engine.screen.BaseScreen;
import com.coffeede.game.QwertyGame;
import com.coffeede.game.data.Chapter;

/**
 * @author Byron
 */
public class EditScreen extends BaseScreen {

	private Chapter chapter;

	private final Table table;
	private final TextField nameField;
	private final TextArea chapterText;

	public EditScreen(final QwertyGame game, final MenuScreen menuScreen) {
		super(game);

		// Build widgets
		table = game.ui.table().top().pad(25);
		nameField = game.ui.textField("untitled", 42, Color.WHITE);
		TextButton saveButton = game.ui.button("Save", 42, onSave);
		chapterText = game.ui.textArea("", 32, Color.WHITE);
		chapterText.setPrefRows(20);
		TextButton backButton = game.ui.button("Back to Editor", 42, menuScreen);

		float margin = 25;
		float textWidth = game.virtualWidth - (margin * 2);
		int buttonWidth = 300;

		table.add(nameField).width(textWidth - 200);
		table.add(saveButton).width(200);
		table.row();
		table.add(chapterText).colspan(2).width(textWidth);
		table.row().padBottom(100);

		table.add(backButton).colspan(2).width(buttonWidth).expandY().bottom();
		table.row();

		stage.addActor(table);
	}

	public void loadChapter(String path) {
		chapter = new Chapter(path);
		if (chapter.load()) {
			nameField.setText(chapter.getPath());
			chapterText.setText(chapter.getText());
		} else {
			error("Failed to load file");
		}
	}

	private ClickListener onSave = new ClickListener() {
		@Override
		public void clicked(InputEvent event, float x, float y) {
			doCheckSave();
		}
	};

	private ClickListener onSaveConfirmed = new ClickListener() {
		@Override
		public void clicked(InputEvent event, float x, float y) {
			doSave();
		}
	};

	private void doCheckSave() {
		String fileName = nameField.getText();
		if (fileName.length() > 0) {
			if (chapter == null) chapter = new Chapter(fileName);
			if (chapter.exists()) {
				TextButton yesButton = game.ui.button("Save", 42, onSaveConfirmed);
				TextButton noButton = game.ui.button("Cancel", 42);
				game.ui.dialog("Overwrite " + chapter.getPath() + "?", 42, yesButton, noButton).show(stage);
			} else {
				doSave();
			}
		}
	}

	private void doSave() {
		chapter.setText(chapterText.getText());
		chapter.save();
	}
}
