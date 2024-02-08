/*     */ package vu.storm.touch.gfx;
/*     */ 
/*     */ import java.awt.Color;
/*     */ import java.awt.Graphics;
/*     */ import java.awt.LayoutManager;
/*     */ import java.awt.event.ActionEvent;
/*     */ import java.awt.event.ActionListener;
/*     */ import java.awt.event.ComponentEvent;
/*     */ import java.awt.event.ComponentListener;
/*     */ import java.awt.event.MouseEvent;
/*     */ import javax.swing.JPanel;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class PageButton
/*     */   extends JPanel
/*     */   implements ComponentListener
/*     */ {
/*     */   StyledButton button;
/*     */   
/*     */   public PageButton(boolean upDown, boolean upOrLeft) {
/*  24 */     this(upDown, upOrLeft, false);
/*     */   }
/*     */   
/*     */   public PageButton(boolean upDown, boolean upOrLeft, boolean inverted) {
/*  28 */     this.button = new StyledButton(upDown, upOrLeft, inverted);
/*  29 */     add(this.button);
/*  30 */     setBackground(new Color(128, 154, 205));
/*  31 */     setLayout((LayoutManager)null);
/*  32 */     addComponentListener(this);
/*     */   }
/*     */   
/*     */   public void addActionListener(ActionListener l) {
/*  36 */     this.button.addActionListener(l);
/*     */   }
/*     */   
/*     */   public void removeActionListener(ActionListener l) {
/*  40 */     this.button.removeActionListener(l);
/*     */   }
/*     */   
/*     */   private class StyledButton
/*     */     extends StandardStyledButton {
/*     */     boolean enabled;
/*     */     boolean upDown;
/*     */     boolean upOrLeft;
/*     */     
/*     */     public StyledButton(boolean upDown, boolean upOrLeft, boolean inverted) {
/*  50 */       super(inverted);
/*  51 */       this.upDown = upDown;
/*  52 */       this.upOrLeft = upOrLeft;
/*  53 */       this.enabled = true;
/*     */     }
/*     */     
/*     */     public void setEnabled(boolean enabled) {
/*  57 */       if (this.enabled != enabled) {
/*     */         
/*  59 */         this.enabled = enabled;
/*  60 */         repaint();
/*     */       } 
/*     */     }
/*     */     
/*     */     public void paint(Graphics g) {
/*  65 */       super.paint(g);
/*  66 */       int w = getWidth();
/*  67 */       int h = getHeight();
/*  68 */       if (!this.enabled) g.setColor(Color.gray);
/*     */       
/*  70 */       if (this.upDown) {
/*     */         
/*  72 */         g.fillPolygon(new int[] { w / 10, w / 2, w * 9 / 10 }, new int[] { this.upOrLeft ? (h * 9 / 10) : (h / 10), this.upOrLeft ? (h / 10) : (h * 9 / 10), this.upOrLeft ? (h * 9 / 10) : (h / 10) }, 3);
/*     */       }
/*     */       else {
/*     */         
/*  76 */         g.fillPolygon(new int[] { this.upOrLeft ? (w * 9 / 10) : (w / 10), this.upOrLeft ? (w / 10) : (w * 9 / 10), this.upOrLeft ? (w * 9 / 10) : (w / 10) }, new int[] { h / 10, h / 2, h * 9 / 10 }, 3);
/*     */       } 
/*     */     }
/*     */     
/*     */     public void mousePressed(MouseEvent arg0) {
/*  81 */       if (this.enabled) super.mousePressed(arg0); 
/*     */     }
/*     */     
/*     */     public void mouseReleased(MouseEvent arg0) {
/*  85 */       if (this.enabled && this.pressed) super.mouseReleased(arg0); 
/*     */     }
/*     */     
/*     */     protected void press() {
/*  89 */       if (this.enabled && this.lst != null) {
/*     */ 
/*     */         
/*  92 */         ActionEvent event = new ActionEvent(this, 1001, this.upOrLeft ? "prev" : "next");
/*  93 */         this.lst.actionPerformed(event);
/*     */       } 
/*     */     }
/*     */   }
/*     */   
/*     */   public void setEnabled(boolean enabled) {
/*  99 */     this.button.setEnabled(enabled);
/*     */   }
/*     */ 
/*     */   
/*     */   public void componentHidden(ComponentEvent arg0) {}
/*     */ 
/*     */   
/*     */   public void componentMoved(ComponentEvent arg0) {}
/*     */   
/*     */   public void componentResized(ComponentEvent arg0) {
/* 109 */     int w = getWidth();
/* 110 */     int h = getHeight();
/* 111 */     this.button.setBounds(w / 6, h / 10, w * 2 / 3, 8 * h / 10);
/* 112 */     this.button.repaint();
/*     */   }
/*     */   
/*     */   public void componentShown(ComponentEvent arg0) {}
/*     */ }


/* Location:              /Users/nicholas/Desktop/old-knt/old-jar/program.jar!/vu/storm/touch/gfx/PageButton.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */