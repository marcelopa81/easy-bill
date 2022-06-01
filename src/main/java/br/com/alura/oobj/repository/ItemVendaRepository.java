package br.com.alura.oobj.repository;

import br.com.alura.oobj.model.ItemVenda;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ItemVendaRepository extends JpaRepository <ItemVenda, Long> {

    List<ItemVenda> findAllByVenda_Id(Long id);
}
