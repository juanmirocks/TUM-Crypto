def sha = Mac.getInstance("HmacSHA256")
SecretKeySpec secret_key = new SecretKeySpec("a".getBytes(), "HmacSHA256")
sha.init(secret_key)
def shaCrypted = new String(sha.doFinal('a'.getBytes()))
println(shaCrypted)
