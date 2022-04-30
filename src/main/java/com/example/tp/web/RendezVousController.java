package com.example.tp.web;

import com.example.tp.entites.Medecin;
import com.example.tp.entites.Patient;
import com.example.tp.entites.RendezVous;
import com.example.tp.entites.StatusRDV;
import com.example.tp.repository.MedcinRepository;
import com.example.tp.repository.PatientRepository;
import com.example.tp.repository.RendezVousRepository;
import com.example.tp.service.HospitalServiceImpl;
import com.example.tp.service.IHospitalService;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.ui.Model;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;
import java.util.Set;

@Controller
@AllArgsConstructor
public class RendezVousController {




    private RendezVousRepository rendezVousRepository;
    private PatientRepository patientRepository;
    private MedcinRepository medcinRepository;

    static Set<StatusRDV> Status = null;
    static {
        Status= EnumSet.allOf(StatusRDV.class);
    }


    @GetMapping(path = "/user/indexR")
    public String rendezVous(Model model,
                          @RequestParam(name = "page",defaultValue = "0") int page,
                          @RequestParam(name = "size",defaultValue = "5")  int size,
                          @RequestParam(name = "keyword",defaultValue = "")  String keyword) {
        Page<RendezVous> pageRendezVous = rendezVousRepository.RechercheMultiCR("%"+keyword+"%", PageRequest.of(page,size));
        model.addAttribute("pageRendezVous", pageRendezVous.getContent());
        model.addAttribute("pages",new int[pageRendezVous.getTotalPages()]);
        model.addAttribute("currentPage",page);
        model.addAttribute("keyword",keyword);
        model.addAttribute("size",size);
        return "rendezVous";
    }


    @GetMapping("/admin/deleteR")
    public String delete(Long id , String keyword , int page){
        rendezVousRepository.deleteById(id);
        return "redirect:/user/indexR?page="+page+"&keyword="+keyword;
    }


    @GetMapping("/admin/formRendezVous")
    public String formRendezVous(Model model) {
        model.addAttribute("rendezVous", new RendezVous());
        List<Patient> patientList = patientRepository.findAll();
        List<Medecin> medecinList = medcinRepository.findAll();
        model.addAttribute("patientList", patientList);
        model.addAttribute("medecinList", medecinList);
        model.addAttribute("Status", Status);
        return "formRendezVous";
    }


    @PostMapping("/admin/saveRendezVous")
    public String save(Model model,
                       @Valid RendezVous rendezVous,
                       BindingResult bindingResult,
                       @RequestParam(defaultValue = "") String keyword,
                       @RequestParam(defaultValue = "0") int page) {
        System.out.println(rendezVous.toString());

        if (bindingResult.hasErrors()) {
            System.out.println(bindingResult);
            return "formRendezVous";
        }
        System.out.println(rendezVous.toString());
        rendezVousRepository.save(rendezVous);
        return "redirect:/user/indexR?page=" + page + "&keyword=" + keyword;


    }



        @GetMapping("/admin/editRendezVous")
        public String editRendezVous(Model model,Long id,String keyword ,int page){
            RendezVous rendezVous = rendezVousRepository.findById(id).orElse(null);
            if (rendezVous==null)
                throw  new RuntimeException("rendezVous introvable");
            model.addAttribute("rendezVous",rendezVous);
            List<Patient> patientList = patientRepository.findAll();
            List<Medecin> medecinList = medcinRepository.findAll();
            model.addAttribute("patientList", patientList);
            model.addAttribute("medecinList", medecinList);
            model.addAttribute("Status", Status);
            model.addAttribute("page",page);
            model.addAttribute("keyword",keyword);
            return "editRendezVous";

        }









}
