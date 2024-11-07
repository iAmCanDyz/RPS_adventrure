package game;

import java.awt.event.WindowEvent;
import java.awt.event.WindowFocusListener;

import javax.swing.JFrame;

public class gameDisplay {

	public gameDisplay(gamePanel gamepanel) {
		JFrame f = new JFrame("RPS Adventure");
		
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setLocationRelativeTo(null);
		f.add(gamepanel);
		
		f.setResizable(false);
		f.pack();
		
		f.setVisible(true);
		f.addWindowFocusListener(new WindowFocusListener() {

			@Override
			public void windowGainedFocus(WindowEvent e) {
				
			}

			@Override
			public void windowLostFocus(WindowEvent e) {
				gamepanel.getGame().windowFocusLost();
			}
			
		});
		
	}

}
