package com.jsexperts.megadojosofia;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Process;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by Iliyan on 3.1.2018 г..
 */

public class ExitFragment extends DialogFragment {
    View v;
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Enter password to exit");
        LayoutInflater inflater = getActivity().getLayoutInflater();
        v = inflater.inflate(R.layout.dialog_signin, null);
        builder.setView(v);
        builder.setMessage("Exit application")
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                            EditText et = (EditText) v.findViewById(R.id.password);
                            String password = et.getText().toString();
                            if ( password.compareTo("MegaAdmin") == 0){
                                Toast.makeText(getActivity(), "Password correct!", Toast.LENGTH_LONG).show();
                                getActivity().finishAffinity();
                                //android.os.Process.killProcess(Process.myPid());
                            }else {
                                Toast.makeText(getActivity(), "Password INcorrect!", Toast.LENGTH_LONG).show();

                            }

                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // User cancelled the dialog
                    }
                });
        // Create the AlertDialog object and return it
        return builder.create();
    }
}
