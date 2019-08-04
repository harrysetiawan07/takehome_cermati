package com.test.before.interview.Interactor.Response;

/**
 * Created by harry_setiawan on 08/04/18.
 */

public class ObjRequestCallback {
    private boolean status;
    private String message;

    public ObjRequestCallback(boolean status, String message) {
        this.status = status;
        this.message = message;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
