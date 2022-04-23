package com.example.tp.web;

import com.example.tp.entites.Medecin;
import com.example.tp.entites.Patient;
import com.example.tp.repository.MedcinRepository;
import com.example.tp.repository.PatientRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Controller
@AllArgsConstructor
public class MedecinController {


    private MedcinRepository medcinRepository;

    static List<String> Specialisation = null;
    static {
        Specialisation = new ArrayList<>();
        Specialisation.add("Generaliste");
        Specialisation.add("Cardio");
        Specialisation.add("Dentiste");
    }


    @GetMapping("/admin/formMedecin")
    public String formMedecin(Model model){
        model.addAttribute("medecin",new Medecin());
        model.addAttribute("specialice",Specialisation);
        return "formMedecin";    }



    @PostMapping("/admin/saveMedecin")
    public String save(Model model,
                       @Valid Medecin medecin,
                       BindingResult bindingResult,
                       @RequestParam(defaultValue = "") String keyword ,
                       @RequestParam(defaultValue = "0") int page){
        if(bindingResult.hasErrors())
            return "formMedecin";

        medcinRepository.save(medecin);
        return "redirect:/user/index?page="+page+"&keyword="+keyword;
    }

  /*  @GetMapping("/admin/editPatient")
    public String editPatient(Model model,Long id,String keyword ,int page){
        Patient patient = patientRepository.findById(id).orElse(null);
        if (patient==null)
            throw  new RuntimeException("patient introvable");
        model.addAttribute("patient",patient);
        model.addAttribute("page",page);
        model.addAttribute("keyword",keyword);
        return "editPatient";

    }

 */

}
