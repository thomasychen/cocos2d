package org.cocos2d.levelhelper.nodes;

import java.util.ArrayList;

import org.cocos2d.actions.base.CCAction;
import org.cocos2d.actions.base.CCRepeatForever;
import org.cocos2d.actions.instant.CCCallFuncND;
import org.cocos2d.actions.interval.CCAnimate;
import org.cocos2d.actions.interval.CCRepeat;
import org.cocos2d.actions.interval.CCSequence;
import org.cocos2d.levelhelper.LevelHelperLoader;
import org.cocos2d.nodes.CCAnimation;
import org.cocos2d.nodes.CCSpriteFrame;
import org.cocos2d.nodes.CCSpriteSheet;
import org.cocos2d.types.CGPoint;
import org.cocos2d.types.CGRect;

import android.util.Log;

public class LHAnimationNode {
	public boolean loop;
	public float speed;
	public int repetitions;
	public boolean startAtLaunch;
	private String uniqueName;
	private String imageName;
	private ArrayList<CCSpriteFrame> frames;
	private ArrayList<CGRect> framesInfo;
	private CCSpriteSheet spriteSheet; // week ptr

	public static LHAnimationNode animationNodeWithUniqueName(String name) {
		LHAnimationNode anim = new LHAnimationNode();
		anim.initWithUniqueName(name);
		return anim;
	}

	private LHAnimationNode() {
		frames = new ArrayList<CCSpriteFrame>();
		speed = 0.2f;
		repetitions = 1;
		startAtLaunch = true;
	}

	public void initWithUniqueName(String name) {
		assert (name != null && name.length() != 0);
		setUniqueName(name);
	}

	public void setFramesInfo(ArrayList<CGRect> frmsInfo) {
		framesInfo.clear();
		framesInfo.addAll(frmsInfo);
	}

	public void setSpriteSheet(CCSpriteSheet _spriteSheet) {
		spriteSheet = _spriteSheet;
	}

	public void computeFrames() {
		if (spriteSheet == null)
			return;
		frames.clear();
		int size = framesInfo.size();
		for (int i = 0; i < size; ++i) {
			CGRect rect = framesInfo.get(i);
			String image = LHSettings.sharedInstance().imagePath(imageName);
			if (LHSettings.sharedInstance().shouldScaleImageOnRetina(image)) {
				rect.origin.x *= 2.0f;
				rect.origin.y *= 2.0f;
				rect.size.width *= 2.0f;
				rect.size.height *= 2.0f;
			}
			CCSpriteFrame frame = CCSpriteFrame.frame(spriteSheet.getTexture(),
					rect, CGPoint.zero());
			frames.add(frame);
		}
	}

	public void runAnimationOnSprite(LHSprite ccsprite,
			Object animNotifierTarget, String animNotifierSelector,
			boolean notifOnLoop) {
		CCAnimation anim = CCAnimation.animation("", speed, frames);
		CCAction seq;
		if (!loop) {
			CCRepeat animAct = CCRepeat.action(CCAnimate.action(anim, false),
					repetitions);
			if (animNotifierTarget != null) {
				CCCallFuncND actionRestart = CCCallFuncND.action(
						animNotifierTarget, animNotifierSelector, uniqueName);
				seq = CCSequence.actions(animAct, actionRestart);
			} else {
				seq = animAct;
			}
		} else {
			if (notifOnLoop && animNotifierTarget != null) {
				CCCallFuncND actionRestart = CCCallFuncND.action(
						animNotifierTarget, animNotifierSelector, uniqueName);

				CCSequence animAct = CCSequence.actions(
						CCAnimate.action(anim, false), actionRestart);

				seq = CCRepeatForever.action(animAct);
			} else {
				seq = CCRepeatForever.action(CCAnimate.action(anim, false));
			}
		}

		if (seq != null) {
			seq.setTag(LevelHelperLoader.LH_ANIM_ACTION_TAG);
			ccsprite.setAnimation(this);
			// setAnimationTexturePropertiesOnSprite(ccsprite);
			ccsprite.runAction(seq);
		}
	}

	public void setAnimationTexturePropertiesOnSprite(LHSprite ccsprite) {
		if (!LHSettings.sharedInstance().isCoronaUser())
			ccsprite.removeFromParentAndCleanup(true);
		ccsprite.setTexture(spriteSheet.getTexture());
		if (!LHSettings.sharedInstance().isCoronaUser()) {
			Log.d("Set sprite batch node on sprite with anim %s\n",
					ccsprite.getUniqueName());
			ccsprite.setSpriteSheet(spriteSheet);
			spriteSheet.addChild(ccsprite);
		}
	}

	public int getNumberOfFrames() {
		return frames.size();
	}

	public void setFrame(int frameNo, LHSprite spr) {
		if (spr == null)
			return;
		if (frameNo >= 0 && frameNo < frames.size()) {
			CCSpriteFrame frame = frames.get(frameNo);
			if (frame != null) {
				spr.setTextureRect(frame.getRect());
			}
		}
	}

	public ArrayList<CCSpriteFrame> getFrames() {
		return frames;
	}

	public void setImageName(String image) {
		imageName = image;
	}

	public String getImageName() {
		return imageName;
	}

	public void setUniqueName(String uniqueName) {
		this.uniqueName = uniqueName;
	}

	public String getUniqueName() {
		return uniqueName;
	}
}
