package org.worldcooking.server.entity.event;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.worldcooking.server.entity.payment.Payment;
import org.worldcooking.server.entity.people.Participant;

/**
 * A subscription is an association between participants and event.
 * 
 * @author MatthieuG
 * 
 */
@Entity
public class Subscription {

	/**
	 * Identify a subscription in DB.
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long id;

	/** Email use for the registration. */
	@Column
	private String email;

	/** If the subscription is validated. */
	private Boolean validate = false;

	/** How this subscription is paid. */
	@OneToOne
	private Payment payment;

	/** Event associated to this subscription. */
	@ManyToOne
	private Event event;

	/** People registered with this subscription. */
	@OneToMany(mappedBy = "subscription")
	@Cascade({ CascadeType.DELETE })
	private Set<Participant> participants = new HashSet<Participant>();

	public Subscription() {
		// nothing to do
	}

	public Subscription(String email, Payment payment, Event event) {
		this.email = email;
		this.payment = payment;
		this.event = event;
	}

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
	 * @return the payment
	 */
	public Payment getPayment() {
		return payment;
	}

	/**
	 * @param payment
	 *            the payment to set
	 */
	public void setPayment(Payment payment) {
		this.payment = payment;
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

	public Set<Participant> getParticipants() {
		return participants;
	}

	protected void setParticipants(Set<Participant> participants) {
		this.participants = participants;
	}

	public void addParticipant(Participant participant) {
		participant.setSubscription(this);
		participants.add(participant);
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Boolean getValidate() {
		return validate;
	}

	public void setValidate(Boolean validate) {
		this.validate = validate;
	}

}
