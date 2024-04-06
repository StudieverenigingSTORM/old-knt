/*     */ package vu.storm.touch.gfx;
/*     */ 
/*     */ import java.awt.Color;
/*     */ import java.awt.LayoutManager;
/*     */ import java.awt.event.ActionEvent;
/*     */ import java.awt.event.ActionListener;
/*     */ import java.awt.event.ComponentEvent;
/*     */ import java.awt.event.ComponentListener;
/*     */ import java.awt.event.KeyEvent;
/*     */ import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;
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
/*     */ 
/*     */ public class Numpad
/*     */   extends JPanel
/*     */   implements ActionListener, ComponentListener, KeyListener
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*     */   NumpadListener numpadListener;
/*     */   private static final int GRID_WIDTH = 3;
/*     */   private static final int GRID_HEIGHT = 5;
/*  37 */   private static final int[] BUTTON_VALUES = new int[] { 
/*  38 */       -1, 
/*  39 */       7, 8, 9, 
/*  40 */       4, 5, 6, 
/*  41 */       1, 2, 3, 
/*  42 */       -2, -3};
/*     */   
/*  44 */   private static final int[] BUTTON_SIZES = new int[] { 
/*  45 */       3, 
/*  46 */       1, 1, 1, 
/*  47 */       1, 1, 1, 
/*  48 */       1, 1, 1, 
/*  49 */       2, 1 };
/*     */   
/*  51 */   private static final int[] BUTTON_X = new int[] {
/*     */       
/*  53 */       0, 0, 1, 2, 
/*  54 */       1, 2, 
/*  55 */       1, 2, 
/*  56 */       2
/*     */     };
/*  58 */   private static final int[] BUTTON_Y = new int[] {
/*     */       
/*  60 */       0, 1, 1, 1, 
/*  61 */       2, 2, 2, 
/*  62 */       3, 3, 3, 
/*  63 */       4, 4
/*     */     };
/*  65 */   private static final String[] BUTTON_LABELS = new String[] { 
/*  66 */       "BACKSPACE", 
/*  67 */       "7", "8", "9", 
/*  68 */       "4", "5", "6", 
/*  69 */       "1", "2", "3", 
/*  70 */       "0", "OK" };
/*     */   
/*     */   private static final int NUM_BUTTONS = 12;
/*     */   
/*     */   private static final String ALLOWED_HARD_KEYS = "0123456789\b\r\n";
/*     */   
/*     */   private static final String TRANSLATED_HARD_KEYS = "0123456789\b\n\n";
/*     */   
///*     */   private NumpadButton[] buttons;
            private final List<NumpadButton> buttons = new ArrayList<>();
/*     */   
/*     */   public Numpad() {
/*  81 */     setLayout((LayoutManager)null);
/*  82 */     this.numpadListener = null;
///*  83 */     this.buttons = new NumpadButton[100];
/*  84 */     for (int i = 0; i < 12; i++) {
/*     */
                String label = BUTTON_LABELS[i];
                System.out.println("Size value array: " + BUTTON_VALUES.length + "\ni: " + i);
                int value = BUTTON_VALUES[i];
                int size = BUTTON_SIZES[i];
                NumpadButton button = new NumpadButton(this, label, value, size);
                this.buttons.add(button);
///*  86 */       this.buttons[i] = ;
/*  87 */       add(button);
/*     */     } 
/*  89 */     setBackground(new Color(192, 192, 192));
/*  90 */     addComponentListener(this);
/*  91 */     setFocusable(true);
/*  92 */     setFocusCycleRoot(true);
/*  93 */     setFocusTraversalKeysEnabled(false);
/*  94 */     addKeyListener(this);
/*     */   }
/*     */   
/*     */   public void addNumpadListener(NumpadListener l) {
/*  98 */     this.numpadListener = NumpadListenerChain.add(this.numpadListener, l);
/*     */   }
/*     */   
/*     */   public void removeNumpadListener(NumpadListener l) {
/* 102 */     this.numpadListener = NumpadListenerChain.remove(this.numpadListener, l);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void actionPerformed(ActionEvent e) {}
/*     */ 
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
/* 122 */     int wcell = getWidth() / 3;
/* 123 */     int hcell = getHeight() / 5;
/* 124 */     for (int i = 0; i < 12; i++) {
/*     */       
///* 126 */       this.buttons[i].setButtonSize(BUTTON_SIZES[i]);
///* 127 */       this.buttons[i].setBounds(BUTTON_X[i] * wcell, BUTTON_Y[i] * hcell, BUTTON_SIZES[i] * wcell, hcell);
                this.buttons.get(i).setButtonSize(BUTTON_SIZES[i]);
                this.buttons.get(i).setBounds(BUTTON_X[i] * wcell, BUTTON_Y[i] * hcell, BUTTON_SIZES[i] * wcell, hcell);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void componentShown(ComponentEvent e) {}
/*     */ 
/*     */   
/*     */   protected void buttonPressed(int value) {
/* 137 */     if (this.numpadListener != null)
/*     */     {
/* 139 */       this.numpadListener.numpadPressed(this, value);
/*     */     }
/*     */   }
/*     */   
/*     */   public void takeKeyboard() {
/* 144 */     requestFocusInWindow();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void keyPressed(KeyEvent e) {}
/*     */ 
/*     */ 
/*     */   
/*     */   public void keyReleased(KeyEvent e) {}
/*     */ 
/*     */ 
/*     */   
/*     */   public void keyTyped(KeyEvent e) {
/*     */     int i;
/* 159 */     if ((i = "0123456789\b\r\n".indexOf(e.getKeyChar())) != -1) {
/*     */       
/* 161 */       char c = "0123456789\b\n\n".charAt(i);
/* 162 */       switch (c) {
/*     */         
/*     */         case '\b':
/* 165 */           buttonPressed(-1);
/*     */           break;
/*     */         case '\n':
/* 168 */           buttonPressed(-2);
/*     */           break;
/*     */         case '0':
/*     */         case '1':
/*     */         case '2':
/*     */         case '3':
/*     */         case '4':
/*     */         case '5':
/*     */         case '6':
/*     */         case '7':
/*     */         case '8':
/*     */         case '9':
/* 180 */           buttonPressed(c - 48 + 0);
/*     */           break;
/*     */       } 
/*     */     } 
/*     */   }
/*     */ }


/* Location:              /Users/nicholas/Desktop/old-knt/old-jar/program.jar!/vu/storm/touch/gfx/Numpad.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */