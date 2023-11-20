import java.util.*;

public class LanguageSpecification {
    private List<String> reservedWords = Arrays.asList("int", "string", "char", "for", "while", "if", "else", "cin", "cout", "return");
    private List<String> operators = Arrays.asList("+", "-", "*", "/", "%", "=", "!", "==", "!=", "<", ">", "<=", ">=", "&&", "||", "<<", ">>");
    private List<String> separators = Arrays.asList("(", ")", ";", "{", "}","[", "]", ",", ":", " ");

    private final HashMap<String, Integer> codification = new HashMap<>();

    public LanguageSpecification() {
        createCodification();
    }

    private void createCodification() {
        codification.put("identifier", 0);
        codification.put("constant", 1);

        int code = 2;

        for (String reservedWord : reservedWords) {
            codification.put(reservedWord, code);
            code++;
        }

        for (String operator : operators) {
            codification.put(operator, code);
            code++;
        }

        for (String separator : separators) {
            codification.put(separator, code);
            code++;
        }
    }

    public boolean isReservedWord(String token) {
        return reservedWords.contains(token);
    }

    public boolean isOperator(String token) {
        return operators.contains(token);
    }

    public boolean isPartOfOperator(char op) {
        return op == '!' || isOperator(String.valueOf(op));
    }

    public boolean isSeparator(String token) {
        return separators.contains(token);
    }

    public boolean isIdentifier(String token) {
        if (token == null || token.isEmpty()) {
            return false;
        }

        char firstChar = token.charAt(0);
        if (!Character.isLetter(firstChar) && firstChar != '_') {
            return false;
        }

        for (int i = 1; i < token.length(); i++) {
            char ch = token.charAt(i);
            if (!Character.isLetterOrDigit(ch) && ch != '_') {
                return false;
            }
        }

        return true;
    }

    public boolean isNumericConstant(String token) {
        try {
            Double.parseDouble(token);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public boolean isCharacterConstant(String token) {
        return token.startsWith("'") && token.endsWith("'") && token.length() == 3;
    }

    public boolean isStringConstant(String token) {
        return token.startsWith("\"") && token.endsWith("\"");
    }

    public boolean isConstant(String token) {
        return isNumericConstant(token) || isCharacterConstant(token) || isStringConstant(token);
    }

    public Integer getCode(String token) {
        return codification.get(token);
    }
}
