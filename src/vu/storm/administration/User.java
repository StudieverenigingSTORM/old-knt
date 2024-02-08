/*     */ package vu.storm.administration;
/*     */ 
/*     */ 
/*     */ 
/*     */ public class User
/*     */   extends IUser
/*     */ {
/*     */   private long balance;
/*     */   private String login;
/*     */   private String firstname;
/*     */   private String betweenname;
/*     */   private String lastname;
/*     */   
/*     */   User(Database db, long id, String login, String firstname, String betweenname, String lastname, long balance, boolean isBorrelUser, boolean borrelHasMax, boolean isBorrelCie, String pin) {
/*  15 */     this.db = db;
/*  16 */     this.id = id;
/*  17 */     this.login = login;
/*  18 */     this.firstname = firstname;
/*  19 */     this.betweenname = (betweenname == null) ? "" : betweenname;
/*  20 */     this.lastname = (lastname == null) ? "" : lastname;
/*  21 */     this.balance = balance;
/*  22 */     this.isBorrelUser = isBorrelUser;
/*  23 */     this.borrelHasMax = borrelHasMax;
/*  24 */     this.isBorrelCie = isBorrelCie;
/*  25 */     this.pin = pin;
/*     */   }
/*     */   private long id; private boolean isBorrelUser; private boolean isBorrelCie; private boolean borrelHasMax; private Database db; private String pin;
/*     */   long getId() {
/*  29 */     return this.id;
/*     */   }
/*     */   
/*     */   void decreaseBalance(long amount) {
/*  33 */     this.balance -= amount;
/*     */   }
/*     */   
/*     */   void increaseBalance(long amount) {
/*  37 */     this.balance += amount;
/*     */   }
/*     */   
/*     */   boolean nameLike(String search) {
/*  41 */     if (!this.firstname.toLowerCase().startsWith(search.toLowerCase()) && 
/*  42 */       !this.lastname.toLowerCase().startsWith(search.toLowerCase())) return false; 
/*     */     return true;
/*     */   }
/*     */   public long getBalance() {
/*  46 */     return this.balance;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getLogin() {
/*  51 */     return this.login;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getName() {
/*  56 */     return String.valueOf(this.firstname) + ((this.betweenname.length() > 0) ? (" " + this.betweenname + " ") : " ") + this.lastname;
/*     */   }
/*     */   
/*     */   public boolean matchesSearch(String search) {
/*  60 */     if (search == null) {
/*  61 */       return true;
/*     */     }
/*     */     
/*  64 */     return nameLike(search);
/*     */   }
/*     */   
/*     */   public boolean isBorrelUser() {
/*  68 */     return this.isBorrelUser;
/*     */   }
/*     */   
/*     */   public boolean borrelHasMax() {
/*  72 */     return this.borrelHasMax;
/*     */   }
/*     */   
/*     */   public long totalOrdersPrice() {
/*  76 */     return this.db.totalOrdersPrice(this);
/*     */   }
/*     */   
/*     */   public boolean isBorrelCie() {
/*  80 */     return this.isBorrelCie;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean authenticatePin(String pin) {
/*  85 */     if (this.pin.startsWith("P-"))
/*     */     {
/*  87 */       return this.pin.substring(2).equals(pin);
/*     */     }
/*  89 */     if (this.pin.startsWith("p-")) {
/*     */       
/*  91 */       String[] pinParts = this.pin.split("-");
/*  92 */       if (pinParts.length < 3) return false; 
/*  93 */       if (pinParts[2].equalsIgnoreCase(Hash.asHex(Hash.createHash(String.valueOf(pinParts[1]) + pin, "SHA-1")))) return true; 
/*  94 */       return false;
/*     */     } 
/*     */ 
/*     */     
/*  98 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean hasPin() {
/* 104 */     return (this.pin != null && this.pin.length() > 0);
/*     */   }
/*     */ }


/* Location:              /Users/nicholas/Desktop/old-knt/old-jar/program.jar!/vu/storm/administration/User.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */