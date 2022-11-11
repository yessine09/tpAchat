package tn.esprit.rh.achat.entities.dto;

import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import tn.esprit.rh.achat.entities.CategorieFournisseur;
import tn.esprit.rh.achat.entities.DetailFournisseur;
import tn.esprit.rh.achat.entities.Facture;
import tn.esprit.rh.achat.entities.SecteurActivite;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FournisseurRequestModel {
	
	private Long idFournisseur;
	private String code;
	private String libelle;
	private CategorieFournisseur  categorieFournisseur;
	private Set<Facture> factures;
	private Set<SecteurActivite> secteurActivites;
	private DetailFournisseur detailFournisseur;
	
}
