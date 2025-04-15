package main;

import java.time.LocalDateTime;
import java.util.Scanner;

import dao.ApplicantDAOImpl;
import dao.ApplicationDAOImpl;
import dao.CompanyDAOImpl;
import dao.JobListingDAOImpl;
import entity.Applicant;
import entity.Company;
import entity.JobApplication;
import entity.JobListing;

public class MainModule {
    public static void main(String[] args) {
        CompanyDAOImpl companyDAO = new CompanyDAOImpl();
        JobListingDAOImpl jobDAO = new JobListingDAOImpl();
        ApplicantDAOImpl applicantDAO = new ApplicantDAOImpl();
        ApplicationDAOImpl applicationDAO = new ApplicationDAOImpl();

        Scanner sc = new Scanner(System.in);
        int choice;
        do {
            System.out.println("\n--- CareerHub Job Board ---");
            System.out.println("1. Add Company");
            System.out.println("2. Post Job");
            System.out.println("3. Register Applicant");
            System.out.println("4. Apply for Job");
            System.out.println("5. View All Jobs");
            System.out.println("6. View Applicants for a Job");
            System.out.println("0. Exit");
            System.out.print("Enter your choice: ");
            choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1:
                    System.out.print("Enter Company ID: ");
                    int compId = sc.nextInt(); sc.nextLine();
                    System.out.print("Enter Company Name: ");
                    String cname = sc.nextLine();
                    System.out.print("Enter Location: ");
                    String cloc = sc.nextLine();
                    companyDAO.insertCompany(new Company(compId, cname, cloc));
                    break;

                case 2:
                    System.out.print("Enter Job ID: ");
                    int jobId = sc.nextInt();
                    System.out.print("Enter Company ID: ");
                    int cId = sc.nextInt(); sc.nextLine();
                    System.out.print("Enter Job Title: ");
                    String jTitle = sc.nextLine();
                    System.out.print("Enter Job Description: ");
                    String jDesc = sc.nextLine();
                    System.out.print("Enter Job Location: ");
                    String jLoc = sc.nextLine();
                    System.out.print("Enter Salary: ");
                    double salary = sc.nextDouble();
                    sc.nextLine();
                    System.out.print("Enter Job Type: ");
                    String type = sc.nextLine();
                    jobDAO.insertJobListing(new JobListing(jobId, cId, jTitle, jDesc, jLoc, salary, type, LocalDateTime.now()));
                    break;

                case 3:
                    System.out.print("Enter Applicant ID: ");
                    int aId = sc.nextInt(); sc.nextLine();
                    System.out.print("Enter First Name: ");
                    String fname = sc.nextLine();
                    System.out.print("Enter Last Name: ");
                    String lname = sc.nextLine();
                    System.out.print("Enter Email: ");
                    String email = sc.nextLine();
                    System.out.print("Enter Phone: ");
                    String phone = sc.nextLine();
                    System.out.print("Enter Resume Path: ");
                    String resume = sc.nextLine();
                    applicantDAO.insertApplicant(new Applicant(aId, fname, lname, email, phone, resume));
                    break;

                case 4:
                    System.out.print("Enter Application ID: ");
                    int appId = sc.nextInt();
                    System.out.print("Enter Job ID: ");
                    int jId = sc.nextInt();
                    System.out.print("Enter Applicant ID: ");
                    int apId = sc.nextInt();
                    sc.nextLine();
                    System.out.print("Enter Cover Letter: ");
                    String letter = sc.nextLine();
                    applicationDAO.insertApplication(new JobApplication(appId, jId, apId, LocalDateTime.now(), letter));
                    break;

                case 5:
                    for (JobListing job : jobDAO.getAllJobListings()) {
                        System.out.println(job.getJobTitle() + " - " + job.getJobLocation() + " - ₹" + job.getSalary());
                    }
                    break;

                case 6:
                    System.out.print("Enter Job ID: ");
                    int jid = sc.nextInt();
                    for (JobApplication app : applicationDAO.getApplicationsByJobId(jid)) {
                        System.out.println("Applicant ID: " + app.getApplicantID() + ", Date: " + app.getApplicationDate());
                    }
                    break;

                case 0:
                    System.out.println("Exiting CareerHub. Goodbye!");
                    break;

                default:
                    System.out.println("Invalid choice.");
            }
        } while (choice != 0);
    }
}
