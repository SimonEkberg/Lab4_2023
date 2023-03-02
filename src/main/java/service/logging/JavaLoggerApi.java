package service.logging;

import java.util.function.Supplier;
import java.util.logging.Level;

public class JavaLoggerApi implements Logger{
    @Override
    public void info(Supplier<String> messageSuplier) {
        java.util.logging.Logger.getLogger(JavaLoggerApi.class.getName()).log(Level.INFO,
                messageSuplier);
    }
    @Override
    public void error(Throwable ex) {
        java.util.logging.Logger.getLogger(JavaLoggerApi.class.getName()).log(Level.SEVERE, ex.getMessage(), ex);
    }
}
