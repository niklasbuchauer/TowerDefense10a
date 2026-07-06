import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

public class Hud
{
    private static final Font LABEL_FONT = new Font("Dialog", Font.PLAIN, 13);
    private static final Font VALUE_FONT = new Font("Dialog", Font.BOLD, 13);
    private static final Color PANEL_BG = new Color(245, 245, 245, 235);
    private static final Color PANEL_BORDER = new Color(190, 190, 190);
    private static final Color TEXT = new Color(25, 25, 25);
    private static final Color ACCENT = new Color(50, 110, 170);

    public void draw(Graphics g, Player player, WaveManager waveManager)
    {
        draw(g, player, waveManager, 0);
    }
    
    public void draw(Graphics g, Player player, WaveManager waveManager, int selected)
    {
        int panelX = 10;
        int panelY = 10;
        int panelW = 780;
        int panelH = 72;

        g.setColor(PANEL_BG);
        g.fillRect(panelX, panelY, panelW, panelH);
        g.setColor(PANEL_BORDER);
        g.drawRect(panelX, panelY, panelW, panelH);

        g.setFont(LABEL_FONT);
        g.setColor(TEXT);
        drawStat(g, "Geld", String.valueOf(player.getMoney()), panelX + 16, panelY + 24);
        drawStat(g, "Leben", String.valueOf(player.getLives()), panelX + 16, panelY + 46);
        drawStat(g, "Welle", String.valueOf(waveManager.getWave()), panelX + 120, panelY + 24);

        int legendX = 260;
        int legendY = panelY + 24;
        drawTowerLine(g, "1", "Basic", "60", selected == 1, legendX, legendY);
        drawTowerLine(g, "2", "Sniper", "100", selected == 2, legendX + 135, legendY);
        drawTowerLine(g, "3", "Rapid", "80", selected == 3, legendX, legendY + 22);
        drawTowerLine(g, "4", "Freeze", "90", selected == 4, legendX + 135, legendY + 22);
    }

    private void drawStat(Graphics g, String label, String value, int x, int y)
    {
        g.setFont(LABEL_FONT);
        g.setColor(TEXT);
        g.drawString(label, x, y);

        g.setFont(VALUE_FONT);
        g.setColor(ACCENT);
        g.drawString(value, x + 52, y);
    }

    private void drawTowerLine(Graphics g, String key, String name, String cost, boolean selected, int x, int y)
    {
        g.setFont(VALUE_FONT);
        g.setColor(selected ? ACCENT : TEXT);
        g.drawString(key + "  " + name + "  " + cost + "$", x, y);
    }
}