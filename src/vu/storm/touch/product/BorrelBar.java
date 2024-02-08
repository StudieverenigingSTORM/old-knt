/*     */ package vu.storm.touch.product;
/*     */ 
/*     */ import java.awt.Color;
/*     */ import java.awt.Component;
/*     */ import java.awt.LayoutManager;
/*     */ import java.awt.event.ActionEvent;
/*     */ import java.awt.event.ActionListener;
/*     */ import java.awt.event.ComponentEvent;
/*     */ import java.awt.event.ComponentListener;
/*     */ import javax.swing.JLabel;
/*     */ import javax.swing.JPanel;
/*     */ import vu.storm.touch.gfx.ClockPanel;
/*     */ import vu.storm.touch.gfx.GfxUtil;
/*     */ import vu.storm.touch.gfx.StandardButton;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class BorrelBar
/*     */   extends JPanel
/*     */   implements ActionListener, ComponentListener
/*     */ {
/*     */   ProductScreen parent;
/*     */   ResetButton resetButton;
/*     */   StandardButton omzetButton;
/*     */   JLabel lOmzet;
/*     */   JLabel lInKas;
/*     */   Thread tOmzetTimeout;
/*     */   ClockPanel clock;
/*     */   
/*     */   public BorrelBar(ProductScreen parent) {
/*  39 */     setBackground(Color.black);
/*  40 */     this.parent = parent;
/*  41 */     setLayout((LayoutManager)null);
/*  42 */     this.clock = new ClockPanel();
/*  43 */     this.resetButton = new ResetButton(parent);
/*  44 */     add(this.resetButton);
/*  45 */     this.omzetButton = new StandardButton("Omzet");
/*  46 */     this.omzetButton.setBackground(Color.black);
/*  47 */     this.lOmzet = new JLabel("Omzet: ");
/*  48 */     this.lOmzet.setBackground(Color.black);
/*  49 */     this.lOmzet.setForeground(Color.white);
/*  50 */     add((Component)this.omzetButton);
/*  51 */     this.omzetButton.addActionListener(this);
/*  52 */     add(this.lOmzet);
/*  53 */     this.lInKas = new JLabel("Kas: ");
/*  54 */     this.lInKas.setBackground(Color.black);
/*  55 */     this.lInKas.setForeground(Color.white);
/*  56 */     add(this.lInKas);
/*  57 */     add((Component)this.clock);
/*  58 */     addComponentListener(this);
/*     */   }
/*     */ 
/*     */   
/*     */   protected void setOmzet(long omzet) {
/*  63 */     String s = String.format("%03d", new Object[] { Long.valueOf(omzet) });
/*  64 */     this.lOmzet.setText("Omzet: € " + s.substring(0, s.length() - 2) + "." + s.substring(s.length() - 2));
/*  65 */     this.lOmzet.setFont(GfxUtil.fitFont(this.lOmzet, "Verdana", 1, this.lOmzet.getText()));
/*     */   }
/*     */   
/*     */   protected void setInKas(long inkas) {
/*  69 */     String s = String.format("%03d", new Object[] { Long.valueOf(inkas) });
/*  70 */     this.lInKas.setText("Kas: € " + s.substring(0, s.length() - 2) + "." + s.substring(s.length() - 2));
/*  71 */     this.lInKas.setFont(GfxUtil.fitFont(this.lInKas, "Verdana", 1, this.lOmzet.getText()));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void componentHidden(ComponentEvent e) {}
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void componentMoved(ComponentEvent e) {}
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void componentResized(ComponentEvent e) {
/*  88 */     int w = getWidth();
/*  89 */     int h = getHeight();
/*  90 */     this.resetButton.setBounds(0, 0, w / 5, h);
/*  91 */     this.lOmzet.setBounds(w / 5, 0, w * 3 / 10, h / 2);
/*  92 */     this.lOmzet.setFont(GfxUtil.fitFont(this.lOmzet, "Verdana", 1, this.lOmzet.getText()));
/*     */     
/*  94 */     this.lInKas.setBounds(w / 5, h / 2, w * 3 / 10, h / 2);
/*  95 */     this.lInKas.setFont(GfxUtil.fitFont(this.lInKas, "Verdana", 1, this.lInKas.getText()));
/*     */     
/*  97 */     this.omzetButton.setBounds(w / 5, 0, w * 3 / 10, h);
/*     */     
/*  99 */     this.clock.setBounds(w * 4 / 5, 0, w / 5, h);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void componentShown(ComponentEvent e) {}
/*     */ 
/*     */ 
/*     */   
/*     */   public void actionPerformed(ActionEvent e) {
/* 110 */     if (e.getSource() == this.omzetButton) {
/*     */       
/* 112 */       if (this.tOmzetTimeout != null)
/*     */       {
/* 114 */         if (this.tOmzetTimeout.isAlive()) {
/*     */           
/*     */           try {
/*     */             
/* 118 */             this.tOmzetTimeout.interrupt();
/* 119 */             this.tOmzetTimeout.join();
/*     */           }
/* 121 */           catch (Exception exception) {}
/*     */         }
/*     */       }
/*     */ 
/*     */       
/* 126 */       this.tOmzetTimeout = new OmzetTimeoutThread(this);
/* 127 */       showOmzet();
/* 128 */       this.tOmzetTimeout.start();
/*     */     } 
/*     */   }
/*     */   
/*     */   private void hideOmzet() {
/* 133 */     this.lInKas.setVisible(false);
/* 134 */     this.lOmzet.setVisible(false);
/* 135 */     this.omzetButton.setVisible(true);
/*     */   }
/*     */   
/*     */   private void showOmzet() {
/* 139 */     this.lInKas.setVisible(true);
/* 140 */     this.lOmzet.setVisible(true);
/* 141 */     this.omzetButton.setVisible(false);
/*     */   }
/*     */   
/*     */   private class OmzetTimeoutThread
/*     */     extends Thread {
/*     */     public OmzetTimeoutThread(BorrelBar parent) {
/* 147 */       this.parent = parent;
/*     */     }
/*     */     BorrelBar parent;
/*     */     
/*     */     public void run() {
/*     */       try {
/* 153 */         Thread.sleep(2000L);
/*     */       }
/* 155 */       catch (InterruptedException interruptedException) {
/*     */ 
/*     */       
/*     */       } finally {
/*     */         
/* 160 */         this.parent.hideOmzet();
/*     */       } 
/*     */     }
/*     */   }
/*     */ }


/* Location:              /Users/nicholas/Desktop/old-knt/old-jar/program.jar!/vu/storm/touch/product/BorrelBar.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */