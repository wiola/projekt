package com.example.milionerzy;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;

public class Imie extends DialogFragment{
	
	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {
	    AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

	    LayoutInflater inflater = getActivity().getLayoutInflater();


	    builder.setView(inflater.inflate(R.layout.dialog_signin, null))

	           .setPositiveButton(R.string.signin, new DialogInterface.OnClickListener() {
	               @Override
	               public void onClick(DialogInterface dialog, int id) {
	       
	               }
	           })
	           .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
	               public void onClick(DialogInterface dialog, int id) {
	                   dialog.cancel();
	               }
	           });      
	    return builder.create();
	}

}
