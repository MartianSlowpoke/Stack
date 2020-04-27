package view;

import java.awt.EventQueue;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.BoxLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Component;
import javax.swing.Box;
import java.awt.Font;
import javax.swing.UIManager;

public class Test {

	private JFrame frame;
	private JPanel panel;
	private JPanel managment_panel;
	private JTextField student_id_field;
	private JTextField first_name_field;
	private JTextField last_name_field;
	private JTextField group_field;
	private JLabel student_id_lbl;
	private JButton push_btn;
	private JButton top_btn;
	private JButton pop_btn;
	private JButton size_btn;
	private JButton is_empty_btn;
	private JPanel workspace_panel;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Test window = new Test();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Test() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 700, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		panel = new JPanel();
		panel.setBounds(0, 0, 700, 542);
		frame.getContentPane().add(panel);
		panel.setLayout(null);

		managment_panel = new JPanel();
		managment_panel.setBounds(0, 0, 220, 542);
		panel.add(managment_panel);
		managment_panel.setLayout(null);

		student_id_lbl = new JLabel("student id");
		student_id_lbl.setFont(new Font("FreeMono", Font.BOLD, 16));
		student_id_lbl.setBounds(12, 12, 196, 15);
		managment_panel.add(student_id_lbl);

		student_id_field = new JTextField();
		student_id_field.setFont(new Font("FreeMono", Font.PLAIN, 16));
		student_id_field.setBounds(12, 39, 196, 26);
		managment_panel.add(student_id_field);
		student_id_field.setColumns(10);

		JLabel first_name_label = new JLabel("first name");
		first_name_label.setFont(new Font("FreeMono", Font.BOLD, 16));
		first_name_label.setBounds(12, 77, 196, 15);
		managment_panel.add(first_name_label);

		first_name_field = new JTextField();
		first_name_field.setFont(new Font("FreeMono", Font.PLAIN, 16));
		first_name_field.setBounds(12, 104, 196, 26);
		managment_panel.add(first_name_field);
		first_name_field.setColumns(10);

		JLabel last_name_label = new JLabel("last name");
		last_name_label.setFont(new Font("FreeMono", Font.BOLD, 16));
		last_name_label.setBounds(12, 142, 196, 15);
		managment_panel.add(last_name_label);

		last_name_field = new JTextField();
		last_name_field.setFont(new Font("FreeMono", Font.PLAIN, 16));
		last_name_field.setBounds(12, 169, 196, 26);
		managment_panel.add(last_name_field);
		last_name_field.setColumns(10);

		JLabel group_label = new JLabel("group");
		group_label.setFont(new Font("FreeMono", Font.BOLD, 16));
		group_label.setBounds(12, 207, 196, 15);
		managment_panel.add(group_label);

		group_field = new JTextField();
		group_field.setFont(new Font("FreeMono", Font.PLAIN, 16));
		group_field.setBounds(12, 234, 196, 26);
		managment_panel.add(group_field);
		group_field.setColumns(10);

		push_btn = new JButton("push");
		push_btn.setBackground(UIManager.getColor("Button.light"));
		push_btn.setFont(new Font("FreeMono", Font.PLAIN, 15));
		push_btn.setBounds(51, 357, 117, 25);
		managment_panel.add(push_btn);

		top_btn = new JButton("top");
		top_btn.setBackground(UIManager.getColor("Button.light"));
		top_btn.setFont(new Font("FreeMono", Font.PLAIN, 15));
		top_btn.setBounds(51, 394, 117, 25);
		managment_panel.add(top_btn);

		pop_btn = new JButton("pop");
		pop_btn.setBackground(UIManager.getColor("Button.light"));
		pop_btn.setFont(new Font("FreeMono", Font.PLAIN, 15));
		pop_btn.setBounds(51, 431, 117, 25);
		managment_panel.add(pop_btn);

		size_btn = new JButton("size");
		size_btn.setBackground(UIManager.getColor("Button.light"));
		size_btn.setFont(new Font("FreeMono", Font.PLAIN, 15));
		size_btn.setBounds(51, 468, 117, 25);
		managment_panel.add(size_btn);

		is_empty_btn = new JButton("is empty");
		is_empty_btn.setBackground(UIManager.getColor("Button.light"));
		is_empty_btn.setFont(new Font("FreeMono", Font.PLAIN, 15));
		is_empty_btn.setBounds(51, 505, 117, 25);
		managment_panel.add(is_empty_btn);

		JScrollPane workspace_scroll = new JScrollPane();
		workspace_scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		workspace_scroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		workspace_scroll.setBounds(232, 0, 468, 542);
		panel.add(workspace_scroll);

		workspace_panel = new JPanel();
		workspace_scroll.setViewportView(workspace_panel);
		workspace_panel.setLayout(new BoxLayout(workspace_panel, BoxLayout.Y_AXIS));
		
		JPanel panel_1 = new JPanel();
		panel_1.setPreferredSize(new Dimension(450, 225));
		panel_1.setMinimumSize(new Dimension(450, 225));
		panel_1.setMaximumSize(new Dimension(450, 225));
		panel_1.setBackground(new Color(245, 255, 250));
		workspace_panel.add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblStudentId = new JLabel("student id");
		lblStudentId.setFont(new Font("FreeMono", Font.BOLD, 16));
		lblStudentId.setBounds(12, 12, 135, 15);
		panel_1.add(lblStudentId);
		
		JLabel label = new JLabel("31232");
		label.setFont(new Font("FreeMono", Font.PLAIN, 16));
		label.setBounds(171, 12, 267, 15);
		panel_1.add(label);
		
		JLabel lblFirstName = new JLabel("first name");
		lblFirstName.setFont(new Font("FreeMono", Font.BOLD, 16));
		lblFirstName.setBounds(12, 39, 135, 15);
		panel_1.add(lblFirstName);
		
		JLabel lblSerhii = new JLabel("serhii");
		lblSerhii.setFont(new Font("FreeMono", Font.PLAIN, 16));
		lblSerhii.setBounds(171, 39, 267, 15);
		panel_1.add(lblSerhii);
		
		JLabel lblLastName = new JLabel("last name");
		lblLastName.setFont(new Font("FreeMono", Font.BOLD, 16));
		lblLastName.setBounds(12, 66, 135, 15);
		panel_1.add(lblLastName);
		
		JLabel lblOlegovich = new JLabel("olegovich");
		lblOlegovich.setFont(new Font("FreeMono", Font.PLAIN, 16));
		lblOlegovich.setBounds(171, 66, 267, 15);
		panel_1.add(lblOlegovich);
		
		JLabel lblGroup = new JLabel("group");
		lblGroup.setFont(new Font("FreeMono", Font.BOLD, 16));
		lblGroup.setBounds(12, 93, 135, 15);
		panel_1.add(lblGroup);
		
		JLabel lblKt = new JLabel("kt-1601");
		lblKt.setFont(new Font("FreeMono", Font.PLAIN, 16));
		lblKt.setBounds(171, 93, 267, 15);
		panel_1.add(lblKt);
		
		JLabel lblRegistration = new JLabel("registration");
		lblRegistration.setFont(new Font("FreeMono", Font.BOLD, 16));
		lblRegistration.setBounds(12, 120, 135, 15);
		panel_1.add(lblRegistration);
		
		JLabel label_1 = new JLabel("2412412424");
		label_1.setFont(new Font("FreeMono", Font.PLAIN, 16));
		label_1.setBounds(171, 120, 267, 15);
		panel_1.add(label_1);
		
		Component verticalStrut = Box.createVerticalStrut(20);
		workspace_panel.add(verticalStrut);

		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);
	}
}
