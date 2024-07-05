import { NavLink, Outlet } from "react-router-dom";

const HomePage = () => {
  return (
    <div className="row">
      <div className="col-lg-2">
        <h5>Categorias</h5>
        <div className="nav flex-column nav-pills">
          <NavLink aria-current="page" className="nav-link" to="/">
            Todos
          </NavLink>
          <NavLink aria-current="page" className="nav-link" to="/acaos">
            Ação
          </NavLink>
          <NavLink aria-current="page" className="nav-link" to="/comedia">
            Comédia
          </NavLink>
          <NavLink aria-current="page" className="nav-link" to="/aventura">
            Aventura
          </NavLink>
        </div>
      </div>
      <div className="col-lg-10">
        <Outlet />
      </div>
    </div>
  );
};

export default HomePage;
