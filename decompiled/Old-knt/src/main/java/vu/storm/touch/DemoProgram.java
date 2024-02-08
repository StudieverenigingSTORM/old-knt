/*    */ package vu.storm.touch;
/*    */ 
/*    */ import java.awt.Dimension;
/*    */ import java.awt.event.WindowEvent;
/*    */ import java.awt.event.WindowListener;
/*    */ import javax.swing.JFrame;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class DemoProgram
/*    */   extends JFrame
/*    */   implements WindowListener
/*    */ {
/*    */   GUIPanel mainPanel;
/*    */   private static final int DEFAULT_WIDTH = 800;
/*    */   private static final int DEFAULT_HEIGHT = 600;
/* 17 */   private int sizeDiffX = 0;
/* 18 */   private int sizeDiffY = 0;
/*    */   
/*    */   private Dimension desiredSize;
/*    */   
/*    */   private DemoProgram(String title, String[] args) {
/* 23 */     this(title, 800, 600, args);
/*    */   }
/*    */ 
/*    */   
/*    */   private DemoProgram(String title, int desiredWidth, int desiredHeight, String[] args) {
/* 28 */     super(title);
/* 29 */     addWindowListener(this);
/* 30 */     setMinimumSize(new Dimension(desiredWidth, desiredHeight));
/* 31 */     setSize(new Dimension(desiredWidth, desiredHeight));
/* 32 */     setVisible(true);
/* 33 */     while (getContentPane().getHeight() == 0) {
/*    */       
/* 35 */       try { Thread.sleep(10L); } catch (InterruptedException interruptedException) {}
/*    */     } 
/* 37 */     this.sizeDiffY = desiredHeight - getContentPane().getHeight();
/* 38 */     this.sizeDiffX = desiredWidth - getContentPane().getWidth();
/* 39 */     this.desiredSize = new Dimension(desiredWidth + this.sizeDiffX, desiredHeight + this.sizeDiffY);
/* 40 */     setSize(this.desiredSize);
/* 41 */     setMinimumSize(this.desiredSize);
/* 42 */     setMaximumSize(this.desiredSize);
/* 43 */     this.mainPanel = new GUIPanel(args);
/* 44 */     add(this.mainPanel);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public static void main(String[] args) {}
/*    */ 
/*    */ 
/*    */   
/*    */   public void windowActivated(WindowEvent e) {}
/*    */ 
/*    */   
/*    */   public void windowClosed(WindowEvent e) {
/* 57 */     System.exit(0);
/*    */   }
/*    */ 
/*    */   
/*    */   public void windowClosing(WindowEvent e) {
/* 62 */     System.exit(0);
/*    */   }
/*    */   
/*    */   public void windowDeactivated(WindowEvent e) {}
/*    */   
/*    */   public void windowDeiconified(WindowEvent e) {}
/*    */   
/*    */   public void windowIconified(WindowEvent e) {}
/*    */   
/*    */   public void windowOpened(WindowEvent e) {}
/*    */ }


/* Location:              /Users/nicholas/Desktop/old-knt/old-jar/program.jar!/vu/storm/touch/DemoProgram.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */