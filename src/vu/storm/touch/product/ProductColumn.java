/*    */ package vu.storm.touch.product;
/*    */ 
/*    */ import java.awt.Color;
/*    */ import java.awt.Font;
/*    */ import java.awt.LayoutManager;
/*    */ import javax.swing.BoxLayout;
/*    */ import javax.swing.JLabel;
/*    */ import javax.swing.JPanel;
/*    */ import javax.swing.JScrollPane;
/*    */ import javax.swing.border.Border;
/*    */ import vu.storm.administration.ICategory;
/*    */ import vu.storm.touch.GUIPanel;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ProductColumn
/*    */   extends JPanel
/*    */ {
/*    */   ProductScreen parent;
/*    */   ICategory category;
/*    */   JLabel lHeader;
/*    */   JScrollPane container;
/*    */   JPanel buttonPanel;
/*    */   BoxLayout layout;
/*    */   ProductButton[] buttons;
/*    */   
/*    */   public ProductColumn(ProductScreen parent, ICategory category) {
/* 28 */     this.parent = parent;
/* 29 */     this.category = category;
/* 30 */     setBackground(Color.RED);
/* 31 */     this.buttonPanel = new JPanel();
/* 32 */     this.container = new JScrollPane(this.buttonPanel, 21, 31);
/* 33 */     this.container.setAutoscrolls(true);
/* 34 */     this.container.setBorder((Border)null);
/* 35 */     setLayout((LayoutManager)null);
/* 36 */     this.layout = new BoxLayout(this.buttonPanel, 1);
/* 37 */     this.buttonPanel.setLayout(this.layout);
/* 38 */     this.buttonPanel.setBackground(new Color(128, 154, 205));
/* 39 */     add(this.container);
/* 40 */     this.lHeader = new JLabel(category.getName());
/* 41 */     this.lHeader.setHorizontalAlignment(0);
/* 42 */     this.container.setColumnHeaderView(this.lHeader);
/* 43 */     this.buttons = new ProductButton[category.numProducts()];
/* 44 */     for (int i = 0; i < category.numProducts(); i++)
/*    */     {
/*    */       
/* 47 */       this.buttonPanel.add(this.buttons[i]);
/*    */     }
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public GUIPanel getMasterPanel() {
/* 54 */     return this.parent.getMasterPanel();
/*    */   }
/*    */   
/*    */   public void rescale() {
/* 58 */     this.lHeader.setFont(new Font("Verdana", 0, this.parent.scaleFontSize(24)));
/* 59 */     this.container.setBounds(0, 0, getWidth(), getHeight());
/* 60 */     for (int i = 0; i < this.buttons.length; i++)
/* 61 */       this.buttons[i].rescale(); 
/*    */   }
/*    */ }


/* Location:              /Users/nicholas/Desktop/old-knt/old-jar/program.jar!/vu/storm/touch/product/ProductColumn.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */