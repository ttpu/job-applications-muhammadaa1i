package applications;

import java.util.ArrayList;
import java.util.List;

public class Skill {
    private final String name;
    private final List<Position> positions = new ArrayList<>();

    public Skill(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public List<Position> getPositions() {
        return new ArrayList<>(positions); // Return a copy to prevent external modification
    }

    public void addPosition(Position position) {
        if (!positions.contains(position)) {
            positions.add(position);
        }
    }
}