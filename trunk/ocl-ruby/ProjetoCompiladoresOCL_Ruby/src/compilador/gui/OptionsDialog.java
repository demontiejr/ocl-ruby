/* Essa interface grafica foi baseada na GUI do JFlex 1.4.3
 ( Copyright (C) 1998-2009  Gerwin Klein <lsf@jflex.de>  ) */

package compilador.gui;

import java.awt.*;
import java.awt.event.*;

import compilador.Main;

public class OptionsDialog extends Dialog {

	private static final long serialVersionUID = 1L;

private Button ok;

  private Checkbox lexical;
  private Checkbox syntactic;
  private Checkbox semantic;
  
  private Checkbox debugOn;
  private Checkbox debugOff;

  /**
   * Create a new options dialog
   * 
   * @param owner
   */
  public OptionsDialog(Frame owner) {
    super(owner, "Opcoes");

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
    ok = new Button("Ok");

    CheckboxGroup analysisType = new CheckboxGroup();
    lexical = new Checkbox(" Lexica",Main.analysisType == Main.LEXICAL, analysisType);
    syntactic = new Checkbox(" Sintatica",Main.analysisType == Main.SYNTACTIC, analysisType);
    semantic = new Checkbox(" Semantica",Main.analysisType == Main.SEMANTIC, analysisType);
    
    CheckboxGroup debug = new CheckboxGroup();
    debugOn = new Checkbox(" ON", Main.debugMode == Main.ON, debug);
    debugOff = new Checkbox(" OFF", Main.debugMode == Main.OFF, debug);
    
    // setup interaction
    ok.addActionListener( new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        close();
      }
    } );

    lexical.addItemListener( new ItemListener() {
      public void itemStateChanged(ItemEvent e) {
        setAnalysisMode();
      }
    } );

    syntactic.addItemListener( new ItemListener() {
        public void itemStateChanged(ItemEvent e) {
          setAnalysisMode();
        }
      } );
    
    semantic.addItemListener( new ItemListener() {
        public void itemStateChanged(ItemEvent e) {
          setAnalysisMode();
        }
      } );
    
    debugOn.addItemListener( new ItemListener() {
        public void itemStateChanged(ItemEvent e) {
          setDebugMode();
        }
      } );
    
    debugOff.addItemListener( new ItemListener() {
        public void itemStateChanged(ItemEvent e) {
          setDebugMode();
        }
      } );
   
    // setup layout
    GridPanel panel = new GridPanel(3,5,10,10);
    panel.setInsets( new Insets(10,5,5,10) );
    
    panel.add(1,4,ok);
     
    panel.add(0,0,1,1,Handles.BOTTOM,new Label("Tipo de analise:"));
    panel.add(0,1,1,1,lexical);
    panel.add(0,2,1,1,syntactic);
    panel.add(0,3,1,1,semantic);

    panel.add(2,0,1,1,Handles.BOTTOM,new Label("Debug:"));
    panel.add(2,1,1,1,debugOn);
    panel.add(2,2,1,1,debugOff);
    
    add("Center",panel);
    
    updateState();
  }
    
  private void setAnalysisMode() {
    if ( lexical.getState() ) {
    	Main.analysisType = Main.LEXICAL;
      return;
    }
    
    if ( syntactic.getState() ) {
    	Main.analysisType = Main.SYNTACTIC;
      return;
    }
    
    if ( semantic.getState() ) {
    	Main.analysisType = Main.SEMANTIC;
      return;
    }
  }
  
  private void setDebugMode() {
		if (debugOn.getState()) {
			Main.debugMode = Main.ON;
		}

		else if (debugOff.getState()) {
			Main.debugMode = Main.OFF;
		}
  }

  private void updateState() {
    lexical.setState(Main.analysisType == Main.LEXICAL);
    syntactic.setState(Main.analysisType == Main.SYNTACTIC);
    semantic.setState(Main.analysisType == Main.SEMANTIC);
    debugOn.setState(Main.debugMode == Main.ON);
    debugOff.setState(Main.debugMode == Main.OFF);
  }


  public void close() {
    hide();
  }

}
