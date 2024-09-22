package com.example.s375058_mappe1;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;

public class SpillAvsluttDialog extends DialogFragment {

    public interface DialogClickListener {
        void onYesClick();
        void onNoClick();
    }

    private DialogClickListener listener;

    public void setDialogClickListener(DialogClickListener listener) {
        this.listener = listener;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage(getString(R.string.DialogAvbryt))
                .setPositiveButton(getString(R.string.DialogAvbrytJa), new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        if (listener != null) {
                            listener.onYesClick();
                        }
                    }
                })
                .setNegativeButton(getString(R.string.DialogAvbrytNei), new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        if (listener != null) {
                            listener.onNoClick();
                        }
                    }
                });
        return builder.create();
    }
}
