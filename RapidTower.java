import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

public class RapidTower extends Tower
{
    private int warmupTimer   = 0;
    private boolean warmedUp  = false;

    public RapidTower(int x, int y)
    {
        super(x, y);
        range    = 90;
        damage   = 100;
        fireRate = 8;
    }

    public void update(ArrayList<Enemy> enemies, ArrayList<Bullet> bullets)
    {
        if (cooldown > 0) { cooldown--; return; }

        Enemy target = findTarget(enemies);

        if (target == null)
        {
            if (level >= 5) warmedUp = false;
            return;
        }

        // Level 5: aufwärm fase der minigunnnn
        if (level >= 5 && !warmedUp)
        {
            warmupTimer++;
            if (warmupTimer >= 60) { warmedUp = true; warmupTimer = 0; }
            return;
        }

        shoot(target, bullets);
        totalDamageDealt += damage;
        cooldown = level >= 5 ? 3 : fireRate;
    }

    private Enemy findTarget(ArrayList<Enemy> enemies)
    {
        for (Enemy e : enemies)
        {
            if (e.isDead()) continue;
            double dx = e.getX() - x;
            double dy = e.getY() - y;
            if (Math.sqrt(dx * dx + dy * dy) <= range) return e;
        }
        return null;
    }

    private void shoot(Enemy target, ArrayList<Bullet> bullets)
    {
        if (level >= 4)
        {
            // Doppelschuss!!!
            bullets.add(new PoisonBullet(x - 3, y, target, damage, level >= 3));
            bullets.add(new PoisonBullet(x + 3, y, target, damage, level >= 3));
        }
        else
        {
            bullets.add(new PoisonBullet(x, y, target, damage, level >= 3));
        }
    }

    public void upgrade()
    {
        if (level >= 5) return;
        level++;
        switch (level)
        {
            case 2: fireRate = 5; break;
            case 3: damage += 3; break;
            case 4: damage += 5; break;
            case 5: damage += 8; break;
        }
    }

    public int getUpgradeCost()
    {
        switch (level)
        {
            case 1: return 100;
            case 2: return 250;
            case 3: return 600;
            case 4: return 1500;
            default: return Integer.MAX_VALUE; // Level 5  is max 
            //selber bug wie bei basic turm
       }
    }

    public String getUpgradeDescription()
    {
        switch (level)
        {
            case 1: return "Feuerrate: " + formatSeconds(8) + " -> " + formatSeconds(5);
            case 2: return "Damage: 8 -> 11";
            case 3: return "Damage: 11 -> 16";
            case 4: return "Damage: 16 -> 24";
        }
        return "Max Level";
    }

    public String getUpgradePreview()
    {
        return getUpgradeDescription();
    }

    private String formatSeconds(int frames)
    {
        double seconds = frames / 60.0;
        return (Math.round(seconds * 10.0) / 10.0) + "s";
    }

    public void draw(Graphics g)
    {
        Color c = level >= 5 ? new Color(0, 200, 100)
                : level >= 3 ? new Color(0, 160, 80)
                : Color.CYAN;

        g.setColor(c);
        g.fillRect(x - 15, y - 15, 30, 30);
        g.setColor(Color.BLACK);
        g.drawString("" + level, x - 3, y + 5);

        // indicator von aufwärmfase
        if (level >= 5 && !warmedUp && warmupTimer > 0)
        {
            g.setColor(Color.RED);
            g.fillRect(x - 15, y + 17, (int)(30.0 * warmupTimer / 60), 4);
        }
    }
}