import Categoria from "./categoria";

interface Filme {
  id?: number;
  imagem: string;
  categoria: Categoria;
  nome: string;
  descricao: string;
  disponivel: boolean;
  dataCadastro: Date;
  qtdEstoque: number;
  preco: number;
}
export default Filme;
