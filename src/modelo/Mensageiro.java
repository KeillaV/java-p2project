package modelo;

import java.util.Date;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;


public class Mensageiro {
	
	public static void enviarListaDeLivros(String endereco) {
		Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.socketFactory.port", "465");
        props.put("mail.smtp.socketFactory.class","javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", "465");
        
        Session session = Session.getDefaultInstance(props, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("estanteonline1@gmail.com", "estante107");   
            }   
        });
        
        try {
            MimeMessage msg = new MimeMessage(session);
            msg.setFrom(new InternetAddress("estanteonline1@gmail.com"));
            InternetAddress[] address = {new InternetAddress(endereco)};
            msg.setRecipients(Message.RecipientType.TO, address);
            msg.setSubject("Estante Virtual");
            
            MimeBodyPart mensagem1 = new MimeBodyPart();
            mensagem1.setText("Segue em anexo a lista de livros cadastrados no sistema");
            
            MimeBodyPart mensagem2 = new MimeBodyPart();
            
            CentralDeInformacoes central = CentralDeInformacoes.getInstance();
            GeradorDeRelatorios.gerarRelatorio(central.getTodosOsLivros()); 
            
            FileDataSource f = new FileDataSource("relatório.pdf");
            mensagem2.setDataHandler(new DataHandler(f));
            mensagem2.setFileName(f.getName());
            
            Multipart mp = new MimeMultipart();
            mp.addBodyPart(mensagem1);
            mp.addBodyPart(mensagem2);
        
            msg.setContent(mp);
            msg.setSentDate(new Date());
            Transport.send(msg);
            
        } catch (MessagingException e) {
            throw new RuntimeException(e);    
        }
        
    }
	
	public static void enviarSenhaProvisoria(String endereco, String senha) {
		Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.socketFactory.port", "465");
        props.put("mail.smtp.socketFactory.class","javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", "465");
        
        Session session = Session.getDefaultInstance(props, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("estanteonline1@gmail.com", "estante107");   
            }   
        });
        
        try {
            MimeMessage msg = new MimeMessage(session);
            msg.setFrom(new InternetAddress("estanteonline1@gmail.com"));
            InternetAddress[] address = {new InternetAddress(endereco)};
            msg.setRecipients(Message.RecipientType.TO, address);
            msg.setSubject("Estante Virtual");
            
            MimeBodyPart mensagem1 = new MimeBodyPart();
            mensagem1.setText("Utilize a senha abaixo para acessar o sistema \n\n");
            
            MimeBodyPart mensagem2 = new MimeBodyPart();
            mensagem2.setText(senha);
            
            Multipart mp = new MimeMultipart();
            mp.addBodyPart(mensagem1);
            mp.addBodyPart(mensagem2);
        
            msg.setContent(mp);
            msg.setSentDate(new Date());
            Transport.send(msg);
            
        } catch (MessagingException e) {
        	throw new RuntimeException(e);
        }
	}
	
	public static void enviarAvisoDeLivroDisponivel(String endereco, Livro livro) {
		Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.socketFactory.port", "465");
        props.put("mail.smtp.socketFactory.class","javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", "465");
        
        Session session = Session.getDefaultInstance(props, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("estanteonline1@gmail.com", "estante107");   
            }   
        });
        
        try {
            MimeMessage msg = new MimeMessage(session);
            msg.setFrom(new InternetAddress("estanteonline1@gmail.com"));
            InternetAddress[] address = {new InternetAddress(endereco)};
            msg.setRecipients(Message.RecipientType.TO, address);
            msg.setSubject("Estante Virtual");
            
            String tituloDoLivro = livro.getTitulo();
            MimeBodyPart mensagem1 = new MimeBodyPart();
            mensagem1.setText("O livro " + tituloDoLivro + " já está disponível para compra! Garanta já o seu!");
  
            Multipart mp = new MimeMultipart();
            mp.addBodyPart(mensagem1);
            
            msg.setContent(mp);
            msg.setSentDate(new Date());
            Transport.send(msg);
            
        } catch (MessagingException e) {
        	throw new RuntimeException(e);
        }
	}
}
