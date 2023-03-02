package service.logging;

import java.util.function.Supplier;

public interface Logger {
    void info(Supplier<String> message);
    void error(Throwable ex);
    static Logger get(){
        return new JavaLoggerApi();
    }
}
