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
 * Created by tinut on 30-12-2016.
 */
public class Layout7Family extends AppCompatActivity {
    TextView changeLag,textF;
    LinearLayout english,malayalam;
    Button yes,no,next,back;
   public static String family="";
    public  static String lan="E";
    boolean showingFirst=true;
    RelativeLayout mlayout,Rch;
    int i=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.layout7_familyhistory);
        yes=(Button)findViewById(R.id.button_Fyes);
        no=(Button)findViewById(R.id.button_Fno);
        changeLag=(TextView)findViewById(R.id.changelangFamily);
        english=(LinearLayout)findViewById(R.id.englishL1F);
        malayalam=(LinearLayout)findViewById(R.id.malayalamF);
        textF=(TextView)findViewById(R.id.Textview_family);
        next=(Button)findViewById(R.id.button_nextF);
        back=(Button)findViewById(R.id.button__backF);
        Rch=(RelativeLayout)findViewById(R.id.RLChangeLang);
mlayout=(RelativeLayout)findViewById(R.id.changelang_layoutfamily);
        malayalam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                back.setText("തിരികെ");
                next.setText("അടുത്തത്");
                changeLag.setText("മലയാളം");
                lan="M";
                textF.setText("മാതാപിതാക്കളിൽ ഒന്നുകിൽ ഹൃദയാഘാതം അനുഭവിച്ചിട്ടുണ്ടോ");
                mlayout.setVisibility(View.INVISIBLE);
            }
        });

        english.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                back.setText("Back");
                next.setText("Next");
                lan="E";
                changeLag.setText("English");
                textF.setText("Have either of your parents had a heart attack");
                malayalam.setVisibility(View.INVISIBLE);
            }
        });

        Rch.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (showingFirst) {
                            mlayout.setVisibility(View.VISIBLE);
                            showingFirst = false;
                        } else {
                            mlayout.setVisibility(View.INVISIBLE);
                            showingFirst = true;
                        }

            }
});
        yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                i++;
                family="Yes";
                yes.setBackgroundResource(R.drawable.curveborderblack);
                no.setBackgroundResource(R.drawable.curveborder);
                yes.setTextColor(Color.WHITE);
                no.setTextColor(Color.parseColor("#999999"));
            }
        });
        no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                i++;
                family="No";
                yes.setTextColor(Color.parseColor("#999999"));
                no.setBackgroundResource(R.drawable.curveborderblack);
                yes.setBackgroundResource(R.drawable.curveborder);
                no.setTextColor(Color.WHITE);
            }

        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent f=new Intent(Layout7Family.this,Layout4Bmi.class);
                f.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                startActivity(f);
            }
        });
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (i==0) {
                    if (lan.equals("M")) {
                        Toast.makeText(Layout7Family.this, "\n" +
                                "എല്ലാ ഫീൽഡുകളും നൽകുക", Toast.LENGTH_SHORT).show();
                    } else
                        Toast.makeText(Layout7Family.this, "Enter  all fields", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Intent ff=new Intent(Layout7Family.this,FinalRisk.class);

                    startActivity(ff);
                }

            }
        });
    }
    @Override
    public void onBackPressed()
    {
        Intent intent = new Intent(Layout7Family.this, Layout4Bmi.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
        startActivity(intent);
    }

}
