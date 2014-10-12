package com.coffeede.engine.tween;

import aurelienribon.tweenengine.TweenAccessor;
import com.badlogic.gdx.graphics.g2d.Sprite;

/**
 * @author Byron
 */
public class SpriteTween implements TweenAccessor<Sprite> {

	public static final int POS_XY = 1;
	public static final int SCALE = 2;
	public static final int COLOR = 3;
	public static final int ALPHA = 4;
	public static final int SIZE = 5;
	public static final int ROTATE = 6;
	public static final int POS_X = 7;
	public static final int POS_Y = 8;

	@Override
	public int getValues(Sprite target, int tweenType, float[] returnValues) {
		switch(tweenType) {
			case POS_XY:
				returnValues[0] = target.getX();
				returnValues[1] = target.getY();
				return 2;
			case SCALE:
				returnValues[0] = target.getScaleX();
				returnValues[1] = target.getScaleY();
				return 2;
			case COLOR:
				returnValues[0] = target.getColor().r;
				returnValues[1] = target.getColor().g;
				returnValues[2] = target.getColor().b;
				returnValues[3] = target.getColor().a;
				return 4;
			case ALPHA:
				returnValues[0] = target.getColor().a;
				return 1;
			case SIZE:
				returnValues[0] = target.getWidth();
				returnValues[1] = target.getHeight();
				return 2;
			case ROTATE:
				returnValues[0] = target.getRotation();
				return 1;
			case POS_X:
				returnValues[0] = target.getX();
				return 1;
			case POS_Y:
				returnValues[0] = target.getY();
				return 1;
			default:
				return -1;
		}
	}

	@Override
	public void setValues(Sprite target, int tweenType, float[] newValues) {
		switch(tweenType) {
			case POS_XY:
				target.setPosition(newValues[0], newValues[1]);
				break;
			case SCALE:
				target.setScale(newValues[0], newValues[1]);
				break;
			case COLOR:
				target.setColor(newValues[0], newValues[1],
						newValues[2], newValues[3]);
				break;
			case ALPHA:
				target.setColor(1, 1, 1, newValues[0]);
				break;
			case SIZE:
				target.setSize(newValues[0], newValues[1]);
				break;
			case ROTATE:
				target.setRotation(newValues[0]);
				break;
			case POS_X:
				target.setX(newValues[0]);
				break;
			case POS_Y:
				target.setY(newValues[0]);
				break;
			default:
		}
	}
}