package com.app.medivision;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.app.medivision.Database.DatabaseHandler;
import com.app.medivision.Database.DatabaseModel;

import java.util.List;

/**
 * Created by digital on 12/29/2016.
 */
public class FinalRisk extends AppCompatActivity {
    Button close;
    LinearLayout language;
    RelativeLayout rllang;
    LinearLayout english;
    LinearLayout malayalam;
    TextView Restart, finalRisk, change;
    String TAG = "Calculation";
    boolean showingFirst = true;
    int risk_smoke = 0, risk_age = 0, risk_cholestrol = 0;
    int risk_bp = 0;
    int total_risk;
    String var_RiskLevelForDiabetesFactor, var_currentRiskLevel, var_riskLevelForBmiFactor, var_riskLevelForFamilyHistoryFactor;
    public static String var_FinalRisk;
    TextView FinalRisk;
    DatabaseHandler ndb;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_finalrisk);
        close = (Button) findViewById(R.id.close);

        language = (LinearLayout) findViewById(R.id.lvlang);
        rllang = (RelativeLayout) findViewById(R.id.Rllang);
        english = (LinearLayout) findViewById(R.id.english);
        malayalam = (LinearLayout) findViewById(R.id.malayalam);
        FinalRisk = (TextView) findViewById(R.id.finalrisk);
        change = (TextView) findViewById(R.id.change);
        ndb = new DatabaseHandler(this);
        final DatabaseModel model = new DatabaseModel();


        risk_age = MainActivity.getAgevalue(Layout1AgeGender.Gender, Layout1AgeGender.ageValue);
        risk_cholestrol = MainActivity.getCholestrolValue(Layout1AgeGender.Gender, Layout1AgeGender.ageValue, Layout3Cholestrol.rowtcvalue, Layout3Cholestrol.rowhdlvalue);
        risk_smoke = MainActivity.getSmokeValue(Layout1AgeGender.Gender, Layout1AgeGender.ageValue, SmokeActivity.Smoker);
        risk_bp = MainActivity.getBpValue(Layout1AgeGender.Gender, BloodPressure.param_isUnderTreatmentsystBP, BloodPressure.rowsysvalue, BloodPressure.rowdiovalue);
        total_risk = risk_bp + risk_smoke + risk_age + risk_cholestrol;
        var_currentRiskLevel = MainActivity.getCurrentRiskLevel(Layout1AgeGender.Gender, total_risk);
        var_RiskLevelForDiabetesFactor = MainActivity.getRiskLevelForDiabetesFactor(var_currentRiskLevel, Diabetes.rowdiabetes, Diabetes.rowhba1c);
        var_riskLevelForBmiFactor = MainActivity.getVar_riskLevelForBmiFactor(var_RiskLevelForDiabetesFactor, Layout1AgeGender.Gender, Layout4Bmi.w, Layout4Bmi.h);
        var_riskLevelForFamilyHistoryFactor = MainActivity.getRiskLevelFromFamilyHistory(var_riskLevelForBmiFactor, Layout1AgeGender.Gender, Layout7Family.family, Diabetes.rowdiabetes, BloodPressure.rowsysvalue, BloodPressure.rowdiovalue, Layout4Bmi.h, Layout4Bmi.w, Layout3Cholestrol.TCValue, Layout3Cholestrol.HDLValue, SmokeActivity.Smoker);

        var_FinalRisk = var_riskLevelForFamilyHistoryFactor;
        FinalRisk.setText(var_riskLevelForFamilyHistoryFactor);

        Log.i(TAG, "Risk Age = " + risk_age);
        Log.i(TAG, "Risk Smoke = " + risk_smoke);
        Log.i(TAG, "Risk BP = " + risk_bp);
        Log.i(TAG, "Risk Cholestrol = " + risk_cholestrol);
        Log.i(TAG, "TOTAL RISK=" + total_risk);
        Log.i(TAG, "Current Risk = " + var_currentRiskLevel);
        Log.i(TAG, "Current diabetes  = " + var_RiskLevelForDiabetesFactor);
        Log.i(TAG, "Current Bmi = " + var_riskLevelForBmiFactor);
        Log.i(TAG, "Current Family History = " + var_riskLevelForFamilyHistoryFactor);


        if (var_riskLevelForFamilyHistoryFactor.equals("LOW RISK")) {
            FinalRisk.setTextColor(Color.GREEN);
            FinalRisk.setText("LOW RISK");
        } else if (var_riskLevelForFamilyHistoryFactor.equals("MODERATE RISK")) {
            FinalRisk.setTextColor(Color.YELLOW);
            FinalRisk.setText("MODERATE RISK");
        } else if (var_riskLevelForFamilyHistoryFactor.equals("HIGH RISK")) {
            FinalRisk.setTextColor(Color.parseColor("#FFA500"));
            FinalRisk.setText("HIGH RISK");
        } else if (var_riskLevelForFamilyHistoryFactor.equals("VERY HIGH RISK")) {
            FinalRisk.setTextColor(Color.RED);
            FinalRisk.setText("VERY HIGH RISK");
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


        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                model.setName(ShareWith.name1);
                model.setPhone(ShareWith.phone1);
                model.setEmail(ShareWith.email1);
                model.setDate(ShareWith.timeStamp);
                model.setGender(Layout1AgeGender.Gender);
                model.setAge(Layout1AgeGender.ageValuespin);
                model.setSmoker(SmokeActivity.Smoker);
                model.setSystoru(BloodPressure.param_isUnderTreatmentsystBP);
                model.setSysvalue(BloodPressure.sysvaluespin);
                model.setDiovalue(BloodPressure.diovaluespin);
                model.setTotalch(Layout3Cholestrol.TCValue);
                model.setHdlch(Layout3Cholestrol.HDLValue);
                model.setDiabetes(Diabetes.rowdiabetes);
                model.setHba1c(Diabetes.rowhba1c);
                model.setBmivalue(Layout4Bmi.var_BmiValue);
                model.setFamilyhistory(Layout7Family.family);
                model.setFinalrisk(var_FinalRisk);

                ndb.addContact(model);
                List<DatabaseModel> contacts = ndb.getAllContacts();
                for (DatabaseModel md : contacts) {
                    String log = "NAME = " + md.getName() + " Age = " + md.getAge() + " SYSTORU = " + md.getSystoru() + " SYSVALUE= " + md.getSysvalue() + " diASTORU = "  + " diavalue = " + md.getDiovalue() + " total ch=" + md.getTotalch() + " hdl ch = " + md.getHdlch() + " diabetes= " + md.getDiabetes() + " hb1c = " + md.getHba1c() + "bmi value = " + md.getBmivalue();
                    Log.e(" DETAILS  - - - - - :", log);
                }

                restartFirstActivity();
            }
        });


    }

    private void restartFirstActivity() {
        Intent i = new Intent(getApplicationContext(), StartActivity.class);

        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        recreate();
        startActivity(i);
    }



    @Override
    public void onBackPressed() {
        Intent intent = new Intent(FinalRisk.this, Layout7Family.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
        startActivity(intent);
    }


}