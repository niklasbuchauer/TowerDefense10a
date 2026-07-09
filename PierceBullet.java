import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

public class PierceBullet extends Bullet
{
    private ArrayList<Enemy> allEnemies;
    private int pierceLeft;
    private boolean elite;

    // richtung is auf ursprung target gelocked 
    private double dirX;
    private double dirY;

    private ArrayList<Enemy> alreadyHit = new ArrayList<>();

    public PierceBullet(double x, double y, Enemy target, int damage,
                        ArrayList<Enemy> allEnemies, int pierceCount, boolean elite)
    {
        super(x, y, target, damage);
        this.allEnemies = allEnemies;
        this.pierceLeft = pierceCount;
        this.elite      = elite;

        // rechnet aus welche richtung und locked dann zum ersten target die richtung
        double dx   = target.getX() - x;
        double dy   = target.getY() - y;
        double dist = Math.sqrt(dx * dx + dy * dy);
        this.dirX = dx / dist;
        this.dirY = dy / dist;
    }

    public void update()
    {
        if (!active) return;

        // bewegt sich in selbe richtung
        x += speed * dirX;
        y += speed * dirY;

        // geht aus wenn aus spielfeld is
        if (x < 0 || x > 800 || y < 0 || y > 600)
        {
            active = false;
            return;
        }

        // checkt collesion bei allen gegner ein mal
        for (Enemy e : allEnemies)
        {
            if (e.isDead()) continue;
            if (alreadyHit.contains(e)) continue;

            double dx   = e.getX() - x;
            double dy   = e.getY() - y;
            double dist = Math.sqrt(dx * dx + dy * dy);

            if (dist < 12) // hit radius
            {
                e.takeDamage(elite ? damage * 3 : damage);
                alreadyHit.add(e);
                pierceLeft--;

                if (pierceLeft <= 0)
                {
                    active = false;
                    return;
                }
            }
        }
    }

    // Movement und collisions sind fully in update() gehandelt           Danke man!
    protected void onHit(Game game) {}

    public void draw(Graphics g)
    {
        g.setColor(elite ? new Color(255, 50, 50) : Color.WHITE);
        g.fillOval((int)x - 4, (int)y - 4, 8, 8);
    }
}