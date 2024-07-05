import { useMutation, useQueryClient } from "@tanstack/react-query";
import Filme from "../interfaces/filme";
import { URL_PRODUTO } from "../util/constants";
import useAPI from "./useAPI";

const useCadastrarFilme = () => {
  const {cadastrar} = useAPI<Filme>(URL_PRODUTO);

  const queryClient = useQueryClient();

  return useMutation({
    mutationFn: (filme: Filme) => cadastrar(filme),
    onSuccess: () =>
      queryClient.invalidateQueries({
        queryKey: ["filmes"],
      }),
  });
};
export default useCadastrarFilme;
