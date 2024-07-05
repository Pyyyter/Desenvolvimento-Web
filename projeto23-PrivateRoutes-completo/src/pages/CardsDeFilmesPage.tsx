import { useParams } from "react-router-dom";
import Card from "../components/Card";
import useFilmesPaginadosPorSlugDaCategoria from "../hooks/useFilmesPaginadosPorSlugDaCategoria";
import InfiniteScroll from "react-infinite-scroll-component";

const CardsDeFilmesPage = () => {
  const { slug } = useParams();
  const tamanho = 12;
  const {
    data,
    isPending: carregandoFilmes,
    error: errorfilmes,
    hasNextPage,
    fetchNextPage
  } = useFilmesPaginadosPorSlugDaCategoria({ tamanho, slug });

  if (carregandoFilmes) return <h6>Carregando...</h6>;
  if (errorfilmes) throw errorfilmes;

  return (
    <InfiniteScroll
      dataLength={data.pages.reduce((total, page) => total + page.itens.length, 0) }
      hasMore={hasNextPage}
      next={() => fetchNextPage()}
      loader={<h6>Carregando...</h6>}>

      <h5>{slug ? slug.charAt(0).toUpperCase() + slug.slice(1) : "Filmes"}</h5>
      <div className="row mb-3">
        {data.pages.map((page) =>
          page.itens.map((filme) => (
            <div key={filme.id} className="col-lg-2 col-md-3 col-sm-4 col-6 mb-3">
              <Card
                imagem={filme.imagem}
                titulo={filme.nome}
                descricao={filme.descricao}
                preco={filme.preco.toLocaleString("pt-BR", {
                  minimumFractionDigits: 2,
                  maximumFractionDigits: 2,
                  useGrouping: true,
                })}
                footer={
                  <input type="button" className="btn btn-primary btn-sm w-100" value="Comprar" />
                }
              />
            </div>
          ))
        )}
      </div>
    </InfiniteScroll>
  );
};
export default CardsDeFilmesPage;
