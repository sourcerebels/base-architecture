package com.sourcerebels.basearchitecture.logger;

public interface Logger {

    void debug(String tag, String message);

    void info(String tag, String message);

    void warn(String tag, String message);

    void warn(String tag, String message, Throwable tr);

    void error(String tag, String message);

    void error(String tag, String message, Throwable tr);
}
