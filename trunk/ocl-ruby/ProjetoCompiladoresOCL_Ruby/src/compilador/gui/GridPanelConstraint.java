/* Essa interface grafica foi baseada na GUI do JFlex 1.4.3
 ( Copyright (C) 1998-2009  Gerwin Klein <lsf@jflex.de>  ) */

package compilador.gui;

import java.awt.Component;

public class GridPanelConstraint {

  int x, y, width, height, handle;
  Component component;

  public GridPanelConstraint(int x, int y, int width, int height, 
                             int handle, Component component) {
    this.x         = x;
    this.y         = y;
    this.width     = width;
    this.height    = height;
    this.handle    = handle;
    this.component = component;
  }
}
