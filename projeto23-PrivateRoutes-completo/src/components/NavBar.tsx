import { Link } from "react-router-dom";
import blockbuster from "/blockbuster.png";
import carrinho from "/carrinho.png";
import useFilmesComPaginacao from "../hooks/useFilmesComPaginacao";
import useFilmeStore from "../store/filmeStore";

function NavBar() {
  const pagina = useFilmeStore((s) => s.pagina);
  const tamanho = useFilmeStore((s) => s.tamanho);
  const nome = useFilmeStore((s) => s.nome);

  const {
    data: resultadoPaginado,
    isPending: carregandoFilmes,
    error: errorFilmes,
  } = useFilmesComPaginacao({ pagina, tamanho, nome });

  if (carregandoFilmes) return <h6>Carregando...</h6>;
  if (errorFilmes) throw errorFilmes;

  const filmes = resultadoPaginado.itens;

  return (
    <>
      <div className="container mt-3 mb-2">
        <div className="row">
          <div className="col-3 d-flex align-items-center">
            <Link to="/" style={{ textDecoration: "none", fontSize: "16px" }}>
              <img className="d-none d-md-block" src={blockbuster} style={{ width: "5vw" }} />
            </Link>
          </div>
          <div className="col-6">
            <ul style={{ listStyleType: "none", marginBottom: "0px" }}>
              <li className="mt-2 d-flex justify-content-center">
                Faça seu
                <Link className="ms-1" to="/login" style={{ textDecoration: "none" }}>
                  login!
                </Link>
              </li>
              <li className="d-flex justify-content-center">
                <Link to="/cadastrar-filme" style={{ textDecoration: "none" }}>
                  Cadastrar filme
                </Link>
              </li>
              <li className="d-flex justify-content-center">
                <Link to="/listar-filmes" style={{ textDecoration: "none" }}>
                  Listar filmes
                </Link>
              </li>
            </ul>
          </div>
          <div className="col-3 d-flex align-items-center justify-content-end">
            <ul style={{ listStyleType: "none", marginBottom: "0px" }}>
              <li className="d-flex justify-content-center">
                <Link to="/carrinho" style={{ textDecoration: "none" }}>
                  <img className="d-none d-md-block" src={carrinho} style={{ width: "35px" }} />
                  Carrinho
                </Link>
              </li>
              <li className="d-flex justify-content-center">
                R${" "}
                {filmes
                  .reduce((total, filme) => total + filme.qtdEstoque * filme.preco, 0)
                  .toLocaleString("pt-BR", {
                    minimumFractionDigits: 2,
                    maximumFractionDigits: 2,
                    useGrouping: true,
                  })}
              </li>
            </ul>
          </div>
        </div>
      </div>

      <div className="bg-danger" style={{ padding: "3px" }}></div>
    </>
  );
}
export default NavBar;
