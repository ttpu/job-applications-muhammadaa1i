package applications;

import java.util.SortedMap;

public class Main {
    public static void main(String[] args) {
        HandleApplications app = new HandleApplications();

        try {
            System.out.println("Adding skills: java, sql");
            app.addSkills("java", "sql");
            System.out.println("Adding position: Developer with skills java, sql");
            app.addPosition("Developer", "java", "sql");

            System.out.println("Adding applicant: John Doe with capabilities java:5,sql:5");
            app.addApplicant("John Doe", "java:5,sql:5");

            System.out.println("Entering application for John Doe to Developer");
            app.enterApplication("John Doe", "Developer");

            System.out.println("Setting winner: John Doe for Developer");
            int score = app.setWinner("John Doe", "Developer");
            System.out.println("Winner score for John Doe: " + score);

            System.out.println("Getting skill applicants");
            SortedMap<String, Long> skillApplicants = app.skill_nApplicants();
            System.out.println("Applicants per skill: " + skillApplicants);

            System.out.println("Getting max position");
            String maxPos = app.maxPosition();
            System.out.println("Position with most applicants: " + maxPos);

            System.out.println("Getting capabilities for John Doe");
            String caps = app.getCapabilities("John Doe");
            System.out.println("John Doe's capabilities: " + caps);

        } catch (ApplicationException e) {
            System.out.println("Error: " + e.getMessage());
            e.printStackTrace();
        }
    }
}