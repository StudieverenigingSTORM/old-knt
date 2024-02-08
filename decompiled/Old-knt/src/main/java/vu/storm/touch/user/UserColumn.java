/*     */ package vu.storm.touch.user;
/*     */ 
/*     */ import java.awt.Color;
/*     */ import java.awt.Component;
/*     */ import java.awt.LayoutManager;
/*     */ import java.awt.event.ActionListener;
/*     */ import java.awt.event.ComponentEvent;
/*     */ import java.awt.event.ComponentListener;
/*     */ import javax.swing.JComponent;
/*     */ import javax.swing.JPanel;
/*     */ import vu.storm.administration.IDatabase;
/*     */ import vu.storm.administration.IUser;
/*     */ import vu.storm.touch.gfx.ButtonProvider;
/*     */ import vu.storm.touch.gfx.PageButton;
/*     */ import vu.storm.touch.gfx.TouchColumn;
/*     */ 
/*     */ public class UserColumn
/*     */   extends JPanel
/*     */   implements ButtonProvider, ComponentListener {
/*     */   TouchColumn touchColumn;
/*     */   IDatabase db;
/*     */   UserScreen parent;
/*     */   PageButton upButton;
/*     */   PageButton downButton;
/*     */   IUser[] users;
/*     */   
/*     */   public UserColumn(UserScreen parent, IDatabase db) {
/*  28 */     this.parent = parent;
/*  29 */     this.db = db;
/*  30 */     this.upButton = new PageButton(true, true);
/*  31 */     this.downButton = new PageButton(true, false);
/*  32 */     this.users = db.getAllUsers();
/*  33 */     this.touchColumn = new TouchColumn(this, 0.14D, 1.0D);
/*  34 */     add((Component)this.touchColumn);
/*  35 */     setLayout((LayoutManager)null);
/*  36 */     this.touchColumn.setBounds(0, 0, getWidth(), getHeight());
/*  37 */     addComponentListener(this);
/*  38 */     setBackground(Color.gray);
/*     */   }
/*     */   
/*     */   public void rereadData() {
/*  42 */     this.users = this.db.getAllUsers();
/*  43 */     this.touchColumn.rereadButtons();
/*  44 */     repaint();
/*     */   }
/*     */   
/*     */   public void rereadFilter() {
/*  48 */     this.touchColumn.rereadFilter();
/*     */   }
/*     */   
/*     */   public void setFilter(String search) {
/*  52 */     this.touchColumn.setFilter(search);
/*     */   }
/*     */ 
/*     */   
/*     */   public JComponent createButton(int index) {
/*  57 */     return new UserButton(this, this.users[index]);
/*     */   }
/*     */   
/*     */   public JComponent createNextPageButton(ActionListener actionListener) {
/*  61 */     this.downButton.addActionListener(actionListener);
/*  62 */     return (JComponent)this.downButton;
/*     */   }
/*     */   
/*     */   public JComponent createPrevPageButton(ActionListener actionListener) {
/*  66 */     this.upButton.addActionListener(actionListener);
/*  67 */     return (JComponent)this.upButton;
/*     */   }
/*     */ 
/*     */   
/*     */   public void destroyButton(JComponent button) {}
/*     */ 
/*     */   
/*     */   public int numButtons() {
/*  75 */     return this.users.length;
/*     */   }
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
/*     */   public void componentResized(ComponentEvent arg0) {
/*  88 */     this.touchColumn.setBounds(0, 0, getWidth(), getHeight());
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void componentShown(ComponentEvent arg0) {}
/*     */ 
/*     */   
/*     */   public boolean matchesFilter(Object filter, JComponent button) {
/*  97 */     if (!(filter instanceof String) && filter != null) return false; 
/*  98 */     if (!(button instanceof UserButton)) return false;
/*     */     
/* 100 */     return (((UserButton)button).matchesFilter((String)filter) && ((UserButton)button).user.isBorrelUser() == this.parent.isBorrelMode);
/*     */   }
/*     */ }


/* Location:              /Users/nicholas/Desktop/old-knt/old-jar/program.jar!/vu/storm/touch/user/UserColumn.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */