package com.test.before.interview.Interactor.Request;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by harry_setiawan on 15/06/2018.
 */

public class DTL<T> {

  @SerializedName("total_count") private int totalCount;
  @SerializedName("incomplete_results") private boolean incompleteResult;
  @SerializedName("items") private List<T> items;

  public int getTotalCount() {
    return totalCount;
  }

  public void setTotalCount(int totalCount) {
    this.totalCount = totalCount;
  }

  public boolean isIncompleteResult() {
    return incompleteResult;
  }

  public void setIncompleteResult(boolean incompleteResult) {
    this.incompleteResult = incompleteResult;
  }

  public List<T> getItems() {
    return items;
  }

  public void setItems(List<T> items) {
    this.items = items;
  }
}
