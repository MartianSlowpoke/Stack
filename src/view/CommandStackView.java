package view;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;

import bean.Command;
import collection.Stack;

public class CommandStackView implements StackView<Command> {

	private JPanel panel;

	private JPanel push_panel;
	private JTextField command_id_field;
	private JTextField type_field;
	private JLabel command_id_lbl;
	private JLabel type_lbl;
	private JLabel comment_lbl;
	private JScrollPane comment_area_scroll;
	private JTextArea comment_area;

	private JButton pop_btn;
	private JButton push_btn;
	private JButton top_btn;
	private JButton size_btn;
	private JButton is_empty_btn;

	private JPanel workspace_panel;
	private JScrollPane workspace_scroll;
	private JPanel command_panel;

	public CommandStackView() {
		panel = new JPanel();
		panel.setBackground(Color.DARK_GRAY);
		panel.setBounds(0, 12, 700, 551);
		panel.setLayout(null);
		initWorkspacePanel();
	}

	@Override
	public void display(Stack<Command> stack) {
		workspace_panel.removeAll();
		while (!stack.isEmpty()) {
			Command command = stack.pop();
			renderCommand(command);
		}
		panel.repaint();
	}

	@Override
	public Command getInput() {
		String command_id = command_id_field.getText();
		String type = type_field.getText();
		String comment = comment_area.getText();
		return new Command(command_id, type, comment);
	}

	@Override
	public void addActionListener(ActionListener controller) {
		pop_btn.addActionListener(controller);
		push_btn.addActionListener(controller);
		top_btn.addActionListener(controller);
		size_btn.addActionListener(controller);
		is_empty_btn.addActionListener(controller);
		pop_btn.setActionCommand("pop");
		push_btn.setActionCommand("push");
		top_btn.setActionCommand("top");
		size_btn.setActionCommand("size");
		is_empty_btn.setActionCommand("empty");
	}

	@Override
	public void removeActionListener(ActionListener controller) {
		pop_btn.removeActionListener(controller);
		push_btn.removeActionListener(controller);
		top_btn.removeActionListener(controller);
		size_btn.removeActionListener(controller);
		is_empty_btn.removeActionListener(controller);
	}

	@Override
	public JPanel getView() {
		return panel;
	}

	@Override
	public void clearInput() {
		command_id_field.setText("");
		type_field.setText("");
		comment_area.setText("");
	}

	private void initWorkspacePanel() {
		workspace_scroll = new JScrollPane();
		workspace_scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		workspace_scroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		workspace_scroll.setBounds(214, 0, 486, 551);
		panel.add(workspace_scroll);

		workspace_panel = new JPanel();
		workspace_scroll.setViewportView(workspace_panel);
		workspace_panel.setLayout(new BoxLayout(workspace_panel, BoxLayout.Y_AXIS));

		push_panel = new JPanel();
		push_panel.setBackground(Color.WHITE);
		push_panel.setBounds(0, 0, 218, 551);
		panel.add(push_panel);
		push_panel.setLayout(null);

		command_id_lbl = new JLabel("command id (String)");
		command_id_lbl.setFont(new Font("FreeMono", Font.BOLD, 16));
		command_id_lbl.setBounds(12, 12, 194, 30);
		push_panel.add(command_id_lbl);

		command_id_field = new JTextField();
		command_id_field.setBounds(12, 42, 194, 30);
		push_panel.add(command_id_field);
		command_id_field.setColumns(10);

		type_lbl = new JLabel("type (String)");
		type_lbl.setFont(new Font("FreeMono", Font.BOLD, 16));
		type_lbl.setBounds(12, 84, 194, 15);
		push_panel.add(type_lbl);

		type_field = new JTextField();
		type_field.setBounds(12, 111, 194, 30);
		push_panel.add(type_field);
		type_field.setColumns(10);

		comment_lbl = new JLabel("comment (String)");
		comment_lbl.setFont(new Font("FreeMono", Font.BOLD, 16));
		comment_lbl.setBounds(12, 153, 194, 15);
		push_panel.add(comment_lbl);

		comment_area_scroll = new JScrollPane();
		comment_area_scroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		comment_area_scroll.setBounds(12, 170, 194, 170);
		push_panel.add(comment_area_scroll);

		comment_area = new JTextArea();
		comment_area_scroll.setViewportView(comment_area);

		pop_btn = new JButton("pop");
		pop_btn.setFont(new Font("FreeMono", Font.PLAIN, 16));
		pop_btn.setBounds(50, 366, 117, 25);
		push_panel.add(pop_btn);

		push_btn = new JButton("push");
		push_btn.setFont(new Font("FreeMono", Font.PLAIN, 16));
		push_btn.setBounds(50, 403, 117, 25);
		push_panel.add(push_btn);

		top_btn = new JButton("top");
		top_btn.setFont(new Font("FreeMono", Font.PLAIN, 16));
		top_btn.setBounds(50, 440, 117, 25);
		push_panel.add(top_btn);

		size_btn = new JButton("size");
		size_btn.setFont(new Font("FreeMono", Font.PLAIN, 16));
		size_btn.setBounds(50, 477, 117, 25);
		push_panel.add(size_btn);

		is_empty_btn = new JButton("is empty?");
		is_empty_btn.setFont(new Font("FreeMono", Font.PLAIN, 16));
		is_empty_btn.setBounds(42, 514, 131, 25);
		push_panel.add(is_empty_btn);
	}

