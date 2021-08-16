package net.gromer.friday.api.configuration;

public abstract class AbstractSerializer<T> {

    public abstract String toString(final T p0);
    public abstract T fromString(final String p0);
}
