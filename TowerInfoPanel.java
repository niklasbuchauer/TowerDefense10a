import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

public class TowerInfoPanel
{
    private Tower selectedTower;

    private static final int PX = 282;
    private static final int PY = 428;
    private static final int PW = 230;
    private static final int PH = 132;
    private static final Font TITLE_FONT = new Font("Dialog", Font.BOLD, 14);
    private static final Font BODY_FONT = new Font("Dialog", Font.PLAIN, 12);
    private static final Font BUTTON_FONT = new Font("Dialog", Font.BOLD, 12);
    private static final Color PANEL_BG = new Color(245, 245, 245, 235);
    private static final Color PANEL_BORDER = new Color(190, 190, 190);
    private static final Color TEXT = new Color(25, 25, 25);
    private static final Color ACCENT = new Color(50, 110, 170);
    private static final Color BUTTON_READY = new Color(76, 140, 76);
    private static final Color BUTTON_BLOCKED = new Color(130, 130, 130);

    public static final int BTN_X = PX + 10;
    public static final int BTN_Y = PY + 115;
    public static final int BTN_W = PW - 20;
    public static final int BTN_H = 24;

    public void select(Tower t)
    {
        selectedTower = t;
    }

    public void deselect()
    {
        selectedTower = null;
    }

    public Tower getSelected()
    {
        return selectedTower;
    }

    public boolean isVisible()
    {
        return selectedTower != null;
    }

    public boolean isUpgradeButtonClick(int x, int y)
    {
        return x >= BTN_X && x <= BTN_X + BTN_W
            && y >= BTN_Y && y <= BTN_Y + BTN_H;
    }

    public void draw(Graphics g, Player player)
    {
        if (selectedTower == null) return;

        g.setColor(PANEL_BG);
        g.fillRect(PX, PY, PW, PH);
        g.setColor(PANEL_BORDER);
        g.drawRect(PX, PY, PW, PH);

        g.setFont(TITLE_FONT);
        g.setColor(TEXT);
        g.drawString(selectedTower.getName() + "  Lv " + selectedTower.getLevel(), PX + 10, PY + 18);

        g.setFont(BODY_FONT);
        int tx = PX + 10;
        int ty = PY + 40;
        int lh = 16;

        g.drawString("Schaden  " + selectedTower.getDamage(), tx, ty);
        g.drawString("Reichweite  " + selectedTower.getRange(), tx, ty + lh);
        g.drawString("Feuerrate  " + getFireRateLabel(), tx, ty + lh * 2);

        g.drawString("Next  " + selectedTower.getUpgradePreview(), tx, ty + lh * 3);

        int cost = selectedTower.getUpgradeCost();
        g.setColor(ACCENT);
        g.drawString("Kosten  " + cost + "$", tx, ty + lh * 4);

        if (selectedTower.getLevel() >= 5)
        {
            g.setColor(BUTTON_BLOCKED);
            g.fillRect(BTN_X, BTN_Y, BTN_W, BTN_H);
            g.setColor(Color.WHITE);
            g.setFont(BUTTON_FONT);
            g.drawString("Max Level", BTN_X + 73, BTN_Y + 16);
        }
        else
        {
            boolean canAfford = player.getMoney() >= cost;
            g.setColor(canAfford ? BUTTON_READY : BUTTON_BLOCKED);
            g.fillRect(BTN_X, BTN_Y, BTN_W, BTN_H);
            g.setColor(Color.WHITE);
            g.setFont(BUTTON_FONT);
            String btnText = canAfford ? "Aufwerten  (" + cost + "$)" : "Zu wenig Gold";
            g.drawString(btnText, BTN_X + 42, BTN_Y + 16);
        }
    }

    private String getFireRateLabel()
    {
        int fr = selectedTower.getFireRate();
        if (fr <= 10)  return "Schnell  " + fr;
        if (fr <= 25)  return "Normal  " + fr;
        if (fr <= 50)  return "Langsam  " + fr;
        return "Sehr langsam  " + fr;
    }
}