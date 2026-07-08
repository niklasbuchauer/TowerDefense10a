import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;

public class WatcherBoss extends BossEnemy
{
    public WatcherBoss(ArrayList<Point> path, int wave)
    {
        super(path, wave);
        health    = 800 + wave * 80;
        maxHealth = health;
        speed     = 0.6;
        baseSpeed = 0.6;
        reward    = 100 + wave * 10;
    }

    public Color getColor() { return new Color(60, 60, 60); }

    
     public void draw(Graphics g)
    {
        super.draw(g);

        int size = 32;
        int cx = (int)x;
        int cy = (int)y;
        int half = size / 2;

    // Weißer Rahmen
        g.setColor(Color.WHITE);
        g.fillRect(cx - half, cy - half, size, size);

    // Rosa Kreuz (hinter dem Auge)
        g.setColor(new Color(255, 190, 210));
        g.fillRect(cx - 3, cy - half, 6, size);
        g.fillRect(cx - half, cy - 3, size, 6);

    // Blauer Rahmen
        g.setColor(new Color(45, 120, 255));
        g.fillRect(cx - half + 4, cy - half + 4, size - 8, 4);              // oben
        g.fillRect(cx - half + 4, cy + half - 8, size - 8, 4);              // unten
        g.fillRect(cx - half + 4, cy - half + 4, 4, size - 8);              // links
        g.fillRect(cx + half - 8, cy - half + 4, 4, size - 8);              // rechts

    // Schwarzes Auge
        g.setColor(Color.BLACK);
        g.fillRect(cx - half + 8, cy - half + 8, size - 16, size - 16);

    // Weißer Lichtreflex
        g.setColor(Color.WHITE);
        g.fillRect(cx + half - 12, cy - half + 8, 4, 4);
    }
}