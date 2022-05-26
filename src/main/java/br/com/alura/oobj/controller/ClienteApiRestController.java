package br.com.alura.oobj.controller;

import br.com.alura.oobj.dto.ClienteForm;
import br.com.alura.oobj.dto.ClienteResposta;
import br.com.alura.oobj.model.Cliente;
import br.com.alura.oobj.repository.ClienteRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;


import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api")
public class ClienteApiRestController {

    private ClienteRepository clienteRepository;

    public ClienteApiRestController(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    @PostMapping("clientes")
    public ResponseEntity<ClienteForm> cadastrarCliente(@RequestBody @Valid ClienteForm cadastraCliente,
                                                        UriComponentsBuilder uriBuilder,
                                                        BindingResult result){
        if(result.hasErrors()){
            return ResponseEntity.badRequest().build();
        }
      Cliente cliente = cadastraCliente.converter();
        clienteRepository.save(cliente);

        URI uri = uriBuilder.path("/api/cliestes/{id}").buildAndExpand(cliente.getId()).toUri();
        return ResponseEntity.created(uri).body(new ClienteForm(cliente));
    }

    @GetMapping("/clientes/{id}")
    public ResponseEntity<ClienteResposta> retornaClientePorId(@PathVariable Long id){
        Optional<Cliente> cliente = clienteRepository.findById(id);
        if(cliente.isPresent()) {
            return ResponseEntity.ok(new ClienteResposta(cliente.get()));
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/admin/clientes")
    public List<ClienteResposta> clientePorEstado(@RequestParam(required = false) String estado){
        if(estado==null){
            List<Cliente> cliente = clienteRepository.findAll();
            return ClienteResposta.converter(cliente);
        }
        List<Cliente> clientePorEstado = clienteRepository.findByEstado(estado);
        return ClienteResposta.converter(clientePorEstado);
    }

}
