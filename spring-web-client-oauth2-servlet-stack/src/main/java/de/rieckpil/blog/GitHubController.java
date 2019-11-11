package de.rieckpil.blog;

import com.fasterxml.jackson.databind.node.ArrayNode;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.annotation.RegisteredOAuth2AuthorizedClient;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.security.oauth2.client.web.reactive.function.client.ServerOAuth2AuthorizedClientExchangeFilterFunction.oauth2AuthorizedClient;

@Controller
@RequestMapping("/")
public class GitHubController {

    private static final String GITHUB_API_URL = "https://api.github.com";

    private WebClient webClient;

    public GitHubController(WebClient webClient) {
        this.webClient = webClient;
    }

    @GetMapping
    public String index(@RegisteredOAuth2AuthorizedClient OAuth2AuthorizedClient authorizedClient,
                        @AuthenticationPrincipal OAuth2User oauth2User,
                        Model model) {

        model.addAttribute("repositories", fetchAllRepositories(authorizedClient));
        model.addAttribute("username", oauth2User.getAttributes().get("login"));

        return "index";
    }

    private List<String> fetchAllRepositories(OAuth2AuthorizedClient authorizedClient) {
        List<String> repositoryNames = new ArrayList<>();

        this.webClient
                .get()
                .uri(GITHUB_API_URL, uriBuilder ->
                        uriBuilder
                                .path("/user/repos")
                                .queryParam("per_page", 100)
                                .build()
                )
                .attributes(oauth2AuthorizedClient(authorizedClient))
                .retrieve()
                .bodyToMono(ArrayNode.class)
                .block()
                .forEach(jsonNode -> repositoryNames.add(jsonNode.get("full_name").asText()));

        return repositoryNames;
    }

}
