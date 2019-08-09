package de.rieckpil.blog;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.togglz.core.manager.FeatureManager;

@RestController
@RequestMapping("/books")
public class BookController {

    @Autowired
    private FeatureManager featureManager;
}
