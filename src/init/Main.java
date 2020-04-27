package init;

import java.awt.EventQueue;
import java.lang.reflect.InvocationTargetException;

import controller.MainController;
import view.MainFrame;

public class Main {

	public static void main(String[] args) throws InvocationTargetException, InterruptedException {
		EventQueue.invokeAndWait(new Runnable() {
			@Override
			public void run() {
				MainFrame main = new MainFrame();
				MainController controller = new MainController(main);
				main.addMainControllerAsListener(controller);
				main.getFrame().setVisible(true);
			}
		});
	}

}
