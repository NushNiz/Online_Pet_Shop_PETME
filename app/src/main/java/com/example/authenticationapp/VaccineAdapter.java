package com.example.authenticationapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.List;

public class VaccineAdapter extends RecyclerView.Adapter<VaccineAdapter.VacViewHolder> {

    private ViewVaccines activity;
    private List<VaccineModel> vlist;
    FirebaseFirestore db = FirebaseFirestore.getInstance();

    public VaccineAdapter(ViewVaccines activity, List<VaccineModel>vlist){
        this.activity = activity;
        this.vlist = vlist;
    }


    public void updateData(int position){
        VaccineModel item = vlist.get(position);
        Bundle bundle = new Bundle();
        bundle.putString("uid", item.getid());
        bundle.putString("upvname", item.getName());
        bundle.putString("upvdate", item.getDate());
        bundle.putString("upvdesc",item.getDesc());
        bundle.putString("upvgot",item.getGot());

        Intent intent = new Intent(activity, Vaccination.class);
        intent.putExtras(bundle);
        activity.startActivity(intent);
    }


    public void deleteData(int position){
        VaccineModel item = vlist.get(position);
        db.collection("Documents").document(item.getid()).delete()
                .addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()){
                    notifyRemoved(position);
                    Toast.makeText(activity,"Data Deleted", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(activity,"Error" + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void notifyRemoved(int position){

        vlist.remove(position);
        notifyItemRemoved(position);
        activity.showData();
    }


    @NonNull
    @Override
    public VaccineAdapter.VacViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(activity).inflate(R.layout.vaccines ,parent, false);
        return new VaccineAdapter.VacViewHolder(v);
    }


    @Override
    public void onBindViewHolder(@NonNull VaccineAdapter.VacViewHolder holder, int position) {

        holder.name.setText(vlist.get(position).getName());
        holder.desc.setText(vlist.get(position).getDesc());
        holder.date.setText(vlist.get(position).getDate());
        holder.got.setText(vlist.get(position).getGot());
    }


    @Override
    public int getItemCount() {
        return vlist.size();
    }

    public static class VacViewHolder extends RecyclerView.ViewHolder{

        TextView name, date, desc, got;

        public VacViewHolder(@NonNull View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.tv_vname);
            date = itemView.findViewById(R.id.tv_vdate);
            desc = itemView.findViewById(R.id.tv_vdesc);
            got = itemView.findViewById(R.id.tv_vgot);
        }
    }
}
