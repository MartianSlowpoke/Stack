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

import bean.Command;
import collection.LinkedStack;
import collection.Stack;
import utils.UnMeasurableException;
import view.StackView;

public class CommandController implements ActionListener {

	private static final String EMPTY = "Empty stack(";
	private static final String SAVED = "The stack has been successfully saved to ";
	private static final String SWAP = "The stack has been successfully swapped with ";
	private static final String SIZE = "Size ";
	private static final String NO_VALID_INPUT = "All fields for input are required";
	private static final String EQUALS = "Do these stacks equal to each other by fields :\nid\ntype\ncomment\nresult:";
	private static final String COMPARISON = "The difference between this stack and that stack equals to ";
	private static DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");

	private File storage;
	private Stack<Command> commands;
	private StackView<Command> view;
	private JFileChooser chooser;

	public CommandController(File storage, Stack<Command> commands, StackView<Command> view) {
		this.view = view;
		this.commands = commands;
		this.storage = storage;
		this.chooser = new JFileChooser();
	}

	public CommandController(StackView<Command> view, Stack<Command> commands) {
		this.commands = commands;
		this.view = view;
		this.chooser = new JFileChooser();
	}

	@SuppressWarnings("unchecked")
	@Override
	public void actionPerformed(ActionEvent e) {
		try {
			switch (e.getActionCommand()) {
			case "save as":
				if (chooser.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
					File storage = chooser.getSelectedFile();
					save(commands, storage);
					this.storage = storage;
					JOptionPane.showMessageDialog(null, SAVED + storage.getPath());
				}
				break;
			case "save":
				if (storage == null) {
					if (chooser.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
						File storage = chooser.getSelectedFile();
						save(commands, storage);
						this.storage = storage;
						JOptionPane.showMessageDialog(null, SAVED + storage.getPath());
						return;
					}
				}
				save(commands, storage);
				JOptionPane.showMessageDialog(null, SAVED + storage.getPath());
				break;
			/*
			 * I have forgotten how does it work!
			 */
			case "swap":
				if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
					File source = chooser.getSelectedFile();
					swap(source);
					view.display(getCommands());
					JOptionPane.showMessageDialog(null, SWAP + source.getPath());
				}
				break;
			case "pop":
				if (commands.isEmpty()) {
					JOptionPane.showMessageDialog(null, EMPTY);
					return;
				}
				Command pop = commands.pop();
				view.display(getCommands());
				JOptionPane.showMessageDialog(null, pop.toString());
				break;
			case "push":
				Command command = view.getInput();
				if (command.getId().isBlank() || command.getType().isBlank() || command.getComment().isBlank()) {
					JOptionPane.showMessageDialog(null, NO_VALID_INPUT);
					return;
				}
				command.setCreated_at(LocalDateTime.now().format(dtf));
				commands.push(command);
				view.clearInput();
				view.display(getCommands());
				break;
			case "top":
				if (commands.isEmpty()) {
					JOptionPane.showMessageDialog(null, EMPTY);
					return;
				}
				Command top = commands.top();
				JOptionPane.showMessageDialog(null, top.toString());
				break;
			case "size":
				int size = commands.size();
				JOptionPane.showMessageDialog(null, SIZE + size);
				break;
			case "empty":
				boolean isEmpty = commands.isEmpty();
				JOptionPane.showMessageDialog(null, isEmpty);
				break;
			case "equals":
				if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
					File location = chooser.getSelectedFile();
					Object model = open(location);
					Stack<Command> commands = (Stack<Command>) model;
					JOptionPane.showMessageDialog(null, EQUALS + this.commands.equals(commands));
				}
				break;
			case "compare":
				try {
					if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
						File location = chooser.getSelectedFile();
						Object model = open(location);
						Stack<Command> commands = (Stack<Command>) model;
						int diff = this.commands.compareTo(commands);
						JOptionPane.showMessageDialog(null, COMPARISON + Integer.toString(diff));
					}
				} catch (UnMeasurableException e1) {
					JOptionPane.showMessageDialog(null,
							"Some of two working stacks doesn't support comprasion operation");
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
			JOptionPane.showMessageDialog(null, e1.getMessage());
		}
	}

	@SuppressWarnings("unchecked")
	private void swap(File file)
			throws FileNotFoundException, IOException, ClassNotFoundException, CloneNotSupportedException {
		try (ObjectInputStream obj_stream = new ObjectInputStream(new FileInputStream(file))) {
			Stack<Command> commands = (Stack<Command>) obj_stream.readObject();
			this.commands.swap(commands);
			save(commands, file);
		}
	}

	private void save(Stack<Command> commands, File file) throws FileNotFoundException, IOException {
		try (ObjectOutputStream obj_stream = new ObjectOutputStream(new FileOutputStream(file))) {
			obj_stream.writeObject(commands);
		}
	}

	public Stack<Command> getCommands() {
		return new LinkedStack<>(commands);
	}

	private Object open(File file) throws FileNotFoundException, IOException, ClassNotFoundException {
		try (ObjectInputStream obj_stream = new ObjectInputStream(new FileInputStream(file))) {
			return obj_stream.readObject();
		}
	}

}
