package de.rieckpil.blog.springbootwithopenliberty;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class ChatMessage {

    @NotEmpty
    private String content;

    @NotEmpty
    private String username;
}
