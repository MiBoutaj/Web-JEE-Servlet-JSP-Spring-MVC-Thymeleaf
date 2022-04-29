package com.example.tp.web;

import com.example.tp.entites.Patient;
import com.example.tp.repository.PatientRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
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
import java.util.List;

@Controller
@AllArgsConstructor
public class PatientControlleur {


    private PatientRepository patientRepository;

    @GetMapping(path = "/user/index")
    public String patient(Model model,
                          @RequestParam(name = "page",defaultValue = "0") int page,
                          @RequestParam(name = "size",defaultValue = "5")  int size,
                          @RequestParam(name = "keyword",defaultValue = "")  String keyword) {
       Page<Patient> pagePatients = patientRepository.RechercheMultiCP("%"+keyword+"%",PageRequest.of(page,size));
        model.addAttribute("listPatients", pagePatients.getContent());
        model.addAttribute("pages",new int[pagePatients.getTotalPages()]);
        model.addAttribute("currentPage",page);
        model.addAttribute("keyword",keyword);
        model.addAttribute("size",size);
        return "patients";
    }


    @GetMapping("/admin/delete")
    public String delete(Long id , String keyword , int page){
        patientRepository.deleteById(id);
        return "redirect:/user/index?page="+page+"&keyword="+keyword;
    }

    @GetMapping("/")
    public String home(){
        return "home";
    }

    @GetMapping("/user/patients")
    @ResponseBody
    public List<Patient> findAll(){
        return patientRepository.findAll();
    }


    @GetMapping("/admin/formPatients")
    public String formPatient(Model model){
        model.addAttribute("patient",new Patient());
        return "formPatients";

    }

    @PostMapping("/admin/save")
    public String save(Model model,
                       @Valid Patient patient,
                       BindingResult bindingResult,
                       @RequestParam(defaultValue = "") String keyword ,
                       @RequestParam(defaultValue = "0") int page){
        if(bindingResult.hasErrors())
            return "formPatients";

        patientRepository.save(patient);
        return "redirect:/user/index?page="+page+"&keyword="+keyword;
    }

    @GetMapping("/admin/editPatient")
    public String editPatient(Model model,Long id,String keyword ,int page){
        Patient patient = patientRepository.findById(id).orElse(null);
        if (patient==null)
            throw  new RuntimeException("patient introvable");
        model.addAttribute("patient",patient);
        model.addAttribute("page",page);
        model.addAttribute("keyword",keyword);
        return "editPatient";

    }

}
