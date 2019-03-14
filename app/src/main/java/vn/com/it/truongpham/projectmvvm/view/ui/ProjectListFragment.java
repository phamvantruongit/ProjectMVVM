package vn.com.it.truongpham.projectmvvm.view.ui;


import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import vn.com.it.truongpham.projectmvvm.R;
import vn.com.it.truongpham.projectmvvm.databinding.FragmentProjectListBinding;
import vn.com.it.truongpham.projectmvvm.service.model.Project;
import vn.com.it.truongpham.projectmvvm.view.adapter.ProjectAdapter;
import vn.com.it.truongpham.projectmvvm.view.callback.ProjectClickCallBack;
import vn.com.it.truongpham.projectmvvm.viewmodel.ProjectListViewModel;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProjectListFragment extends Fragment {

    private ProjectAdapter projectAdapter;
    FragmentProjectListBinding binding;
    public static String TAG="ProjectListFragment";

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        ProjectListViewModel viewmodel=ViewModelProviders.of(this).get(ProjectListViewModel.class);
        observeViewModel(viewmodel);
    }

    private void observeViewModel(ProjectListViewModel viewmodel) {
        viewmodel.getProjectListObservable().observe(this, new Observer<List<Project>>() {
            @Override
            public void onChanged(@Nullable List<Project> projects) {
                if(projects!=null){
                    binding.loaddingProjects.setVisibility(View.GONE);
                    projectAdapter.setProjectList(projects);

                }
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding=DataBindingUtil.inflate(inflater,R.layout.fragment_project_list,container,false);

        projectAdapter=new ProjectAdapter(projectClickCallBack);
        binding.projectList.setLayoutManager(new LinearLayoutManager(getActivity()));
        binding.projectList.setAdapter(projectAdapter);

        return binding.getRoot();
    }

    private final ProjectClickCallBack projectClickCallBack=new ProjectClickCallBack() {
        @Override
        public void onClick(Project project) {
            if(getLifecycle().getCurrentState().isAtLeast(Lifecycle.State.STARTED)){
                ((MainActivity) getActivity()).show(project);
            }
        }
    };

}
