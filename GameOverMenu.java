import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

public class GameOverMenu {
	
	public Rectangle menuButton = new Rectangle(Gamepanel.HEIGHT/2-75, 150, 150, 50);
	public Rectangle restartButton = new Rectangle(Gamepanel.HEIGHT/2-75, 230, 150, 50);
	
	public void render(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		Font fnt0 = new Font("arial", Font.BOLD, 50);
		g.setFont(fnt0);
		g.setColor(Color.WHITE);
		g.drawString("Game Over ", Gamepanel.HEIGHT/2-125, 100);
		g2d.draw(menuButton);
		Font fnt1 = new Font("arial", Font.BOLD, 30);
		g.setFont(fnt1);
		g.drawString("Menu", menuButton.x + 40, menuButton.y + 35);
		g2d.draw(restartButton);
		g.drawString("Restart", restartButton.x + 20, restartButton.y + 35);
	}
}
