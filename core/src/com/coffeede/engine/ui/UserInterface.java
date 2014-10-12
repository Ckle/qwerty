package com.coffeede.engine.ui;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeFontParameter;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane.ScrollPaneStyle;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.ui.TextField.TextFieldStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Tree.Node;
import com.badlogic.gdx.scenes.scene2d.ui.Tree.TreeStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Window.WindowStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.IntMap;
import com.coffeede.engine.screen.BaseGame;
import com.coffeede.engine.screen.BaseScreen;
import net.dermetfan.gdx.scenes.scene2d.ui.FileChooser.Listener;
import net.dermetfan.gdx.scenes.scene2d.ui.TreeFileChooser;
import net.dermetfan.gdx.scenes.scene2d.ui.TreeFileChooser.Style;

/**
 * @author Byron
 */
public class UserInterface implements Disposable {

	public UserInterface(BaseGame game, AssetManager assetManager, String fontFile, String skinFile) {
		this.game = game;
		fontGenerator = new FreeTypeFontGenerator(Gdx.files.internal(fontFile));
		fontCache = new IntMap<BitmapFont>();

		assetManager.load(skinFile, Skin.class);
		assetManager.finishLoading();
		skin = assetManager.get(skinFile, Skin.class);
	}


	// Widget-building shortcuts

	public Table table() {
		Table table = new Table(skin);
		table.setFillParent(true);
		return table;
	}

	public TextButton button(String text, int fontSize) {
		return new TextButton(text, getButtonStyle(fontSize));
	}

	public TextButton button(String text, int fontSize, final BaseScreen screen) {
		TextButton button = new TextButton(text, getButtonStyle(fontSize));
		button.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				game.setScreen(screen);
			}
		});
		return button;
	}

	public TextButton button(String text, int fontSize, final ClickListener clickListener) {
		TextButton button = new TextButton(text, getButtonStyle(fontSize));
		button.addListener(clickListener);
		return button;
	}

	public Dialog dialog(String text, int fontSize, TextButton... buttons) {
		Dialog dialog = new Dialog(text, getWindowStyle(fontSize, Color.WHITE));
		if (buttons.length <= 0) {
			dialog.button(this.button("OK", fontSize));
		}
		for (Button button : buttons) {
			dialog.button(button);
		}
		dialog.pad(50);
		dialog.setModal(true);
		dialog.setMovable(false);
		return dialog;
	}

	public TreeFileChooser fileChooser(int fontSize, Color fontColor, String directory, Listener listener) {
		TreeFileChooser chooser = new TreeFileChooser(getTreeFileChooserStyle(fontSize, fontColor), listener);
		Node node = chooser.add(Gdx.files.internal(directory));
		node.setExpanded(true);
		node.setSelectable(false);
		return chooser;
	}

	public BitmapFont font(int size) {
		return getFont(size);
	}

	public Label label(int fontSize, Color fontColor) {
		return label("", fontSize, fontColor);
	}

	public Label label(String text, int fontSize, Color fontColor) {
		return new Label(text, getLabelStyle(fontSize, fontColor));
	}

	public TextArea textArea(String text, int fontSize, Color fontColor) {
		return new TextArea(text, getTextFieldStyle(fontSize, fontColor));
	}

	public TextField textField(String text, int fontSize, Color fontColor) {
		return new TextField(text, getTextFieldStyle(fontSize, fontColor));
	}


	// Private functions

	private final BaseGame game;
	private FreeTypeFontGenerator fontGenerator;
	private IntMap<BitmapFont> fontCache;
	private Skin skin;

	private BitmapFont getFont(int size) {
		if (!fontCache.containsKey(size)) {
			FreeTypeFontParameter param = new FreeTypeFontParameter();
			param.genMipMaps = true;
			param.minFilter = TextureFilter.MipMapLinearNearest;
			param.magFilter = TextureFilter.Linear;
			param.size = size;
			return fontGenerator.generateFont(param);
		}
		return fontCache.get(size);
	}

	private LabelStyle getLabelStyle(int size, Color color) {
		BitmapFont font = getFont(size);
		return new LabelStyle(font, color);
	}

	private TextButtonStyle getButtonStyle(int size) {
		TextButtonStyle style = new TextButtonStyle();
		style.up = skin.getDrawable("btn_default_normal");
		style.down = skin.getDrawable("btn_default_pressed");
		style.over = skin.getDrawable("btn_default_focused");
		style.disabled = skin.getDrawable("btn_default_disabled");
		style.font = getFont(size);
		return style;
	}

	private TextFieldStyle getTextFieldStyle(int size, Color color) {
		TextFieldStyle style = new TextFieldStyle();
		style.background = skin.getDrawable("text_opaque");
		style.cursor = skin.getDrawable("textfield_cursor");
		style.disabledBackground = skin.getDrawable("text_opaque");
		style.disabledFontColor = Color.GRAY;
		style.focusedBackground = skin.getDrawable("text_opaque");
		style.focusedFontColor = color;
		style.font = getFont(size);
		style.fontColor = color;
		style.selection = skin.getDrawable("textfield_selection");
		return style;
	}

	private Style getTreeFileChooserStyle(int size, Color color) {
		ScrollPaneStyle scrollPaneStyle = new ScrollPaneStyle();
		scrollPaneStyle.background = skin.getDrawable("scroll_opaque");
		scrollPaneStyle.corner = skin.getDrawable("scroll_corner");
		scrollPaneStyle.hScroll = skin.getDrawable("scroll_horizontal");
		scrollPaneStyle.hScrollKnob = skin.getDrawable("scroll_horizontal_knob");
		scrollPaneStyle.vScroll = skin.getDrawable("scroll_vertical");
		scrollPaneStyle.vScrollKnob = skin.getDrawable("scroll_vertical_knob");

		TreeStyle treeStyle = new TreeStyle();
		treeStyle.background = skin.getDrawable("text_opaque");
		treeStyle.minus = skin.getDrawable("tree_minus");
		treeStyle.plus = skin.getDrawable("tree_plus");
		treeStyle.over = skin.getDrawable("text_focused");
		treeStyle.selection = skin.getDrawable("text_selected");

		Style style = new Style();
		style.selectButtonStyle = getButtonStyle(size);
		style.cancelButtonStyle = getButtonStyle(size);
		style.labelStyle = getLabelStyle(size, color);
		style.scrollPaneStyle = scrollPaneStyle;
		style.treeStyle = treeStyle;
		return style;
	}

	private WindowStyle getWindowStyle(int size, Color color) {
		WindowStyle style = new WindowStyle();
		style.background = skin.getDrawable("window");
		style.stageBackground = skin.getDrawable("dialogDim");
		style.titleFont = getFont(size);
		style.titleFontColor = color;
		return style;
	}


	// Cleanup

	@Override
	public void dispose() {
		fontGenerator.dispose();
		for (BitmapFont font : fontCache.values()) {
			font.dispose();
		}
		fontCache.clear();
	}

}
