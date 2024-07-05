import axios, { AxiosRequestConfig } from "axios";
import CustomError from "../util/CustomError";
import { URL_BASE, URL_PRODUTO } from "../util/constants";
import Filme from "../interfaces/filme";

const useAPIFilme = () => {
  const axiosInstance = axios.create({
    baseURL: URL_BASE,
  });

  const recuperarFilmesPorSlugDaCategoria = (slug?: string) =>
    axiosInstance
      .get<Filme[]>(URL_PRODUTO + (slug ? "/slugCategoria/" + slug : ""))
      .then((response) => response.data)
      .catch((error) => {
        if (error.response) {
          throw new CustomError(error.response.data.message, error.response.data.errorCode);
          // significa servidor respondeu
        } else if (error.request) {
          throw error;
          // significa que o servidor não respondeu
        } else {
          throw error;
          // erro desconhecido
        }
      });

  const recuperarFilmesPaginadosPorSluDaCategoria = (config: AxiosRequestConfig) =>
    axiosInstance
      .get<ResultadoPaginado<Filme>>(URL_PRODUTO + "/categoria/paginacao", config)
      .then((response) => response.data)
      .catch((error) => {
        if (error.response) {
          throw new CustomError(error.response.data.message, error.response.data.errorCode);
          // significa servidor respondeu
        } else if (error.request) {
          throw error;
          // significa que o servidor não respondeu
        } else {
          throw error;
          // erro desconhecido
        }
      });

  return { recuperarFilmesPorSlugDaCategoria,
           recuperarFilmesPaginadosPorSluDaCategoria};
};
export default useAPIFilme;
