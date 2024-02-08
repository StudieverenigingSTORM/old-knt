/*     */ package vu.storm.touch;
/*     */ 
/*     */ import java.awt.CardLayout;
/*     */ import java.awt.Color;
/*     */ import java.awt.Component;
/*     */ import java.awt.Cursor;
/*     */ import java.awt.Image;
/*     */ import java.awt.LayoutManager;
/*     */ import java.awt.Point;
/*     */ import java.awt.Toolkit;
/*     */ import java.awt.event.ComponentEvent;
/*     */ import java.awt.event.ComponentListener;
/*     */ import java.awt.image.MemoryImageSource;
/*     */ import java.io.FileInputStream;
/*     */ import java.io.IOException;
/*     */ import java.util.Properties;
/*     */ import javax.swing.JPanel;
/*     */ import vu.storm.administration.DatabaseFactory;
/*     */ import vu.storm.administration.IDatabase;
/*     */ import vu.storm.administration.IOrder;
/*     */ import vu.storm.administration.IUser;
/*     */ import vu.storm.touch.gfx.TopBar;
/*     */ import vu.storm.touch.product.ProductScreen;
/*     */ import vu.storm.touch.user.PinScreen;
/*     */ import vu.storm.touch.user.PinScreenListener;
/*     */ import vu.storm.touch.user.UserScreen;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class GUIPanel
/*     */   extends JPanel
/*     */   implements ComponentListener
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*     */   public static final int REF_WIDTH = 800;
/*     */   public static final int REF_HEIGHT = 600;
/*     */   public static final double D_REF_WIDTH = 800.0D;
/*     */   public static final double D_REF_HEIGHT = 600.0D;
/*     */   private static final String SCREEN_USER = "user";
/*     */   private static final String SCREEN_PRODUCT = "product";
/*     */   private static final String SCREEN_PIN = "pin";
/*     */   Properties properties;
/*  46 */   IUser currentUser = null;
/*     */   
/*     */   IDatabase db;
/*     */   
/*     */   CardLayout layout;
/*     */   UserScreen userScreen;
/*     */   ProductScreen productScreen;
/*     */   PinScreen pinScreen;
/*     */   JPanel mainPanel;
/*     */   TopBar topBar;
/*     */   
/*     */   public GUIPanel() {
/*  58 */     String[] args = { "settings.xml" };
/*  59 */     start(args);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public GUIPanel(String[] args) {
/*  65 */     start(args);
/*     */   }
/*     */ 
/*     */   
/*     */   private void start(String[] args) {
/*  70 */     if (args.length == 0 || args[0] == "") {
/*  71 */       System.out.println("No settings file specified. Specify a settings file as the first argument");
/*  72 */       System.exit(1);
/*     */     } 
/*     */     
/*  75 */     this.properties = new Properties();
/*     */     
/*     */     try {
/*  78 */       this.properties.loadFromXML(new FileInputStream(args[0]));
/*  79 */       System.out.println("Loaded settings...");
/*     */     }
/*  81 */     catch (IOException e) {
/*  82 */       System.out.println("Error while loading settings file. Specify a settings file as the first argument");
/*  83 */       System.out.println(e.getMessage());
/*  84 */       System.exit(1);
/*     */     } 
/*     */     
/*  87 */     this.db = DatabaseFactory.openDatabase(this.properties);
/*  88 */     setBackground(new Color(128, 154, 205));
/*  89 */     this.mainPanel = new JPanel();
/*  90 */     this.layout = new CardLayout();
/*     */     
/*  92 */     this.topBar = new TopBar();
/*     */     
/*  94 */     this.userScreen = new UserScreen(this);
/*  95 */     this.productScreen = new ProductScreen(this);
/*  96 */     this.pinScreen = new PinScreen(this);
/*     */     
/*  98 */     this.mainPanel.setLayout(this.layout);
/*  99 */     this.mainPanel.add((Component)this.userScreen, "user");
/* 100 */     this.mainPanel.add((Component)this.productScreen, "product");
/* 101 */     this.mainPanel.add((Component)this.pinScreen, "pin");
/* 102 */     this.mainPanel.setBackground(Color.green);
/* 103 */     setLayout((LayoutManager)null);
/* 104 */     add((Component)this.topBar);
/* 105 */     add(this.mainPanel);
/* 106 */     addComponentListener(this);
/* 107 */     this.layout.show(this.mainPanel, "product");
/* 108 */     this.layout.show(this.mainPanel, "user");
/*     */     
/*     */     try {
/* 111 */       if (Integer.parseInt(this.properties.getProperty("cursor.visible", "0")) == 0)
/*     */       {
/* 113 */         hideCursor();
/*     */       }
/*     */     }
/* 116 */     catch (Exception exception) {}
/*     */   }
/*     */ 
/*     */   
/*     */   private void hideCursor() {
/* 121 */     int[] pixels = new int[256];
/* 122 */     Image image = Toolkit.getDefaultToolkit().createImage(new MemoryImageSource(16, 16, pixels, 0, 16));
/* 123 */     Cursor transparentCursor = Toolkit.getDefaultToolkit().createCustomCursor(image, new Point(0, 0), "invisiblecursor");
/* 124 */     setCursor(transparentCursor);
/*     */   }
/*     */   
/*     */   public void askPin(PinScreenListener respondTo) {
/* 128 */     this.pinScreen.giveFeedbackTo(respondTo);
/* 129 */     this.layout.show(this.mainPanel, "pin");
/*     */   }
/*     */   
/*     */   public void toUserScreen() {
/* 133 */     this.layout.show(this.mainPanel, "user");
/*     */   }
/*     */   
/*     */   public void startOrder(IUser user) {
/* 137 */     this.productScreen.setOrder(this.db.beginOrder(user));
/* 138 */     this.layout.show(this.mainPanel, "product");
/*     */   }
/*     */   
/*     */   public void cancelOrder() {
/* 142 */     flashWarning("Bestelling afgebroken.");
/* 143 */     this.layout.show(this.mainPanel, "user");
/*     */   }
/*     */   
/*     */   public boolean processOrder(IOrder order) {
/* 147 */     boolean res = this.db.processOrder(order);
/* 148 */     if (res && !order.getUser().isBorrelUser()) this.layout.show(this.mainPanel, "user"); 
/* 149 */     if (!res) {
/*     */       
/* 151 */       flashError("Bestelling mislukt!");
/*     */     }
/*     */     else {
/*     */       
/* 155 */       flashGreen("Bestelling voltooid");
/*     */     } 
/* 157 */     return res;
/*     */   }
/*     */ 
/*     */   
/*     */   public IDatabase getDB() {
/* 162 */     return this.db;
/*     */   }
/*     */ 
/*     */   
/*     */   public int scaleX(int x) {
/* 167 */     return (int)(getWidth() / 800.0D * x);
/*     */   }
/*     */   
/*     */   public int scaleX(double x) {
/* 171 */     return (int)(x * getWidth());
/*     */   }
/*     */   
/*     */   public int scaleY(int y) {
/* 175 */     return (int)(getHeight() / 600.0D * y);
/*     */   }
/*     */   
/*     */   public int scaleY(double y) {
/* 179 */     return (int)(y * getHeight());
/*     */   }
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
/* 193 */     System.err.printf("Scaling [%dx%d]...\n", new Object[] { Integer.valueOf(getWidth()), Integer.valueOf(getHeight()) });
/* 194 */     this.topBar.setBounds(0, 0, getWidth(), scaleY(0.08333333333333333D));
/* 195 */     this.mainPanel.setBounds(0, scaleY(0.08333333333333333D), getWidth(), scaleY(0.9166666666666666D));
/* 196 */     this.userScreen.setBounds(0, 0, this.mainPanel.getWidth(), this.mainPanel.getHeight());
/* 197 */     this.layout.invalidateLayout(this.mainPanel);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void componentShown(ComponentEvent e) {}
/*     */ 
/*     */   
/*     */   public TopBar getTopBar() {
/* 206 */     return this.topBar;
/*     */   }
/*     */   
/*     */   public void flashError(String error) {
/* 210 */     System.err.println("UI Error: " + error);
/* 211 */     this.topBar.flashError(error);
/*     */   }
/*     */   
/*     */   public void flashWarning(String warning) {
/* 215 */     this.topBar.flashWarning(warning);
/*     */   }
/*     */   
/*     */   public void flashGreen(String good) {
/* 219 */     this.topBar.flashGreen(good);
/*     */   }
/*     */   
/*     */   public void showPrompt(String prompt) {
/* 223 */     this.topBar.showPrompt(prompt);
/*     */   }
/*     */   
/*     */   public void endPrompt() {
/* 227 */     this.topBar.endPrompt();
/*     */   }
/*     */ }


/* Location:              /Users/nicholas/Desktop/old-knt/old-jar/program.jar!/vu/storm/touch/GUIPanel.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */