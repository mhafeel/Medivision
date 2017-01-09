package com.app.medivision.Database;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import com.app.medivision.R;

/**
 * Created by digital on 12/21/2016.
 */

/********* Adapter class extends with BaseAdapter and implements with OnClickListener ************/

public class RecycleAdapter extends RecyclerView.Adapter<RecycleAdapter.ViewHolder> {
   private List<DatabaseModel> dbList;
    static Context context;
    static DatabaseHandler helper;




    RecycleAdapter(Context context, List<DatabaseModel> dbList ){
        this.dbList=new ArrayList<DatabaseModel>();
        this.context=context;
        this.dbList=dbList;
        this.helper=new DatabaseHandler(context);
    }
    public RecycleAdapter(List<DatabaseModel> dbList) {
        this.dbList = dbList;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView name,email,id,textView;

        public ViewHolder(View view) {
            super(view);
            name = (TextView) view.findViewById(R.id.name);
            email = (TextView) view.findViewById(R.id.email);
            textView = (TextView) view.findViewById(R.id.textview);
            id=(TextView)view.findViewById(R.id.ID);

        }
    }
    @Override
    public RecycleAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemLayoutView = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.list_row, null);

        return new ViewHolder(itemLayoutView);
    }


    @Override
    public void onBindViewHolder(RecycleAdapter.ViewHolder holder, int position) {
//        dbList=helper.getAllContacts();


        DatabaseModel model = dbList.get(position);
        holder.id.setText(String.valueOf(model.getId()));
        holder.name.setText(model.getName());
        holder.email.setText(model.getEmail());
        holder.textView.setText(model.getDate());


    }

    @Override
    public int getItemCount() {
        return dbList.size();
    }


    public void removeItem(int position) {
        dbList.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(position, dbList.size());
    }




    }








