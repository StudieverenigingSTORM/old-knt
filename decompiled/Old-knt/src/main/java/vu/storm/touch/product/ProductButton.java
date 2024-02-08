/*     */ package vu.storm.touch.product;
/*     */ 
/*     */ import java.awt.Color;
/*     */ import java.awt.Component;
/*     */ import java.awt.Font;
/*     */ import java.awt.FontMetrics;
/*     */ import java.awt.Graphics;
/*     */ import java.awt.LayoutManager;
/*     */ import java.awt.Rectangle;
/*     */ import java.awt.event.ActionEvent;
/*     */ import java.awt.event.ActionListener;
/*     */ import java.awt.event.ComponentEvent;
/*     */ import java.awt.event.ComponentListener;
/*     */ import javax.swing.JComponent;
/*     */ import javax.swing.JPanel;
/*     */ import vu.storm.administration.IProduct;
/*     */ import vu.storm.touch.gfx.GfxUtil;
/*     */ import vu.storm.touch.gfx.LongPressButton;
/*     */ 
/*     */ public class ProductButton
/*     */   extends JPanel implements ActionListener, ComponentListener {
/*     */   NewProductColumn parent;
/*     */   Rectangle beforeScroll;
/*     */   Rectangle afterScroll;
/*     */   int startX;
/*     */   int startY;
/*     */   int lastX;
/*     */   int lastY;
/*     */   boolean scrollStarted = false;
/*     */   IProduct product;
/*     */   StyledButton button;
/*     */   
/*     */   public ProductButton(NewProductColumn parent, IProduct product) {
/*  34 */     this.product = product;
/*  35 */     this.parent = parent;
/*  36 */     this.button = new StyledButton(this, product);
/*  37 */     add((Component)this.button);
/*  38 */     setBackground(new Color(128, 154, 205));
/*  39 */     setLayout((LayoutManager)null);
/*  40 */     addComponentListener(this);
/*  41 */     this.button.addActionListener(this);
/*     */   }
/*     */   
/*     */   private class StyledButton extends LongPressButton {
/*     */     IProduct product;
/*     */     Font font;
/*     */     Font priceFont;
/*     */     ProductButton parent;
/*     */     
/*     */     public StyledButton(ProductButton parent, IProduct product) {
/*  51 */       super(false);
/*  52 */       this.parent = parent;
/*  53 */       this.product = product;
/*  54 */       rescale();
/*     */     }
/*     */     
/*     */     protected void rescale() {
/*  58 */       this.font = GfxUtil.fitFont((JComponent)this, "Verdana", 1, this.product.getName(), getHeight() / 2, getWidth() - 6);
/*  59 */       if (this.font == null) {
/*  60 */         this.font = new Font("Verdana", 1, this.parent.parent.parent.scaleFontSize(15));
/*     */       }
/*  62 */       this.priceFont = new Font("Verdana", 0, this.parent.parent.parent.scaleFontSize(13));
/*  63 */       setFont(this.font);
/*     */     }
/*     */ 
/*     */     
/*     */     public void paint(Graphics g) {
/*  68 */       super.paint(g);
/*  69 */       g.setFont(this.font);
/*  70 */       String priceString = String.valueOf(this.product.getPrice());
/*  71 */       if (priceString.length() == 1) priceString = "0" + priceString; 
/*  72 */       if (priceString.length() == 2) priceString = "0" + priceString; 
/*  73 */       priceString = "€ " + priceString.substring(0, priceString.length() - 2) + "." + priceString.substring(priceString.length() - 2);
/*  74 */       FontMetrics fm = g.getFontMetrics(this.font);
/*  75 */       FontMetrics fmPrice = g.getFontMetrics(this.priceFont);
/*     */       
/*  77 */       g.drawString(this.product.getName(), (getWidth() - fm.stringWidth(this.product.getName())) / 2, fm.getHeight());
/*  78 */       g.setFont(this.priceFont);
/*  79 */       g.drawString(priceString, getWidth() - fmPrice.stringWidth(priceString) - getWidth() / 20, fm.getHeight() + fmPrice.getHeight());
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void rescale() {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void actionPerformed(ActionEvent e) {
/*  97 */     if (e.getActionCommand().equals("long")) {
/*     */ 
/*     */       
/* 100 */       this.parent.parent.productLongClicked(this.product);
/*     */     }
/*     */     else {
/*     */       
/* 104 */       this.parent.parent.productClicked(this.product);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void componentHidden(ComponentEvent arg0) {}
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void componentMoved(ComponentEvent arg0) {}
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void componentResized(ComponentEvent arg0) {
/* 122 */     int w = getWidth();
/* 123 */     int h = getHeight();
/* 124 */     this.button.setBounds(w / 20, h / 10, 9 * w / 10, 8 * h / 10);
/* 125 */     this.button.rescale();
/* 126 */     this.button.repaint();
/*     */   }
/*     */   
/*     */   public void componentShown(ComponentEvent arg0) {}
/*     */ }


/* Location:              /Users/nicholas/Desktop/old-knt/old-jar/program.jar!/vu/storm/touch/product/ProductButton.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */