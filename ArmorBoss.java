import java.awt.Color;
import java.awt.Point;
import java.util.ArrayList;

public class ArmorBoss extends BossEnemy
{
    public ArmorBoss(ArrayList<Point> path, int wave)
    {
        super(path, wave);
        health    = 1200 + wave * 100;
        maxHealth = health;
        speed     = 0.8;
        baseSpeed = 0.8;
        reward    = 150 + wave * 12;
    }

    public void takeDamage(int damage)
    {
        super.takeDamage(damage / 2); // 50% projectile damage weniger
    }

    public Color getColor() { return new Color(80, 60, 30); }
}