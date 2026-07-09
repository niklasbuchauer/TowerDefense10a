import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

public class FloatingText {
    private double x, y;
    private String text;
    private Color color;
    private int alpha = 255;      // Sichtbarkeit (255 = komplett sichtbar, 0 = transparent)
    private int lifetime = 45;    // Lesbenszeit in frames (wie lange die da bleiben)
    private double speedY = -0.8; // Verticale bewegungs geschwindigkeit

    public FloatingText(double x, double y, String text, Color color) {
        this.x = x;
        this.y = y - 15; 
        this.text = text;
        this.color = color;
    }

    public void update() {
        y += speedY;
        lifetime--;
        
        if (lifetime < 20) {
            alpha = Math.max(0, alpha - 13);
        }
    }

    public void draw(Graphics g) {
        if (alpha <= 0) return;
        
        // die alte font wird gespeichert
        Font originalFont = g.getFont();
        
        // text anti aliasing wird an gemacht wenn möglich
        if (g instanceof Graphics2D) {
            Graphics2D g2d = (Graphics2D) g;
            g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        }
        
        // gute, lesbare font (schriftart)
        g.setFont(new Font("Arial", Font.BOLD, 17)); 
        
        // geile sschatten mäßige font
        g.setColor(new Color(0, 0, 0, alpha));
        g.drawString(text, (int)x + 1, (int)y + 1);
        g.drawString(text, (int)x - 1, (int)y - 1);
        g.drawString(text, (int)x + 1, (int)y - 1);
        g.drawString(text, (int)x - 1, (int)y + 1);
        
        // main vordergrund text
        g.setColor(new Color(color.getRed(), color.getGreen(), color.getBlue(), alpha));
        g.drawString(text, (int)x, (int)y);
        
        // die originale font wird restored
        g.setFont(originalFont);
    }

    public boolean isDead() {
        return lifetime <= 0;
    }
}