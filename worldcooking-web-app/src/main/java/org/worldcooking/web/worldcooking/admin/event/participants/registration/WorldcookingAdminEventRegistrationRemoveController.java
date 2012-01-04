/**
 * 
 */
package org.worldcooking.web.worldcooking.admin.event.participants.registration;

import org.oupsasso.mishk.core.dao.exception.EntityIdNotFountException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.worldcooking.server.entity.event.Registration;
import org.worldcooking.server.services.registration.RegistrationService;

/**
 * @author MatthieuG
 * 
 */
@Controller
public class WorldcookingAdminEventRegistrationRemoveController {

	@Autowired
	private RegistrationService registrationService;

	@RequestMapping(value = "/admin/event/{eventReference}/remove/registration", method = RequestMethod.GET)
	public String handleRequest(@PathVariable String eventReference, @RequestParam Long registrationId)
			throws EntityIdNotFountException {

		Registration s = registrationService.removeRegistration(registrationId);

		return "redirect:/admin/event/" + s.getEvent().getReference();
	}

}