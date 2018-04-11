package com.el.betting.common;


import javax.annotation.Nullable;
import java.io.Serializable;
import java.util.Objects;

public final class Wrapper<C, T> implements Serializable {
    private final C equivalence;

    @Nullable
    private final T reference;

    public Wrapper(C equivalence, @Nullable T reference) {
        this.equivalence = Objects.requireNonNull(equivalence);
        this.reference = reference;
    }

    @Nullable
    public T unwrap() {
        return this.reference;
    }

    public boolean equals(@Nullable Object obj) {
        if (obj == this) {
            return true;
        } else {
            if (obj instanceof Wrapper) {
                Wrapper that = (Wrapper) obj;
                if (this.equivalence.equals(that.equivalence)) {
                    C equivalence = this.equivalence;
                    return equivalence.equals(this.equivalence);
                }
            }
            return false;
        }
    }
}