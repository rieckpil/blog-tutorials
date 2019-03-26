package de.rieckpil.blog;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/comments")
public class CommentController {

	@Autowired
	private CommentRepository commentRepository;

	@GetMapping
	public List<Comment> getCommentsContainingText(@RequestParam("q") String queryText) {
		return commentRepository.findAll();
	}

}
