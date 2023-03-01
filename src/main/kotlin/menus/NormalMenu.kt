package menus

import auth.domain.entities.User
import auth.telegram.Strings
import changeinfo.telegram.sendFieldsToChange
import com.ithersta.tgbotapi.menu.builders.MenuBuilder
import common.telegram.DialogState
import generated.menu
import menus.states.MenuState
import notifications.telegram.sendNotificationPreferencesMessage

val normalMenu = menu<User.Normal>(Strings.RoleMenu.Normal, DialogState.Empty) {
    extracted()
}

fun <S : User> MenuBuilder<DialogState, User, S>.extracted() {
    submenu(
        MenuStrings.Questions.Question,
        MenuStrings.Questions.QuestionDescription,
        MenuState.Questions.Main
    ) {
        submenu(
            MenuStrings.Questions.Get,
            MenuStrings.Questions.Description,
            MenuState.Questions.GetQuestion
        ) {
            submenu(
                MenuStrings.Questions.MyQuestions.Main,
                MenuStrings.Questions.MyQuestions.Description,
                MenuState.Questions.GetMyQuestion
            ) {
                button( // TODO
                    MenuStrings.Questions.MyQuestions.ActualQuestions,
                    DialogState.Empty
                ) // /ну видимо хендлер надо тоже или стейт нормальный реализовать
                button(
                    MenuStrings.Questions.MyQuestions.OldQuestions,
                    DialogState.Empty
                ) // /ну видимо хендлер надо тоже или стейт нормальный реализовать
            }
            button(
                MenuStrings.Questions.InterestingQuestions,
                DialogState.Empty
            ) // /ну видимо хендлер надо тоже или стейт нормальный реализовать
        }
        button(
            MenuStrings.Questions.Ask,
            MenuState.Questions.AskQuestion
        )
    }
    button(MenuStrings.Notifications.Main) { sendNotificationPreferencesMessage(it.chat.id) }
    button(MenuStrings.ChangeAccountInfo) { sendFieldsToChange(it) }
    button(
        MenuStrings.Events,
        MenuState.Events
    )
}
