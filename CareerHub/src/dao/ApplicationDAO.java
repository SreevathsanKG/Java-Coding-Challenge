package dao;

import entity.JobApplication;
import java.util.List;

public interface ApplicationDAO {
    void insertApplication(JobApplication application);
    List<JobApplication> getApplicationsByJobId(int jobId);
}