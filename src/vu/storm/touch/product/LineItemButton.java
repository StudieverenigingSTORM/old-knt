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
/*     */ import java.awt.event.MouseListener;
/*     */ import javax.swing.JPanel;
/*     */ import vu.storm.administration.IOrderLine;
/*     */ import vu.storm.touch.gfx.LongPressButton;
/*     */ 
/*     */ public class LineItemButton
/*     */   extends JPanel implements ActionListener, ComponentListener {
/*     */   OrderPane parent;
/*     */   IOrderLine lineItem;
/*     */   StyledButton button;
/*     */   
/*     */   protected LineItemButton(OrderPane parent, IOrderLine lineItem) {
/*  25 */     this.parent = parent;
/*  26 */     this.lineItem = lineItem;
/*  27 */     this.button = new StyledButton(this, lineItem);
/*  28 */     add((Component)this.button);
/*  29 */     setBackground(new Color(128, 154, 205));
/*  30 */     setLayout((LayoutManager)null);
/*  31 */     this.button.addActionListener(this);
/*  32 */     addComponentListener(this);
/*     */   }
/*     */   
/*     */   private class StyledButton
/*     */     extends LongPressButton {
/*     */     IOrderLine lineItem;
/*     */     Font font;
/*     */     Font priceFont;
/*     */     LineItemButton parent;
/*     */     
/*     */     public StyledButton(LineItemButton parent, IOrderLine lineItem) {
/*  43 */       this.parent = parent;
/*  44 */       this.lineItem = lineItem;
/*  45 */       rescale();
/*  46 */       addMouseListener((MouseListener)this);
/*     */     }
/*     */     
/*     */     protected void rescale() {
/*  50 */       this.font = new Font("Verdana", 1, this.parent.parent.parent.scaleFontSize(15));
/*  51 */       this.priceFont = new Font("Verdana", 0, this.parent.parent.parent.scaleFontSize(13));
/*  52 */       setFont(this.font);
/*     */     }
/*     */ 
/*     */     
/*     */     public void paint(Graphics g) {
/*  57 */       g.setColor(!this.pressed ? Color.black : Color.white);
/*  58 */       g.fillRoundRect(0, 0, getWidth() - 1, getHeight() - 1, getHeight() / 4, getHeight() / 4);
/*  59 */       g.setColor(!this.pressed ? Color.white : Color.black);
/*  60 */       g.fillRoundRect(2, 2, getWidth() - 5, getHeight() - 5, getHeight() / 4, getHeight() / 4);
/*  61 */       g.setFont(this.font);
/*  62 */       String priceString = String.valueOf(this.lineItem.getProduct().getPrice() * this.lineItem.getAmount());
/*  63 */       if (priceString.length() == 1) priceString = "0" + priceString; 
/*  64 */       if (priceString.length() == 2) priceString = "0" + priceString; 
/*  65 */       priceString = "€ " + priceString.substring(0, priceString.length() - 2) + "." + priceString.substring(priceString.length() - 2);
/*  66 */       String amountString = String.valueOf(String.valueOf(this.lineItem.getAmount())) + "x";
/*  67 */       FontMetrics fm = g.getFontMetrics(this.font);
/*  68 */       FontMetrics fmPrice = g.getFontMetrics(this.priceFont);
/*  69 */       g.setColor(!this.pressed ? Color.black : Color.white);
/*  70 */       g.drawString(this.lineItem.getProduct().getName(), (getWidth() - fm.stringWidth(this.lineItem.getProduct().getName())) / 2, fm.getHeight());
/*  71 */       g.setFont(this.priceFont);
/*  72 */       g.drawString(amountString, getWidth() / 20, fm.getHeight() + fmPrice.getHeight());
/*  73 */       g.drawString(priceString, getWidth() - fmPrice.stringWidth(priceString) - getWidth() / 20, fm.getHeight() + fmPrice.getHeight());
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public void actionPerformed(ActionEvent e) {
/*  79 */     if (e.getActionCommand().equals("normal")) {
/*     */ 
/*     */       
/*  82 */       this.parent.parent.lineItemClicked(this.lineItem);
/*     */     }
/*  84 */     else if (e.getActionCommand().equals("long")) {
/*     */ 
/*     */       
/*  87 */       this.parent.parent.lineItemLongClicked(this.lineItem);
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
/* 103 */     int w = getWidth();
/* 104 */     int h = getHeight();
/* 105 */     this.button.rescale();
/* 106 */     this.button.setBounds(w / 20, h / 10, 9 * w / 10, 8 * h / 10);
/* 107 */     this.button.repaint();
/*     */   }
/*     */   
/*     */   public void componentShown(ComponentEvent e) {}
/*     */ }


/* Location:              /Users/nicholas/Desktop/old-knt/old-jar/program.jar!/vu/storm/touch/product/LineItemButton.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */