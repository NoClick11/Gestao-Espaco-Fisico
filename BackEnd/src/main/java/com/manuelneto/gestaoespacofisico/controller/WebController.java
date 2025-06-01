package com.manuelneto.gestaoespacofisico.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WebController {
	
	@GetMapping("/")
	public String index() {
		return "index";
	}	
	
	@GetMapping("/dashboard")
    public String dashboard() {
        return "dashboard";
    }
    
    @GetMapping("/espacos")
    public String espacos() {
        return "espacos";
    }
    
    @GetMapping("/equipamentos")
    public String equipamentos() {
        return "equipamentos";
    }
    
    @GetMapping("/solicitacoes")
    public String solicitacoes() {
        return "solicitacoes";
    }
    
    @GetMapping("/nova-solicitacao")
    public String novaSolicitacao() {
        return "nova-solicitacao";
    }
    
    @GetMapping("/usuarios")
    public String usuarios() {
        return "usuarios";
    }
    
    @GetMapping("/avaliacoes")
    public String avaliacoes() {
        return "avaliacoes";
    }
}

