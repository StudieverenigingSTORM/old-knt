/*     */ package vu.storm.touch.gfx;
/*     */ 
/*     */ import java.awt.Color;
/*     */ import java.awt.Font;
/*     */ import java.awt.FontMetrics;
/*     */ import java.awt.Graphics;
/*     */ import java.awt.LayoutManager;
/*     */ import java.awt.event.ActionEvent;
/*     */ import java.awt.event.ActionListener;
/*     */ import java.awt.event.ComponentEvent;
/*     */ import java.awt.event.ComponentListener;
/*     */ import javax.swing.JPanel;
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
/*     */ public class NumpadButton
/*     */   extends JPanel
/*     */   implements ComponentListener, ActionListener
/*     */ {
/*     */   StyledButton button;
/*     */   int size;
/*     */   int value;
/*     */   Numpad parent;
/*     */   
/*     */   protected NumpadButton(Numpad parent, String label, int value, int size) {
/*  33 */     this.parent = parent;
/*  34 */     this.size = size;
/*  35 */     setOpaque(false);
/*  36 */     this.value = value;
/*  37 */     this.button = new StyledButton(this, label);
/*  38 */     this.button.addActionListener(this);
/*  39 */     setLayout((LayoutManager)null);
/*  40 */     add(this.button);
/*  41 */     setBackground(new Color(255, 255, 255, 255));
/*  42 */     addComponentListener(this);
/*     */   }
/*     */   
/*     */   protected void setButtonSize(int size) {
/*  46 */     this.size = size;
/*     */   }
/*     */ 
/*     */   
/*     */   public void paint(Graphics g) {
/*  51 */     super.paint(g);
/*     */   }
/*     */   
/*     */   private class StyledButton
/*     */     extends StandardStyledButton {
/*     */     Font font;
/*     */     NumpadButton parent;
/*     */     String label;
/*     */     
/*     */     public StyledButton(NumpadButton parent, String label) {
/*  61 */       super(false);
/*  62 */       this.parent = parent;
/*  63 */       this.label = label;
/*  64 */       rescale();
/*     */     }
/*     */     
/*     */     protected void rescale() {
/*  68 */       this.font = new Font("Verdana", 0, getHeight() * 4 / 10);
/*  69 */       setFont(this.font);
/*     */     }
/*     */     
/*     */     public void paint(Graphics g) {
/*  73 */       super.paint(g);
/*  74 */       g.setFont(this.font);
/*  75 */       FontMetrics fm = g.getFontMetrics(this.font);
/*  76 */       g.drawString(this.label, (getWidth() - fm.stringWidth(this.label)) / 2, getHeight() / 2 + fm.getDescent());
/*     */     }
/*     */     
/*     */     protected void press() {
/*  80 */       if (this.lst != null) {
/*     */         
/*  82 */         ActionEvent event = new ActionEvent(this, 1001, "normal");
/*  83 */         this.lst.actionPerformed(event);
/*     */       } 
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void componentHidden(ComponentEvent e) {}
/*     */ 
/*     */ 
/*     */   
/*     */   public void componentMoved(ComponentEvent e) {}
/*     */ 
/*     */ 
/*     */   
/*     */   public void componentResized(ComponentEvent e) {
/* 100 */     this.button.setBounds(getWidth() / this.size * 10, getHeight() / this.size * 10, getWidth() * (this.size * 10 - 2) / this.size * 10, getHeight() * (this.size * 10 - 2) / this.size * 10);
/*     */     
/* 102 */     this.button.rescale();
/* 103 */     this.button.repaint();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void componentShown(ComponentEvent e) {}
/*     */ 
/*     */   
/*     */   public void actionPerformed(ActionEvent e) {
/* 112 */     this.parent.buttonPressed(this.value);
/*     */   }
/*     */ }


/* Location:              /Users/nicholas/Desktop/old-knt/old-jar/program.jar!/vu/storm/touch/gfx/NumpadButton.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */