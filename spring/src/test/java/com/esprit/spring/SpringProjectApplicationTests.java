package com.esprit.spring;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import tn.esprit.rh.achat.entities.Operateur;
import tn.esprit.rh.achat.repositories.OperateurRepository;
import tn.esprit.rh.achat.services.OperateurServiceImpl;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
class SpringProjectApplicationTests {

	
	 private OperateurServiceImpl service;
	    private OperateurRepository repository;


	    @Test
	    public void getOperateurTest() {
	        System.out.println("********************** get operateur test *******************************");
	        long id = UUID.randomUUID().getMostSignificantBits() & Long.MAX_VALUE;
	        long id2 = UUID.randomUUID().getMostSignificantBits() & Long.MAX_VALUE;

	        repository = mock(OperateurRepository.class);
	        service = new OperateurServiceImpl(repository);
	        List<Operateur> operateurList = new ArrayList<>();

	        operateurList.add(new Operateur(id, "nom", "prenom", "password", null));
	        operateurList.add(new Operateur(id2, "nom2", "prenom2", "password2",null));

	        when(repository.findAll()).thenReturn(operateurList);

	        List<Operateur> expectedList = service.retrieveAllOperateurs();

	        assertEquals(2, expectedList.size());
	    }
//	    @Test
//	    public void getUserbyAddressTest() {
//	        String address = "Bangalore";
//	        when(repository.findByAddress(address))
//	                .thenReturn(Stream.of(new User(376, "Danile", 31, "USA")).collect(Collectors.toList()));
//	        assertEquals(1, service.getUserbyAddress(address).size());
//	    }
	//
//	    @Test
//	    public void saveUserTest() {
//	        User user = new User(999, "Pranya", 33, "Pune");
//	        when(repository.save(user)).thenReturn(user);
//	        assertEquals(user, service.addUser(user));
//	    }
	//
//	    @Test
//	    public void deleteUserTest() {
//	        User user = new User(999, "Pranya", 33, "Pune");
//	        service.deleteUser(user);
//	        verify(repository, times(1)).delete(user);
//	    }


}
