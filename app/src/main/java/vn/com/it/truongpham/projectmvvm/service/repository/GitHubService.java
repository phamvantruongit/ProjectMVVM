package vn.com.it.truongpham.projectmvvm.service.repository;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import vn.com.it.truongpham.projectmvvm.service.model.Project;

interface GitHubService {
    String BASE_URL ="https://api.github.com/";

    @GET("users/{user}/repos")
    Call<List<Project>> getProjectList(@Path("user") String user);

    @GET("/repos/{user}/{reponame}")
    Call<Project> getProjectDetails(@Path("user") String user,@Path("reponame") String project);

}
