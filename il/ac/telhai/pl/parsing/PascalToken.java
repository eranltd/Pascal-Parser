package il.ac.telhai.pl.parsing;

public abstract class PascalToken {
    public String toString() {
        return getClass().getSimpleName().toUpperCase();
    }
}
