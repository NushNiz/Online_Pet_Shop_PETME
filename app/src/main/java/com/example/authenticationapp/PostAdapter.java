package com.example.authenticationapp;


import android.annotation.SuppressLint;
import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.FirebaseDatabase;
import com.orhanobut.dialogplus.DialogPlus;
import com.squareup.picasso.Picasso;

import java.util.HashMap;
import java.util.Map;

public class PostAdapter extends FirebaseRecyclerAdapter<PostModel,PostAdapter.ViewHolder> {

    private Context context;
    public PostAdapter(@NonNull FirebaseRecyclerOptions<PostModel> options, Context context) {
        super(options);
        this.context=context;
    }

    @Override
    protected void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position, @NonNull PostModel model) {

            holder.tvDescription.setText(model.getDescription());
            holder.tvCategory.setText(model.getCategory());
            holder.tvTelephonenumber.setText(model.getTelephonenumber());
            holder.tvPrice.setText(model.getPrice());

            String imageUri=model.getImage();

            Picasso.get().load(imageUri).into(holder.imageAdd);//from this line we can get the image from database


        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    FirebaseDatabase.getInstance().getReference().child("Pet")
                            .child(getRef(position).getKey())
                            .removeValue()
                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    Toast.makeText(context, "Deleted Successfully", Toast.LENGTH_SHORT).show();
                                }
                            });

                FirebaseDatabase.getInstance().getReference().child("User")
                        .child(getRef(position).getKey())
                        .removeValue()
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                Toast.makeText(context, "Deleted Successfully", Toast.LENGTH_SHORT).show();
                            }
                        });
            }
        });

            holder.update.setOnClickListener(new View.OnClickListener(){

                @Override
                public void onClick(View view) {
                    DialogPlus dialog = DialogPlus.newDialog(context)
                            .setGravity(Gravity.CENTER)
                            .setMargin(50,0,50,0)
                            .setContentHolder(new com.orhanobut.dialogplus.ViewHolder(R.layout.content))
                            .setExpanded(false)
                            .create();
                                View holderView = dialog.getHolderView();

                    EditText description = holderView.findViewById(R.id.update_id);
                    EditText category = holderView.findViewById(R.id.update_name);
                    EditText telephonenumber = holderView.findViewById(R.id.update_fee);
                    EditText price = holderView.findViewById(R.id.update_duration);
                    Button btnUpdate=holderView.findViewById(R.id.btnupdate);



                    description.setText(model.getDescription());
                    category.setText(model.getCategory());
                    telephonenumber.setText(model.getTelephonenumber());
                    price.setText(model.getPrice());

                        btnUpdate.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {

                                Map<String, Object> map= new HashMap<>();
                                map.put("Description",description.getText().toString());
                                map.put("Category",category.getText().toString());
                                map.put("Telephonenumber",telephonenumber.getText().toString());
                                map.put("Price",price.getText().toString());

                                FirebaseDatabase.getInstance().getReference().child("Pet")
                                        .child(getRef(position).getKey())
                                        .updateChildren(map).addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        dialog.dismiss();
                                    }
                                });


                                Map<String, Object> map1= new HashMap<>();
                                map1.put("Description",description.getText().toString());
                                map1.put("Category",category.getText().toString());
                                map1.put("Telephonenumber",telephonenumber.getText().toString());
                                map1.put("Price",price.getText().toString());


                                FirebaseDatabase.getInstance().getReference().child("User")
                                        .child(getRef(position).getKey())
                                        .updateChildren(map1).addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        dialog.dismiss();
                                    }
                                });
                            }
                        });

                    dialog.show();
                }
            });
}


    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.desgin_row_for_recyclerview, parent,false);

       return new ViewHolder(view);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {




        TextView tvDescription,tvCategory,tvTelephonenumber,tvPrice;
        ImageView imageAdd,update,delete;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tvDescription = itemView.findViewById(R.id.tv_courseIdRetrive);
            tvCategory = itemView.findViewById(R.id.tv_courseNameRetrive);
            tvTelephonenumber = itemView.findViewById(R.id.tv_courseFeeRetrive);
            tvPrice = itemView.findViewById(R.id.tv_courseDurationRetrive);
            imageAdd = itemView.findViewById(R.id.image_ViewCourse);
            update=itemView.findViewById(R.id.image_edit);
            delete=itemView.findViewById(R.id.image_delete);



        }
    }
}
