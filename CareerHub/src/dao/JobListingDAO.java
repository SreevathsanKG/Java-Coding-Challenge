package dao;

import entity.JobListing;
import java.util.List;

public interface JobListingDAO {
    void insertJobListing(JobListing job);
    List<JobListing> getAllJobListings();
    List<JobListing> getJobsBySalaryRange(double minSalary, double maxSalary);
}