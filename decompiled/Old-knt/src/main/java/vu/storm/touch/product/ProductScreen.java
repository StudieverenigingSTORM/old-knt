/*     */ package vu.storm.touch.product;
/*     */ 
/*     */ import java.awt.Color;
/*     */ import java.awt.LayoutManager;
/*     */ import java.awt.event.ComponentEvent;
/*     */ import java.awt.event.ComponentListener;
/*     */ import javax.swing.JPanel;
/*     */ import javax.swing.OverlayLayout;
/*     */ import vu.storm.administration.ICategory;
/*     */ import vu.storm.administration.IOrder;
/*     */ import vu.storm.administration.IOrderLine;
/*     */ import vu.storm.administration.IProduct;
/*     */ import vu.storm.touch.GUIPanel;
/*     */ 
/*     */ public class ProductScreen
/*     */   extends JPanel
/*     */   implements ComponentListener
/*     */ {
/*     */   private GUIPanel parent;
/*  20 */   private NewProductColumn[] columns = new NewProductColumn[0];
/*     */   
/*     */   private OrderPane orderPane;
/*     */   
/*     */   private IOrder order;
/*     */   private NumpadOverlay numOverlay;
/*     */   private JPanel bottomContainer;
/*     */   private OverlayLayout layout;
/*     */   private boolean numpadVisible = false;
/*     */   private OrderStack orderStack;
/*     */   private BorrelBar borrelBar;
/*     */   private UserOrderBar userOrderBar;
/*     */   private static final String PANEL_ORDERSTACK = "OrderStack";
/*     */   private static final String PANEL_BORRELBAR = "BorrelBar";
/*     */   private static final String PANEL_USERBAR = "UserOrderBar";
/*     */   
/*     */   public ProductScreen(GUIPanel parent) {
/*  37 */     this.parent = parent;
/*  38 */     this.layout = new OverlayLayout(this);
/*  39 */     setLayout(this.layout);
/*  40 */     this.bottomContainer = new JPanel();
/*  41 */     this.bottomContainer.setLayout((LayoutManager)null);
/*  42 */     this.numOverlay = new NumpadOverlay(this);
/*     */ 
/*     */     
/*  45 */     this.orderPane = new OrderPane(this);
/*  46 */     this.orderStack = new OrderStack(this);
/*     */     
/*  48 */     this.borrelBar = new BorrelBar(this);
/*  49 */     this.userOrderBar = new UserOrderBar(this);
/*     */     
/*  51 */     getMasterPanel().getTopBar().addPanel(this.orderStack, "OrderStack");
/*  52 */     getMasterPanel().getTopBar().addPanel(this.borrelBar, "BorrelBar");
/*  53 */     getMasterPanel().getTopBar().addPanel(this.userOrderBar, "UserOrderBar");
/*     */     
/*  55 */     setBackground(Color.BLACK);
/*     */     
/*  57 */     this.bottomContainer.add(this.orderPane);
/*  58 */     this.numOverlay.setVisible(false);
/*  59 */     updateProducts();
/*  60 */     addComponentListener(this);
/*  61 */     add(this.numOverlay);
/*  62 */     add(this.bottomContainer);
/*     */   }
/*     */   
/*     */   public GUIPanel getMasterPanel() {
/*  66 */     return this.parent;
/*     */   }
/*     */   
/*     */   public void setOrder(IOrder order) {
/*  70 */     this.order = order;
/*  71 */     this.orderPane.setBorrelMode(order.getUser().isBorrelUser());
/*  72 */     if (order.getUser().isBorrelUser()) {
/*     */       
/*  74 */       if (this.orderStack.numOrders() == 0) getMasterPanel().getTopBar().setPanel("BorrelBar"); 
/*  75 */       this.borrelBar.setOmzet(order.getUser().totalOrdersPrice());
/*  76 */       this.borrelBar.setInKas(order.getUser().getBalance());
/*     */     }
/*     */     else {
/*     */       
/*  80 */       getMasterPanel().getTopBar().setPanel("UserOrderBar");
/*     */     } 
/*  82 */     this.orderPane.rereadButtons();
/*     */   }
/*     */   
/*     */   protected IOrder getOrder() {
/*  86 */     return this.order;
/*     */   }
/*     */   
/*     */   protected void switchOrder(IOrder order) {
/*  90 */     if (this.order.numLineItems() > 0) {
/*     */ 
/*     */       
/*  93 */       this.orderStack.addAndRemoveOrder(this.order, order);
/*  94 */       setOrder(order);
/*     */     
/*     */     }
/*     */     else {
/*     */       
/*  99 */       this.orderStack.removeOrder(order);
/* 100 */       setOrder(order);
/* 101 */       if (this.orderStack.numOrders() == 0)
/*     */       {
/* 103 */         getMasterPanel().getTopBar().setPanel("BorrelBar");
/*     */       }
/*     */     } 
/*     */   }
/*     */   
/*     */   protected void postponeOrderClicked() {
/* 109 */     if (this.order.numLineItems() > 0) {
/*     */       
/* 111 */       this.orderStack.addOrder(this.order);
/* 112 */       setOrder(getMasterPanel().getDB().beginOrder(this.order.getUser()));
/* 113 */       getMasterPanel().getTopBar().setPanel("OrderStack");
/*     */     }
/*     */     else {
/*     */       
/* 117 */       getMasterPanel().flashError("Geen lege bestellingen in de wachtrij!");
/*     */     } 
/*     */   }
/*     */   
/*     */   protected void lineItemClicked(IOrderLine lineItem) {
/* 122 */     if (this.numpadVisible)
/* 123 */       return;  setLineItemDeltaAmount(lineItem, -1L);
/*     */   }
/*     */   
/*     */   private void setProductAmount(IProduct product, long amount) {
/* 127 */     if (amount < 0L)
/* 128 */       return;  if (this.order.hasProduct(product)) {
/*     */       
/* 130 */       setProductDeltaAmount(product, amount - this.order.getLineItem(this.order.productIndex(product)).getAmount());
/*     */     }
/* 132 */     else if (amount > 0L) {
/*     */       
/* 134 */       setProductDeltaAmount(product, amount);
/*     */     } 
/*     */   }
/*     */   
/*     */   private void setProductDeltaAmount(IProduct product, long deltaAmount) {
/* 139 */     if (deltaAmount == 0L)
/* 140 */       return;  if (this.order.hasProduct(product)) {
/*     */       
/* 142 */       setLineItemDeltaAmount(this.order.getLineItem(this.order.productIndex(product)), deltaAmount);
/*     */       return;
/*     */     } 
/* 145 */     if (deltaAmount < 0L) {
/*     */       return;
/*     */     }
/*     */     
/* 149 */     if (this.order.addLineItem(product, deltaAmount)) {
/*     */       
/* 151 */       this.orderPane.rereadButtons();
/*     */     }
/*     */     else {
/*     */       
/* 155 */       getMasterPanel().flashError("Te weinig saldo");
/*     */     } 
/*     */   }
/*     */   
/*     */   private void setLineItemDeltaAmount(IOrderLine lineItem, long deltaAmount) {
/* 160 */     if (deltaAmount == 0L)
/* 161 */       return;  if (deltaAmount < 0L) {
/*     */       
/* 163 */       if (lineItem.getAmount() + deltaAmount <= 0L)
/*     */       {
/* 165 */         this.order.removeLineItem(lineItem);
/* 166 */         this.orderPane.rereadButtons();
/*     */       }
/*     */       else
/*     */       {
/* 170 */         lineItem.setAmount(lineItem.getAmount() + deltaAmount);
/* 171 */         this.orderPane.repaintButtons();
/*     */       
/*     */       }
/*     */     
/*     */     }
/* 176 */     else if (lineItem.setAmount(lineItem.getAmount() + deltaAmount)) {
/*     */       
/* 178 */       this.orderPane.repaintButtons();
/*     */     }
/*     */     else {
/*     */       
/* 182 */       getMasterPanel().flashError("Te weinig saldo");
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   protected void placeOrderClicked() {
/* 188 */     if (this.numpadVisible)
/* 189 */       return;  if (this.order.numLineItems() > 0) {
/*     */       
/* 191 */       if (!this.parent.processOrder(this.order))
/* 192 */         return;  for (int i = 0; i < this.columns.length; i++)
/*     */       {
/* 194 */         this.columns[i].updateFilter();
/*     */       }
/*     */     }
/*     */     else {
/*     */       
/* 199 */       getMasterPanel().flashError("Anders nog niets?");
/*     */     } 
/* 201 */     if (getMasterPanel().getDB().hasUpdatedProducts()) updateProducts(); 
/* 202 */     setOrder(getMasterPanel().getDB().beginOrder(this.order.getUser()));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void lineItemLongClicked(IOrderLine lineItem) {
/* 212 */     if (this.numpadVisible)
/* 213 */       return;  setLineItemDeltaAmount(lineItem, -lineItem.getAmount());
/*     */   }
/*     */   
/*     */   protected void productClicked(IProduct product) {
/* 217 */     if (this.numpadVisible)
/* 218 */       return;  setProductDeltaAmount(product, 1L);
/*     */   }
/*     */   
/*     */   protected void cancelOrderClicked() {
/* 222 */     if (this.numpadVisible)
/* 223 */       return;  if (this.order.getUser().isBorrelUser() && this.order.numLineItems() != 0) {
/*     */       
/* 225 */       getMasterPanel().flashWarning("Er staat nog een bestelling open");
/*     */       return;
/*     */     } 
/* 228 */     this.parent.cancelOrder();
/* 229 */     if (getMasterPanel().getDB().hasUpdatedProducts()) updateProducts(); 
/*     */   }
/*     */   
/*     */   protected void updateProducts() {
/* 233 */     ICategory[] categories = this.parent.getDB().getCategories();
/* 234 */     synchronized (this) {
/* 235 */       int i; for (i = 0; i < this.columns.length; i++)
/*     */       {
/* 237 */         this.bottomContainer.remove(this.columns[i]);
/*     */       }
/* 239 */       this.columns = new NewProductColumn[categories.length];
/* 240 */       System.err.printf("Columns: %d\n", new Object[] { Integer.valueOf(this.columns.length) });
/* 241 */       for (i = 0; i < this.columns.length; i++) {
/*     */         
/* 243 */         this.columns[i] = new NewProductColumn(this, categories[i]);
/* 244 */         this.bottomContainer.add(this.columns[i]);
/*     */       } 
/*     */     } 
/* 247 */     rescale();
/*     */   }
/*     */   
/*     */   private void rescale() {
/* 251 */     this.numOverlay.setBounds(0, 0, getWidth(), getHeight());
/* 252 */     this.bottomContainer.setBounds(0, 0, getWidth(), getHeight());
/*     */     
/* 254 */     this.orderPane.setBounds(scaleX(0.75D), scaleY(0), scaleX(0.25D), scaleY(1.0D));
/* 255 */     synchronized (this) {
/* 256 */       double numColumns = this.columns.length;
/* 257 */       double colWidth = 0.75D / numColumns;
/* 258 */       for (int i = 0; i < this.columns.length; i++)
/*     */       {
/* 260 */         this.columns[i].setBounds(scaleX(i * colWidth), scaleY(0.0D), scaleX(colWidth), scaleY(1.0D));
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int scaleX(int x) {
/* 268 */     return x * getWidth() / 800;
/*     */   }
/*     */   
/*     */   public int scaleX(double x) {
/* 272 */     return (int)(x * getWidth());
/*     */   }
/*     */   
/*     */   public int scaleY(int y) {
/* 276 */     return y * getHeight() / 600;
/*     */   }
/*     */ 
/*     */   
/*     */   public int scaleY(double y) {
/* 281 */     return (int)(y * getHeight());
/*     */   }
/*     */   
/*     */   public int scaleFontSize(int size) {
/* 285 */     return Math.min(scaleY(size), scaleX(size));
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
/* 299 */     rescale();
/*     */   }
/*     */ 
/*     */   
/*     */   public void componentShown(ComponentEvent e) {}
/*     */ 
/*     */   
/*     */   protected void numpadResult(IProduct product, int amount) {
/* 307 */     this.numOverlay.setVisible(false);
/* 308 */     getMasterPanel().endPrompt();
/* 309 */     this.numpadVisible = false;
/* 310 */     setProductAmount(product, amount);
/*     */   }
/*     */   
/*     */   protected void numpadCancel() {
/* 314 */     this.numOverlay.setVisible(false);
/* 315 */     getMasterPanel().endPrompt();
/* 316 */     this.numpadVisible = false;
/*     */   }
/*     */   protected void productLongClicked(IProduct product) {
/* 319 */     this.numOverlay.setProduct(product);
/* 320 */     if (this.order.hasProduct(product)) {
/*     */       
/* 322 */       this.numOverlay.setAmount((int)this.order.getLineItem(this.order.productIndex(product)).getAmount());
/*     */     }
/*     */     else {
/*     */       
/* 326 */       this.numOverlay.setAmount(0);
/*     */     } 
/* 328 */     this.numOverlay.setVisible(true);
/* 329 */     getMasterPanel().showPrompt("Voer nieuw aantal in:");
/* 330 */     this.numpadVisible = true;
/*     */   }
/*     */ }


/* Location:              /Users/nicholas/Desktop/old-knt/old-jar/program.jar!/vu/storm/touch/product/ProductScreen.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */