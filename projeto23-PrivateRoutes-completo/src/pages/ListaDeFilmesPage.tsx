import CadastroDeFilmesForm from "../components/CadastroDeFilmesForm";
import Paginacao from "../components/Paginacao";
import Pesquisa from "../components/Pesquisa";
import TabelaDeFilmes from "../components/TabelaDeFilmes";

const ListaDeFilmesPage = () => {
  return (
    <>
      <h4>Cadastro de Filmes</h4>
      <hr className="mt-1" />
      <CadastroDeFilmesForm />

      <h4>Lista de Filmes</h4>
      <hr className="mt-1" />
      <Pesquisa />
      <TabelaDeFilmes />
      <Paginacao />
    </>
  );
};
export default ListaDeFilmesPage;
