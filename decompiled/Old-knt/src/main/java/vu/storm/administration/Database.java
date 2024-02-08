/*     */ package vu.storm.administration;
/*     */ import java.sql.*;
/*     */
/*     */
/*     */ import java.util.Properties;
/*     */ import java.util.Vector;
/*     */ 
/*     */ public class Database implements IDatabase {
/*     */   Connection conn;
/*  10 */   Vector<Category> categories = new Vector<Category>(); boolean dataRead = false;
/*  11 */   Vector<User> users = new Vector<User>();
/*  12 */   Vector<Image> promoImages = new Vector<Image>();
/*     */   
/*     */   private static final String DEFAULT_DB_CLASS = "org.sqlite.JDBC";
/*     */   
/*     */   private static final String DEFAULT_DB_DSN = "jdbc:sqlite:test.db";
/*     */   
/*     */   private static final String DB_CLASS_SQLITE = "org.sqlite.JDBC";
/*     */   private String dbClass;
/*     */   private String dbDsn;
/*     */   
/*     */   public Database() {
/*  23 */     this(new Properties());
/*     */   }
/*     */   public Database(Properties props) {
/*  26 */     this.dbClass = props.getProperty("db.class", "org.sqlite.JDBC");
/*  27 */     this.dbDsn = props.getProperty("db.dsn", "jdbc:sqlite:test.db");
/*  28 */     makeConnection();
/*     */   }
/*     */ 
/*     */   
/*     */   private void makeConnection() {
/*     */     try {
/*  34 */       Class.forName(this.dbClass);
/*  35 */       this.conn = DriverManager.getConnection(this.dbDsn);
/*     */     }
/*  37 */     catch (Exception e) {
/*     */ 
/*     */       
/*  40 */       e.printStackTrace();
/*  41 */       System.exit(1);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private void checkConnection() {
/*     */     try {
/*  48 */       if (this.conn.isValid(500))
/*     */         return; 
/*  50 */     } catch (Exception exception) {}
/*     */ 
/*     */     
/*  53 */     makeConnection();
/*     */   }
/*     */   
/*     */   public IOrder beginOrder(IUser user) {
/*  57 */     checkConnection();
/*  58 */     if (!this.dataRead) readDatabase(); 
/*  59 */     return new Order((User)user);
/*     */   }
/*     */ 
/*     */   
/*     */   public IUser[] getAllUsers() {
/*  64 */     checkConnection();
/*  65 */     if (!this.dataRead) readDatabase(); 
/*  66 */     return this.users.<IUser>toArray((IUser[])new User[0]);
/*     */   }
/*     */ 
/*     */   
/*     */   public ICategory[] getCategories() {
/*  71 */     checkConnection();
/*  72 */     if (!this.dataRead) readDatabase(); 
/*  73 */     return this.categories.<ICategory>toArray((ICategory[])new Category[0]);
/*     */   }
/*     */ 
/*     */   
/*     */   public IUser[] getUsers(String search) {
/*  78 */     checkConnection();
/*  79 */     if (!this.dataRead) readDatabase(); 
/*  80 */     Vector<User> v = new Vector<User>();
/*  81 */     for (int i = 0; i < this.users.size(); i++) {
/*     */       
/*  83 */       if (((User)this.users.elementAt(i)).nameLike(search))
/*     */       {
/*  85 */         v.add(this.users.elementAt(i));
/*     */       }
/*     */     } 
/*  88 */     return v.<IUser>toArray((IUser[])new User[0]);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private boolean getBooleanState(String stateName) {
/*  95 */     checkConnection();
/*  96 */     int res = 0;
/*     */     
/*     */     try {
/*  99 */       PreparedStatement prep = this.conn.prepareStatement("SELECT integer_val FROM db_state WHERE propname = ?");
/* 100 */       prep.setString(1, stateName);
/* 101 */       ResultSet rs = prep.executeQuery();
/* 102 */       if (rs.next())
/*     */       {
/* 104 */         res = rs.getInt(1);
/*     */       }
/* 106 */       rs.close();
/* 107 */       prep.close();
/*     */     }
/* 109 */     catch (Exception e) {
/*     */       
/* 111 */       System.err.println("Error retrieving state");
/* 112 */       e.printStackTrace();
/*     */     } 
/* 114 */     return (res != 0);
/*     */   }
/*     */   
/*     */   private void resetBooleanState(String stateName) {
/* 118 */     checkConnection();
/*     */     
/*     */     try {
/* 121 */       PreparedStatement prep = this.conn.prepareStatement("UPDATE db_state SET integer_val = 0 WHERE propname = ?");
/* 122 */       prep.setString(1, stateName);
/* 123 */       prep.execute();
/* 124 */       prep.close();
/*     */     }
/* 126 */     catch (Exception e) {
/*     */       
/* 128 */       System.err.println("Error setting state");
/* 129 */       e.printStackTrace();
/*     */     } 
/*     */   }
/*     */   
/*     */   public boolean hasUpdatedProducts() {
/* 134 */     if (!getBooleanState("products.updated")) return false;  
/* 135 */     try { readCategories(); resetBooleanState("products.updated"); return true; } catch (Exception exception)
/* 136 */     { return false; }
/*     */   
/*     */   }
/*     */   
/*     */   public boolean hasUpdatedUsers() {
/* 141 */     if (!getBooleanState("users.updated")) return false;  
/* 142 */     try { readUsers(); resetBooleanState("users.updated"); return true; } catch (Exception e) { System.err.println("Error when updating users"); e.printStackTrace();
/* 143 */       return false; }
/*     */   
/*     */   }
/*     */   public boolean hasRestartRequest() {
/* 147 */     if (!getBooleanState("system.restart")) return false;  
/* 148 */     try { readUsers(); resetBooleanState("system.restart"); return true; } catch (Exception exception)
/* 149 */     { return false; }
/*     */   
/*     */   }
/*     */   
/*     */   public boolean processOrder(IOrder order) {
/* 154 */     checkConnection();
/* 155 */     if (!this.dataRead) readDatabase(); 
/* 156 */     if (order.totalPrice() > order.getUser().getBalance()) return false; 
/* 157 */     boolean result = false;
/*     */     
/*     */     try {
/* 160 */       this.conn.setAutoCommit(false);
/*     */       
/* 162 */       long timestamp = System.currentTimeMillis();
/* 163 */       PreparedStatement prep = this.conn.prepareStatement("INSERT INTO orders (user_id, totalprice, timestamp) VALUES (?, ?, ?)");
/*     */ 
/*     */       
/* 166 */       prep.setLong(1, ((User)order.getUser()).getId());
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 172 */       prep.setLong(2, order.totalPrice());
/* 173 */       prep.setLong(3, timestamp);
/* 174 */       prep.executeUpdate();
/* 175 */       prep.close();
/*     */       
/* 177 */       prep = this.conn.prepareStatement("SELECT id FROM orders WHERE timestamp = ? AND user_id = ?");
/* 178 */       prep.setLong(1, timestamp);
/*     */ 
/*     */       
/* 181 */       prep.setLong(2, ((User)order.getUser()).getId());
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 188 */       ResultSet rs = prep.executeQuery();
/* 189 */       if (!rs.next()) throw new Exception("Failed to retrieve new order id"); 
/* 190 */       long orderId = rs.getLong(1);
/* 191 */       rs.close();
/* 192 */       prep.close();
/*     */       
/* 194 */       prep = this.conn.prepareStatement("INSERT INTO lineitems (order_id, product_id, price, amount) VALUES (?,?,?,?)");
/* 195 */       for (int i = 0; i < order.numLineItems(); i++) {
/*     */         
/* 197 */         prep.setLong(1, orderId);
/* 198 */         prep.setLong(2, ((Product)order.getLineItem(i).getProduct()).getId());
/* 199 */         prep.setLong(3, ((Product)order.getLineItem(i).getProduct()).getPrice());
/* 200 */         prep.setLong(4, order.getLineItem(i).getAmount());
/* 201 */         prep.addBatch();
/*     */       } 
/* 203 */       prep.executeBatch();
/*     */       
/* 205 */       prep.close();
/* 206 */       if (((User)order.getUser()).isBorrelUser()) {
/*     */         
/* 208 */         prep = this.conn.prepareStatement("UPDATE users SET balance = balance + ? WHERE id = ?");
/*     */       }
/*     */       else {
/*     */         
/* 212 */         prep = this.conn.prepareStatement("UPDATE users SET balance = balance - ? WHERE id = ?");
/*     */       } 
/* 214 */       prep.setLong(1, order.totalPrice());
/* 215 */       prep.setLong(2, ((User)order.getUser()).getId());
/* 216 */       prep.execute();
/* 217 */       if (((User)order.getUser()).isBorrelUser() && !((User)order.getUser()).borrelHasMax()) {
/*     */         
/* 219 */         ((User)order.getUser()).increaseBalance(order.totalPrice());
/*     */       }
/*     */       else {
/*     */         
/* 223 */         ((User)order.getUser()).decreaseBalance(order.totalPrice());
/*     */       } 
/*     */       
/* 226 */       this.conn.commit();
/*     */       
/* 228 */       result = true;
/* 229 */       this.conn.setAutoCommit(true);
/* 230 */       return true;
/*     */     }
/* 232 */     catch (Exception e) {
/*     */       
/* 234 */       try { if (!result) this.conn.rollback();  } catch (Exception ee) { return false; }
/* 235 */        e.printStackTrace();
/* 236 */       return result;
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private void readCategories() throws Exception {
/* 242 */     checkConnection();
/* 243 */     this.categories.clear();
/* 244 */     Statement stmt = this.conn.createStatement();
/* 245 */     ResultSet rs = stmt.executeQuery("SELECT id,name,visible FROM categories WHERE visible=1 ORDER BY position,name");
/*     */     
/* 247 */     while (rs.next()) {
/*     */       
/* 249 */       Category cat = new Category(rs.getLong(1), rs.getString(2), rs.getBoolean(3));
/* 250 */       this.categories.add(cat);
/*     */     } 
/* 252 */     rs.close();
/* 253 */     stmt.close();
/* 254 */     for (int i = 0; i < this.categories.size(); i++)
/*     */     {
/* 256 */       readProducts(this.categories.elementAt(i));
/*     */     }
/*     */   }
/*     */   
/*     */   private void readProducts(Category cat) throws Exception {
/* 261 */     checkConnection();
/* 262 */     Statement stmt = this.conn.createStatement();
/* 263 */     ResultSet rs = stmt.executeQuery("SELECT id,name,price,description FROM products WHERE visible=1 AND category_id = " + String.valueOf(cat.id) + " ORDER BY position,name");
/*     */     
/* 265 */     while (rs.next()) {
/*     */       
/* 267 */       Product product = new Product(rs.getLong(1), rs.getString(2), rs.getString(4), rs.getLong(3));
/* 268 */       cat.addProduct(product);
/*     */     } 
/* 270 */     rs.close();
/* 271 */     stmt.close();
/*     */   }
/*     */   
/*     */   private void readUsers() throws Exception {
/* 275 */     checkConnection();
/* 276 */     this.users.clear();
/* 277 */     Statement stmt = this.conn.createStatement();
/* 278 */     ResultSet rs = stmt.executeQuery("SELECT id,login,firstname,betweenname,lastname,balance,isborrel,borrelhasmax,isborrelcie,pin FROM users ORDER BY firstname ASC, lastname ASC, balance ASC");
/*     */     
/* 280 */     while (rs.next()) {
/*     */       
/* 282 */       User user = new User(this, rs.getLong(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getLong(6), rs.getBoolean(7), rs.getBoolean(8), rs.getBoolean(9), rs.getString(10));
/* 283 */       this.users.add(user);
/*     */     } 
/* 285 */     rs.close();
/* 286 */     stmt.close();
/*     */   }
/*     */   
/*     */   private void readPromoImages() throws Exception {
/* 290 */     checkConnection();
/* 291 */     this.promoImages.clear();
/* 292 */     Statement stmt = this.conn.createStatement();
/* 293 */     ResultSet rs = stmt.executeQuery("SELECT id,name,data FROM images WHERE name LIKE 'promo_%';");
/*     */     
/* 295 */     while (rs.next()) {
/*     */       
/* 297 */       Image image = new Image(rs.getLong(1), rs.getString(2), rs.getBytes(3));
/* 298 */       this.promoImages.add(image);
/*     */     } 
/* 300 */     rs.close();
/* 301 */     stmt.close();
/*     */   }
/*     */   
/*     */   private int dbVersion() {
/* 305 */     checkConnection();
/* 306 */     int res = 0;
/*     */     
/*     */     try {
/* 309 */       PreparedStatement prep = this.conn.prepareStatement("SELECT MAX(version) FROM db_version");
/* 310 */       ResultSet rs = prep.executeQuery();
/* 311 */       if (rs.next())
/*     */       {
/* 313 */         res = rs.getInt(1);
/*     */       }
/* 315 */       rs.close();
/* 316 */       prep.close();
/*     */     }
/* 318 */     catch (Exception e) {
/*     */       
/* 320 */       System.err.println("Error retrieving version, this is normal during first run!");
/* 321 */       e.printStackTrace();
/*     */     } 
/* 323 */     return res;
/*     */   }
/*     */   
/*     */   private void setDbVersion(int version) {
/* 327 */     checkConnection();
/*     */     
/*     */     try {
/* 330 */       PreparedStatement prep = this.conn.prepareStatement("INSERT INTO db_version (version) VALUES (?)");
/* 331 */       prep.setInt(1, version);
/* 332 */       prep.execute();
/* 333 */       prep.close();
/*     */     }
/* 335 */     catch (Exception e) {
/*     */       
/* 337 */       System.err.println("Error setting version");
/*     */     } 
/*     */   }
/*     */   
/*     */   private void simpleQuery(String sql) throws SQLException {
/* 342 */     checkConnection();
/* 343 */     Statement stmt = this.conn.createStatement();
/* 344 */     stmt.execute(sql);
/* 345 */     stmt.close();
/*     */   }
/*     */   
/*     */   private void upgradeDatabase(int version) {
/* 349 */     checkConnection();
/*     */     
/* 351 */     if (version < 1) {
/*     */       
/* 353 */       System.err.println("Migrate to version 1");
/* 354 */       if (this.dbClass.equals("org.sqlite.JDBC")) {
/*     */ 
/*     */         
/*     */         try {
/* 358 */           simpleQuery("CREATE TABLE db_version (version INTEGER PRIMARY KEY);");
/* 359 */           simpleQuery("CREATE TABLE users (id INTEGER PRIMARY KEY AUTOINCREMENT, login TEXT, firstname TEXT, betweenname TEXT, lastname TEXT, balance INTEGER, isborrel INTEGER, borrelhasmax INTEGER);");
/* 360 */           simpleQuery("CREATE TABLE categories (id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT, visible INTEGER, position INTEGER);");
/* 361 */           simpleQuery("CREATE TABLE products (id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT, category_id INTEGER, price INTEGER, visible INTEGER, position INTEGER);");
/* 362 */           simpleQuery("CREATE TABLE orders (id INTEGER PRIMARY KEY AUTOINCREMENT, user_id INTEGER, totalprice INTEGER, timestamp INTEGER);");
/* 363 */           simpleQuery("CREATE TABLE lineitems (id INTEGER PRIMARY KEY AUTOINCREMENT, order_id INTEGER, product_id INTEGER, price INTEGER, amount INTEGER);");
/*     */         }
/* 365 */         catch (Exception e) {
/*     */           
/* 367 */           e.printStackTrace();
/*     */ 
/*     */ 
/*     */ 
/*     */           
/*     */           return;
/*     */         } 
/*     */       } else {
/*     */         try {
/* 376 */           simpleQuery("CREATE TABLE db_version (version BIGINT PRIMARY KEY);");
/* 377 */           simpleQuery("CREATE TABLE users (id BIGINT AUTO_INCREMENT PRIMARY KEY, login VARCHAR(255), firstname VARCHAR(255), betweenname VARCHAR(255), lastname VARCHAR(255), balance BIGINT, isborrel BOOLEAN, borrelhasmax BOOLEAN) DEFAULT CHARACTER SET = utf8, COLLATE = utf8_bin;");
/* 378 */           simpleQuery("CREATE TABLE categories (id BIGINT AUTO_INCREMENT PRIMARY KEY, name VARCHAR(255), visible BOOLEAN, position BIGINT) DEFAULT CHARACTER SET = utf8, COLLATE = utf8_bin;");
/* 379 */           simpleQuery("CREATE TABLE products (id BIGINT AUTO_INCREMENT PRIMARY KEY, name VARCHAR(255), category_id BIGINT, price BIGINT, visible BOOLEAN, position BIGINT) DEFAULT CHARACTER SET = utf8, COLLATE = utf8_bin;");
/* 380 */           simpleQuery("CREATE TABLE orders (id BIGINT AUTO_INCREMENT PRIMARY KEY, user_id BIGINT, totalprice BIGINT, timestamp BIGINT) DEFAULT CHARACTER SET = utf8, COLLATE = utf8_bin;");
/* 381 */           simpleQuery("CREATE TABLE lineitems (id BIGINT AUTO_INCREMENT PRIMARY KEY, order_id BIGINT, product_id BIGINT, price BIGINT, amount BIGINT) DEFAULT CHARACTER SET = utf8, COLLATE = utf8_bin;");
/*     */         }
/* 383 */         catch (Exception e) {
/*     */           
/* 385 */           e.printStackTrace();
/*     */           return;
/*     */         } 
/*     */       } 
/* 389 */       setDbVersion(1);
/*     */     } 
/* 391 */     if (version < 2) {
/*     */       
/* 393 */       System.err.println("Migrate to version 2");
/* 394 */       if (this.dbClass.equals("org.sqlite.JDBC")) {
/*     */ 
/*     */         
/*     */         try {
/* 398 */           simpleQuery("CREATE TABLE db_state (id INTEGER PRIMARY KEY AUTOINCREMENT,  propname TEXT, integer_val INTEGER, string_val TEXT);");
/* 399 */           simpleQuery("INSERT INTO db_state (propname,integer_val) VALUES ('users.updated',0);");
/* 400 */           simpleQuery("INSERT INTO db_state (propname,integer_val) VALUES ('products.updated',0);");
/*     */         }
/* 402 */         catch (Exception e) {
/*     */           
/* 404 */           e.printStackTrace();
/*     */ 
/*     */ 
/*     */ 
/*     */           
/*     */           return;
/*     */         } 
/*     */       } else {
/*     */         try {
/* 413 */           simpleQuery("CREATE TABLE db_state (id INTEGER AUTO_INCREMENT PRIMARY KEY, propname VARCHAR(255), integer_val INTEGER, string_val VARCHAR(255) ) DEFAULT CHARACTER SET = utf8, COLLATE = utf8_bin;");
/* 414 */           simpleQuery("INSERT INTO db_state (propname,integer_val) VALUES ('users.updated',0),('products.updated',0);");
/*     */         }
/* 416 */         catch (Exception e) {
/*     */           
/* 418 */           e.printStackTrace();
/*     */           return;
/*     */         } 
/*     */       } 
/* 422 */       setDbVersion(2);
/*     */     } 
/* 424 */     if (version < 3) {
/*     */       
/* 426 */       System.err.println("Migrate to version 3");
/* 427 */       if (this.dbClass.equals("org.sqlite.JDBC")) {
/*     */ 
/*     */         
/*     */         try {
/* 431 */           simpleQuery("ALTER TABLE products ADD description TEXT;");
/*     */         }
/* 433 */         catch (Exception e) {
/*     */           
/* 435 */           e.printStackTrace();
/*     */ 
/*     */ 
/*     */           
/*     */           return;
/*     */         } 
/*     */       } else {
/*     */         try {
/* 443 */           simpleQuery("ALTER TABLE products ADD description VARCHAR(255) CHARACTER SET utf8 COLLATE utf8_bin;");
/*     */         }
/* 445 */         catch (Exception e) {
/*     */           
/* 447 */           e.printStackTrace();
/*     */           return;
/*     */         } 
/*     */       } 
/* 451 */       setDbVersion(3);
/*     */     } 
/* 453 */     if (version < 4) {
/*     */       
/* 455 */       System.err.println("Migrate to version 4");
/* 456 */       if (this.dbClass.equals("org.sqlite.JDBC")) {
/*     */ 
/*     */         
/*     */         try {
/* 460 */           simpleQuery("CREATE TABLE images (id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT, data BLOB);");
/*     */         }
/* 462 */         catch (Exception e) {
/*     */           
/* 464 */           e.printStackTrace();
/*     */ 
/*     */ 
/*     */           
/*     */           return;
/*     */         } 
/*     */       } else {
/*     */         try {
/* 472 */           simpleQuery("CREATE TABLE images (id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY , name VARCHAR( 255 ) , data BLOB NOT NULL) CHARACTER SET utf8 COLLATE utf8_bin;");
/*     */         }
/* 474 */         catch (Exception e) {
/*     */           
/* 476 */           e.printStackTrace();
/*     */           return;
/*     */         } 
/*     */       } 
/* 480 */       setDbVersion(4);
/*     */     } 
/* 482 */     if (version < 5) {
/*     */       
/* 484 */       System.err.println("Migrate to version 5");
/* 485 */       if (this.dbClass.equals("org.sqlite.JDBC")) {
/*     */ 
/*     */         
/*     */         try {
/* 489 */           simpleQuery("ALTER TABLE users ADD isborrelcie INTEGER;");
/*     */         }
/* 491 */         catch (Exception e) {
/*     */           
/* 493 */           e.printStackTrace();
/*     */ 
/*     */ 
/*     */           
/*     */           return;
/*     */         } 
/*     */       } else {
/*     */         try {
/* 501 */           simpleQuery("ALTER TABLE users ADD isborrelcie INTEGER;");
/*     */         }
/* 503 */         catch (Exception e) {
/*     */           
/* 505 */           e.printStackTrace();
/*     */           return;
/*     */         } 
/*     */       } 
/* 509 */       setDbVersion(5);
/*     */     } 
/* 511 */     if (version < 6) {
/*     */       
/* 513 */       System.err.println("Migrate to version 6");
/* 514 */       if (this.dbClass.equals("org.sqlite.JDBC")) {
/*     */ 
/*     */         
/*     */         try {
/* 518 */           simpleQuery("ALTER TABLE users ADD pin TEXT;");
/*     */         }
/* 520 */         catch (Exception e) {
/*     */           
/* 522 */           e.printStackTrace();
/*     */ 
/*     */ 
/*     */           
/*     */           return;
/*     */         } 
/*     */       } else {
/*     */         try {
/* 530 */           simpleQuery("ALTER TABLE users ADD pin VARCHAR(255) CHARACTER SET utf8 COLLATE utf8_bin;");
/*     */         }
/* 532 */         catch (Exception e) {
/*     */           
/* 534 */           e.printStackTrace();
/*     */           return;
/*     */         } 
/*     */       } 
/* 538 */       setDbVersion(6);
/*     */     } 
/* 540 */     if (version < 7) {
/*     */       
/* 542 */       System.err.println("Migrate to version 7");
/*     */       
/*     */       try {
/* 545 */         simpleQuery("INSERT INTO db_state (propname,integer_val) VALUES ('system.restart',0);");
/*     */       }
/* 547 */       catch (Exception e) {
/*     */         
/* 549 */         e.printStackTrace();
/*     */         return;
/*     */       } 
/* 552 */       setDbVersion(7);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private void readDatabase() {
/* 559 */     checkConnection();
/* 560 */     if (this.dataRead)
/* 561 */       return;  upgradeDatabase(dbVersion());
/*     */     
/*     */     try {
/* 564 */       readCategories();
/* 565 */       readUsers();
/* 566 */       readPromoImages();
/* 567 */       this.dataRead = true;
/*     */     }
/* 569 */     catch (Exception e) {
/*     */       
/* 571 */       e.printStackTrace();
/*     */     } 
/*     */   }
/*     */   
/*     */   protected long totalOrdersPrice(User user) {
/* 576 */     checkConnection();
/*     */     
/*     */     try {
/* 579 */       PreparedStatement prep = this.conn.prepareStatement("SELECT SUM(totalprice) FROM orders WHERE user_id = ?");
/* 580 */       prep.setLong(1, user.getId());
/* 581 */       ResultSet rs = prep.executeQuery();
/* 582 */       if (!rs.next()) throw new Exception("Could not retrieve totalprice"); 
/* 583 */       long res = rs.getLong(1);
/* 584 */       rs.close();
/* 585 */       prep.close();
/* 586 */       return res;
/*     */     }
/* 588 */     catch (Exception e) {
/*     */       
/* 590 */       return 0L;
/*     */     } 
/*     */   }
/*     */   
/*     */   public IImage[] getPromotionalImages() {
/* 595 */     checkConnection();
/* 596 */     if (!this.dataRead) readDatabase(); 
/* 597 */     return this.promoImages.<IImage>toArray((IImage[])new Image[0]);
/*     */   }
/*     */ }


/* Location:              /Users/nicholas/Desktop/old-knt/old-jar/program.jar!/vu/storm/administration/Database.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */