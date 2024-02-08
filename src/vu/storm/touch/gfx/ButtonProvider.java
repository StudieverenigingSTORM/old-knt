package vu.storm.touch.gfx;

import java.awt.event.ActionListener;
import javax.swing.JComponent;

public interface ButtonProvider {
  JComponent createButton(int paramInt);
  
  void destroyButton(JComponent paramJComponent);
  
  int numButtons();
  
  JComponent createNextPageButton(ActionListener paramActionListener);
  
  JComponent createPrevPageButton(ActionListener paramActionListener);
  
  boolean matchesFilter(Object paramObject, JComponent paramJComponent);
}


/* Location:              /Users/nicholas/Desktop/old-knt/old-jar/program.jar!/vu/storm/touch/gfx/ButtonProvider.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */