package com.example.mathis.cleanapplication.dialogs;

import android.app.Dialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.StyleRes;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.mathis.cleanapplication.R;

/**
 * Created by e-Conception on 17/05/2017.
 */

public class AddUserDialog extends Dialog implements View.OnClickListener{

    private EditText et_firstname;
    private EditText et_lastname;
    private Button butadd;
    private AddUserListener listener;
    private Context context;

    public AddUserDialog(Context context, AddUserListener listener) {
        super(context);
        this.setContentView(R.layout.add_user_dialog);
        this.listener = listener;
        this.context = context;
        et_firstname = (EditText) findViewById(R.id.firstname);
        et_lastname = (EditText) findViewById(R.id.lastname);
        butadd = (Button) findViewById(R.id.butadd);

        butadd.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.butadd:
                clickOnAddButton();
                break;
            default:
                Log.d(AddUserDialog.class.getName(),"Unhandled click");
        }
    }

    private void clickOnAddButton(){
        if(!et_firstname.getText().toString().isEmpty() && !et_lastname.getText().toString().isEmpty()){
            listener.onCreatedUser(et_firstname.getText().toString(), et_lastname.getText().toString());
            dismiss();
        }else{
            Toast.makeText(context,"Vous n'avez pas rempli tout les champs",Toast.LENGTH_LONG).show();
        }
    }

    public interface AddUserListener {
        void onCreatedUser(String firstname, String lastname);
    }


}
