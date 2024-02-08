/*     */ package vu.storm.touch.user;
/*     */ 
/*     */ import java.awt.Color;
/*     */ import java.awt.Component;
/*     */ import java.awt.LayoutManager;
/*     */ import java.awt.event.ComponentEvent;
/*     */ import java.awt.event.ComponentListener;
/*     */ import javax.swing.JLabel;
/*     */ import javax.swing.JPanel;
/*     */ import vu.storm.touch.GUIPanel;
/*     */ import vu.storm.touch.gfx.GfxUtil;
/*     */ import vu.storm.touch.gfx.Keypad;
/*     */ import vu.storm.touch.gfx.KeypadListener;
/*     */ 
/*     */ 
/*     */ 
/*     */ public class PinScreen
/*     */   extends JPanel
/*     */   implements ComponentListener, KeypadListener
/*     */ {
/*     */   private GUIPanel parent;
/*     */   private PinScreenListener psl;
/*     */   private Keypad pinpad;
/*  24 */   private String curPin = "";
/*     */   
/*     */   private JLabel lblMask;
/*     */   private static final int MAX_PIN_LENGTH = 8;
/*     */   private static final String pinMask = "********";
/*     */   
/*     */   public PinScreen(GUIPanel parent) {
/*  31 */     this.parent = parent;
/*  32 */     setLayout((LayoutManager)null);
/*  33 */     this.lblMask = new JLabel();
/*  34 */     this.lblMask.setOpaque(true);
/*  35 */     this.lblMask.setBackground(new Color(192, 192, 192));
/*  36 */     setBackground(new Color(192, 192, 192));
/*     */     
/*  38 */     this.pinpad = new Keypad(1, false);
/*  39 */     this.pinpad.addKeypadListener(this);
/*  40 */     add((Component)this.pinpad);
/*  41 */     add(this.lblMask);
/*  42 */     addComponentListener(this);
/*     */   }
/*     */ 
/*     */   
/*     */   public void giveFeedbackTo(PinScreenListener psl) {
/*  47 */     this.psl = psl;
/*     */   }
/*     */ 
/*     */   
/*     */   public void componentHidden(ComponentEvent arg0) {
/*  52 */     this.parent.endPrompt();
/*     */   }
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
/*  64 */     this.pinpad.setBounds(getWidth() / 4, getHeight() / 4, getWidth() / 2, getHeight() / 2 + getHeight() / 16);
/*  65 */     this.lblMask.setBounds(getWidth() / 4, getHeight() / 8, getWidth() / 2, getHeight() / 4 - getHeight() / 8);
/*  66 */     this.lblMask.setFont(GfxUtil.fitFont(this.lblMask, "Verdana", 1, "********"));
/*     */   }
/*     */ 
/*     */   
/*     */   public void componentShown(ComponentEvent arg0) {
/*  71 */     this.parent.showPrompt("PIN vereist:");
/*  72 */     this.pinpad.takeKeyboard();
/*     */   }
/*     */ 
/*     */   
/*     */   private void resetInput() {
/*  77 */     this.curPin = "";
/*  78 */     updateMask();
/*     */   }
/*     */   
/*     */   private void updateMask() {
/*  82 */     this.lblMask.setText("********".substring(0, this.curPin.length()));
/*     */   }
/*     */   
/*     */   public void keypadPressed(Keypad sender, char key) {
/*  86 */     if (sender != this.pinpad)
/*     */       return; 
/*  88 */     if (key == '\033') {
/*     */       
/*  90 */       resetInput();
/*  91 */       this.psl.pinCancel();
/*     */     }
/*  93 */     else if (key == '\b') {
/*     */       
/*  95 */       resetInput();
/*     */     }
/*  97 */     else if (key == '\n') {
/*     */       
/*  99 */       if (this.psl.pinConfirm(this.curPin))
/*     */       {
/* 101 */         resetInput();
/* 102 */         this.psl.pinDone();
/*     */       }
/*     */       else
/*     */       {
/* 106 */         resetInput();
/* 107 */         this.parent.flashError("Incorrect");
/*     */       
/*     */       }
/*     */     
/*     */     }
/* 112 */     else if (this.curPin.length() < 8) {
/*     */       
/* 114 */       this.curPin = String.valueOf(this.curPin) + key;
/* 115 */       updateMask();
/*     */     } 
/*     */   }
/*     */ }


/* Location:              /Users/nicholas/Desktop/old-knt/old-jar/program.jar!/vu/storm/touch/user/PinScreen.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */