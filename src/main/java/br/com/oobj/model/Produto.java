package br.com.oobj.model;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 150)
    private String name;

    @Column(nullable = false, length = 500)
    private String urlImagem;

    @Column(nullable = true, length = 1000)
    private String descricao;

    private BigDecimal preco;

    private BigDecimal precoPromocional;

    @Column(nullable = true, length = 10)
    private String classeFiscal;

}
