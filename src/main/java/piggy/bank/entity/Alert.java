package piggy.bank.entity;

public class Alert{
    private String title;
    private String content;

    public static Alert create(String title, String content) {
        Alert alert = new Alert();

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