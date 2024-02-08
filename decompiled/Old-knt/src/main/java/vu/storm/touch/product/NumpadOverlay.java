/*     */ package vu.storm.touch.product;
/*     */ 
/*     */ import java.awt.Color;
/*     */ import java.awt.Component;
/*     */ import java.awt.Font;
/*     */ import java.awt.FontMetrics;
/*     */ import java.awt.Graphics;
/*     */ import java.awt.LayoutManager;
/*     */ import java.awt.event.ComponentEvent;
/*     */ import java.awt.event.ComponentListener;
/*     */ import java.awt.event.MouseAdapter;
/*     */ import java.awt.event.MouseEvent;
/*     */ import java.awt.event.MouseListener;
/*     */ import javax.swing.JPanel;
/*     */ import vu.storm.administration.IProduct;
/*     */ import vu.storm.touch.gfx.Numpad;
/*     */ import vu.storm.touch.gfx.NumpadListener;
/*     */ import vu.storm.touch.gfx.WordWrap;
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
/*     */ 
/*     */ public class NumpadOverlay
/*     */   extends JPanel
/*     */   implements ComponentListener, MouseListener
/*     */ {
/*     */   ProductPanel productPanel;
/*     */   IProduct product;
/*     */   int amount;
/*     */   int prevAmount;
/*     */   
/*     */   private class ProductPanel
/*     */     extends JPanel
/*     */     implements NumpadListener, ComponentListener
/*     */   {
/*     */     NumpadOverlay parent;
/*     */     Numpad numpad;
/*     */     Font f;
/*     */     Font smallF;
/*     */     
/*     */     public ProductPanel(NumpadOverlay parent) {
/*  61 */       this.parent = parent;
/*  62 */       this.numpad = new Numpad();
/*     */       
/*  64 */       setLayout((LayoutManager)null);
/*  65 */       add((Component)this.numpad);
/*  66 */       this.f = new Font("Verdana", 0, 15);
/*  67 */       this.smallF = new Font("Verdana", 0, 8);
/*  68 */       setBackground(new Color(255, 255, 255, 255));
/*  69 */       addComponentListener(this);
/*  70 */       this.numpad.addNumpadListener(this);
/*  71 */       addMouseListener(new MouseAdapter()
/*     */           {
/*     */           
/*     */           });
/*     */     }
/*     */     
/*     */     public void numpadPressed(Numpad sender, int code) {
/*  78 */       if (code == -1) {
/*     */         
/*  80 */         NumpadOverlay.this.amount /= 10;
/*  81 */         repaint();
/*     */       }
/*  83 */       else if (code == -2) {
/*     */         
/*  85 */         if (!NumpadOverlay.this.amountChanged)
/*     */         {
/*  87 */           this.parent.parent.numpadCancel();
/*     */         }
/*     */         else
/*     */         {
/*  91 */           this.parent.parent.numpadResult(this.parent.product, NumpadOverlay.this.amount);
/*     */         }
/*     */       
/*  94 */       } else if (code >= 0 && NumpadOverlay.this.amount < 1000) {
/*     */         
/*  96 */         NumpadOverlay.this.amountChanged = true;
/*  97 */         NumpadOverlay.this.amount *= 10;
/*  98 */         NumpadOverlay.this.amount += code;
/*  99 */         repaint();
/*     */       } 
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public void componentHidden(ComponentEvent e) {}
/*     */ 
/*     */ 
/*     */     
/*     */     public void componentMoved(ComponentEvent e) {}
/*     */ 
/*     */ 
/*     */     
/*     */     public void componentResized(ComponentEvent e) {
/* 115 */       this.numpad.setBounds(getWidth() / 2, 0, getWidth() / 2, getHeight());
/* 116 */       this.f = new Font("Verdana", 0, getHeight() / 10);
/* 117 */       this.smallF = new Font("Verdana", 0, getHeight() / 20);
/* 118 */       repaint();
/*     */     }
/*     */     
/*     */     public void componentShown(ComponentEvent e) {
/* 122 */       this.numpad.takeKeyboard();
/*     */     }
/*     */ 
/*     */     
/*     */     public void paint(Graphics g) {
/* 127 */       super.paint(g);
/*     */       
/* 129 */       g.setFont(this.f);
/* 130 */       FontMetrics fm = g.getFontMetrics();
/* 131 */       g.setColor(Color.black);
/* 132 */       if (this.parent.product != null) {
/*     */         
/* 134 */         int y = 0;
/* 135 */         int lines = WordWrap.drawString(g, this.parent.product.getName(), 0, y + fm.getAscent(), getWidth() / 2);
/* 136 */         y = lines * fm.getHeight();
/* 137 */         g.setFont(this.smallF);
/* 138 */         fm = g.getFontMetrics();
/* 139 */         WordWrap.drawString(g, this.parent.product.getDescription(), 0, y + fm.getAscent(), getWidth() / 2);
/* 140 */         g.setFont(this.f);
/* 141 */         fm = g.getFontMetrics();
/*     */       } 
/* 143 */       int ybase = getHeight() / 2;
/* 144 */       int yfontbase = ybase + fm.getAscent();
/* 145 */       g.setColor(Color.black);
/* 146 */       g.drawString("Vorig aantal", 0, yfontbase);
/* 147 */       g.setColor(Color.black);
/* 148 */       g.drawRect(0, ybase + fm.getHeight(), getWidth() / 2 - 2, fm.getHeight());
/* 149 */       g.setColor(new Color(128, 128, 128));
/* 150 */       g.drawString(String.valueOf(this.parent.prevAmount), 0, yfontbase + fm.getHeight());
/* 151 */       g.setColor(Color.black);
/* 152 */       g.drawString("Aantal", 0, yfontbase + 2 * fm.getHeight());
/* 153 */       g.setColor(Color.black);
/* 154 */       g.drawRect(0, ybase + fm.getHeight() * 3, getWidth() / 2 - 2, fm.getHeight());
/* 155 */       g.setColor(new Color(128, 128, 128));
/* 156 */       g.drawString(String.valueOf(this.parent.amount), 0, yfontbase + fm.getHeight() * 3);
/*     */     }
/*     */   } boolean amountChanged = false; ProductScreen parent;
/*     */   public NumpadOverlay(ProductScreen parent) {
/*     */     this.parent = parent;
/*     */     setBackground(new Color(0, 0, 0, 128));
/*     */     this.productPanel = new ProductPanel(this);
/*     */     setLayout((LayoutManager)null);
/*     */     add(this.productPanel);
/*     */     addComponentListener(this);
/*     */     addMouseListener(this);
/*     */   }
/*     */   public void setProduct(IProduct product) {
/*     */     this.product = product;
/*     */   }
/*     */   
/* 172 */   public void componentResized(ComponentEvent e) { this.productPanel.setBounds(getWidth() / 4, getHeight() / 4, getWidth() / 2, getHeight() / 2);
/* 173 */     repaint(); }
/*     */   public void setAmount(int amount) { this.prevAmount = amount;
/*     */     this.amountChanged = false;
/*     */     this.amount = 0; } public void componentShown(ComponentEvent e) {
/* 177 */     this.productPanel.componentShown(e);
/*     */   }
/*     */   public void componentHidden(ComponentEvent e) {}
/*     */   public void componentMoved(ComponentEvent e) {}
/*     */   public void mouseClicked(MouseEvent e) {
/* 182 */     this.parent.numpadCancel();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void mouseEntered(MouseEvent e) {}
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void mouseExited(MouseEvent e) {}
/*     */ 
/*     */ 
/*     */   
/*     */   public void mousePressed(MouseEvent e) {}
/*     */ 
/*     */ 
/*     */   
/*     */   public void mouseReleased(MouseEvent e) {}
/*     */ 
/*     */ 
/*     */   
/*     */   public void paint(Graphics g) {
/* 206 */     super.paint(g);
/*     */   }
/*     */ }


/* Location:              /Users/nicholas/Desktop/old-knt/old-jar/program.jar!/vu/storm/touch/product/NumpadOverlay.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */