package fr.istic.vv;

public class NotSuchElementException extends Exception  {
    String message;

    NotSuchElementException(String str) {
        message = " : " + str;
    }

    NotSuchElementException() {
        String message = "";
    }

    public String toString() {
        return ("NotSuchElementException" + message);
    }
}
