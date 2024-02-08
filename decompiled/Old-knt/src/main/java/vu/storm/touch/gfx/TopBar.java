/*     */ package vu.storm.touch.gfx;
/*     */ 
/*     */ import java.awt.CardLayout;
/*     */ import java.awt.Color;
/*     */ import javax.swing.JPanel;
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
/*     */ public class TopBar
/*     */   extends JPanel
/*     */ {
/*     */   CardLayout layout;
/*     */   JPanel defaultPanel;
/*     */   MessagePanel errorPanel;
/*     */   MessagePanel warningPanel;
/*     */   MessagePanel promptPanel;
/*     */   MessagePanel goodPanel;
/*     */   private static final String DEFAULT_PANEL = "default";
/*     */   private static final String ERROR_PANEL = "ErrorPanel";
/*     */   private static final String WARNING_PANEL = "WarningPanel";
/*     */   private static final String PROMPT_PANEL = "PromptPanel";
/*     */   private static final String GOOD_PANEL = "GoodPanel";
/*     */   Thread tFlash;
/*     */   boolean hasPrompt = false;
/*     */   boolean hasFlash = false;
/*  39 */   String exclusivePanel = "default";
/*     */   
/*     */   public TopBar() {
/*  42 */     setBackground(Color.black);
/*  43 */     this.layout = new CardLayout();
/*  44 */     setLayout(this.layout);
/*     */     
/*  46 */     this.defaultPanel = new ClockPanel();
/*     */ 
/*     */ 
/*     */     
/*  50 */     this.errorPanel = new MessagePanel(Color.red, Color.white);
/*  51 */     this.warningPanel = new MessagePanel(Color.yellow, Color.black);
/*  52 */     this.promptPanel = new MessagePanel(Color.black, Color.white);
/*  53 */     this.goodPanel = new MessagePanel(Color.green, Color.black);
/*     */     
/*  55 */     add(this.defaultPanel, "default");
/*  56 */     add(this.errorPanel, "ErrorPanel");
/*  57 */     add(this.warningPanel, "WarningPanel");
/*  58 */     add(this.promptPanel, "PromptPanel");
/*  59 */     add(this.goodPanel, "GoodPanel");
/*     */     
/*  61 */     this.layout.show(this, this.exclusivePanel);
/*     */   }
/*     */   
/*     */   public void addPanel(JPanel panel, String uniqueId) {
/*  65 */     add(panel, uniqueId);
/*     */   }
/*     */   
/*     */   public void removePanel(JPanel panel, String uniqueId) {
/*  69 */     remove(panel);
/*     */   }
/*     */   
/*     */   public void setPanel(String uniqueId) {
/*  73 */     synchronized (this.exclusivePanel) {
/*  74 */       this.exclusivePanel = uniqueId;
/*     */     } 
/*  76 */     if (!this.hasFlash && !this.hasPrompt) this.layout.show(this, this.exclusivePanel); 
/*     */   }
/*     */   
/*     */   public void releasePanel(String uniqueId) {
/*  80 */     synchronized (this.exclusivePanel) {
/*  81 */       if (this.exclusivePanel == uniqueId)
/*     */       {
/*  83 */         this.exclusivePanel = "default";
/*     */       }
/*     */     } 
/*  86 */     if (!this.hasFlash && !this.hasPrompt) this.layout.show(this, this.exclusivePanel); 
/*     */   }
/*     */   
/*     */   public void releasePanel() {
/*  90 */     synchronized (this.exclusivePanel) {
/*  91 */       this.exclusivePanel = "default";
/*     */     } 
/*  93 */     if (!this.hasFlash && !this.hasPrompt) this.layout.show(this, this.exclusivePanel); 
/*     */   }
/*     */   
/*     */   protected void endFlash() {
/*  97 */     this.hasFlash = false;
/*  98 */     if (this.hasPrompt) {
/*     */       
/* 100 */       this.layout.show(this, "PromptPanel");
/*     */     }
/*     */     else {
/*     */       
/* 104 */       this.layout.show(this, this.exclusivePanel);
/*     */     } 
/*     */   }
/*     */   
/*     */   public void showPrompt(String prompt) {
/* 109 */     this.hasPrompt = true;
/* 110 */     this.promptPanel.setText(prompt);
/* 111 */     if (!this.hasFlash)
/* 112 */       this.layout.show(this, "PromptPanel"); 
/*     */   }
/*     */   
/*     */   public void endPrompt() {
/* 116 */     this.hasPrompt = false;
/* 117 */     this.layout.show(this, this.exclusivePanel);
/*     */   }
/*     */   
/*     */   public void flashError(String message) {
/* 121 */     flash("ErrorPanel", this.errorPanel, 1000, message);
/*     */   }
/*     */   
/*     */   public void flashWarning(String message) {
/* 125 */     flash("WarningPanel", this.warningPanel, 1000, message);
/*     */   }
/*     */   public void flashGreen(String message) {
/* 128 */     flash("GoodPanel", this.goodPanel, 1000, message);
/*     */   }
/*     */   
/*     */   private void flash(String sPanel, MessagePanel panel, final int delay, String message) {
/* 132 */     this.hasFlash = true;
/* 133 */     if (this.tFlash != null) {
/*     */       
/* 135 */       this.tFlash.interrupt(); 
/* 136 */       try { this.tFlash.join(); } catch (InterruptedException interruptedException) {}
/*     */     } 
/* 138 */     panel.setText(message);
/* 139 */     this.layout.show(this, sPanel);
/* 140 */     this.tFlash = new Thread()
/*     */       {
/*     */         public void run()
/*     */         {
/*     */           try {
/* 145 */             Thread.sleep(delay);
/* 146 */             TopBar.this.endFlash();
/*     */           }
/* 148 */           catch (InterruptedException interruptedException) {}
/*     */         }
/*     */       };
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 155 */     this.tFlash.start();
/*     */   }
/*     */ }


/* Location:              /Users/nicholas/Desktop/old-knt/old-jar/program.jar!/vu/storm/touch/gfx/TopBar.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */