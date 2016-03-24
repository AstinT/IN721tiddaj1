package bit.tiddaj1.languagetrainer;

public class Question
{
    //Properties
    private String noun;
    private String article;

    public Question(String noun, String article)
    {
        this.noun = noun;
        this.article = article;
    }

    //Getters and setters
    public String getNoun() { return noun; }
    public void setNoun(String noun) { this.noun = noun; }
    public String getArticle() { return article; }
    public void setArticle(String article) { this.article = article; }
}
