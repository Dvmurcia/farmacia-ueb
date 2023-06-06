package com.unbosque.utils;

import java.util.Properties;
import java.util.Random;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class CorreoManager {
	private final Properties properties = new Properties();
	private final String remitente = "serviciovudu@gmail.com";
	private final String contrasena = "qqzittimwksylpwk";
	private static Session sesion;

	// TODO metodo de generacion aleatoria de contrasena
	// TODO agregar log4j

	public boolean enviarCorreo(String destinatario, String usuario) {
		inicializar();
		String contrasena = generarPassword();

		try {
			MimeMessage message = new MimeMessage(sesion);
			message.setFrom(new InternetAddress(remitente));
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(destinatario));
			// TODO cambiar asunto y cuerpo del correo
			message.setSubject("Verificacion de registro en Vudu");
			message.setText("Bienvenido a la aplicacion Vudu - Creada por Bostinder \n"
					+ "A continuacion, relacionamos sus credenciales de acceso para acceder a la aplicacion: \n"
					+ "Usuario: " + usuario + "\n" + "Contrase�a: " + contrasena);
			Transport.send(message);

			System.out.println("Correo enviado");
			return true;
		} catch (MessagingException e) {
			e.printStackTrace();
			return false;
		}
	}

	private String generarPassword() {
		String mayusculas = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		String minusculas = "abcdefghijklmnopqrstuvwxyz";
		String numbers = "0123456789";
		String charPermitidos = mayusculas + minusculas + numbers;
		Random random = new Random();
		int longitudContrasena = random.nextInt(8 - 5 + 1) + 5;
		StringBuilder contrasena = new StringBuilder();

		// Asegurar al menos un número, una letra mayuscula y una letra minuscula
		contrasena.append(mayusculas.charAt(random.nextInt(mayusculas.length())));
		contrasena.append(minusculas.charAt(random.nextInt(minusculas.length())));
		contrasena.append(numbers.charAt(random.nextInt(numbers.length())));

		for (int i = 3; i < longitudContrasena; i++) {
			int randomIndex = random.nextInt(charPermitidos.length());
			char randomChar = charPermitidos.charAt(randomIndex);
			contrasena.append(randomChar);
		}

		// Mezclar los caracteres en la contrasena generada
		for (int i = contrasena.length() - 1; i > 0; i--) {
			int j = random.nextInt(i + 1);
			char temp = contrasena.charAt(i);
			contrasena.setCharAt(i, contrasena.charAt(j));
			contrasena.setCharAt(j, temp);
		}

		return contrasena.toString();
	}

	private void inicializar() {
		properties.put("mail.smtp.host", "smtp.gmail.com");
		properties.put("mail.smtp.port", "587");
		properties.put("mail.smtp.auth", "true");
		properties.put("mail.smtp.starttls.enable", "true");

		sesion = Session.getDefaultInstance(properties, new Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(remitente, contrasena);
			}
		});
	}
}