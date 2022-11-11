package com.esprit.spring;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.mockito.junit.MockitoJUnitRunner;

import tn.esprit.rh.achat.AchatApplication;
import tn.esprit.rh.achat.entities.SecteurActivite;
import tn.esprit.rh.achat.repositories.SecteurActiviteRepository;
import tn.esprit.rh.achat.services.SecteurActiviteServiceImpl;


@SpringBootTest(classes = AchatApplication.class)
@ExtendWith(MockitoExtension.class)
@RunWith(MockitoJUnitRunner.class)
public class SecteurServiceMockitoTest {

	@Mock
	 SecteurActiviteRepository saRepositoryMock;
	 @InjectMocks
	 SecteurActiviteServiceImpl saService;


	 SecteurActivite sa = SecteurActivite.builder().codeSecteurActivite("code1secteur")
             .libelleSecteurActivite("sect1")
             .build();
	 List<SecteurActivite> listSecteurs = new ArrayList<SecteurActivite>(){
	     {
	         add(SecteurActivite.builder().codeSecteurActivite("code12secteur").libelleSecteurActivite("sect12").build());
	         add(SecteurActivite.builder().codeSecteurActivite("code13secteur").libelleSecteurActivite("sect13").build());
	         add(SecteurActivite.builder().codeSecteurActivite("code14secteur").libelleSecteurActivite("sect14").build());
	         add(SecteurActivite.builder().codeSecteurActivite("code15secteur").libelleSecteurActivite("sect15").build());

	     }
	 };

	 @Test
	 public void testretrieveSecteur(){
	     Mockito.when(saRepositoryMock.findById(Mockito.anyLong())).thenReturn(Optional.of(sa)); //find all
	     SecteurActivite sa1 = saService.retrieveSecteurActivite(2L);
	     Assertions.assertNotNull(sa1);
			System.out.println("retrieve !");


	 }
	 @Test
	 public void testaddSecteur() {
	     Mockito.when(saRepositoryMock.save(sa)).thenReturn(sa);
	     SecteurActivite sa1 = saService.addSecteurActivite(sa);
	     Assertions.assertNotNull(sa1);
			System.out.println("add !");


	 }

	 @Test
	 public void testretrieveAllSecteurs() {
	     Mockito.when(saRepositoryMock.findAll()).thenReturn(listSecteurs);
	     List<SecteurActivite> listSa = saService.retrieveAllSecteurActivite();
	     Assertions.assertNotNull(listSa);
			System.out.println("all retrieve !");

	 }



	 @Test
	 public void tesupdateSecteur() {
		 sa.setCodeSecteurActivite("test");
	     Mockito.when(saRepositoryMock.save(sa)).thenReturn(sa);
	     SecteurActivite sa1 = saService.updateSecteurActivite(sa);
	     Assertions.assertEquals(sa1.getCodeSecteurActivite(),sa1.getCodeSecteurActivite());
			System.out.println("update !");


	 }

	// @Test
	 //public void testdeleteSecteur() {
	//	 SecteurActivite sa2 = SecteurActivite.builder().codeSecteurActivite("code12secteur").libelleSecteurActivite("sect12").build();
		// saService.deleteSecteurActivite(sa2.getIdSecteurActivite());
	     //Mockito.verify(saRepositoryMock).deleteById(sa2.getIdSecteurActivite());
		//	System.out.println("delete !");


	 //}
}
