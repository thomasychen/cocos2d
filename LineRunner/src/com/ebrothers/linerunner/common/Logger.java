package com.ebrothers.linerunner.common;

import android.util.Log;

public class Logger {

	public static final boolean LOGD = true;
	public static final boolean LOGE = true;

	public static void d(String tag, String msg) {
		if (LOGD) {
			Log.d(tag, "****** " + msg);
		}
	}

	public static void e(String tag, String msg) {
		if (LOGE) {
			Log.e(tag, msg);
		}
	}

	public static void e(String tag, Throwable tr) {
		if (LOGE) {
			Log.e(tag, "****** ERROR", tr);
		}
	}
}
