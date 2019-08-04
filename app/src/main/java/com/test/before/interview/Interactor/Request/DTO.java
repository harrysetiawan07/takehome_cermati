package com.test.before.interview.Interactor.Request;

import com.google.gson.annotations.SerializedName;

/**
 * Created by harry_setiawan on 15/06/2018.
 */

public class DTO<T> {

  @SerializedName("status") private int status;
  @SerializedName("message") private String message;
  @SerializedName("timestamp") private int timestamp;
  @SerializedName("data") private T data;

  public int getStatus() {
    return status;
  }

  public void setStatus(int status) {
    this.status = status;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  public int getTimestamp() {
    return timestamp;
  }

  public void setTimestamp(int timestamp) {
    this.timestamp = timestamp;
  }

  public T getData() {
    return data;
  }

  public void setData(T data) {
    this.data = data;
  }
}
