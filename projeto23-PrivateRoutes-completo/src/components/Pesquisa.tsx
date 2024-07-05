import { FormEvent, useRef } from "react";
import useFilmeStore from "../store/filmeStore";

const Pesquisa = () => {
  const nome = useFilmeStore((s) => s.nome);
  const setNome = useFilmeStore((s) => s.setNome);
  const setPagina = useFilmeStore((s) => s.setPagina);

  const tratarNome = (nome: string) => {
    setNome(nome);
    setPagina(0);
  };

  const submit = (event: FormEvent) => {
    event.preventDefault();
    tratarNome(nomeRef.current!.value);
  };

  const nomeRef = useRef<HTMLInputElement>(null);

  return (
    <form onSubmit={submit} className="d-flex mb-3">
      <input
        defaultValue={nome}
        ref={nomeRef}
        type="text"
        className="form-control form-control-sm me-3"
        placeholder="Pesquisar..."
      />
      <button className="btn btn-primary btn-sm" type="submit">
        Pesquisar
      </button>
    </form>
  );
};
export default Pesquisa;
