package com.fp.spacewar.main;


import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import com.fp.spacewar.main.Game.GameState;
public class MouseAction implements MouseListener{
	Game game;
	public MouseAction(Game game) {
		this.game=game;
	}
	public void mouseClicked(MouseEvent me) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent me) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent me) {
		// TODO Auto-generated method stub
		
	
	}

	@Override
	public void mousePressed(MouseEvent me) {
		// TODO Auto-generated method stub
		int mouseX=me.getX();
		int mouseY=me.getY();
		if(Game.currentGameState==GameState.IN_MENU) {
			if(mouseX>=Game.w/2-100 && mouseX<Game.w+100) {
				System.out.println("Press");
				if(mouseY>=200&&mouseY<=270) {
					System.out.println("Play");
					Game.currentGameState= Game.GameState.IN_PLAY;
					game.getPewaktu().timerReset();
				}
				else if(mouseY>=300&&mouseY<=370) {
					System.out.println("Hall of Fame");
					Game.currentGameState= Game.GameState.IN_HOF;
				}
				else if(mouseY>=400&&mouseY<=470) {
					System.out.println("Help");
					Game.currentGameState= Game.GameState.IN_HELP;
				}else if(mouseY>=500&&mouseY<=570) {
					System.out.println("Quit");
					System.exit(1);
				}
			}
		}
		else if(Game.currentGameState == GameState.IN_HOF) {
			if(mouseX>=20 && mouseX<=80) {
				if(mouseY>=50&&mouseY<=80) {
					Game.currentGameState= GameState.IN_MENU;
					System.out.println("sini gan");
				}
//				g2d.draw(new Rectangle(20,50,60,30));
//				g.drawString("Back", 25, 70);
			}
			
		}
		else if(Game.currentGameState == GameState.IN_HELP) {
			if(mouseX>=20 && mouseX<=80) {
				if(mouseY>=50&&mouseY<=80) {
					Game.currentGameState= GameState.IN_MENU;
					System.out.println("sini gan");
				}
//				g2d.draw(new Rectangle(20,50,60,30));
//				g.drawString("Back", 25, 70);
			}
			
		}
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	
	
	

}
