package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

import bean.Command;
import bean.Student;
import collection.LinkedStack;
import collection.Stack;
import view.CommandStackView;
import view.MainFrame;
import view.StackView;
import view.StudentStackView;

public class MainController implements ActionListener {

	private MainFrame view;
	private ActionListener current;

	public MainController(MainFrame view) {
		super();
		this.view = view;
	}

	@SuppressWarnings("unchecked")
	@Override
	public void actionPerformed(ActionEvent e) {
		try {
			switch (e.getActionCommand()) {
			case "open":
				JFileChooser chooser = new JFileChooser();
				if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
					File location = chooser.getSelectedFile();
					Object model = open(location);
					if (model instanceof Stack) {
						try {
							Stack<Command> commands = (Stack<Command>) model;
							StackView<Command> view = new CommandStackView();
							CommandController controller = new CommandController(location, commands, view);
							this.view.removeMenuListener(current);
							this.current = controller;
							this.view.addMenuListener(controller);
							this.view.setStackView(view);
							view.addActionListener(controller);
							view.display(controller.getCommands());
						} catch (ClassCastException ex) {
							ex.printStackTrace();
							try {
								Stack<Student> students = (Stack<Student>) model;
								StackView<Student> view = new StudentStackView();
								StudentController controller = new StudentController(location, students, view);
								this.view.removeMenuListener(current);
								this.current = controller;
								this.view.addMenuListener(controller);
								this.view.setStackView(view);
								view.addActionListener(controller);
								view.display(controller.getStudents());
							} catch (ClassCastException ex1) {
								JOptionPane.showMessageDialog(null, ex1.getMessage());
							}
						}
					}
				}
				break;
			case "create button":
				String type = this.view.getInputType();
				switch (type) {
				case "command":
					Stack<Command> commands = new LinkedStack<>();
					StackView<Command> view = new CommandStackView();
					CommandController controller = new CommandController(view, commands);
					this.view.removeMenuListener(current);
					this.current = controller;
					this.view.addMenuListener(controller);
					this.view.setStackView(view);
					view.addActionListener(controller);
					break;
				case "student":
					Stack<Student> students = new LinkedStack<>();
					StackView<Student> students_view = new StudentStackView();
					StudentController students_controller = new StudentController(students, students_view);
					this.view.removeMenuListener(current);
					this.current = students_controller;
					this.view.addMenuListener(students_controller);
					this.view.setStackView(students_view);
					students_view.addActionListener(students_controller);
					students_view.display(students_controller.getStudents());
					break;
				}
				break;
			case "new":
				this.view.removeMenuListener(current);
				this.current = null;
				this.view.showCreation();
				break;
			}
		} catch (ClassNotFoundException | IOException e1) {
			e1.printStackTrace();
		}
	}

	private Object open(File file) throws FileNotFoundException, IOException, ClassNotFoundException {
		try (ObjectInputStream obj_stream = new ObjectInputStream(new FileInputStream(file))) {
			return obj_stream.readObject();
		}
	}

}
