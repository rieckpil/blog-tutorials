package de.rieckpil.blog;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import java.io.Serializable;

@Named
@ViewScoped
public class CustomerListBean implements Serializable {

	private static final long serialVersionUID = 4773746274170179581L;

	@PostConstruct
	public void init() {

	}

}
