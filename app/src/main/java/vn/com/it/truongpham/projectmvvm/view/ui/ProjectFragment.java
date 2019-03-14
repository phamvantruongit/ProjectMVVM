package vn.com.it.truongpham.projectmvvm.view.ui;


import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import vn.com.it.truongpham.projectmvvm.databinding.FragmentProjectBinding;
import vn.com.it.truongpham.projectmvvm.R;
import vn.com.it.truongpham.projectmvvm.service.model.Project;
import vn.com.it.truongpham.projectmvvm.viewmodel.ProjectViewModel;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProjectFragment extends Fragment {
    private static final String  KEY_PROJECT_ID="project_id";
    private FragmentProjectBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding=DataBindingUtil.inflate(inflater,R.layout.fragment_project,container,false);
        return binding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        ProjectViewModel.Factory factory=new ProjectViewModel.Factory(getActivity().getApplication(),getArguments().getString(KEY_PROJECT_ID));

        ProjectViewModel viewModel=ViewModelProviders.of(this,factory).get(ProjectViewModel.class);

        binding.setProjectViewModel(viewModel);
        observeViewModel(viewModel);
    }

    private void observeViewModel(final ProjectViewModel viewModel) {
        viewModel.getProjectObservable().observe(this, new Observer<Project>() {
            @Override
            public void onChanged(@Nullable Project project) {
                if(project!=null){
                    viewModel.setProject(project);
                }
            }
        });
    }

   public static ProjectFragment forProject(String projectID){
       ProjectFragment fragment=new ProjectFragment();
       Bundle bundle=new Bundle();
       bundle.putString(KEY_PROJECT_ID,projectID);
       fragment.setArguments(bundle);
       return fragment;
    }
}
