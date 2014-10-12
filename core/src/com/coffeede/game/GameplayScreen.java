package com.coffeede.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.BitmapFont.HAlignment;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.coffeede.engine.screen.BaseScreen;
import com.coffeede.game.data.Chapter;

import java.util.concurrent.TimeUnit;

/**
 * @author Byron
 */
public class GameplayScreen extends BaseScreen {

	private Label timerLabel;
	private TextButton backButton;
	private BitmapFont font;
	private Table table;

	public GameplayScreen(final QwertyGame game) {
		super(game);
		font = game.ui.font(32);
	}

	private String storyText;
	private String renderText;
	private int timeLeft;

	private char currentLetter;
	private int currentLetterIndex;
	private boolean gameOver;

	public void load(String path) {
		Chapter chapter = new Chapter(path);
		chapter.load();

		if (table != null) {
			table.remove();
			if (backButton != null) {
				backButton.remove();
			}
		}

		storyText = chapter.getText();
		renderText = "[GREEN]" + storyText.substring(0, 1) + "[]" + storyText.substring(1, storyText.length());
		timeLeft = 45000;

		currentLetterIndex = 0;
		currentLetter = storyText.charAt(currentLetterIndex);
		gameOver = false;

		table = game.ui.table().top();
		timerLabel = game.ui.label(60, Color.YELLOW);

		table.row().padTop(100);
		table.add(timerLabel);
		table.row();

		stage.addActor(table);
	}

	private void addBackButton() {
		backButton = game.ui.button("Back", 42, game.chapterSelectScreen);
		table.addActor(backButton);
		table.row();
		Gdx.input.setOnscreenKeyboardVisible(false);
	}

	@Override
	public boolean keyTyped(char character) {
		String punctuations = ".,:;'/\"!'[]+-@#$%^&*()_-=`~";
		if (Character.isLetterOrDigit(character) || Character.isSpaceChar(character) || punctuations.contains(character + "")) {

		} else {
			return false;
		}
		if (currentLetterIndex >= storyText.length() - 1) {
			renderText = "You win!";
			gameOver = true;
			addBackButton();
		} else if (!gameOver) {
			String color;
			if (character == currentLetter) {
				currentLetterIndex++;
				color = "[GREEN]";
			} else {
				color = "[RED]";
			}
			currentLetter = storyText.charAt(currentLetterIndex);
			String before = storyText.substring(0, currentLetterIndex);
			String after = storyText.substring(currentLetterIndex + 1, storyText.length());
			String current = storyText.substring(currentLetterIndex, currentLetterIndex + 1);
			current = color + current + "[]";
			renderText = before + current + after;
			return true;
		}
		return false;
	}

	@Override
	public void show() {
		super.show();
		Gdx.input.setOnscreenKeyboardVisible(true);
	}

	@Override
	public void hide() {
		super.hide();
		Gdx.input.setOnscreenKeyboardVisible(false);
	}

	@Override
	public void loop(float delta) {
		this.clear();

		batch.begin();
		batch.setProjectionMatrix(viewport.getCamera().combined);

		float margin = 25;
		float width = game.virtualWidth - (margin * 2);
		float y = game.virtualHeight-200;
		float x = margin;

		font.setMarkupEnabled(true);
		font.drawWrapped(batch, renderText, x, y, width, HAlignment.CENTER);
		batch.end();

		if (!gameOver) {
			timeLeft -= (int) (delta * 1000);
		}

		if (timeLeft <= 0) {
			timeLeft = 0;
			gameOver = true;
			renderText = "You lose!";
			addBackButton();
		}

		String timeText = String.format(
				"%01d:%02d",
				TimeUnit.MILLISECONDS.toMinutes(timeLeft) - TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(timeLeft)),
				TimeUnit.MILLISECONDS.toSeconds(timeLeft) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(timeLeft))
		);
		timerLabel.setText(timeText);
	}


}
