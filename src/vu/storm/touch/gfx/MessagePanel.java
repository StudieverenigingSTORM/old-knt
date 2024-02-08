/*     */ package vu.storm.touch.gfx;
/*     */ 
/*     */ import java.awt.Color;
/*     */ import java.awt.LayoutManager;
/*     */ import java.awt.event.ComponentEvent;
/*     */ import java.awt.event.ComponentListener;
/*     */ import javax.swing.JLabel;
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
/*     */ class MessagePanel
/*     */   extends JPanel
/*     */   implements ComponentListener
/*     */ {
/*     */   JLabel messageLabel;
/*     */   
/*     */   public MessagePanel(Color background, Color foreground) {
/* 165 */     this.messageLabel = new JLabel();
/* 166 */     setBackground(background);
/* 167 */     this.messageLabel.setForeground(foreground);
/* 168 */     setLayout((LayoutManager)null);
/* 169 */     add(this.messageLabel);
/* 170 */     addComponentListener(this);
/*     */   }
/*     */   
/*     */   public void setText(String text) {
/* 174 */     this.messageLabel.setText(text);
/* 175 */     this.messageLabel.setFont(GfxUtil.fitFont(this.messageLabel, "Verdana", 1, this.messageLabel.getText(), getHeight(), getWidth()));
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
/*     */   public void componentResized(ComponentEvent e) {
/* 188 */     this.messageLabel.setBounds(0, 0, getWidth(), getHeight());
/* 189 */     this.messageLabel.setFont(GfxUtil.fitFont(this.messageLabel, "Verdana", 1, this.messageLabel.getText(), getHeight(), getWidth()));
/*     */   }
/*     */   
/*     */   public void componentShown(ComponentEvent e) {}
/*     */ }


/* Location:              /Users/nicholas/Desktop/old-knt/old-jar/program.jar!/vu/storm/touch/gfx/MessagePanel.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */