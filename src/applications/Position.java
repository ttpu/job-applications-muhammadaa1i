package applications;

import java.util.ArrayList;
import java.util.List;

public class Position {
    private final String name;
    private final List<String> applicants = new ArrayList<>();
    private String winner = null;

    public Position(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public List<String> getApplicants() {
        return new ArrayList<>(applicants); // Return a copy to prevent external modification
    }

    public String getWinner() {
        return winner;
    }

    public void addApplicant(String applicantName) {
        if (!applicants.contains(applicantName)) {
            applicants.add(applicantName);
        }
    }

    public void setWinner(String winnerName) {
        this.winner = winnerName;
    }

    public boolean hasWinner() {
        return winner != null;
    }
}