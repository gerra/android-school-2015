package com.elegion.githubclient;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;

import com.elegion.githubclient.activity.BaseActivity;

/**
 * Created by root on 03.10.15.
 */
public class LoginDialogFragment extends DialogFragment {
    public static final String KEY = "LOGIN_DIALOG_FRAGMENT";

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        return new AlertDialog.Builder(getActivity())
                .setTitle(R.string.login_problem_dialog_title)
                .setCancelable(true)
                .setPositiveButton(R.string.login_problem_dialog_retry, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (getActivity() instanceof BaseActivity) {
                            ((BaseActivity) getActivity()).logout();
                        }
                    }
                }).create();
    }
}
