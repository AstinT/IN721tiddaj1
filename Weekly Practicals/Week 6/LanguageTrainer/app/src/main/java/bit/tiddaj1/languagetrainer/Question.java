package bit.tiddaj1.languagetrainer;

import android.media.Image;

public class Question
{
    //Properties
    private String noun;
    private String article;
    private int image;

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
