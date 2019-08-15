KeyFactory fact = KeyFactory.getInstance("RSA");
fact = KeyFactory.getInstance("RSA");
RSAPrivateKeySpec keySpec1 = new RSAPrivateKeySpec(
        new BigInteger("ddd2806f523c85fa7c87918ef54317b0e834c5dd75c33a1867ef40e4c9496dc2b2e0e0c332dc535a43e4caee867421957a5b901ad03d03eebc6a10dd400f03aaae90eeab4df5b64600a0346628f16ab0c9bbd639eb9163a21029d782eb024b28a46deb920fd969e5e11a28867be4cd578cd79d1adbf8f8af53eaf1a3186941c3657aa5ccaa9b16d5a264ed1afb32a739466c515f38a4c03614e618f93eab95ca860aa81a522ffca346204fe454d345c17aa220478334a769f80808ff5dd7aa06f5435920087b894164541319efc8af84ccfe269b3ad282f3b1121b2aab41156a510becad6c01538ebca625af80a131435cfc1a4193ff4535a87b358856ce9edb",16),
        new BigInteger("7c4ae1307f51ee78a6a2880b249e8d7dd0ff1107c05ebe04c2e82ae2876f1f93830bc2a55db05afc2f22da1ac137fd4176a82b9cfaa5d5b845d6e0d0387eb13d484769948f4068fbdc89e8127e4a97cfab9a16b5a9ab73ba7178208bf906f5fa35f8699114546ca42477b5d175468550d358556da168666047935406613d495d1a1d20195b5db14f89dcf701a72bb63825ccbf6b26582c6c92e98fc5f0976572fe15c127e6b0a75e7037185e65e197c2f3c6e56d4078322abd15ffba1190cffc2f2e33c537d89e6c8212ae7bc5ad3f2f329cc19cba987a3d8ba96d6697713c8be4f6db08ae1f66e1e90f9a4d3b322d1511ba745d0567e88f62d4f6974a1ecfa1",16)
        );

PrivateKey privKey = fact.generatePrivate(keySpec1);

Cipher cipher = Cipher.getInstance("RSA");
cipher.init(Cipher.ENCRYPT_MODE, privKey);
byte[] cipherData1 = cipher.doFinal("THIS_IS_THE_DATA_ENCRYPT".getBytes("UTF-8"));

System.out.println("ciphered text (by priv key): " + byteArrayToHex(cipherData1));
