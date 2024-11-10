package com.example.donate;

import static com.example.donate.DBHelper.TABLENAME;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {

    Context context;
    int singledata;
    ArrayList<Model> modelArrayList;
    SQLiteDatabase sqLiteDatabase;

    public MyAdapter(Context context, int singledata, ArrayList<Model> modelArrayList, SQLiteDatabase sqLiteDatabase) {
        this.context = context;
        this.singledata = singledata;
        this.modelArrayList = modelArrayList;
        this.sqLiteDatabase = sqLiteDatabase;
    }

    @NonNull
    @Override
    public MyAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(context);
        View view=inflater.inflate(R.layout.singledata,null);
        return new ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull MyAdapter.ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        final Model model=modelArrayList.get(position);
        byte[]image=model.getProavatar();
        Bitmap bitmap= BitmapFactory.decodeByteArray(image,0,image.length);
        holder.imageavatar.setImageBitmap(bitmap);
        holder.txtname.setText(model.getUsername());
        holder.txtquantity.setText(model.getQuantity());
        holder.txtdetail.setText(model.getDetails());
        holder.flowmenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PopupMenu popupMenu=new PopupMenu(context,holder.flowmenu);
                popupMenu.inflate(R.menu.flow_menu1);
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem menuItem) {
                        switch (menuItem.getItemId()) {
                            case R.id.delete_menu:
                                long recdelete = sqLiteDatabase.delete(TABLENAME, "id=" + model.getId(), null);
                                if (recdelete != -1) {
                                    Toast.makeText(context, "Item Deleted!!!", Toast.LENGTH_SHORT).show();
                                    modelArrayList.remove(position);
                                    notifyDataSetChanged();
                                }
                                break;
                            default:
                                return false;
                        }
                        return false;
                    }
                });
                popupMenu.show();
            }
        });
    }

    @Override
    public int getItemCount( ) {
        return modelArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageavatar;
        TextView txtname, txtquantity, txtdetail;
        ImageButton flowmenu;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageavatar = (ImageView) itemView.findViewById(R.id.viewavatar);
            txtname = (TextView) itemView.findViewById(R.id.txt_name);
            txtquantity = (TextView) itemView.findViewById(R.id.txt_quantity);
            txtdetail = (TextView) itemView.findViewById(R.id.txt_details);
            flowmenu = (ImageButton) itemView.findViewById(R.id.flowmenu);
        }
    }
}
