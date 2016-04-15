package bit.tiddaj1.lastfmwebservice;

public class Artist
{
    private String name;
    private int listenerCount;

    public Artist(String name, int listenerCount)
    {
        this.name = name;
        this.listenerCount = listenerCount;
    }

    public String getName()
    {
        return name;
    }

    public int getListenerCount()
    {
        return listenerCount;
    }
}
