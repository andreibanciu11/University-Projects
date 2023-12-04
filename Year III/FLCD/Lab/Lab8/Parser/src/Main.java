public class Main {
    public static void main(String[] args) {
        Grammar g = new Grammar();
        g.readFromFile("src/Files/g2.in");
        System.out.println(g.toString());

        System.out.println("Set of Nonterminals: " + g.getN());
        System.out.println("Set of Terminals: " + g.getE());
        System.out.println("Set of Productions: " + g.getP());

        String nonterminal = "declaration_list";
        if (g.getP().containsKey(nonterminal)) {
            System.out.println("Productions for Nonterminal " + nonterminal + ": " + g.getP().get(nonterminal));
        } else {
            System.out.println("No productions found for Nonterminal " + nonterminal);
        }


        if (g.checkCFG()) {
            System.out.println("The grammar is a CFG");
        } else {
            System.out.println("The grammar is not a CFG");
        }
    }
}