package com.genymobile.scrcpy;

import android.graphics.Rect;

import java.nio.ByteBuffer;

public class VideoSettings {
    private static final int DEFAULT_BIT_RATE = 8000000;
    private static final byte DEFAULT_FRAME_RATE = 60;
    private static final byte DEFAULT_I_FRAME_INTERVAL = 10; // seconds

    private int bitRate = DEFAULT_BIT_RATE;
    private byte frameRate = DEFAULT_FRAME_RATE;
    private byte iFrameInterval = DEFAULT_I_FRAME_INTERVAL;
    private Size bounds;
    private Rect crop;
    private boolean sendFrameMeta; // send PTS so that the client may record properly

    public int getBitRate() {
        return bitRate;
    }

    public void setBitRate(int bitRate) {
        this.bitRate = bitRate;
    }

    public int getFrameRate() {
        return frameRate;
    }

    public void setFrameRate(byte frameRate) {
        this.frameRate = frameRate;
    }

    public int getIFrameInterval() {
        return iFrameInterval;
    }

    public void setIFrameInterval(byte iFrameInterval) {
        this.iFrameInterval = iFrameInterval;
    };

    public Rect getCrop() {
        return crop;
    }

    public void setCrop(Rect crop) {
        this.crop = crop;
    }

    public Size getBounds() {
        return bounds;
    }

    public void setBounds(Size bounds) {
        this.bounds = bounds;
    }

    public boolean getSendFrameMeta() {
        return sendFrameMeta;
    }

    public void setSendFrameMeta(boolean sendFrameMeta) {
        this.sendFrameMeta = sendFrameMeta;
    }

    public byte[] toByteArray() {
        ByteBuffer temp = ByteBuffer.allocate(19);
        temp.putInt(bitRate);
        temp.put(frameRate);
        temp.put(iFrameInterval);
        int width = 0;
        int height = 0;
        if (this.bounds != null) {
            width = this.bounds.getWidth();
            height = this.bounds.getHeight();
        }
        temp.putShort((short) width);
        temp.putShort((short) height);
        int left = 0;
        int top = 0;
        int right = 0;
        int bottom = 0;
        temp.putShort((short) left);
        temp.putShort((short) top);
        temp.putShort((short) right);
        temp.putShort((short) bottom);
        temp.put((byte) (sendFrameMeta ? 1 : 0));
        return temp.array();
    }
}
