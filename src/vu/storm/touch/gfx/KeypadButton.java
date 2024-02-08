/*    */ package vu.storm.touch.gfx;
/*    */ 
/*    */ import java.awt.Color;
/*    */ import java.awt.Font;
/*    */ import java.awt.FontMetrics;
/*    */ import java.awt.Graphics;
/*    */ import java.awt.LayoutManager;
/*    */ import java.awt.event.ActionEvent;
/*    */ import java.awt.event.ActionListener;
/*    */ import java.awt.event.ComponentEvent;
/*    */ import java.awt.event.ComponentListener;
/*    */ import javax.swing.JPanel;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class KeypadButton
/*    */   extends JPanel
/*    */   implements ComponentListener, ActionListener
/*    */ {
/*    */   StyledButton button;
/*    */   int sizeX;
/*    */   int sizeY;
/*    */   char value;
/*    */   Keypad parent;
/*    */   
/*    */   protected KeypadButton(Keypad parent, String label, char value, int sizeX, int sizeY) {
/* 28 */     this.parent = parent;
/* 29 */     this.sizeX = sizeX;
/* 30 */     this.sizeY = sizeY;
/* 31 */     setOpaque(false);
/* 32 */     this.value = value;
/* 33 */     this.button = new StyledButton(this, label);
/* 34 */     this.button.addActionListener(this);
/* 35 */     setLayout((LayoutManager)null);
/* 36 */     add(this.button);
/* 37 */     setBackground(new Color(255, 255, 255, 255));
/* 38 */     addComponentListener(this);
/*    */   }
/*    */   
/*    */   protected void setButtonSize(int sizeX, int sizeY) {
/* 42 */     this.sizeX = sizeX;
/* 43 */     this.sizeY = sizeY;
/*    */   }
/*    */ 
/*    */   
/*    */   public void paint(Graphics g) {
/* 48 */     super.paint(g);
/*    */   }
/*    */   
/*    */   private class StyledButton
/*    */     extends LongPressButton
/*    */   {
/*    */     Font font;
/*    */     KeypadButton parent;
/*    */     String label;
/*    */     
/*    */     public StyledButton(KeypadButton parent, String label) {
/* 59 */       this.parent = parent;
/* 60 */       this.label = label;
/* 61 */       rescale();
/*    */     }
/*    */     
/*    */     protected void rescale() {
/* 65 */       this.font = new Font("Verdana", 0, getHeight() * 4 / 10);
/* 66 */       setFont(this.font);
/*    */     }
/*    */     
/*    */     public void paint(Graphics g) {
/* 70 */       g.setFont(this.font);
/* 71 */       FontMetrics fm = g.getFontMetrics(this.font);
/* 72 */       g.setColor(this.pressed ? Color.black : Color.white);
/* 73 */       g.fillRoundRect(0, 0, getWidth() - 1, getHeight() - 1, getHeight() / 4, getHeight() / 4);
/* 74 */       g.setColor(this.pressed ? Color.white : Color.black);
/* 75 */       g.fillRoundRect(2, 2, getWidth() - 5, getHeight() - 5, getHeight() / 4, getHeight() / 4);
/* 76 */       g.setColor(this.pressed ? Color.black : Color.white);
/* 77 */       g.drawString(this.label, (getWidth() - fm.stringWidth(this.label)) / 2, getHeight() / 2 - fm.getHeight() / 2 + fm.getAscent());
/*    */     }
/*    */   }
/*    */ 
/*    */   
/*    */   public void componentHidden(ComponentEvent e) {}
/*    */ 
/*    */   
/*    */   public void componentMoved(ComponentEvent e) {}
/*    */   
/*    */   public void componentResized(ComponentEvent e) {
/* 88 */     this.button.setBounds(getWidth() / this.sizeX * 10, getHeight() / this.sizeY * 10, getWidth() * (this.sizeX * 10 - 2) / this.sizeX * 10, getHeight() * (this.sizeY * 10 - 2) / this.sizeY * 10);
/*    */     
/* 90 */     this.button.rescale();
/*    */   }
/*    */ 
/*    */   
/*    */   public void componentShown(ComponentEvent e) {}
/*    */   
/*    */   public void actionPerformed(ActionEvent e) {
/* 97 */     this.parent.keypadPressed(this.value);
/*    */   }
/*    */ }


/* Location:              /Users/nicholas/Desktop/old-knt/old-jar/program.jar!/vu/storm/touch/gfx/KeypadButton.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */