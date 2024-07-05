import { useQuery } from "@tanstack/react-query";
import useAPI from "./useAPI";
import { URL_PRODUTO } from "../util/constants";
import Filme from "../interfaces/filme";

const useFilmes = () => {
  const {recuperar} = useAPI<Filme>(URL_PRODUTO);
  return useQuery({
    queryKey: ["filmes"],
    queryFn: () => recuperar(),
    staleTime: 10_000,
  })
};
export default useFilmes;
