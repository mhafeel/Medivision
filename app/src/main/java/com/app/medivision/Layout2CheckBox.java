package com.app.medivision;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by tinut on 29-12-2016.
 */
public class Layout2CheckBox extends AppCompatActivity {
    LinearLayout english,malayalam;
    RelativeLayout mainlayouttt;
    RelativeLayout relativeLayoutChangeLang;
    int value=0;
    CheckBox checkBox1, checkBox2, checkBox3, checkBox4, checkBox5, checkBox6, checkBox7;
    Button back, nextt;
TextView cLang;
    String    lang = "E";
    boolean showingFirst=true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.layout2checkbox);
        checkBox1 = (CheckBox) findViewById(R.id.checkbox1);
        checkBox2 = (CheckBox) findViewById(R.id.checkbox2);
        checkBox3 = (CheckBox) findViewById(R.id.checkbox3);
        checkBox4 = (CheckBox) findViewById(R.id.checkbox4);
        checkBox5 = (CheckBox) findViewById(R.id.checkbox5);
        checkBox6 = (CheckBox) findViewById(R.id.checkbox6);
        checkBox7 = (CheckBox) findViewById(R.id.checkbox7);
        back = (Button) findViewById(R.id.button__backCB);
        nextt = (Button) findViewById(R.id.button_nextCB);
        cLang=(TextView)findViewById(R.id.changelangCheck);
        english=(LinearLayout)findViewById(R.id.Echeckbox);
        malayalam=(LinearLayout)findViewById(R.id.Mcheckbox);
        relativeLayoutChangeLang=(RelativeLayout)findViewById(R.id.RLChangeLang);
        mainlayouttt=(RelativeLayout)findViewById(R.id.Maincheckbox);

        relativeLayoutChangeLang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (showingFirst) {
                    mainlayouttt.setVisibility(View.VISIBLE);
                    showingFirst = false;
                } else {
                    mainlayouttt.setVisibility(View.INVISIBLE);
                    showingFirst = true;
                }
            }
        });

        english.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lang = "E";
                checkBox1.setText("Heart attack or coronary bypass surgery");
                checkBox2.setText("Stroke or transient ischemic attack (TIA)");
                checkBox3.setText("Peripheral artery disease");
                checkBox4.setText("Angioplasty or stent placement");
                checkBox5.setText("Abdominal aortic aneurysm");
                checkBox6.setText("Are you a known patient of chronic kidney disease");
                checkBox7.setText("None of the above");
                mainlayouttt.setVisibility(View.INVISIBLE);
                cLang.setText("English");
                back.setText("back");
                nextt.setText("Next");
                mainlayouttt.setVisibility(View.INVISIBLE);


            }
        });
        malayalam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cLang.setText("മലയാളം");
                checkBox1.setText("ഹൃദയാഘാതം അല്ലെങ്കിൽ കൊറോണറി ബൈപാസ് ശസ്ത്രക്രിയ");
                checkBox2.setText("സ്ട്രോക്ക് അല്ലെങ്കിൽ ക്ഷണികമായ ലേയ്ക്ക് ആക്രമണം (TIA)");
                checkBox3.setText("പെരിഫറൽ ആർട്ടറി രോഗം");
                checkBox4.setText("ആൻജിയോപ്ലാസ്റ്റി അല്ലെങ്കിൽ stent പ്ലെയ്സ്മെന്റ്");
                checkBox5.setText("വയറിലെ ശ്വസൻ aneurysm");
                checkBox6.setText("നിങ്ങൾ വിട്ടുമാറാത്ത വൃക്ക രോഗം (വിട്ടുമാറാത്ത വൃക്കസംബന്ധമായ പരാജയം) ഒരു പ്രമുഖ ക്ഷമ");
                checkBox7.setText("മുകളിൽ കൊടുത്തിരിക്കുന്നതിൽ ഒന്നുമല്ല");
                mainlayouttt.setVisibility(View.INVISIBLE);
                back.setText("തിരികെ");
                nextt.setText("അടുത്തത്");
                lang = "M";
                mainlayouttt.setVisibility(View.INVISIBLE);

            }
        });


        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(Layout2CheckBox.this, Layout1AgeGender.class);
                in.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                startActivity(in);
            }
        });
        checkBox7.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                checkBox1.setChecked(false);
                checkBox2.setChecked(false);
                checkBox3.setChecked(false);
                checkBox4.setChecked(false);
                checkBox5.setChecked(false);
                checkBox6.setChecked(false);
                checkBox1.setClickable(false);
                checkBox2.setClickable(false);
                checkBox3.setClickable(false);
                checkBox4.setClickable(false);
                checkBox5.setClickable(false);
                checkBox6.setClickable(false);
                if (!checkBox7.isChecked()) {
                    checkBox1.setClickable(true);
                    checkBox2.setClickable(true);
                    checkBox3.setClickable(true);
                    checkBox4.setClickable(true);
                    checkBox5.setClickable(true);
                    checkBox6.setClickable(true);
                }

            }
        });

        nextt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                value=0;

                if (checkBox1.isChecked())
                    value++;
                if (checkBox2.isChecked())
                    value++;
                if (checkBox3.isChecked())
                    value++;
                if (checkBox4.isChecked())
                    value++;
                if (checkBox5.isChecked())
                    value++;
                if (checkBox6.isChecked())
                    value++;


                if (value>=1)
                {
                    Intent intent=new Intent(Layout2CheckBox.this,Layout5Message.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                    startActivity(intent);
                }
                else if (checkBox7.isChecked())
                {
                    Intent i=new Intent(Layout2CheckBox.this,SmokeActivity.class);
                    i.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                    startActivity(i);
                }

                else
                if (lang.equals("M"))
                {
                    Toast.makeText(Layout2CheckBox.this, "എല്ലാ ഫീൽഡുകളും നൽകുക", Toast.LENGTH_SHORT).show();
                }
                else
                    Toast.makeText(Layout2CheckBox.this, "Please select anyone of them", Toast.LENGTH_SHORT).show();

            }
        });


    }
    @Override
    public void onBackPressed()
    {
            Intent intent = new Intent(Layout2CheckBox.this, Layout1AgeGender.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
        startActivity(intent);
    }

}
