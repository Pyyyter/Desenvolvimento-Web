import { useMutation, useQueryClient } from "@tanstack/react-query";
import Filme from "../interfaces/filme";
import { URL_PRODUTO } from "../util/constants";
import useAPI from "./useAPI";

const useRemoverFilme = () => {
  const {remover} = useAPI<Filme>(URL_PRODUTO);

  const queryClient = useQueryClient();

  return useMutation({
    mutationFn: (id: number) => remover(id),
    onSuccess: () =>
      queryClient.invalidateQueries({
        queryKey: ["filmes"],
      }),
  });
};
export default useRemoverFilme;
