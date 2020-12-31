package piggy.bank.entity;

public class Snack {
    private String title;
    private String content;

    public static Snack create(String title, String content) {
        Snack alert = new Snack();

        alert.setTitle(title);
        alert.setContent(content);

        return alert;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}