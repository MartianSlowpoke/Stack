package view;

import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

public class MainFrame {

	private JFrame frame;

	private JMenuBar menuBar;
	private JMenu mnFile;
	private JMenuItem mntmNew;
	private JMenuItem mntmOpen;
	private JMenuItem mntmSave;
	private JMenuItem mntmSaveAs;
	private JMenuItem mntmSwap;
	private JMenuItem equals;
	private JMenuItem compare;

	/*
	 * creation panel
	 */
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JPanel panel;
	private JPanel panel_1;
	private JPanel panel_2;
	private JLabel lblTypeOfStack;
	private JRadioButton rdbtnNewRadioButton;
	private JRadioButton rdbtnNewRadioButton_1;
	private JButton btnCreate;

	public MainFrame() {
		frame = new JFrame();
		frame.setBounds(100, 100, 700, 630);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		initMenu();
		initCreationPanel();
		frame.getContentPane().add(panel);
	}

	private void initMenu() {
		menuBar = new JMenuBar();
		menuBar.setBounds(0, 0, 700, 21);
		frame.setJMenuBar(menuBar);
		mnFile = new JMenu("file");
		menuBar.add(mnFile);
		mntmNew = new JMenuItem("new");
		mnFile.add(mntmNew);
		mntmOpen = new JMenuItem("open");
		mnFile.add(mntmOpen);
		mntmSave = new JMenuItem("save");
		mnFile.add(mntmSave);
		mntmSaveAs = new JMenuItem("save as");
		mnFile.add(mntmSaveAs);
		mntmSwap = new JMenuItem("swap");
		mnFile.add(mntmSwap);
		equals = new JMenuItem("equals");
		mnFile.add(equals);
		compare = new JMenuItem("compare");
		mnFile.add(compare);
	}

	public void addMainControllerAsListener(ActionListener controller) {
		mntmOpen.setActionCommand("open");
		mntmOpen.addActionListener(controller);
		mntmNew.addActionListener(controller);
		mntmNew.setActionCommand("new");
		btnCreate.setActionCommand("create button");
		btnCreate.addActionListener(controller);
	}

	public void addMenuListener(ActionListener controller) {
		mntmSwap.setActionCommand("swap");
		mntmSaveAs.setActionCommand("save as");
		mntmSwap.setActionCommand("swap");
		equals.setActionCommand("equals");
		compare.setActionCommand("compare");
		mntmSwap.addActionListener(controller);
		mntmSave.addActionListener(controller);
		mntmSaveAs.addActionListener(controller);
		equals.addActionListener(controller);
		compare.addActionListener(controller);
	}

	public void removeMenuListener(ActionListener old_controller) {
		mntmSave.removeActionListener(old_controller);
		mntmSaveAs.removeActionListener(old_controller);
		mntmSwap.removeActionListener(old_controller);
		equals.removeActionListener(old_controller);
		compare.removeActionListener(old_controller);
	}

	private void initCreationPanel() {
		panel = new JPanel();
		panel.setBounds(12, 30, 676, 521);
		panel.setLayout(null);

		panel_1 = new JPanel();
		panel_1.setBounds(0, 0, 222, 521);
		panel.add(panel_1);
		panel_1.setLayout(null);

		lblTypeOfStack = new JLabel("type of stack");
		lblTypeOfStack.setBounds(12, 12, 198, 15);
		panel_1.add(lblTypeOfStack);

		rdbtnNewRadioButton = new JRadioButton("command");
		buttonGroup.add(rdbtnNewRadioButton);
		rdbtnNewRadioButton.setSelected(true);
		rdbtnNewRadioButton.setBounds(12, 45, 149, 23);
		panel_1.add(rdbtnNewRadioButton);

		rdbtnNewRadioButton_1 = new JRadioButton("student");
		buttonGroup.add(rdbtnNewRadioButton_1);
		rdbtnNewRadioButton_1.setBounds(12, 72, 149, 23);
		panel_1.add(rdbtnNewRadioButton_1);

		btnCreate = new JButton("create");
		btnCreate.setBounds(12, 110, 117, 25);
		panel_1.add(btnCreate);

		panel_2 = new JPanel();
		panel_2.setBounds(234, 0, 442, 521);
		panel.add(panel_2);
	}

	public void showCreation() {
		frame.getContentPane().removeAll();
		frame.getContentPane().repaint();
		frame.getContentPane().add(panel);
	}

	public void setStackView(StackView<?> view) {
		JPanel panel = view.getView();
		frame.getContentPane().removeAll();
		frame.getContentPane().repaint();
		frame.getContentPane().add(panel);
	}

	public String getInputType() {
		if (rdbtnNewRadioButton.isSelected())
			return "command";
		return "student";
	}

	public JFrame getFrame() {
		return frame;
	}
}
