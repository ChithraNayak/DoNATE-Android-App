package com.example.donate;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {
    Context context;
    int singledata;
    ArrayList<Model> modelArrayList;
    SQLiteDatabase sqLiteDatabase;
    public Adapter(Context context, int singledata, ArrayList<Model> modelArrayList, SQLiteDatabase sqLiteDatabase) {
        this.context = context;
        this.singledata = singledata;
        this.modelArrayList = modelArrayList;
        this.sqLiteDatabase = sqLiteDatabase;
    }
    @NonNull
    @Override
    public Adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(context);
        View view=inflater.inflate(R.layout.singledata1,null);
        return new Adapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Adapter.ViewHolder holder, int position) {
        final Model model=modelArrayList.get(position);
        byte[]image=model.getProavatar();
        Bitmap bitmap= BitmapFactory.decodeByteArray(image,0,image.length);
        holder.imageavatar.setImageBitmap(bitmap);
        holder.txtname.setText(model.getItemname());
        holder.txtquantity.setText(model.getQuantity());
        holder.txtdetail.setText(model.getDetails());
        holder.imageavatar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(context,Information.class);
                intent.putExtra("extra1",model.getUsername());
                intent.putExtra("extra3",model.getQuantity());
                intent.putExtra("extra4",model.getDetails());
                intent.putExtra("extra5",model.getItemname());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return modelArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageavatar;
        TextView txtname, txtquantity, txtdetail;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageavatar = (ImageView) itemView.findViewById(R.id.viewavatar);
            txtname = (TextView) itemView.findViewById(R.id.txt_name);
            txtquantity = (TextView) itemView.findViewById(R.id.txt_quantity);
            txtdetail = (TextView) itemView.findViewById(R.id.txt_details);
        }
    }
}
