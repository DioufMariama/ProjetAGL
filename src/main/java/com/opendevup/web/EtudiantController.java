package com.opendevup.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.opendevup.dao.EtudiantRepository;
import com.opendevup.entities.Etudiant;

@Controller
@RequestMapping(value="/etudiant")
public class EtudiantController {

	
	@Autowired
	private EtudiantRepository etudiantRepository;
	
	@RequestMapping(value="/index")
	public String Index(Model model, @RequestParam(name="page",defaultValue="0") int page, 
			@RequestParam(name="motCle",defaultValue="") String motCle) {  
		Page<Etudiant> etds = etudiantRepository.findByMc("%"+motCle+"%", new PageRequest(page, 5));
		
		int NbPage = etds.getTotalPages();
		int[] pages = new int[NbPage];
		for (int i = 0; i < NbPage; i++) {
			pages[i] = i;
		}
		model.addAttribute("pages",pages);
		model.addAttribute("pageEtudiants",etds);
		model.addAttribute("pageCourent",page);
		model.addAttribute("motCle", motCle);
		return "etudiants";
	}
}
