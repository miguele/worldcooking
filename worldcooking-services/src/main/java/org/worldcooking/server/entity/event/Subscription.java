package org.worldcooking.server.entity.event;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.worldcooking.server.entity.paiement.Payment;
import org.worldcooking.server.entity.people.Participant;

/**
 * Association
 * 
 * @author MatthieuG
 * 
 */
@Entity
public class Subscription {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long id;

	/** Email use for the registration. */
	@Column
	private String email;

	/** How this subscription is paid. */
	@ManyToOne
	private Payment paiement;

	/** Event associated to this subscription. */
	@ManyToOne
	private Event event;

	/** People registered with this subscription. */
	@OneToMany(mappedBy = "subscription")
	private List<Participant> participants;

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email
	 *            the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return the paiement
	 */
	public Payment getPaiement() {
		return paiement;
	}

	/**
	 * @param paiement
	 *            the paiement to set
	 */
	public void setPaiement(Payment paiement) {
		this.paiement = paiement;
	}

	/**
	 * @return the event
	 */
	public Event getEvent() {
		return event;
	}

	/**
	 * @param event
	 *            the event to set
	 */
	public void setEvent(Event event) {
		this.event = event;
	}

	public List<Participant> getParticipants() {
		return participants;
	}

	public void setParticipants(List<Participant> participants) {
		this.participants = participants;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

}