/* Essa interface grafica foi baseada na GUI do JFlex 1.4.3
 ( Copyright (C) 1998-2009  Gerwin Klein <lsf@jflex.de>  ) */

package compilador.gui;

import java.awt.*;
import java.awt.event.*;

import compilador.Main;

public class OptionsDialog extends Dialog {

	private static final long serialVersionUID = 1L;

	private Button ok;

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

    CheckboxGroup debug = new CheckboxGroup();
    debugOn = new Checkbox(" ON", Main.debugMode == Main.ON, debug);
    debugOff = new Checkbox(" OFF", Main.debugMode == Main.OFF, debug);
    
    // setup interaction
    ok.addActionListener( new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        close();
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
    GridPanel panel = new GridPanel(1,5,10,10);
    panel.setInsets( new Insets(10,5,5,10) );
    
    panel.add(0,3,ok);

    panel.add(0,0,1,1,Handles.BOTTOM,new Label("Debug:"));
    panel.add(0,1,1,1,debugOn);
    panel.add(0,2,1,1,debugOff);
    
    add("Center",panel);
    
    updateState();
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
    debugOn.setState(Main.debugMode == Main.ON);
    debugOff.setState(Main.debugMode == Main.OFF);
  }


  @SuppressWarnings("deprecation")
public void close() {
    hide();
  }

}
