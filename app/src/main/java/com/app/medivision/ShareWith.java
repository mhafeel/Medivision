package com.app.medivision;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.app.medivision.Database.DatabaseHandler;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Pattern;



/**
 * Created by digital on 12/29/2016.
 */
public class ShareWith extends AppCompatActivity {
    Button Submit;
    LinearLayout language;
    RelativeLayout rllang;
    LinearLayout english;
    LinearLayout malayalam;
    LinearLayout restart;
    EditText name, Email, phone;
    boolean showingFirst = true;
    TextView change;
   public static String name1, email1, phone1,timeStamp;
   public static Long phone2;
    DatabaseHandler ndb;
     public static  String currentDateTimeString;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sharewith);
        restart=(LinearLayout)findViewById(R.id.restart);
        language = (LinearLayout) findViewById(R.id.lvlang);
        rllang = (RelativeLayout) findViewById(R.id.Rllang);
        english = (LinearLayout) findViewById(R.id.english);
        malayalam = (LinearLayout) findViewById(R.id.malayalam);
        ndb = new DatabaseHandler(this);
getSupportActionBar().hide();
        change=(TextView)findViewById(R.id.change);
        name = (EditText) findViewById(R.id.name);
        Email = (EditText) findViewById(R.id.email);
        phone = (EditText) findViewById(R.id.phone);
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        ConnectionDetector cd = new ConnectionDetector(getBaseContext());
        final boolean isInternetPresent = cd.isConnectingToInternet();

        restart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                restartFirstActivity();

            }
        });



       timeStamp = new SimpleDateFormat("HH.mm.ss \n yyyy.MM.dd").format(new Date());
      currentDateTimeString = new SimpleDateFormat(" dd.MM.yyyy ").format(new java.util.Date());

        language.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (showingFirst) {
                    rllang.setVisibility(View.VISIBLE);
                    showingFirst = false;
                } else {
                    rllang.setVisibility(View.INVISIBLE);
                    showingFirst = true;
                }

            }
        });


        english.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rllang.setVisibility(View.INVISIBLE);
change.setText("English ");

            }
        });
        malayalam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rllang.setVisibility(View.INVISIBLE);
                change.setText("മലയാളം ");

            }
        });


        Submit = (Button) findViewById(R.id.submit);
        Submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                name1 = name.getText().toString();
                phone1 = phone.getText().toString();

                email1 = Email.getText().toString();
                if (name1.equals("")) {
                    Toast.makeText(ShareWith.this, "Please Enter your Name", Toast.LENGTH_SHORT).show();
                } else if (!(checkEmail(email1))) {
                    Toast.makeText(getApplicationContext(), "Enter valid email", Toast.LENGTH_SHORT).show();
                } else if (phone1.equals("") || (phone1.length() < 6||phone1.length()>13)) {
                    Toast.makeText(ShareWith.this, "Please Enter a valid Phone Number", Toast.LENGTH_SHORT).show();
                }else if(name1.equals("")&&phone1.equals("")&&email1.equals("")){
                    Toast.makeText(ShareWith.this, "Please Enter all the fields", Toast.LENGTH_SHORT).show();
                }

                else {
                    Intent i = new Intent(getApplicationContext(),Layout1AgeGender.class);
                    i.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                    startActivity(i);
                    }
            }
        });
    }

    private boolean checkEmail(String email) {
        return EMAIL_ADDRESS_PATTERN.matcher(email).matches();
    }

    public static final Pattern EMAIL_ADDRESS_PATTERN = Pattern.compile(
            "[a-zA-Z0-9\\+\\.\\_\\%\\-\\+]{1,256}" +
                    "\\@" +
                    "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}" +
                    "(" +
                    "\\." +
                    "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25}" +
                    ")+"
    );





    @Override
    public void onBackPressed()
    {
        Intent intent = new Intent(ShareWith.this,StartActivity.class);

        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK );
        recreate();
        startActivity(intent);
    }
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        InputMethodManager imm = (InputMethodManager)getSystemService(Context.
                INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
        return true;
    }

    private void restartFirstActivity() {
        Intent i = new Intent(getApplicationContext(), StartActivity.class);

        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        recreate();
        startActivity(i);
    }

}
