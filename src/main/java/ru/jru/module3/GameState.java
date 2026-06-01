package ru.jru.module3;

/**
 * Шаги игрового квеста, содержащие сюжетные вопросы и ответы.
 */
@SuppressWarnings("unused")
public enum GameState {
    START("Ты потерял память. Принять вызов НЛО?", "Принять вызов", "Отклонить вызов"),
    BRIDGE_CHOICE("Ты принял вызов. Поднимаешься на мостик к капитану?", "Подняться на мостик", "Отказаться подниматься на мостик"),
    IDENTITY_CHOICE("Ты поднялся на мостик. Ты кто?", "Рассказать правду о себе", "Солгать о себе"),

    // Финальные экраны не имеют вариантов ответа (передаем null)
    WIN_HOME("Тебя вернули домой. Победа", null, null),
    FAIL_REJECTED("Ты отклонил вызов. Поражение", null, null),
    FAIL_NO_NEGOTIATIONS("Ты не пошел на переговоры. Поражение", null, null),
    FAIL_LIED("Твою ложь разоблачили. Поражение", null, null);

    private final String question;
    private final String option1;
    private final String option2;

    GameState(String question, String option1, String option2) {
        this.question = question;
        this.option1 = option1;
        this.option2 = option2;
    }

    public String getQuestion() { return question; }
    public String getOption1() { return option1; }
    public String getOption2() { return option2; }

    /**
     * Метод определяет завершенность игры.
     */
    public boolean isEnd() {
        return option1 == null;
    }

}
