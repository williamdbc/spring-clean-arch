package com.colatina.app.service;

import com.colatina.app.service.core.domain.AccountDomain;
import com.colatina.app.service.core.domain.enumeration.AccountStatus;
import com.colatina.app.service.core.usecase.CreateAccountUseCase;
import com.colatina.app.service.entrypoint.api.AccountController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

@SpringBootTest
class ServiceApplicationTests {

	@Autowired
	private AccountController accountController;

	@Autowired
	private CreateAccountUseCase createAccountUseCase;


	@Test
	public void testCreateAccount() {


		AccountStatus status = AccountStatus.ACTIVE;
		AccountStatus status1 = AccountStatus.INACTIVE;

		if(status.equals(status1)){
			System.out.println("É igual");
		}

		if(status.name().equals(status1.name())){
			System.out.println("É igual tbm");
		}


		System.out.println();
		/*AccountDomain testData = new AccountDomain();
		testData.setName("William");
		testData.setLastName("Debortoli");
		testData.setDocument("130.....");

		LocalDate birthDate = LocalDate.of(2000, 10, 15);
		//LocalDate birthDate = LocalDate.of(2006, 10, 15); //Menor de 18
		testData.setBirthDate(birthDate);

		accountController.create(testData);

		System.out.println("Status quando criado: " + testData.getStatus());
		//accountController.changeStatus(testData);
		System.out.println("Status quando modificado 1: " + testData.getStatus());

		 */

	}

	@Test
	void contextLoads() {

	}

}
