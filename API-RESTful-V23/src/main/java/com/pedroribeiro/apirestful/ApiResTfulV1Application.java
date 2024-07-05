package com.pedroribeiro.apirestful;

import com.pedroribeiro.apirestful.model.Categoria;
import com.pedroribeiro.apirestful.model.Filme;
import com.pedroribeiro.apirestful.model.Usuario;
import com.pedroribeiro.apirestful.repository.CategoriaRepository;
import com.pedroribeiro.apirestful.repository.FilmeRepository;
import com.pedroribeiro.apirestful.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.math.BigDecimal;
import java.time.LocalDate;

@SpringBootApplication
public class ApiResTfulV1Application implements CommandLineRunner {

	@Autowired
	private CategoriaRepository categoriaRepository;

	@Autowired
	private FilmeRepository filmeRepository;

	@Autowired
	private UsuarioRepository usuarioRepository;

	public static void main(String[] args) {
		SpringApplication.run(ApiResTfulV1Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		Usuario usuario = new Usuario("admin", "12345");
		usuarioRepository.save(usuario);

		Categoria acao = new Categoria("Acaos", "acaos");
		categoriaRepository.save(acao);

		Categoria comedia = new Categoria("Comedia", "comedia");
		categoriaRepository.save(comedia);

		Categoria aventura = new Categoria("Aventura", "aventura");
		categoriaRepository.save(aventura);

		Filme filme = new Filme(
				"afera.jpg",
				"A Fera",
				"Filme de ação na selva",
				true,
				10,
				BigDecimal.valueOf(19.99),
				LocalDate.of(2023, 4, 26),
				acao);
		filmeRepository.save(filme);

		filme = new Filme(
				"badboys.webp",
				"Bad Boys",
				"Filme de policiais falsos",
				false,
				50,
				BigDecimal.valueOf(1.1),
				LocalDate.of(2023, 5, 22),
				comedia);
		filmeRepository.save(filme);

		filme = new Filme(
				"blackpanther.webp",
				"A Pantera Negra",
				"Sucessora do pantera negra agora tem filme",
				true,
				20,
				BigDecimal.valueOf(4.7),
				LocalDate.of(2023, 3, 24),
				acao);
		filmeRepository.save(filme);

		filme = new Filme(
				"cidadeperdida.jpg",
				"Cidade Perdida",
				"A cidade que foi achada mas ainda ta perdida kkkkk",
				true,
				120,
				BigDecimal.valueOf(4.99),
				LocalDate.of(2023, 3, 12),
				aventura);
		filmeRepository.save(filme);

		filme = new Filme(
				"divertidamente.webp",
				"Divertidamente 2",
				"Agora a criança é maluca e tem crise o tempo todo",
				true,
				30,
				BigDecimal.valueOf(2.5),
				LocalDate.of(2023, 5, 17),
				comedia);
		filmeRepository.save(filme);

		filme = new Filme(
				"doisirmaos.jpg",
				"Dois irmãos",
				"Um filme que conta a historia de dois irmaos",
				true,
				220,
				BigDecimal.valueOf(4.99),
				LocalDate.of(2023, 5, 14),
				acao);
		filmeRepository.save(filme);

		filme = new Filme(
				"drestranho.jpg",
				"Dr. Estranho 2",
				"Dr. Estranho acabando com o universo por causa do homem aranha",
				true,
				35,
				BigDecimal.valueOf(1.05),
				LocalDate.of(2023, 2, 22),
				acao);
		filmeRepository.save(filme);

		filme = new Filme(
				"encanto.jpeg",
				"Encanto",
				"Garota que odeia a familia da disney",
				true,
				720,
				BigDecimal.valueOf(1.85),
				LocalDate.of(2023, 2, 23),
				aventura);
		filmeRepository.save(filme);

		filme = new Filme(
				"esquemaderisco.jpg",
				"Esquema de risco",
				"Invasao a banco e coisa e tal",
				true,
				60,
				BigDecimal.valueOf(5.39),
				LocalDate.of(2023, 3, 28),
				acao);
		filmeRepository.save(filme);

		filme = new Filme(
				"monica.jpeg",
				"Monica",
				"Filme da Monica",
				true,
				95,
				BigDecimal.valueOf(0.56),
				LocalDate.of(2023, 4, 30),
				comedia);
		filmeRepository.save(filme);

		filme = new Filme(
				"planeta.jpg",
				"Planeta dos Macacos",
				"u u u ua a a a",
				true,
				350,
				BigDecimal.valueOf(1.01),
				LocalDate.of(2023, 5, 29),
				aventura);
		filmeRepository.save(filme);

		filme = new Filme(
				"wish.jpeg",
				"Wish",
				"Aquele site de compra lá",
				true,
				240,
				BigDecimal.valueOf(11.23),
				LocalDate.of(2023, 5, 11),
				acao);
		filmeRepository.save(filme);
	}
}
