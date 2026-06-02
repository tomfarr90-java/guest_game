<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <title>Звездный Квест — Приветствие</title>
</head>
<body style="font-family: sans-serif; max-width: 600px; margin: 50px auto; padding: 20px; line-height: 1.6;">
    <h2>Добро пожаловать в Звездный Квест!</h2>
    <p>
        <b>Предыстория:</b> Вы очнулись на космическом корабле в полной темноте под тихий писк приборов.
        Голова раскалывается, вы абсолютно ничего не помните. На главном экране мигает неизвестный
        сигнал от приближающегося неопознанного объекта...
    </p>
    <hr>
    <form action="game" method="get">
        <p>
            <label for="name">Перед началом идентификации введите ваше имя:</label><br>
            <input type="text" id="name" name="playerName" required style="padding: 8px; width: 100%; margin-top: 5px; box-sizing: border-box;">
        </p>
        <button type="submit" style="padding: 10px 20px; background-color: #007bff; color: white; border: none; cursor: pointer;">
            Начать игру
        </button>
    </form>
</body>
</html>