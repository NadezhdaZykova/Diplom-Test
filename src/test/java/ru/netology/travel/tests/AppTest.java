package ru.netology.travel.tests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.travel.pages.CreditPage;
import ru.netology.travel.pages.PayPage;
import ru.netology.travel.pages.data;
import ru.netology.travel.tests.SqlGetters;

import static com.codeborne.selenide.Selenide.*;
import static org.junit.jupiter.api.Assertions.*;

public class AppTest {
    //  TODO    mysql    OR    postgresql
    private String database = "mysql";

    SqlGetters sqlGetters = new SqlGetters();

    @BeforeEach
    public void setUpAll() {
        open("http://localhost:8080");
    }

    @Test
    void successfulDebitBuy() {
        var order = new PayPage();
        order.Clickbuybutton();
        order.CardNumber(data.CardNumber.getFirstCardNumber());
        order.inputmonth(data.cardMonth.getThisMonth());
        order.inputyear(data.cardyear.getThisYear());
        order.inputOwner(data.Owner.getCorrectOwner());
        order.inputcode(data.cardCVV.getCorrectcode());
        order.Clickcontinue();
        order.verifySuccess();
        assertEquals("APPROVED", sqlGetters.getStatus(database));
    }

    @Test
    void declinedDebitBuy() {
        var order = new PayPage();
        order.Clickbuybutton();
        order.CardNumber(data.CardNumber.getSecondCardNumber());
        order.inputmonth(data.cardMonth.getThisMonth());
        order.inputyear(data.cardyear.getThisYear());
        order.inputOwner(data.Owner.getCorrectOwner());
        order.inputcode(data.cardCVV.getCorrectcode());
        order.Clickcontinue();
        order.verifyError();
        assertEquals("DECLINED", sqlGetters.getStatus(database));
    }

    @Test
    void successfulCreditBuy() {
        var order = new CreditPage();
        order.Clickcreditbutton();
        order.CardNumber(data.CardNumber.getFirstCardNumber());
        order.inputmonth(data.cardMonth.getThisMonth());
        order.inputyear(data.cardyear.getThisYear());
        order.inputOwner(data.Owner.getCorrectOwner());
        order.inputcode(data.cardCVV.getCorrectcode());
        order.Clickcontinue();
        order.verifySuccess();
        assertEquals("APPROVED", sqlGetters.getStatus(database));
    }

    @Test
    void declinedCreditBuy() {
        var order = new CreditPage();
        order.Clickcreditbutton();
        order.CardNumber(data.CardNumber.getSecondCardNumber());
        order.inputmonth(data.cardMonth.getThisMonth());
        order.inputyear(data.cardyear.getThisYear());
        order.inputOwner(data.Owner.getCorrectOwner());
        order.inputcode(data.cardCVV.getCorrectcode());
        order.Clickcontinue();
        order.verifyError();
        assertEquals("DECLINED", sqlGetters.getStatus(database));
    }

    @Test
    void emptyForm() {
        var order = new PayPage();
        order.Clickbuybutton();
        order.CardNumber(data.CardNumber.getIncorrectCardNumber());
        order.inputmonth(data.cardMonth.getIncorrectMonth());
        order.inputyear(data.cardyear.getIncorrectYear());
        order.inputOwner(data.Owner.getIncorrectOwner());
        order.inputcode(data.cardCVV.getIncorrectcode());
        order.Clickcontinue();
        order.cardValidate();
        order.ownerValidate();
        order.monthValidate();
        order.yearValidate();
        order.codeValidate();
    }

    @Test
    void validateMonth() {
        var order = new PayPage();
        order.Clickbuybutton();
        order.CardNumber(data.CardNumber.getFirstCardNumber());
        order.inputmonth(data.cardMonth.getFalseMonth());
        order.inputyear(data.cardyear.getThisYear());
        order.inputOwner(data.Owner.getCorrectOwner());
        order.inputcode(data.cardCVV.getCorrectcode());
        order.Clickcontinue();
        order.monthValidate();
    }

    @Test
    void wrongSymbolsMonth() {
        var order = new PayPage();
        order.Clickbuybutton();
        order.CardNumber(data.CardNumber.getFirstCardNumber());
        order.inputmonth(data.cardMonth.getSpecialsCardMonth());
        order.inputyear(data.cardyear.getThisYear());
        order.inputOwner(data.Owner.getCorrectOwner());
        order.inputcode(data.cardCVV.getCorrectcode());
        order.Clickcontinue();
        order.monthValidate();
    }

    @Test
    void zeroMonth() {
        var order = new PayPage();
        order.Clickbuybutton();
        order.CardNumber(data.CardNumber.getFirstCardNumber());
        order.inputmonth(data.cardMonth.getIncorrectMonth());
        order.inputyear(data.cardyear.getThisYear());
        order.inputOwner(data.Owner.getCorrectOwner());
        order.inputcode(data.cardCVV.getCorrectcode());
        order.Clickcontinue();
        order.monthValidate();
    }

    @Test
    void validateYear() {
        var order = new PayPage();
        order.Clickbuybutton();
        order.CardNumber(data.CardNumber.getFirstCardNumber());
        order.inputmonth(data.cardMonth.getThisMonth());
        order.inputyear(data.cardyear.getIncorrectYear());
        order.inputOwner(data.Owner.getCorrectOwner());
        order.inputcode(data.cardCVV.getCorrectcode());
        order.Clickcontinue();
        order.yearValidate();
    }

    @Test
    void wrongSymbolsYear() {
        var order = new PayPage();
        order.Clickbuybutton();
        order.CardNumber(data.CardNumber.getFirstCardNumber());
        order.inputmonth(data.cardMonth.getThisMonth());
        order.inputyear(data.cardyear.getSpecialsCardYear());
        order.inputOwner(data.Owner.getCorrectOwner());
        order.inputcode(data.cardCVV.getCorrectcode());
        order.Clickcontinue();
        order.yearValidate();
    }

    @Test
    void validateOwner() {
        var order = new PayPage();
        order.Clickbuybutton();
        order.CardNumber(data.CardNumber.getFirstCardNumber());
        order.inputmonth(data.cardMonth.getThisMonth());
        order.inputyear(data.cardyear.getThisYear());
        order.inputOwner(data.Owner.getIncorrectOwner());
        order.inputcode(data.cardCVV.getCorrectcode());
        order.Clickcontinue();
        order.ownerValidate();
    }

    @Test
    void wrongSymbolsOwner() {
        var order = new PayPage();
        order.Clickbuybutton();
        order.CardNumber(data.CardNumber.getFirstCardNumber());
        order.inputmonth(data.cardMonth.getThisMonth());
        order.inputyear(data.cardyear.getThisYear());
        order.inputOwner(data.Owner.getSpecialsOwner());
        order.inputcode(data.cardCVV.getCorrectcode());
        order.ownerValidate();
    }

    @Test
    void validateCode() {
        var order = new PayPage();
        order.Clickbuybutton();
        order.CardNumber(data.CardNumber.getFirstCardNumber());
        order.inputmonth(data.cardMonth.getThisMonth());
        order.inputyear(data.cardyear.getThisYear());
        order.inputOwner(data.Owner.getCorrectOwner());
        order.inputcode(data.cardCVV.getIncorrectcode());
        order.Clickcontinue();
        order.codeValidate();
    }
}
