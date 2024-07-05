import { useQuery } from "@tanstack/react-query";
import Filme from "../interfaces/filme";
import { URL_PRODUTO } from "../util/constants";
import useAPI from "./useAPI";

interface QueryString {
  pagina: number;
  tamanho: number;
  nome: string;
}

const useFilmesComPaginacao = (query: QueryString) => {
  const { recuperarPagina } = useAPI<Filme>(URL_PRODUTO);

  return useQuery({
    queryKey: ["filmes", "paginacao", query],
    queryFn: () =>
      recuperarPagina({
        params: {
          // pagina: query.pagina,
          // tamanho: query.tamanho
          ...query,
        },
      }),
    staleTime: 10_000,
  });
};
export default useFilmesComPaginacao;
