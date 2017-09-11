package passap.test;

import java.awt.EventQueue;

public class Passap {

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AppLogin window = new AppLogin();
					window.showFrame();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
