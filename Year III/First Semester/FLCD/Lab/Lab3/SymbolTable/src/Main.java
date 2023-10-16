public class Main {
    public static void main(String[] args) {
        SymbolTable symbolTable = new SymbolTable(100);
        symbolTable.put("andrei");
        symbolTable.put("banciu");
        symbolTable.put("banciu");
        symbolTable.printAll();
        }
    }