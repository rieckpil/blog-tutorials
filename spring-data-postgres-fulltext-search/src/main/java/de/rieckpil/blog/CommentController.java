package de.rieckpil.blog;

import java.util.List;

import javax.persistence.EntityManager;

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

	@Autowired
	private EntityManager em;

	@GetMapping
	public List<Comment> getCommentsContainingText(@RequestParam("q") String queryText) {

		List<Comment> list = em.createQuery("SELECT c FROM Comment c where fts(english, c.commentText, :commentText) = true", Comment.class)
				.setParameter("commentText", queryText).getResultList();

		return list;
	}

}
