import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class State {
    private static final AtomicInteger idGenerator = new AtomicInteger(0);

    private ACTION action;
    private final int id;
    private List<Item> closureItems;
    private List<Item> closure;
    private String enrichedSymbol;

    public State() {
        id = 0;
    };

    public State(List<Item> closureItems, List<Item> closure, String enrichedSymbol) {
        this.id = idGenerator.getAndIncrement();
        this.closureItems = closureItems;
        this.closure = closure;
        this.enrichedSymbol = enrichedSymbol;
        setAction(enrichedSymbol);
    }

    public int getId() {
        return id;
    }

    public List<Item> getClosureItems() {
        return closureItems;
    }

    public void setClosureItems(List<Item> closureItems) {
        this.closureItems = closureItems;
    }

    public List<Item> getClosure() {
        return closure;
    }

    public void setClosure(List<Item> closure) {
        this.closure = closure;
    }

    public String getEnrichedSymbol() {
        return enrichedSymbol;
    }

    public void setEnrichedSymbol(String enrichedSymbol) {
        this.enrichedSymbol = enrichedSymbol;
    }

    private void setAction(String enrichedSymbol) {
        if (closure.size() == 1 &&
                closure.get(0).getDotPosition() == closure.get(0).getRhs().size() &&
                closure.get(0).getLhs().equals(enrichedSymbol)) {
            this.action = ACTION.ACCEPT;
        } else if (closure.size() == 1 && closure.get(0).getDotPosition() == closure.get(0).getRhs().size()) {
            this.action = ACTION.REDUCE;
        } else if (!closure.isEmpty() && checkAllNotDotEnd()) {
            this.action = ACTION.SHIFT;
        } else {
            if (closure.size() > 1 && checkAllDotEnd()) {
                this.action = ACTION.REDUCE_REDUCE_CONFLICT;
            } else {
                this.action = ACTION.SHIFT_REDUCE_CONFLICT;
            }
        }
    }

    private boolean checkAllNotDotEnd() {
        return closure.stream().noneMatch(c -> c.getRhs().size() <= c.getDotPosition());
    }

    private boolean checkAllDotEnd() {
        return closure.stream().allMatch(c -> c.getRhs().size() > c.getDotPosition());
    }

    public List<String> getAllSymbolsAfterDot() {
        List<String> result = new ArrayList<>();
        for (Item item : closure) {
            if (item.getDotPosition() < item.getRhs().size()) {
                result.add(item.getRhs().get(item.getDotPosition()));
            }
        }
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        State state = (State) obj;
        return closureItems.equals(state.closureItems);
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder("s" + id + " = closure({");
        for (Item item : closureItems) {
            result.append(item.toString()).append(", ");
        }
        if (!closureItems.isEmpty()) result.setLength(result.length() - 2);
        result.append("}) = {");
        for (Item item : closure) {
            result.append(item.toString()).append(", ");
        }
        if (!closure.isEmpty()) result.setLength(result.length() - 2);
        result.append("}");
        return result.toString();
    }
}
