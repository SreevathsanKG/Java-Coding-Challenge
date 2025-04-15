package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import entity.JobApplication;
import utility.DBConnUtil;

public class ApplicationDAOImpl implements ApplicationDAO {
    public void insertApplication(JobApplication application) {
        try (Connection conn = DBConnUtil.getConnection("C:\\Users\\hp\\Downloads\\Hexware Batch 6 Java\\CareerHub\\resources")) {
            String sql = "INSERT INTO Applications VALUES (?, ?, ?, ?, ?)";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, application.getApplicationID());
            ps.setInt(2, application.getJobID());
            ps.setInt(3, application.getApplicantID());
            ps.setTimestamp(4, Timestamp.valueOf(application.getApplicationDate()));
            ps.setString(5, application.getCoverLetter());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<JobApplication> getApplicationsByJobId(int jobId) {
        List<JobApplication> list = new ArrayList<>();
        try (Connection conn = DBConnUtil.getConnection("C:\\Users\\hp\\Downloads\\Hexware Batch 6 Java\\CareerHub\\resources")) {
            String sql = "SELECT * FROM Applications WHERE JobID = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, jobId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                JobApplication app = new JobApplication(
                    rs.getInt("ApplicationID"),
                    rs.getInt("JobID"),
                    rs.getInt("ApplicantID"),
                    rs.getTimestamp("ApplicationDate").toLocalDateTime(),
                    rs.getString("CoverLetter")
                );
                list.add(app);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
}
