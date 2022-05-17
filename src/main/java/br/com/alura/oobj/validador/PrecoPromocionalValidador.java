package br.com.alura.oobj.validador;

import br.com.alura.oobj.dto.RequisicaoNovoProduto;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;


import static java.util.Objects.isNull;

@Component
public class PrecoPromocionalValidador {
    public void valid(RequisicaoNovoProduto requisicaoNovoProduto, BindingResult result){
        if(isNull(requisicaoNovoProduto.getPreco())){
            return;
        }
        Integer compara = requisicaoNovoProduto.getPreco().compareTo(requisicaoNovoProduto.getPrecoPromocional());
        if(compara.equals(1)){
            return;
        }
        //ObjectError objectError = new ObjectError("precoPromocional","Preço promocional deve der menor que o peço");
        result.rejectValue("precoPromocional", "",
                "Preço promocional deve der menor que o preço");

    }
}
