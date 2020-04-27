package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

import bean.Student;
import collection.LinkedStack;
import collection.Stack;
import utils.UnMeasurableException;
import view.StackView;

public class StudentController implements ActionListener {

	private static DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");

	private File storage;
	private Stack<Student> students;
	private StackView<Student> view;
	private JFileChooser chooser = new JFileChooser();

	public StudentController(File storage, Stack<Student> students, StackView<Student> view) {
		this.storage = storage;
		this.students = students;
		this.view = view;
	}

	public StudentController(Stack<Student> students, StackView<Student> view) {
		this.students = students;
		this.view = view;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		try {
			switch (e.getActionCommand()) {
			case "save as":
				if (chooser.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
					File storage = chooser.getSelectedFile();
					save(students, storage);
					this.storage = storage;
					JOptionPane.showMessageDialog(null, "saved to" + storage.getPath());
				}
				break;
			case "save":
				if (storage == null) {
					if (chooser.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
						File storage = chooser.getSelectedFile();
						save(students, storage);
						this.storage = storage;
						JOptionPane.showMessageDialog(null, "saved to " + storage.getPath());
						return;
					}
				}
				save(students, storage);
				JOptionPane.showMessageDialog(null, "saved to " + storage.getPath());
				break;
			case "swap":
				if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
					File source = chooser.getSelectedFile();
					swap(source);
					view.display(getStudents());
					JOptionPane.showMessageDialog(null, "swap " + source.getPath());
				}
				break;
			case "pop":
				if (students.isEmpty()) {
					JOptionPane.showMessageDialog(null, "empty(");
					return;
				}
				Student pop = students.pop();
				view.display(getStudents());
				JOptionPane.showMessageDialog(null, pop.toString());
				break;
			case "push":
				Student student = view.getInput();
				if (student.getId().isBlank() || student.getFirst_name().isBlank() || student.getLast_name().isBlank()
						|| student.getGroup().isBlank()) {
					JOptionPane.showMessageDialog(null, "no valid input");
					return;
				}
				student.setRegistration_time(LocalDateTime.now().format(dtf));
				students.push(student);
				view.clearInput();
				view.display(getStudents());
				break;
			case "top":
				if (students.isEmpty()) {
					JOptionPane.showMessageDialog(null, "empty(");
					return;
				}
				Student top = students.top();
				JOptionPane.showMessageDialog(null, top.toString());
				break;
			case "size":
				int size = students.size();
				JOptionPane.showMessageDialog(null, "size " + size);
				break;
			case "empty":
				boolean isEmpty = students.isEmpty();
				JOptionPane.showMessageDialog(null, isEmpty);
				break;
			case "equals":
				if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
					File location = chooser.getSelectedFile();
					Object model = open(location);
					@SuppressWarnings("unchecked")
					Stack<Student> students = (Stack<Student>) model;
					JOptionPane.showMessageDialog(null, "equals " + this.students.equals(students));
				}
				break;
			case "compare":
				try {
					if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
						File location = chooser.getSelectedFile();
						Object model = open(location);
						@SuppressWarnings("unchecked")
						Stack<Student> students = (Stack<Student>) model;
						int diff = this.students.compareTo(students);
						JOptionPane.showMessageDialog(null, "comprasion " + Integer.toString(diff));
					}
				} catch (UnMeasurableException e1) {
					JOptionPane.showMessageDialog(null,
							"One of two working stacks doesn't support comprasion operation");
				} catch (ClassCastException e1) {
					JOptionPane.showMessageDialog(null, "The stacks for comprasion should be of the same type");
				}
				break;

			}
		} catch (IOException e1) {
			e1.printStackTrace();
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		} catch (CloneNotSupportedException e1) {
			e1.printStackTrace();
		} catch (ClassCastException e1) {
			e1.printStackTrace();
		}
	}

	@SuppressWarnings("unchecked")
	private void swap(File file)
			throws FileNotFoundException, IOException, ClassNotFoundException, CloneNotSupportedException {
		try (ObjectInputStream obj_stream = new ObjectInputStream(new FileInputStream(file))) {
			Stack<Student> students = (Stack<Student>) obj_stream.readObject();
			this.students.swap(students);
			save(students, file);
		}
	}

	private void save(Stack<Student> students, File file) throws FileNotFoundException, IOException {
		try (ObjectOutputStream obj_stream = new ObjectOutputStream(new FileOutputStream(file))) {
			obj_stream.writeObject(students);
		}
	}

	private Object open(File file) throws FileNotFoundException, IOException, ClassNotFoundException {
		try (ObjectInputStream obj_stream = new ObjectInputStream(new FileInputStream(file))) {
			return obj_stream.readObject();
		}
	}

	public Stack<Student> getStudents() {
		return new LinkedStack<Student>(students);
	}

}
