package br.com.alura.oobj.controller;

import br.com.alura.oobj.dto.RetornoClienteId;
import br.com.alura.oobj.model.Cliente;
import br.com.alura.oobj.repository.ClienteRepository;
import br.com.alura.oobj.dto.CadastraCliente;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;


import javax.validation.Valid;
import java.net.URI;
import java.util.Optional;

@RestController
@RequestMapping("api")
public class ClienteApiRestController {

    private ClienteRepository clienteRepository;

    public ClienteApiRestController(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    @PostMapping("clientes")
    public ResponseEntity<CadastraCliente> cadastrarCliente(@RequestBody @Valid CadastraCliente cadastraCliente,
                                                            UriComponentsBuilder uriBuilder,
                                                            BindingResult result){
        if(result.hasErrors()){
            return ResponseEntity.badRequest().body(new CadastraCliente());
        }
      Cliente cliente = cadastraCliente.converter();
        clienteRepository.save(cliente);

        URI uri = uriBuilder.path("/api/cliestes/{id}").buildAndExpand(cliente.getId()).toUri();
        return ResponseEntity.created(uri).body(new CadastraCliente(cliente));
    }

    @GetMapping("/clientes/{id}")
    public ResponseEntity<RetornoClienteId> retornaClientePorId(@PathVariable Long id){
        Optional<Cliente> cliente = clienteRepository.findById(id);
        if(cliente.isPresent()) {
            return ResponseEntity.ok(new RetornoClienteId(cliente.get()));
        }
        return ResponseEntity.notFound().build();
    }


}
