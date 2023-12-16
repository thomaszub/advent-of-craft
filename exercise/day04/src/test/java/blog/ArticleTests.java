package blog;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class ArticleTests {
    @Test
    void it_should_add_a_comment() throws CommentAlreadyExistException {
        var article = new Article(
                "Lorem Ipsum",
                "consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore"
        );

        var text = "Amazing article !!!";
        var author = "Pablo Escobar";
        article.addComment(text, author);

        var comments = article.getComments();
        assertThat(comments).hasSize(1);
        assertThat(comments.getFirst())
                .hasFieldOrPropertyWithValue("author", author)
                .hasFieldOrPropertyWithValue("text", text);
    }

    @Test
    void it_should_throw_an_exception_when_adding_existing_comment() throws CommentAlreadyExistException {
        var article = new Article(
                "Lorem Ipsum",
                "consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore"
        );
        article.addComment("Amazing article !!!", "Pablo Escobar");

        assertThatThrownBy(() ->
                article.addComment("Amazing article !!!", "Pablo Escobar")
        ).isInstanceOf(CommentAlreadyExistException.class);
    }
}
