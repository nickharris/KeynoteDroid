package com.example.fooo;
public class Channel {
    @com.google.gson.annotations.SerializedName("id")
	private int mId;
	@com.google.gson.annotations.SerializedName("Uri")
	private String mUri;
 
	@com.google.gson.annotations.SerializedName("isWindows")
	private boolean mIsWindows = false;
	
	public int getId() { return mId; }
	public final void setId(int id) { mId = id; }
	
	public String getUri() { return mUri; }
	public final void setUri(String uri) { mUri = uri; }
	
	public boolean getIsWindows() { return mIsWindows; }
	public final void setIsWindows(boolean isWindows) { mIsWindows = isWindows; }
}