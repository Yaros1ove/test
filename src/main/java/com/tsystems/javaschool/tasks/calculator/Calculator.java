public class Calculator {
    int fromIndex = 0;

    /**
     * Evaluate statement represented as string.
     *
     * @param statement mathematical statement containing digits, '.' (dot) as decimal mark,
     *                  parentheses, operations signs '+', '-', '*', '/'<br>
     *                  Example: <code>(1 + 38) * 4.5 - 1 / 2.</code>
     * @return string value containing result of evaluation or null if statement is invalid
     */
    public String evaluate(String statement) {
        // TODO: Implement the logic here
        try {
            if (statement.isEmpty()) return null;
        } catch (NullPointerException e) {
            return null;
        }
        try {
        statement = statement.trim();
        while (statement.indexOf('(') != -1) {
            while (statement.indexOf('(', fromIndex) != -1) {
                fromIndex = statement.indexOf('(', fromIndex) + 1;
            }
            int endIndex = statement.indexOf(')', fromIndex);
            String tmp = statement.substring(fromIndex, endIndex);
            statement = statement.substring(0, fromIndex - 1) + calculateManyOperations(tmp) + statement.substring(endIndex + 1);
            fromIndex = 0;
        }} catch (StringIndexOutOfBoundsException e) {
            return null;
        }

        return calculateManyOperations(statement);
    }

    String calculateOneOperation(String s) {
        String result;
        int intResult = 0;
        double doubleResult = 0;
        String a = "";
        String b = "";
        boolean dec = false;
        String operand = "";
        double aD = 0;
        double bD = 0;
        int aI = 0;
        int bI = 0;
        if (s.indexOf('+') != -1) {
            a = s.substring(0, s.indexOf('+'));
            b = s.substring(s.indexOf('+') + 1);
            operand = "plus";
        } else if (s.indexOf('*') != -1) {
            a = s.substring(0, s.indexOf('*'));
            b = s.substring(s.indexOf('*') + 1);
            operand = "mult";
        } else if (s.indexOf('/') != -1) {
            a = s.substring(0, s.indexOf('/'));
            b = s.substring(s.indexOf('/') + 1);
            operand = "div";
        } else if (s.indexOf('-') != -1) {
            a = s.substring(0, s.indexOf('-'));
            b = s.substring(s.indexOf('-') + 1);
            operand = "minus";
        }
        if (a.indexOf('.') != -1 || b.indexOf('.') != -1 || operand.equals("div")) {
            aD = Double.parseDouble(a);
            bD = Double.parseDouble(b);
            dec = true;
        } else {
            aI = Integer.parseInt(a);
            bI = Integer.parseInt(b);
        }
        switch (operand) {
            case "plus":
                if (dec) {
                    doubleResult = aD + bD;
                } else {
                    intResult = aI + bI;
                }
                break;
            case "minus":
                if (dec) {
                    doubleResult = aD - bD;
                } else {
                    intResult = aI - bI;
                }
                break;
            case "mult":
                if (dec) {
                    doubleResult = aD * bD;
                } else {
                    intResult = aI * bI;
                }
                break;
            case "div":
                if (aD % bD == 0) {
                    aI = (int) aD;
                    bI = (int) bD;
                    intResult = aI / bI;
                    dec = false;
                } else {
                    doubleResult = aD / bD;
                }
                break;
        }
        if (dec) {
            result = String.valueOf(doubleResult);
        } else {
            result = String.valueOf(intResult);
        }
        return result;
    }

    String calculateManyOperations(String s) {
        try {
            while (s.indexOf('*') != -1 || s.indexOf('/') != -1) {
                int operandIndex;
                int beginIndex = 0;
                int endIndex = s.length();
                if (s.indexOf('*') != -1 && (s.indexOf('*') < s.indexOf('/') || s.indexOf('/') == -1)) {
                    operandIndex = s.indexOf('*');
                } else {
                    operandIndex = s.indexOf('/');
                }
                if (s.indexOf('+') != -1 && s.indexOf('+') < operandIndex) {
                    beginIndex = s.indexOf('+') + 1;
                }
                if (s.indexOf('-') != -1 && s.indexOf('-') < operandIndex && s.indexOf('-') > beginIndex) {
                    beginIndex = s.indexOf('-') + 1;
                }
                if (s.indexOf('+', operandIndex + 1) != -1) {
                    endIndex = s.indexOf('+', operandIndex + 1);
                }
                if (s.indexOf('-', operandIndex + 1) != -1 && s.indexOf('-', operandIndex + 1) != operandIndex + 1 && s.indexOf('-', operandIndex + 1) < endIndex) {
                    endIndex = s.indexOf('-', operandIndex + 1);
                }
                if (s.indexOf('*', operandIndex + 1) != -1 && s.indexOf('*', operandIndex + 1) < endIndex) {
                    endIndex = s.indexOf('*', operandIndex + 1);
                }
                if (s.indexOf('/', operandIndex + 1) != -1 && s.indexOf('/', operandIndex + 1) < endIndex) {
                    endIndex = s.indexOf('/', operandIndex + 1);
                }
                String tmp = s.substring(beginIndex, endIndex);
                String tmpResult = calculateOneOperation(tmp);
                s = s.substring(0, beginIndex) + tmpResult + s.substring(endIndex);
            }
            while (s.indexOf('+') != -1 || (s.indexOf('-') != -1 && s.indexOf('-') != 0)) {
                int operandIndex;
                int endIndex = s.length();
                if (s.indexOf('+') != -1 && (s.indexOf('+') < s.indexOf('-') || s.indexOf('-') == -1 || s.indexOf('-') == 0)) {
                    operandIndex = s.indexOf('+');
                } else {
                    operandIndex = s.indexOf('-');
                }
                if (s.indexOf('+', operandIndex + 1) != -1) {
                    endIndex = s.indexOf('+', operandIndex + 1);
                }
                if (s.indexOf('-', operandIndex + 1) != -1 && s.indexOf('-', operandIndex + 1) < endIndex) {
                    endIndex = s.indexOf('-', operandIndex + 1);
                }
                String tmp = s.substring(0, endIndex);
                String tmpResult = calculateOneOperation(tmp);
                s = tmpResult + s.substring(endIndex);
            }
        } catch (NumberFormatException e) {
            return null;
        }
        if (s.equals("Infinity")) return null;
        return s;
    }
}
