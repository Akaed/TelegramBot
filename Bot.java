
import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class Bot extends TelegramLongPollingBot {
    public static void main(String[] args) {
        ApiContextInitializer.init();
        TelegramBotsApi telegramBotsApi = new TelegramBotsApi();
        try{
            telegramBotsApi.registerBot(new Bot()); // регистрация бота

        } catch (TelegramApiException e) {// обработка исключений
            e.printStackTrace();
        }
    }


    public void SendMsg(Message message,String text){
        SendMessage sendMessage = new SendMessage(); // создаем объект класса сендмесседж
        sendMessage.enableMarkdown(true);//включаем возможность разметки
        sendMessage.setChatId(message.getChatId().toString()); // из сообщения получаем айди???(в какой конкретно чат мы должны отправить ответ)
        sendMessage.setReplyToMessageId(message.getMessageId());//определили айди сообщения на котоырй мы будем отвечать
        sendMessage.setText(text) // установим сообщению текст который мы отправляли в этот метод
        try {     // устанавливаем отправку сообщения
            execute(sendMessage);

        } catch (TelegramApiException e) {

            e.printStackTrace();
        }


}
    public void onUpdateReceived(Update update) {
        Message message = update.getMessage(); // создаем объект класса месседж и помещаем туда текст
        if (message != null && message.hasText()) {
            switch (message.getText()){
                case "/help";
                    SendMsg(message , "Чем я могу помочь?");
                    break;
                case "/settings"
                    SendMsg(message , "Чем будем настраивать?");
                default:
            }
        }

    }

    public String getBotUsername() {
        return "2chlinks";
    }

    public String getBotToken() {
        return "1232090313:AAHSwRWKEMErRx4riIVwopRM7kdvfSDwVJo";
    }
}
