package vn.com.it.truongpham.projectmvvm.view.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import vn.com.it.truongpham.projectmvvm.R;
import vn.com.it.truongpham.projectmvvm.service.model.Project;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(savedInstanceState==null){
            ProjectListFragment fragment=new ProjectListFragment();
            getSupportFragmentManager().beginTransaction().add(R.id.fragment_container,fragment,ProjectListFragment.TAG).commit();
        }
    }

    public void show(Project project) {
        ProjectFragment projectFragment=ProjectFragment.forProject(project.name);

        getSupportFragmentManager().beginTransaction().addToBackStack("project")
                .replace(R.id.fragment_container,projectFragment,null).commit();
    }
}
