package com.app.medivision;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by tinut on 29-12-2016.
 */
public class Layout3Cholestrol extends AppCompatActivity {

    LinearLayout english, malayalam;
    RelativeLayout mainlayouttt;
    public static Spinner hdl, totalCholo;
    Button nextc, backc;
    public static int cholestrolValue;
    public static int hdlValue;
    int value;
    public static int chValue;
    TextView cLang, TC, HDL;
public static String rowtcvalue,rowhdlvalue;
    boolean showingFirst = true;
    public static String TCValue, HDLValue, lang = "E";
    public static String tcvaluespin, hdlvaluespin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.layout3_cholestrol);
        hdl = (Spinner) findViewById(R.id.spinnerHdlCholes);
        totalCholo = (Spinner) findViewById(R.id.spinnerTotalCholes);
        nextc = (Button) findViewById(R.id.button_nextlCholestrol);
        backc = (Button) findViewById(R.id.button__backlCHolestrol);
        cLang = (TextView) findViewById(R.id.changelangCholes);
        english = (LinearLayout) findViewById(R.id.englishL1);
        malayalam = (LinearLayout) findViewById(R.id.malayalamL1);
        mainlayouttt = (RelativeLayout) findViewById(R.id.changelang_layout);
        TC = (TextView) findViewById(R.id.TextView_TC);
        HDL = (TextView) findViewById(R.id.textView_hdl);


        int array1 = R.array.Totalcholesterol;

        int array2 = R.array.hdl;

        fun(array1);
        funn(array2);


        english.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lang = "E";
                int array1 = R.array.Totalcholesterol;

                int array2 = R.array.hdl;
                fun(array1);
                funn(array2);
                TC.setText("Total cholesterol, mg/dL");
                HDL.setText("HDL cholesterol, mg/dL");
                backc.setText("Back");
                nextc.setText("Next");
                cLang.setText("English");
                mainlayouttt.setVisibility(View.INVISIBLE);


            }
        });
        malayalam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cLang.setText("മലയാളം");
                int a = R.array.TotalcholesterolM;
                final int array2 = R.array.hdlM;
                funn(array2);
                lang = "M";
                fun(a);
                backc.setText("തിരികെ");
                nextc.setText("അടുത്തത്");
                TC.setText("ആകെ കൊളസ്ട്രോൾ, മില്ലിഗ്രാം / dl");
                HDL.setText("എച്ച്.ഡി.എൽ കൊളസ്ട്രോൾ, മില്ലിഗ്രാം / dl");
                lang = "M";
                mainlayouttt.setVisibility(View.INVISIBLE);

            }
        });


        cLang.setOnClickListener(new View.OnClickListener() {
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




        backc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent c = new Intent(Layout3Cholestrol.this, BloodPressure.class);
                c.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                startActivity(c);

            }
        });
        nextc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (hdl.getSelectedItem().equals("Select") || totalCholo.getSelectedItem().equals("Select")) {
                    if (lang.equals("M")) {
                        Toast.makeText(Layout3Cholestrol.this, "\n" +
                                "എല്ലാ ഫീൽഡുകളും നൽകുക", Toast.LENGTH_SHORT).show();
                    } else
                        Toast.makeText(Layout3Cholestrol.this, "Enter  all fields", Toast.LENGTH_SHORT).show();
                } else if (totalCholo.getSelectedItem().equals("I dont know") || hdl.getSelectedItem().equals("I dont know")) {

                    Toast.makeText(Layout3Cholestrol.this, "Please check your Cholesterol levels to get a more accurate result", Toast.LENGTH_LONG).show();
                    Intent i1 = new Intent(Layout3Cholestrol.this, Diabetes.class);
                    i1.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                    startActivity(i1);
                } else {
                    Intent i = new Intent(Layout3Cholestrol.this, Diabetes.class);
                    i.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                    startActivity(i);
                }
            }

        });


    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(Layout3Cholestrol.this, BloodPressure.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
        startActivity(intent);
    }

    public void fun(int arr) {
        final ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this.getApplicationContext(), arr, R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(R.layout.spinnerdrop);
        totalCholo.setAdapter(adapter);
        totalCholo.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                TCValue = totalCholo.getItemAtPosition(position).toString();

                if (TCValue.equals("Under 160")) {
                    rowtcvalue = "155";

                } else if (TCValue.equals("160–199")) {
                    rowtcvalue = "161";

                } else if (TCValue.equals("200–239")) {
                    rowtcvalue = "201";

                } else if (TCValue.equals("240–279")) {
                    rowtcvalue = "241";

                } else if (TCValue.equals("280 or higher") ) {
                    rowtcvalue = "281";

                } else if (TCValue.equals("I dont know") ) {
                    rowtcvalue = "I dont know";

                }

                totalCholo.setSelection(position);

                Log.e("total cholestrol", TCValue);

            }


            @Override
            public void onNothingSelected(AdapterView<?> parent) {


            }


        });

    }

    public void funn(int b) {
        final ArrayAdapter<CharSequence> hdlr = ArrayAdapter.createFromResource(this.getApplicationContext(), b, R.layout.simple_spinner_item);
        hdlr.setDropDownViewResource(R.layout.spinnerdrop);
        hdl.setAdapter(hdlr);
        hdl.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                HDLValue = hdl.getItemAtPosition(position).toString();

                if (HDLValue.equals("60 or higher") ) {
                    rowhdlvalue = "61";

                } else if (HDLValue.equals("50–59")) {
                    rowhdlvalue = "51";

                } else if (HDLValue.equals("40–49")) {
                    rowhdlvalue = "41";

                } else if (HDLValue.equals("Under 40")) {
                    rowhdlvalue = "39";

                } else if (HDLValue.equals("I dont know")) {
                    rowhdlvalue = "I dont know";

                }
                hdl.setSelection(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }


        });


    }
}
