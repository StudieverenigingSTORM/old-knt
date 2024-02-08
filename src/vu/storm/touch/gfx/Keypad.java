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
/*     */ import java.security.InvalidParameterException;
/*     */ import javax.swing.JPanel;
/*     */ 
/*     */ public class Keypad
/*     */   extends JPanel
/*     */   implements ActionListener, ComponentListener, KeyListener {
/*     */   private static final int GRID_WIDTH_0 = 40;
/*     */   private static final int GRID_HEIGHT_0 = 12;
/*  19 */   private static final char[] BUTTON_VALUES_0 = new char[] { 
/*  20 */       'Q', 'W', 'E', 'R', 'T', 'Y', 'U', 'I', 'O', 'P', 
/*  21 */       'A', 'S', 'D', 'F', 'G', 'H', 'J', 'K', 'L', 
/*  22 */       'Z', 'X', 'C', 'V', 'B', 'N', 'M', '\b' };
/*     */   
/*     */   private static final String ALLOWED_HARD_KEYS_0 = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ\b";
/*     */   private static final String TRANSLATED_HARD_KEYS_0 = "ABCDEFGHIJKLMNOPQRSTUVWXYZABCDEFGHIJKLMNOPQRSTUVWXYZ\b";
/*  26 */   private static final int[] BUTTON_SIZES_X_0 = new int[] { 
/*  27 */       4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 
/*  28 */       4, 4, 4, 4, 4, 4, 4, 4, 4, 
/*  29 */       4, 4, 4, 4, 4, 4, 4, 8 };
/*     */   
/*  31 */   private static final int[] BUTTON_SIZES_Y_0 = new int[] { 
/*  32 */       4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 
/*  33 */       4, 4, 4, 4, 4, 4, 4, 4, 4, 
/*  34 */       4, 4, 4, 4, 4, 4, 4, 4 };
/*     */   
/*  36 */   private static final int[] BUTTON_X_0 = new int[] { 
/*  37 */       0, 4, 8, 12, 16, 20, 24, 28, 32, 36, 
/*  38 */       1, 5, 9, 13, 17, 21, 25, 29, 33, 
/*  39 */       3, 7, 11, 15, 19, 23, 27, 31 };
/*     */   
/*  41 */   private static final int[] BUTTON_Y_0 = new int[] { 
/*     */       0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 
/*  43 */       4, 4, 4, 4, 4, 4, 4, 4, 4, 
/*  44 */       8, 8, 8, 8, 8, 8, 8, 8 };
/*     */   
/*  46 */   private static final String[] BUTTON_LABELS_0 = new String[] { 
/*  47 */       "Q", "W", "E", "R", "T", "Y", "U", "I", "O", "P", 
/*  48 */       "A", "S", "D", "F", "G", "H", "J", "K", "L", 
/*  49 */       "Z", "X", "C", "V", "B", "N", "M", "<--" };
/*     */   
/*     */   private static final int NUM_BUTTONS_0 = 27;
/*     */   
/*     */   private static final int GRID_WIDTH_1 = 20;
/*     */   
/*     */   private static final int GRID_HEIGHT_1 = 16;
/*  56 */   private static final char[] BUTTON_VALUES_1 = new char[] { 
/*  57 */       '7', '8', '9', '\b', 
/*  58 */       '4', '5', '6', '\033', 
/*  59 */       '1', '2', '3', '\n', 
/*  60 */       '0' };
/*     */   
/*     */   private static final String ALLOWED_HARD_KEYS_1 = "0123456789\b\033\n\r";
/*     */   private static final String TRANSLATED_HARD_KEYS_1 = "0123456789\b\033\n\n";
/*  64 */   private static final int[] BUTTON_SIZES_X_1 = new int[] { 
/*  65 */       4, 4, 4, 8, 
/*  66 */       4, 4, 4, 8, 
/*  67 */       4, 4, 4, 8, 
/*  68 */       4 };
/*     */   
/*  70 */   private static final int[] BUTTON_SIZES_Y_1 = new int[] { 
/*  71 */       4, 4, 4, 4, 
/*  72 */       4, 4, 4, 4, 
/*  73 */       4, 4, 4, 4, 
/*  74 */       4 };
/*     */   
/*  76 */   private static final int[] BUTTON_X_1 = new int[] {
/*  77 */       0, 4, 8, 12, 
/*  78 */       4, 8, 12, 
/*  79 */       4, 8, 12
/*     */     };
/*     */   
/*  82 */   private static final int[] BUTTON_Y_1 = new int[] {
/*     */       
/*  84 */       0, 0, 0, 0, 4, 4, 4, 4, 
/*  85 */       8, 8, 8, 8, 
/*  86 */       12
/*     */     };
/*  88 */   private static final String[] BUTTON_LABELS_1 = new String[] { 
/*  89 */       "7", "8", "9", "Corr", 
/*  90 */       "4", "5", "6", "Stop", 
/*  91 */       "1", "2", "3", "OK", 
/*  92 */       "0" };
/*     */   
/*     */   private static final int NUM_BUTTONS_1 = 13;
/*     */   
/*     */   public static final int LAYOUT_ALPHA_ONLY = 0;
/*     */   
/*     */   public static final int LAYOUT_PIN = 1;
/*     */   
/*     */   private KeypadButton[] buttons;
/*     */   private KeypadListener keypadListener;
/*     */   private char[] buttonValues;
/*     */   private int[] buttonSizesX;
/*     */   private int[] buttonSizesY;
/*     */   private int[] buttonX;
/*     */   private int[] buttonY;
/*     */   private String[] buttonLabels;
/*     */   private String allowedHardKeys;
/*     */   private String translatedHardKeys;
/*     */   private int numButtons;
/*     */   private int gridWidth;
/*     */   private int gridHeight;
/*     */   private boolean stretched;
/*     */   
/*     */   public Keypad(int layout) {
/* 116 */     this(layout, false);
/*     */   }
/*     */   
/*     */   public Keypad(int layout, boolean stretched) {
/* 120 */     this.stretched = stretched;
/* 121 */     switch (layout) {
/*     */       
/*     */       case 0:
/* 124 */         this.buttonValues = BUTTON_VALUES_0;
/* 125 */         this.buttonSizesX = BUTTON_SIZES_X_0;
/* 126 */         this.buttonSizesY = BUTTON_SIZES_Y_0;
/* 127 */         this.buttonX = BUTTON_X_0;
/* 128 */         this.buttonY = BUTTON_Y_0;
/* 129 */         this.buttonLabels = BUTTON_LABELS_0;
/* 130 */         this.gridWidth = 40;
/* 131 */         this.gridHeight = 12;
/* 132 */         this.numButtons = 27;
/* 133 */         this.allowedHardKeys = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ\b";
/* 134 */         this.translatedHardKeys = "ABCDEFGHIJKLMNOPQRSTUVWXYZABCDEFGHIJKLMNOPQRSTUVWXYZ\b";
/*     */         break;
/*     */       case 1:
/* 137 */         this.buttonValues = BUTTON_VALUES_1;
/* 138 */         this.buttonSizesX = BUTTON_SIZES_X_1;
/* 139 */         this.buttonSizesY = BUTTON_SIZES_Y_1;
/* 140 */         this.buttonX = BUTTON_X_1;
/* 141 */         this.buttonY = BUTTON_Y_1;
/* 142 */         this.buttonLabels = BUTTON_LABELS_1;
/* 143 */         this.gridWidth = 20;
/* 144 */         this.gridHeight = 16;
/* 145 */         this.numButtons = 13;
/* 146 */         this.allowedHardKeys = "0123456789\b\033\n\r";
/* 147 */         this.translatedHardKeys = "0123456789\b\033\n\n";
/*     */         break;
/*     */       default:
/* 150 */         throw new InvalidParameterException("Layout should be a valid layout");
/*     */     } 
/* 152 */     setLayout((LayoutManager)null);
/* 153 */     this.keypadListener = null;
/* 154 */     this.buttons = new KeypadButton[this.numButtons];
/* 155 */     for (int i = 0; i < this.numButtons; i++) {
/*     */       
/* 157 */       this.buttons[i] = new KeypadButton(this, this.buttonLabels[i], this.buttonValues[i], this.buttonSizesX[i], this.buttonSizesY[i]);
/* 158 */       this.buttons[i].addKeyListener(this);
/* 159 */       add(this.buttons[i]);
/*     */     } 
/* 161 */     setBackground(new Color(192, 192, 192));
/* 162 */     addComponentListener(this);
/* 163 */     addKeyListener(this);
/* 164 */     setFocusable(true);
/* 165 */     setFocusCycleRoot(true);
/* 166 */     setFocusTraversalKeysEnabled(false);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void actionPerformed(ActionEvent e) {}
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
/* 183 */     int wcell = getWidth() / this.gridWidth;
/* 184 */     int hcell = getHeight() / this.gridHeight;
/* 185 */     int xoffset = 0;
/* 186 */     int yoffset = 0;
/* 187 */     if (!this.stretched)
/*     */     {
/* 189 */       if (wcell < hcell) {
/*     */ 
/*     */         
/* 192 */         hcell = wcell;
/* 193 */         yoffset = (getHeight() - hcell * this.gridHeight) / 2;
/*     */       }
/* 195 */       else if (hcell < wcell) {
/*     */ 
/*     */         
/* 198 */         wcell = hcell;
/* 199 */         xoffset = (getWidth() - wcell * this.gridWidth) / 2;
/*     */       } 
/*     */     }
/* 202 */     for (int i = 0; i < this.numButtons; i++) {
/*     */ 
/*     */       
/* 205 */       this.buttons[i].setButtonSize(this.buttonSizesX[i], this.buttonSizesY[i]);
/* 206 */       this.buttons[i].setBounds(xoffset + this.buttonX[i] * wcell, yoffset + this.buttonY[i] * hcell, this.buttonSizesX[i] * wcell, this.buttonSizesY[i] * hcell);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void componentShown(ComponentEvent arg0) {}
/*     */ 
/*     */ 
/*     */   
/*     */   public void takeKeyboard() {
/* 217 */     requestFocusInWindow();
/*     */   }
/*     */ 
/*     */   
/*     */   protected void keypadPressed(char key) {
/* 222 */     if (this.keypadListener != null)
/*     */     {
/* 224 */       this.keypadListener.keypadPressed(this, key);
/*     */     }
/*     */   }
/*     */   
/*     */   public void addKeypadListener(KeypadListener listener) {
/* 229 */     this.keypadListener = KeypadListenerChain.add(this.keypadListener, listener);
/*     */   }
/*     */   
/*     */   public void removeKeypadListener(KeypadListener listener) {
/* 233 */     this.keypadListener = KeypadListenerChain.remove(this.keypadListener, listener);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void keyPressed(KeyEvent e) {}
/*     */ 
/*     */   
/*     */   public void keyReleased(KeyEvent e) {}
/*     */ 
/*     */   
/*     */   public void keyTyped(KeyEvent e) {
/*     */     int i;
/* 246 */     if ((i = this.allowedHardKeys.indexOf(String.valueOf(e.getKeyChar()))) != -1)
/*     */     {
/* 248 */       keypadPressed(this.translatedHardKeys.charAt(i));
/*     */     }
/*     */   }
/*     */ }


/* Location:              /Users/nicholas/Desktop/old-knt/old-jar/program.jar!/vu/storm/touch/gfx/Keypad.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */