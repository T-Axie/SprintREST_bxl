package be.bstorm.akimts.rest.bxl.exceptions;

import java.util.Set;

public class ReferencedSuppresionException extends RuntimeException {

    private final Class<?> referencedBy;
    private final Set<Object> refId;

    public ReferencedSuppresionException(Class<?> referencedBy, Set<Object> refId) {
        super("Cannot delete the resource because it referenced a one or multiple {"+ referencedBy.getSimpleName() +"} ("+ refId + ")");
        this.referencedBy = referencedBy;
        this.refId = Set.copyOf(refId);
    }

    public ReferencedSuppresionException( Class<?> referencedBy, Set<Object> refId, Throwable cause) {
        super("Cannot delete the resource because it referenced a one or multiple {"+ referencedBy.getSimpleName() +"} ("+ refId + ")", cause);
        this.refId = refId;
        this.referencedBy = referencedBy;
    }

    public Class<?> getReferencedBy() {
        return referencedBy;
    }

    public Set<Object> getRefId() {
        return refId;
    }
}
