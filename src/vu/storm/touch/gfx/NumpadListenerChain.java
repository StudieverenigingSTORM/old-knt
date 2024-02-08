/*     */ package vu.storm.touch.gfx;
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
/*     */ class NumpadListenerChain
/*     */   implements NumpadListener
/*     */ {
/*     */   private NumpadListener a;
/*     */   private NumpadListener b;
/*     */   
/*     */   private NumpadListenerChain(NumpadListener a, NumpadListener b) {
/* 194 */     this.a = a;
/* 195 */     this.b = b;
/*     */   }
/*     */   
/*     */   protected static NumpadListener add(NumpadListener a, NumpadListener b) {
/* 199 */     if (a == null) return b; 
/* 200 */     if (b == null) return a; 
/* 201 */     return new NumpadListenerChain(a, b);
/*     */   }
/*     */   
/*     */   protected static NumpadListener remove(NumpadListener l, NumpadListener oldl) {
/* 205 */     if (l == null) return null; 
/* 206 */     if (oldl == null) return l; 
/* 207 */     if (l instanceof NumpadListenerChain)
/*     */     {
/* 209 */       return add(remove(((NumpadListenerChain)l).a, oldl), remove(((NumpadListenerChain)l).b, oldl));
/*     */     }
/*     */ 
/*     */     
/* 213 */     if (l == oldl) return null; 
/* 214 */     return l;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void numpadPressed(Numpad sender, int code) {
/* 220 */     this.a.numpadPressed(sender, code);
/* 221 */     this.b.numpadPressed(sender, code);
/*     */   }
/*     */ }


/* Location:              /Users/nicholas/Desktop/old-knt/old-jar/program.jar!/vu/storm/touch/gfx/NumpadListenerChain.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */