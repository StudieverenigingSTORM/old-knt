/*     */ package vu.storm.touch.user;
/*     */ 
/*     */ import java.awt.Component;
/*     */ import java.awt.Image;
/*     */ import java.awt.LayoutManager;
/*     */ import java.awt.Toolkit;
/*     */ import java.awt.event.ComponentEvent;
/*     */ import java.awt.event.ComponentListener;
/*     */ import javax.swing.JLabel;
/*     */ import javax.swing.JPanel;
/*     */ import vu.storm.administration.IImage;
/*     */ import vu.storm.administration.IUser;
/*     */ import vu.storm.touch.GUIPanel;
/*     */ import vu.storm.touch.gfx.GfxUtil;
/*     */ import vu.storm.touch.gfx.Keypad;
/*     */ import vu.storm.touch.gfx.KeypadListener;
/*     */ 
/*     */ public class UserScreen
/*     */   extends JPanel implements ComponentListener, KeypadListener, PinScreenListener {
/*     */   private GUIPanel parent;
/*     */   private Keypad keypad;
/*     */   private UserColumn userColumn;
/*     */   private JLabel lUserName;
/*  24 */   private String curUsername = "";
/*     */   
/*     */   private UserScreenBar userBar;
/*     */   
/*     */   private static final String USER_BAR = "UserScreenBar";
/*     */   
/*     */   protected boolean isBorrelMode = false;
/*     */   
/*     */   private IImage[] promoImages;
/*     */   
/*     */   private Image[] jPromoImages;
/*     */   
/*     */   private PromoPanel promoPanel;
/*     */   private IUser tmpUser;
/*     */   
/*     */   public UserScreen(GUIPanel parent) {
/*  40 */     this.parent = parent;
/*  41 */     this.keypad = new Keypad(0);
/*  42 */     this.promoPanel = new PromoPanel();
/*  43 */     readPromoImages();
/*  44 */     this.userColumn = new UserColumn(this, parent.getDB());
/*  45 */     this.lUserName = new JLabel("");
/*  46 */     setLayout((LayoutManager)null);
/*  47 */     addComponentListener(this);
/*  48 */     this.keypad.addKeypadListener(this);
/*  49 */     add((Component)this.keypad);
/*  50 */     add(this.userColumn);
/*  51 */     add(this.lUserName);
/*  52 */     add(this.promoPanel);
/*  53 */     this.userBar = new UserScreenBar(this);
/*  54 */     getMasterPanel().getTopBar().addPanel(this.userBar, "UserScreenBar");
/*  55 */     this.keypad.takeKeyboard();
/*     */   }
/*     */   
/*     */   public GUIPanel getMasterPanel() {
/*  59 */     return this.parent;
/*     */   }
/*     */   
/*     */   private void readPromoImages() {
/*  63 */     Toolkit tk = Toolkit.getDefaultToolkit();
/*  64 */     this.promoImages = this.parent.getDB().getPromotionalImages();
/*  65 */     this.jPromoImages = new Image[this.promoImages.length];
/*  66 */     for (int i = 0; i < this.jPromoImages.length; i++)
/*     */     {
/*  68 */       this.jPromoImages[i] = tk.createImage(this.promoImages[i].getData());
/*     */     }
/*  70 */     if (this.jPromoImages.length > 0) {
/*     */       
/*  72 */       this.promoPanel.setImage(this.jPromoImages[0]);
/*     */     }
/*     */     else {
/*     */       
/*  76 */       System.err.println("No images :(");
/*     */     } 
/*     */   }
/*     */   
/*     */   protected void userClicked(IUser user) {
/*  81 */     getMasterPanel().endPrompt();
/*  82 */     if (!user.hasPin()) {
/*     */       
/*  84 */       this.curUsername = "";
/*  85 */       this.lUserName.setText(this.curUsername);
/*  86 */       this.userColumn.setFilter(this.curUsername);
/*  87 */       this.parent.startOrder(user);
/*     */     }
/*     */     else {
/*     */       
/*  91 */       this.tmpUser = user;
/*  92 */       getMasterPanel().askPin(this);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void componentHidden(ComponentEvent arg0) {
/*  99 */     getMasterPanel().getTopBar().releasePanel("UserScreenBar");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void componentMoved(ComponentEvent arg0) {}
/*     */ 
/*     */ 
/*     */   
/*     */   public void componentResized(ComponentEvent arg0) {
/* 110 */     this.userColumn.setBounds(0, 0, getWidth() / 2, getHeight() * 11 / 16);
/* 111 */     this.keypad.setBounds(0, getHeight() * 11 / 16, getWidth(), getHeight() * 5 / 16);
/* 112 */     this.lUserName.setBounds(getWidth() / 2, getHeight() * 9 / 16, getWidth() / 2, getHeight() / 8);
/* 113 */     this.lUserName.setFont(GfxUtil.fitFont(this.lUserName, "Verdana", 0, this.lUserName.getText()));
/* 114 */     this.promoPanel.setBounds(getWidth() / 2, 0, getWidth() / 2, getHeight() * 9 / 16);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void componentShown(ComponentEvent arg0) {
/* 120 */     if (getMasterPanel().getDB().hasRestartRequest())
/*     */     {
/* 122 */       System.exit(0);
/*     */     }
/* 124 */     if (getMasterPanel().getDB().hasUpdatedUsers()) {
/*     */       
/* 126 */       System.err.println("Rereading users");
/* 127 */       this.userColumn.rereadData();
/*     */     } 
/* 129 */     getMasterPanel().getTopBar().setPanel("UserScreenBar");
/* 130 */     this.keypad.takeKeyboard();
/*     */   }
/*     */ 
/*     */   
/*     */   protected void setBorrelMode(boolean inBorrelMode) {
/* 135 */     if (inBorrelMode != this.isBorrelMode) {
/*     */       
/* 137 */       this.isBorrelMode = inBorrelMode;
/* 138 */       System.err.println("Switching modes");
/* 139 */       this.userColumn.rereadFilter();
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public int scaleX(int x) {
/* 145 */     return x * getWidth() / 800;
/*     */   }
/*     */   
/*     */   public int scaleX(double x) {
/* 149 */     return (int)(x * getWidth());
/*     */   }
/*     */   
/*     */   public int scaleY(int y) {
/* 153 */     return y * getHeight() / 600;
/*     */   }
/*     */ 
/*     */   
/*     */   public int scaleY(double y) {
/* 158 */     return (int)(y * getHeight());
/*     */   }
/*     */   
/*     */   public int scaleFontSize(int size) {
/* 162 */     return Math.min(scaleY(size), scaleX(size));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void keypadPressed(Keypad sender, char key) {
/* 168 */     if (key == '\b') {
/*     */       
/* 170 */       if (this.curUsername.length() > 0)
/*     */       {
/* 172 */         this.curUsername = this.curUsername.substring(0, this.curUsername.length() - 1);
/* 173 */         this.lUserName.setText(this.curUsername);
/* 174 */         this.lUserName.setFont(GfxUtil.fitFont(this.lUserName, "Verdana", 0, this.lUserName.getText()));
/* 175 */         this.userColumn.setFilter(this.curUsername);
/*     */       
/*     */       }
/*     */     
/*     */     }
/* 180 */     else if (this.curUsername.length() < 10) {
/*     */       
/* 182 */       this.curUsername = String.valueOf(this.curUsername) + key;
/* 183 */       if (this.curUsername.equals("RESTARTNOW")) System.exit(1); 
/* 184 */       this.lUserName.setText(this.curUsername);
/* 185 */       this.lUserName.setFont(GfxUtil.fitFont(this.lUserName, "Verdana", 0, this.lUserName.getText()));
/* 186 */       this.userColumn.setFilter(this.curUsername);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void pinCancel() {
/* 192 */     this.parent.toUserScreen();
/*     */   }
/*     */   
/*     */   public boolean pinConfirm(String pin) {
/* 196 */     return this.tmpUser.authenticatePin(pin);
/*     */   }
/*     */   
/*     */   public void pinDone() {
/* 200 */     this.curUsername = "";
/* 201 */     this.lUserName.setText(this.curUsername);
/* 202 */     this.userColumn.setFilter(this.curUsername);
/* 203 */     this.parent.startOrder(this.tmpUser);
/*     */   }
/*     */ }


/* Location:              /Users/nicholas/Desktop/old-knt/old-jar/program.jar!/vu/storm/touch/user/UserScreen.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */