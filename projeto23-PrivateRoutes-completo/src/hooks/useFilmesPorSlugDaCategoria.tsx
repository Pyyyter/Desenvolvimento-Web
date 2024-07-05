import { useQuery } from "@tanstack/react-query";
import useAPIFilme from "./useAPIFilme";

const useFilmesPorSlugDaCategoria = (slug?: string) => {
  const {recuperarFilmesPorSlugDaCategoria} = useAPIFilme();
  return useQuery({
    queryKey: slug ? ["filmes", "slugCategoria", slug] : ["filmes"],
    queryFn: () => recuperarFilmesPorSlugDaCategoria(slug),
    staleTime: 10_000,
  })
};
export default useFilmesPorSlugDaCategoria;
