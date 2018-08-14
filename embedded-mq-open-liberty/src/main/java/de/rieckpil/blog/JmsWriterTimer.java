package de.rieckpil.blog;

import javax.ejb.Schedule;
import javax.ejb.Singleton;

@Singleton
public class JmsWriterTimer {

    @Schedule(second = "*/2", minute = "*", hour = "*", persistent = false)
    public void sayHello() {
        System.out.println("Hello World!");
    }
}
