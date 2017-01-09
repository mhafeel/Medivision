package com.app.medivision;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * Created by tinut on 29-12-2016.
 */
public class Layout5Message extends AppCompatActivity {
    LinearLayout english,malayalam;
    RelativeLayout mainlayouttt,chLang;
    TextView message,cLang;
    boolean showingFirst=true;
    Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getSupportActionBar().hide();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout5message);
        english = (LinearLayout) findViewById(R.id.englishL1);
        malayalam = (LinearLayout) findViewById(R.id.malayalamL1);
        mainlayouttt = (RelativeLayout) findViewById(R.id.changelang_layout);
        message = (TextView) findViewById(R.id.message);
        chLang = (RelativeLayout) findViewById(R.id.RLChangeLang);
        button = (Button) findViewById(R.id.BT_restart);
        message.setText("Before increasing your physical activity level, check with your doctor to make sure it's safe for you to proceed.\n" +
                "You have a personal history of heart disease. To help keep your heart as healthy as possible:" +
                "\n \n 1. Gradually increase your physical activity toward a goal of at least 150 minutes a week of moderate aerobic activity, 75 minutes a week of vigorous aerobic activity, or an equal combination of moderate and vigorous activity a week. Perform at least 10 minutes of aerobic" +
                "aerobic activity, or an equal combination of moderate and vigorous activity a week. Perform at least 10 minutes of aerobic ys a week." +
                "\n \n 2. Eat a healthy diet that emphasizes:\n" +
                "Fruits, vegetables and whole grains\n" +
                "Low-fat dairy products and low-fat proteins, such as poultry, fish and legumes\n" +
                "Moderate amounts of healthy fats, such as unsalted nuts, and vegetable and olive oils" +
                "\n \n 3. Maintain a healthy weight.\n" +
                "\n 4.Don't smoke cigarettes or use tobacco.\n" +
                "\n 5.Take medications your doctor has prescribed for your heart condition and other conditions, such as aspirin, statin medications or blood pressure medications.\n" +
                "\n 6. Make sure you have follow-up appointments with your doctor on a regular basis.\n");
        message.setMovementMethod(new ScrollingMovementMethod());
        cLang = (TextView) findViewById(R.id.changelangmessage);

        chLang.setOnClickListener(new View.OnClickListener() {
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
                mainlayouttt.setVisibility(View.INVISIBLE);
                cLang.setText("English");


            }
        });
        malayalam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mainlayouttt.setVisibility(View.INVISIBLE);
                cLang.setText("മലയാളം");

            }
        });
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), StartActivity.class);

                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                recreate();
                startActivity(i);
            }
        });


    }
}
