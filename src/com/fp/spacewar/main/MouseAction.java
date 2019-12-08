package com.fp.spacewar.main;


import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
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
		if(mouseX>=Game.w/2-100 && mouseX<Game.w+100) {
			System.out.println("Press");
			if(mouseY>=300&&mouseY<=370) {
				System.out.println("Play");
				Game.currentGameState= Game.GameState.IN_PLAY;
			}
			else if(mouseY>=400&&mouseY<=470) {
				System.out.println("Hall of Fame");
				Game.currentGameState= Game.GameState.IN_HOF;
			}
			else if(mouseY>=500&&mouseY<=570) {
				System.out.println("Quit");
				System.exit(1);
			}
		}
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	
	
	

}
