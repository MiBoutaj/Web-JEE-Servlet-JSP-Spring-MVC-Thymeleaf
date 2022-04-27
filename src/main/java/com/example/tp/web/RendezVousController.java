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


    @GetMapping(path = "/user/rendezVous")
    public String rendezVous(Model model) {
        return "Ba9i";

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

        if (bindingResult.hasErrors()){
            System.out.println(bindingResult);
            return "formRendezVous";}
        System.out.println(rendezVous.toString());
        rendezVousRepository.save(rendezVous);
        return "redirect:/user/index?page=" + page + "&keyword=" + keyword;
    }


}
