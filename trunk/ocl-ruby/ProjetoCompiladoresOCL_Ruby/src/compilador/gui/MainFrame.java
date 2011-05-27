/* Essa interface grafica foi baseada na GUI do JFlex 1.4.3
 ( Copyright (C) 1998-2009  Gerwin Klein <lsf@jflex.de>  ) */

package compilador.gui;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Dimension;
import java.awt.FileDialog;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Insets;
import java.awt.Label;
import java.awt.Panel;
import java.awt.SystemColor;
import java.awt.TextArea;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.TextEvent;
import java.awt.event.TextListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;
import java.util.Set;

import JFlex.Out;

/*********************************************************
* Universidade Federal de Campina Grande - UFCG          *
* Centro de Engenharia Eletrica e Informatica - CEEI     *
* Departamento de Sistemas e Computacao - DSC            *
*                                                        *
* Projeto da disciplina de Compiladores, 2011.1          *
* Graphics User Interface                                *
*                                                        *
* Grupo: Francisco Demontie dos Santos Junior - 20911084 *
*        Izabela Vanessa de Almeida Melo - 20811018      *
*        Savyo Igor da Nobrega Santos - 20811034         *
*********************************************************/

@SuppressWarnings("serial")
final public class MainFrame extends Frame implements Handles {

  private volatile boolean choosing;

  private String fileName = "";
  private String xmiName = "";
  
  private Button quit; 
  private Button options;
  private Button generate;
  private Button about;
  private Button specChoose;

  private TextField spec;

  private TextArea messages;

  private GeneratorThread thread;

  private OptionsDialog dialog;
  
  private AboutDialog aboutPanel;

  
  public final int LEXICAL = 1, SYNTACTIC = 2;
  
  public int analysisType = 2;
  
  private Button specChooseXmi;
  
  private TextField specXmi;
  
  public MainFrame() {
    super("Compilador OCL para Ruby ");
    buildContent();
    
    addWindowListener( new WindowAdapter() {
      public void windowClosing(WindowEvent e) {
        quit();
      }
    });
    
    pack();
    show();
  }


  private void buildContent() {
    setBackground(SystemColor.control);

    generate   = new Button("Analisar");
    quit       = new Button("Sair");
    options    = new Button("Opcoes");
    about       = new Button("Sobre");
    specChoose = new Button("Procurar");
    spec       = new TextField(10);
    specChooseXmi = new Button("Procurar");
    specXmi       = new TextField(10);
    messages   = new TextArea(10,80);

    messages.setEditable(false);
    Font font = messages.getFont();
    if (font != null)
      messages.setFont(new Font("Monospaced", font.getStyle(), font.getSize()));
    else
      messages.setFont(new Font("Monospaced", Font.PLAIN, 12));

    Out.setGUIMode(messages);

    generate.addActionListener( new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        analyse();
      }
    } );

    options.addActionListener( new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        showOptions();
      }
    } );

    quit.addActionListener( new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        quit();
      }
    } );
    
    about.addActionListener( new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        about();
      }
    } );        

    specChoose.addActionListener( new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        specChoose();
      }
    } );

    spec.addActionListener( new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        fileName = spec.getText();
        analyse();
      }
    } );
    
    spec.addTextListener( new TextListener() {
      public void textValueChanged(TextEvent e) {
        fileName = spec.getText();
      }
    } );
    
    specChooseXmi.addActionListener( new ActionListener() {
        public void actionPerformed(ActionEvent e) {
          specChooseXmi();
        }
      } );

      specXmi.addActionListener( new ActionListener() {
        public void actionPerformed(ActionEvent e) {
          xmiName = specXmi.getText();
          analyse();
        }
      } );
      
      specXmi.addTextListener( new TextListener() {
        public void textValueChanged(TextEvent e) {
          xmiName = specXmi.getText();
        }
      } );
    
    GridPanel north = new GridPanel(5,4,10,10);
    north.setInsets( new Insets(10,5,5,10) );

    north.add( 4,0, options);
    north.add( 4,1, generate);
    north.add( 4,2, about);
    north.add( 4,3, quit);

    north.add( 0,0, BOTTOM, new Label("Arquivo para analise:"));
    north.add( 0,1, 2,1, spec);
    north.add( 2,1, specChoose);
    north.add( 0,2, BOTTOM, new Label("Arquivo XMI:"));
    north.add( 0,3, 2,1, specXmi);
    north.add( 2,3, specChooseXmi);

    Panel center = new Panel(new BorderLayout());   
    center.add("North", new Label("Saida:"));
    center.add("Center", messages);

    add("North", north);
    add("Center", center);

    setEnabledAll(false);
  }

  protected void showOptions() {
    if (dialog == null) {
      dialog = new OptionsDialog(this);
    }
    dialog.show();
  }


  public Dimension getPreferredSize() {
    Dimension d = super.getPreferredSize();
    d.width = messages.getPreferredSize().width;
    return d;
  }
  
  private void setEnabledAll(boolean generating) {
    about.setEnabled( !generating );
    quit.setEnabled( !generating );
    generate.setEnabled( !generating );
    specChoose.setEnabled( !generating );
    spec.setEnabled( !generating );
    specChooseXmi.setEnabled( !generating );
    specXmi.setEnabled( !generating );
  }

  private void analyse() {
    // workaround for a weird AWT bug
    if (choosing) return;
   
    setEnabledAll(true);

    thread = new GeneratorThread(this, fileName, xmiName);
    thread.start();
  } 

  public void analysisFinished(boolean success, List<String> log, List<String> errors, 
		  Set<String> semanticErrors) {
    setEnabledAll(false);
    
    for (String s : log)
    	messages.append(s);
    
    messages.append("\n"+(errors.size() + semanticErrors.size()) + " erro(s)\n");
    for (String error : errors)
    	messages.append(error);
    for (String error : semanticErrors)
    	messages.append(error);
    
    if (success)
      messages.append("\n"+Out.NL+"Analise concluida."+Out.NL);
    else
      messages.append("\n"+Out.NL+"Generation aborted."+Out.NL);
  }
 
  private void quit() {
    setVisible(false);
    System.exit(0);
  }

  private void specChoose() {
    choosing = true;
    
    FileDialog d = new FileDialog(this, "Selecione o arquivo OCL", FileDialog.LOAD);
    
    d.setFile("*.ocl");
    d.show();
    
    if (d.getFile() != null) {
      fileName = d.getDirectory()+d.getFile();
      spec.setText(fileName);
    }
    
    choosing = false;    
  }
  
	private void specChooseXmi() {
		choosing = true;

		FileDialog dxmi = new FileDialog(this, "Selecione o arquivo XMI",
				FileDialog.LOAD);

		dxmi.setFile("*.xml");
		dxmi.show();

		if (dxmi.getFile() != null) {
			xmiName = dxmi.getDirectory() + dxmi.getFile();
			specXmi.setText(xmiName);
		}

		choosing = false;
	}
  
  private void about(){
	  if (aboutPanel == null) {
	      aboutPanel = new AboutDialog(this);
	    }
	    aboutPanel.show();
  }
    
}
