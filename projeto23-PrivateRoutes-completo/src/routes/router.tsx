import { createBrowserRouter } from 'react-router-dom';
import CarrinhoPage from '../pages/CarrinhoPage';
import HomePage from '../pages/HomePage';
import LoginPage from '../pages/LoginPage';
import Layout from './Layout';
import CadastroDeFilmesPage from '../pages/CadastroDeFilmesPage';
import ListaDeFilmesPage from '../pages/ListaDeFilmesPage';
import ErrorPage from '../pages/ErrorPage';
import CardsDeFilmesPage from '../pages/CardsDeFilmesPage';
import PrivateRoutes from './PrivateRoutes';

const router = createBrowserRouter([
    {
      path: "/",
      element: <Layout />,
      errorElement: <ErrorPage />,
      children: [
        {
          path: "",
          element: <HomePage />,
          children: [
            {
              path: ":slug?",
              element: <CardsDeFilmesPage />,
            },
          ],
        },
        { path: "listar-filmes", element: <ListaDeFilmesPage />},
        { path: "login", element: <LoginPage /> },
      ],
    },
    {
      path: "/",
      element: <PrivateRoutes />,
      errorElement: <ErrorPage />,
      children: [
        { path: "cadastrar-filme", element: <CadastroDeFilmesPage /> },
        { path: "carrinho", element: <CarrinhoPage /> },
      ],
    },
  ]);
  export default router;
  