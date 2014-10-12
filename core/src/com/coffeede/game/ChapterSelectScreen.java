package com.coffeede.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.coffeede.engine.screen.BaseScreen;

/**
 * @author Byron
 */
public class ChapterSelectScreen extends BaseScreen {

	private Table table;

	public ChapterSelectScreen(final QwertyGame game) {
		super(game);
	}

	public void populate() {
		if (table != null) {
			table.remove();
		}

		// Build widgets
		table = game.ui.table().top();
		Label titleLabel = game.ui.label("Chapters", 60, Color.YELLOW);

		table.add(titleLabel).height(500);
		table.row();

		int buttonWidth = 300;

		FileHandle[] files = Gdx.files.internal("chapters").list("xml");
		for (final FileHandle file : files) {
			TextButton chapterButton = game.ui.button(file.nameWithoutExtension(), 60);

			table.add(chapterButton).width(buttonWidth);
			table.row();

			// Button callback
			chapterButton.addListener(new ClickListener() {
				@Override
				public void clicked(InputEvent event, float x, float y) {
					game.gameplayScreen.load(file.name());
					game.setScreen(game.gameplayScreen);
				}
			});
		}

		stage.addActor(table);
	}

}
