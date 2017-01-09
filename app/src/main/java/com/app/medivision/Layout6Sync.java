package com.app.medivision;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.app.medivision.Database.DatabaseHandler;
import com.app.medivision.Database.DatabaseModel;
import com.app.medivision.Database.DividerItemDecoration;
import com.app.medivision.Database.RecycleAdapter;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;



/**
 * Created by tinut on 29-12-2016.
 */
public class Layout6Sync extends AppCompatActivity {
    LinearLayout english,malayalam;
    RelativeLayout mainlayouttt,CH;
    TextView cLang;
    Button synclater,SyncNow;
    DatabaseHandler helpher;
    List<DatabaseModel> dbList;
    int position;
    RecyclerView mRecyclerView;
    private RecycleAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private View view;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.layout6_sync);
        cLang=(TextView)findViewById(R.id.changelangSync);
        english=(LinearLayout)findViewById(R.id.englishL1);
        malayalam=(LinearLayout)findViewById(R.id.malayalamL1);
        SyncNow=(Button)findViewById(R.id.button_SyncNow);
        synclater=(Button)findViewById(R.id.SyncLater);
        CH=(RelativeLayout)findViewById(R.id.RLChangeLang);

        synclater.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(Layout6Sync.this,StartActivity.class);
            startActivity(intent1);
            }
        });
        SyncNow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Layout6Sync.this,ShareWith.class);
                startActivity(intent);
            }
        });

        english=(LinearLayout)findViewById(R.id.englishL1);
        malayalam=(LinearLayout)findViewById(R.id.malayalamL1);
        mainlayouttt=(RelativeLayout)findViewById(R.id.changelang_layout);

        CH.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mainlayouttt.setVisibility(View.VISIBLE);
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


        helpher=new DatabaseHandler(this);
        dbList=new ArrayList<>();

        getSupportActionBar().hide();
        dbList=helpher.getAllContacts();

        final String currentDateTimeString = DateFormat.getDateTimeInstance().format(new Date());

        view = getLayoutInflater().inflate(R.layout.list_row,null);
        mRecyclerView = (RecyclerView)findViewById(R.id.recycleview);

        mRecyclerView.setHasFixedSize(true);

        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        // specify an adapter (see also next example)
        mAdapter = new RecycleAdapter(dbList);
        if(helpher.getContactsCount()>1) {
            mRecyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        }
        mRecyclerView.setAdapter(mAdapter);






    }
}
