package main;

import main.data.PaymentDAO;
import main.data.model.Client;
import main.data.model.Payment;
import main.data.model.Product;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * В решении задачи нам важно уверенное владение практиками ООП и хорошее знание языка Java, именно по ним мы оцениваем кандидата.
 Обратите, пожалуйста, на это особое внимание.

 Ниже задача, которую нужно решить.
 В задаче предполагается наличие некоторой системы приема платежей. При попадании в систему платеж передается на обработку. Платежи осуществляются на сумму за некоторую услугу в пользу клиента с лицевым счетом.

 Задача:

 В эту систему нужно добавить подсистему ограничений (лимитов) на прием платежей для борьбы с мошенничеством.
 В решении должна быть возможность определять, например, такие лимиты для платежей:
 1. Не более 5000 руб. днем с 9:00 утра до 23:00 за одну услугу(*);
 2. Не более 1000 руб. ночью с 23:00 до 9:00 утра за одну услугу(*);
 3. Не более 2000 руб. в сутки по одинаковым платежам(**);
 4. Не более 3000 руб. в течение одного часа за одну услугу(*);
 5. Не более 20 одинаковых платежей(**) в сутки;
 6. Не более 30 платежей не более чем на 4000 руб.(***) с 10:00 до 17:00 за одну услугу(*);
 7. Не более 10 платежей не более чем на 3000 руб.(***) в течение двух часов одним клиентом.
 (*) услуга - это, например, пополнение счета мобильного телефона (лицевой счет клиента) провайдера МТС;
 (**) одинаковые платежи - платежи с одинаковыми счетом клиента и услугой;
 (***) сумма указана для совокупности платежей.

 Лимиты должны быть настраиваемые по всем параметрам.

 В случае, если какой-то из лимитов превышен, необходимо переводить "подозрительный" платеж в статус "требует подтверждения". Платеж, прошедший ограничения, должен быть переведен в статус "готов к проведению".

 В решении задачи должны быть юнит-тесты для каждого лимита. Для тестов нужно реализовать примитивную систему приема платежей, в которую в юнит-тестах должны поступать платежи для проверки работоспособности лимитов из списка выше. Дополнительно должен быть реализован хотя бы один юнит-тест, в котором система приема платежей настроена с несколькими разными лимитами одновременно. Для юнит-тестов системе должны быть известны несколько (2-3) клиентов и несколько (2-3) услуг.

 Требования к решению:
 - Задачу необходимо решить на языке Java;
 - Требуется реализовать задачу с юнит-тестами, используя JUnit;
 - Решение должно быть реализовано в парадигме ООП с использованием паттернов проектирования;
 - Все необходимые системы, указанные в задаче, должны быть простыми объектами без использования сторонних технологий;
 - В случае, если требуется внешняя библиотека, например, для работы с датой и временем, проект должен использовать Maven или Gradle.

 Не нужно использовать в решении задачи:
 - Базы данных;
 - Чтение из файлов;
 - Сетевое взаимодействие;
 - Многопоточность;
 - Библиотеки для построения графического пользовательского интерфейса.
 */
public class Main {
    private static Client clients[] = new Client[]{
            new Client(),
            new Client(),
            new Client(),
            new Client(),
            new Client(),
            new Client(),
            new Client(),
            new Client()
    };

    private static Product products[] = new Product[]{
            new Product("MTS"),
            new Product("Mils"),
            new Product("Film"),
            new Product("Car"),
            new Product("Food"),
            new Product("Pizza"),
            new Product("Tourism"),
            new Product("Music"),
    };

    private static Payment payments[] = new Payment[]{
            new Payment(ranValue(), ranTime(), ranProd(), ranCli()),
            new Payment(ranValue(), ranTime(), ranProd(), ranCli()),
            new Payment(ranValue(), ranTime(), ranProd(), ranCli()),
            new Payment(ranValue(), ranTime(), ranProd(), ranCli()),
            new Payment(ranValue(), ranTime(), ranProd(), ranCli())
    };

    public static void main(String[] args) throws InterruptedException {
        BankSystem bankSystem = new BankSystem();

        Arrays.stream(payments).forEach(bankSystem::proccess);

        System.out.println(PaymentDAO.getInstance());

        System.out.println(bankSystem);

    }

    private static Product ranProd() {
        return products[new Random().nextInt(products.length)];
    }

    private static BigDecimal ranValue() {
        return new BigDecimal(new Random().nextInt(5_000));
    }

    private static Client ranCli() {
        return clients[new Random().nextInt(clients.length)];
    }

    private static Instant ranTime() {
        Instant result;

        result = Instant.now()
                .plus(new Random().nextInt(3), ChronoUnit.DAYS)
                .plus(new Random().nextInt(24), ChronoUnit.HOURS);

        return result;
    }
}
