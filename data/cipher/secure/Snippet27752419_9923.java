StringBuffer sbselect1=new StringBuffer();
sbselect1.append("SELECT Keyforkey FROM ");
sbselect1.append(UserConstants.USER_DETAILS_TABLE_NAME2);
sbselect1.append(" where ID=2");
ps1=conn.prepareStatement(sbselect1.toString());
ResultSet rs =ps1.executeQuery();
 rs.next();
String keyskey = rs.getString("KeyforKey");
System.out.println("Fetched Key from DB "+keyskey);
Security.addProvider(new FlexiCoreProvider());
Cipher cipher2 = Cipher.getInstance("AES128_CBC", "FlexiCore");
//  byte[] encodedKey =keyskey.getBytes();
SecretKey key2 = new SecretKeySpec(keyskey.getBytes(), 0,  keyskey.length(), "AES");
System.out.println("invalid Key  "+ key2);
rs.close(); 