	private void renderCommand(Command command) {
		command_panel = new JPanel();
		command_panel.setMaximumSize(new Dimension(486, 300));
		command_panel.setPreferredSize(new Dimension(486, 300));
		command_panel.setMinimumSize(new Dimension(486, 300));
		command_panel.setBackground(new Color(255, 250, 250));
		workspace_panel.add(command_panel);
		command_panel.setLayout(null);

		JLabel lblCommandId = new JLabel("command id");
		lblCommandId.setFont(new Font("FreeMono", Font.BOLD, 16));
		lblCommandId.setBounds(12, 22, 118, 30);
		command_panel.add(lblCommandId);

		JLabel lbldasdsda = new JLabel(command.getId());
		lbldasdsda.setFont(new Font("FreeMono", Font.PLAIN, 16));
		lbldasdsda.setBounds(142, 22, 288, 30);
		command_panel.add(lbldasdsda);

		JLabel lblCommandType = new JLabel("command type");
		lblCommandType.setFont(new Font("FreeMono", Font.BOLD, 16));
		lblCommandType.setBounds(12, 64, 128, 30);
		command_panel.add(lblCommandType);

		JLabel lblDassssssssjklsdajlksadljsdksdljkdsaljk = new JLabel(command.getType());
		lblDassssssssjklsdajlksadljsdksdljkdsaljk.setFont(new Font("FreeMono", Font.PLAIN, 16));
		lblDassssssssjklsdajlksadljsdksdljkdsaljk.setBounds(152, 64, 278, 30);
		command_panel.add(lblDassssssssjklsdajlksadljsdksdljkdsaljk);

		JLabel lblCreatedAt = new JLabel("created at");
		lblCreatedAt.setFont(new Font("FreeMono", Font.BOLD, 16));
		lblCreatedAt.setBounds(12, 106, 117, 30);
		command_panel.add(lblCreatedAt);

		JLabel lbldssaddsasad = new JLabel(command.getCreated_at());
		lbldssaddsasad.setFont(new Font("FreeMono", Font.PLAIN, 16));
		lbldssaddsasad.setBounds(124, 106, 306, 30);
		command_panel.add(lbldssaddsasad);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setBounds(12, 148, 443, 138);
		command_panel.add(scrollPane);

		JTextArea txtrDsssssssssssssssss = new JTextArea();
		txtrDsssssssssssssssss.setFont(new Font("FreeMono", Font.PLAIN, 15));
		scrollPane.setViewportView(txtrDsssssssssssssssss);
		txtrDsssssssssssssssss.setWrapStyleWord(true);
		txtrDsssssssssssssssss.setEditable(false);
		txtrDsssssssssssssssss.setLineWrap(true);
		txtrDsssssssssssssssss.setText(command.getComment());

		Component verticalStrut = Box.createVerticalStrut(20);
		workspace_panel.add(verticalStrut);

		panel.repaint();
		panel.revalidate();
	}

}
