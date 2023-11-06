import java.io.File;
import java.io.IOException;
import java.util.*;

public class FiniteAutomaton {
    public Set<String> alphabet, states, finalStates;
    public String initialState;

    public FiniteAutomaton(String filename) {
        this.states = new HashSet<>();
        this.alphabet = new HashSet<>();
        this.finalStates = new HashSet<>();

        readFiniteAutomaton(filename);
    }

    private void readFiniteAutomaton(String filename){
        try {
            File file = new File(filename);
            Scanner reader = new Scanner(file);
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public String writeAlphabet(){
        StringBuilder builder = new StringBuilder();
        builder.append("Alphabet: ");
        for (String a : alphabet){
            builder.append(a).append(" ");
        }

        return builder.toString();
    }

    public String writeStates(){
        StringBuilder builder = new StringBuilder();
        builder.append("States: ");
        for (String s : states){
            builder.append(s).append(" ");
        }

        return builder.toString();
    }

    @Override
    public String toString() {
        return "FiniteAutomaton{" +
                "alphabet=" + alphabet +
                ", states=" + states +
                ", finalStates=" + finalStates +
                ", initialState='" + initialState + '\'' +
                '}';
    }
}