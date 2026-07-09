import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;

public class Enemy
{
    protected double x;
    protected double y;

    protected double speed;
    protected double baseSpeed;
    protected int health;
    protected int maxHealth;
    protected int reward;

    protected int currentPoint;

    private int freezeTimer;
    //start werte auf null oder aus für default
    private PoisonEffect poison = null; 
    
    protected boolean stealth = false;
    private boolean wasFrozen = false;
    
    public boolean isStealth() { return stealth; }

    public Enemy(ArrayList<Point> path, int health, double speed, int reward)
    {
        //lol das haben wir sogar gelernt
        this.health    = health;
        this.maxHealth = health;
        this.speed     = speed;
        this.baseSpeed = speed;
        this.reward    = reward;
        this.freezeTimer = 0;

        x = path.get(0).x;
        y = path.get(0).y;

        currentPoint = 1;
    }

    public void applyPoison(int damage, int ticks, int tickInterval)
    {
        poison = new PoisonEffect(damage, ticks, tickInterval);
    }

    public boolean isPoisoned() { return poison != null && !poison.isFinished(); }
    
    public void update(ArrayList<Point> path)
    {
        boolean frozenLastFrame = wasFrozen;
        wasFrozen = isFrozen();
        
        if (currentPoint >= path.size())
            return;

        // Poison damage ticks
        if (poison != null)
        {
            int dmg = poison.update();
            if (dmg > 0) takeDamage(dmg);
            if (poison.isFinished()) poison = null;
        }
            
        // Freeze-Effekt: Speed verlangsamen
        if (freezeTimer > 0)
        {
            freezeTimer--;
            speed = baseSpeed * 0.35;
        }
        else
        {
            speed = baseSpeed;
        }

        Point target = path.get(currentPoint);

        double dx = target.x - x;
        double dy = target.y - y;
        double distance = Math.sqrt(dx * dx + dy * dy);

        if (distance < speed)
        {
            x = target.x;
            y = target.y;
            currentPoint++;
        }
        else
        {
            x += speed * dx / distance;
            y += speed * dy / distance;
        }
    }
    //ganz viele variablen, selbst beschreibent                     
    public void applyFreeze(int durationFrames)
    {
        freezeTimer = durationFrames;
    }

    public boolean isFrozen()
    {
        return freezeTimer > 0;
    }

    public boolean wasJustUnfrozen()
    {
        return wasFrozen && !isFrozen();
    }  
    
    public void resetUnfrozenFlag()
    {
        wasFrozen = false;
    }
    
    public boolean reachedEnd(ArrayList<Point> path)
    {
        return currentPoint >= path.size();
    }

    public void takeDamage(int damage)
    {
        health -= damage;
    }

    public void heal(int amount)
    {
        health = Math.min(health + amount, maxHealth);
    }
    
    public boolean isDead()
    {
        return health <= 0;
    }

    public int getReward()  { return reward; }
    public double getX()    { return x; }
    public double getY()    { return y; }

    public void setReward(int r)
    {
        reward = r;
    }

    public Color getColor() { return Color.RED; }

    public void draw(Graphics g)
    {
    // Gegner body: blau wenn eingefriert, grün wen vergiftet
    if (isFrozen())
        g.setColor(new Color(100, 180, 255));
    else if (isPoisoned())
        g.setColor(new Color(80, 200, 80));
    else
        g.setColor(getColor());

        g.fillOval((int)x - 12, (int)y - 12, 24, 24);

        // Health bar      OMG 20 herzen, das ja wie in mc
        g.setColor(Color.BLACK);
        g.drawRect((int)x - 15, (int)y - 22, 30, 5);
        g.setColor(Color.GREEN);
        int width = (int)(30.0 * health / maxHealth);
        g.fillRect((int)x - 15, (int)y - 22, width, 5);
    }
}
