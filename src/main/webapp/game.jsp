<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <title>Космический Квест</title>
</head>
<body style="font-family: sans-serif; max-width: 600px; margin: 50px auto; padding: 20px;">
    <h3>${sessionScope.state.question}</h3>
    <c:choose>
        <c:when test="${sessionScope.state.end}">
            <p>
                <a href="game?action=restart" style="display: inline-block; padding: 6px 15px; border: 1px solid #777; background: #eee; color: black; text-decoration: none;">
                    Начать заново
                </a>
            </p>
        </c:when>
        <c:otherwise>
            <form action="game" method="post">
                <div style="margin-bottom: 15px;">

                    <label style="display: block; margin: 8px 0; cursor: pointer;">
                        <input type="radio" name="choice" value="1" required style="margin-right: 5px;">
                        ${sessionScope.state.option1}
                    </label>
                    <label style="display: block; margin: 8px 0; cursor: pointer;">
                        <input type="radio" name="choice" value="2" style="margin-right: 5px;">
                        ${sessionScope.state.option2}
                    </label>
                </div>
                <button type="submit" style="padding: 5px 15px; cursor: pointer;">Ответить</button>
            </form>
        </c:otherwise>
    </c:choose>
    <br><br><br>
    <div style="border: 1px solid red; padding: 12px; width: fit-content; min-width: 250px; line-height: 1.5;">
        <b>Статистика:</b><br>
        IP address: <i>${requestScope.userIp}</i><br>
        Имя в игре: <i>${sessionScope.playerName}</i><br>
        Количество игр: <i>${sessionScope.gamesCount}</i>
    </div>
</body>
</html>