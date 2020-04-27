package view;

import java.awt.event.ActionListener;

import javax.swing.JPanel;

import collection.Stack;

public interface StackView<T> {

	void display(Stack<T> stack);

	T getInput();

	void addActionListener(ActionListener controller);
	
	void removeActionListener(ActionListener controller);
	
	JPanel getView();
	
	void clearInput();

}
