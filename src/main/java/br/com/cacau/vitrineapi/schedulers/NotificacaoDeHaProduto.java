package br.com.cacau.vitrineapi.schedulers;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.stream.Collectors;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Component;

import br.com.cacau.vitrineapi.persistence.model.Cliente;
import br.com.cacau.vitrineapi.persistence.model.ProdutoVitrine;

@Component
public class NotificacaoDeHaProduto{

    @PersistenceContext
    private EntityManager entityManager;

    public EntityManager getEntityManager() {
        return entityManager;
    }
    
	//QUANDO A QUANTIDADE DO PRODUTO FOR ATUALIZADO PARA MAIS/IGUAL A 1, PESQUISAR SE O ID DO PRODUTO EXISTE NO CLIENTE_FAVORITOS
	

	
	public void avisarCliente() {

		List<Cliente> clienteParaAvisar = clienteParaAvisar();
		
		for(Cliente c : clienteParaAvisar) {
			enviaEmail(c.getEmail());
		}
	}
	
	/**
	 * @return Uma lista dos cliente que devem ser avisados quando existir o produto x que possui o id na lista que volta do virificarDisponibilidade()
	 * 
	 */
	private List<Cliente> clienteParaAvisar() {

		List<Integer> disponiveis = virificarDisponibilidade();
		List<Cliente> clientesParaAvisar = new ArrayList<Cliente>();
		
		for(Integer disponivel : disponiveis) {
			String query = "select c Cliente c WHERE c.id_produto_vitrine :disponivel ";
			TypedQuery<Cliente> produtosNaVitrine = getEntityManager().createQuery(query, Cliente.class);
			produtosNaVitrine.setParameter("disponivel", disponivel);
			List<Cliente> avisaveis = produtosNaVitrine.getResultList();
			clientesParaAvisar.addAll(avisaveis);
		}
		
		clientesParaAvisar = clientesParaAvisar.stream()
			    .distinct()
			    .collect(Collectors.toList());
		
		return clientesParaAvisar;
	}

	/**
	 * @return Ids com quantidade acima de 0; 
	 * Os ids acima de 0 são os produtos do dia ou hoje;
	 */
	private List<Integer> virificarDisponibilidade() {
		
		List<Integer> arrayIdsDisponiveis = new ArrayList<Integer>();
		
		String query = "select v ProdutoVitrine v HAVING c.quantidade_produto >= 1 ";
		List<ProdutoVitrine> produtosNaVitrine = getEntityManager().createQuery(query, ProdutoVitrine.class).getResultList();
		for(ProdutoVitrine pv : produtosNaVitrine ) {
			arrayIdsDisponiveis.add((int) pv.getId_produto());
		}
		return arrayIdsDisponiveis;
	}
	
	
	public static void main(String[] args) {
		enviaEmail("bianca@polvere.com.br");
	}
	
	private static void enviaEmail(String emailTo) {
		  // Recipient's email ID needs to be mentioned.
	      String to = emailTo;

	      // Sender's email ID needs to be mentioned
	      String from = "jp.cedeira@gmail.com";

	      // Assuming you are sending email from localhost
	      String host = "localhost";

	      // Get system properties
	      Properties properties = System.getProperties();

	      // Setup mail server
	      properties.setProperty("mail.smtp.host", host);

	      // Get the default Session object.
	      Session session = Session.getDefaultInstance(properties);

	      try {
	         // Create a default MimeMessage object.
	         MimeMessage message = new MimeMessage(session);

	         // Set From: header field of the header.
	         message.setFrom(new InternetAddress(from));

	         // Set To: header field of the header.
	         message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));

	         // Set Subject: header field
	         message.setSubject("This is the Subject Line!");

	         // Now set the actual message
	         message.setText("This is actual message");

	         // Send message
	         Transport.send(message);
	         System.out.println("Sent message successfully....");
	      } catch (MessagingException mex) {
	         mex.printStackTrace();
	      }
	}
}
