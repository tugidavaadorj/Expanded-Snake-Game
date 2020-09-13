import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MouseInput implements MouseListener{

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	public void mousePressed(MouseEvent e) {
		int mx =  e.getX();
		int my = e.getY();
		
		//Checking for Menu Buttons
		if (Gamepanel.State == Gamepanel.STATE.MENU) {
			if(mx >= Gamepanel.WIDTH/2 - 50 && mx <= Gamepanel.WIDTH/2 + 50) {
				if (my >= 150 && my <= 200) {
					//Play Button
					Gamepanel.State = Gamepanel.STATE.GAME;
				}
				else if (my >= 250 && my <= 300) {
					//Mode Button
					Gamepanel.State = Gamepanel.STATE.MODE;
				}
				else if (my >= 350 && my <= 400) {
					//Quit Button
					System.exit(1);
				}
			}
		}
		if (Gamepanel.State == Gamepanel.STATE.OVER) {
			if(mx >= 175 && my <= 325) {
				if (my >= 150 && my <= 200) {
					//Menu Button
					Gamepanel.State = Gamepanel.STATE.MENU;
				}
				else if (my >= 230 && my <= 280) {
					//Restart Button
					Gamepanel.State = Gamepanel.STATE.GAME;
				}
			}	
		}
		
		if (Gamepanel.State == Gamepanel.STATE.MODE) {
			
			//Checking for Speed Buttons
			if(my >= 120 && my <= 170) {
				if (mx >= 150 && mx <= 230) {
					//Slow Button
					Gamepanel.setSpeed(400000);
				}
				else if (mx >= 250 && mx <= 330) {
					//Medium Button
					Gamepanel.setSpeed(250000);
				}
				else if (mx >= 350 && mx <= 430) {
					//Fast Button
					Gamepanel.setSpeed(180000);
				}
			}
			
			//Checking for Wall Buttons
			if(my >= 210 && my <= 260) {
				if (mx >= 150 && mx <= 270) {
					//Walls On Button
					Gamepanel.Wallmode = Gamepanel.WALLMODE.WALLS;
				}
				else if (mx >= 300 && mx <= 420) {
					//Walls Off Button
					Gamepanel.Wallmode = Gamepanel.WALLMODE.NoWALLS;
				}
			}
			
			//Checking for Self Collision Buttons
			if(my >= 320 && my <= 370) {
				if (mx >= 10 && mx <= 160) {
					//Death Button
					Gamepanel.SelfCol = Gamepanel.SELFCOLL.DEATH;
				}
				else if (mx >= 170 && mx <= 320) {
					//pass Through Button
					Gamepanel.SelfCol = Gamepanel.SELFCOLL.PASS;
				}
				else if (mx >= 330 && mx <= 480) {
					//Cut Tail Button
					Gamepanel.SelfCol = Gamepanel.SELFCOLL.CUT;
				}
			}
			
			//Checking for Back and Start Buttons
			if(my >= 10 && my <= 40) {
				if (mx >= 10 && mx <= 90) {
					//Back Button
					Gamepanel.State = Gamepanel.STATE.MENU;
				}
				else if (mx >= 390 && mx <= 470) {
					//Start Button
					Gamepanel.State = Gamepanel.STATE.GAME;
				}
			}
			

			//public Rectangle painterButton = new Rectangle(120, 400, 110, 50);
			//public Rectangle crazyButton = new Rectangle(250, 400, 110, 50);
			//public Rectangle noneButton = new Rectangle(370, 400, 110, 50);
			
			//Checking for Party Buttons
			if(my >= 400 && my <= 450) {
				if (mx >= 120 && mx <= 230) {
					//Painter Button
					Gamepanel.PartyMode = Gamepanel.PARTYMODE.painter;
				}
				else if (mx >= 250 && mx <= 360) {
					//Crazy Button
					Gamepanel.PartyMode = Gamepanel.PARTYMODE.crazy;
				}
				else if (mx >= 380 && mx <= 490) {
					//None Button
					Gamepanel.PartyMode = Gamepanel.PARTYMODE.none;
				}
			}
			
			
		}
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

}
