package commands;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface CommandDescriptor {
    String[] acceptors() default {"__unhandled"};
}