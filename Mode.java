import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

public class Mode {
	
	public Rectangle backButton = new Rectangle(10, 10, 80, 30);
	public Rectangle startButton = new Rectangle(390, 10, 80, 30);

	//Speed Rects for Button
	public Rectangle slowButton = new Rectangle(150, 120, 80, 50);
	public Rectangle mediumButton = new Rectangle(250, 120, 80, 50);
	public Rectangle fastButton = new Rectangle(350, 120, 80, 50);
	
	//Back button
	
	//Wall Button Rects
	public Rectangle wallsOnButton = new Rectangle(150, 210, 120, 50);
	public Rectangle wallsOffButton = new Rectangle(300, 210, 120, 50);
	//public Rectangle quitButton = new Rectangle(Gamepanel.WIDTH/2 - 50, 350, 100, 50);
	
	//Self Collision Buttons
	public Rectangle deathButton = new Rectangle(10, 320, 150, 50);
	public Rectangle passThroughButton = new Rectangle(170, 320, 150, 50);
	public Rectangle cutTailButton = new Rectangle(330, 320, 150, 50);
	
	//Party Modes
	public Rectangle painterButton = new Rectangle(120, 400, 110, 50);
	public Rectangle crazyButton = new Rectangle(250, 400, 110, 50);
	public Rectangle noneButton = new Rectangle(380, 400, 110, 50);
	//public Mode(int x, int y, int z) {
	//	Color button = Color.WHITE;
	//}
	
	public void render(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		
		Font fnt0 = new Font("arial", Font.BOLD, 50);
		g.setFont(fnt0);
		g.setColor(Color.WHITE);
		g.drawString("Mode", Gamepanel.HEIGHT/2-75, 100);
		
		Font fnt1 = new Font("arial", Font.BOLD, 30);
		Font fnt2 = new Font("arial", Font.BOLD, 20);
		g.setFont(fnt1);
		
		// Speed Buttons
		g.drawString("Speed: ", 20, 150);
		g.drawString("Slow", slowButton.x + 7, slowButton.y + 35);
		g2d.draw(slowButton);
		g.drawString("Med", mediumButton.x + 10, mediumButton.y + 35);
		g2d.draw(mediumButton);
		g.drawString("Fast", fastButton.x + 10, fastButton.y + 35);
		g2d.draw(fastButton);
		
		//Wall Buttons
		g.drawString("Walls: ", 20, 240);
		g.drawString("On", wallsOnButton.x + 40, wallsOnButton.y + 35);
		g2d.draw(wallsOnButton);
		g.drawString("Off", wallsOffButton.x + 40, wallsOffButton.y + 35);
		g2d.draw(wallsOffButton);
		
		//Self Collision Button
		g.drawString("Self Collision: ", Gamepanel.WIDTH/2 - 100, 300);
		g.drawString("Death", deathButton.x + 20, deathButton.y + 35);
		g2d.draw(deathButton);
		g.drawString("Cut Tail", cutTailButton.x + 15, cutTailButton.y + 35);
		g2d.draw(cutTailButton);
		g.setFont(fnt2);
		g.drawString("Pass Through", passThroughButton.x + 10, passThroughButton.y + 35);
		g2d.draw(passThroughButton);
		
		
		//Start and Back buttons
		g.drawString("Back", backButton.x + 15, backButton.y + 20);
		g2d.draw(backButton);
		g.drawString("Start", startButton.x + 15, startButton.y + 20);
		g2d.draw(startButton);

		//Party Buttons
		g.setFont(fnt1);
		g.drawString("Party: ", 20, 435);
		g.drawString("Crazy", crazyButton.x + 10, crazyButton.y + 35);
		g2d.draw(crazyButton);
		g.drawString("Painter", painterButton.x + 1, painterButton.y + 35);
		g2d.draw(painterButton);
		g.drawString("None", noneButton.x + 12, noneButton.y + 35);
		g2d.draw(noneButton);
		
	}

}