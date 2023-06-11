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

import org.jboss.logging.Logger;

public class CorreoManager {
	private static final Logger LOGGER = Logger.getLogger(CorreoManager.class.getName());
	private final Properties properties = new Properties();
	private final String remitente = "serviciovudu@gmail.com";
	private final String contrasena = "qqzittimwksylpwk";
	private static Session sesion;

	public boolean enviarCorreo(String destinatario, String usuario, String contrasena) {
		inicializar();
		try {
			MimeMessage message = new MimeMessage(sesion);
			message.setFrom(new InternetAddress(remitente));
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(destinatario));
			// TODO cambiar asunto y cuerpo del correo
			message.setSubject("Farmacia UEB te da la bienvenida");
			message.setText("¡Bienvenido a la Farmacia UEB! \n"
					+ "A continuacion, relacionamos sus credenciales de acceso para acceder a la aplicacion: \n"
					+ "Usuario: " + usuario + "\n" + "Contraseña: " + contrasena);
			Transport.send(message);

			LOGGER.info("SE HA ENVIADO EL CORREO ELECTRONICO AL DESTINATARIO: " + destinatario);
			return true;
		} catch (MessagingException e) {
			LOGGER.error("Error al enviar el correo electronico: ", e);
			return false;
		}
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