package com.ebrothers.forestrunner.scenes;

import org.cocos2d.layers.CCScene;
import org.cocos2d.nodes.CCSpriteFrameCache;

import com.ebrothers.forestrunner.layers.LevelSelectLayer;

public class LevelSelectScene extends CCScene {

	public static LevelSelectScene scene() {
		LevelSelectScene scene = new LevelSelectScene();
		scene.addChild(new LevelSelectLayer());
		return scene;
	}

	public LevelSelectScene() {
		super();
		CCSpriteFrameCache cache = CCSpriteFrameCache.sharedSpriteFrameCache();
		cache.removeAllSpriteFrames();
		cache.addSpriteFrames("stages.plist");
	}

}
