package com.example.tp.web;

import com.example.tp.entites.Consultation;
import com.example.tp.entites.Medecin;
import com.example.tp.entites.Patient;
import com.example.tp.entites.RendezVous;
import com.example.tp.repository.ConsultationRepository;
import com.example.tp.repository.RendezVousRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.util.List;

@Controller
@AllArgsConstructor
@NoArgsConstructor
public class ConsultationController {

    private ConsultationRepository consultationRepository;
    private RendezVousRepository rendezVousRepository;

    @GetMapping(path = "/user/indexC")
    public String patient(Model model,
                          @RequestParam(name = "page",defaultValue = "0") int page,
                          @RequestParam(name = "size",defaultValue = "5")  int size,
                          @RequestParam(name = "keyword",defaultValue = "")  String keyword) {
        Page<Consultation> pageConsultation = consultationRepository.findByRapportContains("%"+keyword+"%", PageRequest.of(page,size));
        model.addAttribute("pageConsultation", pageConsultation.getContent());
        model.addAttribute("pages",new int[pageConsultation.getTotalPages()]);
        model.addAttribute("currentPage",page);
        model.addAttribute("keyword",keyword);
        model.addAttribute("size",size);
        return "consultation";
    }


    @GetMapping("/admin/formConsultation")
    public String formMedecin(Model model){
        model.addAttribute("consultaion",new Consultation());
        List<RendezVous> listeRDV = rendezVousRepository.findAll();
        model.addAttribute("listeRDV",listeRDV);
        return "formConsultation";    }



    @PostMapping("/admin/saveC")
    public String save(Model model,
                       @Valid Consultation consultation,
                       BindingResult bindingResult,
                       @RequestParam(defaultValue = "") String keyword ,
                       @RequestParam(defaultValue = "0") int page){
        if(bindingResult.hasErrors())
            return "formConsultation";

        System.out.println(consultation.toString());

        consultationRepository.save(consultation);
        return "redirect:/user/indexC?page="+page+"&keyword="+keyword;
    }











}
