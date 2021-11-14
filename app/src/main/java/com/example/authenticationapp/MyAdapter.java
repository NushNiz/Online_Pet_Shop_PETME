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

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.myViewHolder> {

    private ShowActivity activity;
    private List<todoModel> mList;
    FirebaseFirestore db= FirebaseFirestore.getInstance();

    public MyAdapter(ShowActivity activity,List<todoModel>mList){
        this.activity=activity;
        this.mList=mList;
    }

    public void updateTodoData(int position){
        todoModel item = mList.get(position);
        Bundle bundle = new Bundle();
        bundle.putString("uId",item.getId());
        bundle.putString("uTitle",item.getTitle());
        bundle.putString("uDesc",item.getDesc());
        Intent intent = new Intent(activity,todo_list.class);
        intent.putExtras(bundle);
        activity.startActivity(intent);
    }

    public void deleteTodoData(int position){
        todoModel item = mList.get(position);
        db.collection("todo_list").document(item.getId()).delete()
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful()){
                            notifyTodoRemoved(position);
                            Toast.makeText(activity, "Data Deleted!", Toast.LENGTH_SHORT).show();
                        }else{
                            Toast.makeText(activity, "Error"+task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    private void notifyTodoRemoved(int position){
        mList.remove(position);
        notifyItemRemoved(position);
        activity.showData();
    }

    @NonNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(activity).inflate(R.layout.item,parent,false);
        return  new myViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull myViewHolder holder, int position) {

        holder.title.setText(mList.get(position).getTitle());
        holder.desc.setText(mList.get(position).getDesc());

    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public static class myViewHolder extends RecyclerView.ViewHolder{

        TextView title,desc;

        public myViewHolder(@NonNull View itemView) {
            super(itemView);

            title=itemView.findViewById(R.id.title_text);
            desc = itemView.findViewById(R.id.desc_text);
        }
    }
}
