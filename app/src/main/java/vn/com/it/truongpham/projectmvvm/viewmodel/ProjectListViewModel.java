package vn.com.it.truongpham.projectmvvm.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import java.util.List;

import vn.com.it.truongpham.projectmvvm.service.model.Project;
import vn.com.it.truongpham.projectmvvm.service.repository.ProjectRepository;

public class ProjectListViewModel extends AndroidViewModel {
    private final LiveData<List<Project>> projectListObservable;
    public ProjectListViewModel(@NonNull Application application) {
        super(application);
        this.projectListObservable=ProjectRepository.getInstance().getProjectList("Google");
    }

    public LiveData<List<Project>> getProjectListObservable(){
        return projectListObservable;
    }
}
