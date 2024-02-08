/*    */ package vu.storm.administration;
/*    */ 
/*    */ import java.util.Properties;
/*    */ 
/*    */ public class DatabaseFactory
/*    */ {
/*    */   public static IDatabase openDatabase() {
/*  8 */     return new Database();
/*    */   }
/*    */   
/*    */   public static IDatabase openDatabase(Properties props) {
/* 12 */     return new Database(props);
/*    */   }
/*    */ }


/* Location:              /Users/nicholas/Desktop/old-knt/old-jar/program.jar!/vu/storm/administration/DatabaseFactory.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */