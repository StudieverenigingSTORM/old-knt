/*     */ package vu.storm.touch.user;
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
/*     */ import java.awt.event.MouseListener;
/*     */ import javax.swing.JComponent;
/*     */ import javax.swing.JPanel;
/*     */ import vu.storm.administration.IUser;
/*     */ import vu.storm.touch.gfx.GfxUtil;
/*     */ import vu.storm.touch.gfx.StandardStyledButton;
/*     */ 
/*     */ public class UserButton
/*     */   extends JPanel implements ActionListener, ComponentListener {
/*     */   UserColumn parent;
/*     */   Rectangle beforeScroll;
/*     */   Rectangle afterScroll;
/*     */   int startX;
/*     */   int startY;
/*     */   int lastX;
/*     */   int lastY;
/*     */   boolean scrollStarted = false;
/*     */   IUser user;
/*     */   StyledButton button;
/*     */   
/*     */   public UserButton(UserColumn parent, IUser user) {
/*  35 */     this.user = user;
/*  36 */     this.parent = parent;
/*  37 */     this.button = new StyledButton(this, user);
/*  38 */     add((Component)this.button);
/*  39 */     setBackground(new Color(128, 154, 205));
/*  40 */     setLayout((LayoutManager)null);
/*  41 */     addComponentListener(this);
/*  42 */     this.button.addActionListener(this);
/*     */   }
/*     */   
/*     */   private class StyledButton extends StandardStyledButton {
/*     */     IUser user;
/*     */     Font font;
/*     */     Font priceFont;
/*     */     UserButton parent;
/*     */     
/*     */     public StyledButton(UserButton parent, IUser user) {
/*  52 */       super(false);
/*  53 */       this.parent = parent;
/*  54 */       this.user = user;
/*  55 */       if (user.isBorrelCie()) {
/*     */         
/*  57 */         this.normalBorder = Color.white;
/*  58 */         this.normalFront = new Color(90, 176, 255);
/*  59 */         this.normalBack = Color.black;
/*     */       } 
/*  61 */       rescale();
/*  62 */       addMouseListener((MouseListener)this);
/*     */     }
/*     */     
/*     */     protected void rescale() {
/*  66 */       this.font = GfxUtil.fitFont((JComponent)this, "Verdana", 1, this.user.getName(), getHeight() / 2, getWidth() - 6);
/*  67 */       if (this.font == null)
/*  68 */         this.font = new Font("Verdana", 1, this.parent.parent.parent.scaleFontSize(15)); 
/*  69 */       this.priceFont = new Font("Verdana", 0, this.parent.parent.parent.scaleFontSize(13));
/*  70 */       setFont(this.font);
/*     */     }
/*     */     
/*     */     public void paint(Graphics g) {
/*  74 */       super.paint(g);
/*  75 */       g.setFont(this.font);
/*  76 */       String priceString = String.valueOf(this.user.getBalance());
/*  77 */       if (priceString.length() == 1) priceString = "0" + priceString; 
/*  78 */       if (priceString.length() == 2) priceString = "0" + priceString; 
/*  79 */       priceString = "€ " + priceString.substring(0, priceString.length() - 2) + "." + priceString.substring(priceString.length() - 2);
/*  80 */       FontMetrics fm = g.getFontMetrics(this.font);
/*  81 */       FontMetrics fmPrice = g.getFontMetrics(this.priceFont);
/*     */       
/*  83 */       g.drawString(this.user.getName(), (getWidth() - fm.stringWidth(this.user.getName())) / 2, fm.getHeight());
/*  84 */       g.setFont(this.priceFont);
/*  85 */       if (!this.user.isBorrelUser()) g.drawString(priceString, getWidth() - fmPrice.stringWidth(priceString) - getWidth() / 20, fm.getHeight() + fmPrice.getHeight()); 
/*     */     }
/*     */     
/*     */     protected void press() {
/*  89 */       repaint();
/*  90 */       if (this.lst != null) {
/*     */         
/*  92 */         ActionEvent event = new ActionEvent(this, 1001, "normal");
/*  93 */         this.lst.actionPerformed(event);
/*     */       } 
/*     */     }
/*     */   }
/*     */   
/*     */   public void actionPerformed(ActionEvent e) {
/*  99 */     this.parent.parent.userClicked(this.user);
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
/* 111 */     this.button.setBounds(w / 20, h / 10, 9 * w / 10, 8 * h / 10);
/* 112 */     this.button.rescale();
/* 113 */     this.button.repaint();
/*     */   }
/*     */ 
/*     */   
/*     */   public void componentShown(ComponentEvent arg0) {}
/*     */   
/*     */   protected boolean matchesFilter(String filter) {
/* 120 */     return this.user.matchesSearch(filter);
/*     */   }
/*     */ }


/* Location:              /Users/nicholas/Desktop/old-knt/old-jar/program.jar!/vu/storm/touch/user/UserButton.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */