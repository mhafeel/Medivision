package com.app.medivision;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by digital on 12/28/2016.
 */
public class SmokeActivity extends AppCompatActivity {
    Button Yes, No;
   Button Back,Next;
    LinearLayout language;
    RelativeLayout rllang;
    LinearLayout english;
    LinearLayout malayalam;
    TextView change;
    int i=0;
    public static String Smoker;
    boolean showingFirst=true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_smoking);
        Yes=(Button)findViewById(R.id.yes);
        No = (Button)findViewById(R.id.no);
        Back=(Button)findViewById(R.id.back);
        Next=(Button)findViewById(R.id.next);
        language=(LinearLayout)findViewById(R.id.lvlang);
        rllang=(RelativeLayout)findViewById(R.id.Rllang);
        english=(LinearLayout)findViewById(R.id.english);
        malayalam=(LinearLayout)findViewById(R.id.malayalam);
        change=(TextView)findViewById(R.id.change);


getSupportActionBar().hide();


        ///////////////////////////////////////////////////////////////////////




        language.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(showingFirst) {
                    rllang.setVisibility(View.VISIBLE);
                    showingFirst=false;
                }else{
                    rllang.setVisibility(View.INVISIBLE);
                    showingFirst=true;
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




        Yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Yes.setBackgroundResource(R.drawable.curveborderblack);
                No.setBackgroundResource(R.drawable.curveborder);
                Yes.setTextColor(Color.WHITE);
                No.setTextColor(Color.parseColor("#999999"));
                Smoker="Yes";
                i++;

            }
        });
        No.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               Yes.setTextColor(Color.parseColor("#999999"));
                No.setBackgroundResource(R.drawable.curveborderblack);
                Yes.setBackgroundResource(R.drawable.curveborder);
                No.setTextColor(Color.WHITE);
                Smoker="No";
                i++;
            }
        });

        Back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SmokeActivity.this, Layout2CheckBox.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                startActivity(intent);
            }
        });
Next.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        if(i>0) {
            Intent intent = new Intent(SmokeActivity.this, BloodPressure.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
            startActivity(intent);
        }else{
            Toast.makeText(SmokeActivity.this, "Please select required field", Toast.LENGTH_SHORT).show();
        }


    }
});
    }
    @Override
    public void onBackPressed()
    {
        Intent intent = new Intent(SmokeActivity.this, Layout2CheckBox.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
        startActivity(intent);
    }

}