import dayjs from "dayjs";
import deleteIcon from "../assets/skin/database_delete.png";
import useFilmesComPaginacao from "../hooks/useFilmesComPaginacao";
import useFilmeStore from "../store/filmeStore";
import useRemoverFilme from "../hooks/useRemoverFilme";

const TabelaDeFilmes = () => {
  const pagina = useFilmeStore((s) => s.pagina);
  const tamanho = useFilmeStore((s) => s.tamanho);
  const nome = useFilmeStore((s) => s.nome);
  const setPagina = useFilmeStore((s) => s.setPagina);
  const setFilmeSelecionado = useFilmeStore((s) => s.setFilmeSelecionado);

  const { mutate: removerFilme } = useRemoverFilme();

  const tratarRemocao = (id: number) => {
    removerFilme(id);
    setPagina(0);
  };

  const {
    data: resultadoPaginado,
    isPending: carregandoFilmes,
    error: errorFilmes,
  } = useFilmesComPaginacao({ pagina, tamanho, nome });

  if (carregandoFilmes) return <h6>Carregando...</h6>;
  if (errorFilmes) throw errorFilmes;

  const filmes = resultadoPaginado.itens;

  return (
    <table className="table table-responsive table-sm table-hover table-bordered">
      <thead>
        <tr>
          <th className="align-middle text-center">Id</th>
          <th className="align-middle text-center">Imagem</th>
          <th className="align-middle text-center">Categoria</th>
          <th className="align-middle text-center">Nome</th>
          <th className="align-middle text-center">Data de Cadastro</th>
          <th className="align-middle text-center">Quantidade</th>
          <th className="align-middle text-center">Preço</th>
          <th className="align-middle text-center">Ação</th>
        </tr>
      </thead>
      <tbody>
        {filmes.map((filme) => (
          <tr key={filme.id}>
            <td width="8%" className="align-middle text-center">
              {filme.id}
            </td>
            <td width="12%" className="align-middle text-center">
              <img src={filme.imagem} width={45} />
            </td>
            <td width="12%" className="align-middle text-center">
              {filme.categoria.nome}
            </td>
            <td width="20%" className="align-middle">
              <a
                className="link-underline"
                onClick={() => {
                  setFilmeSelecionado(filme);
                }}
              >
                {filme.nome}
              </a>
            </td>
            <td width="12%" className="align-middle text-center">
              {dayjs(filme.dataCadastro).format("DD/MM/YYYY")}
            </td>
            <td width="12%" className="align-middle text-center">
              {filme.qtdEstoque.toLocaleString("pt-BR", {
                useGrouping: true,
              })}
            </td>
            <td width="12%" className="align-middle text-end pe-3">
              {filme.preco.toLocaleString("pt-BR", {
                minimumFractionDigits: 2,
                maximumFractionDigits: 2,
                useGrouping: true,
              })}
            </td>
            <td width="12%" className="align-middle text-center">
              <button onClick={() => tratarRemocao(filme.id!)} className="btn btn-danger btn-sm">
                <img src={deleteIcon} /> Remover
              </button>
            </td>
          </tr>
        ))}
      </tbody>
      <tfoot>
        <tr>
          <td colSpan={4}></td>
          <td className="align-middle text-center fw-bold">Total...</td>
          <td className="align-middle text-center fw-bold" colSpan={2}>
            R${" "}
            {filmes
              .reduce((total, filme) => total + filme.qtdEstoque * filme.preco, 0)
              .toLocaleString("pt-BR", {
                minimumFractionDigits: 2,
                maximumFractionDigits: 2,
                useGrouping: true,
              })}
          </td>
          <td></td>
        </tr>
      </tfoot>
    </table>
  );
};
export default TabelaDeFilmes;
