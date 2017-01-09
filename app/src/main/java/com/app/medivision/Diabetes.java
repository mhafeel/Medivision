package com.app.medivision;

import android.content.Intent;
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
public class Diabetes extends AppCompatActivity {
    Button Back, Next;
    public static Spinner spinnerdiabetes, spinnerhba1c;
    public static String rowdiabetes, rowhba1c,hba1cspin;
    LinearLayout language;
    RelativeLayout rllang;
    LinearLayout english;
    View viewspin;
    RelativeLayout secondlayout;
    RelativeLayout quesrl;
    LinearLayout malayalam;
    boolean showingFirst=true;
    TextView change;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_diabetes);
        Back = (Button) findViewById(R.id.back);
        Next = (Button) findViewById(R.id.next);
        quesrl=(RelativeLayout)findViewById(R.id.quesrl);
        viewspin = (View) findViewById(R.id.view);
        secondlayout = (RelativeLayout) findViewById(R.id.secondlayout);
        spinnerdiabetes = (Spinner) findViewById(R.id.spinnerdiabetes);
        spinnerhba1c = (Spinner) findViewById(R.id.spinnerHbA1c);
        language = (LinearLayout) findViewById(R.id.lvlang);
        rllang = (RelativeLayout) findViewById(R.id.Rllang);
        english = (LinearLayout) findViewById(R.id.english);
        malayalam = (LinearLayout) findViewById(R.id.malayalam);
        change=(TextView)findViewById(R.id.change);
        Back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Diabetes.this, Layout3Cholestrol.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                startActivity(intent);
            }
        });

        Next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (spinnerdiabetes.getSelectedItem().equals("Select")) {
                    Toast.makeText(Diabetes.this, "Please Select Your diabetes factor", Toast.LENGTH_SHORT).show();
                } else if((spinnerhba1c.getSelectedItem().equals("Select"))) {
                    Toast.makeText(Diabetes.this, "Please select your Preferred HbA1c Value ", Toast.LENGTH_SHORT).show();
                }else if((spinnerdiabetes.getSelectedItem().equals("Yes"))&&rowhba1c.equals("I dont know")) {
                    Toast.makeText(Diabetes.this, "Since you are suffering from Diabetes, please ensure you have checked your HbA1c value", Toast.LENGTH_LONG).show();
                    Intent intent1 = new Intent(Diabetes.this, Layout4Bmi.class);
                    intent1.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                    startActivity(intent1);
                }else if(spinnerdiabetes.getSelectedItem().equals("I dont know")){
                    Toast.makeText(Diabetes.this, "Please get yourself tested for Diabetes", Toast.LENGTH_LONG).show();
                    Intent intent2 = new Intent(Diabetes.this, Layout4Bmi.class);
                    intent2.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                    startActivity(intent2);
                    
                }else
                    {
                        Intent intent = new Intent(Diabetes.this, Layout4Bmi.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                        startActivity(intent);
                    }
                
            }
        });

        int array1 = R.array.diabetes;
        //  final String[] age1 = getResources().getStringArray(R.array.treat);
        final ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(this.getApplicationContext(), array1, R.layout.simple_spinner_item);
        adapter1.setDropDownViewResource(R.layout.spinnerdrop);
        spinnerdiabetes.setAdapter(adapter1);
        spinnerdiabetes.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                rowdiabetes = spinnerdiabetes.getItemAtPosition(position).toString();
                if (spinnerdiabetes.getSelectedItem().equals("Yes")) {
                    viewspin.setVisibility(View.VISIBLE);
                    secondlayout.setVisibility(View.VISIBLE);

                }else if(spinnerdiabetes.getSelectedItem().equals("No")) {
                    viewspin.setVisibility(View.VISIBLE);
                    secondlayout.setVisibility(View.VISIBLE);


                }else if(spinnerdiabetes.getSelectedItem().equals("I dont know")){
                    viewspin.setVisibility(View.VISIBLE);
                    secondlayout.setVisibility(View.VISIBLE);

                } else if(spinnerdiabetes.getSelectedItem().equals("Select")){
                    viewspin.setVisibility(View.VISIBLE);
                    secondlayout.setVisibility(View.VISIBLE);

                }
                spinnerdiabetes.setSelection(position);


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }


        });
        int array2 = R.array.hba1c;
        //  final String[] age1 = getResources().getStringArray(R.array.treat);
        final ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(getApplicationContext(), array2, R.layout.simple_spinner_item);
        adapter2.setDropDownViewResource(R.layout.spinnerdrop);
        spinnerhba1c.setAdapter(adapter2);
        spinnerhba1c.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                rowhba1c = spinnerhba1c.getItemAtPosition(position).toString();
                if (rowhba1c.equals("Under 7")) {
                    rowhba1c = "6";
                } else if (rowhba1c.equals("7–8")) {
                    rowhba1c = "7";
                } else if (rowhba1c.equals("8 or higher")) {
                    rowhba1c = "9";
                } else if(rowhba1c.equals("I dont know")){
                    rowhba1c ="I dont know";
                }
                spinnerhba1c.setSelection(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {


            }


        });



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


    }

    @Override
    public void onBackPressed()
    {
        Intent intent = new Intent(Diabetes.this, Layout3Cholestrol.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
        startActivity(intent);
    }


}