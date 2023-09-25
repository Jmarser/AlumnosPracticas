package com.jmarser.alumnospracticas_1.usuarios.adapter;


import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.jmarser.alumnospracticas_1.R;
import com.jmarser.alumnospracticas_1.api.models.Task;
import com.jmarser.alumnospracticas_1.api.models.User;
import com.jmarser.alumnospracticas_1.databinding.RowTodosUserBinding;

import java.util.ArrayList;

public class TasksAdapter extends RecyclerView.Adapter<TasksAdapter.TaskViewHolder> {

    private ArrayList<Task> listadoTasks;
    private ArrayList<Task> listadoTasksFiltered;

    public TasksAdapter(ArrayList<Task> listadoTasks) {
        this.listadoTasks = listadoTasks;
        this.listadoTasksFiltered = new ArrayList<>(listadoTasks);
    }

    @NonNull
    @Override
    public TaskViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        RowTodosUserBinding binding = RowTodosUserBinding.inflate(inflater, parent, false);
        return new TaskViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull TaskViewHolder holder, int position) {
        holder.bindTask(listadoTasksFiltered.get(position));
    }

    @Override
    public int getItemCount() {
        return listadoTasksFiltered.size();
    }

    public void filtrarTasksForUser(User user){
        listadoTasksFiltered.clear();

        for(Task task: listadoTasks){
            if(task.getUserId() == user.getId()){
                listadoTasksFiltered.add(task);
            }
        }
    }

    class TaskViewHolder extends RecyclerView.ViewHolder{

        private RowTodosUserBinding binding;

        public TaskViewHolder(@NonNull RowTodosUserBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bindTask(Task task){
            binding.tvTitleTodo.setText(task.getTitle());

            boolean completed = task.isCompleted();

            if(completed){
                binding.imgCheckTodo.setImageResource(R.drawable.ic_check_true);
            }else{
                binding.imgCheckTodo.setImageResource(R.drawable.ic_check_false);
            }
        }
    }
}
