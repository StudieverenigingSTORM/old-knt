/*     */ package vu.storm.touch.product;
/*     */ 
/*     */ import java.awt.AWTEventMulticaster;
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
/*     */ import javax.swing.JPanel;
/*     */ import vu.storm.administration.IOrder;
/*     */ import vu.storm.touch.gfx.GfxUtil;
/*     */ import vu.storm.touch.gfx.StandardStyledButton;
/*     */ 
/*     */ 
/*     */ public class OrderStackButton
/*     */   extends JPanel
/*     */   implements ActionListener, ComponentListener
/*     */ {
/*     */   OrderStack parent;
/*     */   Rectangle beforeScroll;
/*     */   Rectangle afterScroll;
/*     */   int startX;
/*     */   int startY;
/*     */   int lastX;
/*     */   int lastY;
/*     */   boolean scrollStarted = false;
/*     */   IOrder order;
/*     */   StyledButton button;
/*  35 */   ActionListener lst = null;
/*     */ 
/*     */   
/*     */   public OrderStackButton(OrderStack parent, IOrder order) {
/*  39 */     this.order = order;
/*  40 */     this.parent = parent;
/*  41 */     this.button = new StyledButton(this, order);
/*  42 */     add((Component)this.button);
/*  43 */     setBackground(new Color(128, 154, 205));
/*  44 */     setLayout((LayoutManager)null);
/*  45 */     addComponentListener(this);
/*  46 */     this.button.addActionListener(this);
/*     */   }
/*     */   
/*     */   public void addActionListener(ActionListener l) {
/*  50 */     this.lst = AWTEventMulticaster.add(this.lst, l);
/*     */   }
/*     */   
/*     */   public void removeActionListener(ActionListener l) {
/*  54 */     this.lst = AWTEventMulticaster.remove(this.lst, l);
/*     */   }
/*     */   
/*     */   private class StyledButton extends StandardStyledButton {
/*     */     IOrder order;
/*     */     Font font;
/*     */     Font priceFont;
/*     */     OrderStackButton parent;
/*     */     
/*     */     public StyledButton(OrderStackButton parent, IOrder order) {
/*  64 */       super(true);
/*  65 */       this.parent = parent;
/*  66 */       this.order = order;
/*  67 */       rescale();
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     protected void rescale() {
/*  73 */       this.font = new Font("Verdana", 1, this.parent.parent.parent.scaleFontSize(15));
/*     */       
/*  75 */       this.priceFont = new Font("Verdana", 0, this.parent.parent.parent.scaleFontSize(13));
/*  76 */       setFont(this.font);
/*     */     }
/*     */ 
/*     */     
/*     */     public void paint(Graphics g) {
/*  81 */       super.paint(g);
/*  82 */       g.setFont(this.font);
/*     */ 
/*     */       
/*  85 */       if (this.order.numLineItems() != 0)
/*     */       {
/*     */ 
/*     */         
/*  89 */         if (this.order.numLineItems() == 1) {
/*     */           
/*  91 */           String line1 = String.format("%d %s", new Object[] { Long.valueOf(this.order.getLineItem(0).getAmount()), this.order.getLineItem(0).getProduct().getName() });
/*  92 */           g.setFont(GfxUtil.fitFont(g, "Verdana", 0, line1, getHeight() - 6, getWidth() - 6));
/*  93 */           FontMetrics fm = g.getFontMetrics();
/*  94 */           g.drawString(line1, 6, getHeight() / 2 - fm.getHeight() / 2 + fm.getAscent());
/*     */         }
/*  96 */         else if (this.order.numLineItems() == 2) {
/*     */           
/*  98 */           String line1 = String.format("%d %s", new Object[] { Long.valueOf(this.order.getLineItem(0).getAmount()), this.order.getLineItem(0).getProduct().getName() });
/*  99 */           String line2 = String.format("%d %s", new Object[] { Long.valueOf(this.order.getLineItem(1).getAmount()), this.order.getLineItem(1).getProduct().getName() });
/* 100 */           g.setFont(GfxUtil.fitFont(g, "Verdana", 0, line1, getHeight() / 2 - 3, getWidth() - 6));
/* 101 */           FontMetrics fm = g.getFontMetrics();
/* 102 */           g.drawString(line1, 6, 3 + (getHeight() / 2 - 6) / 2 - fm.getHeight() / 2 + fm.getAscent());
/* 103 */           g.setFont(GfxUtil.fitFont(g, "Verdana", 0, line2, getHeight() / 2 - 3, getWidth() - 6));
/* 104 */           fm = g.getFontMetrics();
/* 105 */           g.drawString(line2, 6, getHeight() / 2 + (getHeight() / 2 - 6) / 2 - fm.getHeight() / 2 + fm.getAscent());
/*     */         }
/* 107 */         else if (this.order.numLineItems() > 2) {
/*     */           
/* 109 */           String line1 = String.format("%d %s", new Object[] { Long.valueOf(this.order.getLineItem(0).getAmount()), this.order.getLineItem(0).getProduct().getName() });
/* 110 */           String line2 = String.format("%d %s", new Object[] { Long.valueOf(this.order.getLineItem(1).getAmount()), this.order.getLineItem(1).getProduct().getName() });
/* 111 */           g.setFont(GfxUtil.fitFont(g, "Verdana", 0, line1, getHeight() / 2 - 3, getWidth() * 9 / 20 - 3));
/* 112 */           FontMetrics fm = g.getFontMetrics();
/* 113 */           g.drawString(line1, 6, 3 + (getHeight() / 2 - 6) / 2 - fm.getHeight() / 2 + fm.getAscent());
/* 114 */           g.setFont(GfxUtil.fitFont(g, "Verdana", 0, line2, getHeight() / 2 - 3, getWidth() * 9 / 20 - 3));
/* 115 */           fm = g.getFontMetrics();
/* 116 */           g.drawString(line2, 6, getHeight() / 2 + (getHeight() / 2 - 6) / 2 - fm.getHeight() / 2 + fm.getAscent());
/* 117 */           if (this.order.numLineItems() > 2) {
/*     */             
/* 119 */             String line3 = String.format("%d %s", new Object[] { Long.valueOf(this.order.getLineItem(2).getAmount()), this.order.getLineItem(2).getProduct().getName() });
/* 120 */             g.setFont(GfxUtil.fitFont(g, "Verdana", 0, line3, getHeight() / 2 - 3, getWidth() / 2 - 3));
/* 121 */             fm = g.getFontMetrics();
/* 122 */             g.drawString(line3, getWidth() / 2, 3 + (getHeight() / 2 - 6) / 2 - fm.getHeight() / 2 + fm.getAscent());
/* 123 */             if (this.order.numLineItems() == 4) {
/*     */ 
/*     */               
/* 126 */               String line4 = String.format("%d %s", new Object[] { Long.valueOf(this.order.getLineItem(3).getAmount()), this.order.getLineItem(3).getProduct().getName() });
/* 127 */               g.setFont(GfxUtil.fitFont(g, "Verdana", 0, line4, getHeight() / 2 - 3, getWidth() / 2 - 3));
/* 128 */               fm = g.getFontMetrics();
/* 129 */               g.drawString(line4, getWidth() / 2, getHeight() / 2 + (getHeight() / 2 - 6) / 2 - fm.getHeight() / 2 + fm.getAscent());
/*     */             }
/* 131 */             else if (this.order.numLineItems() > 4) {
/*     */               
/* 133 */               String line4 = "...";
/* 134 */               g.drawString(line4, getWidth() / 2, getHeight() / 2 + (getHeight() / 2 - 6) / 2 - fm.getHeight() / 2 + fm.getAscent());
/*     */             } 
/*     */           } 
/*     */         }  } 
/*     */     }
/*     */     
/*     */     protected void press() {
/* 141 */       if (this.lst != null)
/*     */       {
/* 143 */         this.lst.actionPerformed(new ActionEvent(this, 0, ""));
/*     */       }
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   protected IOrder getOrder() {
/* 150 */     return this.order;
/*     */   }
/*     */   
/*     */   public void actionPerformed(ActionEvent e) {
/* 154 */     if (this.lst != null)
/*     */     {
/* 156 */       this.lst.actionPerformed(new ActionEvent(this, 0, ""));
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
/*     */   public void componentMoved(ComponentEvent arg0) {}
/*     */ 
/*     */ 
/*     */   
/*     */   public void componentResized(ComponentEvent arg0) {
/* 172 */     int w = getWidth();
/* 173 */     int h = getHeight();
/* 174 */     this.button.setBounds(w / 20, h / 10, 9 * w / 10, 8 * h / 10);
/* 175 */     this.button.rescale();
/* 176 */     this.button.repaint();
/*     */   }
/*     */   
/*     */   public void componentShown(ComponentEvent arg0) {}
/*     */ }


/* Location:              /Users/nicholas/Desktop/old-knt/old-jar/program.jar!/vu/storm/touch/product/OrderStackButton.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */