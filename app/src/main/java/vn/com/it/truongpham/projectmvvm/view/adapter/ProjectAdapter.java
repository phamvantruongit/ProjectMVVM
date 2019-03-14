package vn.com.it.truongpham.projectmvvm.view.adapter;

import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;
import java.util.Objects;

import vn.com.it.truongpham.projectmvvm.R;
import vn.com.it.truongpham.projectmvvm.databinding.ProjectListItemBinding;
import vn.com.it.truongpham.projectmvvm.service.model.Project;
import vn.com.it.truongpham.projectmvvm.view.callback.ProjectClickCallBack;

public class ProjectAdapter extends RecyclerView.Adapter<ProjectAdapter.ProjectViewModel> {
   List<? extends Project> projectList;

   @Nullable
   private final ProjectClickCallBack projectClickCallBack;

    public ProjectAdapter(@Nullable ProjectClickCallBack projectClickCallBack) {
        this.projectClickCallBack = projectClickCallBack;
    }

    public void setProjectList(final List<? extends Project> projectList){
        if(this.projectList==null){
            this.projectList=projectList;
            notifyItemRangeInserted(0,projectList.size());
        }else {
            DiffUtil.DiffResult result=DiffUtil.calculateDiff(new DiffUtil.Callback() {
                @Override
                public int getOldListSize() {
                    return ProjectAdapter.this.projectList.size();
                }

                @Override
                public int getNewListSize() {
                    return projectList.size();
                }

                @Override
                public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
                    return ProjectAdapter.this.projectList.get(oldItemPosition).id==projectList.get(newItemPosition).id;
                }

                @Override
                public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
                    Project project = projectList.get(newItemPosition);
                    Project old = projectList.get(oldItemPosition);
                    return project.id == old.id
                            && Objects.equals(project.git_url, old.git_url);
                }
            });
            this.projectList=projectList;
            result.dispatchUpdatesTo(this);
        }
    }

    @NonNull
    @Override
    public ProjectViewModel onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        ProjectListItemBinding binding=DataBindingUtil.inflate(LayoutInflater.from(viewGroup.getContext()),R.layout.project_list_item,viewGroup,false);
        return new ProjectViewModel(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull final ProjectViewModel projectViewModel, final int position) {
        projectViewModel.binding.setProject(projectList.get(position));
        projectViewModel.binding.executePendingBindings();
        projectViewModel.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                projectClickCallBack.onClick(projectList.get(position));
            }
        });
    }

    @Override
    public int getItemCount() {
        return projectList==null ? 0 : projectList.size();
    }

    public class ProjectViewModel extends RecyclerView.ViewHolder {
        ProjectListItemBinding binding;
        public ProjectViewModel( ProjectListItemBinding binding) {
            super(binding.getRoot());
            this.binding=binding;
        }
    }
}
