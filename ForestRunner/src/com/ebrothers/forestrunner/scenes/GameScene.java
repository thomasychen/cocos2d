package com.ebrothers.forestrunner.scenes;

import org.cocos2d.layers.CCScene;
import org.cocos2d.nodes.CCDirector;

import com.ebrothers.forestrunner.layers.BackgroundLayer;

public class GameScene extends CCScene {

	public static GameScene scene() {
		GameScene scene = new GameScene();
		scene.addChild(new BackgroundLayer());
		return scene;
	}

	@Override
	public void onEnter() {
		super.onEnter();
	}
}
