// IServicePlayer.aidl
package com.example.remote_service20201117;

// Declare any non-default types here with import statements

interface IServicePlayer {
    /**
     * Demonstrates some basic types that you can use as parameters
     * and return values in AIDL.
     */
    void basicTypes(int anInt, long aLong, boolean aBoolean, float aFloat,
            double aDouble, String aString);
	void play();
	void pause();
	void stop();
	int getDuration();
	int getCurrentPosition();
	void seekTo(int current);
	boolean setLoop(boolean loop);
}