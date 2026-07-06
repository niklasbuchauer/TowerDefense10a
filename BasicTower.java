import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

public class BasicTower extends Tower
{
    private ArrayList<Enemy> enemyRef;

    public BasicTower(int x, int y)
    {
        super(x, y);
        range    = 120;
        damage   = 18;
        fireRate = 30;
    }

    public void setEnemyRef(ArrayList<Enemy> enemies) { this.enemyRef = enemies; }

    public void update(ArrayList<Enemy> enemies, ArrayList<Bullet> bullets)
    {
        this.enemyRef = enemies;
        super.update(enemies, bullets);
    }

    protected void shoot(Enemy target, ArrayList<Bullet> bullets)
    {
        boolean elite = level >= 5;

        if (level >= 4)
        {
            // Explosiv + Durchschuss
            int splash = elite ? 80 : 50;
            bullets.add(new ExplosionBullet(x, y, target, damage, enemyRef, splash, elite));
        }
        else if (level >= 3)
        {
            // Piercing shot: hits 2-3 enemies
            int pierce = elite ? 3 : 2;
            bullets.add(new PierceBullet(x, y, target, damage, enemyRef, pierce, false));
        }
        else
        {
            bullets.add(new Bullet(x, y, target, damage));
        }
    }

    public void upgrade()
    {
        if (level >= 5) return;
        level++;
        switch (level)
        {
            case 2: fireRate = (int)(fireRate * 0.75); break;
            case 3: damage += 5; break;
            case 4: damage += 10; break;
            case 5: damage += 20; break;
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
            default: return Integer.MAX_VALUE; // Level 5 cannot be upgraded
        }
    }

    public String getUpgradeDescription()
    {
        switch (level)
        {
            case 1: return "Feuerrate: " + formatSeconds(30) + " -> " + formatSeconds(22);
            case 2: return "Damage: 18 -> 23";
            case 3: return "Damage: 23 -> 33";
            case 4: return "Damage: 33 -> 53";
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
        Color c = level >= 5 ? new Color(255, 80, 0)
                : level >= 4 ? new Color(220, 50, 50)
                : level >= 3 ? new Color(180, 60, 60)
                : level >= 2 ? new Color(100, 100, 200)
                : Color.BLUE;

        g.setColor(c);
        g.fillRect(x - 15, y - 15, 30, 30);
        g.setColor(Color.WHITE);
        g.drawString("" + level, x - 3, y + 5);
    }
}