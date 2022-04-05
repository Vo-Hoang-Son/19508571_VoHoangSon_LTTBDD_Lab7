package com.example.myapplication;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class ListAdapter extends BaseAdapter {
    private List<DiaDiem> list ;
    private int idLayout ;
    private Context context;

    public ListAdapter(List<DiaDiem> list, int idLayout, Context context) {
        this.list = list;
        this.idLayout = idLayout;
        this.context = context;
    }

    @Override

    public int getCount() {
       if(list == null){
           return 0;
       }
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if(view == null){
            view = LayoutInflater.from(viewGroup.getContext()).inflate(idLayout,viewGroup,false);
        }
        TextView name =  view.findViewById(R.id.name);
        name.setText((i+1) +". "+list.get(i).getName());
        ImageView delete = view.findViewById(R.id.delete);
        ImageView edit = view.findViewById(R.id.edit);
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                MainActivityB.databaseHandlerB.deletePerson(list.get(i).getId());
                MainActivityB.loadListView();

            }
        });
        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, UpdateName.class);
                intent.putExtra("person",list.get(i));
                context.startActivity(intent);
            }
        });

        return view;
    }
}
