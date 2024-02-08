/*    */ package vu.storm.touch;
/*    */ 
/*    */ import java.awt.Dimension;
/*    */ import java.awt.Toolkit;
/*    */ import javax.swing.JFrame;
/*    */ 
/*    */ 
/*    */ public class FullscreenProgram
/*    */   extends JFrame
/*    */ {
/*    */   GUIPanel mainPanel;
/*    */   
/*    */   public FullscreenProgram(String[] args) {
/* 14 */     super("Borrel Touch");
/* 15 */     setDefaultCloseOperation(3);
/* 16 */     setUndecorated(true);
/* 17 */     Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
/* 18 */     setBounds(0, 0, screenSize.width, screenSize.height);
/* 19 */     this.mainPanel = new GUIPanel(args);
/* 20 */     add(this.mainPanel, "Center");
/*    */   }
/*    */   
/*    */   public static void main(String[] args) {
/* 24 */     FullscreenProgram fp = new FullscreenProgram(args);
/* 25 */     fp.setVisible(true);
/*    */   }
/*    */ }


/* Location:              /Users/nicholas/Desktop/old-knt/old-jar/program.jar!/vu/storm/touch/FullscreenProgram.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */