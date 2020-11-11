package test.life360.filter;

import test.life360.loggable.BinaryLoggable;

public interface LoggableFilter<T extends BinaryLoggable> {
    boolean match(T loggable);
}
