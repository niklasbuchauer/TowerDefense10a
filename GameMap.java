import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;

public class GameMap
{
    public static final int TILE_SIZE = 40;

    private Tile[][] tiles;
    private ArrayList<Point> path;

    private String[] mapRows;

    public GameMap(MapType type)
    {
        this(MapData.getMap(type));
    }

    public GameMap(String[] mapRows)
    {
        this.mapRows = mapRows;
        tiles = new Tile[mapRows.length][mapRows[0].length()];
        path = new ArrayList<>();
        createMap();
    }

    private void createMap()
    {
        for (int r = 0; r < tiles.length; r++)
        {
            for (int c = 0; c < tiles[r].length; c++)
            {
                tiles[r][c] = new Tile(r, c, TileType.GROUND);
            }
        }

        parseTiles();
        path.clear();
        buildPath();
    }

    private void parseTiles()
    {
        for (int r = 0; r < mapRows.length; r++)
        {
            String row = mapRows[r];
            for (int c = 0; c < row.length(); c++)
            {
                tiles[r][c] = new Tile(r, c, TileType.fromChar(row.charAt(c)));
            }
        }
    }

    private void buildPath()
    {
        Point current = findTile(TileType.START);
        if (current == null)
            throw new IllegalStateException("Map needs exactly one S tile");

        Point previous = null;

        while (current != null)
        {
            path.add(toCenter(current));

            if (tiles[current.y][current.x].getType() == TileType.END)
                break;

            Point next = findNextPathStep(current, previous);
            previous = current;
            current = next;
        }
    }

    private Point findTile(TileType type)
    {
        for (int r = 0; r < tiles.length; r++)
        {
            for (int c = 0; c < tiles[r].length; c++)
            {
                if (tiles[r][c].getType() == type)
                    return new Point(c, r);
            }
        }

        return null;
    }

    private Point findNextPathStep(Point current, Point previous)
    {
        int[][] offsets = new int[][]
        {
            { 1, 0 },
            { -1, 0 },
            { 0, 1 },
            { 0, -1 }
        };

        for (int i = 0; i < offsets.length; i++)
        {
            int nextCol = current.x + offsets[i][0];
            int nextRow = current.y + offsets[i][1];

            if (nextRow < 0 || nextRow >= tiles.length || nextCol < 0 || nextCol >= tiles[0].length)
                continue;

            if (previous != null && nextCol == previous.x && nextRow == previous.y)
                continue;

            TileType type = tiles[nextRow][nextCol].getType();
            if (type.isPath())
                return new Point(nextCol, nextRow);
        }

        return null;
    }

    private Point toCenter(Point tile)
    {
        return new Point(tile.x * TILE_SIZE + TILE_SIZE / 2, tile.y * TILE_SIZE + TILE_SIZE / 2);
    }

    public ArrayList<Point> getPath()
    {
        return path;
    }

    public boolean canBuild(int x, int y)
    {
        int col = x / TILE_SIZE;
        int row = y / TILE_SIZE;

        if (row < 0 || row >= tiles.length || col < 0 || col >= tiles[0].length)
            return false;

        return tiles[row][col].isBuildable();
    }

    public boolean isPath(int x, int y)
    {
        int col = x / TILE_SIZE;
        int row = y / TILE_SIZE;

        if (row < 0 || row >= tiles.length || col < 0 || col >= tiles[0].length)
            return true;

        return tiles[row][col].isPath();
    }

    public void draw(Graphics g)
    {
        for (int r = 0; r < tiles.length; r++)
        {
            for (int c = 0; c < tiles[r].length; c++)
            {
                Tile tile = tiles[r][c];

                g.setColor(tile.getType().getColor());

                g.fillRect(c * TILE_SIZE, r * TILE_SIZE, TILE_SIZE, TILE_SIZE);

                g.setColor(Color.BLACK);
                g.drawRect(c * TILE_SIZE, r * TILE_SIZE, TILE_SIZE, TILE_SIZE);
            }
        }
    }
}