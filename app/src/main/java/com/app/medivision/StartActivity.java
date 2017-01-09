package com.app.medivision;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.app.medivision.Database.DatabaseHandler;
import com.app.medivision.Database.DatabaseModel;

import pl.droidsonroids.gif.GifImageView;


/**
 * Created by digital on 12/27/2016.
 */
public class StartActivity extends AppCompatActivity {
    LinearLayout language;
    RelativeLayout rllang;
    LinearLayout english;
    LinearLayout malayalam;

    FrameLayout Start;
    String lang;
    DatabaseHandler ndb;
    TextView change;
    boolean showingFirst = true;
    public static LinearLayout countlv;
    public static TextView count;
    public static GifImageView gif;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_start);
        ndb = new DatabaseHandler(this);
        language = (LinearLayout) findViewById(R.id.lvlang);
        rllang = (RelativeLayout) findViewById(R.id.Rllang);
        english = (LinearLayout) findViewById(R.id.english);
        malayalam = (LinearLayout) findViewById(R.id.malayalam);
        Start = (FrameLayout) findViewById(R.id.start);

        countlv = (LinearLayout) findViewById(R.id.countLV);
        change=(TextView)findViewById(R.id.change);
        ConnectionDetector cd = new ConnectionDetector(getBaseContext());
        boolean isInternetPresent = cd.isConnectingToInternet();
        count = (TextView) findViewById(R.id.count);
        count.setText(String.valueOf(ndb.getContactsCount()));
        /// gif= (GifImageView)findViewById(R.id.gif);
        getSupportActionBar().hide();
        final DatabaseModel model = new DatabaseModel();
        if (!String.valueOf(ndb.getContactsCount()).equals("0")) {
            countlv.setVisibility(View.VISIBLE);
            //gif.setVisibility(View.INVISIBLE);
            countlv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getApplicationContext(), Layout6Sync.class);
                    startActivity(intent);

                }
            });
        } else {
            countlv.setVisibility(View.INVISIBLE);
        }

        language.setOnClickListener(new View.OnClickListener() {
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
                lang = "eng";
                change.setText("English ");

            }
        });
        malayalam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rllang.setVisibility(View.INVISIBLE);
                lang = "mal";
                change.setText("മലയാളം ");

            }
        });
        Start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ShareWith.class);
                startActivity(intent);


            }
        });


    }



}







