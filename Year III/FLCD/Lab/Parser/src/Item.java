import java.util.List;
import java.util.Objects;

public class Item {
    private String lhs;
    private List<String> rhs;
    private int dotPosition;

    public String getLhs() {
        return lhs;
    }

    public void setLhs(String lhs) {
        this.lhs = lhs;
    }

    public List<String> getRhs() {
        return rhs;
    }

    public void setRhs(List<String> rhs) {
        this.rhs = rhs;
    }

    public int getDotPosition() {
        return dotPosition;
    }

    public void setDotPosition(int dotPosition) {
        this.dotPosition = dotPosition;
    }

    public Item(String lhs, List<String> rhs, int dotPosition) {
        this.lhs = lhs;
        this.rhs = rhs;
        this.dotPosition = dotPosition;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Item)) return false;
        Item item = (Item) o;
        return dotPosition == item.dotPosition &&
                Objects.equals(lhs, item.lhs) &&
                Objects.equals(rhs, item.rhs);
    }

    @Override
    public int hashCode() {
        return Objects.hash(lhs, rhs, dotPosition);
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder("[" + lhs + " -> ");
        for (int i = 0; i < rhs.size(); i++) {
            if (i == dotPosition) {
                result.append(". ");
            }
            result.append(rhs.get(i)).append(" ");
        }
        if (dotPosition == rhs.size()) {
            result.append(".");
        }
        return result.toString().trim() + "]";
    }
}
