import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Parser {
    private Grammar grammar;
    private List<State> canonicalCollection;
    private Map<Integer, Object> parsingTable;

    public Parser(Grammar grammar) {
        this.grammar = grammar;
        this.canonicalCollection = new ArrayList<>();
        this.parsingTable = new HashMap<>();
    }

    public static boolean isItemInClosure(Item item, List<Item> closure) {
        for (Item itemInClosure : closure) {
            if (item.getLhs().equals(itemInClosure.getLhs()) &&
                    item.getRhs().equals(itemInClosure.getRhs()) &&
                    item.getDotPosition() == itemInClosure.getDotPosition()) {
                return true;
            }
        }
        return false;
    }

    public State closure(List<Item> items) {
        List<Item> currentClosure = new ArrayList<>(items);
        boolean finished = false;

        while (!finished) {
            List<Item> oldClosure = new ArrayList<>(currentClosure);
            for (Item closureItem : currentClosure) {
                if (closureItem.getDotPosition() < closureItem.getRhs().size() &&
                        grammar.getN().contains(closureItem.getRhs().get(closureItem.getDotPosition()))) {
                    //TO DO
                }
            }
        }
        return new State();
    }

    public State gotoMethod(State state, String symbol) {
        List<Item> itemsForSymbol = new ArrayList<>();
        for (Item item : state.getClosure()) {
            if (item.getDotPosition() < item.getRhs().size() &&
                    item.getRhs().get(item.getDotPosition()).equals(symbol)) {
                itemsForSymbol.add(new Item(item.getLhs(), item.getRhs(), item.getDotPosition() + 1));
            }
        }

        for (State theState : canonicalCollection) {
            if (theState.getClosureItems().equals(itemsForSymbol)) {
                return theState;
            }
        }

        return closure(itemsForSymbol);
    }
}
