package sample;

import java.io.Serializable;

import javax.enterprise.context.ApplicationScoped;
import javax.faces.push.Push;
import javax.faces.push.PushContext;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@ApplicationScoped
public class StockMonitorBean implements Serializable {

	private static final long serialVersionUID = -8553072748022291934L;

	@Push
	@Inject
	private PushContext stocksChannel;

	public String getMessage() {
		return "Hello World";
	}

	public void getStocks() {
		System.out.println("Sending");
		stocksChannel.send("Hello World");
	}
}