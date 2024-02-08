/*     */ package vu.storm.touch.product;
/*     */ 
/*     */ import java.awt.Color;
/*     */ import java.awt.Component;
/*     */ import java.awt.Font;
/*     */ import java.awt.FontMetrics;
/*     */ import java.awt.Graphics;
/*     */ import java.awt.LayoutManager;
/*     */ import java.awt.event.ActionEvent;
/*     */ import java.awt.event.ActionListener;
/*     */ import java.awt.event.ComponentEvent;
/*     */ import java.awt.event.ComponentListener;
/*     */ import javax.swing.JPanel;
/*     */ import vu.storm.touch.gfx.StandardStyledButton;
/*     */ 
/*     */ 
/*     */ public class OrderUserButton
/*     */   extends JPanel
/*     */   implements ActionListener, ComponentListener
/*     */ {
/*     */   OrderPane parent;
/*     */   StyledButton button;
/*     */   
/*     */   protected OrderUserButton(OrderPane parent) {
/*  25 */     this.parent = parent;
/*  26 */     this.button = new StyledButton(this);
/*  27 */     add((Component)this.button);
/*  28 */     setBackground(new Color(128, 154, 205));
/*  29 */     setLayout((LayoutManager)null);
/*  30 */     this.button.addActionListener(this);
/*  31 */     addComponentListener(this);
/*     */   }
/*     */   
/*     */   private class StyledButton extends StandardStyledButton {
/*     */     Font font;
/*     */     Font priceFont;
/*     */     OrderUserButton parent;
/*     */     
/*     */     public StyledButton(OrderUserButton parent) {
/*  40 */       super(false);
/*  41 */       this.parent = parent;
/*  42 */       rescale();
/*     */     }
/*     */     
/*     */     protected void rescale() {
/*  46 */       this.font = new Font("Verdana", 1, this.parent.parent.parent.scaleFontSize(15));
/*  47 */       this.priceFont = new Font("Verdana", 0, this.parent.parent.parent.scaleFontSize(13));
/*     */       
/*  49 */       setFont(this.font);
/*     */     }
/*     */     
/*     */     public void paint(Graphics g) {
/*  53 */       super.paint(g);
/*  54 */       g.setFont(this.font);
/*  55 */       String priceString = String.valueOf(this.parent.parent.parent.getOrder().totalPrice());
/*  56 */       if (priceString.length() == 1) priceString = "0" + priceString; 
/*  57 */       if (priceString.length() == 2) priceString = "0" + priceString; 
/*  58 */       priceString = "€ " + priceString.substring(0, priceString.length() - 2) + "." + priceString.substring(priceString.length() - 2);
/*     */       
/*  60 */       String balanceString = String.valueOf(this.parent.parent.parent.getOrder().getUser().getBalance());
/*  61 */       if (balanceString.length() == 1) balanceString = "0" + balanceString; 
/*  62 */       if (balanceString.length() == 2) balanceString = "0" + balanceString;
/*     */       
/*  64 */       if (this.parent.parent.parent.getOrder().getUser().getBalance() == Long.MAX_VALUE) {
/*     */         
/*  66 */         balanceString = "(€ ∞)";
/*     */       }
/*     */       else {
/*     */         
/*  70 */         balanceString = "(€ " + balanceString.substring(0, balanceString.length() - 2) + "." + balanceString.substring(balanceString.length() - 2) + ")";
/*     */       } 
/*     */ 
/*     */       
/*  74 */       FontMetrics fm = g.getFontMetrics(this.font);
/*  75 */       FontMetrics fmPrice = g.getFontMetrics(this.priceFont);
/*  76 */       String orderUserName = this.parent.parent.parent.getOrder().getUser().getName();
/*  77 */       g.drawString(orderUserName, (getWidth() - fm.stringWidth(orderUserName)) / 2, fm.getHeight());
/*  78 */       g.setFont(this.priceFont);
/*  79 */       g.drawString(priceString, getWidth() / 20, fm.getHeight() + fmPrice.getHeight());
/*  80 */       g.drawString(balanceString, getWidth() - fmPrice.stringWidth(balanceString) - getWidth() / 20, fm.getHeight() + fmPrice.getHeight());
/*     */     }
/*     */     
/*     */     protected void press() {
/*  84 */       if (this.lst != null) {
/*     */         
/*  86 */         ActionEvent event = new ActionEvent(this, 1001, "normal");
/*  87 */         this.lst.actionPerformed(event);
/*     */       } 
/*     */     }
/*     */   }
/*     */   
/*     */   public void actionPerformed(ActionEvent e) {
/*  93 */     this.parent.parent.cancelOrderClicked();
/*     */   }
/*     */ 
/*     */   
/*     */   public void componentHidden(ComponentEvent e) {}
/*     */ 
/*     */   
/*     */   public void componentMoved(ComponentEvent e) {}
/*     */   
/*     */   public void componentResized(ComponentEvent e) {
/* 103 */     int w = getWidth();
/* 104 */     int h = getHeight();
/* 105 */     this.button.setBounds(w / 20, h / 10, 9 * w / 10, 8 * h / 10);
/* 106 */     this.button.rescale();
/* 107 */     this.button.repaint();
/*     */   }
/*     */   
/*     */   public void componentShown(ComponentEvent e) {}
/*     */ }


/* Location:              /Users/nicholas/Desktop/old-knt/old-jar/program.jar!/vu/storm/touch/product/OrderUserButton.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */