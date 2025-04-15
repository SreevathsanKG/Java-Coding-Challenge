package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import entity.Company;
import utility.DBConnUtil;

public class CompanyDAOImpl implements CompanyDAO {
    public void insertCompany(Company company) {
        try (Connection conn = DBConnUtil.getConnection("C:\\Users\\hp\\Downloads\\Hexware Batch 6 Java\\CareerHub\\resources")) {
            String sql = "INSERT INTO Companies VALUES (?, ?, ?)";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, company.getCompanyID());
            ps.setString(2, company.getCompanyName());
            ps.setString(3, company.getLocation());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Company> getAllCompanies() {
        List<Company> list = new ArrayList<>();
        try (Connection conn = DBConnUtil.getConnection("C:\\Users\\hp\\Downloads\\Hexware Batch 6 Java\\CareerHub\\resources")) {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM Companies");
            while (rs.next()) {
                Company c = new Company(
                    rs.getInt("CompanyID"),
                    rs.getString("CompanyName"),
                    rs.getString("Location")
                );
                list.add(c);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
}
