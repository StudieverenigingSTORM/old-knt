/*    */ package vu.storm.touch.user;
/*    */ 
/*    */ import java.awt.Color;
/*    */ import java.awt.Component;
/*    */ import java.awt.LayoutManager;
/*    */ import java.awt.event.ActionEvent;
/*    */ import java.awt.event.ActionListener;
/*    */ import java.awt.event.ComponentEvent;
/*    */ import java.awt.event.ComponentListener;
/*    */ import javax.swing.JPanel;
/*    */ import vu.storm.touch.gfx.ClockPanel;
/*    */ import vu.storm.touch.gfx.StandardButton;
/*    */ 
/*    */ public class UserScreenBar
/*    */   extends JPanel
/*    */   implements ComponentListener, ActionListener
/*    */ {
/*    */   UserScreen parent;
/*    */   StandardButton btnKTList;
/*    */   StandardButton btnBorrel;
/*    */   ClockPanel clock;
/*    */   
/*    */   public UserScreenBar(UserScreen parent) {
/* 24 */     setBackground(Color.black);
/* 25 */     this.parent = parent;
/* 26 */     this.btnBorrel = new StandardButton("Borrelmode");
/* 27 */     this.btnKTList = new StandardButton("K&T-lijst");
/* 28 */     this.clock = new ClockPanel();
/* 29 */     setLayout((LayoutManager)null);
/* 30 */     addComponentListener(this);
/* 31 */     add((Component)this.btnKTList);
/* 32 */     add((Component)this.btnBorrel);
/* 33 */     add((Component)this.clock);
/* 34 */     this.btnKTList.addActionListener(this);
/* 35 */     this.btnBorrel.addActionListener(this);
/* 36 */     this.btnKTList.setBackground(Color.black);
/* 37 */     this.btnBorrel.setBackground(Color.black);
/* 38 */     this.btnKTList.setVisible(false);
/*    */   }
/*    */ 
/*    */   
/*    */   public void componentHidden(ComponentEvent e) {}
/*    */ 
/*    */   
/*    */   public void componentMoved(ComponentEvent e) {}
/*    */   
/*    */   public void componentResized(ComponentEvent e) {
/* 48 */     this.btnKTList.setBounds(0, 0, getWidth() / 4, getHeight());
/* 49 */     this.btnBorrel.setBounds(getWidth() / 4, 0, getWidth() / 4, getHeight());
/* 50 */     this.clock.setBounds(getWidth() * 3 / 4, 0, getWidth() / 4, getHeight());
/*    */   }
/*    */ 
/*    */   
/*    */   public void componentShown(ComponentEvent e) {}
/*    */   
/*    */   public void actionPerformed(ActionEvent e) {
/* 57 */     if (e.getSource() == this.btnBorrel) {
/*    */       
/* 59 */       this.parent.setBorrelMode(true);
/* 60 */       this.btnBorrel.setVisible(false);
/* 61 */       this.btnKTList.setVisible(true);
/*    */     }
/* 63 */     else if (e.getSource() == this.btnKTList) {
/*    */       
/* 65 */       this.parent.setBorrelMode(false);
/* 66 */       this.btnKTList.setVisible(false);
/* 67 */       this.btnBorrel.setVisible(true);
/*    */     } 
/*    */   }
/*    */ }


/* Location:              /Users/nicholas/Desktop/old-knt/old-jar/program.jar!/vu/storm/touch/user/UserScreenBar.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */