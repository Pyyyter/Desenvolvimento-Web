import { create } from "zustand";
import Filme from "../interfaces/filme";

interface FilmeStore {
    pagina: number;
    tamanho: number;
    nome: string;
    filmeSelecionado: Filme;

    setPagina: (pagina: number) => void;
    setTamanho: (tamanho: number) => void;
    setNome: (nome: string) => void;
    setFilmeSelecionado: (filmeSelecionado: Filme) => void;
}

const useFilmeStore = create<FilmeStore>((set) => ({
    pagina: 0,
    tamanho: 5,
    nome: "",
    filmeSelecionado: {} as Filme,

    setPagina: (pagina: number) => set(() => ({pagina: pagina})),
    setTamanho: (tamanho: number) => set(() => ({tamanho: tamanho})),
    setNome: (nome: string) => set(() => ({nome: nome})),
    setFilmeSelecionado: (filmeSelecionado: Filme) => set(() => ({filmeSelecionado: filmeSelecionado}))
})) 
export default useFilmeStore;