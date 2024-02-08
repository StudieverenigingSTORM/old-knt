/*     */ package vu.storm.administration;
/*     */ 
/*     */ import java.io.FileInputStream;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.io.UnsupportedEncodingException;
/*     */ import java.security.MessageDigest;
/*     */ import java.security.NoSuchAlgorithmException;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class Hash
/*     */ {
/*     */   public static byte[] createFileHash(String filename, String method) {
/*     */     try {
/*  18 */       InputStream fis = new FileInputStream(filename);
/*     */       
/*  20 */       byte[] buffer = new byte[1024];
/*  21 */       MessageDigest complete = MessageDigest.getInstance(method);
/*  22 */       int numRead = 0;
/*  23 */       while (numRead != -1) {
/*  24 */         numRead = fis.read(buffer);
/*  25 */         if (numRead > 0) {
/*  26 */           complete.update(buffer, 0, numRead);
/*     */         }
/*     */       } 
/*  29 */       fis.close();
/*  30 */       return complete.digest();
/*     */     }
/*  32 */     catch (NoSuchAlgorithmException nsae) {
/*  33 */       return null;
/*     */ 
/*     */     
/*     */     }
/*  37 */     catch (IOException e) {
/*  38 */       return null;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static byte[] createHash(String text, String method) {
/*     */     try {
/*     */       byte[] b;
/*     */       try {
/*  49 */         b = text.getBytes("UTF-8");
/*     */       }
/*  51 */       catch (UnsupportedEncodingException uee) {
/*     */         
/*  53 */         b = text.getBytes();
/*     */       } 
/*  55 */       MessageDigest algorithm = MessageDigest.getInstance(method);
/*  56 */       algorithm.reset();
/*  57 */       algorithm.update(b);
/*  58 */       byte[] messageDigest = algorithm.digest();
/*  59 */       return messageDigest;
/*     */     
/*     */     }
/*  62 */     catch (NoSuchAlgorithmException nsae) {
/*  63 */       return null;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static String getFileHash(String filename) {
/*     */     try {
/*  73 */       byte[] b = createFileHash(filename, "SHA-1");
/*  74 */       return asHex(b);
/*     */     }
/*  76 */     catch (Exception e) {
/*  77 */       return null;
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public static String getHash(String text) {
/*     */     try {
/*  84 */       byte[] b = createHash(text, "SHA-1");
/*  85 */       return asHex(b);
/*     */     }
/*  87 */     catch (Exception e) {
/*  88 */       return null;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static String asHex(byte[] b) {
/*  99 */     String result = "";
/* 100 */     if (b != null)
/* 101 */       for (int i = 0; i < b.length; i++) {
/* 102 */         result = String.valueOf(result) + 
/* 103 */           Integer.toString((b[i] & 0xFF) + 256, 16).substring(1);
/*     */       } 
/* 105 */     return result;
/*     */   }
/*     */ }


/* Location:              /Users/nicholas/Desktop/old-knt/old-jar/program.jar!/vu/storm/administration/Hash.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */