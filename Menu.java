import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

public class Menu {
	
	public Rectangle playButton = new Rectangle(Gamepanel.WIDTH/2 - 50, 150, 100, 50);
	public Rectangle modeButton = new Rectangle(Gamepanel.WIDTH/2 - 50, 250, 100, 50);
	public Rectangle quitButton = new Rectangle(Gamepanel.WIDTH/2 - 50, 350, 100, 50);
	
	public void render(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		
		Font fnt0 = new Font("arial", Font.BOLD, 50);
		g.setFont(fnt0);
		g.setColor(Color.WHITE);
		g.drawString("Snake", Gamepanel.HEIGHT/2-75, 100);
		
		Font fnt1 = new Font("arial", Font.BOLD, 30);
		g.setFont(fnt1);
		g.drawString("Play", playButton.x + 15, playButton.y + 35);
		g2d.draw(playButton);
		g.drawString("Mode", modeButton.x + 15, modeButton.y + 35);
		g2d.draw(modeButton);
		g.drawString("Quit", quitButton.x + 19, quitButton.y + 35);
		g2d.draw(quitButton);
	}

}
