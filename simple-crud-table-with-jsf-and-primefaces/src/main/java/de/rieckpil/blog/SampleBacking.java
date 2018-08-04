package de.rieckpil.blog;

import javax.faces.view.ViewScoped;
import javax.inject.Named;
import java.io.Serializable;

@Named
@ViewScoped
public class SampleBacking implements Serializable {

    private String someValue = "Hans";

    public void updateValue() {
        this.someValue = "Hello World!";
    }

    public String getSomeValue() {
        return someValue;
    }

    public void setSomeValue(String someValue) {
        this.someValue = someValue;
    }
}
