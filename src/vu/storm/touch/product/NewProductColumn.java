/*    */ package vu.storm.touch.product;
/*    */ 
/*    */ import java.awt.Color;
/*    */ import java.awt.Component;
/*    */ import java.awt.LayoutManager;
/*    */ import java.awt.event.ActionListener;
/*    */ import java.awt.event.ComponentEvent;
/*    */ import java.awt.event.ComponentListener;
/*    */ import javax.swing.JComponent;
/*    */ import javax.swing.JPanel;
/*    */ import vu.storm.administration.ICategory;
/*    */ import vu.storm.touch.gfx.ButtonProvider;
/*    */ import vu.storm.touch.gfx.PageButton;
/*    */ import vu.storm.touch.gfx.TouchColumn;
/*    */ 
/*    */ public class NewProductColumn
/*    */   extends JPanel implements ButtonProvider, ComponentListener {
/*    */   TouchColumn touchColumn;
/*    */   ProductScreen parent;
/*    */   PageButton upButton;
/*    */   PageButton downButton;
/*    */   ICategory category;
/*    */   
/*    */   public NewProductColumn(ProductScreen parent, ICategory category) {
/* 25 */     this.parent = parent;
/* 26 */     this.category = category;
/* 27 */     this.upButton = new PageButton(true, true);
/* 28 */     this.downButton = new PageButton(true, false);
/* 29 */     this.touchColumn = new TouchColumn(this, 0.1D, 0.9166666666666666D);
/* 30 */     add((Component)this.touchColumn);
/* 31 */     setLayout((LayoutManager)null);
/* 32 */     this.touchColumn.setBounds(0, 0, getWidth(), getHeight());
/* 33 */     addComponentListener(this);
/* 34 */     setBackground(Color.gray);
/*    */   }
/*    */   
/*    */   void updateFilter() {
/* 38 */     this.touchColumn.rereadFilter();
/*    */   }
/*    */ 
/*    */   
/*    */   public JComponent createButton(int index) {
/* 43 */     return new ProductButton(this, this.category.getProduct(index));
/*    */   }
/*    */   
/*    */   public JComponent createNextPageButton(ActionListener actionListener) {
/* 47 */     this.downButton.addActionListener(actionListener);
/* 48 */     return (JComponent)this.downButton;
/*    */   }
/*    */   
/*    */   public JComponent createPrevPageButton(ActionListener actionListener) {
/* 52 */     this.upButton.addActionListener(actionListener);
/* 53 */     return (JComponent)this.upButton;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void destroyButton(JComponent button) {}
/*    */ 
/*    */   
/*    */   public int numButtons() {
/* 62 */     return this.category.numProducts();
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void componentHidden(ComponentEvent arg0) {}
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void componentMoved(ComponentEvent arg0) {}
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void componentResized(ComponentEvent arg0) {
/* 80 */     this.touchColumn.setBounds(0, 0, getWidth(), getHeight());
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void componentShown(ComponentEvent arg0) {}
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean matchesFilter(Object filter, JComponent button) {
/* 91 */     return true;
/*    */   }
/*    */ }


/* Location:              /Users/nicholas/Desktop/old-knt/old-jar/program.jar!/vu/storm/touch/product/NewProductColumn.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */