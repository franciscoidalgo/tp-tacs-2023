package com.tacs.util;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.binary.Hex;

public class HashingUtils {
  private HashingUtils() {
  }

  public static String hashPassword(String password)
      throws NoSuchAlgorithmException, InvalidKeySpecException {
    var random = new SecureRandom();
    byte[] salt = new byte[16];
    random.nextBytes(salt);
    var spec = new PBEKeySpec(password.toCharArray(), salt, 65536, 256);
    var factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
    byte[] hash = factory.generateSecret(spec).getEncoded();

    return String.format("%d:%s:%s", 65536, Hex.encodeHexString(salt), Hex.encodeHexString(hash));
  }

  public static boolean verifyHash(String password, String correctPassword)
      throws DecoderException, NoSuchAlgorithmException, InvalidKeySpecException {
    String[] split = correctPassword.split(":");
    var iterations = Integer.parseInt(split[0]);
    var salt = Hex.decodeHex(split[1]);
    var hash = Hex.decodeHex(split[2]);

    var spec = new PBEKeySpec(password.toCharArray(), salt, iterations, hash.length * 8);
    var factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
    byte[] testHash = factory.generateSecret(spec).getEncoded();

    int diff = hash.length ^ testHash.length;
    for (int i = 0; i < hash.length && i < testHash.length; i++) {
      diff |= hash[i] ^ testHash[i];
    }
    return diff == 0;
  }
}
