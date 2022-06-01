package br.com.alura.oobj.controller;


import br.com.alura.oobj.dto.RequisicaoItemVenda;
import br.com.alura.oobj.dto.RequisicaoVenda;
import br.com.alura.oobj.dto.VendaResposta;
import br.com.alura.oobj.model.ItemVenda;
import br.com.alura.oobj.model.Venda;
import br.com.alura.oobj.repository.ItemVendaRepository;
import br.com.alura.oobj.repository.VendaRepository;
import br.com.alura.oobj.service.ServiceVenda;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/api")
public class VendaApiRestController {

    private final ServiceVenda serviceVenda;
    private final VendaRepository vendaRepository;
    private final ItemVendaRepository itemVendaRepository;


    public VendaApiRestController(ServiceVenda serviceVenda, VendaRepository vendaRepository,
                                  ItemVendaRepository itemVendaRepository) {
        this.serviceVenda = serviceVenda;
        this.vendaRepository = vendaRepository;
        this.itemVendaRepository = itemVendaRepository;
    }


    @PostMapping("/vendas")
    @Transactional
    public ResponseEntity<RequisicaoItemVenda> criaVenda(@RequestBody @Valid RequisicaoVenda requisicaoVenda,
                                                         UriComponentsBuilder uriBuilder, BindingResult result){
        if(result.hasErrors()){
            return ResponseEntity.badRequest().build();
        }
        Venda venda = serviceVenda.registraVenda(requisicaoVenda);

        URI uri = uriBuilder.path("/api/venda/{id}").buildAndExpand(venda.getId()).toUri();
        return ResponseEntity.created(uri).body(new RequisicaoItemVenda());
    }

    @GetMapping("/vendas/{id}")
    public ResponseEntity<VendaResposta> retornaVendaPorId(@PathVariable Long id){
        Optional<Venda> venda = vendaRepository.findById(id);
        List<ItemVenda> itemVenda = itemVendaRepository.findAllByVenda_Id(id);
        if(venda.isPresent()){
            VendaResposta vendaResposta= VendaResposta.converter(venda, itemVenda);
            return ResponseEntity.ok(vendaResposta);
        }

        return ResponseEntity.notFound().build();
    }
}
