package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import entity.JobListing;
import utility.DBConnUtil;

public class JobListingDAOImpl implements JobListingDAO {
    public void insertJobListing(JobListing job) {
        try (Connection conn = DBConnUtil.getConnection("C:\\Users\\hp\\Downloads\\Hexware Batch 6 Java\\CareerHub\\resources")) {
            String sql = "INSERT INTO Jobs VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, job.getJobID());
            ps.setInt(2, job.getCompanyID());
            ps.setString(3, job.getJobTitle());
            ps.setString(4, job.getJobDescription());
            ps.setString(5, job.getJobLocation());
            ps.setDouble(6, job.getSalary());
            ps.setString(7, job.getJobType());
            ps.setTimestamp(8, Timestamp.valueOf(job.getPostedDate()));
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<JobListing> getAllJobListings() {
        List<JobListing> list = new ArrayList<>();
        try (Connection conn = DBConnUtil.getConnection("C:\\Users\\hp\\Downloads\\Hexware Batch 6 Java\\CareerHub\\resources")) {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM Jobs");
            while (rs.next()) {
                JobListing job = new JobListing(
                    rs.getInt("JobID"),
                    rs.getInt("CompanyID"),
                    rs.getString("JobTitle"),
                    rs.getString("JobDescription"),
                    rs.getString("JobLocation"),
                    rs.getDouble("Salary"),
                    rs.getString("JobType"),
                    rs.getTimestamp("PostedDate").toLocalDateTime()
                );
                list.add(job);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public List<JobListing> getJobsBySalaryRange(double minSalary, double maxSalary) {
        List<JobListing> list = new ArrayList<>();
        try (Connection conn = DBConnUtil.getConnection("C:\\Users\\hp\\Downloads\\Hexware Batch 6 Java\\CareerHub\\resources")) {
            String sql = "SELECT * FROM Jobs WHERE Salary BETWEEN ? AND ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setDouble(1, minSalary);
            ps.setDouble(2, maxSalary);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                JobListing job = new JobListing(
                    rs.getInt("JobID"),
                    rs.getInt("CompanyID"),
                    rs.getString("JobTitle"),
                    rs.getString("JobDescription"),
                    rs.getString("JobLocation"),
                    rs.getDouble("Salary"),
                    rs.getString("JobType"),
                    rs.getTimestamp("PostedDate").toLocalDateTime()
                );
                list.add(job);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
}
