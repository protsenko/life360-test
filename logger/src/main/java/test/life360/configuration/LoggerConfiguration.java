package test.life360.configuration;

import test.life360.loggable.BinaryLoggable;

import java.util.List;

public interface LoggerConfiguration<T extends BinaryLoggable> {
    List<T> filters();
    List<T> preprocessors();
}
