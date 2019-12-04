package utils;

import java.util.Objects;

class Pair<T> {

    T thisVal;
    T thatVal;

    public Pair(T thisVal, T thatVal) {
        this.thisVal = thisVal;
        this.thatVal = thatVal;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pair<?> pair = (Pair<?>) o;
        return thisVal.equals(pair.thisVal) && thatVal.equals(pair.thatVal);
    }

    @Override
    public int hashCode() {
        return Objects.hash(thisVal, thatVal);
    }
}
