import { useInfiniteQuery } from "@tanstack/react-query";
import Filme from "../interfaces/filme";
import useAPIFilme from "./useAPIFilme";

interface QueryString {
  tamanho: number;
  slug?: string;
}

const useFilmesPaginadosPorSlugDaCategoria = (query: QueryString) => {
  const { recuperarFilmesPaginadosPorSluDaCategoria } = useAPIFilme();

  return useInfiniteQuery<ResultadoPaginado<Filme>>({
    queryKey: ["filmes", "categoria", "paginacao", query],
    queryFn: ({ pageParam }) =>
      recuperarFilmesPaginadosPorSluDaCategoria({
        params: {
          // pagina: query.pagina,
          // tamanho: query.tamanho
          pagina: pageParam,
          ...query,
        },
      }),
    initialPageParam: 0,
    staleTime: 10_000,
    getNextPageParam: (lastPage, allPages) => {
      return lastPage.paginaCorrente < lastPage.totalDePaginas - 1
        ? lastPage.paginaCorrente + 1
        : undefined;
    },
  });
};
export default useFilmesPaginadosPorSlugDaCategoria;
