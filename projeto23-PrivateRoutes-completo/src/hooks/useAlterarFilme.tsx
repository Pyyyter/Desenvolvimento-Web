import { useMutation, useQueryClient } from "@tanstack/react-query";
import Filme from "../interfaces/filme";
import { URL_PRODUTO } from "../util/constants";
import useAPI from "./useAPI";

const useAlterarFilme = () => {
  const {alterar} = useAPI<Filme>(URL_PRODUTO);

  const queryClient = useQueryClient();

  return useMutation({
    mutationFn: (filme: Filme) => alterar(filme),
    onSuccess: () =>
      queryClient.invalidateQueries({
        queryKey: ["filmes"],
      }),
  });
};
export default useAlterarFilme;
