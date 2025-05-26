package applications;

import java.util.*;
import java.util.stream.Collectors;

public class HandleApplications {
    private final Map<String, Skill> skills = new HashMap<>();
    private final Map<String, Position> positions = new HashMap<>();
    private final Map<String, Map<String, Integer>> applicants = new HashMap<>(); // applicantName -> skill -> level

    public void addSkills(String... names) throws ApplicationException {
        if (names == null) {
            throw new ApplicationException();
        }
        for (String name : names) {
            if (name == null || name.trim().isEmpty()) {
                throw new ApplicationException();
            }
            if (skills.containsKey(name)) {
                throw new ApplicationException();
            }
            skills.put(name, new Skill(name));
        }
    }

    public void addPosition(String name, String... skillNames) throws ApplicationException {
        if (name == null || name.trim().isEmpty() || skillNames == null) {
            throw new ApplicationException();
        }
        if (positions.containsKey(name)) {
            throw new ApplicationException();
        }
        for (String skillName : skillNames) {
            if (skillName == null || skillName.trim().isEmpty() || !skills.containsKey(skillName)) {
                throw new ApplicationException();
            }
        }
        Position position = new Position(name);
        positions.put(name, position);
        for (String skillName : skillNames) {
            skills.get(skillName).addPosition(position);
        }
    }

    public Skill getSkill(String name) {
        return skills.get(name);
    }

    public Position getPosition(String name) {
        return positions.get(name);
    }

    public void addApplicant(String name, String capabilities) throws ApplicationException {
        if (name == null || name.trim().isEmpty() || capabilities == null) {
            throw new ApplicationException();
        }
        if (applicants.containsKey(name)) {
            throw new ApplicationException();
        }
        Map<String, Integer> capabilityMap = new HashMap<>();
        if (!capabilities.trim().isEmpty()) {
            String[] pairs = capabilities.split(",");
            for (String pair : pairs) {
                String[] parts = pair.split(":");
                if (parts.length != 2 || !skills.containsKey(parts[0].trim())) {
                    throw new ApplicationException();
                }
                try {
                    int level = Integer.parseInt(parts[1].trim());
                    if (level < 1 || level > 10) {
                        throw new ApplicationException();
                    }
                    capabilityMap.put(parts[0].trim(), level);
                } catch (NumberFormatException e) {
                    throw new ApplicationException();
                }
            }
        }
        applicants.put(name, capabilityMap);
    }

    public String getCapabilities(String applicantName) throws ApplicationException {
        if (applicantName == null || applicantName.trim().isEmpty()) {
            throw new ApplicationException();
        }
        if (!applicants.containsKey(applicantName)) {
            throw new ApplicationException();
        }
        Map<String, Integer> capabilities = applicants.get(applicantName);
        if (capabilities.isEmpty()) {
            return "";
        }
        return capabilities.entrySet().stream()
                .map(e -> e.getKey() + ":" + e.getValue())
                .collect(Collectors.joining(","));
    }

    public void enterApplication(String applicantName, String positionName) throws ApplicationException {
        if (applicantName == null || applicantName.trim().isEmpty() ||
                positionName == null || positionName.trim().isEmpty()) {
            throw new ApplicationException();
        }
        if (!applicants.containsKey(applicantName) || !positions.containsKey(positionName)) {
            throw new ApplicationException();
        }
        Position position = positions.get(positionName);
        if (position.getApplicants().contains(applicantName)) {
            throw new ApplicationException();
        }
        Map<String, Integer> capabilities = applicants.get(applicantName);
        List<String> requiredSkills = skills.values().stream()
                .filter(s -> s.getPositions().contains(position))
                .map(Skill::getName)
                .collect(Collectors.toList());
        for (String skill : requiredSkills) {
            if (!capabilities.containsKey(skill)) {
                throw new ApplicationException();
            }
        }
        position.addApplicant(applicantName);
    }

    public int setWinner(String applicantName, String positionName) throws ApplicationException {
        if (applicantName == null || applicantName.trim().isEmpty() ||
                positionName == null || positionName.trim().isEmpty()) {
            throw new ApplicationException();
        }
        if (!applicants.containsKey(applicantName) || !positions.containsKey(positionName)) {
            throw new ApplicationException();
        }
        Position position = positions.get(positionName);
        if (position.hasWinner()) {
            throw new ApplicationException();
        }
        if (!position.getApplicants().contains(applicantName)) {
            throw new ApplicationException();
        }
        List<String> requiredSkills = skills.values().stream()
                .filter(s -> s.getPositions().contains(position))
                .map(Skill::getName)
                .collect(Collectors.toList());
        Map<String, Integer> capabilities = applicants.get(applicantName);
        int sum = 0;
        for (String skill : requiredSkills) {
            if (capabilities.containsKey(skill)) {
                sum += capabilities.get(skill);
            }
        }
        if (sum > 6 * requiredSkills.size()) {
            throw new ApplicationException();
        }
        position.setWinner(applicantName);
        return sum;
    }

    public SortedMap<String, Long> skill_nApplicants() {
        SortedMap<String, Long> result = new TreeMap<>();
        for (Skill skill : skills.values()) {
            long count = applicants.values().stream()
                    .filter(c -> c.containsKey(skill.getName()))
                    .count();
            result.put(skill.getName(), count);
        }
        return result;
    }

    public String maxPosition() {
        if (positions.isEmpty()) {
            return null;
        }
        return positions.values().stream()
                .max((p1, p2) -> p1.getApplicants().size() - p2.getApplicants().size())
                .map(Position::getName)
                .orElse(null);
    }
}