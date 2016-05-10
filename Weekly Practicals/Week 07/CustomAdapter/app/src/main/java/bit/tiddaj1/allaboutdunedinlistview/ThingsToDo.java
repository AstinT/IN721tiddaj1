package bit.tiddaj1.allaboutdunedinlistview;

import android.graphics.drawable.Drawable;

public class ThingsToDo
{
    //Properties
    String thingsToDoName;
    Drawable thingsToDoImage;

    //Constructor
    public ThingsToDo(String thingsToDoName, Drawable thingsToDoImage)
    {
        this.thingsToDoName = thingsToDoName;
        this.thingsToDoImage = thingsToDoImage;
    }

    //Methods
    @Override
    public String toString()
    {
        return thingsToDoName;
    }
}
