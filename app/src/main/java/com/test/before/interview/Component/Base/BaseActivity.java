package com.test.before.interview.Component.Base;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.AppCompatDelegate;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import com.test.before.interview.Component.Utils;
import com.test.before.interview.R;

import butterknife.ButterKnife;

public abstract class BaseActivity extends AppCompatActivity implements View.OnClickListener{
  public Dialog dialog_alert;

  static {
    AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);
  }

  protected Context ctx = this;

  @Override
  protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
    setContentView(DeclareLayout());
    ButterKnife.bind(this);
    onInitialize(savedInstanceState);
    DeclareDialog();
  }

  public void DeclareDialog() {
    dialog_alert = new Dialog(ctx);
    dialog_alert.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
    dialog_alert.setContentView(R.layout.dialog_alert);
  }

  protected abstract int DeclareLayout();

  @Override
  protected void onDestroy() {
    super.onDestroy();
  }

  protected abstract void onInitialize(Bundle savedInstance);

  @Override
  public boolean isActivityTransitionRunning() {
    return super.isActivityTransitionRunning();
  }

  public interface itemClickCallbackDialog{
    void onItemClick(boolean action);
  }
  public interface itemClickCallbackDialogType{
    void onItemClick(int action);
  }

  public itemClickCallbackDialog itemClickCallbackDialog;
  public itemClickCallbackDialogType itemClickCallbackDialogType;

  public void setItemClickCallback(final itemClickCallbackDialog itemClickCallbackDialog){
    this.itemClickCallbackDialog = itemClickCallbackDialog;
  }

  public void hideDialogAlert(){
    dialog_alert.hide();
  }

  public void showAlert(String message, String textButton, itemClickCallbackDialog callback) {
    setItemClickCallback(callback);
    TextView title = (TextView) dialog_alert.findViewById(R.id.title);
    TextView text = (TextView) dialog_alert.findViewById(R.id.isi);
    Button declineButton = (Button) dialog_alert.findViewById(R.id.btn_ok);
    text.setText(message);

    title.setTypeface(Utils.typefaceBold());
    text.setTypeface(Utils.typefaceNormal());
    declineButton.setTypeface(Utils.typefaceBold());

    dialog_alert.setCancelable(false);
    dialog_alert.show();

    declineButton.setText(textButton);
    declineButton.setOnClickListener(new android.view.View.OnClickListener() {
      @Override
      public void onClick(android.view.View v) {
        hideDialogAlert();
        callback.onItemClick(true);
      }
    });
  }

}
