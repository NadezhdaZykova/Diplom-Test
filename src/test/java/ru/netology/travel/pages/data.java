package ru.netology.travel.pages;

import com.github.javafaker.Faker;
import lombok.Value;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class data {
    private data() {
    }

    @Value
    public static class CardNumber {
        private String cardNumber;
        public static CardNumber getFirstCardNumber() {
            return new CardNumber("4444 4444 4444 4441");
        }
        public static CardNumber getSecondCardNumber() {
            return new CardNumber("4444 4444 4444 4442");
        }
        public static CardNumber getIncorrectCardNumber() {
            return new CardNumber("");
        }
    }
    @Value
    public static class Owner {
        private String Owner;
        public static Owner getCorrectOwner() {
            Faker faker;
            faker = new Faker();
            String customerName = faker.name().fullName();
            return new Owner(customerName);
        }
        public static Owner getIncorrectOwner() {
            return new Owner("");
        }
        public static Owner getSpecialsOwner() {
            return new Owner("@@");
        }
    }
    @Value
    public static class cardCVV {
        private String cardCVV;
        public static cardCVV getCorrectcode() {
            Faker faker;
            faker = new Faker();
            int intcode = faker.number().numberBetween(100, 999);
            String cardCVV = Integer.toString(intcode);
            return new cardCVV(cardCVV);
        }
        public static cardCVV getIncorrectcode() {
            return new cardCVV("");
        }
        public static cardCVV getSpecialsCardcode() {
            return new cardCVV("@@");
        }
    }
    @Value
    public static class cardyear {
        private String cardyear;
        public static cardyear getThisYear() {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yy");
            String thisYear = LocalDate.now().format(formatter);
            return new cardyear(thisYear);
        }
        public static cardyear getIncorrectYear() {
            return new cardyear("");
        }
        public static cardyear getSpecialsCardYear() {
            return new cardyear("@@");
        }
    }
    @Value
    public static class cardMonth {
        private String cardMonth;
        public static cardMonth getFalseMonth() {
            return new cardMonth("13");
        }
        public static cardMonth getIncorrectMonth() {
            return new cardMonth("");
        }
        public static cardMonth getThisMonth() {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM");
            String thisMonth = LocalDate.now().format(formatter);
            return new cardMonth(thisMonth);
        }
        public static cardMonth getSpecialsCardMonth() {
            return new cardMonth("@@");
        }

    }
}