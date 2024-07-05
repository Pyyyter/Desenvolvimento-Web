package com.pedroribeiro.apirestful.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.yaml.snakeyaml.tokens.ScalarToken;

import java.math.BigDecimal;
import java.time.LocalDate;

@ToString
@Getter
@Setter
@NoArgsConstructor
@Entity
public class Filme {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "A 'Imagem' deve ser informada.")
    private String imagem;

    @NotEmpty(message = "O 'Nome' deve ser informado.")
    private String nome;

    @NotEmpty(message = "A 'Descrição' deve ser informada.")
    private String descricao;

    private boolean disponivel;

    @Min(value=0, message = "A 'Quantidade em estoque' deve ser maior ou igual a 0.")
    private int qtdEstoque;

    @NotNull(message = "O 'Preço' deve ser informado.")
    @DecimalMin(inclusive = true, value="0.1", message = "O 'Preço' deve ser maior ou igual a 0.1.")
    private BigDecimal preco;

    @NotNull(message = "A 'Data de Cadastro' deve ser informada.")
    private LocalDate dataCadastro;

    @ManyToOne
    private Categoria categoria;

    public Filme(String imagem,
                   String nome,
                   String descricao,
                   boolean disponivel,
                   int qtdEstoque,
                   BigDecimal preco,
                   LocalDate dataCadastro,
                   Categoria categoria) {
        this.imagem = imagem;
        this.nome = nome;
        this.descricao = descricao;
        this.disponivel = disponivel;
        this.qtdEstoque = qtdEstoque;
        this.preco = preco;
        this.dataCadastro = dataCadastro;
        this.categoria = categoria;
    }
}
