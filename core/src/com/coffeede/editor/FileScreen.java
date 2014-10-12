package com.coffeede.editor;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Array;
import com.coffeede.engine.screen.BaseScreen;
import com.coffeede.game.QwertyGame;
import net.dermetfan.gdx.scenes.scene2d.ui.FileChooser.Listener;
import net.dermetfan.gdx.scenes.scene2d.ui.TreeFileChooser;

/**
 * @author Byron
 */
public class FileScreen extends BaseScreen {

	private final MenuScreen menuScreen;
	private final Table table;
	private TreeFileChooser fileChooser;

	public FileScreen(final QwertyGame game, final MenuScreen menuScreen) {
		super(game);
		this.menuScreen = menuScreen;

		// Build widgets
		table = game.ui.table().top();
		Label titleLabel = game.ui.label("Load Chapter", 60, Color.YELLOW);

		table.add(titleLabel).height(500);
		table.row();

		stage.addActor(table);

		this.refresh();
	}

	public void refresh() {
		if (fileChooser != null) {
			table.removeActor(fileChooser);
		}

		fileChooser = game.ui.fileChooser(42, Color.WHITE, "chapters", new Listener() {
			@Override
			public void choose(FileHandle file) {
				menuScreen.editScreen.loadChapter(file.name());
				game.setScreen(menuScreen.editScreen);
			}

			@Override
			public void choose(Array<FileHandle> files) {
				if (files.size == 1) {
					menuScreen.editScreen.loadChapter(files.get(0).name());
					game.setScreen(menuScreen.editScreen);
				}
			}

			@Override
			public void cancel() {
				game.setScreen(menuScreen);
			}
		});

		table.add(fileChooser).width(500);
		table.row();
	}

}
