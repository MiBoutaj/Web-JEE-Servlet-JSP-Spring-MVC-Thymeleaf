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




    @GetMapping(path = "/user/indexM")
    public String medecin(Model model,
                          @RequestParam(name = "page",defaultValue = "0") int page,
                          @RequestParam(name = "size",defaultValue = "5")  int size,
                          @RequestParam(name = "keyword",defaultValue = "")  String keyword) {
        Page<Medecin> pageMedecin = medcinRepository.RechercheMultiCM("%"+keyword+"%",PageRequest.of(page,size));
        model.addAttribute("pageMedecin", pageMedecin.getContent());
        model.addAttribute("pages",new int[pageMedecin.getTotalPages()]);
        model.addAttribute("currentPage",page);
        model.addAttribute("keyword",keyword);
        model.addAttribute("size",size);
        return "medecin";
    }

    @GetMapping("/admin/deleteM")
    public String delete(Long id , String keyword , int page){
        medcinRepository.deleteById(id);
        return "redirect:/user/indexM?page="+page+"&keyword="+keyword;
    }


    @GetMapping("/admin/formMedecin")
    public String formMedecin(Model model){
        model.addAttribute("medecin",new Medecin());
        model.addAttribute("specialisation",Specialisation);
        return "formMedecin";    }



    @PostMapping("/admin/saveMedecin")
    public String save(Model model,
                       @Valid Medecin medecin,
                       BindingResult bindingResult,
                       @RequestParam(defaultValue = "") String keyword ,
                       @RequestParam(defaultValue = "0") int page){
        if(bindingResult.hasErrors())
            return "formMedecin";

        System.out.println(medecin.toString());

        medcinRepository.save(medecin);
        return "redirect:/user/indexM?page="+page+"&keyword="+keyword;
    }

    @GetMapping("/admin/editMedecin")
    public String editPatient(Model model,Long id,String keyword ,int page){
        Medecin medecin = medcinRepository.findById(id).orElse(null);
        if (medecin==null)
            throw  new RuntimeException("medecin introvable");
        model.addAttribute("medecin",medecin);
        model.addAttribute("specialisation",Specialisation);
        model.addAttribute("page",page);
        model.addAttribute("keyword",keyword);
        return "editMedecin";

    }

}
