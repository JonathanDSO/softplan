package br.com.softplan.controller;

import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

public class Criptografia {

	private static final String KEY = "viruschinescovid";

	public static String criptografar(String mensagem) {
		try {
			Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
			cipher.init(Cipher.ENCRYPT_MODE, new SecretKeySpec(KEY.getBytes(), "AES"));
			byte[] senhaCipher = cipher.doFinal(mensagem.getBytes());
			return Base64.getEncoder().encodeToString(senhaCipher);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
