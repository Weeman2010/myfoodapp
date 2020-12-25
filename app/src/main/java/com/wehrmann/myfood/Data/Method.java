package com.wehrmann.myfood.Data;

import android.net.Uri;

public class Method {
    private int step;
    private String method;
    private Uri image;
    private Uri video;

    public Method(int step, String method) {
        this.step = step;
        this.method = method;
        this.image=null;
        this.video=null;

    }

    public Method(int step, String method, Uri image, Uri video) {
        this.step = step;
        this.method = method;
        this.image = image;
        this.video = video;
    }

    public int getStep() {
        return step;
    }

    public void setStep(int step) {
        this.step = step;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public Uri getImage() {
        return image;
    }

    public void setImage(Uri image) {
        this.image = image;
    }

    public Uri getVideo() {
        return video;
    }

    public void setVideo(Uri video) {
        this.video = video;
    }
}
