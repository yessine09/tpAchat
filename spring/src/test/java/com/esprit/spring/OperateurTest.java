package com.esprit.spring;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.slf4j.Logger;
import org.springframework.boot.test.context.SpringBootTest;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.UUID;
import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import tn.esprit.rh.achat.entities.Operateur;
import tn.esprit.rh.achat.repositories.OperateurRepository;
import tn.esprit.rh.achat.services.OperateurServiceImpl;
@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
public class OperateurTest {
	
	 @InjectMocks
	 private OperateurServiceImpl service;
	 
	 @Mock
	 private OperateurRepository repository;
	 
	 @Mock
		Logger logger;

		@Test
		public void insertOperateurTest() {
			System.out.println("********************** add operateur test *******************************");
			long id = UUID.randomUUID().getMostSignificantBits() & Long.MAX_VALUE;
			Operateur operateur = new Operateur(id,"boussaid", "zaineb", "Action");
			when(logger.isInfoEnabled()).thenReturn(false);
			when(repository.save(operateur)).thenReturn(operateur);
			assertEquals(operateur, service.addOperateur(operateur));
		}
		
		   @Test
		    public void getOperateurTest() {
		        System.out.println("********************** get operateur test *******************************");
		        long id = UUID.randomUUID().getMostSignificantBits() & Long.MAX_VALUE;
		        long id2 = UUID.randomUUID().getMostSignificantBits() & Long.MAX_VALUE;

		        repository = mock(OperateurRepository.class);
		        service = new OperateurServiceImpl(repository);
		        List<Operateur> operateurList = new ArrayList<>();

		        operateurList.add(new Operateur(id, "nom", "prenom", "password"));
		        operateurList.add(new Operateur(id2, "nom2", "prenom2", "password2"));

		        when(repository.findAll()).thenReturn(operateurList);

		        List<Operateur> expectedList = service.retrieveAllOperateurs();

		        assertEquals(2, expectedList.size());
		    }

}
