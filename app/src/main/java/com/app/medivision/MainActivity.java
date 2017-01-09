package com.app.medivision;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText gender, age, smoker, SysTorUnT, SysValue, DiaTorUnT, DiaValue, TotalCholestrol, HDLCholestrol, DiabetsYorN, Hba1cValue, height, weight, famHistory;
    public static String var_currentRiskLevel;
    String var_riskLevelForDiabetesFactor;
    String var_riskLevelForBmiFactor;
    String var_RiskLevelFromFamilyHistory;
    String var_finalRiskLevel = null;
    int risk_age = 0, risk_Smoke = 0, risk_BP = 0, risk_Cholestrol = 0, total_risk = 0;
    Button result;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        result.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String Age, Gender, ISsmoker;
                String tou, Svalue, Dvalue, DTorU;
                String TotalCHvalue, HDLValue;
                String diabetes, Hba1c;
                String height1, weight1;
                double weight2, height2;

                Age = age.getText().toString();
                Gender = gender.getText().toString();
                diabetes = DiabetsYorN.getText().toString();
                Hba1c = Hba1cValue.getText().toString();
                ISsmoker = smoker.getText().toString();
                tou = SysTorUnT.getText().toString();
                Svalue = SysValue.getText().toString();
                DTorU = DiaTorUnT.getText().toString();
                Dvalue = DiaValue.getText().toString();
                String family = famHistory.getText().toString();
                weight1 = weight.getText().toString();
                height1 = height.getText().toString();
                TotalCHvalue = TotalCholestrol.getText().toString();
                HDLValue = HDLCholestrol.getText().toString();


                //   ****************  MODULE: getAgeValue  **************************

                if (!Gender.equals("Female") && !Gender.equals("Male")) {
                    Toast.makeText(MainActivity.this, "Please enter valid Gender", Toast.LENGTH_SHORT).show();
                }
                risk_age = getAgevalue(Gender, Age);
                if (!Age.equals("")) {
                    Toast.makeText(MainActivity.this, "Age value = " + risk_age, Toast.LENGTH_SHORT).show();
                }
                Log.e(" To find Out Age Value", String.valueOf(risk_age));


                // *********************** MODULE: getSmokeValue  ****************************************************************


                risk_Smoke = getSmokeValue(Gender, Age, ISsmoker);
                if (!ISsmoker.equals("")) {
                    Toast.makeText(MainActivity.this, "Smoke Value = " + risk_Smoke, Toast.LENGTH_SHORT).show();
                }
                Log.e("If cigarette smoker: ", String.valueOf(risk_Smoke));


                // ********************** MODULE: getBloodPressureValue *************************************************************************


                risk_BP = getBpValue(Gender, tou, Svalue, Dvalue);
                if ((!tou.equals("") && !Svalue.equals("")) || ((!DTorU.equals("") && !Dvalue.equals("")))) {
                    Toast.makeText(MainActivity.this, "Blood pressure Value = " + risk_BP, Toast.LENGTH_SHORT).show();
                }
                Log.e("Blood Value ", String.valueOf(risk_BP));

                //******************************To find out the Cholestrol Value ******************************************


                risk_Cholestrol = getCholestrolValue(Gender, Age, TotalCHvalue, HDLValue);
                if (!TotalCHvalue.equals("") && !HDLValue.equals("")) {
                    Toast.makeText(MainActivity.this, "Cholestrol value = " + risk_Cholestrol, Toast.LENGTH_SHORT).show();
                }

                Log.e("Total Cholestrol Value ", String.valueOf(risk_Cholestrol));


                // ****************************** Now Calculate risk Value **************************************************

                if ((risk_age == -99) || risk_Smoke == -99 || risk_BP == -99 || risk_Cholestrol == -99) {
                    Toast.makeText(MainActivity.this, "Please enter valid parameters for calculating total risk", Toast.LENGTH_SHORT).show();
                } else {
                    total_risk = risk_age + risk_Smoke + risk_BP + risk_Cholestrol;
                    Log.e("Total Risk", String.valueOf(total_risk));
                    Toast.makeText(MainActivity.this, "Total Risk=" + total_risk, Toast.LENGTH_SHORT).show();

                 //********************************* CurrentRiskLevel ************************************************************

                    var_currentRiskLevel = getCurrentRiskLevel(Gender, total_risk);

                    Log.e("Risk Level ", String.valueOf(var_currentRiskLevel));
                    Toast.makeText(MainActivity.this, "Current Risk Level=" + var_currentRiskLevel, Toast.LENGTH_SHORT).show();

                    // ******************************* now Calculate the variation in risk with DIABETES YES OR NO ******************

                    if (diabetes.equals("Yes") || diabetes.equals("No") || diabetes.equals("I dont know")) {
                        var_riskLevelForDiabetesFactor = getRiskLevelForDiabetesFactor(var_currentRiskLevel, diabetes, Hba1c);
                        Toast.makeText(MainActivity.this, "Risk Level for diabetes = " + var_riskLevelForDiabetesFactor, Toast.LENGTH_SHORT).show();
                        Log.e(" updated Risk diabetecs", String.valueOf(var_riskLevelForDiabetesFactor));

                    } else {
                        Toast.makeText(MainActivity.this, "Please enter valid diabetes factor", Toast.LENGTH_SHORT).show();
                    }

                    //************************************Calculate the bmi of the person ******************************************


                    if (!weight1.equals("") && (!height1.equals(""))) {




                            var_riskLevelForBmiFactor = getVar_riskLevelForBmiFactor(var_riskLevelForDiabetesFactor, Gender, weight1, height1);
                            Toast.makeText(MainActivity.this, "Risk Level for BMI = " + var_riskLevelForBmiFactor, Toast.LENGTH_SHORT).show();
                            Log.e(" Risk after Bmicalcul", String.valueOf(var_riskLevelForBmiFactor));
                        } else {
                        Toast.makeText(MainActivity.this, "please enter valid height and weight", Toast.LENGTH_SHORT).show();
                    }

                ////////****************************** variation of the  Risk with  FAMILY HISTORY *******************************
                        if (family.equals("Yes") || family.equals("No")) {

                            var_RiskLevelFromFamilyHistory = getRiskLevelFromFamilyHistory(var_riskLevelForBmiFactor, Gender, family, diabetes, Svalue, Dvalue, height1, weight1, TotalCHvalue, HDLValue, ISsmoker);
                            Log.e("Risklevel from family", String.valueOf(var_RiskLevelFromFamilyHistory));
                            Toast.makeText(MainActivity.this, "Risk Level for Family History = " + var_RiskLevelFromFamilyHistory, Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(MainActivity.this, "Please enter either Yes or No for Family", Toast.LENGTH_SHORT).show();
                        }
                        if (family.equals("Yes") || family.equals("No") && (diabetes.equals("Yes") || diabetes.equals("No"))) {
                            var_finalRiskLevel = var_RiskLevelFromFamilyHistory;
                            Log.e("Final Risk Level=", var_finalRiskLevel);
                            Toast.makeText(MainActivity.this, "Final Risk Level =  " + var_finalRiskLevel, Toast.LENGTH_SHORT).show();
                        }
                    }
                }



        });

    }








    ///////////////////////////********** Methods ***********/////////////////////

    public static int getAgevalue(String param_gender, String param_age) {
        int var_age = 0;
        int b = 0;
if(param_age!=null) {
    b = Integer.parseInt(param_age);
}
        if (b > 79 || b < 20) {
            var_age = -99;
            return var_age;
        }
        if (!param_gender.equals("Female") && !param_gender.equals("Male")) {
            var_age = -99;
        }

//////////////////////////////////////////////////

        if (param_gender.equals("Female")) {
            if (20 <= b && b <= 34) {
                var_age = -7;
            } else if (35 <= b && b <= 39) {
                var_age = -3;
            } else if (40 <= b && b <= 44)
                var_age = 0;
            else if (45 <= b && b <= 49)
                var_age = 3;
            else if (50 <= b && b <= 54)
                var_age = 6;
            else if (55 <= b && b < 59)
                var_age = 8;
            else if (60 <= b && b <= 64)
                var_age = 10;
            else if (65 <= b && b <= 69)
                var_age = 12;
            else if (70 <= b && b <= 74)
                var_age = 14;
            else if (75 <= b && b <= 79)
                var_age = 16;

        } else if (param_gender.equals("Male")) {
            if (20 <= b && b <= 34) {
                var_age = -9;
            } else if (35 <= b && b <= 39) {
                var_age = -4;
            } else if (40 <= b && b <= 44)
                var_age = 0;
            else if (45 <= b && b <= 49)
                var_age = 3;
            else if (50 <= b && b <= 54)
                var_age = 6;
            else if (55 <= b && b < 59)
                var_age = 8;
            else if (60 <= b && b <= 64)
                var_age = 10;
            else if (65 <= b && b <= 69)
                var_age = 11;
            else if (70 <= b && b <= 74)
                var_age = 12;
            else if (75 <= b && b <= 79)
                var_age = 13;

        }


        return var_age;


    }


    public static int getSmokeValue(String param_gender, String param_age, String param_isSmoker) {
        int var_smokeValue = 0;
        int ca = 0;
        if (param_age!=null) {
            ca = Integer.parseInt(param_age);
        }
        if (param_isSmoker!=("Yes") && param_isSmoker!=("No")) {
            var_smokeValue = -99;
        }
        if (ca > 79 || ca < 20) {
            var_smokeValue = -99;
        }
        if (param_gender!=("Female") && param_gender!=("Male")) {
            var_smokeValue = -99;
        }
        if (param_gender.equals("Female") && param_isSmoker.equals("Yes")) {
            if (20 <= ca && ca <= 39) {
                var_smokeValue = 9;
            } else if (40 <= ca && ca <= 49) {
                var_smokeValue = 7;
            } else if (50 <= ca && ca <= 59)
                var_smokeValue = 4;
            else if (60 <= ca && ca <= 69)
                var_smokeValue = 2;
            else if (70 <= ca && ca <= 79)
                var_smokeValue = 1;


        } else if (param_isSmoker.equals("No")) {
            var_smokeValue = 0;
        } else if (param_gender.equals("Male") && param_isSmoker.equals("Yes")) {
            if (20 <= ca && ca <= 39) {
                var_smokeValue = 8;
            } else if (40 <= ca && ca <= 49) {
                var_smokeValue = 5;
            } else if (50 <= ca && ca <= 59)
                var_smokeValue = 3;
            else if (60 <= ca && ca <= 69)
                var_smokeValue = 1;
            else if (70 <= ca && ca <= 79)
                var_smokeValue = 1;

        }


        return var_smokeValue;

    }

    public static int getBpValue(String param_gender, String param_isUnderTreatmentBP,  String param_systolicBP, String param_diastolicBP) {
        int var_systolicBP = 0, var_diastolicBP = 0, var_riskBP = 0;
        int sbp = 0, dbp = 0;
        if (!param_gender.equals("Female") && !param_gender.equals("Male")) {
            var_riskBP = -99;
            return var_riskBP;
        }
        if (!param_isUnderTreatmentBP.equals("Yes") && !param_isUnderTreatmentBP.equals("No")) {
            var_riskBP = -99;
            return var_riskBP;
        }

        if (!param_systolicBP.equals("")) {

            sbp = Integer.parseInt(param_systolicBP);


        }

        if (param_gender.equals("Female")) {
            if (param_isUnderTreatmentBP.equals("Yes")) {
                if (sbp < 120)
                    var_systolicBP = 0;
                else if (120 <= sbp && sbp <= 129)
                    var_systolicBP = 3;
                else if (130 <= sbp && sbp <= 139)
                    var_systolicBP = 4;
                else if (140 <= sbp && sbp <= 159)
                    var_systolicBP = 5;
                else if (sbp >= 160)
                    var_systolicBP = 6;

            } else if (param_isUnderTreatmentBP.equals("No")) {
                if (sbp < 120)
                    var_systolicBP = 0;
                else if (120 <= sbp && sbp <= 129)
                    var_systolicBP = 1;
                else if (130 <= sbp && sbp <= 139)
                    var_systolicBP = 2;
                else if (140 <= sbp && sbp <= 159)
                    var_systolicBP = 3;
                else if (sbp >= 160)
                    var_systolicBP = 4;
            }
        } else if (param_gender.equals("Male")) {
            if (param_isUnderTreatmentBP.equals("Yes")) {
                if (sbp < 120)
                    var_systolicBP = 0;
                else if (120 <= sbp && sbp <= 129)
                    var_systolicBP = 1;
                else if (130 <= sbp && sbp <= 139)
                    var_systolicBP = 2;
                else if (140 <= sbp && sbp <= 159)
                    var_systolicBP = 2;
                else if (sbp >= 160)
                    var_systolicBP = 3;

            } else if (param_isUnderTreatmentBP.equals("No")) {
                if (sbp < 120)
                    var_systolicBP = 0;
                else if (120 <= sbp && sbp <= 129)
                    var_systolicBP = 0;
                else if (130 <= sbp && sbp <= 139)
                    var_systolicBP = 1;
                else if (140 <= sbp && sbp <= 159)
                    var_systolicBP = 1;
                else if (sbp >= 160)
                    var_systolicBP = 2;
            }
        }
        if (!param_diastolicBP.equals("")) {

            dbp = Integer.parseInt(param_diastolicBP);

        }
        if (param_gender.equals("Female")) {
            if (param_isUnderTreatmentBP.equals("Yes")) {
                if (dbp < 80)
                    var_diastolicBP = 0;
                else if (80 <= dbp && dbp <= 84)
                    var_diastolicBP = 3;
                else if (85 <= dbp && dbp <= 89)
                    var_diastolicBP = 4;
                else if (90 <= dbp && dbp <= 99)
                    var_diastolicBP = 5;
                else if (dbp >= 100)
                    var_diastolicBP = 6;

            } else if (param_isUnderTreatmentBP.equals("No")) {
                if (dbp < 80)
                    var_diastolicBP = 0;
                else if (80 <= dbp && dbp <= 84)
                    var_diastolicBP = 1;
                else if (85 <= dbp && dbp <= 89)
                    var_diastolicBP = 2;
                else if (90 <= dbp && dbp <= 99)
                    var_diastolicBP = 3;
                else if (dbp >= 100)
                    var_diastolicBP = 4;
            }
        } else if (param_gender.equals("Male")) {
            if (param_isUnderTreatmentBP.equals("Yes")) {
                if (dbp < 80)
                    var_diastolicBP = 0;
                else if (80 <= dbp && dbp <= 84)
                    var_diastolicBP = 1;
                else if (85 <= dbp && dbp <= 89)
                    var_diastolicBP = 2;
                else if (90 <= dbp && dbp <= 99)
                    var_diastolicBP = 2;
                else if (dbp >= 100)
                    var_diastolicBP = 3;

            } else if (param_isUnderTreatmentBP.equals("No")) {
                if (dbp < 80)
                    var_diastolicBP = 0;
                else if (80 <= dbp && dbp <= 84)
                    var_diastolicBP = 0;
                else if (85 <= dbp && dbp <= 89)
                    var_diastolicBP = 1;
                else if (90 <= dbp && dbp <= 99)
                    var_diastolicBP = 1;
                else if (dbp >= 100)
                    var_diastolicBP = 2;
            }
        }
        if (var_systolicBP >= var_diastolicBP) {
            var_riskBP = var_systolicBP;
        } else {
            var_riskBP = var_diastolicBP;
        }
        return var_riskBP;
    }


    public static int getCholestrolValue(String param_gender, String param_age, String param_totalCholestrolValue, String param_hdlCholestrolValue)

    {
        int qAge = 0, var_TCholestrolValue = 0, var_hdlCholestrolValue = 0, var_tempTotalcho = 0, var_tempHdlcho = 0, var_cholestrolValue = 0;
        if (param_age!=null) {
            qAge = Integer.parseInt(param_age);
        }
        if (qAge > 79 || qAge < 20) {
            var_cholestrolValue = -99;
            return var_cholestrolValue;
        }
        if (!param_gender.equals("Female") && !param_gender.equals("Male")) {
            var_cholestrolValue = -99;
            return var_cholestrolValue;
        }


        if (param_gender.equals("Female")) {
            if (!param_totalCholestrolValue.equals("I dont know")) {
                try {
                    var_tempTotalcho = Integer.parseInt(param_totalCholestrolValue);
                } catch (Exception e) {
                    var_cholestrolValue = -99;
                    return var_cholestrolValue;
                }
                if (20 <= qAge && qAge <= 39) {
                    if (var_tempTotalcho < 160)
                        var_TCholestrolValue = 0;
                    else if (160 <= var_tempTotalcho && var_tempTotalcho <= 199)
                        var_TCholestrolValue = 4;
                    else if (200 <= var_tempTotalcho && var_tempTotalcho <= 239)
                        var_TCholestrolValue = 8;
                    else if (240 <= var_tempTotalcho && var_tempTotalcho <= 279)
                        var_TCholestrolValue = 11;
                    else if (var_tempTotalcho >= 280)
                        var_TCholestrolValue = 13;
                } else if (40 <= qAge && qAge <= 49) {
                    if (var_tempTotalcho < 160)
                        var_TCholestrolValue = 0;
                    else if (160 <= var_tempTotalcho && var_tempTotalcho <= 199)
                        var_TCholestrolValue = 3;
                    else if (200 <= var_tempTotalcho && var_tempTotalcho <= 239)
                        var_TCholestrolValue = 6;
                    else if (240 <= var_tempTotalcho && var_tempTotalcho <= 279)
                        var_TCholestrolValue = 8;
                    else if (var_tempTotalcho >= 280)
                        var_TCholestrolValue = 10;
                } else if (50 <= qAge && qAge <= 59) {
                    if (var_tempTotalcho < 160)
                        var_TCholestrolValue = 0;
                    else if (160 <= var_tempTotalcho && var_tempTotalcho <= 199)
                        var_TCholestrolValue = 2;
                    else if (200 <= var_tempTotalcho && var_tempTotalcho <= 239)
                        var_TCholestrolValue = 4;
                    else if (240 <= var_tempTotalcho && var_tempTotalcho <= 279)
                        var_TCholestrolValue = 5;
                    else if (var_tempTotalcho >= 280)
                        var_TCholestrolValue = 7;
                } else if (60 <= qAge && qAge <= 69) {
                    if (var_tempTotalcho < 160)
                        var_TCholestrolValue = 0;
                    else if (160 <= var_tempTotalcho && var_tempTotalcho <= 199)
                        var_TCholestrolValue = 1;
                    else if (200 <= var_tempTotalcho && var_tempTotalcho <= 239)
                        var_TCholestrolValue = 2;
                    else if (240 <= var_tempTotalcho && var_tempTotalcho <= 279)
                        var_TCholestrolValue = 3;
                    else if (var_tempTotalcho >= 280)
                        var_TCholestrolValue = 4;
                } else if (70 <= qAge && qAge <= 79) {

                    if (var_tempTotalcho < 160)
                        var_TCholestrolValue = 0;
                    else if (160 <= var_tempTotalcho && var_tempTotalcho <= 199)
                        var_TCholestrolValue = 1;
                    else if (200 <= var_tempTotalcho && var_tempTotalcho <= 239)
                        var_TCholestrolValue = 1;
                    else if (240 <= var_tempTotalcho && var_tempTotalcho <= 279)
                        var_TCholestrolValue = 2;
                    else if (var_tempTotalcho >= 280)
                        var_TCholestrolValue = 2;
                }
            } else if (param_totalCholestrolValue.equals("I dont know")) {
                var_TCholestrolValue = 0;
            }
        } else if (param_gender.equals("Male")) {

            if (!param_totalCholestrolValue.equals("I dont know")) {
                try {
                    var_tempTotalcho = Integer.parseInt(param_totalCholestrolValue);
                } catch (Exception e) {
                    var_cholestrolValue = -99;
                    return var_cholestrolValue;
                }
                if (20 <= qAge && qAge <= 39) {
                    if (var_tempTotalcho < 160)
                        var_TCholestrolValue = 0;
                    else if (160 <= var_tempTotalcho && var_tempTotalcho <= 199)
                        var_TCholestrolValue = 4;
                    else if (200 <= var_tempTotalcho && var_tempTotalcho <= 239)
                        var_TCholestrolValue = 7;
                    else if (240 <= var_tempTotalcho && var_tempTotalcho <= 279)
                        var_TCholestrolValue = 9;
                    else if (var_tempTotalcho >= 280)
                        var_TCholestrolValue = 11;
                } else if (40 <= qAge && qAge <= 49) {

                    if (var_tempTotalcho < 160)
                        var_TCholestrolValue = 0;
                    else if (160 <= var_tempTotalcho && var_tempTotalcho <= 199)
                        var_TCholestrolValue = 3;
                    else if (200 <= var_tempTotalcho && var_tempTotalcho <= 239)
                        var_TCholestrolValue = 5;
                    else if (240 <= var_tempTotalcho && var_tempTotalcho <= 279)
                        var_TCholestrolValue = 6;
                    else if (var_tempTotalcho >= 280)
                        var_TCholestrolValue = 8;
                } else if (50 <= qAge && qAge <= 59) {

                    if (var_tempTotalcho < 160)
                        var_TCholestrolValue = 0;
                    else if (160 <= var_tempTotalcho && var_tempTotalcho <= 199)
                        var_TCholestrolValue = 2;
                    else if (200 <= var_tempTotalcho && var_tempTotalcho <= 239)
                        var_TCholestrolValue = 3;
                    else if (240 <= var_tempTotalcho && var_tempTotalcho <= 279)
                        var_TCholestrolValue = 4;
                    else if (var_tempTotalcho >= 280)
                        var_TCholestrolValue = 5;
                } else if (60 <= qAge && qAge <= 69) {

                    if (var_tempTotalcho < 160)
                        var_TCholestrolValue = 0;
                    else if (160 <= var_tempTotalcho && var_tempTotalcho <= 199)
                        var_TCholestrolValue = 1;
                    else if (200 <= var_tempTotalcho && var_tempTotalcho <= 239)
                        var_TCholestrolValue = 1;
                    else if (240 <= var_tempTotalcho && var_tempTotalcho <= 279)
                        var_TCholestrolValue = 2;
                    else if (var_tempTotalcho >= 280)
                        var_TCholestrolValue = 3;
                } else if (70 <= qAge && qAge <= 79) {

                    if (var_tempTotalcho < 160)
                        var_TCholestrolValue = 0;
                    else if (160 <= var_tempTotalcho && var_tempTotalcho <= 199)
                        var_TCholestrolValue = 0;
                    else if (200 <= var_tempTotalcho && var_tempTotalcho <= 239)
                        var_TCholestrolValue = 0;
                    else if (240 <= var_tempTotalcho && var_tempTotalcho <= 279)
                        var_TCholestrolValue = 1;
                    else if (var_tempTotalcho >= 280)
                        var_TCholestrolValue = 1;
                }
            } else if (param_totalCholestrolValue.equals("I dont know")) {
                var_TCholestrolValue = 0;
            }
        }


        if (param_hdlCholestrolValue.equals("I dont know")) {
            var_hdlCholestrolValue = -1;
        } else if (!param_hdlCholestrolValue.equals("I dont know")) {
            try {
                var_tempHdlcho = Integer.parseInt(param_hdlCholestrolValue);
            } catch (Exception e) {
                var_cholestrolValue = -99;
                return var_cholestrolValue;
            }
            if (var_tempHdlcho >= 60)
                var_hdlCholestrolValue = -1;
            else if (50 <= var_tempHdlcho && var_tempHdlcho <= 59)
                var_hdlCholestrolValue = 0;
            else if (40 <= var_tempHdlcho && var_tempHdlcho <= 49)
                var_hdlCholestrolValue = 1;
            else if (var_tempHdlcho < 40)
                var_hdlCholestrolValue = 2;

        }
        var_cholestrolValue = var_hdlCholestrolValue + var_TCholestrolValue;
        return var_cholestrolValue;

    }

    public static String getCurrentRiskLevel(String param_gender, int param_totalRisk) {
        int risk_perc = 0;
        String currentRiskLevel = null;
        if (!param_gender.equals("Female") && !param_gender.equals("Male")) {
            currentRiskLevel = "-99";
        }
        if (param_gender.equals("Female")) {
            if (param_totalRisk < 9)
                risk_perc = 0;
            else if (9 <= param_totalRisk && param_totalRisk <= 12)
                risk_perc = 1;
            else if (13 <= param_totalRisk && param_totalRisk <= 14)
                risk_perc = 2;
            else if (param_totalRisk == 15)
                risk_perc = 3;
            else if (param_totalRisk == 16)
                risk_perc = 4;
            else if (param_totalRisk == 17)
                risk_perc = 5;
            else if (param_totalRisk == 18)
                risk_perc = 6;
            else if (param_totalRisk == 19)
                risk_perc = 8;
            else if (param_totalRisk == 20)
                risk_perc = 11;
            else if (param_totalRisk == 21)
                risk_perc = 14;
            else if (param_totalRisk == 22)
                risk_perc = 17;
            else if (param_totalRisk == 23)
                risk_perc = 22;
            else if (param_totalRisk == 24)
                risk_perc = 27;
            else if (param_totalRisk >= 25)
                risk_perc = 30;

        } else if (param_gender.equals("Male")) {
            if (param_totalRisk == 0)
                risk_perc = 0;
            else if (1 <= param_totalRisk && param_totalRisk <= 4)
                risk_perc = 1;
            else if (5 <= param_totalRisk && param_totalRisk <= 6)
                risk_perc = 2;
            else if (param_totalRisk == 7)
                risk_perc = 3;
            else if (param_totalRisk == 8)
                risk_perc = 4;
            else if (param_totalRisk == 9)
                risk_perc = 5;
            else if (param_totalRisk == 10)
                risk_perc = 6;
            else if (param_totalRisk == 11)
                risk_perc = 8;
            else if (param_totalRisk == 12)
                risk_perc = 10;
            else if (param_totalRisk == 13)
                risk_perc = 12;
            else if (param_totalRisk == 14)
                risk_perc = 16;
            else if (param_totalRisk == 15)
                risk_perc = 20;
            else if (param_totalRisk == 16)
                risk_perc = 25;
            else if (param_totalRisk >= 17)
                risk_perc = 30;


        }
        if (risk_perc < 5) {
            currentRiskLevel = "LOW RISK";
        } else if (5 <= risk_perc && risk_perc <= 14) {
            currentRiskLevel = "MODERATE RISK";
        } else if (15 <= risk_perc && risk_perc <= 20) {
            currentRiskLevel = "HIGH RISK";
        } else if (risk_perc > 20) {
            currentRiskLevel = "VERY HIGH RISK";
        }
        return currentRiskLevel;

    }


    public static String getRiskLevelForDiabetesFactor(String param_currentRiskLevel, String param_diabetes, String param_HbA1c) {
        int var_HbA1c = 0;
        String risk_DiabetesLevel = null;
        // Log.e("Diabets value", String.valueOf(value));
        if (!param_currentRiskLevel.equals("LOW RISK") && !param_currentRiskLevel.equals("MODERATE RISK") && !param_currentRiskLevel.equals("HIGH RISK") && !param_currentRiskLevel.equals("VERY HIGH RISK")) {
            risk_DiabetesLevel = "-99";
        }
        if (!param_diabetes.equals("Yes") || !param_diabetes.equals("No") || !param_diabetes.equals("I dont know")) {
            risk_DiabetesLevel = "-99";
        }

        if (param_diabetes.equals("Yes")) {
            if (!param_HbA1c.equals("I dont know")) {
                try {
                    var_HbA1c = Integer.parseInt(param_HbA1c);
                } catch (Exception e) {
                    risk_DiabetesLevel = "-99";
                }

                if (var_HbA1c < 7) {
                    risk_DiabetesLevel = param_currentRiskLevel;
                } else if (7 <= var_HbA1c && var_HbA1c <= 8) {
                    switch (param_currentRiskLevel) {
                        case "LOW RISK":
                            risk_DiabetesLevel = "MODERATE RISK";
                            break;
                        case "MODERATE RISK":
                            risk_DiabetesLevel = "HIGH RISK";
                            break;
                        case "HIGH RISK":
                            risk_DiabetesLevel = "HIGH RISK";
                            break;
                        case "VERY HIGH RISK":
                            risk_DiabetesLevel = "VERY HIGH RISK";
                            break;
                    }
                } else if (var_HbA1c > 8) {
                    switch (param_currentRiskLevel) {
                        case "LOW RISK":
                            risk_DiabetesLevel = "HIGH RISK";
                            break;
                        case "MODERATE RISK":
                            risk_DiabetesLevel = "HIGH RISK";
                            break;
                        case "HIGH RISK":
                            risk_DiabetesLevel = "HIGH RISK";
                            break;
                        case "VERY HIGH RISK":
                            risk_DiabetesLevel = "VERY HIGH RISK";
                            break;
                    }
                }
            } else if (param_HbA1c.equals("I dont know")) {
                risk_DiabetesLevel = param_currentRiskLevel;
            }
        } else if (param_diabetes.equals("I dont know")) {

            risk_DiabetesLevel = param_currentRiskLevel;

        } else if (param_diabetes.equals("No")) {
            risk_DiabetesLevel = param_currentRiskLevel;

        }
        return risk_DiabetesLevel;
    }

    public static String getVar_riskLevelForBmiFactor(String param_currentRiskLevel, String param_gender, String valuew, String valueh) {
        String var_bmiRiskLevel = null;
        double var_bmiValue;
        double weight, height,height1,total_height;
        weight = Double.parseDouble(valuew);
        height = Double.parseDouble(valueh);
        if (!param_currentRiskLevel.equals("LOW RISK") && !param_currentRiskLevel.equals("MODERATE RISK") && !param_currentRiskLevel.equals("HIGH RISK") && !param_currentRiskLevel.equals("VERY HIGH RISK")) {
            var_bmiRiskLevel = "-99";
        }

        if (weight < (double) 1 || height < (double) 1) {
            var_bmiRiskLevel = "-99";
            Log.e("Second", var_bmiRiskLevel);
        }
        if (!param_gender.equals("Female") && !param_gender.equals("Male")) {
            var_bmiRiskLevel = "-99";
            Log.e("Third", var_bmiRiskLevel);
        }

       height1 = (double)height/100;
        total_height = (double)height1*(double)height1;
        var_bmiValue = (double)(weight/total_height);

        Log.e("Tinutomy", String.valueOf(var_bmiValue));
        if (param_gender.equals("Male")) {
            if ((double) var_bmiValue < (double) 26.4) {
                //Normal weight
                var_bmiRiskLevel = param_currentRiskLevel;
            } else if (var_bmiValue >= (double) 26.4 && var_bmiValue < (double) 31.1) {
                //Over Weight
                //  LR->LR; MR->HR; HR->HR; VHR->VHR
                if (param_currentRiskLevel.equals("LOW RISK"))
                    var_bmiRiskLevel = "LOW RISK";
                else if (param_currentRiskLevel.equals("MODERATE RISK"))
                    var_bmiRiskLevel = "HIGH RISK";
                else if (param_currentRiskLevel.equals("HIGH RISK"))
                    var_bmiRiskLevel = "HIGH RISK";
                else if (param_currentRiskLevel.equals("VERY HIGH RISK"))
                    var_bmiRiskLevel = "VERY HIGH RISK";
            } else if (var_bmiValue >= (double) 31.1 && var_bmiValue < (double) 40) {
                //  obese
                //    LR->MR; MR->HR; HR->VHR; VHR->VHR

                if (param_currentRiskLevel.equals("LOW RISK"))
                    var_bmiRiskLevel = "MODERATE RISK";
                else if (param_currentRiskLevel.equals("MODERATE RISK"))
                    var_bmiRiskLevel = "HIGH RISK";
                else if (param_currentRiskLevel.equals("HIGH RISK"))
                    var_bmiRiskLevel = "VERY HIGH RISK";
                else if (param_currentRiskLevel.equals("VERY HIGH RISK"))
                    var_bmiRiskLevel = "VERY HIGH RISK";

            } else if (var_bmiValue >= (double) 40) {
                // extremly obse
//       LR->HR; MR->VHR; HR->VHR; VHR->VHR

                if (param_currentRiskLevel.equals("LOW RISK"))
                    var_bmiRiskLevel = "HIGH RISK";
                else if (param_currentRiskLevel.equals("MODERATE RISK"))
                    var_bmiRiskLevel = "VERY HIGH RISK";
                else if (param_currentRiskLevel.equals("HIGH RISK"))
                    var_bmiRiskLevel = "VERY HIGH RISK";
                else if (param_currentRiskLevel.equals("VERY HIGH RISK"))
                    var_bmiRiskLevel = "VERY HIGH RISK";
            }
        } else if (param_gender.equals("Female")) {
            if (var_bmiValue < (double) 25.8) {
                //Normal weight
                var_bmiRiskLevel = param_currentRiskLevel;
            } else if (var_bmiValue >= (double) 25.8 && var_bmiValue < (double) 32.3) {
                //Over Weight
                //  LR->LR; MR->HR; HR->HR; VHR->VHR
                if (param_currentRiskLevel.equals("LOW RISK"))
                    var_bmiRiskLevel = "LOW RISK";
                else if (param_currentRiskLevel.equals("MODERATE RISK"))
                    var_bmiRiskLevel = "HIGH RISK";
                else if (param_currentRiskLevel.equals("HIGH RISK"))
                    var_bmiRiskLevel = "HIGH RISK";
                else if (param_currentRiskLevel.equals("VERY HIGH RISK"))
                    var_bmiRiskLevel = "VERY HIGH RISK";
            } else if (var_bmiValue >= (double) 32.3 && var_bmiValue < (double) 40) {
                //  obese
                //    LR->MR; MR->HR; HR->VHR; VHR->VHR

                if (param_currentRiskLevel.equals("LOW RISK"))
                    var_bmiRiskLevel = "MODERATE RISK";
                else if (param_currentRiskLevel.equals("MODERATE RISK"))
                    var_bmiRiskLevel = "HIGH RISK";
                else if (param_currentRiskLevel.equals("HIGH RISK"))
                    var_bmiRiskLevel = "VERY HIGH RISK";
                else if (param_currentRiskLevel.equals("VERY HIGH RISK"))
                    var_bmiRiskLevel = "VERY HIGH RISK";

            } else if (var_bmiValue >= (double) 40) {
                // extremly obse
//       LR->HR; MR->VHR; HR->VHR; VHR->VHR
                Log.e("loop bmi", "High Risk");

                if (param_currentRiskLevel.equals("LOW RISK"))
                    var_bmiRiskLevel = "HIGH RISK";
                else if (param_currentRiskLevel.equals("MODERATE RISK"))
                    var_bmiRiskLevel = "VERY HIGH RISK";
                else if (param_currentRiskLevel.equals("HIGH RISK"))
                    var_bmiRiskLevel = "VERY HIGH RISK";
                else if (param_currentRiskLevel.equals("VERY HIGH RISK"))
                    var_bmiRiskLevel = "VERY HIGH RISK";
            }
        }
        return var_bmiRiskLevel;
    }


    public static String getRiskLevelFromFamilyHistory(String param_currentRiskLevel, String param_gender, String param_IsFamilyHistory, String param_diabetes, String param_systolicBP, String param_diastolicBP, String valueh, String valuew, String param_totalCholestrolValue, String param_hdlCholestrolValue, String param_IsSmoking) {

        String var_finalRiskLevel = null;
        int countyes = 0;
        int countno = 0;
        double var_bmiValue1;
        double weight, height,height1,total_height;
        weight = Double.parseDouble(valuew);
        height = Double.parseDouble(valueh);
        int sys = 0, dias = 0, totalch = 0, hdlch = 0;

        height1 = (double)height/100;
        total_height = (double)height1*(double)height1;
        var_bmiValue1 = (double)(weight/total_height);
        Log.e("var_bmi 2", String.valueOf(var_bmiValue1));

        if (!param_gender.equals("Female") && !param_gender.equals("Male")) {
            var_finalRiskLevel = "-99";
        }
        if (!param_diabetes.equals("Yes") || !param_diabetes.equals("No") || !param_diabetes.equals("I dont know")) {
            var_finalRiskLevel = "-99";
        }
        if (!param_IsFamilyHistory.equals("Yes") && !param_IsFamilyHistory.equals("No")) {
            var_finalRiskLevel = "-99";
        }
        if (!param_systolicBP.equals("")) {
            try {
                sys = Integer.parseInt(param_systolicBP);
            } catch (Exception e) {
                var_finalRiskLevel = "-99";
            }
        }
        if (!param_diastolicBP.equals("")) {
            try {
                dias = Integer.parseInt(param_diastolicBP);
            } catch (Exception e) {
                var_finalRiskLevel = "-99";
            }

        }
        if (!param_totalCholestrolValue.equals("")) {
            try {
                totalch = Integer.parseInt(param_totalCholestrolValue);
            } catch (Exception e) {
                var_finalRiskLevel = "-99";
            }
        }
        if (!param_hdlCholestrolValue.equals("")) {
            try {
                hdlch = Integer.parseInt(param_hdlCholestrolValue);
            } catch (Exception e) {
                var_finalRiskLevel = "-99";
            }
        }
        if (!param_IsSmoking.equals("Yes") && !param_IsSmoking.equals("No")) {
            var_finalRiskLevel = "-99";
        }


        ////////////////////////////////////////
        if (param_IsFamilyHistory.equals("Yes")) {
            if ((param_diabetes.equals("Yes"))) {
                countyes++;
                Log.e("diabetecs", String.valueOf(countyes));
            }
            if (sys >= 140 || dias >= 90) {
                countyes++;
                Log.e("sys", String.valueOf(countyes));
            }
            // Total Cholesterol -> >160 or HDL < 50

            if (totalch > 160 || hdlch < 50) {
                countyes++;
                Log.e("cholest", String.valueOf(countyes));
            }
            if (((param_gender.equals("Female") && var_bmiValue1 >= (double) 32.2)) || ((param_gender.equals("Male") && var_bmiValue1 >= (double) 31.1))) {
                countyes++;
                Log.e("BMI", String.valueOf(countyes));


            }

            if (countyes == 3) {

                var_finalRiskLevel = "VERY HIGH RISK";
                Log.e("fINAL RISK", String.valueOf(var_finalRiskLevel));
            }
            if (countyes < 3 || countyes > 3) {
                var_finalRiskLevel = param_currentRiskLevel;
                Log.e("Final risk", String.valueOf(var_finalRiskLevel));

            }
            countyes = 0;
        } else if (param_IsFamilyHistory.equals("No")) {

            if ((param_diabetes.equals("Yes"))) {
                countno++;
                Log.e("diabetes", String.valueOf(countno));
            }
            if (sys >= 140 || dias >= 90) {
                countno++;
                Log.e("sys", String.valueOf(countno));
            }

            if ((((param_gender.equals("Female") && var_bmiValue1 >= (double) 32.2) || ((param_gender.equals("Male") && var_bmiValue1 >= (double) 31.1))))) {
                countno++;
                Log.e("bmi", String.valueOf(countno));
            }
            if (totalch > 160 || hdlch < 50) {
                countno++;
                Log.e("tcvalue", String.valueOf(countno));
            }
            if (countno == 3) {
                var_finalRiskLevel = "VERY HIGH RISK";
            }
            if (countno == 3 && param_IsSmoking.equals("Yes")) {
                var_finalRiskLevel = "VERY HIGH RISK";
                Log.e("final", String.valueOf(var_finalRiskLevel));
            }
            if (countno == 4) {
                var_finalRiskLevel = "VERY HIGH RISK";
            }
            if (countno > 4) {
                var_finalRiskLevel = param_currentRiskLevel;
            }
            if (countno < 3) {
                var_finalRiskLevel = param_currentRiskLevel;
                Log.e("risk", String.valueOf(var_finalRiskLevel));
            }
            if (countno == 3 && param_IsSmoking.equals("No")) {
                var_finalRiskLevel = param_currentRiskLevel;
                Log.e("final", String.valueOf(var_finalRiskLevel));
            }
            countno = 0;
        }


        return var_finalRiskLevel;
    }


}
