package book.dto;

public class ArticleForm {

    private long id;
    private String title;
    private String content;

    public ArticleForm(long id, String title, String content) {
        this.id = id;
        this.title = title;
        this.content = content;
    }
}
