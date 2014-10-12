package com.coffeede.editor;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.coffeede.engine.screen.BaseScreen;
import com.coffeede.game.QwertyGame;

/**
 * @author Byron
 */
public class MenuScreen extends BaseScreen {

	protected final EditScreen editScreen;
	protected final FileScreen fileScreen;

	public MenuScreen(final QwertyGame game) {
		super(game);

		editScreen = new EditScreen(game, this);
		fileScreen = new FileScreen(game, this);

		// Build widgets
		Table table = game.ui.table().top();
		Label titleLabel = game.ui.label("Chapter Editor", 60, Color.YELLOW);
		TextButton newButton = game.ui.button("New", 60);
		TextButton loadButton = game.ui.button("Load", 60);
		TextButton backButton = game.ui.button("Back to Game", 42, game.mainMenuScreen);

		int buttonWidth = 400;

		table.add(titleLabel).height(500);
		table.row();
		table.add(newButton).width(buttonWidth);
		table.row();
		table.add(loadButton).width(buttonWidth);
		table.row();
		table.add(backButton).width(buttonWidth).padTop(200);
		table.row();

		stage.addActor(table);

		newButton.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				editScreen.makeEmpty();
				game.setScreen(editScreen);
			}
		});

		loadButton.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				fileScreen.refresh();
				game.setScreen(fileScreen);
			}
		});
	}

}
