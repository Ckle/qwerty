package com.coffeede.game;

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
public class MainMenuScreen extends BaseScreen {

	public MainMenuScreen(final QwertyGame game) {
		super(game);

		// Build widgets
		Table table = game.ui.table().top();
		Label titleLabel = game.ui.label("Qwerty", 60, Color.YELLOW);
		TextButton startButton = game.ui.button("Play", 60);
		TextButton editorButton = game.ui.button("Editor", 60/*, game.editorMenuScreen*/);

		int buttonWidth = 300;

		table.add(titleLabel).height(500);
		table.row();
		table.add(startButton).width(buttonWidth);
		table.row();

		if (game.editor) {
			table.add(editorButton).width(buttonWidth);
			table.row();
		}

		startButton.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				game.chapterSelectScreen.populate();
				game.setScreen(game.chapterSelectScreen);
//				game.setScreenWithTransition(game.chapterSelectScreen);
			}
		});

		editorButton.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				game.setScreen(game.editorMenuScreen);
//				game.setScreenWithTransition(game.editorMenuScreen);
			}
		});

		stage.addActor(table);
	}

}
