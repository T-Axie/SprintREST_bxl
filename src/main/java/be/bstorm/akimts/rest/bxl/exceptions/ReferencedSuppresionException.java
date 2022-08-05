package be.bstorm.akimts.rest.bxl.exceptions;

import java.util.Set;

public class ReferencedSuppresionException extends RuntimeException {

    private final Set<Object> referencer;

    public ReferencedSuppresionException(Set<Object> referencer) {
        super("Cannot delete the resource for it is referenced by another one");
        this.referencer = Set.copyOf(referencer);
    }

    public Set<Object> getReferencer() {
        return referencer;
    }
}
