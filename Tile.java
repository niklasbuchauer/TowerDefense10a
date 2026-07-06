public class Tile
{
    private int row;
    private int col;
    private TileType type;

    public Tile(int row, int col, TileType type)
    {
        this.row = row;
        this.col = col;
        this.type = type;
    }

    public int getRow()
    {
        return row;
    }

    public int getCol()
    {
        return col;
    }

    public boolean isPath()
    {
        return type.isPath();
    }

    public boolean isBuildable()
    {
        return type.isBuildable();
    }

    public TileType getType()
    {
        return type;
    }
}