package ru.jru.module3;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;


/**
 * Сервлет для управления процессом игры.
 * Обрабатывает шаги квеста, считает количество игр и сохраняет всё в сессию.
 */
@WebServlet("/game")
public class GameServlet extends HttpServlet {

    /**
     * Отвечает за старт игры, сброс статистики при перезапуске
     * и отображение страницы game.jsp.
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();

        String ipAddress = req.getRemoteAddr();
        req.setAttribute("userIp", ipAddress);

        if (req.getParameter("playerName") != null && session.getAttribute("state") == null) {
            session.setAttribute("playerName", req.getParameter("playerName"));
            session.setAttribute("gamesCount", 0);
            session.setAttribute("state", GameState.START);
        }

        if ("restart".equals(req.getParameter("action"))) {
            Integer count = (Integer) session.getAttribute("gamesCount");
            session.setAttribute("gamesCount", count != null ? count + 1 : 1);
            session.setAttribute("state", GameState.START);
        }

        req.getRequestDispatcher("/game.jsp").forward(req, resp);
    }

    /**
     * Принимает выбранный игроком ответ, меняет состояние игры
     * и делает редирект, чтобы страница не дублировалась при обновлении.
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        HttpSession session = req.getSession();

        GameState currentState = (GameState) session.getAttribute("state");
        String choice = req.getParameter("choice");

        if (currentState != null && choice != null) {
            GameState nextState = getNextState(currentState, choice);
            session.setAttribute("state", nextState);
        }

        resp.sendRedirect(req.getContextPath() + "/game");
    }

    /**
     * Логика переходов квеста.
     * Вынесена отдельно, чтобы её можно было проверить через JUnit-тесты.
     *
     * @param current текущий шаг игры
     * @param choice  выбранный вариант (1 или 2)
     * @return следующий шаг игры
     */
    public GameState getNextState(GameState current, String choice) {
        if (current == null || choice == null) {
            return GameState.START;
        }

        return switch (current) {
            case START -> "1".equals(choice) ? GameState.BRIDGE_CHOICE : GameState.FAIL_REJECTED;
            case BRIDGE_CHOICE -> "1".equals(choice) ? GameState.IDENTITY_CHOICE : GameState.FAIL_NO_NEGOTIATIONS;
            case IDENTITY_CHOICE -> "1".equals(choice) ? GameState.WIN_HOME : GameState.FAIL_LIED;
            default -> current;
        };
    }
}

