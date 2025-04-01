package com.codey.snipperapp.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

@Component
public class EncryptionService {

  @Value("${encryption.key}")
  private String encryptionKey;
  final String ALGORITHM = "AES";
  KeyGenerator keyGen;
  SecretKey secretKey;

  public EncryptionService() throws Exception {
    keyGen = KeyGenerator.getInstance(ALGORITHM);
    keyGen.init(128);
    secretKey = keyGen.generateKey();
  }


  public String encrypt(String text) throws Exception {
    Cipher cipher = Cipher.getInstance(ALGORITHM);
    cipher.init(Cipher.ENCRYPT_MODE, secretKey);

    byte[] plaintextBytes = text.getBytes(StandardCharsets.UTF_8);
    byte[] ciphertext = cipher.doFinal(plaintextBytes);

    return Base64.getEncoder().encodeToString(ciphertext);
  }

  public String decrypt(String encryptedText) throws Exception {
    Cipher cipher = Cipher.getInstance(ALGORITHM);
    cipher.init(Cipher.DECRYPT_MODE, secretKey);

    byte[] decodedCiphertext = Base64.getDecoder().decode(encryptedText);
    byte[] decryptedBytes = cipher.doFinal(decodedCiphertext);

    return new String(decryptedBytes, StandardCharsets.UTF_8);
  }
}
