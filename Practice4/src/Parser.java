public class Parser { //грамматический анализатор
    char[] symbols;
    int pos = 0;

    public Parser(char[] symbols) {
        this.symbols = symbols;
    }

    void error(String message) {
        throw new RuntimeException(message + " at " + pos);
    }

    boolean match(char expected) {
        if (pos < symbols.length) {
            char c = symbols[pos];
            if (c == expected) { pos++; return true;}
        }
        return false;
    }

    enum State {
        S_0, S_FINAL, S_LOOP
    }

    State newState(State s) {
        switch (s) {
            case S_0:
            case S_LOOP:
                if (match('N'))
                    return State.S_FINAL;
                error("Symbol N expected!");
            case S_FINAL:
                if (match('+'))
                    return State.S_LOOP;
                error("Symbol + expected");
        }
        return null;
    }

    boolean run() { //использует конечные автоматы, подход годится только для регулярных грамматик
        State s = State.S_0;
        while (pos < symbols.length) {
            s = newState(s);
        }
        return s == State.S_FINAL;
    }

    void run2() { //run2 и require используют метод рекурсивного спуска, а не конечные автоматы
        State s = State.S_0;
        require('N');
        while (pos < symbols.length) { //while is equivalent to Kleene star
            require('+'); //S_FINAL to S_LOOP
            require('N'); //S_LOOP to S_FINAL
        }
    }

    void require(char c) {
        if (!match(c))
            error("Symbol " + c + " expected!");
    }
}
