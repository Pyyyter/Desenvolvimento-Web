import useFilmesComPaginacao from "../hooks/useFilmesComPaginacao";
import useFilmeStore from "../store/filmeStore";

const Paginacao = () => {
  const pagina = useFilmeStore((s) => s.pagina);
  const tamanho = useFilmeStore((s) => s.tamanho);
  const nome = useFilmeStore((s) => s.nome);
  const setPagina = useFilmeStore((s) => s.setPagina);

  const tratarPaginacao = (pagina: number) => {
    setPagina(pagina);
  };

  const {
    data: resultadoPaginado,
    isPending: carregandoFilmes,
    error: errorFilmes,
  } = useFilmesComPaginacao({ pagina, tamanho, nome });

  if (carregandoFilmes) return <h6>Carregando...</h6>;
  if (errorFilmes) throw errorFilmes;

  const totalDePaginas = resultadoPaginado.totalDePaginas;

  const arrayDePaginas = [];

  for (let i = 0; i < totalDePaginas; i++) {
    arrayDePaginas.push(
      <li key={i} className="page-item">
        <a
          onClick={() => tratarPaginacao(i)}
          className={pagina === i ? "page-link active" : "page-link"}
          href="#"
        >
          {i + 1}
        </a>
      </li>
    );
  }

  if (totalDePaginas < 2) return null;

  return (
    <nav aria-label="Paginação">
      <ul className="pagination">
        <li className={pagina === 0 ? "page-item disabled" : "page-item"}>
          <a onClick={() => tratarPaginacao(pagina - 1)} className="page-link">
            Anterior
          </a>
        </li>
        {arrayDePaginas}
        <li className={pagina === totalDePaginas - 1 ? "page-item disabled" : "page-item"}>
          <a onClick={() => tratarPaginacao(pagina + 1)} className="page-link">
            Próxima
          </a>
        </li>
      </ul>
    </nav>
  );
};
export default Paginacao;
