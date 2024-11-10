package com.example.donate;

import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;

public class AddItem extends AppCompatActivity {
    Button btn_add;
    ImageView mImageView;
    EditText item_name, item_quantity, item_details;
    ActivityResultLauncher<String> mGetContent;
    DBHelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_item);
        mImageView = (ImageView) findViewById(R.id.image_view);
        item_name = (EditText) findViewById(R.id.item_name);
        item_quantity = (EditText) findViewById(R.id.item_quantity);
        item_details = (EditText) findViewById(R.id.item_details);
        btn_add = (Button) findViewById(R.id.btn_add);
        DB = new DBHelper(this);
        String str1=getIntent().getStringExtra("extra1");
        String str2=getIntent().getStringExtra("extra2");
        mGetContent = registerForActivityResult(new ActivityResultContracts.GetContent(), new ActivityResultCallback<Uri>() {
            @Override
            public void onActivityResult(Uri result) {
                mImageView.setImageURI(result);
            }
        });
        mImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mGetContent.launch("image/*");
            }
        });
        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String i1 = item_name.getText().toString();
                String i2 = item_quantity.getText().toString();
                String i3 = item_details.getText().toString();
                byte[] img = imageViewToByte(mImageView);
                if (i1.equals("") || i2.equals("") || i3.equals("")) {
                    Toast.makeText(AddItem.this, "Enter All Fields", Toast.LENGTH_SHORT).show();
                } else {
                    boolean insert = DB.insertdata1(str1, str2, i1, i2, i3, img);
                    if (insert == false) {

                        Toast.makeText(AddItem.this, "New Item Not Inserted", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(AddItem.this, "New Item Inserted", Toast.LENGTH_SHORT).show();
                        item_name.setText("");
                        item_details.setText("");
                        item_quantity.setText("");
                    }
                }
            }
        });
    }

    private byte[] imageViewToByte(ImageView image) {
        Bitmap bitmap = ((BitmapDrawable) image.getDrawable()).getBitmap();
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
        byte[] byteArray = stream.toByteArray();
        return byteArray;
    }
}
