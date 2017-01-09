package com.app.medivision;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
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
 * Created by digital on 12/29/2016.
 */
public class BloodPressure extends AppCompatActivity {
    LinearLayout language;
    RelativeLayout rllang;
    LinearLayout english;
    LinearLayout malayalam;
    Button treatedsys,untreatedsys;
    Button treateddias,untreateddias;
    Button Back,Next;
    TextView change;
    public static Spinner sysvalues, diovalues;
    public static String rowsysvalue,sysvaluespin;
    public static String rowdiovalue,diovaluespin;
    int i=0;
    int s=0;
    boolean showingFirst=true;
    public static String param_isUnderTreatmentsystBP;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_bloodpressure);
        language=(LinearLayout)findViewById(R.id.lvlang);
        rllang=(RelativeLayout)findViewById(R.id.Rllang);
        english=(LinearLayout)findViewById(R.id.english);
        malayalam=(LinearLayout)findViewById(R.id.malayalam);
        treatedsys=(Button)findViewById(R.id.treatedsys);
        untreatedsys = (Button)findViewById(R.id.untreatedsys);
        Back=(Button)findViewById(R.id.back);
        Next=(Button)findViewById(R.id.next);
        change=(TextView)findViewById(R.id.change);
        sysvalues = (Spinner) findViewById(R.id.spinnersys);
        diovalues = (Spinner) findViewById(R.id.spinnerdias);
    //////////////////////////////////////////////////////////////////////////////////////////////////


        ////////////////////////////////////////////////Blood Pressure////////////////////////////////////////////



        ///////////////dsfds;f///////////////////////


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


        treatedsys.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                treatedsys.setBackgroundResource(R.drawable.curveborderblack);
                untreatedsys.setBackgroundResource(R.drawable.curveborder);
                treatedsys.setTextColor(Color.WHITE);
                untreatedsys.setTextColor(Color.parseColor("#999999"));
                param_isUnderTreatmentsystBP="Yes";
                i++;

            }
        });
        untreatedsys.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                treatedsys.setTextColor(Color.parseColor("#999999"));
                untreatedsys.setBackgroundResource(R.drawable.curveborderblack);
                treatedsys.setBackgroundResource(R.drawable.curveborder);
                untreatedsys.setTextColor(Color.WHITE);
                param_isUnderTreatmentsystBP="No";
                i++;
            }
        });


        int array1 = R.array.bpsys;
        //  final String[] age1 = getResources().getStringArray(R.array.treat);
        final ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(this.getApplicationContext(), array1, R.layout.simple_spinner_item);
        adapter1.setDropDownViewResource(R.layout.spinnerdrop);
        sysvalues.setAdapter(adapter1);
        sysvalues.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                sysvaluespin = sysvalues.getItemAtPosition(position).toString();
                if(sysvaluespin.equals("Under 120")){
                    rowsysvalue="119";
                }else if(sysvaluespin.equals("120–129")){
                    rowsysvalue="125";
                }else if(sysvaluespin.equals("130–139")){
                    rowsysvalue="135";
                }else if(sysvaluespin.equals("140–159")){
                    rowsysvalue="145";
                } else if(sysvaluespin.equals("160 or higher")){
                    rowsysvalue="161";
                }
                sysvalues.setSelection(position);

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }


        });


        int array2 = R.array.bpdio;
        //  final String[] age1 = getResources().getStringArray(R.array.treat);
        final ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(this.getApplicationContext(), array2, R.layout.simple_spinner_item);
        adapter2.setDropDownViewResource(R.layout.spinnerdrop);
        diovalues.setAdapter(adapter2);
        diovalues.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                diovaluespin=diovalues.getItemAtPosition(position).toString();
                if(diovaluespin.equals("Under 80")){
                    rowdiovalue="79";
                }else if(diovaluespin.equals("80–84")){
                    rowdiovalue="82";
                }else if(diovaluespin.equals("85–89")){
                    rowdiovalue="87";
                }else if(diovaluespin.equals("90–99")){
                    rowdiovalue="95";
                } else if(diovaluespin.equals("100 or higher")){
                    rowdiovalue="100";
                }
                diovalues.setSelection(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {


            }


        });










        Back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(BloodPressure.this, SmokeActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                startActivity(intent);
            }
        });
        Next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if((i>0&&!sysvalues.getSelectedItem().equals("Select")&&!diovalues.getSelectedItem().equals("Select"))) {

                    Intent intent = new Intent(BloodPressure.this, Layout3Cholestrol.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                    startActivity(intent);
                } else {
                    Toast.makeText(BloodPressure.this, "Please Select Your Known Blood pressure", Toast.LENGTH_SHORT).show();

                }
            }
        });









    }


    @Override
    public void onBackPressed()
    {
        Intent intent = new Intent(BloodPressure.this, SmokeActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
        startActivity(intent);
    }


}
