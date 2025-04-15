package dao;

import entity.Applicant;
import java.util.List;

public interface ApplicantDAO {
    void insertApplicant(Applicant applicant);
    List<Applicant> getAllApplicants();
}