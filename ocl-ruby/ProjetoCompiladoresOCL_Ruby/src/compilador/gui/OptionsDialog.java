/* Essa interface grafica foi baseada na GUI do JFlex 1.4.3
 ( Copyright (C) 1998-2009  Gerwin Klein <lsf@jflex.de>  ) */

package compilador.gui;

import java.awt.*;
import java.awt.event.*;

import compilador.Main;

import JFlex.GeneratorException;
import JFlex.Options;

public class OptionsDialog extends Dialog {

  private Button ok;

  private Checkbox lexical;
  private Checkbox syntactic;
  private Checkbox packG;
  

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

    CheckboxGroup codeG = new CheckboxGroup();
    lexical = new Checkbox(" Lexica",Main.analysisType == Main.LEXICAL, codeG);
    syntactic = new Checkbox(" Sintatica",Main.analysisType == Main.SYNTACTIC, codeG);
    packG = new Checkbox(" Semantica",Main.analysisType == Options.PACK, codeG);
    packG.setEnabled(false);
    
    // setup interaction
    ok.addActionListener( new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        close();
      }
    } );

    lexical.addItemListener( new ItemListener() {
      public void itemStateChanged(ItemEvent e) {
        setGenMethod();
      }
    } );

    syntactic.addItemListener( new ItemListener() {
        public void itemStateChanged(ItemEvent e) {
          setGenMethod();
        }
      } );
    
    packG.addItemListener( new ItemListener() {
        public void itemStateChanged(ItemEvent e) {
          setGenMethod();
        }
      } );
   
    // setup layout
    GridPanel panel = new GridPanel(1,7,10,10);
    panel.setInsets( new Insets(10,5,5,10) );
    
    panel.add(0,4,ok);
     
    panel.add(0,0,1,1,Handles.BOTTOM,new Label("Tipo de analise:"));
    panel.add(0,1,1,1,lexical);
    panel.add(0,2,1,1,syntactic);
    panel.add(0,3,1,1,packG);

    add("Center",panel);
    
    updateState();
  }
    
  private void setGenMethod() {
    if ( lexical.getState() ) {
    	Main.analysisType = Main.LEXICAL;
      return;
    }
    
    if ( syntactic.getState() ) {
    	Main.analysisType = Main.SYNTACTIC;
      return;
    }
    
    if ( packG.getState() ) {
    	Main.analysisType = Options.PACK;
      return;
    }
  }

  private void updateState() {
    lexical.setState(Main.analysisType == Main.LEXICAL);
    syntactic.setState(Main.analysisType == Main.SYNTACTIC);
    packG.setState(Main.analysisType == Options.PACK);     
  }


  public void close() {
    hide();
  }

}
