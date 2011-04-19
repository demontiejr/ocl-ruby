package compilador.gui;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Checkbox;
import java.awt.CheckboxGroup;
import java.awt.Dialog;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Insets;
import java.awt.Label;
import java.awt.Panel;
import java.awt.TextArea;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import JFlex.Options;

import compilador.Main;

public class AboutDialog extends Dialog{
	  private Frame owner;
	  
	  private String infString = "Universidade Federal de Campina Grande - UFCG\n" +
		"Centro de Engenharia Eletrica e Informatica - CEEI\n" +
  		"Departamento de Sistemas e Computacao - DSC\n\n" +
  		"Projeto da disciplina de Compiladores, 2011.1\n" +
  		"Professor: Franklin Ramalho\n" +
  		"Compilador de Pre e Pos condicoes para verificacao\n" +
  		"OCL para Ruby\n\n" +
  		"Grupo: Francisco Demontie dos Santos Junior - 20911084\n" +
  		"Izabela Vanessa de Almeida Melo - 20811018\n" +
  		"Savyo Igor da Nobrega Santos - 20811034"; 

	  private TextArea information;
	  private Button ok;

	  /**
	   * Create a new options dialog
	   * 
	   * @param owner
	   */
	  public AboutDialog(Frame owner) {
	    super(owner, "Sobre");

	    this.owner = owner;
	    
	    setup();
	    pack();
	    
	    addWindowListener( new WindowAdapter() {
	      public void windowClosing(WindowEvent e) {
	        close();
	      }
	    });
	  }

	  public void setup() {
	    // create components
		information = new TextArea(12,48);
		information.setText(infString);
		information.setEditable(false);
		
		ok = new Button("Ok");
	    
	    // setup interaction
	    ok.addActionListener( new ActionListener() {
	      public void actionPerformed(ActionEvent e) {
	        close();
	      }
	    } );
	    
	    // setup layout
	    Panel panel = new Panel(new BorderLayout());
	    panel.add("Center",information);
	    panel.add("South",ok);
	     
	    add("Center",panel);
	  }
	  
	  public void close() {
	    hide();
	  }
}
