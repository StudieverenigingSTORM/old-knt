/*    */ package vu.storm.touch.gfx;
/*    */ 
/*    */ import java.awt.event.ActionEvent;
/*    */ import java.awt.event.MouseEvent;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class LongPressButton
/*    */   extends StandardStyledButton
/*    */ {
/* 12 */   Object pressedLock = new Object();
/*    */   
/*    */   Thread tLongPress;
/*    */   
/*    */   public LongPressButton() {
/* 17 */     this(false);
/*    */   }
/*    */   
/*    */   public LongPressButton(boolean inverted) {
/* 21 */     super(inverted);
/*    */   }
/*    */   
/*    */   public void mousePressed(MouseEvent e) {
/* 25 */     synchronized (this.pressedLock) {
/* 26 */       if (this.pressed)
/* 27 */         return;  this.pressed = true;
/*    */     } 
/* 29 */     this.tLongPress = new Thread()
/*    */       {
/*    */         public void run()
/*    */         {
/*    */           try {
/* 34 */             Thread.sleep(600L);
/* 35 */             if (LongPressButton.this.pressed) LongPressButton.this.longPressDone();
/*    */           
/* 37 */           } catch (InterruptedException interruptedException) {}
/*    */         }
/*    */       };
/*    */ 
/*    */ 
/*    */     
/* 43 */     this.tLongPress.start();
/* 44 */     repaint();
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void mouseReleased(MouseEvent e) {
/* 50 */     synchronized (this.pressedLock) {
/* 51 */       if (!this.pressed)
/* 52 */         return;  this.pressed = false;
/*    */     } 
/* 54 */     this.tLongPress.interrupt(); 
/* 55 */     try { this.tLongPress.join(); } catch (InterruptedException interruptedException) {}
/* 56 */     press();
/*    */   }
/*    */ 
/*    */   
/*    */   public void longPressDone() {
/* 61 */     synchronized (this.pressedLock) {
/* 62 */       if (!this.pressed)
/* 63 */         return;  this.pressed = false;
/*    */     } 
/* 65 */     longPress();
/*    */   }
/*    */   
/*    */   protected void longPress() {
/* 69 */     repaint();
/* 70 */     if (this.lst != null) {
/*    */       
/* 72 */       ActionEvent event = new ActionEvent(this, 1001, "long");
/* 73 */       this.lst.actionPerformed(event);
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   protected void press() {
/* 79 */     repaint();
/* 80 */     if (this.lst != null) {
/*    */       
/* 82 */       ActionEvent event = new ActionEvent(this, 1001, "normal");
/* 83 */       this.lst.actionPerformed(event);
/*    */     } 
/*    */   }
/*    */ }


/* Location:              /Users/nicholas/Desktop/old-knt/old-jar/program.jar!/vu/storm/touch/gfx/LongPressButton.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */