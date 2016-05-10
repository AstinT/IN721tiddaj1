package bit.tiddaj1.languagetrainer;

public class Question
{
    //Properties
    private String noun;
    private String article;
    private int image;

    //Constructor
    public Question(String noun, String article, int image)
    {
        this.noun = noun;
        this.article = article;
        this.image = image;
    }

    //Getters and setters
    public String getArticle() { return article; }
    public int getImage() { return image; }
}
