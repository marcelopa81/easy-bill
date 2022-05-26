package br.com.alura.oobj.repository;

import br.com.alura.oobj.model.Venda;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VendaRepository extends JpaRepository<Venda, Long> {
}
