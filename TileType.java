import java.awt.Color;

public enum TileType
{
    GROUND('#', true, false, new Color(70, 150, 70)),
    PATH('X', false, true, new Color(220, 150, 60)),
    START('S', false, true, new Color(220, 150, 60)),
    END('E', false, true, new Color(220, 150, 60));

    private final char symbol;
    private final boolean buildable;
    private final boolean path;
    private final Color color;

    TileType(char symbol, boolean buildable, boolean path, Color color)
    {
        this.symbol = symbol;
        this.buildable = buildable;
        this.path = path;
        this.color = color;
    }

    public char getSymbol()
    {
        return symbol;
    }

    public boolean isBuildable()
    {
        return buildable;
    }

    public boolean isPath()
    {
        return path;
    }

    public Color getColor()
    {
        return color;
    }

    public static TileType fromChar(char ch)
    {
        for (TileType type : values())
        {
            if (type.symbol == ch)
                return type;
        }

        return GROUND;
    }
}